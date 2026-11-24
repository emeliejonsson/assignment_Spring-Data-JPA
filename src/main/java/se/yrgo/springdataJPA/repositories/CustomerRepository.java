package se.yrgo.springdataJPA.repositories;

import se.yrgo.springdataJPA.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    //hämta alla kunder tillsammans med deras fordon.
    @Query("SELECT c, v FROM Customer c LEFT JOIN c.vehicles v")
    List<Object[]> customerAndVehicles();
}