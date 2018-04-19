package be.claimed.api.allocations;

import be.claimed.service.allocations.AllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {
    private AllocationService allocationService;
    private AllocationMapper allocationMapper;

    @Inject
    public AllocationController(AllocationService allocationService, AllocationMapper allocationMapper) {
        this.allocationService = allocationService;
        this.allocationMapper = allocationMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AllocationDto create(AllocationDto allocationDto) {
        return allocationMapper.toDto(allocationService.create(allocationMapper.toDomain(allocationDto)));
    }
}
