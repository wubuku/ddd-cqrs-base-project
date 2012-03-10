package com.librarymanagement.presentation;

import com.google.common.collect.Maps;
import com.librarymanagement.application.commands.RegisterBookCommand;
import com.librarymanagement.application.commands.UpdateBookCommand;
import com.librarymanagement.domain.Book;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.Map;

@Composer
public class RegisterBookComposer extends AbstractZkComposer {
    private Map<String, ?> bookDto = Maps.newHashMap();
    private boolean isUpdateView = false;

    @Autowired
    private ILibraryFinder bookFinder;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initializePage();
        String id = Executions.getCurrent().getParameter("bookId");
        if (UtilValidator.isNotEmpty(id)) {
            Long bookId = Long.valueOf(Executions.getCurrent().getParameter("bookId"));
            bookDto = bookFinder.findBookWithId(bookId);
            isUpdateView = true;
        }
        comp.setAttribute("bookDto", bookDto);
    }


    private void initializePage() {
        isUpdateView = false;
        bookDto = Maps.newHashMap();
    }

    public boolean isRegisterBookView() {
        return !isUpdateView();
    }

    public boolean isUpdateView() {
        return isUpdateView;
    }


    public void registerBook() {
        RegisterBookCommand registerBookCommand = new RegisterBookCommand();
        populate(bookDto, registerBookCommand);
        Long bookId = (Long) sendCommand(registerBookCommand);
        if (isSuccess(bookId)) {
            displayMessages.displaySuccess();
            navigation.redirect("bookList");
        }
    }


    public void updateBook()  {
        UpdateBookCommand updateBookCommand = new UpdateBookCommand();
        populate(bookDto, updateBookCommand);
        Book book = (Book) sendCommand(updateBookCommand);
        if (isSuccess(book))
            displayMessages.displaySuccess();
    }


}
