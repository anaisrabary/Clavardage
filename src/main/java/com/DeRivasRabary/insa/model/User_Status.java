package com.DeRivasRabary.insa.model;

public enum User_Status {
    ONLINE ("Disponible"),
    BUSY ("occupé");

    private String status_name;

    User_Status(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return status_name;
    }
}
