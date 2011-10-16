package com.simplepersoncrud.presentation;

import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonTestServlet extends javax.servlet.http.HttpServlet {

static final Logger logger = LoggerFactory.getLogger(PersonTestServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException    {
        super.doPost(req, resp);
        logger.debug("***** DO POST ******");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        logger.debug("**** DO GET *****");
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IPersonService personService =(IPersonService)springContext.getBean("personService");
        Person person = new Person();
        person.setName("Sudarshan12");
        Long personId = personService.createPerson(person);
        logger.debug("person.getId--> " + personService.getPersonWithId(personId).getVersion());
    }
}
