package com.usmanjamil.flightsite.services;

import com.usmanjamil.flightsite.utils.PropertyUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by usmanjamil on 24/02/2017.
 */
@Service
public abstract class ForgerockUserService implements UserServiceI {

    public String signUp(String email) {

        initializeSignUp();

        // TODO : Validate response status code

       String response = registerEmailAddress(email);


        return response;
    }

    private String initializeSignUp() {
        String fileName = "forgerock.properties";
        String propertyName = "forgerock.registerationGet";
        String response;

        String GetRequestUrl = new PropertyUtils().getProperty(fileName, propertyName);

        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.getForObject(GetRequestUrl, String.class);


        return response;
    }

    private String registerEmailAddress(String email) {
        String fileName = "forgerock.properties";
        String propertyName = "forgerock.registerationPost";
        String response;

        String PostRequestUrl = new PropertyUtils().getProperty(fileName, propertyName);

        JsonObject obj = Json.createObjectBuilder()
                .add("input", Json.createObjectBuilder()
                    .add("mail", email))
                .build();

        String json = obj.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> apiResponse = restTemplate
                .exchange(PostRequestUrl, HttpMethod.POST, entity, String.class);

        response = apiResponse.getBody();

        return response;

    }

}
