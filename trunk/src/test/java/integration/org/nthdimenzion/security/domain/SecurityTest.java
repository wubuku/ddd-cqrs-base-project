package org.nthdimenzion.security.domain;


import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.Test;
import org.modelmapper.internal.util.Assert;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.infrastructure.repositories.hibernate.UserLoginRepository;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


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
    public void testLoadSecurityPermissionsForUserName(){
        // Setup from import.sql
        UserDetails userDetails = userService.loadUserByUsername("sa");

        Assert.notNull(userDetails);
        Assert.isTrue("sa".equals(userDetails.getUsername()));
        Assert.isTrue(Integer.valueOf(1).equals(userDetails.getAuthorities().size()));
    }

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Test
    public void createUserLogin(){
        SecurityPermission securityPermission = new SecurityPermission("ROLE_TEST","Role for test");
        crudDao.add(securityPermission);
        SecurityGroup securityGroup = new SecurityGroup(new SecurityGroupId("1")).add(securityPermission);
        crudDao.add(securityGroup );
        UserLogin userLogin = new UserLogin(new Credentials("test","test"),new UserLoginId("2")).add(securityGroup);
        Long id = crudDao.add(userLogin);
        hibernateDaoOperations.flush();
        Assert.notNull(id);
        UserLogin resultUserLogin = userLoginRepository.findUserLoginWithUserName("test");
        securityGroup = (SecurityGroup) resultUserLogin.getSecurityGroups().toArray()[0];
        SecurityPermission resultSecurityPermission = (SecurityPermission) securityGroup.getSecurityPermissions()
                .toArray()[0];
        Assert.notNull(resultSecurityPermission.getId());
    }

}
