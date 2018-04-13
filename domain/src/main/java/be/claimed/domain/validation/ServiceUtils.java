package be.claimed.domain.validation;

import be.claimed.domain.divisions.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceUtils {
    private static ServiceUtils instance;

    @Autowired
    private DivisionRepository divisionRepository;

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    public static DivisionRepository getDivisionRepository() {
        return instance.divisionRepository;
    }
}
