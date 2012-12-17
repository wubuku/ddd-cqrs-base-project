package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import com.librarymanagement.domain.BookId;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class SellBookCopiesCommand implements ICommand{
    public final BookId bookId;
    public final Integer noOfCopiesToSell;

    public SellBookCopiesCommand(BookId bookId, Integer noOfCopiesToSell) {
        this.bookId = bookId;
        this.noOfCopiesToSell = noOfCopiesToSell;
    }

    @Override
    public void validate() {
        Preconditions.checkNotNull(bookId);
        Preconditions.checkNotNull(noOfCopiesToSell);
        Preconditions.checkArgument(noOfCopiesToSell>0,"How did you manage to sell " + noOfCopiesToSell + " copies ? ");
    }
}
