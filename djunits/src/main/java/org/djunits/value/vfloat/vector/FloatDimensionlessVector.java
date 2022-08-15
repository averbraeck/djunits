package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatDimensionlessVector, a vector of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDimensionlessVector
        extends AbstractFloatVectorRel<DimensionlessUnit, FloatDimensionless, FloatDimensionlessVector>
        implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionlessVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatDimensionlessVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit DimensionlessUnit; the unit
     */
    public FloatDimensionlessVector(final FloatVectorData data, final DimensionlessUnit unit)
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
    public final FloatDimensionlessVector acos()
    {
        assign(FloatMathFunctions.ACOS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector asin()
    {
        assign(FloatMathFunctions.ASIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector atan()
    {
        assign(FloatMathFunctions.ATAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector cbrt()
    {
        assign(FloatMathFunctions.CBRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector cos()
    {
        assign(FloatMathFunctions.COS);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector cosh()
    {
        assign(FloatMathFunctions.COSH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector exp()
    {
        assign(FloatMathFunctions.EXP);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector expm1()
    {
        assign(FloatMathFunctions.EXPM1);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector log()
    {
        assign(FloatMathFunctions.LOG);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector log10()
    {
        assign(FloatMathFunctions.LOG10);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector log1p()
    {
        assign(FloatMathFunctions.LOG1P);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector pow(final double x)
    {
        assign(FloatMathFunctions.POW((float) x));
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector signum()
    {
        assign(FloatMathFunctions.SIGNUM);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector sin()
    {
        assign(FloatMathFunctions.SIN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector sinh()
    {
        assign(FloatMathFunctions.SINH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector sqrt()
    {
        assign(FloatMathFunctions.SQRT);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector tan()
    {
        assign(FloatMathFunctions.TAN);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector tanh()
    {
        assign(FloatMathFunctions.TANH);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDimensionlessVector inv()
    {
        assign(FloatMathFunctions.INV);
        return this;
    }

}
