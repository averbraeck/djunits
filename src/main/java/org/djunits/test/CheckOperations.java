package org.djunits.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.djunits.quantity.AbsoluteTemperature;
import org.djunits.quantity.AbsorbedDose;
import org.djunits.quantity.Acceleration;
import org.djunits.quantity.AmountOfSubstance;
import org.djunits.quantity.Angle;
import org.djunits.quantity.AngularAcceleration;
import org.djunits.quantity.AngularVelocity;
import org.djunits.quantity.Area;
import org.djunits.quantity.CatalyticActivity;
import org.djunits.quantity.Density;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Duration;
import org.djunits.quantity.ElectricCharge;
import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.ElectricalCapacitance;
import org.djunits.quantity.ElectricalConductance;
import org.djunits.quantity.ElectricalInductance;
import org.djunits.quantity.ElectricalResistance;
import org.djunits.quantity.Energy;
import org.djunits.quantity.EquivalentDose;
import org.djunits.quantity.FlowMass;
import org.djunits.quantity.FlowVolume;
import org.djunits.quantity.Force;
import org.djunits.quantity.Frequency;
import org.djunits.quantity.Illuminance;
import org.djunits.quantity.Length;
import org.djunits.quantity.LinearDensity;
import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.LuminousFlux;
import org.djunits.quantity.LuminousIntensity;
import org.djunits.quantity.MagneticFlux;
import org.djunits.quantity.MagneticFluxDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Momentum;
import org.djunits.quantity.Position;
import org.djunits.quantity.Power;
import org.djunits.quantity.Pressure;
import org.djunits.quantity.Quantity;
import org.djunits.quantity.RadioActivity;
import org.djunits.quantity.SolidAngle;
import org.djunits.quantity.Speed;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.Time;
import org.djunits.quantity.Torque;
import org.djunits.quantity.Volume;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;

/**
 * Reflection-based checker for {@code times(...)} and {@code divide(...)} methods across quantity classes.
 * <p>
 * This utility:
 * <ol>
 * <li>Discovers all public single-argument {@code times} and {@code divide} methods on the provided quantity classes.</li>
 * <li>Prints a human-readable list of discovered calculations, e.g., {@code Energy * Frequency -> Power}.</li>
 * <li>Performs a dimensional check by combining operand SI units using {@link SIUnit#plus(SIUnit)} (for multiply) and
 * {@link SIUnit#minus(SIUnit)} (for divide), and comparing the result with the actual result quantity's SI unit.</li>
 * </ol>
 * <p>
 * <strong>Assumptions:</strong>
 * <ul>
 * <li>All quantity classes extend {@link Quantity} with type parameters {@code Quantity<?, ?>}.</li>
 * <li>Each quantity class exposes a public static constant {@code ONE} or a static factory method {@code ofSi(double)}.</li>
 * <li>{@link Quantity#getDisplayUnit()} returns a {@link UnitInterface} whose {@link UnitInterface#siUnit()} returns an
 * {@link SIUnit}.</li>
 * <li>{@link SIUnit} provides {@link SIUnit#siDimensions()} (double array in order rad, sr, kg, m, s, A, K, mol, cd),
 * {@link SIUnit#plus(SIUnit)}, {@link SIUnit#minus(SIUnit)}, and a meaningful {@link SIUnit#toString()}.</li>
 * </ul>
 * <p>
 * Usage: run {@link #main(String[])} or call {@link #run()} and {@link #printReport(List)} in your test harness.
 * </p>
 */
public final class CheckOperations
{
    /** Set of quantity classes to scan for {@code times} and {@code divide} methods. */
    private static final Class<?>[] QUANTITY_CLASSES = new Class<?>[] {AbsoluteTemperature.class, AbsorbedDose.class,
            Acceleration.class, AmountOfSubstance.class, Angle.class, AngularAcceleration.class, AngularVelocity.class,
            Area.class, CatalyticActivity.class, Density.class, Dimensionless.class, Direction.class, Duration.class,
            ElectricCharge.class, ElectricCurrent.class, ElectricPotential.class, ElectricalCapacitance.class,
            ElectricalConductance.class, ElectricalInductance.class, ElectricalResistance.class, Energy.class,
            EquivalentDose.class, FlowMass.class, FlowVolume.class, Force.class, Frequency.class, Illuminance.class,
            Length.class, LinearDensity.class, LinearObjectDensity.class, LuminousFlux.class, LuminousIntensity.class,
            MagneticFlux.class, MagneticFluxDensity.class, Mass.class, Momentum.class, Position.class, Power.class,
            Pressure.class, RadioActivity.class, SolidAngle.class, Speed.class, Temperature.class, Time.class, Torque.class,
            Volume.class};

