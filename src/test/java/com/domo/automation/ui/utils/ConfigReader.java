package com.domo.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    //static initializer
    static {

        String filePath = "src/test/resources/properties/domo.properties"; //directory/location

        FileInputStream input = null; //used for reading binary data from a file
        try {
            input = new FileInputStream(filePath); //go and read this file
            properties = new Properties(); //initializing properties
            properties.load(input); //take everything you read and load it to this properties

        } catch (IOException e) {
            System.out.println("File not Found");
        }

        finally {
            try {
                input.close(); //clean up method. Always close the `FileInputStream` in finally block once you're done with it, to release system resources.
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load configuration file.");
            }
        }
    }

    public static String getPropertiesValue(String key){
        return properties.getProperty(key);
    }
}
