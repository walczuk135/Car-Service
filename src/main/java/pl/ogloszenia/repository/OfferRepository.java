package pl.ogloszenia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ogloszenia.jpa.CarModel;
import pl.ogloszenia.jpa.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Offer findOfferById(int id);


    Page<Offer> findAll(Pageable pageable);


    List<Offer> findOfferByModelId(int modelId);


    @Query(
            value = "select o from Offer o where o.model.manufacturer.id=:id"
    )
    List<Offer> findOffersByManufacturerId(@Param("id") int manufacturerId);

}
