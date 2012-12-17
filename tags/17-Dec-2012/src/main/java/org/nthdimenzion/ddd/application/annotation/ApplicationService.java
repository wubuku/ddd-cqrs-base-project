/**
 * 
 */
package org.nthdimenzion.ddd.application.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public @interface ApplicationService {
    String value() default "";
}
