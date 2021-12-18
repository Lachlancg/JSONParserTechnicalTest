package com.company.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Vehicle implements Comparable<Vehicle>{

    @JsonProperty("_id") private String id;
    private String make;
    private String model;
    private String name;
    private String assetType;
    private String replaced;
    private Map<String, Object> others = new HashMap<>();

    public Vehicle(){

    }

    public Vehicle(String _id, String make, String model, String name, String assetType, String replaced){
        this.id = _id;
        this.make = make;
        this.model = model;
        this.name = name;
        this.assetType = assetType;
        this.replaced = replaced;
    }

    //Jackson custom annotation to deal with unknown properties in JSON object
    @JsonAnyGetter
    public Map<String, Object> getUnknownProperties() { return others; }
    @JsonAnySetter
    public void setUnknownProperty(String key, Object value) { others.put(key, value); }


    /**
     * Parses JSON file and returns an ArrayList
     *
     * @param filePath
     * @return
     */
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


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Vehicle o) {
        //Sorting by vehicle name alphabetically
        String veh1Name = this.getName().toUpperCase();
        String veh2Name = o.getName().toUpperCase();

        return veh1Name.compareTo(veh2Name);
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getReplaced() {
        return replaced;
    }

    public void setReplaced(String replaced) {
        this.replaced = replaced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
