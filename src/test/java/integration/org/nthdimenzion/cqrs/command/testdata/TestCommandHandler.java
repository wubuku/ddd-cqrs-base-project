package integration.org.nthdimenzion.cqrs.command.testdata;

import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;

@CommandHandler
@Qualifier("testCommandHandler")
public class TestCommandHandler implements ICommandHandler<TestCommand,Long>{

    @Override
    public Long handle(TestCommand command) throws Throwable {
        return 1L;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
