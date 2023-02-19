package cocktailofferer.service;

import lombok.Data;

@Data
public class Id<T> {
    private final T value;
}
