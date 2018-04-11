package be.claimed.api.addresses;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.addresses.Address;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddressMapper extends AbstractMapper<AddressDto, Address> {
    private PostCodeMapper postCodeMapper;

    @Inject
    public AddressMapper(PostCodeMapper postCodeMapper) {
        this.postCodeMapper = postCodeMapper;
    }

    @Override
    public AddressDto toDto(Address domainObject) {
        AddressDto dtoObject = new AddressDto();
        dtoObject.id = domainObject.getId();
        dtoObject.streetName = domainObject.getStreetName();
        dtoObject.streetNumber = domainObject.getStreetNumber();
        dtoObject.postCodeDto = postCodeMapper.toDto(domainObject.getPostCode());
        return dtoObject;
    }

    @Override
    public Address toDomain(AddressDto dtoObject) {
        Address domainObject = Address.AddressBuilder.address()
                .withStreetName(dtoObject.streetName)
                .withStreetNumber(dtoObject.streetNumber)
                .withPostCode(postCodeMapper.toDomain(dtoObject.postCodeDto))
                .build();
        return domainObject;
    }
}
