package com.usmanjamil.flightsite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.usmanjamil.flightsite.model.Auth0User;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        String response = null;

        try {
            String filename = "auth0.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            prop.load(input);
            String clientId = prop.getProperty("auth0.clientId");
            String connection = "Username-Password-Authentication";

            Auth0User user = new Auth0User(clientId, email, password, connection);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);


            RestTemplate restTemplate = new RestTemplate();
            String url = "https://usmanj.eu.auth0.com/dbconnections/signup";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            response = restTemplate.postForObject(url, entity, String.class);


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

        return response;
    }
}
