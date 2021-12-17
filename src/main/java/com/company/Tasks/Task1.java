package com.company.Tasks;

import com.company.Models.Vehicle;
import com.company.SharedTaskFunctions;

import java.util.ArrayList;
import java.util.Collections;

public class Task1{

    public static void main(String[] args) {

        printVehiclesSorted(SharedTaskFunctions.getVehicles("vehicle.json"));
    }

    public static void printVehiclesSorted(ArrayList<Vehicle> vehicles) {

        //Sort vehicles by make using custom comparator
        Collections.sort(vehicles);

        //Iterate over vehicles and print the make
        vehicles.forEach(System.out::println);
    }
}
