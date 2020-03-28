package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidIngredientNameException;
import mk.ukim.finki.wp.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientsApi {

    private final IngredientService ingredientService;

    public IngredientsApi(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam String name,
                                       @RequestParam boolean spicy,
                                       @RequestParam float amount,
                                       @RequestParam boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder) {
        Ingredient result = ingredientService.createIngredient(name, spicy, amount, veggie);
//        response.setHeader("Location", builder.path("/ingredients/{id}").buildAndExpand(result.getName()).toUriString());
        return result;
    }

    @PatchMapping("/{name}")
    public Ingredient updateIngredient(@PathVariable String name,
                                       @RequestParam boolean spicy,
                                       @RequestParam float amount,
                                       @RequestParam boolean veggie) {
        return ingredientService.updateIngredient(name, spicy, amount, veggie);
    }

    @DeleteMapping("/{name}")
    public void deleteIngredient(@PathVariable String name) {
        ingredientService.deleteIngredient(name);
    }

    @GetMapping
    public Page<Ingredient> getAllIngredientsSortedByName(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                                          @RequestHeader(name = "size", defaultValue = "5", required = false) int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 10), Sort.by("name"));
        return ingredientService.getAllIngredientsSortedByName(pageable);
    }

    @GetMapping("/{name}")
    public Ingredient getIngredient(@PathVariable String name) {
        return ingredientService.findByName(name).orElseThrow(() -> new InvalidIngredientNameException(name));
    }

    @GetMapping(params = "spicy")
    public List<Ingredient> getAllSpicyIngredients(@RequestParam boolean spicy) {
        return ingredientService.getAllSpicyIngredients(spicy);
    }

    @GetMapping("/{name}/pizzas")
    public List<Pizza> getAllPizzasWithIngredient(@PathVariable String name) {
        return ingredientService.getAllPizzasWithIngredient(name);
    }

    @GetMapping(params = "term")
    public List<Ingredient> getAllSpicyIngredients(@RequestParam String term) {
        return ingredientService.searchIngredients(term);
    }

}
