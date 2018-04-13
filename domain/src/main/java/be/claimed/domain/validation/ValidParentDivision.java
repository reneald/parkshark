package be.claimed.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ParentDivisionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParentDivision {
    String message() default "Invalid parent division";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
