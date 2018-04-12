package be.claimed.domain.parkinglots;

import be.claimed.domain.abstracts.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parking_lots")
public class ParkingLot extends AbstractEntity {
}
