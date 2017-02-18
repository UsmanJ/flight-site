package com.usmanjamil.flightsite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usmanjamil.flightsite.model.Auth0SignIn;
import com.usmanjamil.flightsite.model.Auth0SignUp;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
            String url = prop.getProperty("auth0.signUp");
            String connection = prop.getProperty("auth0.connection");

            Auth0SignUp user = new Auth0SignUp(clientId, email, password, connection);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            RestTemplate restTemplate = new RestTemplate();
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

    public String signIn(String username, String password) {
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
            String url = prop.getProperty("auth0.signIn");
            String connection = prop.getProperty("auth0.connection");
            String scope = prop.getProperty("auth0.scope");

            Auth0SignIn user = new Auth0SignIn(clientId, username, password, connection, scope);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            RestTemplate restTemplate = new RestTemplate();
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

    public String logout() {
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
            String url = prop.getProperty("auth0.logout");

            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(url, String.class);

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
