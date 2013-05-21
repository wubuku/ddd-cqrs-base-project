package org.nthdimenzion.ddd.domain.service;

import com.google.common.base.Preconditions;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.domain.PersonRole;
import org.nthdimenzion.ddd.infrastructure.LoggedInUserHolder;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 10/4/13
 * Time: 11:19 PM
 */
@Service
public class RoleService {

    @Autowired
    private ICrud crudDao;

    private SystemUser systemUser;

    @Autowired
    private IUserLoginRepository userLoginRepository;

    /***
     * Preconditions loggedInUser must have a valid @DomainRole
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getRolePlayedByLoggedInUser(Class<T> clazz){
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(LoggedInUserHolder.getUserName());
        PersonRole personRole = userLogin.getPersonRole();
        Preconditions.checkNotNull(personRole);
        return (T)crudDao.find(clazz,personRole.getId());
    }

}
