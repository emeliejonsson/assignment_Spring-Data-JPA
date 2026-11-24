package se.yrgo.springdataJPA.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.springdataJPA.dto.CustomerDTO;
import se.yrgo.springdataJPA.dto.VehicleDTO;
import se.yrgo.springdataJPA.entities.Customer;
import se.yrgo.springdataJPA.entities.Vehicle;
import se.yrgo.springdataJPA.repositories.CustomerRepository;
import se.yrgo.springdataJPA.repositories.VehicleRepository;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepo;
    private VehicleRepository vehicleRepo;

    public CustomerController(CustomerRepository customerRepo, VehicleRepository vehicleRepo) {
        this.customerRepo = customerRepo;
        this.vehicleRepo = vehicleRepo;
    }

    //skapa en kund
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestParam String name, @RequestParam String phoneNumber) {
        Customer customer = new Customer(name, phoneNumber);
        customerRepo.save(customer);
        return ResponseEntity.ok("New customer created" + customer);
    }

    //returnera en lista med alla kunder och deras fordon
    @GetMapping
    public List<CustomerDTO> allCustomerAndTheirVehicles() {
        List<Object[]> allCustomerAndVehicles = customerRepo.customerAndVehicles();
        Map<Long, CustomerDTO> customerMap = new HashMap<>();

        for (Object[] result : allCustomerAndVehicles) {
            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDTO customerDTO = customerMap.getOrDefault(customer.getId(), new CustomerDTO());
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());

            if (vehicle != null) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setId(vehicle.getId());
                vehicleDTO.setBrand(vehicle.getBrand());
                vehicleDTO.setRegNumber(vehicle.getRegNumber());
                vehicleDTO.setBrand(vehicle.getBrand());
                vehicleDTO.setProdYear(vehicle.getProdYear());
                vehicleDTO.setModel(vehicle.getModel());

                customerDTO.getVehicles().add(vehicleDTO);
            }
            customerMap.put(customer.getId(), customerDTO);
        }
        return new ArrayList<>(customerMap.values());
    }

    //hitta kund-id utifrån namn
    @GetMapping("/customer-id")
    public ResponseEntity<Long> getCustomerByName(@RequestParam String name) {
        Optional<Customer> customer = customerRepo.findByName(name);

        return customerRepo.findByName(name)
                .map(Customer::getId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
