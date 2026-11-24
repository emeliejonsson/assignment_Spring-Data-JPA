package se.yrgo.springdataJPA.entities;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regNumber;
    private String brand;
    private String model;
    private String prodYear;

    @ManyToOne
    private Customer owner;

    public Vehicle() {
    }

    public Vehicle(String regNumber, String brand, String model, String prodYear) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.prodYear = prodYear;
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() {
        return id;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
    public String getRegNumber() {
        return regNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setProdYear(String prodYear) {
        this.prodYear = prodYear;
    }
    public String getProdYear() {
        return prodYear;
    }

    public void setOwner(Customer owner) { this.owner = owner; }
    public Customer getOwner() { return owner; }

    @Override
    public String toString() {
        return " with id: " + id +
                ", registration number:'" + regNumber + '\'' +
                ", brand:'" + brand + '\'' +
                ", model:'" + model + '\'' +
                ", production year:'" + prodYear + '\'';
    }
}
