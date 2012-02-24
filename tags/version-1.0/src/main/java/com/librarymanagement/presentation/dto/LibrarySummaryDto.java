package com.librarymanagement.presentation.dto;

import java.util.List;

public class LibrarySummaryDto {

    public Integer numberOfBooks;

    public Integer countOfMembersWhoHaveBorrowedBooks;

    public List<MemberDto> memberDtos;

    public LibrarySummaryDto(Integer numberOfBooks, Integer countOfMembersWhoHaveBorrowedBooks ) {
        this.numberOfBooks = numberOfBooks;
        this.countOfMembersWhoHaveBorrowedBooks = countOfMembersWhoHaveBorrowedBooks;
    }

    public List<MemberDto> getMemberDtos() {
        return memberDtos;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

}
