package org.djunits.value.vdouble.scalar;

import static org.junit.Assert.assertEquals;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.Unit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.junit.Test;

/**
 * Test the instantiation utility functions.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleScalarInstantiateTest
{
    /**
     * Test the instantiation utility function of classes.
     */
    @Test
    public final void instantiateTest()
    {
        Dimensionless dimensionless = DoubleScalar.instantiate(10.0, DimensionlessUnit.SI);
        assertEquals("Dimensionless", 10.0, dimensionless.getSI(), 0.0001d);

        Acceleration acceleration = DoubleScalar.instantiate(10.0, AccelerationUnit.SI);
        assertEquals("Acceleration", 10.0, acceleration.getSI(), 0.0001d);
        acceleration = DoubleScalar.instantiate(12960.0, AccelerationUnit.KM_PER_HOUR_2);
        assertEquals("Acceleration", 1.0, acceleration.getSI(), 0.001d);

        SolidAngle angleSolid = DoubleScalar.instantiate(10.0, SolidAngleUnit.SI);
        assertEquals("SolidAngle", 10.0, angleSolid.getSI(), 0.0001d);
        angleSolid = DoubleScalar.instantiate(1.0, SolidAngleUnit.SQUARE_DEGREE);
        assertEquals("SolidAngle", (Math.PI / 180.0) * (Math.PI / 180.0), angleSolid.getSI(), 0.001d);

        Angle angle = DoubleScalar.instantiate(10.0, AngleUnit.SI);
        assertEquals("Angle", 10.0, angle.getSI(), 0.0001d);
        angle = DoubleScalar.instantiate(1.0, AngleUnit.DEGREE);
        assertEquals("Angle", Math.PI / 180.0, angle.getSI(), 0.001d);

        Direction direction = DoubleScalar.instantiate(10.0, DirectionUnit.DEFAULT);
        assertEquals("Direction", 10.0, direction.getSI(), 0.0001d);
        direction = DoubleScalar.instantiate(1.0, DirectionUnit.EAST_DEGREE);
        assertEquals("Direction", Math.PI / 180.0, direction.getSI(), 0.001d);

        Area area = DoubleScalar.instantiate(10.0, AreaUnit.SI);
        assertEquals("Area", 10.0, area.getSI(), 0.0001d);
        area = DoubleScalar.instantiate(1.0, AreaUnit.HECTARE);
        assertEquals("Area", 10000.0, area.getSI(), 0.001d);

        // TODO: other base units

    }

    /**
     * Test the instantiation utility function of classes for anonymous units, also for the compiler.
     */
    @Test
    public final void anonymousUnitTest()
    {
        Unit<?> unitSI = LengthUnit.SI;
        Unit<?> unitKM = LengthUnit.KILOMETER;
        Length length = DoubleScalar.instantiateAnonymous(10.0, unitSI);
        assertEquals("Length", 10.0, length.getSI(), 0.0001d);
        length = DoubleScalar.instantiateAnonymous(1.0, unitKM);
        assertEquals("Length", 1000.0, length.getSI(), 0.001d);

        // TODO: other base units

    }
}
