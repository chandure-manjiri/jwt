package com.jwt.jwt.JwtConfig;

import com.jwt.jwt.Entity.UserData;
import com.jwt.jwt.Repository.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDataRepo userDataRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataRepo.findUserByUsername(username);
        System.out.println("userData: " + userData);
        if (userData != null) {
            Collection<String> AutorityListOfUser = Arrays.asList(userData.getRole().split(","));
            return new User(username, new BCryptPasswordEncoder().encode(userData.getPassword()), AutorityListOfUser.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        } else {
             throw new UsernameNotFoundException("user not found with username: "+username);
        }

    }

}
