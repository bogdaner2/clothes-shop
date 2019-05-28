package com.clothesshop.client.Controllers;


import com.clothesshop.client.DAL.AuthRepository;
import com.clothesshop.client.DAL.ProfileRepository;
import com.clothesshop.client.DAL.RoleRepository;
import com.clothesshop.client.Dto.RegistrationFormDto;
import com.clothesshop.client.Models.Authorities;
import com.clothesshop.client.Models.Profile;
import com.clothesshop.client.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfileRepository profileRepository;

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
    public String registration_post(@ModelAttribute RegistrationFormDto form, String role) {
        Users user = new Users(form.getUsername(), form.getPassword());
        Authorities auth = new Authorities(form.getUsername(),"ROLE_"+ role.toUpperCase());
        Profile profile = new Profile(form.getUsername(),form.getName(),form.getEmail(),form.getPhone());

        user.setEnabled(true);

        authRepository.save(user);
        roleRepository.save(auth);
        profileRepository.save(profile);

        return "redirect:/login";
    }
}
