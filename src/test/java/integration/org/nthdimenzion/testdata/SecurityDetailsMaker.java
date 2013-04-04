package org.nthdimenzion.testdata;

import org.springframework.security.authentication.TestingAuthenticationToken;

public class SecurityDetailsMaker {

    public static TestingAuthenticationToken makeTestingAuthenticationToken(String ...grantedAuthorities) {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user", "pass", grantedAuthorities);
        token.setAuthenticated(true);
        return token;
    }

}

