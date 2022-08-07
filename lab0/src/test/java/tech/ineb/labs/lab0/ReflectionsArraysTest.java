package tech.ineb.labs.lab0;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectionsArraysTest {
    static void add(Object parent, Object child) {
        try {
            Field parentChildsField = assertAdd(parent, child);
            if (!parentChildsField.isAccessible()) {
                parentChildsField.setAccessible(true);
            }
            Object data = parentChildsField.get(parent);
            parentChildsField.setAccessible(false);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("childs field doesn't exists");
        }
    }

    private static Field assertAdd(Object parent, Object child) throws NoSuchFieldException {
        Objects.requireNonNull(parent, "parent couldn't be null");
        Objects.requireNonNull(child, "child couldn't be null");
        Class<?> parentClazz = parent.getClass();
        Class<?> childClazz = child.getClass();
        if (parentClazz != childClazz)
            throw new IllegalArgumentException("child and parent classes should be equals");
        return parentClazz.getDeclaredField("childrens");
    }
}
