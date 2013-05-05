package com.librarymanagement.presentation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.librarymanagement.presentation.queries.DateOfBirthCriteria;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;
import org.nthdimenzion.cqrs.query.IPage;
import org.nthdimenzion.cqrs.query.IPageFinder;
import org.nthdimenzion.cqrs.query.PagingUtil;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

import java.util.List;
import java.util.Map;

@Composer
public class BookListComposer extends AbstractZkComposer{

    @Autowired
    private ILibraryFinder libraryFinder;

    private List<Map<String,?>> bookList = Lists.newArrayList();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initializePage();

    }

    private void initializePage() {
        getBookList();
    }

    @Autowired
    private IPageFinder pageFinder;

    public List<Map<String, ?>> getBookList() {
        IPage pagedResult = pageFinder.findAll(ILibraryFinder.FIND_ALL_BOOKS, new RowBounds(3,3));
        bookList = pagedResult.getContent();
        return bookList;
    }

    public void selectBook(Map<String,?> book,String viewId){
        navigation.redirect(viewId, ImmutableMap.of("bookId",String.valueOf(book.get("id"))));
    }
}
