package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.application.annotation.StateFullComponent;
import org.nthdimenzion.ddd.domain.INamed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@StateFullComponent
public class SystemUser implements UserDetails,INamed{
    private UserDetails userDetails;

    public SystemUser() {
    }

    public SystemUser(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public SystemUser uses(UserDetails userDetails){
        setUserDetails(userDetails);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
    public String getName() {
        return getUsername();
    }

}
