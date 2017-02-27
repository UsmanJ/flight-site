package com.usmanjamil.flightsite.services;

/**
 * Created by usmanjamil on 24/02/2017.
 */
public interface UserServiceI {
    String signUp(String email, String password);

    String signIn(String username, String password);

    String logout();

    String changePassword(String email);

    String getProfile();
}
