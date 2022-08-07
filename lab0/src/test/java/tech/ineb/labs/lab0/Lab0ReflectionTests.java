package tech.ineb.labs.lab0;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Lab0ReflectionTests {
    private static final Logger LOGGER = LogManager.getLogger(Lab0ReflectionTests.class);

    //given
    private static final UUID id = UUID.randomUUID();
    private static final DemoEntity demoEntity = new DemoEntity(id, "data");

    @Test
    public void testGetFieldPrivateAccessModifier() {
        //when
        String data = null;
        try {
            Field field = demoEntity.getClass().getDeclaredField("data");
            field.setAccessible(true);
            data = (String) field.get(demoEntity);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //then
        Assert.assertEquals(id + "data", id + data);
    }

    @Test
    public void testChangeFieldPrivateAccessModifier() {
        //when
        String data = "newData";
        try {
            Field field = demoEntity.getClass().getDeclaredField("data");
            field.setAccessible(true);
            //could be set even for final field
            field.set(demoEntity, data);
            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //Assert.assertEquals(id + data, id + );
    }

    @Test
    public void testChangeMethodPrivateAccessModifier() {
        String data = null;
        try {
            Method method = demoEntity.getClass().getDeclaredMethod("fire");
            method.setAccessible(true);
            data = (String) method.invoke(demoEntity);
            method.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(data, id + "data");
    }

    @Test
    public void testNewClassInstanceViaReflection() {
        DemoEntity demoEntity = null;

        Wrapper wrapper = new Wrapper("tech.ineb.labs.lab0.Lab0ReflectionTests$DemoEntity");
        try {
            demoEntity = wrapper.get();
        } catch (ExWrapper exWrapper) {
            exWrapper.printStackTrace();
        }

        LOGGER.info("DemoEntity='{}'", demoEntity);
    }

    static class DemoEntity {
        private static final StringBuilder sb = new StringBuilder();
        private final UUID id;
        private final String data;

        public DemoEntity(UUID id, String data) {
            this.id = id;
            this.data = data;
        }

        private String getData() {
            return data;
        }

        private String fire() {
            return sb
                    .append(id)
                    .append(data)
                    .toString();
        }
    }

    class Wrapper {
        private String className;

        Wrapper(String className) {
            this.className = className;
        }

        @SuppressWarnings("unchecked")
        DemoEntity get() throws ExWrapper {
            DemoEntity demoEntity;
            try {
                Class<?> clazz = Class.forName(className);
                Class<?>[] params = {UUID.class, String.class};
                demoEntity = (DemoEntity) clazz.getConstructor(params)
                        .newInstance(UUID.randomUUID(), "data2");
            } catch (ClassNotFoundException
                    | IllegalAccessException
                    | InstantiationException
                    | NoSuchMethodException
                    | InvocationTargetException e) {
                throw new ExWrapper(e);
            }
            return demoEntity;
        }
    }

    class ExWrapper extends Exception {
        public ExWrapper() {
            super();
        }

        public ExWrapper(String message) {
            super(message);
        }

        public ExWrapper(String message, Throwable cause) {
            super(message, cause);
        }

        public ExWrapper(Throwable cause) {
            super(cause);
        }
    }

}
