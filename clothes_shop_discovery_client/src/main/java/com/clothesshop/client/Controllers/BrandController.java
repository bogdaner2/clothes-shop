package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.Models.Brand;
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
@RequestMapping(path="brands")
public class BrandController {


    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private LoadBalancerClient client;

    public String getInstancesRun(){
        // ServiceInstance instance = client.choose("clothes_shop_api");
        // TEMP
        return "http://localhost:5000";
        // return instance.getUri().toString();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model, String error, String logout) {

        String url = String.format("%s/api/brands", getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Brand[] brands = DeserializeList(response.getBody());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        String header = restTemplate.getForEntity(getInstancesRun() + "/config", String.class).getBody();
        Profile profile = profileRepository.getByUsername(name);
        if(profile == null) {
            return "error";
        }
        model.addAttribute("headerTitle", "bg-" + header);
        model.addAttribute("name", profile.getName());
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("brands", brands);

        return "brandsList";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Brand brand) {
        String url = String.format("%s/api/brands/", getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Brand> entity = new HttpEntity<>(brand, headers);

        restTemplate
                .exchange(url,HttpMethod.POST, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/brands");
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {

        String url = String.format("%s/api/brands/" + id, getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate
                .exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>(){})
                .getBody();

        if(Boolean.parseBoolean(response)) {
            return new ModelAndView("redirect:/brands");
        }
        return new ModelAndView("redirect:/brands");
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("brandsDetail");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        String url = String.format("%s/api/brands/" + id, getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        String brand = restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {})
                .getBody();

        Profile profile = profileRepository.getByUsername(name);
        String header = restTemplate.getForEntity(getInstancesRun() + "/config", String.class).getBody();
        model.addObject("headerTitle", "bg-" + header);

        model.addObject("name", profile.getName());
        model.addObject("isAdmin", iaAdmin);

        model.addObject("brand", Deserialize(brand));
        return model;
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Brand brand) {

        brand.setId(id);

        String url = String.format("%s/api/brands/"+ id, getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Brand> entity = new HttpEntity<>(brand, headers);

        String response = restTemplate
                .exchange(url,HttpMethod.PUT, entity, new ParameterizedTypeReference<String>(){})
                .getBody();

        return new ModelAndView("redirect:/brands");
    }

    private Brand[] DeserializeList(String brandString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Brand[] brands= mapper.readValue(brandString, Brand[].class);
            return  brands;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Brand Deserialize(String brandString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Brand brand = mapper.readValue(brandString, Brand.class);
            return  brand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}