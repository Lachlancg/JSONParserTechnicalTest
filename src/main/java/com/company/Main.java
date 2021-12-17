package com.company;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Retrieve the vehicles from the JSON file
        ArrayList<Vehicle> vehicles = getVehicles("vehicle.json");

        //Task 1 - Print the vehicles in
        printVehiclesSorted(vehicles);

        //Task 2 - Count number of makes and write to JSON file
        createJSONForMake(vehicles);

        //Task 3 - Replace vehicle types whom contain model jumpy to VAN
        modifyVehiclesIfJumpy(vehicles);



    }

    private static void modifyVehiclesIfJumpy(ArrayList<Vehicle> vehicles) {

        try{
            //Could use parallelStream in future to increase performance
            //Use stream filter lambada to find all models containing jumpy and then iterate over results to replace assetType with VAN
            vehicles.stream().filter(v -> v.getModel().toLowerCase().contains("jumpy")).forEach(v -> v.setAssetType("VAN"));

            //Create an object mapper and write arrayList to JSON file
            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("vehicleEdited.json"), vehicles);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    private static void createJSONForMake(ArrayList<Vehicle> vehicles){

        try{
            //Retrieve the count of each make of vehicle
            Map<String, Long> numOfMakes = countMakes(vehicles);

            //Create an Object mapper and write the Map to a json file
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("vehicleMakes.json"), numOfMakes);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static Map<String, Long> countMakes(ArrayList<Vehicle> vehicles){

        //Use collectors to group common vehicles makes, and count the number of occurrences, store result in a map
        return vehicles.stream().collect(Collectors.groupingBy(v -> v.getMake(), Collectors.counting()));
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
