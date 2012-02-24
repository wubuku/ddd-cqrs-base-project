package org.nthdimenzion.cqrs.command.testdata;

import org.nthdimenzion.cqrs.command.AbstractCommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
@Qualifier("advisedTestCommandHandler")
public class MultiTestCommandHandler extends AbstractCommandHandler{

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Long anyName(TestCommand command) throws Throwable {
        return 1L;
    }

    public Long anyNameWithoutSecurity(TestCommand1 command) throws Throwable {
        return 1L;
    }
}
