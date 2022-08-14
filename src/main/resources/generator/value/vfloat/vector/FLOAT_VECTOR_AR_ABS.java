package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.value.vfloat.scalar.*;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djunits.value.vfloat.vector.base.*;

/**
 * Absolute Float%TypeAbs% Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class Float%TypeAbs%Vector extends AbstractFloatVectorAbs<%TypeAbsUnit%, Float%TypeAbs%, Float%TypeAbs%Vector,
    %TypeRelUnit%, Float%TypeRel%, Float%TypeRel%Vector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a new Absative Immutable Float%TypeAbs%Vector.
     * @param data FloatVectorData; an internal data object
     * @param unit %TypeAbsUnit%; the unit
     */
    public Float%TypeAbs%Vector(final FloatVectorData data, final %TypeAbsUnit% unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Float%TypeAbs%>

    getScalarClass()
    {
        return Float%TypeAbs%.class;
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeAbs%Vector instantiateVector(final FloatVectorData fvd, final %TypeAbsUnit% displayUnit)
    {
        return new Float%TypeAbs%Vector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeAbs% instantiateScalarSI(final float valueSI, final %TypeAbsUnit% displayUnit)
    {
        Float%TypeAbs% result = Float%TypeAbs%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeRel%Vector instantiateVectorRel(final FloatVectorData fvd, final %TypeRelUnit% displayUnit)
    {
        return new Float%TypeRel%Vector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Float%TypeRel% instantiateScalarRelSI(final float valueSI, final %TypeRelUnit% displayUnit)
    {
        Float%TypeRel% result = Float%TypeRel%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %FORMULAS%%TypeAbs%%
}
