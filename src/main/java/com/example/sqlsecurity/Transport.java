package com.example.sqlsecurity;

import java.math.BigDecimal;

public class Transport {
    BigDecimal transport_id;
    BigDecimal owner_id;
    String license_plate;
    String brand;
    String model;
    BigDecimal model_year;
    String color;

    public Transport(BigDecimal transport_id, BigDecimal owner_id, String license_plate, String brand, String model, BigDecimal model_year, String color) {
        this.transport_id = transport_id;
        this.owner_id = owner_id;
        this.license_plate = license_plate;
        this.brand = brand;
        this.model = model;
        this.model_year = model_year;
        this.color = color;
    }

    public BigDecimal getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(BigDecimal transport_id) {
        this.transport_id = transport_id;
    }

    public BigDecimal getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(BigDecimal owner_id) {
        this.owner_id = owner_id;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getModel_year() {
        return model_year;
    }

    public void setModel_year(BigDecimal model_year) {
        this.model_year = model_year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
