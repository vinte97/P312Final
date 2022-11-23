package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Profile;
import ru.kata.spring.boot_security.demo.repo.ProfileRepo;

import java.util.Collections;
import java.util.Set;

@Service
public class ProfileService implements UserDetailsService {

    @Autowired
    private ProfileRepo profileRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile tmpProfile = profileRepo.findByUsername(username);
        if (tmpProfile == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                tmpProfile.getUsername(),
                tmpProfile.getPassword(),
                tmpProfile.getAuthorities()
        );
    }
}
