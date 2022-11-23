package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Profile;
import ru.kata.spring.boot_security.demo.repo.ProfileRepo;
import ru.kata.spring.boot_security.demo.service.ProfileService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ProfileRepo profileRepo;
    private AuthenticationProvider authenticationProvider;

    @GetMapping("/admin")
    public String home(Model model, Principal principal) {
        model.addAttribute("users", profileRepo.findAll());
        return "/admin";
    }

    @GetMapping("/user")
    public String home(Principal principal, Model model) {
        Profile profile = profileRepo.findByUsername(principal.getName());
        model.addAttribute("profile", profile);
        return "/user";
    }
}
