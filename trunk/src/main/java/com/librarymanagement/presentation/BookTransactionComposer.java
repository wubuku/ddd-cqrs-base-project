package com.librarymanagement.presentation;

import com.google.common.collect.Maps;
import com.librarymanagement.application.commands.PurchaseBookCopiesCommand;
import com.librarymanagement.application.commands.SellBookCopiesCommand;
import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookId;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;

import java.util.Map;

import static org.nthdimenzion.object.utils.UtilMisc.returnDefaultIfNull;

@Composer
public class BookTransactionComposer extends AbstractZkComposer {

    @Autowired
    private ILibraryFinder bookFinder;

    private Map<String, ?> bookDto = Maps.newHashMap();

    private final static Integer BUY_TXN_CODE = 0;
    private final static Integer SELL_TXN_CODE = 1;

    private Button buyBtn;
    private Button sellBtn;

    private Long bookId;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        String id = getParam("bookId");
        if (UtilValidator.isNotEmpty(id)) {
            bookId = Long.valueOf(getParam("bookId"));
            bookDto = getBookDto();
        }
        comp.setAttribute("dto", bookDto);
    }


    public Map<String, ?> getBookDto() {
        bookDto = bookFinder.findBookWithId(bookId);
        return bookDto;
    }

    public void selectTransaction(String txnMode) {
        buyBtn.setVisible(isBuyMode(Integer.valueOf(txnMode)));
        sellBtn.setVisible(isSellMode(Integer.valueOf(txnMode)));
    }

    public void buyCopies(Integer noOfCopies){
        PurchaseBookCopiesCommand purchaseBookCopiesCommand = new PurchaseBookCopiesCommand(new BookId((String)bookDto.get("bookId")),returnDefaultIfNull(noOfCopies,1));
        Book result = (Book) sendCommand(purchaseBookCopiesCommand);
        bookDto = getBookDto();
       if(isSuccess(result)){
           displayMessages.displaySuccess("Purchased Copies Successfully, now available count is " + bookDto.get("availableCopies"));
       }
    }

    private boolean isNegative(Integer noOfCopies) {
        return noOfCopies < 0;
    }

    public void sellCopies(Integer noOfCopies){
        SellBookCopiesCommand sellBookCopiesCommand = new SellBookCopiesCommand(new BookId((String)bookDto.get("bookId")),returnDefaultIfNull(noOfCopies,1));
        Book result = (Book) sendCommand(sellBookCopiesCommand);
        bookDto = getBookDto();
       if(isSuccess(result)){
           displayMessages.displaySuccess("Sold Copies Successfully, now available count is " + bookDto.get("availableCopies"));
       }
    }


    private boolean isBuyMode(Integer txnMode) {
        return BUY_TXN_CODE.equals(txnMode);
    }

    private boolean isSellMode(Integer txnMode) {
        return SELL_TXN_CODE.equals(txnMode);
    }

}
