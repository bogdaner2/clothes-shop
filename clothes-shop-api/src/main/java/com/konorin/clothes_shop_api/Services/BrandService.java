package com.konorin.clothes_shop_api.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konorin.clothes_shop_api.Models.Brand;
import com.konorin.clothes_shop_api.Models.Log;
import com.konorin.clothes_shop_api.Producer.Producer;
import com.konorin.clothes_shop_api.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    Producer publisher;

    @Value("${jsa.rabbitmq.queue.creation_logs}")
    String creationLogsQueue;

    @Value("${jsa.rabbitmq.queue.update_logs}")
    String updateLogsQueue;

    @Value("${jsa.rabbitmq.queue.deletion_logs}")
    String deletionLogsQueue;

    public Iterable<Brand> GetAll(){
        return brandRepository.findByIsDeletedFalse();
    }

    public Brand Get(Integer id) throws Exception{

        Optional<Brand> brand = brandRepository.findByIdAndIsDeletedFalse(id);
        if (!brand.isPresent())
            throw new Exception("No item with this id");

        return brand.get();
    }

    public Brand Create(Brand brand) throws Exception{
        if(brand.getName() == "") throw new Exception("You should to input name");
        logItem(brand, "CREATE", creationLogsQueue);
        return brandRepository.save(brand);
    }

    public Brand Update(Integer id, Brand brand)throws Exception{
        if(brand.getName() == "") throw new Exception("You should to input name");
        try {
            Brand updatedBrand = Get(id);

            updatedBrand.setName(brand.getName());
            updatedBrand.setHistory(brand.getHistory());
            updatedBrand.setFoundationYear(brand.getFoundationYear());

            brandRepository.save(updatedBrand);
            logItem(brand, "UPDATE", updateLogsQueue);

            return updatedBrand;
        }
        catch (Exception e) {
            throw new Exception("Item was not updated");
        }
    }

    public Boolean Delete(Integer id) throws Exception{
        try{
            Brand brand = Get(id);

            brand.setIsDeleted(true);
            logItem(brand, "DELETE", deletionLogsQueue);

            brandRepository.save(brand);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    private void logItem(Brand brand, String action, String queueName) {
        Log log = new Log();
        ObjectMapper mapper = new ObjectMapper();

        log.setActionType(action);

        String logMessage = null;
        try {
            log.setMessage("Object: " + mapper.writeValueAsString(brand));
            logMessage = mapper.writeValueAsString(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
        publisher.produceMessage(logMessage, queueName);
    }
}
