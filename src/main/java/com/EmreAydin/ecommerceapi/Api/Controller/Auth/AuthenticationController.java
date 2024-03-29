package com.EmreAydin.ecommerceapi.Api.Controller.Auth;

import com.EmreAydin.ecommerceapi.Api.Model.LoginBody;
import com.EmreAydin.ecommerceapi.Api.Model.LoginResponse;
import com.EmreAydin.ecommerceapi.Api.Model.RegistrationBody;
import com.EmreAydin.ecommerceapi.Exception.UserAlreadyExistsException;
import com.EmreAydin.ecommerceapi.Models.User;
import com.EmreAydin.ecommerceapi.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if(jwt == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/profile")
    public User loggedUserProfile(@AuthenticationPrincipal User user){
        return user;
    }
}
