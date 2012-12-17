package org.nthdimenzion.ddd.infrastructure.spring;

import org.springframework.beans.BeansException;

public interface IApplicationContext {

    Object getBean(String name) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
    
    boolean isProfile(String profile);
}
