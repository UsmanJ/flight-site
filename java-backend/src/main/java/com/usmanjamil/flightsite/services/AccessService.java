package com.usmanjamil.flightsite.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by usmanjamil on 21/02/2017.
 */
@Service
public class AccessService {
    private  String idToken;
    private String accessToken;
    private static AccessService instance = null;

    private AccessService() {
        // Exists only to defeat instantiation.
    }

    public static AccessService getInstance() {
        if(instance == null) {
            instance = new AccessService();
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return this.idToken;
    }
}
