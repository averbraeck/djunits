package org.djunits.value.vdouble.matrix;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.djunits.unit.Unit;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * DOUBLEMATRIX for creating different test matrices. Matrix values from a double array are stored as m[row][column].
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
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
     * @param random whether values will contain a random element or are fully predictable
     * @return an array with all nonzero values
     */
    public static double[][] denseRectArrays(final int rows, final int cols, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        double[][] array = new double[rows][];
        for (int i = 0; i < rows; i++)
        {
            double[] r = new double[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = cols * i + j + 1.0 + rand.nextDouble();
            }
        }
        return array;
    }

    /**
     * Return a rectangular array with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param random whether values will contain a random element or are fully predictable
     * @return an array with only nonzero values on the diagonal
     */
    public static double[][] sparseRectArrays(final int rows, final int cols, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        double[][] array = new double[rows][];
        for (int i = 0; i < rows; i++)
        {
            double[] r = new double[cols];
            array[i] = r;
            for (int j = 0; j < cols; j++)
            {
                r[j] = (i == j) ? i + 1 + rand.nextDouble() : 0.0;
            }
        }
        return array;
    }

    /**
     * Return a rectangular array with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit the unit to use for construction
     * @param random whether values will contain a random element or are fully predictable
     * @return an array with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> S[][] denseRectScalarArrays(final int rows, final int cols,
            final Class<S> scalarClass, final U unit, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Constructor<S> ofSI = scalarClass.getConstructor(new Class<?>[] {double.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    array[r][c] = (S) ofSI.newInstance(cols * r + c + 1.0 + rand.nextDouble(), unit);
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
     * @param unit the unit to use for construction
     * @param random whether values will contain a random element or are fully predictable
     * @return an array with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> S[][] sparseRectScalarArrays(final int rows, final int cols,
            final Class<S> scalarClass, final U unit, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        try
        {
            S[][] array = (S[][]) Array.newInstance(scalarClass, rows, cols);
            Constructor<S> ofSI = scalarClass.getConstructor(new Class<?>[] {double.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    array[r][c] =
                            (r == c) ? (S) ofSI.newInstance(r + 1 + rand.nextDouble(), unit) : (S) ofSI.newInstance(0.0, unit);
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
     * @param random whether values will contain a random element or are fully predictable
     * @return a collection with all nonzero values
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> Collection<DoubleSparseValue<U, S>> denseRectTuples(
            final int rows, final int cols, final Class<S> scalarClass, final U unit, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        try
        {
            List<DoubleSparseValue<U, S>> matrixList = new ArrayList<>();
            Constructor<S> ofSI = scalarClass.getConstructor(new Class<?>[] {double.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    S v = (S) ofSI.newInstance(cols * r + c + 1.0 + rand.nextDouble(), unit);
                    DoubleSparseValue<U, S> dsv = new DoubleSparseValue<U, S>(r, c, v);
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
     * Return a collection of tuples with all values != 0.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U the unit
     * @param random whether values will contain a random element or are fully predictable
     * @return a collection with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <U extends Unit<U>,
            S extends DoubleScalar<U, S>> Collection<DoubleSparseValue<?, ?>> denseRectTuplesAnonymous(final int rows,
                    final int cols, final Class<?> scalarClass, final Unit<?> unit, final boolean random)
    {
        return (Collection) denseRectTuples(rows, cols, (Class<S>) scalarClass, (U) unit, random);
    }

    /**
     * Return a collection of tuples with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U the unit
     * @param random whether values will contain a random element or are fully predictable
     * @return a collection with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> Collection<DoubleSparseValue<U, S>> sparseRectTuples(
            final int rows, final int cols, final Class<S> scalarClass, final U unit, final boolean random)
    {
        Random rand = random ? new Random(222) : new Rand0();
        try
        {
            List<DoubleSparseValue<U, S>> matrixList = new ArrayList<>();
            Constructor<S> ofSI = scalarClass.getConstructor(new Class<?>[] {double.class, unit.getClass()});
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    if (r == c)
                    {
                        S v = (S) ofSI.newInstance(r + 1 + rand.nextDouble(), unit);
                        DoubleSparseValue<U, S> dsv = new DoubleSparseValue<U, S>(r, c, v);
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

    /**
     * Return a collection of tuples with only nonzero values on the diagonal.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param scalarClass the class of scalars to use
     * @param unit U the unit
     * @param random whether values will contain a random element or are fully predictable
     * @return a collection with only nonzero values on the diagonal
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <U extends Unit<U>,
            S extends DoubleScalar<U, S>> Collection<DoubleSparseValue<?, ?>> sparseRectTuplesAnonymous(final int rows,
                    final int cols, final Class<?> scalarClass, final Unit<?> unit, final boolean random)
    {
        return (Collection) sparseRectTuples(rows, cols, (Class<S>) scalarClass, (U) unit, random);
    }

    /** */
    static class Rand0 extends Random
    {
        /** */
        private static final long serialVersionUID = 1L;

        @Override
        public double nextDouble()
        {
            return 0.0;
        }
    }
}
