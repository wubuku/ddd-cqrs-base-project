package org.nthdimenzion.security.presentation;

import org.nthdimenzion.ddd.infrastructure.LoggedInUserHolder;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.infrastructure.Navigation;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.domain.UserLogin;
import org.nthdimenzion.security.infrastructure.repositories.hibernate.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements LogoutSuccessHandler{

     private final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Autowired
    private Navigation navigation;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserService userService;


    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
        experiment();
        LoggedInUserHolder.clearLoggedInUser();
        final String username = request.getParameter("j_username");
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(username);
        UserDetails userDetails = userService.loadUserByUsername(username );
        SystemUser systemUser = new SystemUser(userDetails);
        logger.debug("Entry onAuthenticationSuccess " +  " getDefaultTargetUrl() " + getDefaultTargetUrl() + " isAlwaysUseDefaultTargetUrl() " + isAlwaysUseDefaultTargetUrl() + " navigation " + navigation
        + "userDetails " + systemUser + " userLogin.getHomepageViewId()  " + userLogin.getHomepageViewId());
        request.getSession().setAttribute("loggedInUser", systemUser);

        LoggedInUserHolder.setUserName(username);

        String homepageViewId = userLogin.getHomepageViewId();
        if(UtilValidator.isNotEmpty(homepageViewId)){
            String viewUrl = navigation.findViewUrl(homepageViewId);
            super.setTargetUrlParameter(viewUrl);
            super.setDefaultTargetUrl(viewUrl);
        }
        super.onAuthenticationSuccess(request, response, authentication);

	}

    private void experiment() {
        //To change body of created methods use File | Settings | File Templates.
    }


    @Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("Entry onLogoutSuccess");
	/*if(authentication != null){
		UserLogin login = ((UserLoginUserDetailsAdapter) authentication.getPrincipal()).getUserLogin();
		EventLog log = new EventLog(EventType.APPLICATION_LOGOUT, "User - "+ login.getUsername() +" logged out.");
		log.setPractice(login.getPractice());
		log.setCreatedBy(login.getUsername());
		ApplicationEvents.postEvent(log);
	} */
        navigation.redirect("login");
	}


}