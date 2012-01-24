package com.librarymanagement.application.commands;

import com.librarymanagement.domain.BookId;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;
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
    public boolean equals(Object o) {
            return EqualsBuilder.reflectionEquals(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{bookIds, memberId});
    }

}
