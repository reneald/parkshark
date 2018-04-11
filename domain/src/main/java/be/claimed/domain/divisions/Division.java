package be.claimed.domain.divisions;

import be.claimed.domain.AbstractBuilder;
import be.claimed.domain.entities.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "DIVISIONS",
    uniqueConstraints = {@UniqueConstraint(name = "unique_name_orig",columnNames = {"NAME_ORIG"})})
public class Division extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "name_orig")
    private String originalName;
    @Column(name = "director")
    private String director;
    @Column(name = "fk_parent_div_id")
    private UUID parentDivision;

    public Division() {
        super();
    }

    public Division(DivisionBuilder divisionBuilder) {
        super(divisionBuilder.id);
        name = divisionBuilder.name;
        originalName = divisionBuilder.originalName;
        director = divisionBuilder.director;
        parentDivision = divisionBuilder.parentDivision;

    }

    public Division(UUID id, String name, String originalName, String director, UUID parentDivision) {
        super(id);
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivision = parentDivision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public UUID getParentDivision() {
        return parentDivision;
    }

    public void setParentDivision(UUID parentDivision) {
        this.parentDivision = parentDivision;
    }

    public static class DivisionBuilder extends AbstractBuilder<Division> {
        private UUID id;
        private String name;
        private String originalName;
        private String director;
        private UUID parentDivision;

        public static DivisionBuilder division() {
            return new DivisionBuilder();
        }


        public Division build() {
            return new Division(this);

        }

        public DivisionBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public DivisionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DivisionBuilder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public DivisionBuilder withDirector(String director) {
            this.director = director;
            return this;
        }

        public DivisionBuilder withParentDivision(UUID parentDivision) {
            this.parentDivision = parentDivision;
            return this;
        }


    }

}
