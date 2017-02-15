/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usmanjamil.flightsite.services;

import com.usmanjamil.flightsite.model.Auth0User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author usmanjamil
 */
@Service
public class UserService {
     
    public String signUp(String email, String password) {
        RestTemplate template = new RestTemplate();
        
        Auth0User user = new Auth0User(clientId, email, password, connection);
     
        template.postForObject("https://YOUR_AUTH0_DOMAIN/dbconnections/signup", user, Auth0User.class);
        
        return null;
    }
}
