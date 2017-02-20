/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usmanjamil.flightsite.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author usmanjamil
 */
@Getter
@Setter
public class Auth0User implements Serializable{
    private String client_id;
    private String email;
    private String password;
    private String connection;

    public Auth0User(String client_id, String email, String password, String connection) {
        this.client_id = client_id;
        this.email = email;
        this.password = password;
        this.connection = connection;
    }
}
