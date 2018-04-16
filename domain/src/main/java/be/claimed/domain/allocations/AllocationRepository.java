package be.claimed.domain.allocations;

import be.claimed.domain.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AllocationRepository extends AbstractRepository<Allocation> {
}
