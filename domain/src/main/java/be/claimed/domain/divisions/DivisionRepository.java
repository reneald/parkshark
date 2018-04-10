package be.claimed.domain.divisions;

import be.claimed.domain.AbstractRepository;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DivisionRepository extends AbstractRepository<Division> {


}
