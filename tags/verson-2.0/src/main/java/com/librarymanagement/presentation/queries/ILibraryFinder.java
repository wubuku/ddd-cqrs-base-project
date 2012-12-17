
package com.librarymanagement.presentation.queries;

import com.librarymanagement.presentation.dto.MemberDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.List;
import java.util.Map;

@Finder
public interface ILibraryFinder {

    List<Map<String,?>> findAllBooks();

    Integer findBookCountInLibrary();

    Integer findCountOfMembersWhoHaveBorrowedBooks();

    List<MemberDto> upcomingBirthDays();

    List<Map<String,?>> findAllBooksWithMember(Long memberId);

    Map<String,?> findBookWithId(Long bookId);

    List<Map<String,?>> findBookLendings(Long memberId,Long bookId);

    String findMostActiveBook();

    MemberDto findMostActiveMember();


}
