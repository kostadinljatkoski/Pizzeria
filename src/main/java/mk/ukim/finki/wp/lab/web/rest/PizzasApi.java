package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidPizzaNameException;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzasApi {

    private final PizzaService pizzaService;

    public PizzasApi(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam List<String> ingredients,
                             HttpServletResponse response,
                             UriComponentsBuilder builder) {
        Pizza result = pizzaService.createPizza(name, description, ingredients);
        response.setHeader("Location", builder.path("/pizzas/{id}").buildAndExpand(result.getName()).toUriString());
        return result;
    }

    @PutMapping("/{name}")
    public Pizza updatePizza(@PathVariable String name,
                             @RequestParam String description,
                             @RequestParam List<String> ingredients) {
        return pizzaService.updatePizza(name, description, ingredients);
    }

    @DeleteMapping("/{name}")
    public void deletePizza(@PathVariable String name) {
        pizzaService.deletePizza(name);
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{name}")
    public Pizza getPizza(@PathVariable String name) {
        return pizzaService.findByName(name).orElseThrow(() -> new InvalidPizzaNameException(name));
    }

    @GetMapping(params = "totalIngredients")
    public List<Pizza> getAllPizzasWithLessIngredientsThan(@RequestParam int totalIngredients) {
        return pizzaService.getAllPizzasWithLessIngredientsThan(totalIngredients);
    }

    @GetMapping(path = "/compare")
    public Set<Ingredient> getSameIngredientsFromTwoPizzas(@RequestParam String pizza1,
                                                           @RequestParam String pizza2) {
        return pizzaService.getSameIngredientsFromTwoPizzas(pizza1, pizza2);
    }

    @GetMapping(path = "/compare/query")
    public List<Ingredient> getSameIngredientsFromTwoPizzasWithQuery(@RequestParam String pizza1,
                                                                    @RequestParam String pizza2) {
        return pizzaService.getSameIngredientsFromTwoPizzasWithQuery(pizza1, pizza2);
    }
}
