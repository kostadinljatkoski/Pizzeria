package mk.ukim.finki.wp.pizzeria.repository;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import mk.ukim.finki.wp.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    @Query("select p from Pizza p where size(p.ingredients) < :totalIngredients")
    List<Pizza> getAllPizzasWithLessIngredientsThan(int totalIngredients);

    @Query("select i from Pizza p join p.ingredients i where p.name = :pizza1 and i.name in " +
            "(select i2.name from Pizza p2 join p2.ingredients i2 where p2.name = :pizza2)")
    List<Ingredient> getSameIngredientsFromTwoPizzasWithQuery(String pizza1, String pizza2);

    List<Pizza> findAllByIngredientsContaining(Ingredient ingredient);

}
