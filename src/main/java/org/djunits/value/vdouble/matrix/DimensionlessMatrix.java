package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.vector.DimensionlessVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double DimensionlessMatrix, a matrix of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class DimensionlessMatrix
        extends AbstractDoubleMatrixRel<DimensionlessUnit, Dimensionless, DimensionlessVector, DimensionlessMatrix>
        implements DimensionlessFunctions<DimensionlessUnit, DimensionlessMatrix>
{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit DimensionlessUnit; the unit
     */
    public DimensionlessMatrix(final DoubleMatrixData data, final DimensionlessUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Dimensionless> getScalarClass()
    {
        return Dimensionless.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<DimensionlessVector> getVectorClass()
    {
        return DimensionlessVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public DimensionlessMatrix instantiateMatrix(final DoubleMatrixData dmd, final DimensionlessUnit displayUnit)
    {
        return new DimensionlessMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public DimensionlessVector instantiateVector(final DoubleVectorData dvd, final DimensionlessUnit displayUnit)
    {
        return new DimensionlessVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Dimensionless instantiateScalarSI(final double valueSI, final DimensionlessUnit displayUnit)
    {
        Dimensionless result = Dimensionless.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix acos()
    {
        assign(DoubleMathFunctions.ACOS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix asin()
    {
        assign(DoubleMathFunctions.ASIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix atan()
    {
        assign(DoubleMathFunctions.ATAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix cbrt()
    {
        assign(DoubleMathFunctions.CBRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix cos()
    {
        assign(DoubleMathFunctions.COS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix cosh()
    {
        assign(DoubleMathFunctions.COSH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix exp()
    {
        assign(DoubleMathFunctions.EXP);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix expm1()
    {
        assign(DoubleMathFunctions.EXPM1);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix log()
    {
        assign(DoubleMathFunctions.LOG);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix log10()
    {
        assign(DoubleMathFunctions.LOG10);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix log1p()
    {
        assign(DoubleMathFunctions.LOG1P);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix pow(final double x)
    {
        assign(DoubleMathFunctions.POW((float) x));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix signum()
    {
        assign(DoubleMathFunctions.SIGNUM);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix sin()
    {
        assign(DoubleMathFunctions.SIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix sinh()
    {
        assign(DoubleMathFunctions.SINH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix sqrt()
    {
        assign(DoubleMathFunctions.SQRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix tan()
    {
        assign(DoubleMathFunctions.TAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix tanh()
    {
        assign(DoubleMathFunctions.TANH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final DimensionlessMatrix inv()
    {
        assign(DoubleMathFunctions.INV);
        return this;
    }

}
