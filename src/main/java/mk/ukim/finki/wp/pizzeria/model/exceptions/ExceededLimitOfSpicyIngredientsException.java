package mk.ukim.finki.wp.pizzeria.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceededLimitOfSpicyIngredientsException extends RuntimeException {
    public ExceededLimitOfSpicyIngredientsException() {
        super("The number of spicy ingredients can not be more than 3!");
    }
}
