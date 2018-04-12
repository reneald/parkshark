package be.claimed.domain.members;


import be.claimed.domain.abstracts.AbstractEntity;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.members.emails.Email;
import be.claimed.domain.members.licensePlates.LicensePlate;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "members")
public class Member extends AbstractEntity {

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be empty")
    private String lastName;

    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_email_id")
    private Email email;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_member_id")
    private List<PhoneNumber> phoneNumbers;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_member_id")
    private List<LicensePlate> licensePlates;


    @Column(name = "registration_date")
    @NotNull(message = "registrationDate cannot be empty")
    private LocalDate registrationDate;

    public Member(MemberBuilder memberBuilder) {
        super(memberBuilder.memberId);
        firstName = memberBuilder.firstName;
        lastName = memberBuilder.lastName;
        address = memberBuilder.address;
        email = memberBuilder.email;
        phoneNumbers = memberBuilder.phoneNumbers;
        licensePlates = memberBuilder.licensePlate;
        registrationDate = memberBuilder.registrationDate;
    }

    private Member() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public Email getEmail() {
        return email;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<LicensePlate> getLicensePlates() {
        return licensePlates;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class MemberBuilder {
        private UUID memberId;
        private String firstName;
        private String lastName;
        private Address address;
        private Email email;
        private List<PhoneNumber> phoneNumbers;
        private List<LicensePlate> licensePlate;
        private LocalDate registrationDate;

        public static MemberBuilder member(){
            return new MemberBuilder();
        }

        public Member build(){
            return new Member (this);
        }

        public MemberBuilder withMemberId(UUID memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public MemberBuilder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public MemberBuilder withPhoneNumbers(List<PhoneNumber> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
            return this;
        }

        public MemberBuilder withLicensePlate(List<LicensePlate> licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public MemberBuilder withRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }
    }
}
