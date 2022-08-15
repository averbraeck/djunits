package org.djunits.value.vdouble.matrix;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.*;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.*;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.*;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.*;
import org.djunits.value.vdouble.vector.SIVector;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Easy access methods for the generic Relative SI DoubleMatrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class SIMatrix extends AbstractDoubleMatrixRel<SIUnit, SIScalar, SIVector, SIMatrix>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct a new Relative Double SIMatrix.
     * @param values double[][]; the values of the entries in the new Relative Double SIMatrix
     * @param unit SIUnit; the unit of the new Relative Double SIMatrix
     * @param storageType StorageType; the data type to use (e.g., DENSE or SPARSE)
     * @return SIMatrix; the SIMatrix of the given unit
     * @throws ValueRuntimeException when values is null
     */
    public static SIMatrix instantiate(final double[][] values, final SIUnit unit, final StorageType storageType)
            throws ValueRuntimeException
    {
        return new SIMatrix(DoubleMatrixData.instantiate(values, unit.getScale(), storageType), unit);
    }

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit SIUnit; the unit
     */
    public SIMatrix(final DoubleMatrixData data, final SIUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<SIScalar> getScalarClass()
    {
        return SIScalar.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<SIVector> getVectorClass()
    {
        return SIVector.class;
    }

    /**
     * Returns an SIMatrix based on an array of values and the textual representation of the unit.
     * @param values double[][]; the values to use
     * @param unitString String; the textual representation of the unit
     * @param storageType StorageType; the storage type to use
     * @return SIMatrix; the matrix representation of the values in their unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIMatrix of(final double[][] values, final String unitString, final StorageType storageType)
    {
        Throw.whenNull(values, "Error parsing SIMatrix: value is null");
        Throw.whenNull(unitString, "Error parsing SIMatrix: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIMatrix: empty unitString");
        Throw.whenNull(storageType, "Error parsing SIMatrix: storageType is null");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return SIMatrix.instantiate(values, unit, storageType);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIMatrix with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public SIMatrix instantiateMatrix(final DoubleMatrixData dmd, final SIUnit unit)
    {
        return new SIMatrix(dmd, unit);
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
     * Return the current matrix transformed to a matrix in the given unit. Of course the SI dimensionality has to match,
     * otherwise the matrix cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the matrix needs to be expressed
     * @return M; the matrix that has been transformed into the right matrix type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    public final <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>,
            V extends AbstractDoubleVectorRel<U, S, V>, M extends AbstractDoubleMatrixRel<U, S, V, M>> M as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "SIMatrix with unit %s cannot be converted to a matrix with unit %s",
                getDisplayUnit(), displayUnit);
        M result = DoubleMatrix.instantiate(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %%ASMETHODS%%
}
