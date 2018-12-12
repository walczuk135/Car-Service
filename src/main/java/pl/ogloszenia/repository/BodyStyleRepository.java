package pl.ogloszenia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ogloszenia.jpa.BodyStyle;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BodyStyleRepository extends JpaRepository<BodyStyle,Integer> {
    List<BodyStyle> findAll();
}
