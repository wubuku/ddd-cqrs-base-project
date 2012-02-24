package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import com.librarymanagement.domain.BookId;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;
import org.nthdimenzion.object.utils.UtilValidator;

import java.util.Set;

@Command
public class IssueBooksCommand implements ICommand {

    public final Set<BookId> bookIds;
    public final Long memberId;

    public IssueBooksCommand( Set<BookId> bookIds, Long memberId) {
        this.bookIds = bookIds;
        this.memberId = memberId;
    }


    @Override
    public void validate() {
        Preconditions.checkNotNull(memberId);
        Preconditions.checkArgument(UtilValidator.isNotEmpty(bookIds));
    }
}
