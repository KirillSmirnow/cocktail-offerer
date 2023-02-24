package cocktailofferer.service.image.query;

import cocktailofferer.service.image.ImageMetadata;
import cocktailofferer.service.image.ImageMetadataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = ImageMetadataQueryServiceTestConfiguration.class)
public class ImageMetadataQueryServiceTest {
    @Autowired
    private ImageMetadataQueryService imageMetadataQueryService;
    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Test
    public void givenImageMetadata_whenGetById_thenCorrectMetadataReturned() {
        var imageMetadata = createImageMetadata();

        var actualImageMetadata = imageMetadataQueryService.getById(imageMetadata.getId());

        assertThat(actualImageMetadata.id()).isEqualTo(imageMetadata.getId());
        assertThat(actualImageMetadata.name()).isEqualTo(imageMetadata.getName());
        assertThat(actualImageMetadata.key()).isEqualTo(imageMetadata.getKey());
    }

    private ImageMetadata createImageMetadata() {
        var imageMetadata = ImageMetadata.builder()
                .id(1)
                .key("key")
                .name("name")
                .build();
        return imageMetadataRepository.save(imageMetadata);
    }

    @Test
    public void givenImageMetadata_whenGetById_thenCorrectExceptionThrown() {

        assertThrows(ImageNotFoundException.class, () -> imageMetadataQueryService.getById(2));
    }
}
