package org.nthdimenzion.cqrs.command.annotations;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public @interface CommandHandler {
}
