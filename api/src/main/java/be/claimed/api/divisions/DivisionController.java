package be.claimed.api.divisions;

import be.claimed.service.divisions.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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

    @PostMapping (consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus (HttpStatus.CREATED)
    public DivisionDto create (@RequestBody  DivisionDto divisionDto){
        return divisionMapper.toDto((divisionService.create(divisionMapper.toDomain(divisionDto))));
    }
}
