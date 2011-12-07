package com.simplepersoncrud.presentation.composer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PeopleSummaryDto;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.nthdimenzion.presentation.infrastructure.Navigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericComposer;

import java.util.List;
import java.util.Set;

@Composer
public class PersonComposer extends AbstractZkComposer {

    private final Logger logger = LoggerFactory.getLogger(PersonComposer.class);

    @Autowired
    private IPersonFinder iPersonFinder;

    private List<PersonDetailsDto> peopleDetails = Lists.newArrayList();

    private PersonDetailsDto selectedPerson;

    public void setSelectedPerson(PersonDetailsDto selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        peopleDetails = getPeopleDetails();
        logger.debug("Entry into doAfterCompose " + peopleDetails);
    }

    public void selectPerson(Long personId) {
        logger.debug("Entry into selectPerson " + personId);
        navigation.navigateToDefaultContainer("registerPerson", ImmutableMap.of("personId", personId));
    }

    public void unregisterPeople(List<PersonDetailsDto> peopleDetailsDto) {
        logger.debug(peopleDetailsDto.toString());
        Set<Long> unRegisteredPeople = Sets.newHashSet();
        if (UtilValidator.isNotEmpty(peopleDetailsDto)) {
            for (PersonDetailsDto personDetailsDto : peopleDetailsDto) {
                unRegisteredPeople.add(personDetailsDto.getId());
            }
            UnRegisterCommand unRegisterCommand = new UnRegisterCommand(unRegisteredPeople);
            sendCommand(unRegisterCommand);
            displayMessages.displaySuccess();
        }
    }

    public List<PersonDetailsDto> getPeopleDetails() {
        return iPersonFinder.findAllPeople();
    }

    public boolean isRecordsNoFound() {
        return getPeopleSummary().totalPeopleCount <= 0;
    }

    public PeopleSummaryDto getPeopleSummary() {
        return iPersonFinder.getPeopleSummary();
    }

    public void navigateToUnRegisterPerson() {
        if (isRecordsNoFound()) {
            displayMessages.displaySuccess("No Registered People to Un Register");
            return;
        }
        navigation.navigateToDefaultContainer("UnRegisterPeople", null);
    }
}
