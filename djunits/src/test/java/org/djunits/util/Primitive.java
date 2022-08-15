package org.djunits.util;

/**
 * The Primitive class is a utility class to deal with primitives. Besides widening and unwidening this class casts and parses
 * UTF8 strings into appropriate primitive classes.
 * <p>
 * Copyright (c) 2002-2009 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
 * <p>
 * See for project information <a href="http://www.getSI()mulation.tudelft.nl/"> www.getSI()mulation.tudelft.nl</a>.
 * <p>
 * The DSOL project is distributed under the following BSD-style license:<br>
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following
 * conditions are met:
 * <ul>
 * <li>Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.</li>
 * <li>Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.</li>
 * <li>Neither the name of Delft University of Technology, nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.</li>
 * </ul>
 * This software is provided by the copyright holders and contributors "as is" and any express or implied warranties, including,
 * but not limited to, the implied warranties of merchantability and fitness for a particular purpose are disclaimed. In no
 * event shall the copyright holder or contributors be liable for any direct, indirect, incidental, special, exemplary, or
 * consequential damages (including, but not limited to, procurement of substitute goods or services; loss of use, data, or
 * profits; or business interruption) however caused and on any theory of liability, whether in contract, strict liability, or
 * tort (including negligence or otherwise) arising in any way out of the use of this software, even if advised of the
 * possibility of such damage.
 * @version $Revision: 1.2 $ $Date: 2009/10/21 07:32:43 $
 * @author <a href="https://www.linkedin.com/in/peterhmjacobs">Peter Jacobs </a>
 */
public final class Primitive
{
    /**
     * constructs a new Primitive.
     */
    private Primitive()
    {
        // unreachable code
    }

    /**
     * casts a set of values to classes.
     * @param classes the classes to cast to
     * @param values the values
     * @return the newly creates values
     */
    public static Object[] cast(final Class<?>[] classes, final Object[] values)
    {
        for (int i = 0; i < classes.length; i++)
        {
            values[i] = Primitive.cast(classes[i], values[i]);
        }
        return values;
    }

    /**
     * casts an object to a instance of clazz.
     * @param clazz the class to cast to
     * @param object the object to cast
     * @return the casted object
     */
    public static Object cast(final Class<?> clazz, final Object object)
    {
        if (clazz.isInstance(object) || !clazz.isPrimitive() || (clazz.equals(Primitive.getPrimitive(object.getClass()))))
        {
            return object;
        }

        // Boolean
        if (clazz.equals(boolean.class))
        {
            return Primitive.toBoolean(object);
        }

        // Character
        if (clazz.equals(char.class))
        {
            return Primitive.toCharacter(object);
        }

        // Byte
        if (clazz.equals(byte.class))
        {
            return Primitive.toByte(object);
        }

        // Double
        if (clazz.equals(double.class))
        {
            return Primitive.toDouble(object);
        }

        // Float
        if (clazz.equals(float.class))
        {
            return Primitive.toFloat(object);
        }

        // Long
        if (clazz.equals(long.class))
        {
            return Primitive.toLong(object);
        }

        // Integer
        if (clazz.equals(int.class))
        {
            return Primitive.toInteger(object);
        }

        // Short
        if (clazz.equals(short.class))
        {
            return Primitive.toShort(object);
        }
        return object;
    }

    /**
     * returns the primitiveClass of the name given as defined by the Java VM class constants. (i.e. both "int" and "I" return
     * int.class). Both void and "V" return void.class. null is returned whenever an unknown className is given.
     * @param className the className
     * @return Class the primitiveClass
     */
    public static Class<?> forName(final String className)
    {
        if (className.equals("int") || className.equals("I"))
        {
            return int.class;
        }
        if (className.equals("double") || className.equals("D"))
        {
            return double.class;
        }
        if (className.equals("byte") || className.equals("B"))
        {
            return byte.class;
        }
        if (className.equals("float") || className.equals("F"))
        {
            return float.class;
        }
        if (className.equals("long") || className.equals("J"))
        {
            return long.class;
        }
        if (className.equals("boolean") || className.equals("Z"))
        {
            return boolean.class;
        }
        if (className.equals("char") || className.equals("C"))
        {
            return char.class;
        }
        if (className.equals("short") || className.equals("S"))
        {
            return short.class;
        }
        if (className.equals("void") || className.equals("V"))
        {
            return void.class;
        }
        return null;
    }

