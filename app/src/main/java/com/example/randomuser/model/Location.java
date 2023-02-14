package com.example.randomuser.model;

import android.annotation.SuppressLint;

import java.io.Serializable;

class Location implements Serializable {
    Street street;
    String city;
    String state;
    String country;
    String postcode;
    Coordinates coordinates;
    Timezone timezone;

    @SuppressLint("DefaultLocale")
    public String getStreet() {
        return String.format("%s,%d", street.name, street.number);
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    private static class Street implements Serializable {
        Integer number;
        String name;
    }

    private static class Coordinates implements Serializable {
        String latitude;
        String longitude;
    }

    private static class Timezone implements Serializable {
        String offset;
        String description;
    }
}
