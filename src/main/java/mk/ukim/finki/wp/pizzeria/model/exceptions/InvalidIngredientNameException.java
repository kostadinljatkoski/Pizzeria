package mk.ukim.finki.wp.pizzeria.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidIngredientNameException extends RuntimeException {
    public InvalidIngredientNameException(String name) {
        super(String.format("Ingredient with name %s does not exist", name));
    }
}
