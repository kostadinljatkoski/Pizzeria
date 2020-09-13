package mk.ukim.finki.wp.pizzeria.service.impl;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import mk.ukim.finki.wp.pizzeria.model.Pizza;
import mk.ukim.finki.wp.pizzeria.model.exceptions.InvalidIngredientNameException;
import mk.ukim.finki.wp.pizzeria.model.exceptions.InvalidPizzaNameException;
import mk.ukim.finki.wp.pizzeria.repository.IngredientsRepository;
import mk.ukim.finki.wp.pizzeria.repository.PizzaRepository;
import mk.ukim.finki.wp.pizzeria.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientsRepository ingredientsRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository, IngredientsRepository ingredientsRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Pizza createPizza(String name, String description, List<String> ingredients) {
        List<Ingredient> ingredientList = ingredients.stream()
                .map(id -> ingredientsRepository.findById(id).orElseThrow(() -> new InvalidIngredientNameException(id)))
                .collect(Collectors.toList());
        boolean veggie = ingredientList.stream().allMatch(Ingredient::isVeggie);
        return pizzaRepository.save(new Pizza(name, description, ingredientList, veggie));
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza updatePizza(String name, String description, List<String> ingredients) {
        Pizza pizza = pizzaRepository.findById(name).orElseThrow(() -> new InvalidPizzaNameException(name));
        List<Ingredient> ingredientList = ingredients.stream()
                .map(id -> ingredientsRepository.findById(id).orElseThrow(() -> new InvalidIngredientNameException(id)))
                .collect(Collectors.toList());
        pizza.description = description;
        pizza.ingredients = ingredientList;
        pizza.veggie = ingredientList.stream().allMatch(Ingredient::isVeggie);
        return pizzaRepository.save(pizza);
    }

    @Override
    public Optional<Pizza> findByName(String name) {
        return pizzaRepository.findById(name);
    }

    @Override
    public void deletePizza(String name) {
        findByName(name).orElseThrow(() -> new InvalidPizzaNameException(name));
        pizzaRepository.deleteById(name);
    }

    @Override
    public List<Pizza> getAllPizzasWithLessIngredientsThan(int totalIngredients) {
        return pizzaRepository.getAllPizzasWithLessIngredientsThan(totalIngredients);
    }

    @Override
    public Set<Ingredient> getSameIngredientsFromTwoPizzas(String pizza1, String pizza2) {
        Pizza p1 = findByName(pizza1).orElseThrow(() -> new InvalidPizzaNameException(pizza1));
        Pizza p2 = findByName(pizza2).orElseThrow(() -> new InvalidPizzaNameException(pizza2));
        Set<Ingredient> ingredients1 = new HashSet<>(p1.ingredients);
        Set<Ingredient> ingredients2 = new HashSet<>(p2.ingredients);
        ingredients1.retainAll(ingredients2);
        return ingredients1;
    }

    @Override
    public List<Ingredient> getSameIngredientsFromTwoPizzasWithQuery(String pizza1, String pizza2) {
        return pizzaRepository.getSameIngredientsFromTwoPizzasWithQuery(pizza1, pizza2);
    }
}
