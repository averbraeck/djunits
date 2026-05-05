package org.djunits.unit.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.Power;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ElectricalPotential.Unit test. <p>
 * Copyright (c) 2013-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Peter Knoppers
 */
public class ElectricPotentialUnitTest extends AbstractLinearUnitTest<ElectricPotential.Unit>
{
    /**
     * Set the locale to "US" so we know what texts should be retrieved from the resources.
     */
    @BeforeEach
    public final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Verify conversion factors, English names and abbreviations.
     */
    @Test
    public final void conversions()
    {
        assertEquals("kgm2/s3A", ElectricPotential.Unit.SI_UNIT.format(true, false));
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.V, 1, 0.00000001, "volt", "V");
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.mV, 0.001, 0.00000000001, "millivolt", "mV");
        checkUnitRatioNameAndAbbreviation(ElectricPotential.Unit.kV, 1000, 0.005, "kilovolt", "kV");
        // Check two conversions between non-standard units
        assertEquals(1000000, getMultiplicationFactorTo(ElectricPotential.Unit.kV, ElectricPotential.Unit.mV), 0.0001,
                "one KILOVOLT is 1000000 MILLIVOLT");
    }

    /**
     * Verify that we can create our own electrical potential unit.
     */
    @Test
    public final void createElectricPotentialUnit()
    {
        ElectricPotential.Unit myEPU = ElectricPotential.Unit.V.deriveUnit("nanoV", "NanoVolt", 1e-9, UnitSystem.SI_DERIVED);
        assertTrue(null != myEPU, "Can create a new ElectricPotential.Unit");
        checkUnitRatioNameAndAbbreviation(myEPU, 1e-9, 0.1, "NanoVolt", "nanoV");
        Units.unregister(myEPU);

        myEPU = ElectricPotential.Unit.SI.deriveUnit("ft.lbfph/uA", "foot pound-force per hour per microA",
                Power.Unit.ft_lbf_h.getScale().toIdentityScale(1.0) / ElectricCurrent.Unit.muA.getScale().toIdentityScale(1.0),
                UnitSystem.IMPERIAL);
        assertTrue(null != myEPU, "Can create a new ElectricPotential.Unit");
        checkUnitRatioNameAndAbbreviation(myEPU, 376.6, 0.1, "foot pound-force per hour per microA", "ft.lbfph/uA");
        Units.unregister(myEPU);
    }

    /**
     * Check the standard methods.
     */
    @Test
    final void testStandardMethods()
    {
        assertEquals(SIUnit.of("kgm2/s3A"), ElectricPotential.ONE.getDisplayUnit().siUnit());
        assertEquals(ElectricPotential.Unit.V, ElectricPotential.ONE.getDisplayUnit().getBaseUnit());
        assertEquals(ElectricPotential.ONE, ElectricPotential.Unit.V.ofSi(1.0));

        ElectricPotential.Unit nonlinearUnit =
                new ElectricPotential.Unit("xx", "xx", "xx", new GradeScale(0.1), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> nonlinearUnit.deriveUnit("yy", "yy", 0.1, UnitSystem.OTHER));
        Units.unregister(nonlinearUnit);
    }

}
