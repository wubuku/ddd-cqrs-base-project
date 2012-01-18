package org.nthdimenzion.security;


import org.nthdimenzion.testdata.SecurityDetailsMaker;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class SecurityTest extends AbstractTransactionalJUnit4SpringContextTests {

     @BeforeClass
    public static void onTimeSetUp(){
    TestingAuthenticationToken token = SecurityDetailsMaker.makeTestingAuthenticationToken(new GrantedAuthorityImpl[]{new GrantedAuthorityImpl("ROLE_SUPERADMIN")});
    SecurityContextHolder.getContext().setAuthentication(token);
     }

    @AfterClass
    public static void onTimeTearDown(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserDetailsFromUserName(){
        // Setup from import.sql
        userService.setSystemUser(new SystemUser(null));

        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
    }

    @Test
    public void testLoadSecurityPermissionsForUserName(){
        // Setup from import.sql
        userService.setSystemUser(new SystemUser(null));

        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
        Assert.isTrue(Integer.valueOf(1).equals(userDetails.getAuthorities().size()));
    }

}
