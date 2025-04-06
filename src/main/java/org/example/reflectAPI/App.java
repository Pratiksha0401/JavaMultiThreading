package org.example.reflectAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Demonstrates Java Reflection:
 * - Accessing private fields
 * - Invoking private methods
 * - Dynamically creating objects
 * - Calling methods via reflection
 */
class Test {
    private int count = 0;

    /**
     * Getter for the count field.
     *
     * @return current value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Private method to increment count by 1.
     */
    private void increment() {
        count++;
    }

    /**
     * Constructor with count initialization.
     *
     * @param count initial value for the count field
     */
    public Test(int count) {
        this.count = count;
    }
}

public class App {
    public static void main(String[] args)
            throws NoSuchFieldException,
            IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {

        // 🧪 Create instance of Test using constructor
        Test test = new Test(100);
        System.out.println("Initial count (via constructor): " + test.getCount());

        // 🔍 Access and modify private field 'count'
        Field field = Test.class.getDeclaredField("count");
        field.setAccessible(true); // Make private field accessible
        field.set(test, 300); // Set new value to 'count'
        System.out.println("After reflection field set: " + test.getCount());

        // ⚙️ Access and invoke private method 'increment'
        Method increment = Test.class.getDeclaredMethod("increment");
        increment.setAccessible(true); // Make private method accessible
        increment.invoke(test); // Invoke method on 'test' instance
        System.out.println("After invoking increment(): " + test.getCount());

        // 🏗️ Create object via reflection with constructor that takes int parameter
        Class<?> testClass = Class.forName("org.example.reflectAPI.Test"); // Load class by name
        Constructor<?> declaredConstructor = testClass.getDeclaredConstructor(int.class);
        Object o = declaredConstructor.newInstance(500); // Create new instance with value 500

        // 📞 Call public method 'getCount' via reflection
        Method getCount = testClass.getMethod("getCount");
        Object result = getCount.invoke(o); // Call method on object created via reflection
        System.out.println("Result from reflected getCount(): " + result);
    }
}
