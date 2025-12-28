package org.djunits.test;

import java.util.Comparator;
import java.util.Map;

import org.djunits.quantity.AbsoluteTemperature;
import org.djunits.quantity.Duration;
import org.djunits.quantity.Length;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Units;

/**
 * Test.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class Test
{
    /** */
    private Test()
    {
        length();
        temp();
        duration();
        printUnits();
    }

    /** */
    private void length()
    {
        System.out.println("\nLENGTH");
        Length length = new Length(5.0, "m");
        System.out.println(length);

        Length l2 = new Length(5.0, Length.Unit.KILOMETER);
        System.out.println(l2.toDisplayString());
        System.out.println("SI = " + l2.si() + " [" + l2.getDisplayUnit().getBaseUnit().getId() + "]");

        Length l3 = new Length(10.0, "Qm");
        System.out.println(l3);
        System.out.println(l3.si());

        Length l4 = new Length(Math.PI, "mum");
        System.out.println(l4);
        System.out.println(l4.plus(length));
        System.out.println(length.plus(l4));
    }

    /** */
    private void temp()
    {
        System.out.println("\nTEMPERATURE");
        System.out.println("Temperature");
        var t1 = AbsoluteTemperature.valueOf("10 degC");
        System.out.println(t1 + "  si=" + t1.si());
        System.out.println(AbsoluteTemperature.of(10, "K"));
    }

    /** */
    private void duration()
    {
        System.out.println("\nDURATION");
        var d1 = Duration.of(42.0, "ms");
        System.out.println("42 ms: " + d1);
        var f1 = d1.reciprocal();
        System.out.println("reciprocal: " + f1);
        var d2 = Duration.valueOf("1 day");
        System.out.println("1 day: " + d2 + " = " + d2.setDisplayUnit("s") + " seconds");
        System.out.println("1 day: " + d2 + " = " + d2.setDisplayUnit(Duration.Unit.SECOND));
    }

    /** */
    private void printUnits()
    {
        System.out.println("\n\nUNITS");
        Units.registerStandardUnits();
        var unitMap = Units.registeredUnits();
        unitMap.entrySet().stream().sorted(Comparator.comparing(e -> Units.unitClassName(e.getKey()))).forEach(entry ->
        {
            Class<?> clazz = entry.getKey();
            Map<String, UnitInterface<?>> inner = entry.getValue();
            System.out.printf("%n%s%n", Units.unitClassName(clazz));
            System.out.printf("%-15s %-10s %-40s = %s%n", "Textual", "Display", "Name", "Convert to base value");
            System.out.println("-".repeat(97));

            inner.entrySet().stream()
                    // sort by key; change to Comparator.comparing(e -> e.getValue().getNr()) if you prefer sorting by Nr
                    .sorted(Comparator.comparing(e -> e.getValue().toBaseValue(1.0))).forEach(e ->
                    {
                        UnitInterface<?> v = e.getValue();
                        System.out.printf("%-15s %-10s %-40s = %-22s %s%n", e.getKey(), v.getDisplayAbbreviation(), v.getName(),
                                v.toBaseValue(1.0), v.getBaseUnit().getDisplayAbbreviation());
                    });
        });
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        new Test();
    }

}
