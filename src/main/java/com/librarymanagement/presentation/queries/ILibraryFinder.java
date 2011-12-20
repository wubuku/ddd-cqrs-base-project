package com.librarymanagement.presentation.queries;

import com.librarymanagement.domain.Member;
import com.librarymanagement.presentation.dto.BookDetailsDto;
import com.librarymanagement.presentation.dto.LibrarySummaryDto;
import com.librarymanagement.presentation.dto.MemberDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.List;

@Finder
public interface ILibraryFinder {

    List<BookDetailsDto> findAllBooks();

    Integer findBookCountInLibrary();

    String findMostBorrowedBook();

    String findLeastBorrowedBook();

    Integer findCountOfMembersWhoHaveBorrowedBooks();

    List<MemberDto> upcomingBirthDays();
}
