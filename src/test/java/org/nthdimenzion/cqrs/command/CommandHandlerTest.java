package org.nthdimenzion.cqrs.command;

import com.simplepersoncrud.application.commands.CreatePersonCommand;
import org.nthdimenzion.cqrs.command.testdata.InvalidCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/queryContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CommandHandlerTest {

    @Autowired
    private ICommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("simpleCommandBus")
    private ICommandBus commandBus;

    @Test
    public void testCommandHandlerFinder(){
        Assert.notNull(commandHandlerRegistry);
        String expectedCommandHandlerName = "CreatePersonCommandHandler";
        String actualCommandHandlerName = commandHandlerRegistry.findCommandHanlerFor(CreatePersonCommand.class).getClass().getSimpleName();
        Assert.isTrue(expectedCommandHandlerName.equalsIgnoreCase(actualCommandHandlerName));
    }

    @Test(expected = NoCommandHandlerFoundException.class)
    public void testCommandHandlerFinderForInvalidCommand(){
        Assert.notNull(commandHandlerRegistry);
        String actualCommandHandlerName = commandHandlerRegistry.findCommandHanlerFor(InvalidCommand.class).getClass().getSimpleName();
    }

}
