package be.claimed.api.divisions;

import be.claimed.MockitoExtension;
import be.claimed.api.ControllerIntegrationTest;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DivisionControllerIntegrationTest extends ControllerIntegrationTest<Division, DivisionRepository> {



    @Override
    @BeforeEach
    public void setUpDatabase() {
        clearDataBase(Division.class);
    }

    @Override
    @AfterEach
    public void breakDatabase() {
        clearDataBase(Division.class);
    }

    @Test
    void create() {
        DivisionDto divisionDto = DivisionDto.divisionDto()
                .withName("Bouh")
                .withOriginalName("Babidi")
                .withDirector("Bibidi");

        DivisionDto divisionDto1 = new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), divisionDto, DivisionDto.class);
        Assertions.assertThat(divisionDto1).isNotNull();
        Assertions.assertThat(divisionDto1.getId()).isNotNull().isNotEmpty();

    }

    @Test
    void getAll() {
        DivisionDto divisionDto1 = DivisionDto.divisionDto().withName("ekufg").withOriginalName("qskdug").withDirector("Bibidi");
        DivisionDto divisionDto2 = DivisionDto.divisionDto().withName("bouri").withOriginalName("boura").withDirector("Bibidi");

        new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), divisionDto1, DivisionDto.class);
        new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), divisionDto2, DivisionDto.class);

        DivisionDto[] divisionDtos = new TestRestTemplate().getForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), DivisionDto[].class);
        Assertions.assertThat(divisionDtos[0].getName()).isEqualTo("ekufg");
        Assertions.assertThat(divisionDtos[1].getName()).isEqualTo("bouri");
        Assertions.assertThat(divisionDtos[1].getId()).isNotNull();

    }
}