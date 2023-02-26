package cocktailofferer.service.image.query;

import cocktailofferer.service.image.ImageMetadataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageMetadataServiceQueryServiceImpl implements ImageMetadataQueryService {
    private final ImageMetadataRepository imageMetadataRepository;

    @Override
    public GetImageMetadataQueryResult getById(int id) {
        var imageMetadata = imageMetadataRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return GetImageMetadataQueryResult.builder()
                .id(imageMetadata.getId())
                .name(imageMetadata.getName())
                .key(imageMetadata.getImageKey())
                .build();
    }
}
