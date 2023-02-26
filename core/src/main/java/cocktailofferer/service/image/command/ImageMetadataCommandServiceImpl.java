package cocktailofferer.service.image.command;

import cocktailofferer.service.image.ImageMetadata;
import cocktailofferer.service.image.ImageMetadataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageMetadataCommandServiceImpl implements ImageMetadataCommandService {
    private final ImageMetadataRepository imageMetadataRepository;

    public CreateImageMetadataCommandResult create(CreateImageMetadataCommand command) {
        var imageMetadata = ImageMetadata.builder()
                .name(command.name())
                .imageKey(command.key())
                .build();
        imageMetadata = imageMetadataRepository.save(imageMetadata);
        return CreateImageMetadataCommandResult.builder()
                .id(imageMetadata.getId())
                .name(imageMetadata.getName())
                .key(imageMetadata.getImageKey())
                .build();
    }
}
