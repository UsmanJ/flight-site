package com.usmanjamil.flightsite.controllers;

import com.usmanjamil.flightsite.services.ForgerockUserService;
import com.usmanjamil.flightsite.services.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usmanjamil
 */
@Controller
public class UserController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private ForgerockUserService forgerockUserService;

    @RequestMapping(
        value = "/user/sign-up", 
        method = RequestMethod.POST, 
        produces = "application/json",
        consumes ="application/json"
    )
    @ResponseBody
    public Response signUp(@RequestBody String input) {
        JSONObject obj = new JSONObject(input);

//        String signUp = userService.signUp(obj.getString("email"), obj.getString("password"));

        String signUp = forgerockUserService.signUp(obj.getString("email"));


        return Response.ok(signUp).build();
    }

    @RequestMapping(
            value = "/user/sign-in",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes ="application/json"
    )
    @ResponseBody
    public Response signIn(@RequestBody String input) {
        JSONObject obj = new JSONObject(input);

        String signIn = userService.signIn(obj.getString("username"), obj.getString("password"));

        return Response.ok(signIn).build();
    }

    @RequestMapping(
            value = "/user/logout",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Response logout() {

        String logout = userService.logout();

        return Response.ok(logout).build();
    }

    @RequestMapping(
            value = "/user/change-password",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes ="application/json"
    )
    @ResponseBody
    public Response changePassword(@RequestBody String input) {
        JSONObject obj = new JSONObject(input);

        String changePassword = userService.changePassword(obj.getString("email"));

        return Response.ok(changePassword).build();
    }

    @RequestMapping(
            value = "/user/get-profile",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes ="application/json"
    )
    @ResponseBody
    public Response getProfile() {

        String profile = userService.getProfile();

        return Response.ok(profile).build();
    }
}
