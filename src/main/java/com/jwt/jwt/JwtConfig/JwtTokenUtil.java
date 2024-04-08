package com.jwt.jwt.JwtConfig;

import com.jwt.jwt.Entity.UserData;
import com.jwt.jwt.Repository.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenUtil{
    public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;

    @Autowired
    UserDataRepo userDataRepo;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        UserData user = userDataRepo.findUserByUsername(userDetails.getUsername());
        Object role = user.getRole();
        claims.put("Role",role);
        return doGenerateToken(claims, userDetails.getUsername());
    }
  private String doGenerateToken(Map<String, Object> claims, String subject) {

      byte[] decodedKey = secret.getBytes();
      SecretKey secretKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());

      return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }



}
