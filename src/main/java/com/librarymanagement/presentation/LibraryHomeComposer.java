package com.librarymanagement.presentation;

import com.librarymanagement.presentation.dto.LibrarySummaryDto;
import com.librarymanagement.presentation.queries.BookQueries;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

@Composer
public class LibraryHomeComposer extends AbstractZkComposer{

    @Autowired
    private BookQueries bookQueries;

    private LibrarySummaryDto librarySummaryDto;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        librarySummaryDto = bookQueries.getLibrarySummary();
        System.out.println(librarySummaryDto.numberOfBooks);
        comp.setAttribute("dto",librarySummaryDto);
    }

    public LibrarySummaryDto getLibrarySummaryDto() {
        return librarySummaryDto;
    }
}
