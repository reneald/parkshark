package be.claimed.domain.parkinglots;

import be.claimed.configuration.Config;
import be.claimed.domain.addresses.Address;
import be.claimed.domain.addresses.PostCode;
import be.claimed.domain.contactPersons.ContactPerson;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import be.claimed.domain.members.emails.Email;
import be.claimed.domain.members.phoneNumbers.PhoneNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(Config.class)
@Transactional
class ParkingLotRepositoryTest {

    @Autowired
    public ParkingLotRepositoryTest(ParkingLotRepository parkingLotRepository, DivisionRepository divisionRepository){
        this.parkingLotRepository = parkingLotRepository;
        this.divisionRepository = divisionRepository;
    }

    private ParkingLotRepository parkingLotRepository;
    private DivisionRepository divisionRepository;

    @Test
    void create_whenGivenAParkingLot_shouldAddItToTheDataBase(){
        //GIVEN
        PhoneNumber phoneNumber2 = PhoneNumber.PhoneNumberBuilder.phoneNumber().withTelephoneNumber("465798965").build();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber2);
        Email email = Email.EmailBuilder.email().withEmail("mqlksjdfoisqu@gmail.com").build();
        Address address = Address.AddressBuilder.address()
                .withStreetName("Somewhere Street")
                .withStreetNumber("42")
                .withPostCode(PostCode.PostCodeBuilder.postCode().withPostCode("2850").withLabel("Boom").build())
                .build();
        ContactPerson contactPerson = ContactPerson.ContactPersonBuilder.contactPerson()
                .withFirstName("Bob")
                .withLastName("TheBuilder")
                .withPhoneNumbers(phoneNumbers)
                .withEmail(email)
                .build();
        Division division = Division.DivisionBuilder.division().build();
        divisionRepository.create(division);
        ParkingLot parking = ParkingLot.ParkingLotBuilder.parkingLot()
                .withName("parking")
                .withDivision(division)
                .withBuildingType(BuildingType.ABOVE_GROUND)
                .withCapacity(12256)
                .withPricePerHour(new BigDecimal(12546))
                .withContactPerson(contactPerson)
                .withAddress(address)
                .build();

        ParkingLot actual = parkingLotRepository.create(parking);

        Assertions.assertThat(actual.getId()).isNotNull();
    }

}