package be.claimed.api.addresses;

import be.claimed.api.AbstractMapper;
import be.claimed.domain.addresses.PostCode;

import javax.inject.Named;

@Named
public class PostCodeMapper extends AbstractMapper<PostCodeDto, PostCode> {
    @Override
    public PostCodeDto toDto(PostCode domainObject) {
        PostCodeDto dtoObject = new PostCodeDto();
        dtoObject.id = domainObject.getId();
        dtoObject.postCode = domainObject.getPostCode();
        dtoObject.label = domainObject.getLabel();
        return dtoObject;
    }

    @Override
    public PostCode toDomain(PostCodeDto dtoObject) {
        PostCode domainObject = PostCode.PostCodeBuilder.postCode()
                .withPostCode(dtoObject.postCode)
                .withLabel(dtoObject.label)
                .build();
        return domainObject;
    }
}
