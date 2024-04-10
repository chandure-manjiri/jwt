package com.jwt.jwt.Controller;

import com.jwt.jwt.Dto.UserDataDto;
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
    public JwtResponseDto createAuthToken(@RequestBody UserDataDto userDataDto)throws Exception{
       authenticate(userDataDto.getUsername(), userDataDto.getPassword());

       final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userDataDto.getUsername());

       final String token = jwtTokenUtil.generateToken(userDetails);
       return new JwtResponseDto(token);
    }

    private void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            throw new UnauthorizedException(e.toString());
        }
    }

}
