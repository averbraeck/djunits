package org.djunits.value.vdouble.matrix;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.djunits.unit.Unit;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;

/**
 * DOUBLEMATRIX for creating different test matrices. Matrix values from a double array are stored as m[row][column].
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class DOUBLEMATRIX
{
    /** */
    private DOUBLEMATRIX()
    {
        // Utility class
    }

    /**
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @return an array with all nonzero values
     */
    public static double[][] denseRectArrays(final int rows, final int cols)
    {
        double[][] array = new double[rows][];
        for (int i = 0; i < rows; i++)
        {
            double[] r = new double[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = cols * i + j + 1.0;
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
    public static double[][] sparseRectArrays(final int rows, final int cols)
    {
        double[][] array = new double[rows][];
        for (int i = 0; i < rows; i++)
        {
            double[] r = new double[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = (i == j) ? i + 1 : 0.0;
            }
        }
        return array;
    }

    /**
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @return an array with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>> S[][] denseRectScalarArrays(final int rows,
            final int cols, final Class<S> scalarClass)
    {
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Method instantiateSI = scalarClass.getMethod("instantiateSI", new Class<?>[] { double.class });
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    array[i][j] = (S) instantiateSI.invoke(null, cols * i + j + 1.0);
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
     * @return an array with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>> S[][] sparseRectScalarArrays(final int rows,
            final int cols, final Class<S> scalarClass)
    {
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Method instantiateSI = scalarClass.getMethod("instantiateSI", new Class<?>[] { double.class });
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    array[i][j] = (i == j) ? (S) instantiateSI.invoke(null, i + 1) : (S) instantiateSI.invoke(null, 0.0);
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
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @return an array with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>,
            S extends DoubleScalarInterface<U, S>> Collection<DoubleSparseValue<U, S>> denseRectTuples(final int rows,
                    final int cols, final Class<S> scalarClass)
    {
        try
        {
            List<DoubleSparseValue<U, S>> matrixList = new ArrayList<>();
            Method instantiateSI = scalarClass.getMethod("instantiateSI", new Class<?>[] { double.class });
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    S v = (S) instantiateSI.invoke(null, cols * i + j + 1.0);
                    DoubleSparseValue<U, S> dsv = new DoubleSparseValue<U, S>(i, j, v);
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
     * Return a rectangular array with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @return an array with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public static <U extends Unit<U>,
            S extends DoubleScalarInterface<U, S>> Collection<DoubleSparseValue<U, S>> sparseRectTuples(final int rows,
                    final int cols, final Class<S> scalarClass)
    {
        try
        {
            List<DoubleSparseValue<U, S>> matrixList = new ArrayList<>();
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    if (i == j)
                    {
                        double v = i + 1;
                        DoubleSparseValue<U, S> dsv = new DoubleSparseValue<U, S>(i, j, v);
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
