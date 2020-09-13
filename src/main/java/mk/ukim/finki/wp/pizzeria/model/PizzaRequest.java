package mk.ukim.finki.wp.pizzeria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PizzaRequest {

    public String name;
    public String description;
    public List<String> ingredients;

}
