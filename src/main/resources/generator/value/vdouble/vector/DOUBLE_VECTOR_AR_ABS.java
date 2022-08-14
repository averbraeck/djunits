package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.*;
import org.djunits.value.vdouble.vector.*;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double %TypeAbs%Vector, a vector of values with a %TypeAbsUnit%. 
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %TypeAbs%Vector extends AbstractDoubleVectorAbs<%TypeAbsUnit%, %TypeAbs%, %TypeAbs%Vector, 
        %TypeRelUnit%, %TypeRel%, %TypeRel%Vector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an %TypeAbs%Vector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param unit %TypeAbsUnit%; the display unit of the vector data
     */
    public %TypeAbs%Vector(final DoubleVectorData data, final %TypeAbsUnit% unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<%TypeAbs%> getScalarClass()
    {
        return %TypeAbs%.class;
    }

    /** {@inheritDoc} */
    @Override
    public %TypeAbs%Vector instantiateVector(final DoubleVectorData dvd, final %TypeAbsUnit% displayUnit)
    {
        return new %TypeAbs%Vector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeAbs% instantiateScalarSI(final double valueSI, final %TypeAbsUnit% displayUnit)
    {
        %TypeAbs% result = %TypeAbs%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public %TypeRel%Vector instantiateVectorRel(final DoubleVectorData dvd, final %TypeRelUnit% displayUnit)
    {
        return new %TypeRel%Vector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeRel% instantiateScalarRelSI(final double valueSI, final %TypeRelUnit% displayUnit)
    {
        %TypeRel% result = %TypeRel%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %FORMULAS%%TypeAbs%%
}
