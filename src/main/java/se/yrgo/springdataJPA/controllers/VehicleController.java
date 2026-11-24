package se.yrgo.springdataJPA.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.springdataJPA.dto.VehicleBrandRegNumberDTO;
import se.yrgo.springdataJPA.dto.VehicleDTO;
import se.yrgo.springdataJPA.entities.Customer;
import se.yrgo.springdataJPA.entities.Vehicle;
import se.yrgo.springdataJPA.repositories.CustomerRepository;
import se.yrgo.springdataJPA.repositories.VehicleRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleRepository vehicleRepo;
    private CustomerRepository customerRepo;

    public VehicleController(VehicleRepository vehicleRepo, CustomerRepository customerRepo) {
        this.vehicleRepo = vehicleRepo;
        this.customerRepo = customerRepo;
    }

    private VehicleDTO toDto(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(v.getId());
        dto.setRegNumber(v.getRegNumber());
        dto.setBrand(v.getBrand());
        dto.setModel(v.getModel());
        dto.setProdYear(v.getProdYear());
        return dto;
    }

    //skapa fordon
    @PostMapping()
    public ResponseEntity<String> createVehicle(@RequestParam String regNumber,
                                                @RequestParam String brand,
                                                @RequestParam String model,
                                                @RequestParam String prodYear) {
        Vehicle vehicle = new Vehicle(regNumber, brand, model, prodYear);
        vehicleRepo.save(vehicle);
        return ResponseEntity.ok("New vehicle created " + vehicle);
    }

    //koppla fordon till en kund
    @PostMapping("/add-vehicle-to-customer")
    public ResponseEntity<String> connectVehicleToCustomer(@RequestParam Long customerId, @RequestParam Long vehicleId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);

        if (customerOptional.isEmpty() || vehicleOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid customer or vehicle");
        }

        Customer customer = customerOptional.get();
        Vehicle vehicle = vehicleOptional.get();
        vehicle.setOwner(customer);
        vehicleRepo.save(vehicle);
        return ResponseEntity.ok("Vehicle " + vehicle.getBrand() + " " + vehicle.getRegNumber() + " connected to customer " + customer.getName());
    }

    //returnera en lista med alla fordon
    @GetMapping
    public List<VehicleDTO> allVehicles() {
        return vehicleRepo.findAll().stream().map(this::toDto).toList();
    }

    //returnera bilar med info om regnummer och märke
    @GetMapping("/vehicle-brand-info")
    public List<VehicleBrandRegNumberDTO> vehicleBrandRegInfo() {
        return vehicleRepo.vehiclesBrandAndRegNumber()
                .stream()
                .map(i -> new VehicleBrandRegNumberDTO(i.getBrand(), i.getRegNumber()))
                .toList();
    }

    //returnera alla fordon av ett visst bilmärke
    @GetMapping("/vehicle-by-brand")
    public List<VehicleDTO> getVehicleByBrand(@RequestParam String brand) {
        String brandIgnoreCase = brand.trim().toLowerCase(Locale.ROOT);
        return vehicleRepo.findByBrand(brandIgnoreCase).stream().map(this::toDto).toList();
    }

    //uppdatera bilmärke
    @PutMapping("/{id}/brand")
    public ResponseEntity<String> updateBrandForVehicle(@PathVariable Long id, @RequestParam String brand) {
        int updated = vehicleRepo.updateVehicleBrand(id, brand);
        if (updated == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Brand updated");
    }

    //hämta fordon utifrån kund-ID
    @GetMapping("/vehicle-by-customer/{customerId}")
    public List<VehicleDTO> getVehiclesByCustomer(@PathVariable Long customerId) {
        return vehicleRepo.findByOwnerId(customerId).stream().map(this::toDto).toList();
    }

}
