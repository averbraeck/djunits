package org.djunits.value.vfloat.vector;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.djunits.unit.Unit;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;

/**
 * DOUBLEVECTOR for creating different test vectors.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class FLOATVECTOR
{
    /** */
    private FLOATVECTOR()
    {
        // Utility class
    }

    /**
     * Return an array with all values != 0.
     * @param size the number of cells
     * @return an array with all nonzero values
     */
    public static float[] denseArray(final int size)
    {
        float[] array = new float[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i + 1.0f;
        }
        return array;
    }

    /**
     * Return an array with nonzero values in every 10th place.
     * @param size the number of cells
     * @return an array with only nonzero values on the diagonal
     */
    public static float[] sparseArray(final int size)
    {
        float[] array = new float[size];
        float v = 1.0f;
        for (int i = 0; i < size; i++)
        {
            array[i] = (i % 10 == 0) ? v++ : 0.0f;
        }
        return array;
    }

    /**
     * Return a scalar array with all values != 0.
     * @param size the number of cells
     * @param scalarClass the class of scalars to use
     * @return an array with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> S[] denseScalarArray(final int size,
            final Class<S> scalarClass)
    {
        try
        {
            S[] array = (S[]) Array.newInstance(scalarClass, size);
            Method instantiateSI = scalarClass.getMethod("instantiateSI", new Class<?>[] { float.class });
            for (int i = 0; i < size; i++)
            {
                array[i] = (S) instantiateSI.invoke(null, i + 1.0f);
            }
            return array;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a scalar array with nonzero values in every 10th place.
     * @param size the number of cells
     * @param scalarClass the class of scalars to use
     * @return an array with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> S[] sparseScalarArray(final int size,
            final Class<S> scalarClass)
    {
        try
        {
            S[] array = (S[]) Array.newInstance(scalarClass, size);
            Method instantiateSI = scalarClass.getMethod("instantiateSI", new Class<?>[] { float.class });
            float v = 1.0f;
            for (int i = 0; i < size; i++)
            {
                array[i] = (S) instantiateSI.invoke(null, (i % 10 == 0) ? v++ : 0.0f);
            }
            return array;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
