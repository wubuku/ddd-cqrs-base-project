package com.librarymanagement.presentation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.librarymanagement.presentation.queries.ILibraryFinder;
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

    public List<Map<String, ?>> getBookList() {
        bookList = libraryFinder.findAllBooks();
        return bookList;
    }

    public void selectBook(Map<String,?> book){
        System.out.println(book);
//        navigation.redirect("updateBook");
        navigation.redirect("updateBook", ImmutableMap.of("bookId",String.valueOf(book.get("id"))));
    }
}
