package be.claimed.api;

public class DivisionDto {
    private String id;
    private String name;
    private String originalName;
    private String director;
    private String parentDivision;

    public static DivisionDto divisionDto() {
        return new DivisionDto();
    }

    public DivisionDto withId(String id) {
        this.id = id;
        return this;
    }

    public DivisionDto withName(String name) {
        this.name = name;
        return this;
    }

    public DivisionDto withOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public DivisionDto withDirector(String director) {
        this.director = director;
        return this;
    }

    public DivisionDto withParentDivision(String parentDivision) {
        this.parentDivision = parentDivision;
        return this;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirector() {
        return director;
    }

    public String getParentDivision() {
        return parentDivision;
    }

}


