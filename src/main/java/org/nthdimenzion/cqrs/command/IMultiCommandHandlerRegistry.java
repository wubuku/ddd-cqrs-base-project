package org.nthdimenzion.cqrs.command;

import java.lang.reflect.Method;

public interface IMultiCommandHandlerRegistry {

    Handler findCommandHandlerFor(Class<? extends ICommand> clazz);
}
