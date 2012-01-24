package org.nthdimenzion.ddd.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Target(ElementType.TYPE)
public @interface EventHandlers {

}
