
package com.librarymanagement.presentation.queries;

import com.librarymanagement.presentation.dto.MemberDto;
import org.apache.ibatis.annotations.Param;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Finder
public interface ILibraryFinder {

    String FIND_ALL_BOOKS="findAllBooks";
    String UPCOMING_BIRTHDAYS="testBirthDays";
    String UPCOMING_BIRTHDAYS_WITH_OBJECT_PARAM="upcomingBirthDaysWithObjectParam";

    List<Map<String,?>> findAllBooks();

    Integer findBookCountInLibrary();

    Integer findCountOfMembersWhoHaveBorrowedBooks();

    List<MemberDto> upcomingBirthDays(Date today);

    List<Map<String,?>> findAllBooksWithMember(Long memberId);

    Map<String,?> findBookWithId(Long bookId);

    List<Map<String,?>> findBookLendings(Long memberId,Long bookId);

    String findMostActiveBook();

    MemberDto findMostActiveMember();

    List<MemberDto> findMembersHoldingBook(Long bookId);


}
