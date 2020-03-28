package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    public String name;

    public String description;

//    @JsonIgnore
//    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Ingredient> ingredients;

    public boolean veggie;

    public Pizza(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
