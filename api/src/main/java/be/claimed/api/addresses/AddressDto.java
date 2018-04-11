package be.claimed.api.addresses;

import java.util.UUID;

public class AddressDto {
    public UUID id;
    public String streetName;
    public String streetNumber;
    public PostCodeDto postCodeDto;

    public AddressDto() {
    }
}
