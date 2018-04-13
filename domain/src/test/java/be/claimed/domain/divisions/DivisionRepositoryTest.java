package be.claimed.domain.divisions;

import be.claimed.MockitoExtension;
import be.claimed.configuration.Config;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;


@SpringJUnitConfig(Config.class)
@Transactional
@ExtendWith(MockitoExtension.class)
class DivisionRepositoryTest {
    private DivisionRepository divisionRepository;

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
    @Transactional
    void getAll() {
        Division firstDivision = Division.DivisionBuilder.division()
                .withName("firstDivision")
                .withOriginalName("originalFirstName")
                .withDirector("Yoda")
                .build();
        Division secondDivision = Division.DivisionBuilder.division()
                .withName("secondDivision")
                .withOriginalName("originalSecondName")
                .withDirector("Obi-Wan")
                .build();

        divisionRepository.create(firstDivision);
        divisionRepository.create(secondDivision);

        List<Division> actualDivisions = divisionRepository.getAll(Division.class);

        Assertions.assertThat(actualDivisions).containsExactly(firstDivision, secondDivision);
    }

    @Test
    void findById_whenGivenValidId_shouldReturnDivision() {
        Division firstDivision = Division.DivisionBuilder.division().build();
        divisionRepository.create(firstDivision);
        UUID divisionId = firstDivision.getId();

        Division actualDivision = divisionRepository.findById(divisionId, Division.class);

        Assertions.assertThat(actualDivision).isEqualTo(firstDivision);
    }

    @Test
    void findById_whenGivenInvalidId_shouldReturnNull() {
        Division actualDivision = divisionRepository.findById(UUID.randomUUID(), Division.class);

        Assertions.assertThat(actualDivision).isNull();
    }
}