package com.company.Tasks;

import com.company.Models.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void generateSequencesTest() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("12345", "Ford", "Fiesta", "bCarName", "CAR", null));
        vehicles.add(new Vehicle("54321", "Vauxhall", "Astra", "cCarName", "CAR", "12345"));
        vehicles.add(new Vehicle("46372", "Fiat", "500", "aCarName", "CAR", "54321"));

        Map<String, String> result = Task4.generateSequences(vehicles);

        Map<String, String> actual = new HashMap<>();
        actual.put("54321", "12345");
        actual.put("46372", "54321");

        assertEquals(actual, result);
    }

    @Test
    void findStartNodesTest() {
        Map<String, String> nodes = new HashMap<>();
        nodes.put("54321", "12345");
        nodes.put("46372", "54321");

        ArrayList<String> result = Task4.findStartNodes(nodes);

        ArrayList<String> actual = new ArrayList<>();
        actual.add("46372");

        assertEquals(actual, result);
    }

}