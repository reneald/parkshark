package be.claimed.api;

import be.claimed.domain.divisions.DivisionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.inject.Inject;

@SpringBootTest(classes = {TestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DivisionControllerTest  {

    @LocalServerPort
//    @Value("${server.port}")
    public int port;

    @Inject
    private DivisionRepository divisionRepository;


    @Test
    void create() {
        DivisionDto divisionDto = DivisionDto.divisionDto()
                .withName("Bouh")
                .withOriginalName("Babidi")
                .withDirector("Bibidi");

       DivisionDto divisionDto1 =  new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s", 9123, "divisions" ), divisionDto, DivisionDto.class) ;

        Assertions.assertThat(divisionDto1).isNotNull();
//        Assertions.assertThat(divisionDto1.getId()).isNotNull().isNotEmpty();



    }
}