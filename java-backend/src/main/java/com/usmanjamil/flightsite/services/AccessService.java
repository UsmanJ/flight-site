package com.usmanjamil.flightsite.services;

import org.springframework.stereotype.Service;

/**
 * Created by usmanjamil on 21/02/2017.
 */
@Service
public class AccessService {
    private static AccessService instance = null;
    protected AccessService() {
        // Exists only to defeat instantiation.
    }
    public static AccessService getInstance() {
        if(instance == null) {
            instance = new AccessService();
        }
        return instance;
    }
}
