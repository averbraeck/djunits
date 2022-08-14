package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.*;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.*;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Easy access methods for the generic Relative SI DoubleVector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class SIVector extends AbstractDoubleVectorRel<SIUnit, SIScalar, SIVector>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct a new Relative Double SIVector.
     * @param values double[]; the values of the entries in the new Relative Double SIVector
     * @param unit SIUnit; the unit of the new Relative Double SIVector
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return SIVector; the SIVector of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static SIVector instantiate(final double[] values, final SIUnit unit, final StorageType storageType)
            throws ValueRuntimeException
    {
        return new SIVector(DoubleVectorData.instantiate(values, unit.getScale(), storageType), unit);
    }

    /**
     * Construct a new Relative Double SIVector.
     * @param values List&lt;Double&gt;; the values of the entries in the new Relative Double SIVector
     * @param unit SIUnit; the unit of the new Relative Double SIVector
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return SIVector; the SIVector of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static SIVector instantiate(final List<Double> values, final SIUnit unit, final StorageType storageType)
            throws ValueRuntimeException
    {
        return new SIVector(DoubleVectorData.instantiate(values, unit.getScale(), storageType), unit);
    }

    /**
     * Construct a new Relative Double SIVector.
     * @param values SortedMap&lt;Integer, Double&gt;; the map of indexes to values of the Relative Sparse Double
     *            SIVector
     * @param length int; the size of the vector
     * @param unit SIUnit; the unit of the new Relative Sparse Double SIVector
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return SIVector; the SIVector of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static SIVector instantiate(final SortedMap<Integer, Double> values, final int length, final SIUnit unit,
            final StorageType storageType) throws ValueRuntimeException
    {
        return new SIVector(DoubleVectorData.instantiate(values, length, unit.getScale(), storageType), unit);
    }

    /**
     * @param data DoubleVectorData; an internal data object
     * @param unit SIUnit; the unit
     */
    public SIVector(final DoubleVectorData data, final SIUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<SIScalar> getScalarClass()
    {
        return SIScalar.class;
    }

    /**
     * Returns an SIVector based on an array of values and the textual representation of the unit.
     * @param value double[]; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return SIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final double[] value, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(value, "Error parsing SIVector: value is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIVector: empty unitString");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return SIVector.instantiate(value, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    /**
     * Returns an SIVector based on an array of values and the textual representation of the unit.
     * @param valueList List&lt;Double&gt;; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return SIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final List<Double> valueList, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(valueList, "Error parsing SIVector: valueList is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIVector: empty unitString");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return SIVector.instantiate(valueList, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    /**
     * Returns an SIVector based on a (sparse) map of values and the textual representation of the unit.
     * @param valueMap SortedMap&lt;Integer, Double&gt;; the values to use
     * @param unitString String; the textual representation of the unit
     * @param length int; the size of the vector
     * @param storageType StorageType; the storage type to use
     * @return SIVector; the vector representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIVector of(final SortedMap<Integer, Double> valueMap, final String unitString, final int length,
            final StorageType storageType)
    {
        Throw.whenNull(valueMap, "Error parsing SIVector: valueMap is null");
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIVector: empty unitString");
        Throw.whenNull(storageType, "Error parsing SIVector: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return SIVector.instantiate(valueMap, length, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public SIVector instantiateVector(final DoubleVectorData dvd, final SIUnit unit)
    {
        return new SIVector(dvd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar instantiateScalarSI(final double valueSI, final SIUnit unit)
    {
        return new SIScalar(valueSI, unit);
    }
    
    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current vector transformed to a vector in the given unit. Of course the SI dimensionality has to match,
     * otherwise the vector cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the vector needs to be expressed
     * @return V; the vector that has been transformed into the right vector type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     */
    public final <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>,
            V extends AbstractDoubleVectorRel<U, S, V>> V as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "SIVector with unit %s cannot be converted to a vector with unit %s",
                getDisplayUnit(), displayUnit);
        V result = DoubleVector.instantiate(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %%ASMETHODS%%
}
