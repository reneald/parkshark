package be.claimed.domain.members.emails;

import be.claimed.domain.entities.AbstractEntity;
import be.claimed.domain.members.Member;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = " emails")
public class Email extends AbstractEntity {

    @Column (name = "email")
    @javax.validation.constraints.Email(message = "Please enter a valid email address")
    private String email;

    public Email() {
        super();
    }

    public Email(UUID id) {
        super(id);
    }

    public Email(EmailBuilder emailBuilder) {
        super(emailBuilder.id);
        this.email = emailBuilder.email;
    }

    public String getEmail() {
        return email;
    }

    public static class EmailBuilder {
        private UUID id;
        private String email;

        public static EmailBuilder email() {
            return new EmailBuilder();
        }

        public Email build() {
            return new Email(this);
        }

        public EmailBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public EmailBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
    }
}
