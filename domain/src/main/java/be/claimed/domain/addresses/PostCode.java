package be.claimed.domain.addresses;

import be.claimed.domain.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table (name = "postal_codes")
public class PostCode extends AbstractEntity {

    @Column (name =  "postal_code")
    @Size(min = 4, max = 4, message = "the postcode has to be a 4 digit number")
    private String postCode;

    @Column (name = "label")
    @NotNull(message = "you must provide a region")
    private String label;

    public PostCode() {
        super();
    }

    public PostCode(PostCodeBuilder postCodeBuilder) {
        super(postCodeBuilder.postCodeId);
        postCode = postCodeBuilder.postCode;
        label = postCodeBuilder.label;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getLabel() {
        return label;
    }

    public static class PostCodeBuilder {
        private UUID postCodeId;
        private String postCode;
        private String label;

        public static PostCodeBuilder postCode() {
            return new PostCodeBuilder();
        }

        public PostCode build() {
            return new PostCode(this);
        }

        public PostCodeBuilder withPostCodeId(UUID postCodeId) {
            this.postCodeId = postCodeId;
            return this;
        }

        public PostCodeBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public PostCodeBuilder withLabel(String label) {
            this.label = label;
            return this;
        }
    }
}
