package com.company.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Vehicle {

    @JsonProperty("_id") private String id;
    private String make;
    private String model;
    private String name;
    private String assetType;
    private String replaced;
    private Map<String, Object> others = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getUnknownProperties() { return others; }
    @JsonAnySetter
    public void setUnknownProperty(String key, Object value) { others.put(key, value); }

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

   public static Comparator<Vehicle> VehNameComparator = new Comparator<Vehicle>() {
       @Override
       public int compare(Vehicle o1, Vehicle o2) {
           String veh1Name = o1.getName().toUpperCase();
           String veh2Name = o2.getName().toUpperCase();

           return veh1Name.compareTo(veh2Name);
       }
   };

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