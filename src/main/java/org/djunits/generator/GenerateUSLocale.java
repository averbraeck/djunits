package org.djunits.generator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.unitsystem.UnitSystem;
import org.djunits.unit.util.UNITS;

/**
 * Generator for the locale properties file.
 * <p>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class GenerateUSLocale
{
    /**
     * Determine if a double is (very near to) a power of 10.
     * @param value double; the value to test
     * @return boolean; true if <code>value</code> is (very near to) a power of 10; false otherwise
     */
    static boolean isPowerOf10(final double value)
    {
        double log = Math.log10(value);
        double rintLog = Math.rint(log);
        boolean result = Math.abs(rintLog - log) < 0.000001;
        // System.out.println("value " + value + " is power of 10? " + result);
        return result;
    }

    /**
     * Compare two double values.
     * @param left double; the left value
     * @param right double; the right value
     * @return int; 0 if the values are equal; -1 if left should be sorted before right; +1 if right should be sorted before
     *         left
     */
    static int compareDoubles(final double left, final double right)
    {
        if (left > right)
        {
            return -1;
        }
        else if (left < right)
        {
            return 1;
        }
        return 0;
    }

    /**
     * Indicate if a unit is SI.
     * @param unit Unit&lt;?&gt;; the unit
     * @return boolean; true if the unit is SI; false otherwise
     */
    static boolean isSI(final Unit<?> unit)
    {
        return unit.getUnitSystem().equals(UnitSystem.SI_ACCEPTED) || unit.getUnitSystem().equals(UnitSystem.SI_BASE)
                || unit.getUnitSystem().equals(UnitSystem.SI_DERIVED);
    }

    /**
     * Generate the localeunit.properties file.
     * @param args String[]; the command line arguments; not used (yet)
     */
    public static void main(final String[] args)
    {
        @SuppressWarnings("unused")
        AreaUnit junk = UNITS.ACRE; // force loading of all units

        System.out.println("# Format:");
        System.out.println("# UnitType.unitname = abbreviation | description");
        System.out.println("# UnitType.unitname = abbreviation | description | text_abbreviation1 | text_abbreviation2 | ...");

        TreeMap<String, Quantity<?>> unitTypes = new TreeMap<>(Quantities.INSTANCE.getRegistry());
        for (String quantityName : unitTypes.keySet())
        {
            Quantity<?> quantity = unitTypes.get(quantityName);
            System.out.println("#");
            System.out.println("# " + quantity.getName());
            System.out.println("#");
            System.out.println(quantity.getName() + " = " + quantity.getName());
            TreeMap<String, Unit<?>> ids = new TreeMap<>(quantity.getUnitsById());
            String[] idArray = ids.keySet().toArray(new String[0]);
            Arrays.sort(idArray, new Comparator<String>()
            {
                @Override
                public int compare(String o1, String o2)
                {
                    // System.out.println("Comparing " + o1 + " to " + o2);
                    Unit<?> left = quantity.getUnitById(o1);
                    Unit<?> right = quantity.getUnitById(o2);
                    // if (left.equals(FrequencyUnit.PER_DAY) || right.equals(FrequencyUnit.PER_DAY))
                    // {
                    // System.out.println("Comparing " + o1 + " to " + o2);
                    // }
                    if (left.equals(right))
                    {
                        return o1.compareTo(o2);
                    }
                    if (left.equals(left.getQuantity().getStandardUnit()))
                    {
                        return -1;
                    }
                    if (right.equals(right.getQuantity().getStandardUnit()))
                    {
                        return 1;
                    }
                    if (isSI(left) && (!isSI(right)))
                    {
                        return -1;
                    }
                    if ((!isSI(left) && isSI(right)))
                    {
                        return 1;
                    }
                    double leftFactor = left.getScale().toStandardUnit(1.0) - left.getScale().toStandardUnit(0.0);
                    double rightFactor = right.getScale().toStandardUnit(1.0) - right.getScale().toStandardUnit(0.0);
                    if (isPowerOf10(leftFactor) && (isPowerOf10(rightFactor)))
                    {
                        return compareDoubles(leftFactor, rightFactor);
                    }
                    if (isPowerOf10(leftFactor))
                    {
                        return -1;
                    }
                    if (isPowerOf10(rightFactor))
                    {
                        return 1;
                    }
                    if (left.getName().endsWith("watt-hour") && right.getName().endsWith("watt-hour"))
                    {
                        return compareDoubles(leftFactor, rightFactor);
                    }
                    if (left.getName().endsWith("watt-hour"))
                    {
                        return -1;
                    }
                    if (right.getName().endsWith("watt-hour"))
                    {
                        return 1;
                    }
                    if (left.getName().endsWith("electronvolt") && right.getName().endsWith("electronvolt"))
                    {
                        return compareDoubles(leftFactor, rightFactor);
                    }
                    if (left.getName().endsWith("electronvolt"))
                    {
                        return -1;
                    }
                    if (right.getName().endsWith("electronvolt"))
                    {
                        return 1;
                    }
                    return left.getName().compareTo(right.getName());
                }
            });
            for (String unitId : idArray)
            {
                Unit<?> u = quantity.getUnitById(unitId);
                System.out.print(quantity.getName() + "." + u.getId() + " = "
                        + u.getDefaultDisplayAbbreviation() + " | " + u.getName());
                if (u.getAbbreviations().size() > 1)
                {
                    for (String abbreviation : u.getAbbreviations())
                    {
                        if (!abbreviation.equals(u.getDefaultDisplayAbbreviation()))
                        {
                            System.out.print(" | " + abbreviation);
                        }
                    }
                }
                System.out.println();
            }
        }
    }

}
