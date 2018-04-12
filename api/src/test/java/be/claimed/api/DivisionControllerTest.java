package be.claimed.api;

import be.claimed.domain.divisions.DivisionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@SpringBootTest(classes = {TestApplication.class})
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
class DivisionControllerTest  {

    @LocalServerPort
    @Value("${server.port}")
    public int port;

    @Inject
    private DivisionRepository divisionRepository;


    @Test
    void create() {
        DivisionDto divisionDto = DivisionDto.divisionDto()
                .withName("Bouh")
                .withOriginalName("Babidi")
                .withDirector("Bibidi");

       DivisionDto divisionDto1 =  new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", 9123, "divisions" ), divisionDto, DivisionDto.class) ;


        System.out.println(divisionDto1.getName());
        Assertions.assertThat(divisionDto1).isNotNull();
        Assertions.assertThat(divisionDto1.getId()).isNotNull().isNotEmpty();



    }
}