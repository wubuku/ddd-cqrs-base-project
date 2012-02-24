package org.nthdimenzion.testdata;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class SecurityDetailsMaker {

    public static TestingAuthenticationToken makeTestingAuthenticationToken(GrantedAuthorityImpl[] grantedAuthorities) {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user", "pass", grantedAuthorities);
        token.setAuthenticated(true);
        return token;
    }

}

