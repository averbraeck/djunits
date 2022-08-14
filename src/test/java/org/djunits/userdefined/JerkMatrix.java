package org.djunits.userdefined;

import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double JerkMatrix, a matrix of values with a JerkUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class JerkMatrix extends AbstractDoubleMatrixRel<JerkUnit, Jerk, JerkVector, JerkMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param displayUnit JerkUnit; the unit in which the data will be displayed
     */
    public JerkMatrix(final DoubleMatrixData data, final JerkUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Jerk> getScalarClass()
    {
        return Jerk.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<JerkVector> getVectorClass()
    {
        return JerkVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public JerkMatrix instantiateMatrix(final DoubleMatrixData dmd, final JerkUnit displayUnit)
    {
        return new JerkMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public JerkVector instantiateVector(final DoubleVectorData dvd, final JerkUnit displayUnit)
    {
        return new JerkVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Jerk instantiateScalarSI(final double valueSI, final JerkUnit displayUnit)
    {
        Jerk result = new Jerk(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
