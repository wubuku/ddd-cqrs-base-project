package com.librarymanagement.presentation;

import com.google.common.collect.Lists;
import com.librarymanagement.presentation.dto.MemberDto;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

import java.util.List;

@Composer
public class MembersHoldingBooksComposer extends AbstractZkComposer {

    private List<MemberDto> membersHoldingBook = Lists.newArrayList();

    @Autowired
    private ILibraryFinder libraryFinder;

    private String bookId = null;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bookId = getParam("bookId");
        membersHoldingBook = getMembersHoldingBook(Long.valueOf(bookId));
    }

    public List<MemberDto> getMembersHoldingBook(Long bookId) {
        List<MemberDto> members = libraryFinder.findMembersHoldingBook(bookId);
        return UtilValidator.isNotEmpty(members) ? members : null;
    }

    public boolean isNoRecordsFound() {
        return UtilValidator.isEmpty(membersHoldingBook);
    }

    public boolean isRecordsFound(){
        return !isNoRecordsFound();
    }

    public List<MemberDto> getMembersHoldingBook() {
        return getMembersHoldingBook(Long.valueOf(bookId));
    }


}

