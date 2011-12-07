package com.librarymanagement.presentation;

import com.librarymanagement.presentation.dto.BookDetailsDto;
import com.simplepersoncrud.presentation.dto.PeopleSummaryDto;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.List;

@Finder
public interface IBookFinder {

    List<BookDetailsDto> findAllBooks();

}
