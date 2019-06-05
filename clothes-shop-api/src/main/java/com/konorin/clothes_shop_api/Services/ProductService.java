package com.konorin.clothes_shop_api.Services;

import com.konorin.clothes_shop_api.Dto.ProductDto;
import com.konorin.clothes_shop_api.Models.Brand;
import com.konorin.clothes_shop_api.Models.Product;
import com.konorin.clothes_shop_api.Repository.BrandRepository;
import com.konorin.clothes_shop_api.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;

    public Iterable<Product> GetAll(){
        return productRepository.findByIsDeletedFalse();
    }

    public Product Get(Integer id) throws Exception{
        Optional<Product> product = productRepository.findByIdAndIsDeletedFalse(id);
        if (!product.isPresent())
            throw new Exception("No item with this id");

        return product.get();
    }

    public Product Create(ProductDto product) throws Exception {
        if(product.getPrice() == 0) throw new Exception("You should to input price");

        try {
            Brand brand = brandRepository.findById(product.getBrandId()).get();

            Product newProduct = new Product(brand, product.getPrice(), product.getType());

            return productRepository.save(newProduct);
        }
        catch (Exception e) {
            throw new Exception("Item was not created");
        }
    }

    public Product Update(Integer id, ProductDto product) throws Exception{
        if(product.getPrice() == 0) throw new Exception("You should to input price");

        try {
            Product updatedProduct = Get(id);

            Brand brand = brandRepository.findById(product.getBrandId()).get();

            updatedProduct.setBrand(brand);
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setType(product.getType());

            productRepository.save(updatedProduct);

            return updatedProduct;
        }
        catch (Exception e){
            throw new Exception("Item was not updated");
        }
    }

    public Boolean Delete(Integer id) throws Exception{
        try{
            Product product = Get(id);

            product.setIsDeleted(true);

            productRepository.save(product);
        } catch (Exception e) {
            throw new Exception("Item was not deleted");
        }
        return true;
    }
}