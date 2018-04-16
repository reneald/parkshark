package be.claimed.service.parkingLots;

import be.claimed.domain.parkinglots.ParkingLot;
import be.claimed.domain.parkinglots.ParkingLotRepository;
import be.claimed.service.divisions.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;
    private DivisionService divisionService;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, DivisionService divisionService){
        this.parkingLotRepository = parkingLotRepository;
        this.divisionService = divisionService;
    }

    public ParkingLot registerParkingLot(ParkingLot parkingLot){
        setDivision(parkingLot);
        return parkingLotRepository.create(parkingLot);
    }

    public List<ParkingLot> getAllParkingLots(){
        return parkingLotRepository.getAll(ParkingLot.class);
    }

    private void setDivision(ParkingLot parkingLot) {
        if (divisionService.findByName(parkingLot.getDivision().getName()) == null){
            throw new IllegalArgumentException("The division provided for parking lot doesn't exist!");
        }

        parkingLot.setDivision(divisionService.findByName(parkingLot.getDivision().getName()));
    }
}
