package org.nthdimenzion.cqrs.command.testdata;

import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class InvalidCommand implements ICommand{
    @Override
    public void validate() {
    }
}
