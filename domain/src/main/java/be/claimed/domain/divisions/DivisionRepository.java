package be.claimed.domain.divisions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DivisionRepository {

    private EntityManager entityManager;
    @Autowired
    public DivisionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Division create(Division division) {
        entityManager.persist(division);
        return division;
    }
}
