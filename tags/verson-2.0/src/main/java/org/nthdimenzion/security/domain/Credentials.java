package org.nthdimenzion.security.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;

@ValueObject
@Embeddable
public class Credentials {

    private String username;
    private String password;

    Credentials(){
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return new String(username.intern());
    }

    void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return new String(password);
    }

    void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            Credentials obj = (Credentials) o;
            return new EqualsBuilder().reflectionEquals(obj,this);
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{username,password});
    }

    @Override
    public String toString() {
        return username;
    }
}

