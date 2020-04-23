package pl.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offer.jpa.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel,Integer> {
    Optional<CarModel> findOfferById(int id);

    List<CarModel> findAll();

    List<CarModel> findByManufacturerId(int manufacturerId);
}
