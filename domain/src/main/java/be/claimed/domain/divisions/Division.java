package be.claimed.domain.divisions;

import be.claimed.domain.entities.AbstractEntity;

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

    public Division(UUID id, String name, String originalName, String director, Integer parentDivision) {
        super(id);
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivision = parentDivision;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
