package org.nthdimenzion.cqrs.command;

public interface ICommandHandlerRegistry {

    ICommandHandler findCommandHanlerFor(Class<? extends ICommand> clazz);
}
