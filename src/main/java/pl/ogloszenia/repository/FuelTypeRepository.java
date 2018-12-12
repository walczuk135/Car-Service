package pl.ogloszenia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogloszenia.jpa.FuelType;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelType,Integer> {
    List<FuelType> findAll();
}
