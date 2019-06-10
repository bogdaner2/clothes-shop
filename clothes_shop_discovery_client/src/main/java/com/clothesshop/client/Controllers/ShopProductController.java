package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.Dto.ProductDto;
import com.clothesshop.client.Dto.ShopProductDto;
import com.clothesshop.client.Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(path="shopProducts")
public class ShopProductController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private LoadBalancerClient client;

    //    public String getInstancesRunOfClothesShopApi(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
//    }
    public String getInstancesRunOfClothesShopApi(){
        return "http://localhost:5000";
    }

    //    public String getInstancesRunOfShopsApi(){
//        ServiceInstance instance = client.choose("shops_api");
//        return instance.getUri().toString();
//    }
    public String getInstancesRunOfShopsApi(){
        return "http://localhost:5100";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        String shopProductsUrl = String.format("%s/api/shopProducts", getInstancesRunOfShopsApi());
        String productUrl = String.format("%s/api/products", getInstancesRunOfClothesShopApi());
        String shopsUrl = String.format("%s/api/shops", getInstancesRunOfShopsApi());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> shopProductsResponse = restTemplate.getForEntity(shopProductsUrl, String.class);
        ShopProduct[] shopProducts = DeserializeShopProductList(shopProductsResponse.getBody());

        ResponseEntity<String> shopResponse = restTemplate.getForEntity(shopsUrl, String.class);
        Shop[] shops = DeserializeShopList(shopResponse.getBody());

        ResponseEntity<String> productResponse = restTemplate.getForEntity(productUrl, String.class);
        Product[] products = DeserializeProductList(productResponse.getBody());


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        String header = restTemplate.getForEntity(getInstancesRunOfClothesShopApi() + "/config", String.class).getBody();

        Profile profile = profileRepository.getByUsername(name);
        if(profile == null) {
            return "error";
        }

        String allowDelete = restTemplate.getForEntity(getInstancesRunOfShopsApi() + "/config", String.class).getBody();


        model.addAttribute("name", profile.getName());
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("headerTitle", "bg-" + header);

        model.addAttribute("shops", shops);
        model.addAttribute("products", products);
        model.addAttribute("shopProducts", shopProducts);

        model.addAttribute("allowDelete", allowDelete);

        return "shops/shopProductList";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute ShopProductDto shopProduct) {
        String url = String.format("%s/api/shopProducts/", getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ShopProductDto> entity = new HttpEntity<>(shopProduct, headers);

        restTemplate
                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/shopProducts");
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {

        String url = String.format("%s/api/shopProducts/" + id, getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate
                .exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>(){})
                .getBody();

        if(Boolean.parseBoolean(response)) {
            return new ModelAndView("redirect:/shopProducts");
        }
        return new ModelAndView("redirect:/shopProducts");
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("shops/shopProductDetail");
        RestTemplate restTemplate = new RestTemplate();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        Profile profile = profileRepository.getByUsername(name);
        String header = restTemplate.getForEntity(getInstancesRunOfClothesShopApi() + "/config", String.class).getBody();

        model.addObject("name", profile.getName());
        model.addObject("isAdmin", iaAdmin);
        model.addObject("headerTitle", "bg-" + header);

        String shopProductUrl = String.format("%s/api/shopProducts/" + id, getInstancesRunOfShopsApi());
        String productUrl = String.format("%s/api/products", getInstancesRunOfClothesShopApi());
        String shopsUrl = String.format("%s/api/shops", getInstancesRunOfShopsApi());

        ResponseEntity<String> shopResponse = restTemplate.getForEntity(shopsUrl, String.class);
        Shop[] shops = DeserializeShopList(shopResponse.getBody());

        ResponseEntity<String> productResponse = restTemplate.getForEntity(productUrl, String.class);
        Product[] products = DeserializeProductList(productResponse.getBody());

        ResponseEntity<String> productsResponse = restTemplate.getForEntity(shopProductUrl, String.class);
        ShopProduct product = Deserialize(productsResponse.getBody());

        model.addObject("shopProduct", product);
        model.addObject("shops", shops);
        model.addObject("products", products);

        return model;
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute ShopProductDto product) {

        String url = String.format("%s/api/shopProducts/"+ id, getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ShopProductDto> entity = new HttpEntity<>(product, headers);

        restTemplate
                .exchange(url,HttpMethod.PUT, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/shopProducts");
    }

    private ShopProduct[] DeserializeShopProductList(String productString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ShopProduct[] products= mapper.readValue(productString, ShopProduct[].class);
            return  products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Product[] DeserializeProductList(String productString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product[] product = mapper.readValue(productString, Product[].class);
            return  product;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Shop[] DeserializeShopList(String shopsString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Shop[] shops = mapper.readValue(shopsString, Shop[].class);
            return  shops;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private ShopProduct Deserialize(String productString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ShopProduct ShopProduct = mapper.readValue(productString, ShopProduct.class);
            return  ShopProduct;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}