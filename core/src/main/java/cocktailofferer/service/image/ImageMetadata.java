package cocktailofferer.service.image;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = "id")
public class ImageMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = ImageMetadataConstraints.Key.MIN_SIZE, max = ImageMetadataConstraints.Key.MAX_SIZE)
    @Column(unique = true)
    private String key;

    @NotNull
    @Size(min = ImageMetadataConstraints.Name.MIN_SIZE, max = ImageMetadataConstraints.Name.MAX_SIZE)
    private String name;
}
