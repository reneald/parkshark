package be.claimed.domain.divisions;

import be.claimed.domain.abstracts.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DivisionRepository extends AbstractRepository<Division> {

    public Division findByName (String name){
        return  super.getEntityManager().createQuery("from Division where name = :name", Division.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
