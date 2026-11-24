package se.yrgo.springdataJPA.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private List<VehicleDTO> vehicles = new ArrayList<>();

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name;}

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return phoneNumber; }

    public List<VehicleDTO> getVehicles() { return vehicles; }
    public void setVehicles(List<VehicleDTO> vehicles) { this.vehicles = vehicles; }
}
