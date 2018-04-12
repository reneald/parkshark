package be.claimed.api.divisions;

import be.claimed.api.divisions.DivisionDto;
import be.claimed.api.divisions.DivisionMapper;
import be.claimed.domain.divisions.Division;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class DivisionMapperTest {
    private static DivisionMapper divisionMapper;

  @BeforeAll
  public static void instantiateMapper(){
      divisionMapper = new DivisionMapper();
  }

    @Test
    void toDto() {
        Division division = Division.DivisionBuilder.division()
                .withName("MyParkMate")
                .withOriginalName("MyMate")
                .withDirector("Marc")
                .withParentDivision(UUID.randomUUID())
                .build();

        DivisionDto actualDivision = divisionMapper.toDto(division);

        Assertions.assertThat(actualDivision).isEqualToIgnoringGivenFields(division, "id", "parentDivision");

    }

    @Test
    void toDomain() {
      DivisionDto divisionDto = DivisionDto.divisionDto()
              .withName("MyParkMate")
              .withOriginalName("MyMate")
              .withDirector("Marc")
              .withParentDivision(UUID.randomUUID().toString());

      Division actualDivision = divisionMapper.toDomain(divisionDto);

      Assertions.assertThat(actualDivision).isEqualToIgnoringGivenFields(divisionDto, "id", "parentDivision");

    }
}