package com.company;

import com.company.Models.Vehicle;
import com.company.Tasks.Task1;
import com.company.Tasks.Task2;
import com.company.Tasks.Task3;
import com.company.Tasks.Task4;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Retrieve the vehicles from the JSON file
        ArrayList<Vehicle> vehicles = SharedTaskFunctions.getVehicles("vehicle.json");

        //Task 1 - Print the vehicles in
        Task1.printVehiclesSorted(vehicles);

        //Task 2 - Count number of makes and write to JSON file
        Task2.createJSONForMake(vehicles);

        //Task 3 - Replace vehicle types whom contain model jumpy to VAN
        Task3.modifyVehiclesIfJumpy(vehicles);

        //Task 4 - Calculate chains of replacement and print them
        Task4.printReplacements(vehicles);

    }







}
