package be.claimed.domain.members.emails;

import be.claimed.domain.abstracts.AbstractEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class Email{

    @Column (name = "email")
    @javax.validation.constraints.Email(message = "Please enter a valid email address")
    private String email;

    private Email() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }

    public Email(EmailBuilder emailBuilder) {
        this.email = emailBuilder.email;
    }

    public String getEmail() {
        return email;
    }

    public static class EmailBuilder {
        private String email;

        public static EmailBuilder email() {
            return new EmailBuilder();
        }

        public Email build() {
            return new Email(this);
        }


        public EmailBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
    }
}
