package org.nthdimenzion.testinfrastructure;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Map;

/**
 * This simple scope implementation uses internal a {@link Map} to hold objects
 * in the scope. This scope implementation can be used for testing beans which
 * wants to be stayed for example in session or request scope.
 * <p>
 * To use this scope in your test cases, simple configure a
 * {@link org.springframework.beans.factory.config.CustomScopeConfigurer} with
 * the required scope name:
 * <p/>
 * <pre>
 * &lt;bean class=&quot;org.springframework.beans.factory.config.CustomScopeConfigurer&quot;&gt;
 *   &lt;property name=&quot;scopes&quot;&gt;
 *     &lt;map&gt;
 *       &lt;entry key=&quot;session&quot;&gt;
 *         &lt;bean class=&quot;org.waffel.spring.SessionScope&quot; /&gt;
 *       &lt;/entry&gt;
 *     &lt;/map&gt;
 *   &lt;/property&gt;
 * &lt;/bean&gt;
 * </pre>
 * <p/>
 * Because the
 * {@link org.springframework.beans.factory.config.CustomScopeConfigurer} is a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} all
 * requested beans for the scope loaded with that post processor if the
 * registered scope match.
 * </p>
 */


public class SimpleMapScope implements Scope {

    /* This map contains for each bean name or ID the created object. The objects
    * are created with a spring object factory.
    */
    private final Map<String, Object> objectMap = Maps.newHashMap();

    public SimpleMapScope(){
    }

    @Override
    public Object get(final String theName, final ObjectFactory theObjectFactory) {
        Object object = objectMap.get(theName);
        if (null == object) {
            object = theObjectFactory.getObject();
            objectMap.put(theName, object);
        }
        return object;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(final String theName, final Runnable theCallback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        System.out.println("Hello World");
        return null;
    }

    @Override
    public Object remove(final String theName) {
        return objectMap.remove(theName);
    }
}
