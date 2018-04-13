package be.claimed.domain.parkinglots;

import be.claimed.domain.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ParkingLotRepository extends AbstractRepository<ParkingLot> {

}
