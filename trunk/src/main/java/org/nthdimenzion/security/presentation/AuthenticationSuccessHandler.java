package org.nthdimenzion.security.presentation;

import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.infrastructure.Navigation;
import org.nthdimenzion.security.domain.UserDetailsDto;
import org.nthdimenzion.security.domain.UserLogin;
import org.nthdimenzion.security.infrastructure.repositories.hibernate.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private UserDetailsDto userDetailsDto;

    @Autowired
    private UserLoginRepository userLoginRepository;

    /*private List<PasswordPolicyErrorStrategy> passwordCheckStrategies;

     private UserLoginService userLoginService;
     private CommonCrudService commonCrudService;
     private PasswordPolicyService passwordPolicyService;

     public PasswordPolicyService getPasswordPolicyService() {
     return passwordPolicyService;
     }

     @Resource(name="passwordPolicyService")
     @Required
     public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
     this.passwordPolicyService = passwordPolicyService;
     }


     public void setUserLoginService(UserLoginService userLoginService) {
     this.userLoginService = userLoginService;
     }

     public void setPasswordCheckStrategies(List<PasswordPolicyErrorStrategy> passwordCheckStrategies) {
     this.passwordCheckStrategies = passwordCheckStrategies;
     }*/

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
        UserLogin userLogin = userLoginRepository.findUserLoginWithUserName(userDetailsDto.getUsername());
        logger.debug("Entry onAuthenticationSuccess " +  " getDefaultTargetUrl() " + getDefaultTargetUrl() + " isAlwaysUseDefaultTargetUrl() " + isAlwaysUseDefaultTargetUrl() + " navigation " + navigation
        + "userDetails " + userDetailsDto + " userLogin.getHomepageViewId()  " + userLogin.getHomepageViewId());
        String homepageViewId = userLogin.getHomepageViewId();
        if(UtilValidator.isNotEmpty(homepageViewId)){
            super.setTargetUrlParameter(navigation.findViewUrl(homepageViewId));
        }
        super.onAuthenticationSuccess(request, response, authentication);

	}



	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("Entry onAuthenticationSuccess");
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