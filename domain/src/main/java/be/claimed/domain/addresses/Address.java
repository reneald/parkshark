package be.claimed.domain.addresses;

import be.claimed.domain.entities.Entity;

import java.util.UUID;

public class Address extends Entity {

    private String streetName;
    private String streetNumber;
    private PostCode postCode;

    public Address(AddressBuilder addressBuilder) {
        super(addressBuilder.id);
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
