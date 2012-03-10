package com.librarymanagement.infrastructure.repositories.hibernate;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookLending;
import com.librarymanagement.domain.IBookLendingRepository;
import com.librarymanagement.domain.Member;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.object.utils.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

@DomainRepositoryImpl
public class BookLendingRepository extends GenericHibernateRepository<BookLending,Long> implements IBookLendingRepository{

    protected BookLendingRepository(){

    }

    @Autowired
    public BookLendingRepository(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public Long startBookLending(BookLending bookLending) {
        return super.save(bookLending).getId();
    }

    @Override
    public Long completeBookLending(BookLending bookLending) {
        return super.save(bookLending).getId();
    }

    @Override
    public BookLending findOpenBookLending(Book book, Member member) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BookLending.class);
        criteria.add(Restrictions.eq("book", book));
        criteria.add(Restrictions.eq("member", member));
        criteria.add(Restrictions.isNull("lendingInterval.thruDate"));
        BookLending bookLending = UtilValidator.isNotEmpty(hibernateTemplate.findByCriteria(criteria)) ? (BookLending)hibernateTemplate.findByCriteria(criteria).get(0): null;
        return bookLending;
    }

    @Override
    public List<BookLending> findAllBooksWithMember(Member member) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BookLending.class);
        criteria.add(Restrictions.eq("member", member));
        criteria.add(Restrictions.isNull("lendingInterval.thruDate"));
        List<BookLending> openBookLendings = hibernateTemplate.findByCriteria(criteria);
        return openBookLendings;
    }

    @Override
    public BookLending getBookLendingFromId(Long id) {
        return get(id);
    }
}
