package com.company.Tasks;

import com.company.Models.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void countMakesTests() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("12345", "Ford", "Fiesta", "bCarName", "CAR", null));
        vehicles.add(new Vehicle("54321", "Vauxhall", "Astra", "cCarName", "CAR", null));
        vehicles.add(new Vehicle("46372", "Fiat", "500", "aCarName", "CAR", null));

        Map<String, Long> result = Task2.countMakes(vehicles);

        Map<String, Long>  actual = new HashMap<>();
        actual.put("Ford", Long.valueOf(1));
        actual.put("Vauxhall", Long.valueOf(1));
        actual.put("Fiat", Long.valueOf(1));

        assertEquals(actual, result);
    }

}