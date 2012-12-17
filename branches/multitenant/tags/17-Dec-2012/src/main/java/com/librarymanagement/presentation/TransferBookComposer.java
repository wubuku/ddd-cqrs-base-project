package com.librarymanagement.presentation;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.librarymanagement.application.commands.IssueBooksCommand;
import com.librarymanagement.application.commands.ReturnBooksCommand;
import com.librarymanagement.domain.BookId;
import com.librarymanagement.domain.Member;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.nthdimenzion.presentation.util.ViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listbox;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Composer
public class TransferBookComposer extends AbstractZkComposer {
    private List<Map<String, ?>> allBooks = Lists.newArrayList();

    private List<Map<String, ?>> booksWithMember = Lists.newArrayList();

    private Listbox availableBooksListBox;

    private Listbox booksWithMemberListBox;

    @Autowired
    private ILibraryFinder libraryFinder;

    @Autowired
    private ICrud crudDao;

    private Long memberId;

    private Member member;

    public String getMemberName() {
        return member.getName();
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        memberId = getParam("memberId");
        member = crudDao.find(Member.class,memberId);
        allBooks = getAllBooks();
        booksWithMember = getBooksWithMember(memberId);
    }

    public List<Map<String, ?>> getAllBooks() {
        allBooks = libraryFinder.findAllBooks();
        return allBooks;
    }

    public List<Map<String, ?>> getBooksWithMember() {
        return getBooksWithMember(memberId);
    }

    public List<Map<String, ?>> getBooksWithMember(Long memberId) {
        Preconditions.checkNotNull(memberId);
        booksWithMember = libraryFinder.findAllBooksWithMember(memberId);
        return booksWithMember;
    }

    public void returnBooks() {
        List<Map<String, ?>> booksToBeReturned = ViewUtil.getSelectedItems(booksWithMemberListBox);
        if (UtilValidator.isNotEmpty(booksToBeReturned))
            returnBooks(getBookIds(booksToBeReturned));
    }

    private void returnBooks(Set<BookId> bookIds) {
        Preconditions.checkNotNull(bookIds);
        ReturnBooksCommand bookReturnCommand = new ReturnBooksCommand(bookIds, memberId);
        Boolean result = (Boolean) sendCommand(bookReturnCommand);
        if (isSuccess(result))
            displayMessages.displaySuccess("Books have been returned successfully");

    }

    public void issueBooks() {
        List<Map<String, ?>> booksToBeIssued = ViewUtil.getSelectedItems(availableBooksListBox);
        if (UtilValidator.isNotEmpty(booksToBeIssued))
            issueBooks(getBookIds(booksToBeIssued));
    }

    private Set<BookId> getBookIds(List<Map<String, ?>> booksToBeIssued) {
        Set<BookId> bookIds = Sets.newHashSet();
        for (Map bookToBeIssued : booksToBeIssued) {
            bookIds.add(new BookId((String) bookToBeIssued.get("uid")));
        }
        return bookIds;
    }

    private void issueBooks(Set<BookId> bookIds) {
        Preconditions.checkNotNull(bookIds);
        IssueBooksCommand bookIssueCommand = new IssueBooksCommand(bookIds, memberId);
        Boolean result = (Boolean) sendCommand(bookIssueCommand);
        if (isSuccess(result))
            displayMessages.displaySuccess("Books have been issued successfully");
    }

    public void setBooksWithMember(List<Map<String, ?>> booksWithMember) {
        this.booksWithMember = booksWithMember;
    }
}
