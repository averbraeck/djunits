package org.djunits.value.vfloat.matrix;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.djunits.unit.Unit;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * FLOATMATRIX for creating different test matrices. Matrix values from a float array are stored as m[row][column].
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class FLOATMATRIX
{
    /** */
    private FLOATMATRIX()
    {
        // Utility class
    }

    /**
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @return an array with all nonzero values
     */
    public static float[][] denseRectArrays(final int rows, final int cols)
    {
        float[][] array = new float[rows][];
        for (int i = 0; i < rows; i++)
        {
            float[] r = new float[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = cols * i + j + 1.0f;
            }
        }
        return array;
    }

    /**
     * Return a rectangular array with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @return an array with only nonzero values on the diagonal
     */
    public static float[][] sparseRectArrays(final int rows, final int cols)
    {
        float[][] array = new float[rows][];
        for (int i = 0; i < rows; i++)
        {
            float[] r = new float[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = (i == j) ? i + 1 : 0.0f;
            }
        }
        return array;
    }

    /**
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U; the unit to use for construction
     * @return an array with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalar<U, S>> S[][] denseRectScalarArrays(final int rows, final int cols,
            final Class<S> scalarClass, final U unit)
    {
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Constructor<S> instantiateSI = scalarClass.getConstructor(new Class<?>[] {float.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    array[r][c] = (S) instantiateSI.newInstance((float) (cols * r + c + 1.0f), unit);
                }
            }
            return array;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a rectangular array with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U; the unit to use for construction
     * @return an array with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalar<U, S>> S[][] sparseRectScalarArrays(final int rows, final int cols,
            final Class<S> scalarClass, final U unit)
    {
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Constructor<S> instantiateSI = scalarClass.getConstructor(new Class<?>[] {float.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    array[r][c] = (r == c) ? (S) instantiateSI.newInstance((float) (r + 1), unit)
                            : (S) instantiateSI.newInstance(0.0f, unit);
                }
            }
            return array;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a collection of tuples with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U the unit
     * @return a collection with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public static <U extends Unit<U>, S extends FloatScalar<U, S>> Collection<FloatSparseValue<U, S>> denseRectTuples(
            final int rows, final int cols, final Class<S> scalarClass, final U unit)
    {
        try
        {
            List<FloatSparseValue<U, S>> matrixList = new ArrayList<>();
            Constructor<S> instantiateSI = scalarClass.getConstructor(new Class<?>[] {float.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    S v = (S) instantiateSI.newInstance((float) (cols * r + c + 1.0f), unit);
                    FloatSparseValue<U, S> dsv = new FloatSparseValue<U, S>(r, c, v);
                    matrixList.add(dsv);
                }
            }
            return matrixList;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a collection of tuples with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U the unit
     * @return a collection with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public static <U extends Unit<U>, S extends FloatScalar<U, S>> Collection<FloatSparseValue<U, S>> sparseRectTuples(
            final int rows, final int cols, final Class<S> scalarClass, final U unit)
    {
        try
        {
            List<FloatSparseValue<U, S>> matrixList = new ArrayList<>();
            Constructor<S> instantiateSI = scalarClass.getConstructor(new Class<?>[] {float.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    if (r == c)
                    {
                        S v = (S) instantiateSI.newInstance((float) (r + 1), unit);
                        FloatSparseValue<U, S> dsv = new FloatSparseValue<U, S>(r, c, v);
                        matrixList.add(dsv);
                    }
                }
            }
            return matrixList;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
