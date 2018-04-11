package be.claimed.api;

import be.claimed.service.divisions.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping (path = "/divisions")
public class DivisionController {
    private DivisionService divisionService;
    private DivisionMapper divisionMapper;

    @Inject
    public DivisionController(DivisionService divisionService, DivisionMapper divisionMapper) {
        this.divisionService = divisionService;
        this.divisionMapper = divisionMapper;
    }

    @PostMapping (consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus (HttpStatus.CREATED)
    public DivisionDto create (DivisionDto divisionDto){
        return divisionMapper.toDto((divisionService.create(divisionMapper.toDomain(divisionDto))));
    }
}
