package org.nthdimenzion.security.domain;

import com.google.common.collect.Sets;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.PersonRole;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.security.domain.error.HomePageAlreadyExistsForUser;

import javax.persistence.*;
import java.util.Set;

@AggregateRoot
@Entity
public class UserLogin extends BaseAggregateRoot {

    private Credentials credentials;
    private UserLoginId userLoginId;
    private Set<SecurityGroup> securityGroups = Sets.newHashSet();
    private Boolean isEnabled = Boolean.TRUE;
    private String homepageViewId;
    private PersonRole personRole;

    protected UserLogin() {
    }

    public UserLogin(Credentials credentials, UserLoginId userLoginId) {
        this.credentials = credentials;
        this.userLoginId = userLoginId;
    }

    @ManyToMany
    Set<SecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    void setSecurityGroups(Set<SecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public UserLogin add(SecurityGroup securityGroup) {
        this.securityGroups.add(securityGroup);
        return this;
    }

    public UserLogin addAll(Set<SecurityGroup> securityGroups) {
        if (UtilValidator.isNotEmpty(securityGroups)) {
            for (SecurityGroup securityGroup : securityGroups) {
                add(securityGroup);
            }
        }
        return this;
    }

    public void assignHomePage(HomePageDetails homePageDetails) throws HomePageAlreadyExistsForUser {
        assignHomePage(homePageDetails, true);
    }


    public void assignHomePage(HomePageDetails homepageViewId, boolean override) throws HomePageAlreadyExistsForUser {
        if (UtilValidator.isNotEmpty(this.homepageViewId) && !override) {
            throw new HomePageAlreadyExistsForUser(new ErrorDetails.Builder("003").build());
        } else {
            this.homepageViewId = homepageViewId.getHomepageViewId();
        }
    }

    @Embedded
    public UserLoginId getUserLoginId() {
        return userLoginId;
    }

    void setUserLoginId(UserLoginId userLoginId) {
        this.userLoginId = userLoginId;
    }

    @Embedded
    Credentials getCredentials() {
        return credentials;
    }

    void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }


    Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getHomepageViewId() {
        return homepageViewId;
    }

    public void setHomepageViewId(String homepageViewId) {
        this.homepageViewId = homepageViewId;
    }

    @OneToOne
    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    @Override
    public String toString() {
        return credentials.toString();
    }
}

