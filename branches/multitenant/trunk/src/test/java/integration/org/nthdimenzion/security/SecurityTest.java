package org.nthdimenzion.security;


import org.junit.AfterClass;
import org.junit.Test;
import org.modelmapper.internal.util.Assert;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;

public class SecurityTest extends AbstractTestFacilitator {

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
    @Rollback(value = false)
    public void testLoadSecurityPermissionsForUserName(){
        // Setup from import.sql
        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
        Assert.isTrue(Integer.valueOf(1).equals(userDetails.getAuthorities().size()));
    }

}
