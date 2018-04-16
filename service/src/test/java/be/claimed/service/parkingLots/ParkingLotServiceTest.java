package be.claimed.service.parkingLots;

import be.claimed.MockitoExtension;
import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;

    @Mock
    private ParkingLotRepository parkingLotRepository;
    private ParkingLot firstParkingLot;

    @BeforeEach
    void setUp(){
        when(parkingLotRepository.create(firstParkingLot)).thenReturn(firstParkingLot);
        parkingLotService = new ParkingLotService(parkingLotRepository);
    }
    @Test
    void create_ShouldCallRepositoryMethod() {
        parkingLotService.createParkingLot(firstParkingLot);
        verify(parkingLotRepository, times(1)).create(firstParkingLot);
    }
}