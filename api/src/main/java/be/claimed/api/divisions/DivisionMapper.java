package be.claimed.api.divisions;

import be.claimed.domain.divisions.Division;

import javax.inject.Named;
import java.util.UUID;

@Named
public class DivisionMapper {
    public DivisionDto toDto (Division division){
        return DivisionDto.divisionDto()
                .withId(division.getId() == null ? null : division.getId().toString())
                .withName(division.getName())
                .withOriginalName(division.getOriginalName())
                .withDirector(division.getDirector())
                .withParentDivision(division.getParentDivision() == null? null: division.getParentDivision().toString());
    }

    public Division toDomain(DivisionDto divisionDto) {
        return Division.DivisionBuilder.division()
                .withName(divisionDto.getName())
                .withOriginalName(divisionDto.getOriginalName())
                .withDirector(divisionDto.getDirector())
                .withParentDivision(divisionDto.getParentDivision()==null ? null : UUID.fromString(divisionDto.getParentDivision()))
                .build();
    }
}
