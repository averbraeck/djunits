package org.djunits.value.vdouble.matrix;

import java.util.List;
import java.util.SortedMap;

import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.vector.*;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djunits.value.vdouble.scalar.*;

/**
 * Immutable %TypeRel% Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class %TypeRel%Matrix extends AbstractDoubleMatrixRelWithAbs<%TypeAbsUnit%, %TypeAbs%, %TypeAbs%Vector, %TypeAbs%Matrix,
    %TypeRelUnit%, %TypeRel%, %TypeRel%Vector, %TypeRel%Matrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit %TypeRel%Unit; the unit
     */
    public %TypeRel%Matrix(final DoubleMatrixData data, final %TypeRel%Unit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<%TypeRel%> getScalarClass()
    {
        return %TypeRel%.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<%TypeRel%Vector> getVectorClass()
    {
        return %TypeRel%Vector.class;
    }
    
    /** {@inheritDoc} */
    @Override
    public %TypeRel%Matrix instantiateMatrix(final DoubleMatrixData dmd, final %TypeRelUnit% displayUnit)
    {
        return new %TypeRel%Matrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeRel%Vector instantiateVector(final DoubleVectorData dvd, final %TypeRelUnit% displayUnit)
    {
        return new %TypeRel%Vector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeRel% instantiateScalarSI(final double valueSI, final %TypeRelUnit% displayUnit)
    {
        %TypeRel% result = %TypeRel%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public %TypeAbs%Matrix instantiateMatrixAbs(final DoubleMatrixData dmd, final %TypeAbsUnit% displayUnit)
    {
        return new %TypeAbs%Matrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeAbs%Vector instantiateVectorAbs(final DoubleVectorData dvd, final %TypeAbsUnit% displayUnit)
    {
        return new %TypeAbs%Vector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public %TypeAbs% instantiateScalarAbsSI(final double valueSI, final %TypeAbsUnit% displayUnit)
    {
        %TypeAbs% result = %TypeAbs%.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

%FORMULAS%%TypeRel%%
}
