package org.nthdimenzion.security;


import org.nthdimenzion.testdata.SecurityDetailsMaker;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.testinfrastructure.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SecurityTest extends AbstractTest {

    @AfterClass
    public static void onTimeTearDown(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserDetailsFromUserName(){
        // Setup from import.sql
        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
    }

    @Test
    public void testLoadSecurityPermissionsForUserName(){
        // Setup from import.sql
        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
        Assert.isTrue(Integer.valueOf(1).equals(userDetails.getAuthorities().size()));
    }

}
