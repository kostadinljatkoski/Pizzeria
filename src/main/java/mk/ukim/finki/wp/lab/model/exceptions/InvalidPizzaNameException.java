package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPizzaNameException extends RuntimeException {
    public InvalidPizzaNameException(String name) {
        super(String.format("Pizza with name %s does not exist", name));
    }
}
