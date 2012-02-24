package org.nthdimenzion.cqrs.command;

public interface ICommandBus {

    /**
         * Dispatch the given <code>command</code> to the CommandHandler subscribed to that type of <code>command</code>. No
         * feedback is given about the status of the dispatching process. Implementations may return immediately after
         * asserting a valid handler is registered for the given command.
         *
         * @param command The Command to dispatch
         * @throws NoCommandHandlerFoundException when no command handler is registered for the given <code>command</code>.
         */
        Object send(ICommand command);

}
