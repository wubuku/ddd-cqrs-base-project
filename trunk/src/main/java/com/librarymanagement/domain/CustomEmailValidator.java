package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.nthdimenzion.crud.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 23/4/13
 * Time: 5:21 PM
 */
public class CustomEmailValidator implements ConstraintValidator<CustomEmail,String> {

    @Autowired
    private ICrud crudDao;

    @Override
    public void initialize(CustomEmail customEmail) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Preconditions.checkNotNull(crudDao);
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
