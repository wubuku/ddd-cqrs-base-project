package org.nthdimenzion.ddd.domain.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Component
@Target(ElementType.TYPE)
public @interface EventHandlers {

}
