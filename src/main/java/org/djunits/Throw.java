package org.djunits;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * The Throw class has a number of static methods that make it easy to throw an exception under conditions for any Exception
 * class, including the standard Java exceptions and exceptions from libraries that are used in the project. Instead of:
 * 
 * <pre>
 * if (car == null)
 * {
 *     throw new NullPointerException("Car may not be null.");
 * }
 * if (Double.isNaN(car.getPosition()))
 * {
 *     throw new IllegalArgumentException("Position of car " + car + " is NaN.");
 * }
 * </pre>
 * 
 * we can write:
 * 
 * <pre>
 * Throw.whenNull(car, "Car may not be null.");
 * Throw.when(Double.isNaN(car.getPosition()), IllegalArgumentException.class, "Position of car %s is NaN.", car);
 * </pre>
 * 
 * The exception message can be formatted with additional arguments, such that the overhead of building the exception message
 * only occurs if the exception condition is met. All methods have a version where the first parameter is returned. Thereby, the
 * Throw can be used as part of a <b>super</b>(...) call in a constructor.
 * <p>
 * Copyright (c) 2016-2022 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank"> https://djunits.org</a>. The DJUNITS project is
 * distributed under a three-clause BSD-style license, which can be found at
 * <a href="https://djunits.org/docs/license.html" target="_blank"> https://djunits.org/docs/license.html</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class Throw
{
    /** private constructor for utility class. */
    private Throw()
    {
        // utility class
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. Use as
     * follows: <br>
     * 
     * <pre>
     * Throw.when(Double.isNan(object.getValue()), IllegalArgumentException.class, "Value may not be NaN.");
     * </pre>
     * 
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     */
    public static <T extends Throwable> void when(final boolean condition, final Class<T> throwableClass, final String message)
            throws T
    {
        if (condition)
        {
            throwMessage(throwableClass, message, new ArrayList<>());
        }
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. Use as
     * follows: <br>
     * 
     * <pre>
     * Throw.when(Double.isNaN(object.getValue()), IllegalArgumentException.class, <br>
     *     "Value may not be NaN for object %s.", object);
     * </pre>
     * 
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg Object; value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     */
    public static <T extends Throwable> void when(final boolean condition, final Class<T> throwableClass, final String message,
            final Object arg) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg);
            throwMessage(throwableClass, message, argList);
        }
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. Use as
     * follows: <br>
     * 
     * <pre>
     * Throw.when(Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s.", object, name);
     * </pre>
     * 
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     */
    public static <T extends Throwable> void when(final boolean condition, final Class<T> throwableClass, final String message,
            final Object arg1, final Object arg2) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            throwMessage(throwableClass, message, argList);
        }
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. Use as
     * follows: <br>
     * 
     * <pre>
     * Throw.when(Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s and id %s.", object, name, id);
     * </pre>
     * 
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     */
    public static <T extends Throwable> void when(final boolean condition, final Class<T> throwableClass, final String message,
            final Object arg1, final Object arg2, final Object arg3) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            throwMessage(throwableClass, message, argList);
        }
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. Use as
     * follows: <br>
     * 
     * <pre>
     * Throw.when(Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s, id %s and parent %s.", object, name, id, parent);
     * </pre>
     * 
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @param args Object...; potential 4th and further values to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     */
    public static <T extends Throwable> void when(final boolean condition, final Class<T> throwableClass, final String message,
            final Object arg1, final Object arg2, final Object arg3, final Object... args) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            argList.addAll(Arrays.asList(args));
            throwMessage(throwableClass, message, argList);
        }
    }

    /**
     * Private method to handle the throwing an Exception, Throwable or Error.
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with potential formatting identifiers
     * @param argList List&lt;Object&gt;; List with potential values to use for the formatting identifiers
     * @throws T the throwable to throw
     * @param <T> the Throwable type
     */
    private static <T extends Throwable> void throwMessage(final Class<T> throwableClass, final String message,
            final List<Object> argList) throws T
    {
        // create a clear message
        List<StackTraceElement> steList = new ArrayList<>(Arrays.asList(new Throwable().getStackTrace()));
        steList.remove(0); // remove the throwMessage(...) call
        steList.remove(0); // remove the when(...) call
        StackTraceElement[] ste = steList.toArray(new StackTraceElement[steList.size()]);
        String where = ste[0].getClassName() + "." + ste[0].getMethodName() + " (" + ste[0].getLineNumber() + "): ";
        Object[] args = argList.toArray();
        String formattedMessage;
        try
        {
            formattedMessage = where + String.format(message, args);
        }
        catch (IllegalFormatException exception)
        {
            formattedMessage = where + message + " [FormatException; args=" + argList + "]";
        }

        // throw all other exceptions through reflection
        T exception;
        try
        {
            Constructor<T> constructor = throwableClass.getConstructor(new Class<?>[] {String.class});
            exception = constructor.newInstance(formattedMessage);
            exception.setStackTrace(ste);
        }
        catch (Throwable t)
        {
            RuntimeException rte = new RuntimeException(t.getMessage(), new Exception(formattedMessage));
            rte.setStackTrace(ste);
            throw rte;
        }
        throw exception;
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. This
     * version of the method returns its first parameter, so it can be used inside a constructor. Use e.g., as follows:
     * 
     * <pre>
     * super(Throw.when(object, Double.isNan(object.getValue()), IllegalArgumentException.class, "Value may not be NaN."));
     * </pre>
     * 
     * @param object O; the object to return by this static method
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     */
    public static <T extends Throwable, O extends Object> O when(final O object, final boolean condition,
            final Class<T> throwableClass, final String message) throws T
    {
        if (condition)
        {
            throwMessage(throwableClass, message, new ArrayList<>());
        }
        return object;
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. This
     * version of the method returns its first parameter, so it can be used inside a constructor. Use e.g., as follows:
     * 
     * <pre>
     * super(Throw.when(object, Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s.", object));
     * </pre>
     * 
     * @param object O; the object to return by this static method
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg Object; value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     */
    public static <T extends Throwable, O extends Object> O when(final O object, final boolean condition,
            final Class<T> throwableClass, final String message, final Object arg) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg);
            throwMessage(throwableClass, message, argList);
        }
        return object;
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. This
     * version of the method returns its first parameter, so it can be used inside a constructor. Use e.g., as follows:
     * 
     * <pre>
     * super(Throw.when(object, Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s.", object, name));
     * </pre>
     * 
     * @param object O; the object to return by this static method
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     */
    public static <T extends Throwable, O extends Object> O when(final O object, final boolean condition,
            final Class<T> throwableClass, final String message, final Object arg1, final Object arg2) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            throwMessage(throwableClass, message, argList);
        }
        return object;
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. This
     * version of the method returns its first parameter, so it can be used inside a constructor. Use e.g., as follows:
     * 
     * <pre>
     * super(Throw.when(object, Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s and id %s.", object, name, id));
     * </pre>
     * 
     * @param object O; the object to return by this static method
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     */
    public static <T extends Throwable, O extends Object> O when(final O object, final boolean condition,
            final Class<T> throwableClass, final String message, final Object arg1, final Object arg2, final Object arg3)
            throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            throwMessage(throwableClass, message, argList);
        }
        return object;
    }

    /**
     * Throw a Throwable (such as an Exception or Error) if a condition is met, e.g. for pre- and postcondition checking. This
     * version of the method returns its first parameter, so it can be used inside a constructor. Use e.g., as follows:
     * 
     * <pre>
     * super(Throw.when(object, Double.isNan(object.getValue()), IllegalArgumentException.class,
     *         "Value may not be NaN for object %s with name %s, id %s and parent %s.", object, name, id, parent));
     * </pre>
     * 
     * @param object O; the object to return by this static method
     * @param condition boolean; the condition to check; an exception will be thrown if this is <b>true</b>
     * @param throwableClass Class&lt;T&gt;; the Throwable type to throw
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @param args Object...; potential 4th and further values to use for the formatting identifiers
     * @throws T the throwable to throw on true condition
     * @param <T> the Throwable type
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public static <T extends Throwable, O extends Object> O when(final O object, final boolean condition,
            final Class<T> throwableClass, final String message, final Object arg1, final Object arg2, final Object arg3,
            final Object... args) throws T
    {
        if (condition)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            argList.addAll(Arrays.asList(args));
            throwMessage(throwableClass, message, argList);
        }
        return object;
    }

    /**
     * Throw a NullPointerException if object is null, e.g. for pre- and postcondition checking. Use as follows: <br>
     * 
     * <pre>
     * Throw.when(object.getValue(), "Value may not be null.");
     * </pre>
     * 
     * @param object object to check; an exception will be thrown if this is <b>null</b>
     * @param message String; the message to use in the exception
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     * @throws NullPointerException if object is null
     */
    public static <O extends Object> O whenNull(final O object, final String message) throws NullPointerException
    {
        if (object == null)
        {
            throwMessage(NullPointerException.class, message, new ArrayList<>());
        }
        return object;
    }

    /**
     * Throw a NullPointerException if object is null, e.g. for pre- and postcondition checking. Use as follows: <br>
     * 
     * <pre>
     * Throw.whenNull(object.getValue(), "Value may not be null for object %s.", object);
     * </pre>
     * 
     * @param object object to check; an exception will be thrown if this is <b>null</b>
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg Object; value to use for the formatting identifiers
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     * @throws NullPointerException if object is null
     */
    public static <O extends Object> O whenNull(final O object, final String message, final Object arg)
            throws NullPointerException
    {
        if (object == null)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg);
            throwMessage(NullPointerException.class, message, argList);
        }
        return object;
    }

    /**
     * Throw a NullPointerException if object is null, e.g. for pre- and postcondition checking. Use as follows: <br>
     * 
     * <pre>
     * Throw.whenNull(object.getValue(), "Value may not be null for object %s with name %s.", object, name);
     * </pre>
     * 
     * @param object object to check; an exception will be thrown if this is <b>null</b>
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     * @throws NullPointerException if object is null
     */
    public static <O extends Object> O whenNull(final O object, final String message, final Object arg1, final Object arg2)
            throws NullPointerException
    {
        if (object == null)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            throwMessage(NullPointerException.class, message, argList);
        }
        return object;
    }

    /**
     * Throw a NullPointerException if object is null, e.g. for pre- and postcondition checking. Use as follows: <br>
     * 
     * <pre>
     * Throw.whenNull(object.getValue(), "Value may not be null for object %s with name %s and id %s.", object, name, id);
     * </pre>
     * 
     * @param object object to check; an exception will be thrown if this is <b>null</b>
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     * @throws NullPointerException if object is null
     */
    public static <O extends Object> O whenNull(final O object, final String message, final Object arg1, final Object arg2,
            final Object arg3) throws NullPointerException
    {
        if (object == null)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            throwMessage(NullPointerException.class, message, argList);
        }
        return object;
    }

    /**
     * Throw a NullPointerException if object is null, e.g. for pre- and postcondition checking. Use as follows: <br>
     * 
     * <pre>
     * Throw.whenNull(object.getValue(), "Value may not be null for object %s with name %s, id %s and parent %s.", <br>
     *     object, name, id, parent);
     * </pre>
     * 
     * @param object object to check; an exception will be thrown if this is <b>null</b>
     * @param message String; the message to use in the exception, with formatting identifiers
     * @param arg1 Object; 1st value to use for the formatting identifiers
     * @param arg2 Object; 2nd value to use for the formatting identifiers
     * @param arg3 Object; 3rd value to use for the formatting identifiers
     * @param args Object...; potential 4th and further values to use for the formatting identifiers
     * @param <O> the Object type to return
     * @return the object that was passed as the first parameter
     * @throws NullPointerException if object is null
     */
    public static <O extends Object> O whenNull(final O object, final String message, final Object arg1, final Object arg2,
            final Object arg3, final Object... args) throws NullPointerException
    {
        if (object == null)
        {
            List<Object> argList = new ArrayList<>();
            argList.add(arg1);
            argList.add(arg2);
            argList.add(arg3);
            argList.addAll(Arrays.asList(args));
            throwMessage(NullPointerException.class, message, argList);
        }
        return object;
    }

}
