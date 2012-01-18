package com.librarymanagement.presentation;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Listbox;

import java.util.List;
import java.util.Map;

@Composer
public class TransferBookComposer extends AbstractZkComposer {
    private final Logger logger = LoggerFactory.getLogger(MemberRegistrationComposer.class);

    private List<Map<String, ?>> allBooks = Lists.newArrayList();

    private List<Map<String, ?>> booksWithMember = Lists.newArrayList();

    private Listbox availableBooksListBox;

    private Listbox booksWithMemberListBox;

    @Autowired
    private ILibraryFinder libraryFinder;

    private Long memberId;


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        memberId = (Long) Executions.getCurrent().getArg().get("memberId");
        System.out.println(memberId);
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

    public void returnBooks(){
        System.out.println("transferBooks");
    }

    private void returnBooks(List<Long> bookId){

    }

    public void issueBooks(){
        System.out.println("issueBooks");
    }

    private void issueBooks(List<Long> bookId){

    }

    public void setBooksWithMember(List<Map<String, ?>> booksWithMember) {
        this.booksWithMember = booksWithMember;
    }
}
