package org.nthdimenzion.cqrs.command;

@Deprecated
public interface ICommandHandler<C extends ICommand,R> {

    R handle(C command) throws Throwable;
}
