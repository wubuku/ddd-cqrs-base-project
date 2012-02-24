package com.librarymanagement.presentation.queries;

import com.librarymanagement.presentation.dto.LibrarySummaryDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Finder
@Component
public class BookQueries {

    @Autowired
    private ILibraryFinder libraryFinder;

    public LibrarySummaryDto getLibrarySummary(){
        Integer bookCountInLibrary = libraryFinder.findBookCountInLibrary();
        Integer countOfMembersWhoHaveBorrowedBooks = libraryFinder.findCountOfMembersWhoHaveBorrowedBooks();
        LibrarySummaryDto librarySummaryDto = new LibrarySummaryDto(bookCountInLibrary,countOfMembersWhoHaveBorrowedBooks);
        librarySummaryDto.memberDtos = libraryFinder.upcomingBirthDays();
        return librarySummaryDto;
    }
}
