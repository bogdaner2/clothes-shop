package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.Models.Shop;
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
@RequestMapping(path="shops")
public class ShopController {


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
    public String all(Model model, String error, String logout) {

        String url = String.format("%s/api/shops", getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Shop[] shops = DeserializeList(response.getBody());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        String header = restTemplate.getForEntity(getInstancesRunOfClothesShopApi() + "/config", String.class).getBody();
        Profile profile = profileRepository.getByUsername(name);
        if(profile == null) {
            return "error";
        }
        model.addAttribute("headerTitle", "bg-" + header);
        model.addAttribute("name", profile.getName());
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("shops", shops);

        return "shops/shopsList";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Shop shop) {
        String url = String.format("%s/api/shops/", getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Shop> entity = new HttpEntity<>(shop, headers);

        restTemplate
                .exchange(url,HttpMethod.POST, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/shops");
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {

        String url = String.format("%s/api/shops/" + id, getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate
                .exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>(){})
                .getBody();

        if(Boolean.parseBoolean(response)) {
            return new ModelAndView("redirect:/shops");
        }
        return new ModelAndView("redirect:/shops");
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("shops/shopsDetail");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        String url = String.format("%s/api/shops/" + id, getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        String shop = restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {})
                .getBody();

        Profile profile = profileRepository.getByUsername(name);
        String header = restTemplate.getForEntity(getInstancesRunOfClothesShopApi() + "/config", String.class).getBody();
        model.addObject("headerTitle", "bg-" + header);

        model.addObject("name", profile.getName());
        model.addObject("isAdmin", iaAdmin);

        model.addObject("shop", Deserialize(shop));
        return model;
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Shop shop) {

        shop.setId(id);

        String url = String.format("%s/api/shops/"+ id, getInstancesRunOfShopsApi());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Shop> entity = new HttpEntity<>(shop, headers);

        String response = restTemplate
                .exchange(url,HttpMethod.PUT, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/shops");
    }

    private Shop[] DeserializeList(String brandString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Shop[] shops= mapper.readValue(brandString, Shop[].class);
            return  shops;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Shop Deserialize(String brandString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Shop shop = mapper.readValue(brandString, Shop.class);
            return  shop;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}