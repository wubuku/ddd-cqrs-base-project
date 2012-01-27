package com.simplepersoncrud.presentation.composer;

import com.google.common.collect.Sets;
import com.simplepersoncrud.application.commands.PersonNameChangeCommand;
import com.simplepersoncrud.application.commands.PersonRegistrationCommand;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.Set;

@Composer
public class PersonRegistrationComposer extends AbstractZkComposer {

    private final Logger logger = LoggerFactory.getLogger(PersonRegistrationComposer.class);
    private PersonDetailsDto personDetailsDto = new PersonDetailsDto();

    @Autowired
    private IPersonFinder personFinder;

    private String personRegistrationBtnLbl = "Register Person";
    private boolean isUpdateView = false;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initializePage();
        Long personId = (Long) Executions.getCurrent().getArg().get("personId");
        if (personId != null) {
            personDetailsDto = personFinder.findPersonDetails(personId);
            personRegistrationBtnLbl = "Update Registration Details";
            isUpdateView = true;
        }
        comp.setAttribute("dto", personDetailsDto);
    }

    private void initializePage() {
        personRegistrationBtnLbl = "Register Person";
        isUpdateView = false;
    }

    public boolean isUpdateView() {
        return isUpdateView;
    }

    public void registerPerson() {
        if (isUpdateView) {
            updateRegistrationDetails(personDetailsDto);
            return;
        }
        logger.debug("Entry into registerPerson " + personDetailsDto);
        PersonRegistrationCommand personRegistrationCommand = populate(personDetailsDto, PersonRegistrationCommand.class);
        logger.debug(personRegistrationCommand.getName());
        Long id = (Long) sendCommand(personRegistrationCommand);
        if (isSuccess(id)) {
            displayMessages.showSuccess("Person has been registered successfully with id " + id);
            navigation.redirect("personPanel");
        }
    }

    private void updateRegistrationDetails(PersonDetailsDto personDetailsDto) {
        PersonNameChangeCommand personNameChangeCommand = populate(personDetailsDto, PersonNameChangeCommand.class);
        logger.debug(" personNameChangeCommand.getId() " + personNameChangeCommand.getId());
        if (isSuccess(sendCommand(personNameChangeCommand))) {
            navigation.redirect("personPanel");
        }
    }

    public void unRegisterPerson(Long personId) {
        logger.debug("Entry into deregisterPerson " + personId);
        Set<Long> personTpBeDeleted = Sets.newHashSet(personId);
        UnRegisterCommand deletePersonCommand = new UnRegisterCommand(personTpBeDeleted);
        if (isSuccess(sendCommand(deletePersonCommand)))
            navigation.redirect("personPanel");
    }

    public String getPersonRegistrationBtnLbl() {
        return personRegistrationBtnLbl;
    }
}
