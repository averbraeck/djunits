package org.djunits.value.vdouble.matrix.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.djunits.Throw;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.SIMatrix;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.djunits.value.vdouble.vector.base.DoubleVectorInterface;

/**
 * DoubleMatrix utility methods, e.g., for creating DoubleMatrixs from different types of data.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class DoubleMatrix
{
    /** The cache to make the lookup of the constructor for a Immutable Matrix belonging to a unit faster. */
    private static final Map<Unit<?>, Constructor<? extends DoubleMatrixInterface<?, ?, ?, ?>>> CACHE_DATA = new HashMap<>();

    /** Do not instantiate. */
    private DoubleMatrix()
    {
        // Utility class.
    }

    /**
     * Instantiate the DoubleMatrix based on its unit. Rigid check on types for the compiler. The double array is of the form
     * d[rows][columns] so each value can be found with d[row][column].
     * @param valuesInUnit double[][]; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the matrix is SPARSE or DENSE
     * @return M; an instantiated DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final double[][] valuesInUnit, final U unit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(DoubleMatrixData.instantiate(valuesInUnit, unit.getScale(), storageType), unit);
    }

    /**
     * Instantiate the DoubleMatrix based on its unit. Rigid check on types for the compiler. The class for the matrix is
     * explicitly provided, e.g., for user-defined matrix classes. The double array is of the form d[rows][columns] so each
     * value can be found with d[row][column].
     * @param valuesInUnit double[][]; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the matrix is SPARSE or DENSE
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final double[][] valuesInUnit, final U unit,
                    final StorageType storageType, final Class<M> matrixClass)
    {
        return instantiateAnonymous(DoubleMatrixData.instantiate(valuesInUnit, unit.getScale(), storageType), unit,
                matrixClass);
    }

    /**
     * Instantiate the DoubleMatrix based on its unit. Rigid check on types for the compiler. The double array is of the form
     * d[rows][columns] so each value can be found with d[row][column].
     * @param valuesSI double[][]; the values in the SI unit.
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the matrix is SPARSE or DENSE
     * @return M; an instantiated DoubleMatrix with the SI values and display unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiateSI(final double[][] valuesSI, final U displayUnit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(DoubleMatrixData.instantiate(valuesSI, IdentityScale.SCALE, storageType), displayUnit);
    }

    /**
     * Instantiate the DoubleMatrix based on its unit. Rigid check on types for the compiler. The class for the matrix is
     * explicitly provided, e.g., for user-defined matrix classes. The double array is of the form d[rows][columns] so each
     * value can be found with d[row][column].
     * @param valuesSI double[][]; the values in the SI unit
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the matrix is SPARSE or DENSE
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated DoubleMatrix with the SI values and display unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiateSI(final double[][] valuesSI, final U displayUnit,
                    final StorageType storageType, final Class<M> matrixClass)
    {
        return instantiateAnonymous(DoubleMatrixData.instantiate(valuesSI, IdentityScale.SCALE, storageType), displayUnit,
                matrixClass);
    }

    /**
     * Instantiate the Mutable DoubleMatrix based on its unit. Rigid check on types for the compiler.
     * @param values DoubleMatrixData; the values
     * @param unit U; the unit in which the values are expressed
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final DoubleMatrixData values, final U unit)
    {
        return instantiateAnonymous(values, unit);
    }

    /**
     * Instantiate the Mutable DoubleMatrix based on its unit. Rigid check on types for the compiler. The class for the matrix
     * is explicitly provided, e.g., for user-defined matrix classes.
     * @param values DoubleMatrixData; the values
     * @param unit U; the unit in which the values are expressed
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final DoubleMatrixData values, final U unit,
                    final Class<M> matrixClass)
    {
        return instantiateAnonymous(values, unit, matrixClass);
    }

    /**
     * Construct a new Relative Immutable Double Matrix. Rigid check on types for the compiler. The scalar array is of the form
     * s[rows][columns] so each value can be found with s[row][column].
     * @param values S[][]; the values of the entries in the new Relative Immutable Double Matrix
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final S[][] values, final U displayUnit,
                    final StorageType storageType)
    {
        return instantiate(DoubleMatrixData.instantiate(values, storageType), displayUnit);
    }

    /**
     * Construct a new Relative Immutable Double Matrix. Rigid check on types for the compiler. The class for the matrix is
     * explicitly provided, e.g., for user-defined matrix classes. The scalar array is of the form s[rows][columns] so each
     * value can be found with s[row][column].
     * @param values S[][]; the values of the entries in the new Relative Immutable Double Matrix
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final S[][] values, final U displayUnit,
                    final StorageType storageType, final Class<M> matrixClass)
    {
        return instantiate(DoubleMatrixData.instantiate(values, storageType), displayUnit, matrixClass);
    }

    /**
     * Construct a new Relative Immutable Double Matrix. Rigid check on types for the compiler.
     * @param values Collection&lt;DoubleSparseValue&lt;U, S&gt;&gt;; the (sparse [X, Y, SI]) values to store
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final Collection<DoubleSparseValue<U, S>> values,
                    final int rows, final int cols, final U displayUnit, final StorageType storageType)
    {
        return instantiate(DoubleMatrixData.instantiate(values, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a new Relative Immutable Double Matrix. Rigid check on types for the compiler. The class for the matrix is
     * explicitly provided, e.g., for user-defined matrix classes.
     * @param values Collection&lt;DoubleSparseValue&lt;U, S&gt;&gt;; the (sparse [X, Y, SI]) values to store
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated mutable DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiate(final Collection<DoubleSparseValue<U, S>> values,
                    final int rows, final int cols, final U displayUnit, final StorageType storageType,
                    final Class<M> matrixClass)
    {
        return instantiate(DoubleMatrixData.instantiate(values, rows, cols, storageType), displayUnit, matrixClass);
    }

    /**
     * Instantiate the Immutable DoubleMatrix based on its unit. Loose check for types on the compiler. This allows the unit to
     * be specified as a Unit&lt;?&gt; type.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param values DoubleMatrixData; the values
     * @param unit Unit&lt;?&gt;; the unit in which the values are expressed
     * @return M; an instantiated DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiateAnonymous(final DoubleMatrixData values,
                    final Unit<?> unit)
    {
        Throw.whenNull(values, "data values cannot be null");
        Throw.whenNull(unit, "unit cannot be null");
        try
        {
            Constructor<? extends DoubleMatrixInterface<?, ?, ?, ?>> matrixConstructor = CACHE_DATA.get(unit);
            if (matrixConstructor == null)
            {
                if (!unit.getClass().getSimpleName().endsWith("Unit"))
                {
                    throw new ClassNotFoundException("Unit " + unit.getClass().getSimpleName()
                            + " name does not end with 'Unit'. Cannot find corresponding matrix");
                }
                Class<? extends DoubleMatrixInterface<?, ?, ?, ?>> matrixClass;
                if (unit instanceof SIUnit)
                {
                    matrixClass = SIMatrix.class;
                }
                else
                {
                    matrixClass = (Class<DoubleMatrixInterface<?, ?, ?, ?>>) Class.forName("org.djunits.value.vdouble.matrix."
                            + unit.getClass().getSimpleName().replace("Unit", "") + "Matrix");
                }
                matrixConstructor = matrixClass.getDeclaredConstructor(DoubleMatrixData.class, unit.getClass());
                CACHE_DATA.put(unit, matrixConstructor);
            }
            return (M) matrixConstructor.newInstance(values, unit);
        }
        catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassNotFoundException | NoSuchMethodException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate DoubleMatrixInterface of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }

    /**
     * Instantiate the Immutable DoubleMatrix based on its unit. Loose check for types on the compiler. This allows the unit to
     * be specified as a Unit&lt;?&gt; type. The class for the matrix is explicitly provided, e.g., for user-defined matrix
     * classes.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param values DoubleMatrixData; the values
     * @param unit Unit&lt;?&gt;; the unit in which the values are expressed
     * @param matrixClass Class&lt;M&gt;; the class of the matrix to instantiate
     * @return M; an instantiated DoubleMatrix with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     * @param <M> the corresponding matrix type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends DoubleScalarInterface<U, S>, V extends DoubleVectorInterface<U, S, V>,
            M extends DoubleMatrixInterface<U, S, V, M>> M instantiateAnonymous(final DoubleMatrixData values,
                    final Unit<?> unit, final Class<M> matrixClass)
    {
        Throw.whenNull(values, "data values cannot be null");
        Throw.whenNull(unit, "unit cannot be null");
        Throw.whenNull(matrixClass, "matrix class cannot be null");
        try
        {
            Constructor<? extends DoubleMatrixInterface<?, ?, ?, ?>> matrixConstructor = CACHE_DATA.get(unit);
            if (matrixConstructor == null)
            {
                matrixConstructor = matrixClass.getDeclaredConstructor(DoubleMatrixData.class, unit.getClass());
                CACHE_DATA.put(unit, matrixConstructor);
            }
            return (M) matrixConstructor.newInstance(values, unit);
        }
        catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate DoubleMatrixInterface of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }
}
