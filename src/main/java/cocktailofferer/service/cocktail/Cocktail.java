package cocktailofferer.service.cocktail;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(unique = true)
    @Setter
    private String name;

    @Setter
    private boolean cooked;

    @Setter
    private boolean available;

    public Cocktail(String name) {
        this.name = name;
        this.cooked = false;
        this.available = false;
    }
}
