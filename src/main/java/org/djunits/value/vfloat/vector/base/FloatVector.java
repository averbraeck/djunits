package org.djunits.value.vfloat.vector.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;
import org.djunits.value.vfloat.vector.FloatSIVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * FloatVector utility methods, e.g., for creating FloatVectors from different types of data.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class FloatVector
{
    /** The cache to make the lookup of the constructor for a Immutable Vector belonging to a unit faster. */
    private static final Map<Unit<?>, Constructor<? extends FloatVectorInterface<?, ?, ?>>> CACHE_DATA = new HashMap<>();

    /** Do not instantiate. */
    private FloatVector()
    {
        // Utility class.
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valuesInUnit float[]; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final float[] valuesInUnit, final U unit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valuesInUnit, unit.getScale(), storageType), unit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valuesInUnit float[]; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final float[] valuesInUnit, final U unit,
                    final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valuesInUnit, unit.getScale(), storageType), unit, vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valuesSI float[]; the values in the SI unit
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final float[] valuesSI, final U displayUnit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valuesSI, IdentityScale.SCALE, storageType), displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valuesSI float[]; the values in the SI unit
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final float[] valuesSI, final U displayUnit,
                    final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valuesSI, IdentityScale.SCALE, storageType), displayUnit,
                vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param values S[]; the values
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final S[] values, final U displayUnit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(values, storageType), displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param values S[]; the values
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final S[] values, final U displayUnit,
                    final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(values, storageType), displayUnit, vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueListInUnit List&lt;Float&gt;; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @throws ValueRuntimeException on vector init error
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final List<Float> valueListInUnit, final U unit,
                    final StorageType storageType) throws ValueRuntimeException
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueListInUnit, unit.getScale(), storageType), unit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueListInUnit List&lt;Float&gt;; the values in the given unit
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @throws ValueRuntimeException on vector init error
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final List<Float> valueListInUnit, final U unit,
                    final StorageType storageType, final Class<V> vectorClass) throws ValueRuntimeException
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueListInUnit, unit.getScale(), storageType), unit,
                vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueListSI List&lt;Float&gt;; the values in the SI unit
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @throws ValueRuntimeException on vector init error
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final List<Float> valueListSI, final U displayUnit,
                    final StorageType storageType) throws ValueRuntimeException
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueListSI, IdentityScale.SCALE, storageType), displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueListSI List&lt;Float&gt;; the values in the SI unit
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @throws ValueRuntimeException on vector init error
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final List<Float> valueListSI, final U displayUnit,
                    final StorageType storageType, final Class<V> vectorClass) throws ValueRuntimeException
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueListSI, IdentityScale.SCALE, storageType), displayUnit,
                vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueList List&lt;S&gt;; the value list
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateList(final List<S> valueList, final U displayUnit,
                    final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiateList(valueList, storageType), displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueList List&lt;S&gt;; the value list
     * @param displayUnit U; the unit in which the values will be displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateList(final List<S> valueList, final U displayUnit,
                    final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiateList(valueList, storageType), displayUnit, vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueMapInUnit SortedMap&lt;Integer, Float&gt;; the values in the given unit
     * @param length int; the size of the vector
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final SortedMap<Integer, Float> valueMapInUnit,
                    final int length, final U unit, final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueMapInUnit, length, unit.getScale(), storageType), unit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueMapInUnit SortedMap&lt;Integer, Float&gt;; the values in the given unit
     * @param length int; the size of the vector
     * @param unit U; the unit in which the values are expressed and displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final SortedMap<Integer, Float> valueMapInUnit,
                    final int length, final U unit, final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueMapInUnit, length, unit.getScale(), storageType), unit,
                vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueMapSI SortedMap&lt;Integer, Float&gt;; the values in the SI unit
     * @param length int; the size of the vector
     * @param displayUnit U; the unit in which the values are displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final SortedMap<Integer, Float> valueMapSI,
                    final int length, final U displayUnit, final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueMapSI, length, IdentityScale.SCALE, storageType),
                displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueMapSI SortedMap&lt;Integer, Float&gt;; the values in the SI unit
     * @param length int; the size of the vector
     * @param displayUnit U; the unit in which the values are displayed
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateSI(final SortedMap<Integer, Float> valueMapSI,
                    final int length, final U displayUnit, final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiate(valueMapSI, length, IdentityScale.SCALE, storageType),
                displayUnit, vectorClass);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler.
     * @param valueMap SortedMap&lt;Integer, S&gt;; the value map
     * @param displayUnit U; the unit in which the values will be displayed
     * @param length int; the size of the vector
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateMap(final SortedMap<Integer, S> valueMap, final int length,
                    final U displayUnit, final StorageType storageType)
    {
        return instantiateAnonymous(FloatVectorData.instantiateMap(valueMap, length, storageType), displayUnit);
    }

    /**
     * Instantiate the FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param valueMap SortedMap&lt;Integer, S&gt;; the value map
     * @param displayUnit U; the unit in which the values will be displayed
     * @param length int; the size of the vector
     * @param storageType StorageType; whether the vector is SPARSE or DENSE
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateMap(final SortedMap<Integer, S> valueMap, final int length,
                    final U displayUnit, final StorageType storageType, final Class<V> vectorClass)
    {
        return instantiateAnonymous(FloatVectorData.instantiateMap(valueMap, length, storageType), displayUnit, vectorClass);
    }

    /**
     * Instantiate the Mutable FloatVector based on its unit. Rigid check on types for the compiler.
     * @param values FloatVectorData; the values
     * @param unit U; the unit in which the values are expressed
     * @return V; an instantiated mutable FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final FloatVectorData values, final U unit)
    {
        return instantiateAnonymous(values, unit);
    }

    /**
     * Instantiate the Mutable FloatVector based on its unit. Rigid check on types for the compiler. The class for the vector is
     * explicitly provided, e.g., for user-defined vector classes.
     * @param values FloatVectorData; the values
     * @param unit U; the unit in which the values are expressed
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated mutable FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiate(final FloatVectorData values, final U unit,
                    final Class<V> vectorClass)
    {
        return instantiateAnonymous(values, unit, vectorClass);
    }

    /**
     * Instantiate the Immutable FloatVector based on its unit. Loose check for types on the compiler. This allows the unit to
     * be specified as a Unit&lt;?&gt; type.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param values FloatVectorData; the values
     * @param unit Unit&lt;?&gt;; the unit in which the values are expressed
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateAnonymous(final FloatVectorData values, final Unit<?> unit)
    {
        try
        {
            Constructor<? extends FloatVectorInterface<?, ?, ?>> vectorConstructor = CACHE_DATA.get(unit);
            if (vectorConstructor == null)
            {
                if (!unit.getClass().getSimpleName().endsWith("Unit"))
                {
                    throw new ClassNotFoundException("Unit " + unit.getClass().getSimpleName()
                            + " name does not end with 'Unit'. Cannot find corresponding vector");
                }
                Class<? extends FloatVectorInterface<?, ?, ?>> vectorClass;
                if (unit instanceof SIUnit)
                {
                    vectorClass = FloatSIVector.class;
                }
                else
                {
                    vectorClass = (Class<FloatVectorInterface<?, ?, ?>>) Class.forName("org.djunits.value.vfloat.vector.Float"
                            + unit.getClass().getSimpleName().replace("Unit", "") + "Vector");
                }
                vectorConstructor = vectorClass.getDeclaredConstructor(FloatVectorData.class, unit.getClass());
                CACHE_DATA.put(unit, vectorConstructor);
            }
            return (V) vectorConstructor.newInstance(values, unit);
        }
        catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassNotFoundException | NoSuchMethodException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate FloatVectorInterface of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }

    /**
     * Instantiate the Immutable FloatVector based on its unit. Loose check for types on the compiler. This allows the unit to
     * be specified as a Unit&lt;?&gt; type. The class for the vector is explicitly provided, e.g., for user-defined vector
     * classes.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param values FloatVectorData; the values
     * @param unit Unit&lt;?&gt;; the unit in which the values are expressed
     * @param vectorClass Class&lt;V&gt;; the class of the vector to instantiate
     * @return V; an instantiated FloatVector with the values expressed in their unit
     * @param <U> the unit type
     * @param <S> the corresponding scalar type
     * @param <V> the corresponding vector type
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>,
            V extends FloatVectorInterface<U, S, V>> V instantiateAnonymous(final FloatVectorData values, final Unit<?> unit,
                    final Class<V> vectorClass)
    {
        try
        {
            Constructor<? extends FloatVectorInterface<?, ?, ?>> vectorConstructor = CACHE_DATA.get(unit);
            if (vectorConstructor == null)
            {
                vectorConstructor = vectorClass.getDeclaredConstructor(FloatVectorData.class, unit.getClass());
                CACHE_DATA.put(unit, vectorConstructor);
            }
            return (V) vectorConstructor.newInstance(values, unit);
        }
        catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate FloatVectorInterface of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }

}
