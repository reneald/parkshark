package be.claimed.domain;

import be.claimed.domain.entities.AbstractEntity;

public abstract class AbstractBuilder<D extends AbstractEntity> {
    public abstract class Builder<T> {

        public abstract T build();

    }
}
