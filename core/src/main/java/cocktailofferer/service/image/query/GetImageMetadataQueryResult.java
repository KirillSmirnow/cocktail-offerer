package cocktailofferer.service.image.query;

import lombok.Builder;

@Builder
public record GetImageMetadataQueryResult(
        int id,
        String key,
        String name
) {
}
