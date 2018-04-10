package be.claimed.domain;

import be.claimed.domain.entities.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository<T extends AbstractEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }
}
