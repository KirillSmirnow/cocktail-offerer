package cocktailofferer.service.image.command;

import lombok.Builder;

@Builder
public record CreateImageMetadataCommandResult(
        int id,
        String key,
        String name
) {
}
