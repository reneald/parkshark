package be.claimed.domain.members.phoneNumbers;

import be.claimed.domain.abstracts.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Embeddable
public class PhoneNumber{

    @Column (name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column (name = "telephone_number")
    private String telephoneNumber;


    private PhoneNumber() {
    }


    public PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        this.mobilePhoneNumber = phoneNumberBuilder.mobilePhoneNumber;
        this.telephoneNumber = phoneNumberBuilder.telephoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }


    public static class PhoneNumberBuilder {
        private String mobilePhoneNumber;
        private String telephoneNumber;

        public static PhoneNumberBuilder phoneNumber() {
            return new PhoneNumberBuilder();
        }

        public PhoneNumber build() {
            return new PhoneNumber(this);
        }

        public PhoneNumberBuilder withMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public PhoneNumberBuilder withTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }
    }
}
