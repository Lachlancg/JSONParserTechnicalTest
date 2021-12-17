package com.company.Tasks;

import com.company.Models.Vehicle;
import com.company.SharedTaskFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class Task3 {

    public static void main(String[] args) {

        modifyVehiclesIfJumpy(SharedTaskFunctions.getVehicles("vehicle.json"));
    }

    public static void modifyVehiclesIfJumpy(ArrayList<Vehicle> vehicles) {

        try {
            //Could use parallelStream in future to increase performance
            //Use stream filter lambada to find all models containing jumpy and then iterate over results to replace assetType with VAN
            vehicles.stream().filter(v -> v.getModel().toLowerCase().contains("jumpy")).forEach(v -> v.setAssetType("VAN"));

            //Create an object mapper and write arrayList to JSON file
            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("vehicleEdited.json"), vehicles);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
