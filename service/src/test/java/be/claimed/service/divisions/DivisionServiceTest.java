package be.claimed.service.divisions;

import be.claimed.configuration.Config;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;

@SpringJUnitConfig(Config.class)
class DivisionServiceTest {
    private DivisionService divisionService;
    private DivisionRepository divisionRepository;

    @Autowired
    public DivisionServiceTest(DivisionService divisionService, DivisionRepository divisionRepository) {
        this.divisionService = divisionService;
        this.divisionRepository = divisionRepository;
    }

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
    void create_divisionThatAlreadyHasId_shouldReturnException(){
        //GIVEN
        Division division = new Division();
        division.setId(15);

        //WHEN&THEN
        Assertions.assertThatExceptionOfType(InvalidDataAccessApiUsageException.class).isThrownBy(() -> divisionService.create(division));
    }

    @Test
    @Transactional
    @Ignore
    void create_divisionWithExistingOriginalName_shouldReturnException() {
        //GIVEN
        Division firstDivision = new Division("Hallo", "OriginalHallo", "John", 1);
        Division secondDivision = new Division("Hello", "OriginalHallo", "John", 1);
        divisionService.create(firstDivision);

        //WHEN&THEN
        Assertions.assertThatExceptionOfType(InvalidDataAccessApiUsageException.class).isThrownBy(() -> divisionService.create(secondDivision));
    }

}