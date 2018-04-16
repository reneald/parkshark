package be.claimed.api.parkingLots;

import be.claimed.api.ControllerIntegrationTest;
import be.claimed.api.addresses.AddressDto;
import be.claimed.api.addresses.PostCodeDto;
import be.claimed.api.contactPersons.ContactPersonDto;
import be.claimed.api.divisions.DivisionDto;
import be.claimed.api.members.emails.EmailDto;
import be.claimed.api.members.phoneNumbers.PhoneNumberDto;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotControllerIntegrationTest extends ControllerIntegrationTest <ParkingLot, ParkingLotRepository>{

    @Inject
    private DivisionRepository divisionRepository;

    @BeforeEach
    @Override
    public void setUpDatabase() {
        clearDataBase(ParkingLot.class);
    }

    @AfterEach
    @Override
    public void breakDatabase() {
        clearDataBase(ParkingLot.class);
    }

    @Test
    void registerParkingLot(){
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.telephoneNumber = "465798965";
        List<PhoneNumberDto> phoneNumberDtos= new ArrayList<>();
        phoneNumberDtos.add(phoneNumberDto);

        EmailDto emailDto = new EmailDto();
        emailDto.email = "hello@gmail.com";

        ContactPersonDto contactPersonDto = new ContactPersonDto();
        contactPersonDto.firstName = "Michael";
        contactPersonDto.lastName = "Jordan";
        contactPersonDto.emailDto = emailDto;
        contactPersonDto.phoneNumbers = phoneNumberDtos;

       PostCodeDto postCodeDto = new PostCodeDto();
       postCodeDto.label = "Argentina";
       postCodeDto.postCode = "0071";

        AddressDto addressDto = new AddressDto();
        addressDto.streetNumber = "74";
        addressDto.streetName = "Nowhere";
        addressDto.postCodeDto =postCodeDto;

        DivisionDto divisionDto1 = DivisionDto.divisionDto()
                .withName("BigDivision")
                .withOriginalName("LittleDivision")
                .withDirector("BAAAAAAA");

        new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "divisions"), divisionDto1, DivisionDto.class);

        DivisionDto divisionDto = DivisionDto.divisionDto().withName("BigDivision");

        ParkingLotDto parkingLotDto = new ParkingLotDto();
        parkingLotDto.name = "wait";
        parkingLotDto.buildingTypeDto = "ABOVE_GROUND";
        parkingLotDto.capacity = 4545;
        parkingLotDto.contactPersonDto = contactPersonDto;
        parkingLotDto.addressDto = addressDto;
        parkingLotDto.pricePerHour = 4545.4;
        parkingLotDto.divisionDto = divisionDto;

        ParkingLotDto actualParkingLotDto = new TestRestTemplate().postForObject(String.format("http://localhost:%s/%s/", getPort(), "parkingLots"), parkingLotDto, ParkingLotDto.class);

        Assertions.assertThat(actualParkingLotDto.id).isNotNull();
        Assertions.assertThat(actualParkingLotDto.buildingTypeDto).isEqualTo("ABOVE_GROUND");
        Assertions.assertThat(actualParkingLotDto.contactPersonDto.firstName).isEqualTo("Michael");
        Assertions.assertThat(actualParkingLotDto.addressDto.streetNumber).isEqualTo("74");
    }
}
