package pl.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.offer.jpa.BodyStyle;

import java.util.List;

public interface BodyStyleRepository extends JpaRepository<BodyStyle,Integer> {
    List<BodyStyle> findAll();
}
