package be.claimed.api.members.emails;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.members.emails.Email;

import javax.inject.Named;

@Named
public class EmailMapper extends AbstractMapper<EmailDto, Email> {

    @Override
    public EmailDto toDto(Email domainObject) {
        EmailDto dtoObject = new EmailDto();
        dtoObject.id = domainObject.getId().toString();
        dtoObject.email = domainObject.getEmail();
        return dtoObject;
    }

    @Override
    public Email toDomain(EmailDto dtoObject) {
        Email domainObject = Email.EmailBuilder.email()
                .withEmail(dtoObject.email)
                .build();
        return domainObject;
    }
}
