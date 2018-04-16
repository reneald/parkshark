package be.claimed.service.parkingLots;

import be.claimed.MockitoExtension;
import be.claimed.domain.divisions.Division;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import be.claimed.service.divisions.DivisionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @Mock
    private ParkingLot firstParkingLot;

    @Mock
    private Division divisionMock;

    @Mock
    private DivisionService divisionService;

    @BeforeEach
    void setUp(){
        when (firstParkingLot.getDivision()).thenReturn(divisionMock);
        when (divisionMock.getName()).thenReturn("BOOOOOO");
        when(parkingLotRepository.create(firstParkingLot)).thenReturn(firstParkingLot);
        when (divisionService.findByName("BOOOOOO")).thenReturn(divisionMock);
        parkingLotService = new ParkingLotService(parkingLotRepository, divisionService);
    }
    @Test
    void create_ShouldCallRepositoryMethod() {
        parkingLotService.registerParkingLot(firstParkingLot);
        verify(parkingLotRepository, times(1)).create(firstParkingLot);
    }

    @Test
    void getAllParkingLots_shouldCallRepositoryMethod(){
        parkingLotService.getAllParkingLots();
        verify(parkingLotRepository).getAll(ParkingLot.class);
    }
}