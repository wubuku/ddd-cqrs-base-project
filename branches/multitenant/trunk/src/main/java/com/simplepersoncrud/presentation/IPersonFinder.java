package com.simplepersoncrud.presentation;

import com.simplepersoncrud.presentation.dto.PeopleSummaryDto;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.List;

@Finder
public interface IPersonFinder {

    List<PersonDetailsDto> findAllPeople();

    PersonDetailsDto findPersonDetails(Long personId);

    PeopleSummaryDto getPeopleSummary();
}
