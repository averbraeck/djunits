package org.djunits.unit.quantity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.djunits.unit.Unit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.junit.Test;

/**
 * QuantityTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class QuantityTest
{
    /**
     * Test the Quantity functions.
     * @throws UnitException on error
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testQuantity() throws UnitException
    {

        assertEquals(QUnit.BASE, QUnit.BASE);
        assertNotEquals(QUnit.BASE, null);
        assertNotEquals(QUnit.BASE, new Object());
        Quantity<QUnit> quantity1 = new Quantity<>("kgm4/s5A3", SIDimensions.of("kgm4/s5A3"));
        Quantity<QUnit> quantity2 = new Quantity<>("Test2", new SIDimensions(0, 0, 1, 4, -5, -3, 0, 0, 0));
        Quantity<QUnit> quantity3 = new Quantity<>("Test3", new byte[] { 0, 0, 1, 4, -5, -3, 0, 0, 0 });
        assertEquals(quantity1, quantity2);
        assertEquals(quantity1, quantity3);
        assertNotEquals(quantity1, QUnit.BASE); // QUnit has a standard base; quantity1 not (yet)
        assertNotEquals(quantity1.hashCode(), QUnit.BASE.hashCode()); // QUnit has a standard base; quantity1 not (yet)
        assertEquals(new SIDimensions(0, 0, 1, 4, -5, -3, 0, 0, 0), quantity1.getSiDimensions());
        Quantity<QUnit> quantity4 = new Quantity<>("kg2m4/s5A3", SIDimensions.of("kg2m4/s5A3"));
        assertNotEquals(quantity1, quantity4);

        try
        {
            SIDimensions si = null;
            Quantity<QUnit> quantity5 = new Quantity<>("Test4", si);
            fail("Should not have been able to create " + quantity5 + " with argument null");
        }
        catch (NullPointerException | UnitRuntimeException e)
        {
            // ok
        }

        try
        {
            Quantity<QUnit> quantity5 = new Quantity<>("", SIDimensions.of("kg2m4/s5A3"));
            fail("Should not have been able to create " + quantity5 + " with empty name");
        }
        catch (NullPointerException | UnitRuntimeException e)
        {
            // ok
        }

        try
        {
            Quantity<QUnit> quantity5 = new Quantity<>(null, SIDimensions.of("kg2m4/s5A3"));
            fail("Should not have been able to create " + quantity5 + " with null name");
        }
        catch (NullPointerException | UnitRuntimeException e)
        {
            // ok
        }

        assertEquals(QUnit.SI, QUnit.BASE.of("Q"));
        assertEquals(QUnit.SI, QUnit.BASE.getStandardUnit());
        assertEquals(QUnit.SI, QUnit.BASE.getUnitByAbbreviation("Q"));
        assertEquals(QUnit.SI, QUnit.BASE.getUnitById("Q"));

        assertEquals(QUnit.QQQ, QUnit.BASE.of("QQQ"));
        assertNotEquals(QUnit.QQQ, QUnit.BASE.getStandardUnit());
        assertEquals(QUnit.QQQ, QUnit.BASE.getUnitByAbbreviation("QQQ"));
        assertEquals(QUnit.QQQ, QUnit.BASE.getUnitById("QQQ"));

        assertEquals(QUnit.KILOQUEEZ, QUnit.BASE.of("kQ"));
        assertNotNull(QUnit.BASE.of("yQ"));
        assertNotNull(QUnit.BASE.of("YQ"));
        assertNotNull(QUnit.BASE.of("muQ"));
        assertNotNull(QUnit.BASE.of("MQ"));

        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kgm4/s5A3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kg.m4/s5.A3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kg.m^4/s^5.A^3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kgm4s-5A-3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kg.m4.s-5.A-3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("kg.m^4.s^-5.A^-3"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("m4kg/A3s5"));
        assertEquals(QUnit.QUEEZ, QUnit.BASE.of("s^-5.A^-3.kg.m^4"));
        assertNull(QUnit.BASE.of("kgm4/s5A4"));
        assertNull(QUnit.BASE.of("abcdef"));
        assertNull(QUnit.BASE.of("s^-5.A^-3.kg^-1.m^4"));

        assertEquals(QUnit.BASE.of("GQ"), QUnit.BASE.getUnitById("GQ"));
        assertEquals(QUnit.BASE.of("GQ"), QUnit.BASE.getUnitsById().get("GQ"));
        assertEquals(QUnit.BASE.of("GQ"), QUnit.BASE.getUnitByAbbreviation("GQ"));
        assertEquals(QUnit.BASE.of("GQ"), QUnit.BASE.getUnitsByAbbreviation().get("GQ"));

        try
        {
            SIDimensions.of("m/m/m/m");
            fail("Quantity.of string with multiple slashes should have thrown a UnitRuntimeException");
        }
        catch (UnitException ut)
        {
            // Ignore expected exception
        }
        try
        {
            new Quantity("m/m/m/m", "m/m/m/m");
            fail("constructing Quantity from string with multiple slashes should have thrown a UnitRuntimeException");
        }
        catch (UnitRuntimeException urt)
        {
            // Ignore expected exception
        }

        QUnit.BASE.unregister(QUnit.QQQ);
        assertNull(QUnit.BASE.of("QQQ"));
        Quantities.INSTANCE.unregister(quantity1); // probably not registered
        Quantities.INSTANCE.unregister(quantity2); // probably not registered
        Quantities.INSTANCE.unregister(quantity3); // probably not registered
        Quantities.INSTANCE.unregister(quantity4); // probably not registered
        Quantities.INSTANCE.unregister(QUnit.BASE); // should unregister
    }

    /** */
    protected static class QUnit extends Unit<QUnit>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /** */
        public static final Quantity<QUnit> BASE = new Quantity<>("kgm4/s5A3", "kgm4/s5A3");

        /** */
        public static final QUnit SI =
                new QUnit().build(new Unit.Builder<QUnit>().setId("Q").setName("queez").setScale(IdentityScale.SCALE)
                        .setSiPrefixes(SIPrefixes.UNIT, 1.0).setQuantity(BASE).setUnitSystem(UnitSystem.OTHER));

        /** */
        public static final QUnit QUEEZ = SI;

        /** */
        public static final QUnit KILOQUEEZ =
                new QUnit().build(new Unit.Builder<QUnit>().setId("kQ").setName("kiloqueez").setScale(new LinearScale(1000.0))
                        .setSiPrefixes(SIPrefixes.NONE, 1.0).setQuantity(BASE).setUnitSystem(UnitSystem.OTHER));

        /** */
        public static final QUnit MEGAQUEEZ = new QUnit()
                .build(new Unit.Builder<QUnit>().setId("MQ").setName("megaqueez").setScale(new LinearScale(1_000_000.0))
                        .setSiPrefixes(SIPrefixes.NONE, 1.0).setQuantity(BASE).setUnitSystem(UnitSystem.OTHER));

        /** */
        public static final QUnit QQQ =
                new QUnit().build(new Unit.Builder<QUnit>().setId("QQQ").setName("qqqeezz").setScale(new LinearScale(86400.0))
                        .setSiPrefixes(SIPrefixes.NONE, 1.0).setQuantity(BASE).setUnitSystem(UnitSystem.OTHER));
    }

}
