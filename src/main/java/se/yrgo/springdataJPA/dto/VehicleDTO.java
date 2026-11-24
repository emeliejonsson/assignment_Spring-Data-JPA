package se.yrgo.springdataJPA.dto;

public class VehicleDTO {

    public Long id;
    public String regNumber;
    public String brand;
    public String model;
    public String prodYear;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

  public void setRegNumber(String regNumber) { this.regNumber = regNumber; }
  public String getRegNumber() { return regNumber; }

  public void setBrand(String brand) { this.brand = brand; }
  public String getBrand() { return brand; }

  public void setModel(String model) { this.model = model; }
  public String getModel() { return model; }

  public void setProdYear(String prodYear) { this.prodYear = prodYear; }
  public String getProdYear() { return prodYear; }

}
