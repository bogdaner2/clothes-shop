//package com.clothesshop.client.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//public class MainController {
//
//    @Bean
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    private LoadBalancerClient client;
//
//    @RequestMapping(value = "/instances")
//    public String getInstancesRun(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
//    }
//
//    @RequestMapping(value = "/brands/{id}", method = RequestMethod.GET)
//    public String getBrand(@PathVariable Integer id) {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/brands/" + id, url),
//                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/brands", method = RequestMethod.GET)
//    public String getBrands() {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/brands", url),
//                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/brands", method = RequestMethod.POST)
//    public String createBrand(@RequestBody String object) {
//        String url = getInstancesRun();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(object, headers);
//
//        String response = this.restTemplate.exchange(String.format("%s/api/brands", url),
//                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/brands/{id}", method = RequestMethod.PUT)
//    public String updateBrand(@RequestBody String object, @PathVariable Integer id) {
//        String url = getInstancesRun();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(object, headers);
//
//        String response = this.restTemplate.exchange(String.format("%s/api/brands/" + id, url),
//                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/brands/{id}", method = RequestMethod.DELETE)
//    public String deleteBrand(@PathVariable Integer id) {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/brands/"+ id, url),
//                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
//    public String getProduct(@PathVariable Long id) {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/products/" + id, url),
//                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//
//        return response;
//    }
//
//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public String getProducts() {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/products", url),
//                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/products", method = RequestMethod.POST)
//    public String createProduct(@RequestBody String object) {
//        String url = getInstancesRun();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(object, headers);
//
//        String response = this.restTemplate.exchange(String.format("%s/api/products", url),
//                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
//    public String updateProduct(@RequestBody String object, @PathVariable Long id) {
//        String url = getInstancesRun();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(object, headers);
//
//        String response = this.restTemplate.exchange(String.format("%s/api/products/%s", url, id),
//                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//    public String deleteProduct(@PathVariable Long id) {
//        String url = getInstancesRun();
//
//        String response = this.restTemplate.exchange(String.format("%s/api/products/%s", url, id),
//                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
//                }).getBody();
//
//        return response;
//    }
//}
