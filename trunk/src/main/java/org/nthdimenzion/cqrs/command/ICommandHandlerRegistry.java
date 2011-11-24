package org.nthdimenzion.cqrs.command;

@Deprecated
public interface ICommandHandlerRegistry {

    ICommandHandler findCommandHandlerFor(Class<? extends ICommand> clazz);
}
