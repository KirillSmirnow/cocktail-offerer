package cocktailofferer.service.image.command;

import cocktailofferer.service.image.ImageMetadataConstraints;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateImageMetadataCommand(
        @NotNull
        @Size(min = ImageMetadataConstraints.Name.MIN_SIZE, max = ImageMetadataConstraints.Name.MAX_SIZE)
        String name,

        @NotNull
        @Size(min = ImageMetadataConstraints.Key.MIN_SIZE, max = ImageMetadataConstraints.Key.MAX_SIZE)
        String key
) {
}
