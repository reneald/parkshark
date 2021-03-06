package be.claimed.domain.abstracts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public abstract class AbstractRepository<T extends AbstractEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<T> getAll(Class<T> abstractClass){
        String className = String.format("from %s", abstractClass.getSimpleName());
        return getEntityManager().createQuery(className, abstractClass)
                .getResultList();
    }

    public T findById(UUID id, Class<T> abstractClass) {
        return entityManager.find(abstractClass, id);
    }

}
