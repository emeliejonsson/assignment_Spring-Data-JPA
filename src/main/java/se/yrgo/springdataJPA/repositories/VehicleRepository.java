package se.yrgo.springdataJPA.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.springdataJPA.dto.VehicleBrandRegNumberDTO;
import se.yrgo.springdataJPA.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    //lista fordon utifrån märke
    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.brand) =:brand ")
    List<Vehicle> findByBrand(@Param("brand")String brand);

    //lista fordon + regnummer och bilmärke
    @Query("SELECT v.brand AS brand, v.regNumber AS regNumber FROM Vehicle v")
    List<VehicleBrandRegNumberDTO> vehiclesBrandAndRegNumber();

    //uppdaterar bilmärket för ett fordon basert på id
    @Modifying
    @Transactional
    @Query("UPDATE Vehicle v SET v.brand = :brand WHERE v.id = :id")
    int updateVehicleBrand(@Param("id") long id, @Param("brand") String brand);

    //hämta alla forden för en viss kund baserat på kundens id
    @Query("SELECT v FROM Vehicle v WHERE v.owner.id = :id")
    List<Vehicle> findByOwnerId(@Param("id") Long id);
}
