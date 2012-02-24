package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import com.librarymanagement.domain.BookId;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class PurchaseBookCopiesCommand implements ICommand{
    public final BookId bookId;
    public final Integer noOfCopiesToPurchase;

    public PurchaseBookCopiesCommand(BookId bookId,Integer noOfCopiesToPurchase){
    this.bookId = bookId;
    this.noOfCopiesToPurchase = noOfCopiesToPurchase;
    }

    @Override
    public void validate() {
        Preconditions.checkNotNull(bookId);
        Preconditions.checkNotNull(noOfCopiesToPurchase);
        Preconditions.checkArgument(noOfCopiesToPurchase >0,"Number of copies purchased cannot be Zero");
    }
}
