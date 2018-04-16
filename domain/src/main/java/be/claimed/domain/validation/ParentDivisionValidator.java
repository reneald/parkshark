package be.claimed.domain.validation;

import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class ParentDivisionValidator implements ConstraintValidator<ValidParentDivision, UUID> {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public void initialize(ValidParentDivision constraintAnnotation) {
        divisionRepository = ServiceUtils.getDivisionRepository();
    }

    @Override
    public boolean isValid(UUID parentDivisionId, ConstraintValidatorContext context) {
        return parentDivisionId == null || divisionRepository.findById(parentDivisionId, Division.class) != null;
    }
}