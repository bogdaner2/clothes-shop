package com.clothesshop.client.Controllers;

import com.clothesshop.client.Models.Brand;
import com.clothesshop.client.Models.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@RequestMapping(path="logs")
public class LogsController {
    @Autowired
    private LoadBalancerClient client;

    public String getInstancesRun(){
        ServiceInstance instance = client.choose("clothes_shop_api");
        // TEMP
        return "http://localhost:5000";
        // return instance.getUri().toString();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model, String error, String logout) {

        String url = String.format("%s/api/logs", getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Log[] logs = DeserializeList(response.getBody());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("name", name);
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("logs", logs);

        return "logs";
    }

    private Log[] DeserializeList(String logsString)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Log[] logs = mapper.readValue(logsString, Log[].class);
            return logs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}