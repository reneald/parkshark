package be.claimed.domain.members;

import be.claimed.domain.entities.AbstractEntity;

import java.util.UUID;

public class Email extends AbstractEntity {

    private String email;
    private UUID memberId;

    public Email(EmailBuilder emailBuilder) {
        super(emailBuilder.id);
        this.email = emailBuilder.email;
        this.memberId = emailBuilder.memberId;
    }

    public String getEmail() {
        return email;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public static class EmailBuilder {
        private UUID id;
        private String email;
        private UUID memberId;

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

        public EmailBuilder withMemberId(UUID memberId) {
            this.memberId = memberId;
            return this;
        }
    }
}
