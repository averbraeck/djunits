package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.*;
import org.djunits.value.vdouble.vector.*;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double %Type%Vector, a vector of values with a %Type%Unit. 
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %Type%Vector extends AbstractDoubleVectorRel<%Type%Unit, %Type%, %Type%Vector>
%DIMLESS%
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an %Type%Vector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit %Type%Unit; the display unit of the vector data
     */
    public %Type%Vector(final DoubleVectorData data, final %Type%Unit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<%Type%> getScalarClass()
    {
        return %Type%.class;
    }

    /** {@inheritDoc} */
    @Override
    public %Type%Vector instantiateVector(final DoubleVectorData dvd, final %Type%Unit displayUnit)
    {
        return new %Type%Vector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %Type% instantiateScalarSI(final double valueSI, final %Type%Unit displayUnit)
    {
        %Type% result = %Type%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %FORMULAS%%Type%%
}

