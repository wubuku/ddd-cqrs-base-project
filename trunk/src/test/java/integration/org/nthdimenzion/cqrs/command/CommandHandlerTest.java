package integration.org.nthdimenzion.cqrs.command;

import integration.org.nthdimenzion.cqrs.command.testdata.*;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.*;
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
    private IMultiCommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("simpleCommandBus")
    private ICommandBus commandBus;

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
