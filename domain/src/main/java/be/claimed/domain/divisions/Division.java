package be.claimed.domain.divisions;

import be.claimed.domain.AbstractBuilder;
import be.claimed.domain.entities.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "DIVISIONS")
public class Division extends AbstractEntity {
    //    @Id
//    @SequenceGenerator(name = "division_seq", sequenceName = "DIVISION_SEQUENCE", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
//    @Column(name = "id")
//    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "name_orig")
    private String originalName;
    @Column(name = "director")
    private String director;
    @Column(name = "fk_parent_div_id")
    private Integer parentDivision;

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

    public Division(UUID id, String name, String originalName, String director, Integer parentDivision) {
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

    public Integer getParentDivision() {
        return parentDivision;
    }

    public void setParentDivision(Integer parentDivision) {
        this.parentDivision = parentDivision;
    }

    public static class DivisionBuilder extends AbstractBuilder<Division> {
        private UUID id;
        private String name;
        private String originalName;
        private String director;
        private Integer parentDivision;

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

        public DivisionBuilder withOriginalName(String OriginalName) {
            this.originalName = originalName;
            return this;
        }

        public DivisionBuilder withDirector(String director) {
            this.director = director;
            return this;
        }

        public DivisionBuilder parentDivision(Integer parentDivision) {
            this.parentDivision = parentDivision;
            return this;
        }


    }

}
