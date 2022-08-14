package org.djunits.value.vfloat.matrix;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.scalar.*;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.vector.*;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djunits.value.vfloat.matrix.*;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;

/**
 * Easy access methods for the generic Relative SI FloatMatrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class FloatSIMatrix extends AbstractFloatMatrixRel<SIUnit, FloatSIScalar, FloatSIVector, FloatSIMatrix>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct a new Relative Float FloatSIMatrix.
     * @param values float[][]; the values of the entries in the new Relative Float FloatSIMatrix
     * @param unit SIUnit; the unit of the new Relative Float FloatSIMatrix
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return FloatSIMatrix; the FloatSIMatrix of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static FloatSIMatrix instantiate(final float[][] values, final SIUnit unit, final StorageType storageType)
            throws ValueRuntimeException
    {
        return new FloatSIMatrix(FloatMatrixData.instantiate(values, unit.getScale(), storageType), unit);
    }

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit SIUnit; the unit
     */
    public FloatSIMatrix(final FloatMatrixData data, final SIUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSIScalar> getScalarClass()
    {
        return FloatSIScalar.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSIVector> getVectorClass()
    {
        return FloatSIVector.class;
    }

    /**
     * Returns an FloatSIMatrix based on an array of values and the textual representation of the unit.
     * @param values float[][]; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return FloatSIMatrix; the matrix representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSIMatrix of(final float[][] values, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(values, "Error parsing FloatSIMatrix: value is null");
        Throw.whenNull(unitString, "Error parsing FloatSIMatrix: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatSIMatrix: empty unitString");
        Throw.whenNull(storageType, "Error parsing FloatSIMatrix: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return FloatSIMatrix.instantiate(values, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing FloatSIMatrix with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIMatrix instantiateMatrix(final FloatMatrixData fmd, final SIUnit unit)
    {
        return new FloatSIMatrix(fmd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIVector instantiateVector(final FloatVectorData fvd, final SIUnit unit)
    {
        return new FloatSIVector(fvd, unit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar instantiateScalarSI(final float valueSI, final SIUnit unit)
    {
        return new FloatSIScalar(valueSI, unit);
    }

    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current matrix transformed to a matrix in the given unit. Of course the SI dimensionality has to match,
     * otherwise the matrix cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the matrix needs to be expressed
     * @return M; the matrix that has been transformed into the right matrix type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    public final <U extends Unit<U>, S extends AbstractFloatScalarRel<U, S>,
            V extends AbstractFloatVectorRel<U, S, V>, M extends AbstractFloatMatrixRel<U, S, V, M>> M as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "FloatSIMatrix with unit %s cannot be converted to a FloatMatrix with unit %s",
                getDisplayUnit(), displayUnit);
        M result = FloatMatrix.instantiate(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %%ASMETHODS%%
}
