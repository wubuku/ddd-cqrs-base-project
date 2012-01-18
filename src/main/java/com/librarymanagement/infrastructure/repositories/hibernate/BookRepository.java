package com.librarymanagement.infrastructure.repositories.hibernate;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.IBookRepository;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

@DomainRepositoryImpl
public class BookRepository extends GenericHibernateRepository<Book, Long> implements IBookRepository {

    @Autowired
    public BookRepository(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public Long purchaseBook(Book book) {
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
}
