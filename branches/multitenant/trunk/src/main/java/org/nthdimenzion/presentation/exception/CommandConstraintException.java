package org.nthdimenzion.presentation.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;
import org.nthdimenzion.object.utils.UtilValidator;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 22/4/13
 * Time: 10:38 AM
 */
public class CommandConstraintException extends RuntimeException implements IBaseException {

    Set<ConstraintViolation<ICommand>> constraintViolations = Sets.newHashSet();

    List<String> constraintMessages = Lists.newArrayList();

    public CommandConstraintException(Set<ConstraintViolation<ICommand>> constraintViolations){
        if(UtilValidator.isNotEmpty(constraintViolations)){
            for(ConstraintViolation constraintViolation : constraintViolations){
                constraintMessages.add(constraintViolation.getMessage());
            }
            this.constraintViolations = constraintViolations;
        }
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
