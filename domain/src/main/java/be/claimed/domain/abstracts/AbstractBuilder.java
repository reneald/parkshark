package be.claimed.domain.abstracts;

import be.claimed.domain.abstracts.AbstractEntity;

public abstract class AbstractBuilder<D extends AbstractEntity> {
    public abstract class Builder<T> {

        public abstract T build();

    }
}
