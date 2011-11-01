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

        /**
         * Subscribe the given <code>handler</code> to commands of type <code>commandType</code>.
         * <p/>
         * If a subscription already exists for the given type, the behavior is undefined. Implementations may throw an
         * Exception to refuse duplicate subscription or alternatively decide whether the existing or new
         * <code>handler</code> gets the subscription.
         *
         * @param commandType The type of command to subscribe the handler to
         * @param handler     The handler instance that handles the given type of command
         * @param <C>         The Type of command
         */
        <C> void subscribe(Class<C> commandType, ICommandHandler handler);

        /**
         * Unsubscribe the given <code>handler</code> to commands of type <code>commandType</code>. If the handler is not
         * currently assigned to that type of command, no action is taken.
         *
         * @param commandType The type of command the handler is subscribed to
         * @param handler     The handler instance to unsubscribe from the CommandBus
         * @param <C>         The Type of command
         */
        <C> void unsubscribe(Class<C> commandType, ICommandHandler handler);

}
