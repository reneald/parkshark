package be.claimed.api.divisions;

import be.claimed.api.ControllerIntegrationTest;
import be.claimed.domain.divisions.DivisionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.inject.Inject;

class DivisionControllerTest extends ControllerIntegrationTest {

    @Inject
    private DivisionRepository divisionRepository;


    @Test
    void create() {
        DivisionDto divisionDto = DivisionDto.divisionDto()
                .withName("Bouh")
                .withOriginalName("Babidi")
                .withDirector("Bibidi");

        DivisionDto divisionDto1 = new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), divisionDto, DivisionDto.class);


        System.out.println(divisionDto1.getName());
        Assertions.assertThat(divisionDto1).isNotNull();
        Assertions.assertThat(divisionDto1.getId()).isNotNull().isNotEmpty();


    }
}