    /**
     * gets the primitive of the given wrapperClass.
     * @param wrapperClass the wrapper class
     * @return the primitive Class. null is returned whenever wrapperClass is not a wrapperclass.
     */
    public static Class<?> getPrimitive(final Class<?> wrapperClass)
    {
        if (wrapperClass.equals(Integer.class))
        {
            return int.class;
        }
        if (wrapperClass.equals(Double.class))
        {
            return double.class;
        }
        if (wrapperClass.equals(Byte.class))
        {
            return byte.class;
        }
        if (wrapperClass.equals(Float.class))
        {
            return float.class;
        }
        if (wrapperClass.equals(Long.class))
        {
            return long.class;
        }
        if (wrapperClass.equals(Boolean.class))
        {
            return boolean.class;
        }
        if (wrapperClass.equals(Character.class))
        {
            return char.class;
        }
        if (wrapperClass.equals(Short.class))
        {
            return short.class;
        }
        return null;
    }

    /**
     * gets the wrapper of this primitive class.
     * @param primitiveClass the primitive class
     * @return the Class. null is returned whenever wrapperClass is not a wrapperclass.
     */
    public static Class<?> getWrapper(final Class<?> primitiveClass)
    {
        if (primitiveClass.equals(int.class))
        {
            return Integer.class;
        }
        if (primitiveClass.equals(double.class))
        {
            return Double.class;
        }
        if (primitiveClass.equals(byte.class))
        {
            return Byte.class;
        }
        if (primitiveClass.equals(float.class))
        {
            return Float.class;
        }
        if (primitiveClass.equals(long.class))
        {
            return Long.class;
        }
        if (primitiveClass.equals(boolean.class))
        {
            return Boolean.class;
        }
        if (primitiveClass.equals(char.class))
        {
            return Character.class;
        }
        if (primitiveClass.equals(short.class))
        {
            return Short.class;
        }
        throw new IllegalArgumentException(primitiveClass + " != primitive");
    }

    /**
     * casts an object to Boolean.
     * @param object the object
     * @return Boolean
     */
    public static Boolean toBoolean(final Object object)
    {
        if (object instanceof Number)
        {
            int value = ((Number) object).intValue();
            if (value == 1)
            {
                return Boolean.TRUE;
            }
            if (value == 0)
            {
                return Boolean.FALSE;
            }
            throw new IllegalArgumentException("object.intValue !=0 && !=1");
        }
        return (Boolean) object;
    }

    /**
     * casts an object to Byte.
     * @param object the object
     * @return Byte
     */
    public static Byte toByte(final Object object)
    {
        if (object instanceof Number)
        {
            return Byte.valueOf(((Number) object).byteValue());
        }
        return (Byte) object;
    }

    /**
     * casts an object to Character.
     * @param object the object to parse
     * @return Integer the result
     */
    public static Character toCharacter(final Object object)
    {
        if (object instanceof Number)
        {
            return Character.valueOf((char) ((Number) object).byteValue());
        }
        return (Character) object;
    }

    /**
     * casts an object to Double.
     * @param object the object to parse
     * @return Integer the result
     */
    public static Double toDouble(final Object object)
    {
        return Double.valueOf(((Number) object).doubleValue());
    }

    /**
     * casts an object to Float.
     * @param object the object to parse
     * @return Float the result
     */
    public static Float toFloat(final Object object)
    {
        return Float.valueOf(((Number) object).floatValue());
    }

    /**
     * casts an object to Long.
     * @param object the object to parse
     * @return Long the result
     */
    public static Long toLong(final Object object)
    {
        return Long.valueOf(((Number) object).longValue());
    }

    /**
     * casts an object to Short.
     * @param object the object to parse
     * @return Long the result
     */
    public static Short toShort(final Object object)
    {
        return Short.valueOf(((Number) object).shortValue());
    }

    /**
     * casts an object to Integer.
     * @param object the object to parse
     * @return Integer the result
     */
    public static Integer toInteger(final Object object)
    {
        if (object instanceof Character)
        {
            return Integer.valueOf(((Character) object).charValue());
        }
        if (object instanceof Boolean)
        {
            if (((Boolean) object).booleanValue())
            {
                return Integer.valueOf(1);
            }
            return Integer.valueOf(0);
        }
        return Integer.valueOf(((Number) object).intValue());
    }
}
