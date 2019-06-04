package com.clothesshop.client.Controllers;

import com.clothesshop.client.DAL.AuthRepository;
import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.DAL.RoleRepository;
import com.clothesshop.client.Dto.EditProfileDto;
import com.clothesshop.client.Dto.ProductDto;
import com.clothesshop.client.Models.Brand;
import com.clothesshop.client.Models.Product;
import com.clothesshop.client.Models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model, String error, String logout) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        boolean iaAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        Profile profile = repo.getByUsername(name);
        model.addAttribute("name", profile.getName());
        model.addAttribute("isAdmin", iaAdmin);
        model.addAttribute("profile", profile);

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
        authRepository.deleteByUsername(p.get().getUsername());
        roleRepository.deleteByUsername(p.get().getUsername());

        return new ModelAndView("redirect:/logout");

    }

}
