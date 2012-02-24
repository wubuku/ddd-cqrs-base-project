package org.nthdimenzion.object.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("idGenerator")
public class InMemoryIdGenerator implements IIdGenerator{

    @Override
    public String nextId() {
        return UUID.randomUUID().toString().toUpperCase();
    }
}
