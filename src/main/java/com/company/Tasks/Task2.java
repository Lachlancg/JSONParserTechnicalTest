package com.company.Tasks;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 extends Task{

    public static void main(String[] args) {
        createJSONForMake(getVehicles("vehicle.json"));
    }

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

    private static Map<String, Long> countMakes(ArrayList<Vehicle> vehicles) {

        //Use collectors to group common vehicles makes, and count the number of occurrences, store result in a map
        return vehicles.stream().collect(Collectors.groupingBy(v -> v.getMake(), Collectors.counting()));
    }
}
