package org.nthdimenzion.cqrs.command;

public interface ICommandHandlerRegistry {

    ICommandHandler findCommandHandlerFor(Class<? extends ICommand> clazz);
}
