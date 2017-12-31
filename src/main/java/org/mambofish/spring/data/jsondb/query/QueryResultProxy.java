package org.mambofish.spring.data.jsondb.query;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.InvocationHandler;

class QueryResultProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(QueryResultProxy.class);

    private static final Pattern beanGetterPattern = Pattern.compile("^(is|get)(\\w+)");

    private final Map<String, ?> data;

    QueryResultProxy(Map<String, ?> queryResults) {
        this.data = queryResults;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (isNotTraditionalGetter(method)) {
            log.warn("QueryResult interface method " + method.getName()
                    + " doesn't appear to be a getter and therefore may not return the correct result.");
        }

        Matcher matcher = beanGetterPattern.matcher(method.getName());
        if (matcher.matches()) {
            String propertyKey = matcher.group(2);
            propertyKey = propertyKey.substring(0, 1).toLowerCase().concat(propertyKey.substring(1));
            return data.get(propertyKey);
        }

        return data.get(method.getName());
    }

    private boolean isNotTraditionalGetter(Method method) {
        return method.getParameterTypes().length != 0 || Void.class.equals(method.getReturnType())
                || (!method.getName().startsWith("get") && !method.getName().startsWith("is"));
    }

}
