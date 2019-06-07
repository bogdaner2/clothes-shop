package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.Models.Log;
import com.clothesshop.client.Models.Profile;
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
public class LogController {
    @Autowired
    private LoadBalancerClient client;

    @Autowired
    ProfileRepository profileRepository;

//    public String getInstancesRun(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
//    }
    public String getInstancesRun(){
        return "http://localhost:5000";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        String url = String.format("%s/api/logs", getInstancesRun());
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Log[] logs = DeserializeList(response.getBody());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        Profile profile = profileRepository.getByUsername(name);

        String header = restTemplate.getForEntity(getInstancesRun() + "/config", String.class).getBody();
        model.addAttribute("headerTitle", "bg-" + header);

        model.addAttribute("name", profile.getName());
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