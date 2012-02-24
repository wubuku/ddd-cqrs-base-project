package org.nthdimenzion.cqrs.command.testdata;

import com.google.common.base.Preconditions;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class TestCommand implements ICommand{

    public String name;

    @Override
    public void validate() {
        Preconditions.checkNotNull(name,"Name cannot be empty");
    }
}
