package org.nthdimenzion.cqrs.command;

public interface ICommandInterceptor {

    boolean pre(ICommand command);

    boolean post(ICommand command,Object result);
}
