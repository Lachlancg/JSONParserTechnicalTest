package com.company.Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testToString() {
        Vehicle v = new Vehicle("12345", "Ford", "Fiesta", "bCarName", "CAR", null);
        String actual = "bCarName";
        assertEquals(actual, v.toString());
    }

    @Test
    void compareTo() {

        Vehicle v1 = new Vehicle("12345", "Ford", "Fiesta", "aCarName", "CAR", null);
        Vehicle v2 = new Vehicle("12345", "Ford", "Fiesta", "bCarName", "CAR", null);

        assertEquals(-1, v1.compareTo(v2));

        Vehicle v3 = new Vehicle("12345", "Ford", "Fiesta", "aCarName", "CAR", null);
        Vehicle v4 = new Vehicle("12345", "Ford", "Fiesta", "aCarName", "CAR", null);

        Assertions.assertEquals(0, v3.compareTo(v4));

        Vehicle v5 = new Vehicle("12345", "Ford", "Fiesta", "bCarName", "CAR", null);
        Vehicle v6 = new Vehicle("12345", "Ford", "Fiesta", "aCarName", "CAR", null);

        Assertions.assertEquals(1, v5.compareTo(v6));
    }
}