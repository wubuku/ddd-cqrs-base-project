package org.nthdimenzion.testinfrastructure;

import com.simplepersoncrud.testdata.DummyDisplayMessages;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.presentation.exception.PresentationDecoratedExceptionHandler;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.testdata.SecurityDetailsMaker;
import org.nthdimenzion.testdata.TestUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testContext.xml","classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Ignore
public class AbstractTestFacilitator extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier("simpleCommandBus")
    protected ICommandBus commandBus;

    @Autowired
    @Qualifier("presentationDecoratedExceptionHandler")
    protected PresentationDecoratedExceptionHandler presentationDecoratedExceptionHandler;

     @Autowired
    protected ICrud crudDao;

    @Autowired
    protected SystemUser systemUser;

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    protected IDisplayMessages displayMessages = new DummyDisplayMessages();

    @Before
    public void setUpForEachTest(){
    UserDetails userDetails = new TestUserDetails();
    systemUser.uses(userDetails);
    presentationDecoratedExceptionHandler.setDisplayMessages(displayMessages);
    TestingAuthenticationToken token = SecurityDetailsMaker.makeTestingAuthenticationToken("ROLE_SUPERADMIN");
    SecurityContextHolder.getContext().setAuthentication(token);
    }

}
