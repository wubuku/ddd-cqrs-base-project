package com.librarymanagement.presentation;

import com.google.common.collect.Maps;
import com.librarymanagement.application.commands.PurchaseBookCommand;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.apache.commons.beanutils.BeanUtils;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Composer
public class PurchaseBookComposer extends AbstractZkComposer {
    private final Logger logger = LoggerFactory.getLogger(PurchaseBookComposer.class);
    private Map<String, ?> bookDto = Maps.newHashMap();
    private boolean isUpdateView = false;

    @Autowired
    private ILibraryFinder bookFinder;


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initializePage();
        Long bookId = Long.valueOf(Executions.getCurrent().getParameter("bookId"));
        if (bookId != null) {
            bookDto = bookFinder.findBookWithId(bookId);
            isUpdateView = true;
        }
        comp.setAttribute("bookDto", bookDto);
    }


    private void initializePage() {
        isUpdateView = false;
    }

    public boolean isPurchaseBookView(){
        return !isUpdateView();
    }

    public boolean isUpdateView() {
        return isUpdateView;
    }


    public void purchaseBook() throws InvocationTargetException, IllegalAccessException {
        PurchaseBookCommand purchaseBookCommand = new PurchaseBookCommand();
        BeanUtils.populate(purchaseBookCommand, bookDto);
        Long bookId = (Long)sendCommand(purchaseBookCommand);
        displayMessages.displaySuccess();
    }


    public void updateBook(){
        System.out.println("updateBook");
    }
}
