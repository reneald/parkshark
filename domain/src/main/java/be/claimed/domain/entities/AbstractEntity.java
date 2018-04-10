package be.claimed.domain.entities;

import javax.persistence.Id;
import java.util.UUID;

public abstract class AbstractEntity {

    @Id
    private UUID id;

    public AbstractEntity(UUID id) {
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
