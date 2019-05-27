package com.clothesshop.client.Controllers;


import com.clothesshop.client.DAL.AuthService;
import com.clothesshop.client.DAL.RoleService;
import com.clothesshop.client.Models.Authorities;
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
    AuthService authService;

    @Autowired
    RoleService roleService;

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
    public String registration_post(@ModelAttribute Users user, String role) {
        user.setEnabled(true);
        authService.save(user);
        Authorities a = new Authorities(user.getUsername(),"ROLE_"+role.toUpperCase());
        roleService.save(a);
        return "redirect:/login";
    }
}
