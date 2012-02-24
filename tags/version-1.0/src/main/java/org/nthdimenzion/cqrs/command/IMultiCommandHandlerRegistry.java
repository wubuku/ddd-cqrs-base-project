package org.nthdimenzion.cqrs.command;

public interface IMultiCommandHandlerRegistry {

    Handler findCommandHandlerFor(Class<? extends ICommand> clazz);
}
