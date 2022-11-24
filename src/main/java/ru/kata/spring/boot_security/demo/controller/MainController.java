package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Profile;
import ru.kata.spring.boot_security.demo.repo.ProfileRepo;
import ru.kata.spring.boot_security.demo.service.ProfileService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ProfileRepo profileRepo;
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/admin")
    public String home(Model model, Principal principal) {
        model.addAttribute("users", profileService.getAllProfiles());
        return "/admin";
    }

    @GetMapping("/user")
    public String home(Principal principal, Model model) {
        Profile profile = profileRepo.findByUsername(principal.getName());
        model.addAttribute("profile", profile);
        return "/user";
    }
}
