package pl.iwona.securityencoderweek4.model;

import javax.persistence.*;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne
    private ApiUser apiUser;

    public VerificationToken(ApiUser apiUser, String value) {
        this.apiUser = apiUser;
        this.value = value;
    }
    public VerificationToken() {
    }

    public ApiUser getApiUser() {
        return apiUser;
    }

    public void setApiUser(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
