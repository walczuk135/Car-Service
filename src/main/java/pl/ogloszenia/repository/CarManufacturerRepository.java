package pl.ogloszenia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogloszenia.jpa.CarManufacturer;

import java.util.List;

public interface CarManufacturerRepository extends JpaRepository<CarManufacturer,Integer> {
    CarManufacturer findOfferById(int id);

    List<CarManufacturer> findAll();
}
