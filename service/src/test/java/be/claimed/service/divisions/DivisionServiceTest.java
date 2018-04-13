package be.claimed.service.divisions;

import be.claimed.MockitoExtension;
import be.claimed.configuration.Config;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Transactional
@SpringJUnitConfig(Config.class)
class DivisionServiceTest {
    private DivisionService divisionService;
    private DivisionRepository divisionRepository;
//    @Mock
//    private DivisionRepository mockRepository;

    @Autowired
    public DivisionServiceTest(DivisionService divisionService, DivisionRepository divisionRepository) {
        this.divisionService = divisionService;
        this.divisionRepository = divisionRepository;
    }

//    @BeforeEach
//    void setUp() {
//        Mockito.when(mockRepository.getAll(Division.class)).thenReturn();
//    }

//    @AfterEach
//    public void clearDataBase() {
//        divisionRepository.getEntityManager().createQuery("delete from Division").executeUpdate();
//    }


    @Test
    void create_shouldCallCreateInRepository() {
        //GIVEN
        Division division = new Division();

        //WHEN
        Division actualDivision = divisionService.create(division);

        //THEN
        Assertions.assertThat(actualDivision.getId()).isNotNull();
    }

    @Test
    void create_divisionThatAlreadyHasId_shouldReturnException() {
        //GIVEN
        Division division = Division.DivisionBuilder.division().withOriginalName("kjhdfsdko").withParentDivision(UUID.randomUUID()).withDirector("dfs").withName("efougsdlkfg").build();
        division.setId(UUID.randomUUID());

        //WHEN&THEN
        Assertions.assertThatExceptionOfType(InvalidDataAccessApiUsageException.class).isThrownBy(() -> divisionService.create(division));
    }

    @Test
    void create_divisionWithExistingOriginalName_shouldReturnException() {
        //GIVEN
        Division firstDivision = Division.DivisionBuilder.division().withOriginalName("OriginalHello").build();
        Division secondDivision = Division.DivisionBuilder.division().withOriginalName("OriginalHello").build();
        divisionService.create(firstDivision);

        //WHEN&THEN
        Assertions.assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> divisionService.create(secondDivision));
    }

    @Test
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
        firstDivision.setParentDivision(firstDivision.getId());
        divisionRepository.create(secondDivision);
        secondDivision.setParentDivision(firstDivision.getId());

        List<Division> actualDivisions = divisionService.getAll();

        Assertions.assertThat(actualDivisions).containsExactly(firstDivision, secondDivision);
    }
}