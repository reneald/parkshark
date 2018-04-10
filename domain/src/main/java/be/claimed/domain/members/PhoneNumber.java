package be.claimed.domain.members;

import be.claimed.domain.entities.Entity;

import java.util.UUID;

public class PhoneNumber extends Entity {
    private String countryPrefix;
    private String number;
    private UUID memberId;

    public PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        super(phoneNumberBuilder.id);
        this.countryPrefix = phoneNumberBuilder.countryPrefix;
        this.number = phoneNumberBuilder.number;
        this.memberId = phoneNumberBuilder.memberId;
    }

    public String getCountryPrefix() {
        return countryPrefix;
    }

    public String getNumber() {
        return number;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public static class PhoneNumberBuilder {
        private UUID id;
        private String countryPrefix;
        private String number;
        private UUID memberId;

        public static PhoneNumberBuilder phoneNumber() {
            return new PhoneNumberBuilder();
        }

        public PhoneNumber build() {
            return new PhoneNumber(this);
        }

        public PhoneNumberBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public PhoneNumberBuilder withCountryPrefix(String countryPrefix) {
            this.countryPrefix = countryPrefix;
            return this;
        }

        public PhoneNumberBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public PhoneNumberBuilder withMemberId(UUID memberId) {
            this.memberId = memberId;
            return this;
        }
    }
}
