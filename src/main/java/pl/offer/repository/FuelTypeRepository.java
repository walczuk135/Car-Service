package pl.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offer.model.FuelType;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelType,Integer> {
    List<FuelType> findAll();
}
