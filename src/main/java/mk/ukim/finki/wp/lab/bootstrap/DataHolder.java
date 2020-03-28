package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.IngredientsRepository;
import mk.ukim.finki.wp.lab.repository.PizzaRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataHolder {

    private final PizzaRepository pizzaRepository;
    private final IngredientsRepository ingredientsRepository;
    private static final List<Ingredient> ingredients = new ArrayList<>();
    private static final List<Pizza> pizzas = new ArrayList<>();

    public DataHolder(PizzaRepository pizzaRepository, IngredientsRepository ingredientsRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @PostConstruct
    public void init() {
        initIngredients();
        initPizzas();
    }

    private void initIngredients() {
        ingredients.add(new Ingredient("tomato sauce", false, 200, true));
        ingredients.add(new Ingredient("mozzarella", false, 100, true));
        ingredients.add(new Ingredient("oregano", false, 50, true));
        ingredients.add(new Ingredient("garlic", true, 30, true));
        ingredients.add(new Ingredient("basil", false, 410, true));
        ingredients.add(new Ingredient("mushrooms", false, 623, true));
        ingredients.add(new Ingredient("ham", false, 78, false));
        ingredients.add(new Ingredient("artichokes", false, 30, true));
        ingredients.add(new Ingredient("olives", false, 52, true));
        ingredients.add(new Ingredient("eggs", false, 10, true));
        ingredients.add(new Ingredient("bacon", false, 963, false));
        ingredients.add(new Ingredient("seafood", false, 12, false));
        ingredients.add(new Ingredient("gorgonzola cheese", false, 10, true));
        ingredients.add(new Ingredient("onions", true, 52, true));
        ingredients.add(new Ingredient("anchovies", false, 74, true));
        ingredients.add(new Ingredient("gorgonzola cheese", false, 20, true));
        ingredients.add(new Ingredient("parmesan", false, 20, true));
        ingredients.add(new Ingredient("pepperoni", true, 20, true));
        if(ingredientsRepository.count() == 0)
            ingredientsRepository.saveAll(ingredients);
    }

    private void initPizzas() {
        pizzas.add(new Pizza("Margherita", "tomato sauce, mozzarella, oregano"));
        pizzas.add(new Pizza("Marinara", "tomato sauce, garlic, basil"));
        pizzas.add(new Pizza("Quattro Stagioni", "tomato sauce, mozzarella, mushrooms, ham, artichokes, olives, oregano"));
        pizzas.add(new Pizza("Carbonara", "tomato sauce, mozzarella, parmesan, eggs, bacon"));
        pizzas.add(new Pizza("Frutti di Mare", "tomato sauce, seafood"));
        pizzas.add(new Pizza("Quattro Formaggi", "tomato sauce, mozzarella, parmesan, gorgonzola cheese, artichokes, oregano"));
        pizzas.add(new Pizza("Napoletana", "tomato sauce, mozzarella, oregano, anchovies"));
        pizzas.add(new Pizza("Pugliese", "tomato sauce, mozzarella, oregano, onions"));
        pizzas.add(new Pizza("Montanara", "tomato sauce, mozzarella, mushrooms, pepperoni"));

        pizzas.forEach(pizza -> {
            List<Ingredient> pizzaIngredients = Arrays.stream(pizza.getDescription().split(","))
                    .map(String::trim)
                    .map(ingredientString -> ingredients.stream()
                            .filter(i -> i.getName().equals(ingredientString))
                            .findFirst().orElseThrow(RuntimeException::new))
                    .collect(Collectors.toList());
            pizza.setIngredients(pizzaIngredients);
            pizza.setVeggie(pizza.getIngredients().stream().allMatch(Ingredient::isVeggie));
        });
        if(pizzaRepository.count() == 0)
            pizzaRepository.saveAll(pizzas);
    }
}
