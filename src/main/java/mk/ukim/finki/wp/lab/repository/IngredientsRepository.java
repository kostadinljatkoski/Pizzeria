package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredient, String> {

    List<Ingredient> getAllByNameContaining(String term);

    List<Ingredient> getAllBySpicy(boolean spicy);

    List<Ingredient> getAllBySpicyTrue();

}
