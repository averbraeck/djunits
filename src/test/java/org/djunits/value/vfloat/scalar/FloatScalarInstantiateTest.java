package org.djunits.value.vfloat.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.Unit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.junit.jupiter.api.Test;

/**
 * Test the instantiation utility functions.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatScalarInstantiateTest
{
    /**
     * Test the instantiation utility function of classes.
     */
    @Test
    public final void instantiateTest()
    {
        FloatDimensionless dimensionless = FloatScalar.instantiate(10.0f, DimensionlessUnit.SI);
        assertEquals(10.0f, dimensionless.getSI(), 0.0001d, "FloatDimensionless");

        FloatAcceleration acceleration = FloatScalar.instantiate(10.0f, AccelerationUnit.SI);
        assertEquals(10.0f, acceleration.getSI(), 0.0001d, "FloatAcceleration");
        acceleration = FloatScalar.instantiate(12960.0f, AccelerationUnit.KM_PER_HOUR_2);
        assertEquals(1.0, acceleration.getSI(), 0.001d, "FloatAcceleration");

        FloatSolidAngle angleSolid = FloatScalar.instantiate(10.0f, SolidAngleUnit.SI);
        assertEquals(10.0f, angleSolid.getSI(), 0.0001d, "FloatSolidAngle");
        angleSolid = FloatScalar.instantiate(1.0f, SolidAngleUnit.SQUARE_DEGREE);
        assertEquals((Math.PI / 180.0) * (Math.PI / 180.0), angleSolid.getSI(), 0.001d, "FloatSolidAngle");

        FloatAngle angle = FloatScalar.instantiate(10.0f, AngleUnit.SI);
        assertEquals(10.0f, angle.getSI(), 0.0001d, "FloatAngle");
        angle = FloatScalar.instantiate(1.0f, AngleUnit.DEGREE);
        assertEquals(Math.PI / 180.0, angle.getSI(), 0.001d, "FloatAngle");

        FloatDirection direction = FloatScalar.instantiate(10.0f, DirectionUnit.DEFAULT);
        assertEquals(10.0f, direction.getSI(), 0.0001d, "FloatDirection");
        direction = FloatScalar.instantiate(1.0f, DirectionUnit.EAST_DEGREE);
        assertEquals(Math.PI / 180.0, direction.getSI(), 0.001d, "FloatDirection");

        FloatArea area = FloatScalar.instantiate(10.0f, AreaUnit.SI);
        assertEquals(10.0f, area.getSI(), 0.0001d, "FloatArea");
        area = FloatScalar.instantiate(1.0f, AreaUnit.HECTARE);
        assertEquals(10000.0, area.getSI(), 0.001d, "FloatArea");

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
        FloatLength length = FloatScalar.instantiateAnonymous(10.0f, unitSI);
        assertEquals(10.0f, length.getSI(), 0.0001d, "FloatLength");
        length = FloatScalar.instantiateAnonymous(1.0f, unitKM);
        assertEquals(1000.0, length.getSI(), 0.001d, "FloatLength");

        // TODO: other base units

    }
}
