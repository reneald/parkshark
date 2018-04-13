package be.claimed.domain.contactPersons;

import be.claimed.domain.abstracts.AbstractEntity;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.members.emails.Email;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contact_persons")
public class ContactPerson extends AbstractEntity{

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Embedded
    private List<@NotNull(message = "You must provide at least one phone number")  PhoneNumber> phoneNumbers;

    @Embedded
    @NotNull
    private Email email;

    @Embedded
    private Address address;

    private ContactPerson() {
    }

    public ContactPerson(ContactPersonBuilder contactPersonBuilder) {
        super(contactPersonBuilder.id);
        this.firstName = contactPersonBuilder.firstName;
        this.lastName = contactPersonBuilder.lastName;
        this.phoneNumbers = contactPersonBuilder.phoneNumbers;
        this.email = contactPersonBuilder.email;
        this.address = contactPersonBuilder.address;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public static class ContactPersonBuilder{
        private UUID id;
        private String firstName;
        private String lastName;
        private List<PhoneNumber> phoneNumbers;
        private Email email;
        private Address address;

        public static ContactPersonBuilder contactPerson(){
            return new ContactPersonBuilder();
        }

        public ContactPerson build(){
            return new ContactPerson(this);
        }

        public ContactPersonBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ContactPersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ContactPersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactPersonBuilder withPhoneNumbers(List<PhoneNumber> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
            return this;
        }

        public ContactPersonBuilder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public ContactPersonBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }
    }
}
