package pl.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offer.jpa.CarManufacturer;

import java.util.List;
import java.util.Optional;

public interface CarManufacturerRepository extends JpaRepository<CarManufacturer,Integer> {
    Optional<CarManufacturer> findOfferById(int id);

    List<CarManufacturer> findAll();
}
