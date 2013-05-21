package org.nthdimenzion.ddd.infrastructure.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.ddd.domain.IEvent;
import org.nthdimenzion.object.utils.UtilValidator;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class CommandValidationFailed implements IEvent{

    public String details = "Operation could not be completed";
    public final Throwable rootCause;


    Set<ConstraintViolation<ICommand>> constraintViolations = Sets.newHashSet();

    List<String> constraintMessages = Lists.newArrayList();


    public CommandValidationFailed(Set<ConstraintViolation<ICommand>> constraintViolations){
        if(UtilValidator.isNotEmpty(constraintViolations)){
            for(ConstraintViolation constraintViolation : constraintViolations){
                constraintMessages.add(constraintViolation.getMessage());
            }
            this.constraintViolations = constraintViolations;
        }
        details = constraintMessages.get(0);
        rootCause= null;
    }

    public CommandValidationFailed(Throwable rootCause){
        this.rootCause = rootCause;
    }


}
