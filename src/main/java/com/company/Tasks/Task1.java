package com.company.Tasks;

import com.company.Models.Vehicle;

import java.util.ArrayList;
import java.util.Collections;

public class Task1 extends AbstractTask {

    public static void main(String[] args) {

        //Task 1 - Print the vehicles to console in alphabetical order
        printVehiclesSorted(getVehicles("vehicle.json"));
    }


    /**
     * Prints sorted vehicle objects
     *
     * @param vehicles List of vehicles
     */
    public static void printVehiclesSorted(ArrayList<Vehicle> vehicles) {

        //Sort vehicles by make using custom comparator
        Collections.sort(vehicles);

        //Iterate over vehicles and print the make
        vehicles.forEach(System.out::println);
    }
}
