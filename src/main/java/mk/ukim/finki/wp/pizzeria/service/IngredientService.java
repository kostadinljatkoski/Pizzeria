package mk.ukim.finki.wp.pizzeria.service;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import mk.ukim.finki.wp.pizzeria.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IngredientService {

    Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie);

    List<Ingredient> getAllIngredients();

    List<Ingredient> searchIngredients(String term);

    Optional<Ingredient> findByName(String name);

    Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie);

    void deleteIngredient(String name);

    Page<Ingredient> getAllIngredientsSortedByName(Pageable pageable);

    List<Ingredient> getAllSpicyIngredients(boolean spicy);

    List<Pizza> getAllPizzasWithIngredient(String name);
}
