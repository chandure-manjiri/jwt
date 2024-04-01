package com.jwt.jwt.Controller;

import com.jwt.jwt.Entity.UserData;
import com.jwt.jwt.Exception.UnauthorizedException;
import com.jwt.jwt.JwtConfig.JwtResponseDto;
import com.jwt.jwt.JwtConfig.JwtTokenUtil;
import com.jwt.jwt.JwtConfig.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/login")
    public JwtResponseDto createAuthToken(@RequestBody UserData userData)throws Exception{
       authenticate(userData.getUsername(), userData.getPassword());

       final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userData.getUsername());

       final String token = jwtTokenUtil.generateToken(userDetails);
       return new JwtResponseDto(token);
    }

    private void authenticate(String username, String password)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new UnauthorizedException("USER_DISABLED");
        }catch (BadCredentialsException e){
            throw new UnauthorizedException("Invalid Credentials");
        }
    }

}
