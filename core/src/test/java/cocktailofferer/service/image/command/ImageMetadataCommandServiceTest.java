package cocktailofferer.service.image.command;


import cocktailofferer.service.image.ImageMetadataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(classes = ImageMetadataCommandServiceTestConfiguration.class)
class ImageMetadataCommandServiceTest {
    @Autowired
    private ImageMetadataCommandService imageMetadataCommandService;
    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Test
    public void givenCreateImageMetadataCommand_whenCreate_thenImageMetadataCreated() {
        var createImageMetadataCommand = createCreateImageMetadataCommand();

        var createImageMetadataCommandResult = imageMetadataCommandService.create(createImageMetadataCommand);

        assertThat(imageMetadataRepository.existsById(createImageMetadataCommandResult.id())).isTrue();
    }

    private CreateImageMetadataCommand createCreateImageMetadataCommand() {
        return CreateImageMetadataCommand.builder()
                .name("name")
                .key("key")
                .build();
    }

    @Test
    public void givenCreateImageMetadataCommand_whenCreate_thenCorrectCreateImageMetadataResultReturned() {
        var createImageMetadataCommand = createCreateImageMetadataCommand();

        var createImageMetadataCommandResult = imageMetadataCommandService.create(createImageMetadataCommand);

        assertThat(createImageMetadataCommandResult.name()).isEqualTo(createImageMetadataCommand.name());
        assertThat(createCreateImageMetadataCommand().key()).isEqualTo(createImageMetadataCommand.key());
    }
}