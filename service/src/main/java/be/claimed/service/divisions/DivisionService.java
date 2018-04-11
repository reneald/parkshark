package be.claimed.service.divisions;

import be.claimed.domain.divisions.Division;
import be.claimed.domain.divisions.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DivisionService {
    private DivisionRepository divisionRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division create (Division division){
        if (divisionRepository.getAll().stream().map(division1 -> division1.getOriginalName()).collect(Collectors.toList()).contains(division.getOriginalName())) {
            throw new DataIntegrityViolationException("This division already exist");
        }
        return divisionRepository.create(division);
    }

}
