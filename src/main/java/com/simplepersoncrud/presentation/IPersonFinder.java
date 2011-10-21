package com.simplepersoncrud.presentation;

import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.nthdimenzion.cqrs.query.annotations.Finder;

import java.util.List;

@Finder
public interface IPersonFinder {

    List<PersonDetailsDto> findAllPeople();

}
