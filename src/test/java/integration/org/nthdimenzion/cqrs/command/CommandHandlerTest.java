package integration.org.nthdimenzion.cqrs.command;

import integration.org.nthdimenzion.cqrs.command.testdata.AdvisedTestCommandHandler;
import integration.org.nthdimenzion.cqrs.command.testdata.TestCommand;
import integration.org.nthdimenzion.cqrs.command.testdata.TestCommandForAdvised;
import integration.org.nthdimenzion.cqrs.command.testdata.TestCommandHandler;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.ICommandHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/queryContext.xml","classpath:/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CommandHandlerTest {

    @Autowired
    private ICommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("simpleCommandBus")
    private ICommandBus commandBus;

    @Test
    public void testCommandHandlerFinder(){
        Assert.assertNotNull(commandHandlerRegistry);

        ICommandHandler actualNonAdvisedCommandHandler = commandHandlerRegistry.findCommandHanlerFor(TestCommand.class);
        ICommandHandler actualAdvisedCommandHandler = commandHandlerRegistry.findCommandHanlerFor(TestCommandForAdvised.class);

        Assert.assertTrue(actualAdvisedCommandHandler instanceof AdvisedTestCommandHandler);
        Assert.assertTrue(actualNonAdvisedCommandHandler instanceof TestCommandHandler);
    }

    /*@Test(expected = NoCommandHandlerFoundException.class)
    public void testCommandHandlerFinderForInvalidCommand(){
        Assert.assertNotNull(commandHandlerRegistry);
        String actualCommandHandlerName = commandHandlerRegistry.findCommandHanlerFor(InvalidCommand.class).getClass().getSimpleName();
    }
*/
}
