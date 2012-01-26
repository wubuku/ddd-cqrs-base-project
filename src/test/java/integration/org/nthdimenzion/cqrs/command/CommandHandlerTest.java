package org.nthdimenzion.cqrs.command;

import junit.framework.Assert;
import org.junit.Test;
import org.nthdimenzion.cqrs.command.testdata.InvalidCommand;
import org.nthdimenzion.cqrs.command.testdata.TestCommand;
import org.nthdimenzion.cqrs.command.testdata.TestCommand1;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandHandlerTest extends AbstractTestFacilitator {

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

    @Test
    public void testInvalidCommand(){
        TestCommand testCommand = new TestCommand();
        commandBus.send(testCommand);

        Assert.assertTrue(presentationDecoratedExceptionHandler.isExceptionHandled());
    }
}
