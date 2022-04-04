package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This is a controller class for token.
 * It is used to generate a jwt token if a user login
 * with correct credentials
 */

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {
    @Autowired
    private LoginService loginService;

    private static final Logger LOGGER = LogManager.getLogger(TokenController.class);

    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";

    /**
     * This is an endpoint for generate a token if the correct login credentials it presented.
     * @param loginRequest
     * @return Loginresponse where the jwtoken is stored
     * @throws Exception
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LoginResponse generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {

        LOGGER.info("Generating token");
        LoginResponse loginResponse = loginService.doLoginRequest(loginRequest);

        if (loginResponse.getLoginStatus() == "Success") {
            loginResponse.setJWToken(generateToken(loginRequest.getUsername()));
            return loginResponse;
        }
        loginResponse.setJWToken("Access denied, wrong credentials....");
        return loginResponse;
    }

    /**
     * This is the helping method for generateToken
     * @param userId
     * @return a jwtoken
     * @throws Exception
     */

    public String generateToken(String userId) throws Exception {
        Key key = Keys.hmacShaKeyFor(keyStr.getBytes("UTF-8"));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userId", userId);
        claims.put("authorities", grantedAuthorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(key)
                .compact();
    }
}

