package org.nthdimenzion.testinfrastructure.testdata;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TestUserDetails implements UserDetails {

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (Collection)Lists.newArrayList(new GrantedAuthorityImpl("ROLE_SUPERADMIN"));
    }

    @Override
    public String getPassword() {
        return "sa";
    }

    @Override
    public String getUsername() {
        return "sa";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabled() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
