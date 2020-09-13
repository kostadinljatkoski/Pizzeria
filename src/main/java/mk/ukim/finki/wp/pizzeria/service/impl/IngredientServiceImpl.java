package mk.ukim.finki.wp.pizzeria.service.impl;

import mk.ukim.finki.wp.pizzeria.model.Ingredient;
import mk.ukim.finki.wp.pizzeria.model.Pizza;
import mk.ukim.finki.wp.pizzeria.model.exceptions.ExceededLimitOfSpicyIngredientsException;
import mk.ukim.finki.wp.pizzeria.model.exceptions.IngredientAlreadyExistsException;
import mk.ukim.finki.wp.pizzeria.model.exceptions.InvalidIngredientNameException;
import mk.ukim.finki.wp.pizzeria.repository.IngredientsRepository;
import mk.ukim.finki.wp.pizzeria.repository.PizzaRepository;
import mk.ukim.finki.wp.pizzeria.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;
    private final PizzaRepository pizzaRepository;

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository, PizzaRepository pizzaRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie) {
        if(this.ingredientsRepository.findById(name).isPresent())
            throw new IngredientAlreadyExistsException(name);

        if(spicy && this.ingredientsRepository.getAllBySpicyTrue().size() == 3)
            throw new ExceededLimitOfSpicyIngredientsException();

        return this.ingredientsRepository.save(new Ingredient(name, spicy, amount, veggie));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return this.ingredientsRepository.findAll();
    }

    @Override
    public List<Ingredient> searchIngredients(String term) {
        return this.ingredientsRepository.getAllByNameContaining(term);
    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return this.ingredientsRepository.findById(name);
    }

    @Override
    public Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = this.ingredientsRepository.findById(name).orElseThrow(() -> new InvalidIngredientNameException(name));

        if(this.ingredientsRepository.getAllBySpicyTrue().size() == 3 && !ingredient.isSpicy() && spicy)
            throw new ExceededLimitOfSpicyIngredientsException();

        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);
        return this.ingredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
//        findByName(name).orElseThrow(() -> new InvalidIngredientNameException(name));
        Ingredient ingredient = this.ingredientsRepository.findById(name).orElseThrow(() -> new InvalidIngredientNameException(name));
        List<Pizza> pizzas = pizzaRepository.findAllByIngredientsContaining(ingredient);
        pizzas.forEach(pizza -> {
            List<Ingredient> newIngredients = pizza.getIngredients().stream()
                    .filter(i -> !i.getName().equals(name))
                    .collect(Collectors.toList());
            pizza.setIngredients(newIngredients);
            pizzaRepository.save(pizza);
        });
        this.ingredientsRepository.deleteById(name);
    }

    @Override
    public Page<Ingredient> getAllIngredientsSortedByName(Pageable pageable) {
        return this.ingredientsRepository.findAll(pageable);
    }

    @Override
    public List<Ingredient> getAllSpicyIngredients(boolean spicy) {
        return this.ingredientsRepository.getAllBySpicy(spicy);
    }

    @Override
    public List<Pizza> getAllPizzasWithIngredient(String name) {
        Ingredient ingredient = findByName(name).orElseThrow(() -> new InvalidIngredientNameException(name));
        return this.pizzaRepository.findAllByIngredientsContaining(ingredient);
    }
}
