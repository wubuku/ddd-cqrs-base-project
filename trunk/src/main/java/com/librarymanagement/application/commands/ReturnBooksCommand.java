package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import com.librarymanagement.domain.BookId;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;
import org.nthdimenzion.object.utils.UtilValidator;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Command
public class ReturnBooksCommand implements ICommand{
    public final Set<BookId> bookIds;
    public final Long memberId;

    public ReturnBooksCommand(Set<BookId> bookIds, Long memberId) {
        this.bookIds = bookIds;
        this.memberId = memberId;
    }

    @Override
    public void validate() {
        Preconditions.checkArgument(UtilValidator.isNotEmpty(bookIds));
        Preconditions.checkNotNull(memberId);
    }
}
