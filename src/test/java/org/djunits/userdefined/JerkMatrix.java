package org.djunits.userdefined;

import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double JerkMatrix, a matrix of values with a JerkUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public class JerkMatrix extends DoubleMatrixRel<JerkUnit, Jerk, JerkVector, JerkMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data an internal data object
     * @param displayUnit the unit in which the data will be displayed
     */
    public JerkMatrix(final DoubleMatrixData data, final JerkUnit displayUnit)
    {
        super(data, displayUnit);
    }

    @Override
    public Class<Jerk> getScalarClass()
    {
        return Jerk.class;
    }

    @Override
    public Class<JerkVector> getVectorClass()
    {
        return JerkVector.class;
    }

    @Override
    public JerkMatrix instantiateMatrix(final DoubleMatrixData dmd, final JerkUnit displayUnit)
    {
        return new JerkMatrix(dmd, displayUnit);
    }

    @Override
    public JerkVector instantiateVector(final DoubleVectorData dvd, final JerkUnit displayUnit)
    {
        return new JerkVector(dvd, displayUnit);
    }

    @Override
    public Jerk instantiateScalarSI(final double valueSI, final JerkUnit displayUnit)
    {
        Jerk result = new Jerk(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
