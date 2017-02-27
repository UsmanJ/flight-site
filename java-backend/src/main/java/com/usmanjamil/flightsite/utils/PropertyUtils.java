package com.usmanjamil.flightsite.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by usmanjamil on 27/02/2017.
 */
public class PropertyUtils {

    public String getProperty(String fileName, String propertyName) {
        Properties prop = new Properties();
        String response = null;
        InputStream input;

        try {
            input = getClass().getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return null;
            }

            prop.load(input);
            response = prop.getProperty(propertyName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
