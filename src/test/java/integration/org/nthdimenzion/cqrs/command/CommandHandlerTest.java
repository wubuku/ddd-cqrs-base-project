package org.nthdimenzion.cqrs.command;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.Handler;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.cqrs.command.IMultiCommandHandlerRegistry;
import org.nthdimenzion.cqrs.command.NoCommandHandlerFoundException;
import org.nthdimenzion.cqrs.command.testdata.InvalidCommand;
import org.nthdimenzion.cqrs.command.testdata.TestCommand;
import org.nthdimenzion.cqrs.command.testdata.TestCommand1;
import org.nthdimenzion.testinfrastructure.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class CommandHandlerTest extends AbstractTest{

    @Autowired
    private IMultiCommandHandlerRegistry commandHandlerRegistry;

    @Test
    public void testCommandHandlerFinder(){
        Assert.assertNotNull(commandHandlerRegistry);
        Handler handlerForTestCommand = commandHandlerRegistry.findCommandHandlerFor(TestCommand.class);
        Handler handlerForTestCommand1 = commandHandlerRegistry.findCommandHandlerFor(TestCommand1.class);

        Assert.assertTrue("anyName".equals(handlerForTestCommand.getMethod().getName()));
        Assert.assertTrue("anyNameWithoutSecurity".equals(handlerForTestCommand1.getMethod().getName()));
    }

    @Test(expected = NoCommandHandlerFoundException.class)
    public void testCommandHandlerFinderForInvalidCommand(){
        Assert.assertNotNull(commandHandlerRegistry);
        Handler handler = commandHandlerRegistry.findCommandHandlerFor(InvalidCommand.class);
    }
}
