package integration.org.nthdimenzion.cqrs.command.testdata;

import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.nthdimenzion.cqrs.command.testdata.TestCommand;
import org.nthdimenzion.cqrs.command.testdata.TestCommand1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
@Qualifier("advisedTestCommandHandler")
public class MultiTestCommandHandler {

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Long anyName(TestCommand command) throws Throwable {
        return 1L;
    }

    public Long anyNameWithoutSecurity(TestCommand1 command) throws Throwable {
        return 1L;
    }
}
