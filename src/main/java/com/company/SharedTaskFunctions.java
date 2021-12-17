package com.company;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class SharedTaskFunctions {

    public static ArrayList<Vehicle> getVehicles(String filePath) {
        ArrayList<Vehicle> vehicles;

        try {
            //Create ObjectMapper to Parse the JSON file
            ObjectMapper mapper = new ObjectMapper();
            //Enable Allow Training Commas as there is an error in the JSON file
            mapper.enable(JsonParser.Feature.ALLOW_TRAILING_COMMA);

            //Read the JSON straight into an ArrayList of vehicles
            vehicles = mapper.readValue(new File(filePath), new TypeReference<ArrayList<Vehicle>>() {
            });

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return vehicles;
    }
}
