package mk.ukim.finki.wp.pizzeria.repository;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredient, String> {

    List<Ingredient> getAllByNameContaining(String term);

    List<Ingredient> getAllBySpicy(boolean spicy);

    List<Ingredient> getAllBySpicyTrue();

}
