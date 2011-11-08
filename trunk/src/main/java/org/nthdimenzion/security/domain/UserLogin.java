package org.nthdimenzion.security.domain;

import com.google.common.collect.Sets;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.object.utils.UtilValidator;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@AggregateRoot
@Entity
public class UserLogin extends BaseAggregateRoot{

    private Credentials credentials;
    private UserLoginId userLoginId;
    private Set<SecurityGroup> securityGroups = Sets.newHashSet();
    private Boolean isEnabled = Boolean.TRUE;

    protected UserLogin() {
    }

    public UserLogin(Credentials credentials,UserLoginId userLoginId) {
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

    public void add(SecurityGroup securityGroup){
        this.securityGroups.add(securityGroup);
    }

    public void addAll(Set<SecurityGroup> securityGroups){
        if(UtilValidator.isNotEmpty(securityGroups)){
            for(SecurityGroup securityGroup : securityGroups){
                add(securityGroup);
            }
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

    @Override
    public String toString() {
        return credentials.getUsername();
    }
}

