package com.EmreAydin.ecommerceapi.Service;

import com.EmreAydin.ecommerceapi.Api.Model.LoginBody;
import com.EmreAydin.ecommerceapi.Api.Model.RegistrationBody;
import com.EmreAydin.ecommerceapi.Exception.UserAlreadyExistsException;
import com.EmreAydin.ecommerceapi.Models.DAO.UserDAO;
import com.EmreAydin.ecommerceapi.Models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private EncryptionService encryptionService;
    private UserDAO userDAO;
    private JWTService jwtService;

    public UserService(EncryptionService encryptionService, UserDAO userDAO, JWTService jwtService) {
        this.encryptionService = encryptionService;
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    public User registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {

        if (userDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                && userDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return userDAO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<User> optUser = userDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if(optUser.isPresent()) {
            User user = optUser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                 return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
