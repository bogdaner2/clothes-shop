package com.clothesshop.client.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthController {

//    @Autowired
//    AuthService authService;
//
//    @Autowired
//    RoleService roleService;

//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    private LoadBalancerClient client;
//
    @RequestMapping(value = "/instances")
    public String getInstancesRun(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
        return "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, String error, String logout) {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration_post(@ModelAttribute String object,String role) {

        String url = getInstancesRun();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

//        String response = this.restTemplate.exchange(String.format("%s/api/brands", url),
//                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
//                }).getBody();
//        user.setEnabled(true);
//        authService.save(user);
//        Authorities a = new Authorities(user.getUsername(),"ROLE_"+role.toUpperCase());
//        roleService.save(a);
        return "redirect:/login";
    }
}
