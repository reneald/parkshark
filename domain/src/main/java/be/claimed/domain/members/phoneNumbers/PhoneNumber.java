package be.claimed.domain.members.phoneNumbers;

import be.claimed.domain.abstracts.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table (name = "phone_numbers")
public class PhoneNumber extends AbstractEntity {

    @Column (name = "country_prefix")
    @NotNull(message = "country prefix must be filled in")
    private String countryPrefix;

    @Column (name = "phone_number")
    @NotNull(message = "country prefix must be filled in")
    private String number;


    public PhoneNumber(UUID id) {
        super(id);
    }

    public PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        super(phoneNumberBuilder.id);
        this.countryPrefix = phoneNumberBuilder.countryPrefix;
        this.number = phoneNumberBuilder.number;
    }

    public String getCountryPrefix() {
        return countryPrefix;
    }

    public String getNumber() {
        return number;
    }


    public static class PhoneNumberBuilder {
        private UUID id;
        private String countryPrefix;
        private String number;

        public static PhoneNumberBuilder phoneNumber() {
            return new PhoneNumberBuilder();
        }

        public PhoneNumber build() {
            return new PhoneNumber(this);
        }

        public PhoneNumberBuilder withCountryPrefix(String countryPrefix) {
            this.countryPrefix = countryPrefix;
            return this;
        }

        public PhoneNumberBuilder withNumber(String number) {
            this.number = number;
            return this;
        }
    }
}
