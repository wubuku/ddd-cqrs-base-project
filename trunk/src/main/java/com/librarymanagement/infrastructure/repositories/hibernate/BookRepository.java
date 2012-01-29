package com.librarymanagement.infrastructure.repositories.hibernate;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookId;
import com.librarymanagement.domain.IBookRepository;
import com.simplepersoncrud.domain.PersonId;
import com.simplepersoncrud.domain.SimplePerson;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

@DomainRepositoryImpl
public class BookRepository extends GenericHibernateRepository<Book, Long> implements IBookRepository {

    protected BookRepository(){

    }

    @Autowired
    public BookRepository(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public Long registerBook(Book book) {
        book = save(book);
        return book.getId();
    }

    @Override
    public Book updateBook(Book book) {
        return save(book);
    }

    @Override
    public Book getBookFromId(Long id) {
        return get(id);
    }

    @Override
    public Book geBookWithUid(BookId bookId) {
        DetachedCriteria bookFromUid = criteriaForUid("bookId",Book.class,bookId);
        Book book = (Book) hibernateTemplate.findByCriteria(bookFromUid).get(0);
        return updateBookWithDependencies(book);
    }

    @Override
    public Book lend(Book book) {
        book = save(book);
        return book;
    }

    @Override
    public Book rentalExpiry(Book book) {
        return save(book);
    }

    @Override
    public Book purchaseCopies(Book book) {
        return updateBook(book);
    }

    @Override
    public Book sellCopies(Book book) {
        return updateBook(book);
    }

    private Book updateBookWithDependencies(Book book) {
        if (book != null)
            book.setDomainEventBus(domainEventBus);
        return book;
    }
}
