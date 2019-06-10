package com.konorin.shops_api.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konorin.shops_api.Models.Shop;
import com.konorin.shops_api.Models.Log;
import com.konorin.shops_api.Producer.Producer;
import com.konorin.shops_api.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    Producer publisher;

    @Value("${jsa.rabbitmq.queue.creation_logs}")
    String creationLogsQueue;

    @Value("${jsa.rabbitmq.queue.update_logs}")
    String updateLogsQueue;

    @Value("${jsa.rabbitmq.queue.deletion_logs}")
    String deletionLogsQueue;

    public Iterable<Shop> GetAll(){
        return shopRepository.findByIsDeletedFalse();
    }

    public Shop Get(Integer id) throws Exception{

        Optional<Shop> brand = shopRepository.findByIdAndIsDeletedFalse(id);
        if (!brand.isPresent())
            throw new Exception("No item with this id");

        return brand.get();
    }

    public Shop Create(Shop shop) throws Exception{
        if(shop.getName() == "") throw new Exception("You should to input name");
        logItem(shop, "CREATE", creationLogsQueue);
        return shopRepository.save(shop);
    }

    public Shop Update(Integer id, Shop shop)throws Exception{
        if(shop.getName() == "") throw new Exception("You should to input name");
        try {
            Shop updatedShop = Get(id);

            updatedShop.setName(shop.getName());
            updatedShop.setLocation(shop.getLocation());
            updatedShop.setOpen(shop.isOpen());

            shopRepository.save(updatedShop);
            logItem(updatedShop, "UPDATE", updateLogsQueue);

            return updatedShop;
        }
        catch (Exception e) {
            throw new Exception("Item was not updated");
        }
    }

    public Boolean Delete(Integer id) throws Exception{
        try{
            Shop shop = Get(id);

            shop.setIsDeleted(true);
            logItem(shop, "DELETE", deletionLogsQueue);

            shopRepository.save(shop);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    private void logItem(Shop shop, String action, String queueName) {
        Log log = new Log();
        ObjectMapper mapper = new ObjectMapper();

        log.setActionType(action);

        String logMessage = null;
        try {
            log.setMessage("Object: " + mapper.writeValueAsString(shop));
            logMessage = mapper.writeValueAsString(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
        publisher.produceMessage(logMessage, queueName);
    }
}
