package be.claimed.domain.addresses;

import be.claimed.domain.abstracts.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Embeddable
public class Address {

    @Column (name = "street_name")
    @Size(min = 5, max = 150, message = "the street name has to be of a length between 5 and  150 characters")
    private String streetName;

    @Column (name = "street_number")
    @Size(min = 1, max = 5, message = "the street name has to be of a length between 1 and  5 characters")
    private String streetNumber;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_postal_code_id")
    @NotNull(message = "a post code should be provided")
    private PostCode postCode;

    private Address() {
    }

    public Address(AddressBuilder addressBuilder) {
        this.streetName = addressBuilder.streetName;
        this.streetNumber = addressBuilder.streetNumber;
        this.postCode = addressBuilder.postCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public static class AddressBuilder {
        private UUID id;
        private String streetName;
        private String streetNumber;
        private PostCode postCode;

        public static AddressBuilder address() {
            return new AddressBuilder();
        }

        public Address build() {
            return new Address(this);
        }

        public AddressBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withPostCode(PostCode postCode) {
            this.postCode = postCode;
            return this;
        }
    }
}
