package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.vector.FloatDimensionlessVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatDimensionlessMatrix, a matrix of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDimensionlessMatrix extends
        AbstractFloatMatrixRel<DimensionlessUnit, FloatDimensionless, FloatDimensionlessVector, FloatDimensionlessMatrix>
        implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionlessMatrix>
{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit DimensionlessUnit; the unit
     */
    public FloatDimensionlessMatrix(final FloatMatrixData data, final DimensionlessUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDimensionless> getScalarClass()
    {
        return FloatDimensionless.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDimensionlessVector> getVectorClass()
    {
        return FloatDimensionlessVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDimensionlessMatrix instantiateMatrix(final FloatMatrixData fmd, final DimensionlessUnit displayUnit)
    {
        return new FloatDimensionlessMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDimensionlessVector instantiateVector(final FloatVectorData fvd, final DimensionlessUnit displayUnit)
    {
        return new FloatDimensionlessVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDimensionless instantiateScalarSI(final float valueSI, final DimensionlessUnit displayUnit)
    {
        FloatDimensionless result = FloatDimensionless.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix acos()
    {
        assign(FloatMathFunctions.ACOS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix asin()
    {
        assign(FloatMathFunctions.ASIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix atan()
    {
        assign(FloatMathFunctions.ATAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix cbrt()
    {
        assign(FloatMathFunctions.CBRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix cos()
    {
        assign(FloatMathFunctions.COS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix cosh()
    {
        assign(FloatMathFunctions.COSH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix exp()
    {
        assign(FloatMathFunctions.EXP);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix expm1()
    {
        assign(FloatMathFunctions.EXPM1);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix log()
    {
        assign(FloatMathFunctions.LOG);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix log10()
    {
        assign(FloatMathFunctions.LOG10);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix log1p()
    {
        assign(FloatMathFunctions.LOG1P);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix pow(final double x)
    {
        assign(FloatMathFunctions.POW((float) x));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix signum()
    {
        assign(FloatMathFunctions.SIGNUM);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix sin()
    {
        assign(FloatMathFunctions.SIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix sinh()
    {
        assign(FloatMathFunctions.SINH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix sqrt()
    {
        assign(FloatMathFunctions.SQRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix tan()
    {
        assign(FloatMathFunctions.TAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix tanh()
    {
        assign(FloatMathFunctions.TANH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessMatrix inv()
    {
        assign(FloatMathFunctions.INV);
        return this;
    }

}
