package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.Unit;
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
public class DoubleScalarInstantiateTest
{
    /**
     * Test the instantiation utility function of classes.
     */
    @Test
    public final void instantiateTest()
    {
        Dimensionless dimensionless = SIScalar.instantiate(10.0, DimensionlessUnit.SI);
        assertEquals(10.0, dimensionless.getSI(), 0.0001d, "Dimensionless");

        Acceleration acceleration = SIScalar.instantiate(10.0, AccelerationUnit.SI);
        assertEquals(10.0, acceleration.getSI(), 0.0001d, "Acceleration");
        acceleration = SIScalar.instantiate(12960.0, AccelerationUnit.KM_PER_HOUR_2);
        assertEquals(1.0, acceleration.getSI(), 0.001d, "Acceleration");

        SolidAngle angleSolid = SIScalar.instantiate(10.0, SolidAngleUnit.SI);
        assertEquals(10.0, angleSolid.getSI(), 0.0001d, "SolidAngle");
        angleSolid = SIScalar.instantiate(1.0, SolidAngleUnit.SQUARE_DEGREE);
        assertEquals((Math.PI / 180.0) * (Math.PI / 180.0), angleSolid.getSI(), 0.001d, "SolidAngle");

        Angle angle = SIScalar.instantiate(10.0, AngleUnit.SI);
        assertEquals(10.0, angle.getSI(), 0.0001d, "Angle");
        angle = SIScalar.instantiate(1.0, AngleUnit.DEGREE);
        assertEquals(Math.PI / 180.0, angle.getSI(), 0.001d, "Angle");

        Direction direction = SIScalar.instantiate(10.0, DirectionUnit.DEFAULT);
        assertEquals(10.0, direction.getSI(), 0.0001d, "Direction");
        direction = SIScalar.instantiate(1.0, DirectionUnit.EAST_DEGREE);
        assertEquals(Math.PI / 180.0, direction.getSI(), 0.001d, "Direction");

        Area area = SIScalar.instantiate(10.0, AreaUnit.SI);
        assertEquals(10.0, area.getSI(), 0.0001d, "Area");
        area = SIScalar.instantiate(1.0, AreaUnit.HECTARE);
        assertEquals(10000.0, area.getSI(), 0.001d, "Area");

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
        Length length = SIScalar.instantiateAnonymous(10.0, unitSI);
        assertEquals(10.0, length.getSI(), 0.0001d, "Length");
        length = SIScalar.instantiateAnonymous(1.0, unitKM);
        assertEquals(1000.0, length.getSI(), 0.001d, "Length");

        // TODO: other base units

    }
}
