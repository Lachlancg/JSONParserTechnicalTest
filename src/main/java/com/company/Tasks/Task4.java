package com.company.Tasks;

import com.company.Models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Task4 extends AbstractTask {

    public static void main(String[] args) {

        //Task 4 - Calculate chains of replacement and print them
        printReplacements(getVehicles("vehicle.json"));
    }


    /**
     * Organises steps to print vehicle replacements
     *
     * @param vehicles
     */
    public static void printReplacements(ArrayList<Vehicle> vehicles) {

        //Generate sequences from vehicle objects
        Map<String, String> sequences = generateSequences(vehicles);

        //Find the start nodes for each chain of sequences
        ArrayList<String> startNodes = findStartNodes(sequences);

        //Build the chains from the start nodes and sequences
        buildAndPrintChain(startNodes, sequences);
    }

    /**
     * Builds the replaced vehicle chain and prints to console
     *
     * @param startNodes
     * @param sequences
     */
    private static void buildAndPrintChain(ArrayList<String> startNodes, Map<String, String> sequences) {
        //Build the chain of sequences
        for (String n : startNodes) {
            StringBuilder outStringSb = new StringBuilder();
            //Get the first key to search
            String key = n;
            while (true) {
                //Search the map for the corresponding value
                String result = sequences.get(key);

                //If we find a value we know were not on the last node of the chain, and we add the details to the out string
                if (result != null) {
                    //If it is the first node to be added, then add the key
                    if (outStringSb.isEmpty()) {
                        outStringSb.append(key);
                    }
                    outStringSb.append(" > ");
                    outStringSb.append(result);
                    key = result;
                }
                //We know this would be the last node of the chain, so we print the result and break the loop
                else {
                    System.out.println(outStringSb);
                    break;
                }
            }
        }
    }

    /**
     * Finds the start nodes of each sequence
     *
     * @param sequences
     * @return
     */
    public static ArrayList<String> findStartNodes(Map<String, String> sequences) {
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
            if (result.isEmpty()) {
                startNodes.add(node.getKey());
            }
        }

        return startNodes;
    }

    /**
     * Generates sequence pairs from vehicle objects
     *
     * @param vehicles
     * @return
     */
    public static Map<String, String> generateSequences(ArrayList<Vehicle> vehicles) {
        //Create map of all the replacement sequences
        Map<String, String> sequences = new HashMap<>();
        for (Vehicle v : vehicles) {
            String currentId = v.getId();
            String replacedId = v.getReplaced();
            //Only add if it exists
            if (replacedId != null) {
                sequences.put(currentId, replacedId);
            }
        }
        return sequences;
    }

}
