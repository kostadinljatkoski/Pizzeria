package mk.ukim.finki.wp.lab.repository.inmemory;

import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Profile("servlets")
@Repository
public class PizzaRepository {

    private static final List<Pizza> pizzas = new ArrayList<>();

    @PostConstruct
    public void init() {
//        pizzas.add(new Pizza("Margherita", "tomato sauce, mozzarella"));
//        pizzas.add(new Pizza("Carbonara", "fresh cream, mozzarella, bacon"));
//        pizzas.add(new Pizza("Vegetariana", "tomato sauce, mushrooms"));
//        pizzas.add(new Pizza("Calzone", "pizza dough, ricotta, pepperoni, pizza sauce, olive oil"));
//        pizzas.add(new Pizza("Cheddar", "cheddar, tomato sauce"));
//        pizzas.add(new Pizza("Capricciosa", "tomato sauce, cheese, ham"));
//        pizzas.add(new Pizza("Burger Classic", "barbecue sauce, beef, mozzarella, onions"));
//        pizzas.add(new Pizza("Burger Barbecue", "ham, chicken meat, onions"));
//        pizzas.add(new Pizza("Pepperoni", "tomato sauce, mozzarella, sausage"));
//        pizzas.add(new Pizza("Quattro Formaggi", "Taleggio, Mascarpone, Gorgonzola, Parmesan"));
    }

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }
}