    /** Immutable set of quantity classes for quick membership tests. */
    private static final Set<Class<?>> CLASS_SET = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(QUANTITY_CLASSES)));

    /** Numerical tolerance used when comparing SI dimension exponent arrays. */
    private static final double EPS = 1e-12;

    /** */
    private CheckOperations()
    {
        // static class.
    }

    /** Immutable record for one discovered calculation and its dimensional check outcome. */
    private static final class Calc
    {
        /** Left-hand-side quantity class. */
        private final Class<?> lhs;

        /** Operator symbol, either "*" or "/". */
        private final String op;

        /** Right-hand-side quantity class. */
        private final Class<?> rhs;

        /** Return quantity class. */
        private final Class<?> ret;

        /** Whether invocation of the method succeeded. */
        private final boolean invoked;

        /** Whether the dimensional check passed when invoked. */
        private final boolean dimOk;

        /** Expected SI unit string computed from operand units. */
        private final String expectedUnitStr;

        /** Actual SI unit string of the returned quantity. */
        private final String gotUnitStr;

        /** Optional note, typically the error message on invocation failure. */
        private final String note;

        /**
         * Constructs a calculation record.
         * @param lhsClass the left-hand-side quantity class.
         * @param opSymbol the operator symbol, "*" for multiply or "/" for divide.
         * @param rhsClass the right-hand-side quantity class.
         * @param retClass the returned quantity class.
         * @param wasInvoked whether invocation of the operation method succeeded.
         * @param dimensionsOk whether dimensional analysis matched the expected SI unit.
         * @param expectedStr the expected SI unit as a string.
         * @param gotStr the actual SI unit as a string.
         * @param message an optional note or error message; may be empty.
         */
        @SuppressWarnings("parameternumber")
        Calc(final Class<?> lhsClass, final String opSymbol, final Class<?> rhsClass, final Class<?> retClass,
                final boolean wasInvoked, final boolean dimensionsOk, final String expectedStr, final String gotStr,
                final String message)
        {
            this.lhs = lhsClass;
            this.op = opSymbol;
            this.rhs = rhsClass;
            this.ret = retClass;
            this.invoked = wasInvoked;
            this.dimOk = dimensionsOk;
            this.expectedUnitStr = expectedStr;
            this.gotUnitStr = gotStr;
            this.note = message;
        }

        /**
         * Returns a one-line human-readable signature of the calculation.
         * @return a signature line such as {@code Energy * Frequency -> Power}.
         */
        String signatureLine()
        {
            return this.lhs.getSimpleName() + " " + this.op + " " + this.rhs.getSimpleName() + " -> "
                    + this.ret.getSimpleName();
        }

        /**
         * Returns a one-line human-readable result describing dimensional success or failure.
         * @return a result line including expected and actual SI unit strings.
         */
        String resultLine()
        {
            if (!this.invoked)
            {
                return "   ❌ INVOCATION FAILED: " + this.note;
            }
            return (this.dimOk ? "   ✅ DIM OK " : "   ❌ DIM MISMATCH ") + "(expected " + this.expectedUnitStr + ", got "
                    + this.gotUnitStr + ")";
        }
    }

    /**
     * Program entry point that runs the checker and prints the report.
     * @param args command-line arguments; not used.
     */
    public static void main(final String[] args)
    {
        List<Calc> results = run();
        printReport(results);
    }

    /**
     * Runs discovery and dimensional validation for all registered quantity classes.
     * <p>
     * Steps:
     * <ol>
     * <li>Build canonical instances for each quantity class using {@code ONE} or {@code ofSi(1.0)}.</li>
     * <li>Discover public single-argument {@code times} and {@code divide} methods that operate on known quantity classes and
     * return known quantity classes.</li>
     * <li>Invoke the operations with the canonical instances and perform the dimensional check.</li>
     * </ol>
     * @return an ordered list of calculation records including dimensional check outcomes.
     */
    public static List<Calc> run()
    {
        Map<Class<?>, Quantity<?, ?>> ones = buildOnes();

        List<Calc> out = new ArrayList<>();
        for (Class<?> lhsClass : QUANTITY_CLASSES)
        {
            for (Method m : lhsClass.getMethods())
            { // public methods including inherited
                if (!isTimesOrDivide(m) || m.getParameterCount() != 1)
                {
                    continue;
                }

                Class<?> rhsClass = m.getParameterTypes()[0];
                Class<?> retClass = m.getReturnType();

                if (!CLASS_SET.contains(rhsClass))
                {
                    continue;
                }
                if (!CLASS_SET.contains(retClass))
                {
                    continue;
                }

                String op = m.getName().equals("times") ? "*" : "/";
                Calc calc = performCheck(lhsClass, m, rhsClass, retClass, op, ones);
                out.add(calc);
            }
        }

        out.sort(Comparator.comparing((
                final Calc c
        ) -> c.lhs.getSimpleName()).thenComparing(c -> c.op).thenComparing(c -> c.rhs.getSimpleName())
                .thenComparing(c -> c.ret.getSimpleName()));
        return out;
    }

    /**
     * Returns whether a method is a non-void {@code times} or {@code divide} method.
     * @param m the method to inspect; must not be {@code null}.
     * @return {@code true} if the method name is {@code times} or {@code divide} and it has a non-void return type; otherwise
     *         {@code false}.
     */
    private static boolean isTimesOrDivide(final Method m)
    {
        String n = m.getName();
        return (n.equals("times") || n.equals("divide")) && m.getReturnType() != Void.TYPE;
    }

    /**
     * Builds canonical instances for all registered quantity classes using {@code ONE} or {@code ofSi(1.0)}.
     * <p>
     * If neither {@code ONE} nor {@code ofSi(double)} is available for a class, the class is omitted from the map and a
     * diagnostic is printed to standard error.
     * </p>
     * @return a map from quantity class to its canonical instance.
     */
    private static Map<Class<?>, Quantity<?, ?>> buildOnes()
    {
        Map<Class<?>, Quantity<?, ?>> map = new HashMap<>();
        for (Class<?> q : QUANTITY_CLASSES)
        {
            Quantity<?, ?> inst = null;
            // Try public static field ONE first.
            try
            {
                Field f = q.getField("ONE");
                Object v = f.get(null);
                inst = (Quantity<?, ?>) v;
            }
            catch (NoSuchFieldException | IllegalAccessException ignored)
            {
                // Fall back to static ofSi(1.0).
                try
                {
                    Method ofSi = q.getMethod("ofSi", double.class);
                    Object v = ofSi.invoke(null, 1.0);
                    inst = (Quantity<?, ?>) v;
                }
                catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
                {
                    System.err.println("Cannot construct ONE for " + q.getSimpleName() + ": " + e);
                }
            }
            if (inst != null)
            {
                map.put(q, inst);
            }
        }
        return map;
    }

    /**
     * Performs invocation of a {@code times} or {@code divide} method and validates the dimensional consistency.
     * @param lhsClass the left-hand-side quantity class.
     * @param opMethod the method to invoke; expected to be a public single-argument {@code times} or {@code divide}.
     * @param rhsClass the right-hand-side quantity class.
     * @param retClass the return quantity class.
     * @param opSymbol the operator symbol, "*" for multiply or "/" for divide.
     * @param ones a map of canonical quantity instances keyed by class; must contain entries for the involved classes.
     * @return a calculation record including invocation and dimensional validation results.
     */
    private static Calc performCheck(final Class<?> lhsClass, final Method opMethod, final Class<?> rhsClass,
            final Class<?> retClass, final String opSymbol, final Map<Class<?>, Quantity<?, ?>> ones)
    {

        Quantity<?, ?> lhs = ones.get(lhsClass);
        Quantity<?, ?> rhs = ones.get(rhsClass);

        if (lhs == null || rhs == null)
        {
            String note = (lhs == null ? "No ONE/ofSi for " + lhsClass.getSimpleName() + ". " : "")
                    + (rhs == null ? "No ONE/ofSi for " + rhsClass.getSimpleName() + "." : "");
            return new Calc(lhsClass, opSymbol, rhsClass, retClass, false, false, "-", "-", note.trim());
        }

        try
        {
            Object resultObj = opMethod.invoke(lhs, rhs);
            Quantity<?, ?> result = (Quantity<?, ?>) resultObj;

            // Dimensional analysis via shared APIs (no reflection helpers).
            UnitInterface<?> lhsUnit = lhs.getDisplayUnit();
            UnitInterface<?> rhsUnit = rhs.getDisplayUnit();
            UnitInterface<?> resUnit = result.getDisplayUnit();

            SIUnit lhsSI = lhsUnit.siUnit();
            SIUnit rhsSI = rhsUnit.siUnit();
            SIUnit resSI = resUnit.siUnit();

            SIUnit expectedSI = opSymbol.equals("*") ? lhsSI.plus(rhsSI) : lhsSI.minus(rhsSI);

            String expectedStr = expectedSI.toString();
            String gotStr = resSI.toString();

            boolean ok = dimsEqual(expectedSI, resSI, EPS);
            return new Calc(lhsClass, opSymbol, rhsClass, retClass, true, ok, expectedStr, gotStr, "");
        }
        catch (InvocationTargetException ite)
        {
            Throwable cause = ite.getCause() != null ? ite.getCause() : ite;
            return new Calc(lhsClass, opSymbol, rhsClass, retClass, false, false, "-", "-", cause.toString());
        }
        catch (Exception e)
        {
            return new Calc(lhsClass, opSymbol, rhsClass, retClass, false, false, "-", "-", e.toString());
        }
    }

    /**
     * Compares two SI unit dimension vectors for equality within a given tolerance.
     * @param a the first SI unit.
     * @param b the second SI unit.
     * @param eps the tolerance used to compare dimension exponents.
     * @return {@code true} if all corresponding exponents differ by no more than {@code eps}; otherwise {@code false}.
     */
    private static boolean dimsEqual(final SIUnit a, final SIUnit b, final double eps)
    {
        double[] da = a.siDimensions();
        double[] db = b.siDimensions();
        if (da.length != db.length)
        {
            return false;
        }
        for (int i = 0; i < da.length; i++)
        {
            if (Math.abs(da[i] - db[i]) > eps)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints a report with discovered calculations and dimensional check results.
     * <p>
     * The report includes:
     * </p>
     * <ul>
     * <li>A list of signature lines for all discovered calculations.</li>
     * <li>Per-calculation dimensional validation outcome lines.</li>
     * <li>A summary with totals for discovered, invocation failures, passes, and failures.</li>
     * </ul>
     * @param results the list of calculation records produced by {@link #run()}.
     */
    private static void printReport(final List<Calc> results)
    {
        System.out.println("=== AVAILABLE CALCULATIONS (times/divide via reflection) ===");
        List<String> sigs = results.stream().map(Calc::signatureLine).distinct().collect(Collectors.toList());
        for (String s : sigs)
        {
            System.out.println(" - " + s);
        }

        System.out.println("\n=== DIMENSIONAL CHECK ===");
        int total = 0;
        int ok = 0;
        int failed = 0;
        int invFailed = 0;
        for (Calc c : results)
        {
            System.out.println(c.signatureLine());
            System.out.println(c.resultLine());
            total++;
            if (!c.invoked)
            {
                invFailed++;
            }
            else if (c.dimOk)
            {
                ok++;
            }
            else
            {
                failed++;
            }
        }

        System.out.println("\n=== SUMMARY ===");
        System.out.printf("Total discovered: %d%n", total);
        System.out.printf("Invocation failures: %d%n", invFailed);
        System.out.printf("Dim OK: %d%n", ok);
        System.out.printf("Dim FAIL: %d%n", failed);
    }
}
