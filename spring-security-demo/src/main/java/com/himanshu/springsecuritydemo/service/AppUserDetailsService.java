package com.himanshu.springsecuritydemo.service;

import com.himanshu.springsecuritydemo.appwrappers.AppUserWrapper;
import com.himanshu.springsecuritydemo.entity.AppUser;
import com.himanshu.springsecuritydemo.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {

    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUserByUsername = appUserRepository.findAppUserByUsername(username);
        return new AppUserWrapper(appUserByUsername);
    }
}
