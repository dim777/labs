package tech.ineb.labs.lab0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class Lab0ProxyTests {
    private static final Logger LOGGER = LogManager.getLogger(Lab0ProxyTests.class);

    @Test
    public void createProxyInstance() {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                Lab0ProxyTests.class.getClassLoader(),
                new Class[]{Map.class},
                (proxy, method, methodArgs) -> getObject(method));

        Integer res = (Integer) proxyInstance.get("hello"); // 42
        proxyInstance.put("hello", "world"); // exception
    }

    private static Object getObject(Method method) {
        if (method.getName().equals("get")) {
            return 42;
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported method: " + method.getName());
        }
    }
}
