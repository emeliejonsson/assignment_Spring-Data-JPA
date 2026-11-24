package se.yrgo.springdataJPA.dto;

public class VehicleBrandRegNumberDTO {
    private String brand;
    private String regNumber;

    public VehicleBrandRegNumberDTO (String brand, String regNumber) {
        this.brand = brand;
        this.regNumber = regNumber;
    }

    public String getBrand() { return brand;}
    public String getRegNumber() { return regNumber; }
}
