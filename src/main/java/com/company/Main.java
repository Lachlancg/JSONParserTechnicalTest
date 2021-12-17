package com.company;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Retrieve the vehicles from the JSON file
        ArrayList<Vehicle> vehicles = getVehicles("vehicle.json");

    }

    private static ArrayList getVehicles(String filePath) {
        //Map<String, Vehicle> vehicles = new HashMap<>();
        ArrayList vehicles = new ArrayList();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(JsonParser.Feature.ALLOW_TRAILING_COMMA);

            vehicles = mapper.readValue(new File("vehicle.json"), new TypeReference<ArrayList<Vehicle>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}
