package org.djunits.value.vfloat.matrix;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.value.vfloat.matrix.base.*;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.*;
import org.djunits.value.vfloat.vector.*;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float%TypeRel% Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class Float%TypeRel%Matrix extends AbstractFloatMatrixRelWithAbs<%TypeAbsUnit%, Float%TypeAbs%, Float%TypeAbs%Vector, Float%TypeAbs%Matrix,
%TypeRelUnit%, Float%TypeRel%, Float%TypeRel%Vector, Float%TypeRel%Matrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;
    
    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit %TypeRel%Unit; the unit
     */
    public Float%TypeRel%Matrix(final FloatMatrixData data, final %TypeRel%Unit unit)
    {
        super(data, unit);
    }
    
    /** {@inheritDoc} */
    @Override
    public Class<Float%TypeRel%> getScalarClass()
    {
        return Float%TypeRel%.class;
    }
    
    /** {@inheritDoc} */
    @Override
    public Class<Float%TypeRel%Vector> getVectorClass()
    {
        return Float%TypeRel%Vector.class;
    }
        
    /** {@inheritDoc} */
    @Override
    public Float%TypeRel%Matrix instantiateMatrix(final FloatMatrixData fmd, final %TypeRelUnit% displayUnit)
    {
        return new Float%TypeRel%Matrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeRel%Vector instantiateVector(final FloatVectorData fvd, final %TypeRelUnit% displayUnit)
    {
        return new Float%TypeRel%Vector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeRel% instantiateScalarSI(final float valueSI, final %TypeRelUnit% displayUnit)
    {
        Float%TypeRel% result = Float%TypeRel%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeAbs%Matrix instantiateMatrixAbs(final FloatMatrixData fmd, final %TypeAbsUnit% displayUnit)
    {
        return new Float%TypeAbs%Matrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeAbs%Vector instantiateVectorAbs(final FloatVectorData fvd, final %TypeAbsUnit% displayUnit)
    {
        return new Float%TypeAbs%Vector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeAbs% instantiateScalarAbsSI(final float valueSI, final %TypeAbsUnit% displayUnit)
    {
        Float%TypeAbs% result = Float%TypeAbs%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %FORMULAS%%TypeRel%%
}
