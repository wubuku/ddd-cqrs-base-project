package com.librarymanagement.domain;

import com.librarymanagement.domain.error.NotEnoughCopiesException;

public interface IRentable {

    void rentalExpiry(Long rentedTo);
    void lend(Long rentedTo) throws NotEnoughCopiesException;
}
