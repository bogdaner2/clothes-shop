package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.Dto.ProductDto;
import com.clothesshop.client.Models.Brand;
import com.clothesshop.client.Models.Product;
import com.clothesshop.client.Models.Profile;
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
@RequestMapping(path="products")
public class ProductController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private LoadBalancerClient client;

//    public String getInstancesRun(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
//    }
    public String getInstancesRun(){
        return "http://localhost:5000";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        String productsUrl = String.format("%s/api/products", getInstancesRun());
        String brandsUrl = String.format("%s/api/brands", getInstancesRun());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> productsResponse = restTemplate.getForEntity(productsUrl, String.class);
        Product[] products = DeserializeProductList(productsResponse.getBody());

        ResponseEntity<String> response = restTemplate.getForEntity(brandsUrl, String.class);
        Brand[] brands = DeserializeBrandsList(response.getBody());


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        String header = restTemplate.getForEntity(getInstancesRun() + "/config", String.class).getBody();

        Profile profile = profileRepository.getByUsername(name);
        if(profile == null) {
            return "error";
        }
        model.addAttribute("name", profile.getName());
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("headerTitle", "bg-" + header);

        return "productsList";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute ProductDto Product) {
        String url = String.format("%s/api/products/", getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductDto> entity = new HttpEntity<>(Product, headers);

        String response = restTemplate
                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {

        String url = String.format("%s/api/products/" + id, getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate
                .exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>(){})
                .getBody();

        if(Boolean.parseBoolean(response)) {
            return new ModelAndView("redirect:/products");
        }
        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("productsDetail");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        String productUrl = String.format("%s/api/products/" + id, getInstancesRun());
        String brandsUrl = String.format("%s/api/brands", getInstancesRun());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> productsResponse = restTemplate.getForEntity(productUrl, String.class);
        Product product = Deserialize(productsResponse.getBody());

        ResponseEntity<String> response = restTemplate.getForEntity(brandsUrl, String.class);
        Brand[] brands = DeserializeBrandsList(response.getBody());
        Profile profile = profileRepository.getByUsername(name);
        String header = restTemplate.getForEntity(getInstancesRun() + "/config", String.class).getBody();

        model.addObject("name", profile.getName());
        model.addObject("isAdmin", iaAdmin);
        model.addObject("product", product);
        model.addObject("brands", brands);
        model.addObject("headerTitle", "bg-" + header);

        return model;
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute ProductDto product) {

        String url = String.format("%s/api/products/"+ id, getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductDto> entity = new HttpEntity<>(product, headers);

        String response = restTemplate
                .exchange(url,HttpMethod.PUT, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/products");
    }

    private Product[] DeserializeProductList(String productString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product[] products= mapper.readValue(productString, Product[].class);
            return  products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Brand[] DeserializeBrandsList(String brandString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Brand[] brands = mapper.readValue(brandString, Brand[].class);
            return  brands;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    private Product Deserialize(String productString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Product Product = mapper.readValue(productString, Product.class);
            return  Product;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}