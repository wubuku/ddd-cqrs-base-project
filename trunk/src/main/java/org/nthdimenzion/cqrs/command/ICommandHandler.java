package org.nthdimenzion.cqrs.command;

public interface ICommandHandler<C extends ICommand,R> {

    R handle(C command) throws Throwable;
}
