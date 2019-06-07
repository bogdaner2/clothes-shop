package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.AuthRepository;
import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.DAL.RoleRepository;
import com.clothesshop.client.Dto.EditProfileDto;
import com.clothesshop.client.Models.Profile;
import com.clothesshop.client.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
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

import java.util.Optional;

@Controller
@RequestMapping(path="userProfile")
public class UserProfileController {

    @Autowired
    ProfileRepository repo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthRepository authRepository;

//    @Autowired
//    private LoadBalancerClient client;
//
//        public String getInstancesRun(){
//        ServiceInstance instance = client.choose("clothes_shop_api");
//        return instance.getUri().toString();
    // }
    public String getInstancesRun(){
        return "http://localhost:5000";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model, String error, String logout) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        Profile profile = repo.getByUsername(name);
        if(profile == null) {
            return "error";
        }
        model.addAttribute("name", profile.getName());
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(getInstancesRun() + "/config", String.class);
        String header = response.getBody();

        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("profile", profile);
        model.addAttribute("headerTitle", "bg-" + header);

        return "profile";
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id, @ModelAttribute EditProfileDto profile) {

        Profile p = repo.findById(id).get();

        p.setName(profile.getName());
        p.setEmail(profile.getEmail());
        p.setPhone(profile.getPhone());

        repo.save(p);

        return new ModelAndView("redirect:/userProfile");
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {

        Optional<Profile> p = repo.findById(id);
        if(!p.isPresent()) {
            return new ModelAndView("redirect:/login");
        }

        repo.delete(p.get());
        Users user = authRepository.findByUsername(p.get().getUsername());

        user.setEnabled(false);

        authRepository.save(user);

        return new ModelAndView("redirect:/logout");

    }

}
