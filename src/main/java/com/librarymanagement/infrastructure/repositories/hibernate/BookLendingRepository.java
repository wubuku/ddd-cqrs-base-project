package com.librarymanagement.infrastructure.repositories.hibernate;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookLending;
import com.librarymanagement.domain.IBookLendingRepository;
import com.librarymanagement.domain.Member;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.ddd.infrastructure.hibernate.IHibernateDaoOperations;
import org.nthdimenzion.object.utils.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainRepositoryImpl
@Transactional
public class BookLendingRepository extends GenericHibernateRepository<BookLending,Long> implements IBookLendingRepository{

    protected BookLendingRepository(){

    }

    @Autowired
    public BookLendingRepository(IHibernateDaoOperations hibernateDaoOperations) {
        super(hibernateDaoOperations);
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
        BookLending bookLending = UtilValidator.isNotEmpty(hibernateDaoOperations.findByCriteria(criteria)) ? (BookLending)hibernateDaoOperations.findByCriteria(criteria).get(0): null;
        return bookLending;
    }

    @Override
    public BookLending getBookLendingFromId(Long id) {
        return get(id);
    }

    @Override
    public List<BookLending> findAllBooksWithMember(Member member){
        DetachedCriteria criteria = DetachedCriteria.forClass(BookLending.class);
        criteria.add(Restrictions.eq("member", member));
        criteria.add(Restrictions.isNull("lendingInterval.thruDate"));
        List<BookLending> openBookLendings = hibernateDaoOperations.findByCriteria(criteria);
        return openBookLendings;
    }
}
