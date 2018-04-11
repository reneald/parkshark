package be.claimed.domain.members.emails;

import be.claimed.domain.entities.AbstractEntity;
import be.claimed.domain.members.Member;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = " emails")
public class Email extends AbstractEntity {

    @Column (name = "email")
    private String email;

//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "fk_member_id")
//    private Member member;

    public Email() {
        super();
    }

    public Email(UUID id) {
        super(id);
    }

    public Email(EmailBuilder emailBuilder) {
        super(emailBuilder.id);
        this.email = emailBuilder.email;
        //this.member = emailBuilder.member;
    }

    public String getEmail() {
        return email;
    }

//    public Member getMember() {
//        return member;
//    }

    public static class EmailBuilder {
        private UUID id;
        private String email;
        //private Member member;

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

//        public EmailBuilder withMember(Member member) {
//            this.member = member;
//            return this;
//        }
    }
}
