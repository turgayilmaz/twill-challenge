package com.twilllogistics.assessment.twilluserapi.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;

/**
 * Created by turgay on 01/09/17.
 */
public class TestHelper {

    public static String invokeMethod(String s, Object object) {
        try {
            String methodName = "get" + s.substring(0, 1).toUpperCase() + s.substring(1);
            Method method = object.getClass().getMethod(methodName);
            return (String) method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
        return null;
    }
}
