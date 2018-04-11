package be.claimed.api;

public abstract class AbstractMapper<DTO, DOMAIN> {

    public abstract DTO toDto(DOMAIN domainObject);

    public abstract DOMAIN toDomain(DTO dtoObject);

}
