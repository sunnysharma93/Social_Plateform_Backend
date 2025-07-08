package com.zosh.social.media.plateformw.controller;

import com.zosh.social.media.plateformw.config.JwtProvider;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.reponse.AuthResponse;
import com.zosh.social.media.plateformw.repository.UserRepository;
import com.zosh.social.media.plateformw.request.LoginRequest;
import com.zosh.social.media.plateformw.service.CustomerUserDetailsservice;
import com.zosh.social.media.plateformw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.sax.SAXResult;
import java.util.prefs.BackingStoreException;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsservice customerUserDetailsservice;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("email already used with another account");
        }

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));


        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Register success");
        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) throws BackingStoreException {

        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Login success");
        return res;
    }

    private Authentication authenticate(String email, String password) throws BackingStoreException {
        UserDetails userDetails = customerUserDetailsservice.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invaled username");
        }

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BackingStoreException("password not matched");
            }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
