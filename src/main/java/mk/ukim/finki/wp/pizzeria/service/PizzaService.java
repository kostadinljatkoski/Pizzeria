package mk.ukim.finki.wp.pizzeria.service;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import mk.ukim.finki.wp.pizzeria.model.Pizza;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PizzaService {

    Pizza createPizza(String name, String description, List<String> ingredients);

    List<Pizza> getAllPizzas();

    Pizza updatePizza(String name, String description, List<String> ingredients);

    Optional<Pizza> findByName(String name);

    void deletePizza(String name);

    List<Pizza> getAllPizzasWithLessIngredientsThan(int totalIngredients);

    Set<Ingredient> getSameIngredientsFromTwoPizzas(String pizza1, String pizza2);

    List<Ingredient> getSameIngredientsFromTwoPizzasWithQuery(String pizza1, String pizza2);


}
