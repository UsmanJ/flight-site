/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usmanjamil.flightsite.services;

import com.usmanjamil.flightsite.model.Auth0User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author usmanjamil
 */
@Service
public class UserService {
     
    public String signUp(String email, String password) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("auth0.properties");
            prop.load(input);

            System.out.println(prop.getProperty("auth0.clientId"));
            String clientId = prop.getProperty("auth0.clientId");
            String connection = "flightsite";

            Auth0User user = new Auth0User(clientId, email, password, connection);


            RestTemplate template = new RestTemplate();
            template.postForObject("https://usmanj.eu.auth0.com/dbconnections/signup", user, Auth0User.class);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                try {

                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
