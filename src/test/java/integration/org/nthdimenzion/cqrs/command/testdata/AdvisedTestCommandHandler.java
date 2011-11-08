package integration.org.nthdimenzion.cqrs.command.testdata;

import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
@Qualifier("advisedTestCommandHandler")
public class AdvisedTestCommandHandler implements ICommandHandler<TestCommandForAdvised, Long> {

    @Override
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Long handle(TestCommandForAdvised command) throws Throwable {
        return 1L;
    }
}
