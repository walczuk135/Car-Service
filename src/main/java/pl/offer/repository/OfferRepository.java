package pl.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.offer.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Optional<Offer> findOfferById(int id);


    List<Offer> findAll();


    List<Offer> findOfferByModelId(int modelId);


    @Query(
            value = "select o from Offer o where o.model.manufacturer.id=:id"
    )
    List<Offer> findOffersByManufacturerId(@Param("id") int manufacturerId);

}
