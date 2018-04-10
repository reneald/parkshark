package be.claimed.domain.entities;

import java.util.UUID;

public abstract class Entity {

    private UUID id;

    public Entity(UUID id) {
        this.id = id;
    }

    public void generateId() throws IllegalStateException{
        if (id != null){
            throw new IllegalStateException("Id already present");
        }
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
