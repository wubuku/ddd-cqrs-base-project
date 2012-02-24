package org.nthdimenzion.cqrs.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Handler{
    private final Method method;
    private final Class<?> clazz;
    private final Object target;

    public Handler(Method method, Object target) {
        this.method = method;
        this.target = target;
        clazz = method.getClass();
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "Handler{" +
                "method=" + method +
                ", clazz=" + clazz +
                '}';
    }

    public Object invokeMethod(ICommand command) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,new Object[]{command});
    }
}

