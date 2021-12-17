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

        //Task 1 - Print the vehicles in
        printVehiclesSorted(vehicles);

    }

    private static void printVehiclesSorted(ArrayList<Vehicle> vehicles){

        //Sort vehicles by make using custom comparator
        vehicles.sort(Vehicle.VehNameComparator);

        //Iterate over vehicles and print the make
        vehicles.forEach(v -> System.out.println(v.getName()));
    }

    private static ArrayList<Vehicle> getVehicles(String filePath) {
        //Map<String, Vehicle> vehicles = new HashMap<>();
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(JsonParser.Feature.ALLOW_TRAILING_COMMA);

            vehicles = mapper.readValue(new File(filePath), new TypeReference<ArrayList<Vehicle>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}
