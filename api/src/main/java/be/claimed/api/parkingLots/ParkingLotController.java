package be.claimed.api.parkingLots;

import be.claimed.service.parkingLots.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/parkingLots")
public class ParkingLotController {
    private ParkingLotMapper parkingLotMapper;
    private ParkingLotService parkingLotService;

    @Inject
    public ParkingLotController(ParkingLotMapper parkingLotMapper, ParkingLotService parkingLotService) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDto registerParkingLot(@RequestBody ParkingLotDto parkingLotToRegister, String divisionName){
        return parkingLotMapper.toDto(parkingLotService.registerParkingLot(parkingLotMapper.toDomain(parkingLotToRegister)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingLotDto> getAllParkingLots(){
        return parkingLotService.getAllParkingLots().stream()
                .map(parkingLot -> parkingLotMapper.toDto(parkingLot))
                .collect(Collectors.toList());
    }
}
