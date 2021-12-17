package com.company;

import com.company.Models.Vehicle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

        //Task 4
        printReplacements(vehicles);

    }

    private static void printReplacements(ArrayList<Vehicle> vehicles){

        //Generate sequences from vehicle objects
        Map<String, String> sequences = generateSequences(vehicles);

        //Find the start nodes for each chain of sequences
        ArrayList<String> startNodes = findStartNodes(sequences);

        //Build the chains from the start nodes and sequences
        buildAndPrintChain(startNodes, sequences);
    }

    private static void buildAndPrintChain(ArrayList<String> startNodes, Map<String, String> sequences){
        //Build the chain of sequences
        for (String n : startNodes){
            StringBuilder outStringSb = new StringBuilder();
            //Get the first key to search
            String key = n;
            while (true){
                //Search the map for the corresponding value
                String result = sequences.get(key);

                //If we find a value we know were not on the last node of the chain, and we add the details to the out string
                if (result != null){
                    //If it is the first node to be added, then add the key
                    if (outStringSb.isEmpty()){
                        outStringSb.append(key);
                    }
                    outStringSb.append("->");
                    outStringSb.append(result);
                    key = result;
                }
                //We know this would be the last node of the chain, so we print the result and break the loop
                else{
                    System.out.println(outStringSb);
                    break;
                }
            }
        }
    }

    private static ArrayList<String> findStartNodes(Map<String, String> sequences){
        //Now find all the vehicles (nodes) which start a sequence
        ArrayList<String> startNodes = new ArrayList<>();

        for (Map.Entry<String, String> node : sequences.entrySet()) {

            //Get the current nodes key and use it to search the map of sequences
            String searchKey = node.getKey();
            //This will be slow as I am searching the map by value
            Optional<Map.Entry<String, String>> result = sequences
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(searchKey))
                    .sorted(Map.Entry.comparingByKey())
                    .findFirst();

            //If no result is found, we know this is the first node in the chain, and we can add it to our list
            if (result.isEmpty()){
                startNodes.add(node.getKey());
            }
        }

        return startNodes;
    }

    private static Map<String, String> generateSequences(ArrayList<Vehicle> vehicles){
        //Create map of all the replacement sequences
        Map<String, String> sequences = new HashMap<>();
        for (Vehicle v:vehicles) {
            String currentId = v.getId();
            String replacedId = v.getReplaced();
            //Only add if it exists
            if(replacedId != null){
                sequences.put(currentId, replacedId);
            }
        }
        return sequences;
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
