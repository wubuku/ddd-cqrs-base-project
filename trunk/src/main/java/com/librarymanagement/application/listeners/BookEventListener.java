package com.librarymanagement.application.listeners;

import com.adamtaft.eb.EventHandler;
import com.google.common.eventbus.Subscribe;
import com.librarymanagement.application.events.BookIssuedEvent;
import com.librarymanagement.application.events.BookReturnedEvent;
import com.librarymanagement.domain.*;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.domain.annotations.EventHandlers;
import org.nthdimenzion.ddd.infrastructure.AbstractEventListener;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@EventHandlers
public class BookEventListener extends AbstractEventListener{

    @Autowired
    private IBookLendingRepository bookLendingRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ICrud crudDao;

    @Autowired
    private BookLendingFactory bookLendingFactory;

    @Autowired
    protected BookEventListener(@Qualifier("domainEventBus") IEventBus domainEventBus) {
        super(domainEventBus);
    }

     @Subscribe
    public void beginBookLendingTerm(BookIssuedEvent bookIssuedEvent){
        Book book = bookRepository.geBookWithUid(bookIssuedEvent.bookId);
        Member member = crudDao.find(Member.class,bookIssuedEvent.memberId);
        BookLending bookLending = bookLendingFactory.create(book,member);
        bookLendingRepository.startBookLending(bookLending);
    }

     @Subscribe
    public void completeBookLendingTerm(BookReturnedEvent bookReturnedEvent) {
        Book book = bookRepository.geBookWithUid(bookReturnedEvent.bookId);
        Member member = crudDao.find(Member.class,bookReturnedEvent.memberId);
        BookLending bookLending = bookLendingRepository.findOpenBookLending(book,member);
        bookLending.bookReturned();
        bookLendingRepository.completeBookLending(bookLending);
    }


}
