package pl.ogloszenia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogloszenia.jpa.CarModel;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel,Integer> {
    CarModel findOfferById(int id);

    List<CarModel> findAll();

    List<CarModel> findByManufacturerId(int manufacturerId);
}
