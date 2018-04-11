package be.claimed.domain.members.emails;

import be.claimed.domain.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table (name = " emails")
public class Email extends AbstractEntity {

    @Column (name = "email")
    private String email;

    @Column (name = "fk_member_id")
    private UUID memberId;

    public Email(UUID id) {
        super(id);
    }

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
