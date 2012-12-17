package org.nthdimenzion.testdata;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class TestUserDetails implements UserDetails {

    Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

    public TestUserDetails() {
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void grantAuthority(String authority) {
        grantAuthorities(Lists.newArrayList(authority));
    }

    public void grantAuthorities(Collection<String> authorities) {
        for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
    }

    public void revokeAuthority(String authority){
        revokeAuthorities(Lists.newArrayList(authority));
    }

    public void revokeAuthorities(Collection<String> authorities){
         for (String authority : authorities) {
            grantedAuthorities.remove(new SimpleGrantedAuthority(authority));
        }
    }
}
