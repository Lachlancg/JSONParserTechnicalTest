package com.company.Tasks;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 {

    public static void main(String[] args) {

        //Task 2 - Count number of makes and write to JSON file
        createJSONForMake(Vehicle.getVehicles("vehicle.json"));
    }


    /**
     * Creates a JSON file from the counts of each make of vehicle
     *
     * @param vehicles List of vehicles
     */
    private static void createJSONForMake(ArrayList<Vehicle> vehicles) {

        try {
            //Retrieve the count of each make of vehicle
            Map<String, Long> numOfMakes = countMakes(vehicles);

            //Create an Object mapper and write the Map to a json file
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("vehicleMakes.json"), numOfMakes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    /**
     * Counts the number of occurrences of each make of vehicle
     *
     * @param vehicles List of vehicles
     * @return Map of make counts
     */
    public static Map<String, Long> countMakes(ArrayList<Vehicle> vehicles) {

        //Use collectors to group common vehicles makes, and count the number of occurrences, store result in a map
        //Use method reference to retrieve vehicle make
        return vehicles.stream().collect(Collectors.groupingBy(Vehicle::getMake, Collectors.counting()));
    }
}
