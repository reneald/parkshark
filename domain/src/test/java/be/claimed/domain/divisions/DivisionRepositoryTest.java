package be.claimed.domain.divisions;

import be.claimed.configuration.Config;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;
import java.util.List;


@SpringJUnitConfig(Config.class)
@Transactional
class DivisionRepositoryTest {
    private DivisionRepository divisionRepository;

//    public DivisionRepositoryTest() {
//
//    }

    @Autowired
    public DivisionRepositoryTest(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }


    @Test
    void create_shouldCreateDivisionInDB() {
        //GIVEN
        Division division = Division.DivisionBuilder.division().build();

        //WHEN
        divisionRepository.create(division);

        //THEN
        Assertions.assertThat(division.getId()).isNotNull();

    }

    @Test
    void getAll() {
        Division firstDivision = Division.DivisionBuilder.division().build();
        Division secondDivision = Division.DivisionBuilder.division().build();

        divisionRepository.create(firstDivision);
        divisionRepository.create(secondDivision);

        List<Division> actualDivisions = divisionRepository.getAll(Division.class);

        Assertions.assertThat(actualDivisions).containsExactly(firstDivision, secondDivision);
    }
}