package be.claimed.service.divisions;

import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DivisionService {
    private DivisionRepository divisionRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division create (Division division){
        return divisionRepository.create(division);
    }

}
