package com.konorin.shops_api.Services;

import com.konorin.shops_api.Dto.ShopProductDto;
import com.konorin.shops_api.Models.Product;
import com.konorin.shops_api.Models.Shop;
import com.konorin.shops_api.Models.ShopProduct;
import com.konorin.shops_api.Repository.ShopProductRepository;
import com.konorin.shops_api.Repository.ShopRepository;
import com.konorin.shops_api.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopProductRepository shopProductRepository;

    public Iterable<ShopProduct> GetAll(){
        return shopProductRepository.findByIsDeletedFalse();
    }

    public ShopProduct Get(Integer id) throws Exception{
        Optional<ShopProduct> product = shopProductRepository.findByIdAndIsDeletedFalse(id);
        if (!product.isPresent())
            throw new Exception("No item with this id");

        return product.get();
    }

    public ShopProduct Create(ShopProductDto productDto) throws Exception {

        try {
            Shop shop = shopRepository.findById(productDto.getShopId()).get();
            Product product = productRepository.findById(productDto.getProductId()).get();

            ShopProduct newProduct = new ShopProduct(shop, product);

            return shopProductRepository.save(newProduct);
        }
        catch (Exception e) {
            throw new Exception("Item was not created");
        }
    }

    public ShopProduct Update(Integer id, ShopProductDto productDto) throws Exception{

        try {
            ShopProduct updatedProduct = Get(id);

            Shop shop = shopRepository.findById(productDto.getShopId()).get();
            Product product = productRepository.findById(productDto.getProductId()).get();

            updatedProduct.setProduct(product);
            updatedProduct.setShop(shop);

            shopProductRepository.save(updatedProduct);

            return updatedProduct;
        }
        catch (Exception e){
            throw new Exception("Item was not updated");
        }
    }

    public Boolean Delete(Integer id) throws Exception{
        try{
            ShopProduct shopProduct = Get(id);

            shopProduct.setDeleted(true);

            shopProductRepository.save(shopProduct);
        } catch (Exception e) {
            throw new Exception("Item was not deleted");
        }
        return true;
    }
}