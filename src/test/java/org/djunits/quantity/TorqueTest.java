package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TorqueTest tests the Torque quantity class.<br>
 * <br>
 * This test suite verifies:
 * <ul>
 * <li>Constructors, constants, copy behavior, and SI conversions</li>
 * <li>String parsing and all parsing-related error branches</li>
 * <li>Arithmetic operations (divide and multiply) producing correct quantities</li>
 * <li>Unit conversions, including imperial units (lbf·ft, lbf·in) and kilogram-force meters</li>
 * <li>Unit derivation behavior—including linear unit derivation and the exception path for non-linear scale derivation</li>
 * <li>Proper resolution of units through the Units registry</li>
 * </ul>
 * <br>
 * This test class aims for full branch coverage. <br>
 * <br>
 * © 2025–2025 Delft University of Technology. Distributed under the three-clause BSD license.
 */
class TorqueTest
{
    /**
     * Set a predictable locale before each test.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, string parsing, SI conversions, instantiate, siUnit(), and ofSi().
     */
    @Test
    void testBasics()
    {
        Torque t0 = new Torque(0.0, Torque.Unit.Nm);
        assertEquals(Torque.ZERO, t0);
        assertEquals(0.0, Torque.ZERO.si(), 1E-12);

        Torque t1 = new Torque(1.0, Torque.Unit.Nm);
        assertEquals(Torque.ONE, t1);
        assertEquals(1.0, Torque.ONE.si(), 1E-12);

        // Constants
        assertTrue(Double.isNaN(Torque.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Torque.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Torque.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Torque.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Torque.NEG_MAXVALUE.si());

        // Copy constructor
        Torque two = new Torque(2.0, Torque.Unit.Nm);
        Torque copy = new Torque(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct from abbreviation string
        Torque s = new Torque(1.5, "Nm");
        assertEquals(1.5, s.si(), 1E-12);

        // Parsing helpers
        Torque p1 = Torque.valueOf("2 Nm");
        assertEquals(2.0, p1.si(), 1E-12);

        Torque p2 = Torque.of(5.0, "Nm");
        assertEquals(5.0, p2.si(), 1E-12);

        // instantiate delegate
        assertEquals(-10.1, s.instantiate(-10.1).si(), 1E-12);

        // SI unit textual form
        assertEquals("kgm2/s2", s.siUnit().toString(true, false));

        // ofSi
        Torque neg = Torque.ofSi(-3.0);
        assertEquals(-3.0, neg.si(), 1E-12);
    }

    /**
     * Test all parsing error branches: null, empty, unknown unit, malformed numeric value, and of() error paths.
     */
    @Test
    void testParsingErrors()
    {
        assertThrows(NullPointerException.class, () -> Torque.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> Torque.valueOf(""));
        assertThrows(IllegalArgumentException.class, () -> Torque.valueOf("10 blargh"));
        assertThrows(IllegalArgumentException.class, () -> Torque.valueOf("not-a-number Nm"));

        assertThrows(NullPointerException.class, () -> Torque.of(1.0, null));
        assertThrows(IllegalArgumentException.class, () -> Torque.of(1.0, ""));
        assertThrows(UnitRuntimeException.class, () -> Torque.of(1.0, "blargh"));
    }

    /**
     * Test correctness of unit conversions for Nm, meter-kilogram-force, pound-foot, and pound-inch. Also verifies registry
     * resolution behavior.
     */
    @Test
    void testUnitConversions()
    {
        // Base unit checks
        assertEquals(Torque.Unit.SI_UNIT, Torque.Unit.Nm.siUnit());
        assertEquals(Torque.Unit.SI, Torque.Unit.Nm.getBaseUnit());

        double g = Acceleration.Unit.CONST_GRAVITY;

        // meter kilogram-force (m·kgf)
        Torque t1 = new Torque(1.0, Torque.Unit.m_kgf);
        assertEquals(g, t1.si(), 1E-12);

        // pound-foot
        double ft = Length.Unit.CONST_FT;
        double lb = Mass.Unit.CONST_LB;
        Torque t2 = new Torque(1.0, Torque.Unit.lbf_ft);
        assertEquals(ft * lb * g, t2.si(), 1E-12);

        // pound-inch
        double inch = Length.Unit.CONST_IN;
        Torque t3 = new Torque(1.0, Torque.Unit.lbf_in);
        assertEquals(inch * lb * g, t3.si(), 1E-12);

        // Registry sanity
        assertEquals(Torque.Unit.Nm, Units.resolve(Torque.Unit.class, "Nm"));
        assertEquals(Torque.Unit.lbf_ft, Units.resolve(Torque.Unit.class, "lbf.ft"));
    }

    /**
     * Test all arithmetic operations provided by Torque.
     * <ul>
     * <li>divide(Torque)</li>
     * <li>divide(Force) → Length</li>
     * <li>divide(Length) → Force</li>
     * <li>multiply(LinearObjectDensity) → Force</li>
     * <li>divide(Duration) → Power</li>
     * <li>divide(Power) → Duration</li>
     * <li>multiply(Frequency) → Power</li>
     * <li>divide(Volume) → Pressure</li>
     * <li>divide(Pressure) → Volume</li>
     * </ul>
     */
    @Test
    @SuppressWarnings("checkstyle:localvariablename")
    void testOperations()
    {
        // τ / τ
        var d1 = Torque.ONE.divide(Torque.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Torque.ofSi(2.0).divide(Torque.ofSi(4.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // τ / 0
        var dInf = Torque.ofSi(1.0).divide(Torque.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // τ / F → L
        var L1 = Torque.ofSi(10.0).divide(Force.ofSi(2.0));
        assertTrue(L1 instanceof Length);
        assertEquals(5.0, L1.si(), 1E-12);

        // τ / L → F
        var F1 = Torque.ofSi(12.0).divide(Length.ofSi(3.0));
        assertTrue(F1 instanceof Force);
        assertEquals(4.0, F1.si(), 1E-12);

        // τ * LOD → F
        var F2 = Torque.ofSi(8.0).multiply(LinearObjectDensity.ofSi(0.5));
        assertTrue(F2 instanceof Force);
        assertEquals(4.0, F2.si(), 1E-12);

        // τ / t → P
        var P1 = Torque.ofSi(20.0).divide(Duration.ofSi(4.0));
        assertTrue(P1 instanceof Power);
        assertEquals(5.0, P1.si(), 1E-12);

        // τ / P → t
        var t1 = Torque.ofSi(18.0).divide(Power.ofSi(3.0));
        assertTrue(t1 instanceof Duration);
        assertEquals(6.0, t1.si(), 1E-12);

        // τ * f → P
        var P2 = Torque.ofSi(6.0).multiply(Frequency.ofSi(2.0));
        assertTrue(P2 instanceof Power);
        assertEquals(12.0, P2.si(), 1E-12);

        // τ / V → p
        var p1 = Torque.ofSi(30.0).divide(Volume.ofSi(10.0));
        assertTrue(p1 instanceof Pressure);
        assertEquals(3.0, p1.si(), 1E-12);

        // τ / p → V
        var V1 = Torque.ofSi(40.0).divide(Pressure.ofSi(8.0));
        assertTrue(V1 instanceof Volume);
        assertEquals(5.0, V1.si(), 1E-12);
    }

    /**
     * Test unit derivation.
     * <ul>
     * <li>Deriving a linear unit</li>
     * <li>Exception branch for non-linear scale derivation</li>
     * <li>ofSi delegation</li>
     * </ul>
     */
    @Test
    void testUnitBehavior()
    {
        // ofSi
        Torque fromUnit = Torque.Unit.Nm.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Linear derivation
        Torque.Unit twoNm = Torque.Unit.Nm.deriveUnit("2Nm", "2Nm", "two newton meter", 2.0, UnitSystem.OTHER);
        Torque x = new Torque(1.0, twoNm);
        assertEquals(2.0, x.si(), 1E-12);

        // Non-linear derivation throws
        Torque.Unit bad = new Torque.Unit("gNm", "gNm", "grade-like Nm", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { bad.deriveUnit("bad", "bad", "bad unit", 3.0, UnitSystem.OTHER); });
    }
}
