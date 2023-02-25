package cocktailofferer.service.image.command;

import lombok.Builder;

@Builder
public record CreateImageMetadataCommand(
        String name,
        String key
) {
}
