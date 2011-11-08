package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.application.annotation.StateFullComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@StateFullComponent
public class UserDetailsDto implements UserDetails{
    private UserDetails userDetails;

    UserDetailsDto() {
    }

    public UserDetailsDto(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public  UserDetailsDto uses(UserDetails userDetails){
        setUserDetails(userDetails);
        return this;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    @Override
    public String toString() {
        return userDetails.toString();
    }
}
