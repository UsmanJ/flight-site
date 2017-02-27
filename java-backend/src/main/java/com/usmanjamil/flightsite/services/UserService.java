package com.usmanjamil.flightsite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.usmanjamil.flightsite.model.Auth0SignIn;
import com.usmanjamil.flightsite.model.Auth0User;
import org.json.JSONObject;
import org.springframework.http.*;
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
public class UserService implements UserServiceI {
     
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

            Auth0User user = new Auth0User(clientId, email, password, connection);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

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

            String json = new GsonBuilder().create().toJson(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> apiResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);

            response = apiResponse.getBody();

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

            JSONObject obj = new JSONObject(response);

            String accessToken = obj.getString("access_token");
            String idToken = obj.getString("id_token");
            AccessService.getInstance().setAccessToken(accessToken);
            AccessService.getInstance().setIdToken(idToken);
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

            AccessService.getInstance().setAccessToken(null);
            AccessService.getInstance().setIdToken(null);
        }

        return response;
    }

    public String changePassword(String email) {
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
            String url = prop.getProperty("auth0.changePassword");
            String connection = prop.getProperty("auth0.connection");
            String password = "";

            Auth0User user = new Auth0User(clientId, email, password, connection);

            String json = new GsonBuilder().create().toJson(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> apiResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);

            response = apiResponse.getBody();

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

    public String getProfile() {
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
            String url = prop.getProperty("auth0.getProfile");

            JSONObject obj = new JSONObject();
            String idToken = AccessService.getInstance().getIdToken();
            obj.put("id_token", idToken);
            String json = obj.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> apiResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);

            response = apiResponse.getBody();

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
