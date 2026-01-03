package org.djunits.unit.si;

import java.util.Arrays;

import org.djunits.quantity.SIQuantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.system.UnitSystem;
import org.djutils.exceptions.Throw;

/**
 * SIUnit stores the dimensionality of a unit using the SI standards. Angle (rad) and solid angle (sr) have been added to be
 * able to specify often used units regarding rotation.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class SIUnit implements UnitInterface<SIUnit, SIQuantity>
{
    /** The (currently) 9 dimensions we take into account: rad, sr, kg, m, s, A, K, mol, cd. */
    public static final int NUMBER_DIMENSIONS = 9;

    /** The abbreviations of the SI units we use in SIUnit. */
    private static final String[] SI_ABBREVIATIONS = new String[] {"rad", "sr", "kg", "m", "s", "A", "K", "mol", "cd"};

    /** For parsing, the mol has to be parsed before the m, otherwise the "m" from "mol" is eaten; same for "s" and "sr". */
    private static final int[] PARSE_ORDER = new int[] {0, 1, 2, 7, 3, 4, 5, 6, 8};

    /** the dimensionless SIUnit. */
    public static final SIUnit DIMLESS = new SIUnit(0, 0, 0, 0, 0, 0, 0, 0, 0);

    /**
     * The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle (sr), 2: mass (kg), 3: length
     * (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance (mol), 8: luminous intensity (cd). As an
     * example, speed is indicated as length = 1; time = -1.
     */
    private final int[] dimensions;

    /**
     * Create an immutable SIUnit instance based on a safe copy of a given dimensions specification. As an example, speed is
     * indicated as length = 1; time = -1 with the other dimensions equal to zero.
     * @param dimensions The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle (sr), 2:
     *            mass (kg), 3: length (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance (mol), 8:
     *            luminous intensity (cd).
     */
    public SIUnit(final int[] dimensions)
    {
        Throw.whenNull(dimensions, "dimensions cannot be null");
        Throw.when(dimensions.length != NUMBER_DIMENSIONS, IllegalArgumentException.class,
                "SIUnit has the wrong dimensionality: %s instead of %s", dimensions.length, NUMBER_DIMENSIONS);
        this.dimensions = dimensions.clone(); // safe copy
    }

    /**
     * Create an immutable SIUnit instance based on a safe copy of a given dimensions specification.
     * @param angle dimension of the angle (rad)
     * @param solidAngle dimension of the solidAngle (sr)
     * @param mass dimension of the mass (kg)
     * @param length dimension of the length (m)
     * @param time dimension of the time (s)
     * @param current dimension of the current (A)
     * @param temperature dimension of the temperature (K)
     * @param amountOfSubstance dimension of the amount of substance (mol)
     * @param luminousIntensity dimension of the luminous intensity (cd)
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public SIUnit(final int angle, final int solidAngle, final int mass, final int length, final int time, final int current,
            final int temperature, final int amountOfSubstance, final int luminousIntensity)
    {
        this.dimensions = new int[NUMBER_DIMENSIONS];
        this.dimensions[0] = angle;
        this.dimensions[1] = solidAngle;
        this.dimensions[2] = mass;
        this.dimensions[3] = length;
        this.dimensions[4] = time;
        this.dimensions[5] = current;
        this.dimensions[6] = temperature;
        this.dimensions[7] = amountOfSubstance;
        this.dimensions[8] = luminousIntensity;
    }

    /**
     * Parse a string representing SI dimensions to an SIUnit object. Example: SIUnit.of("kgm/s2") and SIUnit.of("kgms-2") will
     * both be translated to a dimensions object with vector {0,0,1,1,-2,0,0,0,0}. It is allowed to use 0 or 1 for the
     * dimensions. Having the same unit in the numerator and the denominator is not seen as a problem: the values are subtracted
     * from each other, so m/m will have a length dimensionality of 0. Dimensions between -9 and 9 are allowed. Spaces, periods
     * and ^ are taken out, but other characters are not allowed and will lead to a UnitException. The order of allowed units is
     * arbitrary, so "kg/ms2" is accepted as well as "kg/s^2.m".
     * @param siString the string to parse
     * @return the corresponding SI dimensions
     * @throws UnitRuntimeException when the string could not be parsed into dimensions
     */
    public static SIUnit of(final String siString) throws UnitRuntimeException
    {
        Throw.whenNull(siString, "siString cannot be null");
        String dimString = siString.replaceAll("[ .^]", "");
        if (dimString.contains("/"))
        {
            String[] parts = dimString.split("\\/");
            if (parts.length != 2)
            {
                throw new UnitRuntimeException("SI String " + dimString + " contains more than one division sign");
            }
            int[] numerator = parse(parts[0]);
            int[] denominator = parse(parts[1]);
            for (int i = 0; i < NUMBER_DIMENSIONS; i++)
            {
                numerator[i] -= denominator[i];
            }
            return new SIUnit(numerator);
        }
        return new SIUnit(parse(dimString));
    }

    /**
     * Translate a string representing SI dimensions to an SIUnit object. Example: SIUnit.of("kgm2") is translated to a vector
     * {0,0,1,2,0,0,0,0,0}. It is allowed to use 0 or 1 for the dimensions. Dimensions between -9 and 9 are allowed. The parsing
     * is quite lenient: periods and carets (^) are taken out, and the order can be arbitrary, so "kgms-2" is accepted as well
     * as "m.s^-2.kg". Note that the empty string parses to the dimensionless unit.
     * @param siString concatenation of SI units with positive or negative dimensions. No divisions sign is allowed.
     * @return a vector of length <code>NUMBER_DIMENSIONS</code> with the dimensions for the SI units
     * @throws UnitRuntimeException when the String cannot be parsed, e.g. due to units not being recognized
     */
    private static int[] parse(final String siString) throws UnitRuntimeException
    {
        Throw.whenNull(siString, "siString cannot be null");
        int[] result = new int[NUMBER_DIMENSIONS];
        if (siString.equals("1") || siString.length() == 0)
        {
            return result;
        }
        String copy = siString;
        int copyLength = copy.length();
        while (copyLength > 0)
        {
            // find the next unit
            for (int j = 0; j < SI_ABBREVIATIONS.length; j++)
            {
                int i = PARSE_ORDER[j];
                String si = SI_ABBREVIATIONS[i];
                if (copy.startsWith(si))
                {
                    if (result[i] != 0)
                    {
                        throw new UnitRuntimeException("SI string " + siString + " has a double entry for unit " + si);
                    }
                    copy = copy.substring(si.length());
                    if (copy.length() == 0)
                    {
                        result[i] = 1;
                        break;
                    }
                    else if (copy.startsWith("-"))
                    {
                        if (copy.length() == 1)
                        {
                            throw new UnitRuntimeException("SI string " + siString + " ends with a minus sign");
                        }
                        if (Character.isDigit(copy.charAt(1)))
                        {
                            result[i] = (-1 * (copy.charAt(1) - '0'));
                            copy = copy.substring(2);
                            break;
                        }
                        throw new UnitRuntimeException(
                                "SI string " + siString + " has a minus sign for unit " + si + " but no dimension");
                    }
                    else if (Character.isDigit(copy.charAt(0)))
                    {
                        result[i] = (copy.charAt(0) - '0');
                        copy = copy.substring(1);
                        break;
                    }
                    else
                    {
                        result[i] = 1;
                        break;
                    }
                }
            }
            if (copy.length() == copyLength)
            {
                // we did not parse anything... wrong character
                break;
            }
            copyLength = copy.length();
        }
        if (copy.length() != 0)
        {
            throw new UnitRuntimeException("Trailing information in SI string " + siString);
        }
        return result;
    }

    /**
     * Returns a safe copy of the SI abbreviations (a public static final String[] is mutable).
     * @return a safe copy of the SI abbreviations
     */
    public String[] siAbbreviations()
    {
        return SI_ABBREVIATIONS.clone();
    }

    /**
     * Return a safe copy of the exponents of the SI dimensions in the order rad, sr, kg, m, s, A, K, mol, cd. Since it is a
     * safe copy, calculations can be carried out on the int[] return value.
     * @return a safe copy of the exponents of the SI dimensions in the order rad, sr, kg, m, s, A, K, mol, cd
     */
    public int[] siDimensions()
    {
        return this.dimensions.clone();
    }

    /**
     * Add a set of SI dimensions to this SIUnit. Note: as dimensions are considered to be immutable, a new dimension is
     * returned. The original dimension (<code>this</code>) remains unaltered.
     * @param other the dimensions to add (usually as a result of multiplication of scalars)
     * @return the new dimensions with the dimensions of this object plus the dimensions in the parameter
     */
    public SIUnit plus(final SIUnit other)
    {
        int[] result = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = this.dimensions[i] + other.dimensions[i];
        }
        return new SIUnit(result);
    }

    /**
     * Subtract a set of SI dimensions from this SIUnit. Note: as dimensions are considered to be immutable, a new dimension is
     * returned. The original dimension (<code>this</code>) remains unaltered.
     * @param other the dimensions to subtract (usually as a result of division of scalars)
     * @return the new dimensions with the dimensions of this object minus the dimensions in the parameter
     */
    public SIUnit minus(final SIUnit other)
    {
        int[] result = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = this.dimensions[i] - other.dimensions[i];
        }
        return new SIUnit(result);
    }

    /**
     * Invert a set of SI dimensions; instead of m/s we get s/m. Note: as dimensions are considered to be immutable, a new
     * dimension is returned. The original dimension (<code>this</code>) remains unaltered.
     * @return the new dimensions that are the inverse of the dimensions in this object
     */
    public SIUnit invert()
    {
        int[] result = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = -this.dimensions[i];
        }
        return new SIUnit(result);
    }

    /**
     * Raise a set of SI dimensions to the n-th power. Note: as dimensions are considered to be immutable, a new dimension is
     * returned. The original dimension (<code>this</code>) remains unaltered.
     * @param n the power to which to raise this set of dimensions
     * @return the new dimensions with the dimensions of this object raised to the n-th power
     */
    public SIUnit pow(final int n)
    {
        int[] result = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = this.dimensions[i] * n;
        }
        return new SIUnit(result);
    }

    /**
     * Add two SIUnit and return the new SIUnit. Usually, dimensions are added as a result of multiplication of scalars.
     * @param dim1 the first set of dimensions
     * @param dim2 the second set of dimensions
     * @return the new dimensions with the sum of the dimensions in the parameters
     */
    public static SIUnit add(final SIUnit dim1, final SIUnit dim2)
    {
        int[] dim = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            dim[i] = dim1.dimensions[i] + dim2.dimensions[i];
        }
        return new SIUnit(dim);
    }

    /**
     * Subtract an SIUnit (dim2) from another SIUnit (dim1) and return the new SIUnit. Usually, dimensions are added as a result
     * of division of scalars.
     * @param dim1 the first set of dimensions
     * @param dim2 the second set of dimensions that will be subtracted from dim1
     * @return the new dimensions with the difference of the dimensions in the parameters
     */
    public static SIUnit subtract(final SIUnit dim1, final SIUnit dim2)
    {
        int[] dim = new int[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            dim[i] = dim1.dimensions[i] - dim2.dimensions[i];
        }
        return new SIUnit(dim);
    }

    @Override
    public SIQuantity ofSi(final double si)
    {
        return new SIQuantity(si, this);
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s^2" or "kg.m.s^-2" from this SIUnit.
     * @param divided if true, return m/s2 for acceleration; if false return ms-2
     * @param separator add this string between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @param powerPrefix the prefix for the power, e.g., "^" or "&lt;sup&gt;"
     * @param powerPostfix the postfix for the power, e.g., "&lt;/sup&gt;"
     * @return a formatted string for this SIUnit
     */
    public String toString(final boolean divided, final String separator, final String powerPrefix, final String powerPostfix)
    {
        StringBuffer s = new StringBuffer();
        boolean first = true;
        boolean negative = false;
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            if (this.dimensions[i] < 0)
            {
                negative = true;
            }
            if ((!divided && this.dimensions[i] != 0) || (divided && this.dimensions[i] > 0))
            {
                if (!first)
                {
                    s.append(separator);
                }
                else
                {
                    first = false;
                }
                s.append(SI_ABBREVIATIONS[i]);
                if (this.dimensions[i] != 1)
                {
                    s.append(powerPrefix);
                    s.append(this.dimensions[i]);
                    s.append(powerPostfix);
                }
            }
        }
        if (s.length() == 0)
        {
            s.append("1");
        }
        if (divided && negative)
        {
            s.append("/");
        }
        if (divided)
        {
            first = true;
            for (int i = 0; i < NUMBER_DIMENSIONS; i++)
            {
                if (this.dimensions[i] < 0)
                {
                    if (!first)
                    {
                        s.append(separator);
                    }
                    else
                    {
                        first = false;
                    }
                    s.append(SI_ABBREVIATIONS[i]);
                    if (this.dimensions[i] < -1)
                    {
                        s.append(powerPrefix);
                        s.append(-this.dimensions[i]);
                        s.append(powerPostfix);
                    }
                }
            }
        }
        if (s.toString().equals("1"))
        {
            return "";
        }
        return s.toString();
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s2" or "kg.m.s-2" from this SIUnit.
     * @param divided if true, return m/s2 for acceleration; if false return ms-2
     * @param separator if true, add a period between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @return a formatted string describing this SIUnit
     */
    public String toString(final boolean divided, final boolean separator)
    {
        return toString(divided, separator ? "." : "", "", "");
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s^2" or "kg.m.s^-2" from this SIUnit.
     * @param divided if true, return m/s2 for acceleration; if false return ms-2
     * @param separator if true, add a period between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @param power if true, add a ^ sign before the power, e.g., "kg.m^2/s^3" instead of "kg.m2/s3"
     * @return a formatted string describing this SIUnit
     */
    public String toString(final boolean divided, final boolean separator, final boolean power)
    {
        return toString(divided, separator ? "." : "", power ? "^" : "", "");
    }

    /**
     * Return a string such as "kgm/s<sup>2</sup>" or or "kg.m.s<sup>-2</sup>" from this SIUnit.
     * @param divided if true, return "m/s<sup>2</sup>" for acceleration; if false return "ms<sup>-2</sup>"
     * @param separator if true, add a period between successive units, e.g. kg.m.s<sup>-2</sup>
     * @return a formatted string describing this SIUnit
     */
    public String toHTMLString(final boolean divided, final boolean separator)
    {
        return toString(divided, separator ? "." : "", "<sup>", "</sup>");
    }

    @Override
    public String toString()
    {
        return Arrays.toString(this.dimensions);
    }

    @Override
    public String getId()
    {
        return toString(true, false);
    }

    @Override
    public Scale getScale()
    {
        return IdentityScale.SCALE;
    }

    @Override
    public UnitSystem getUnitSystem()
    {
        return UnitSystem.SI_BASE;
    }

    @Override
    public SIUnit siUnit()
    {
        return this;
    }

    @Override
    public SIUnit getBaseUnit()
    {
        return this;
    }

    @Override
    public String getTextualAbbreviation()
    {
        return toString(true, false);
    }

    @Override
    public String getDisplayAbbreviation()
    {
        return toString(true, false);
    }

    @Override
    public String getName()
    {
        return toString(true, false);
    }

    @Override
    public String getStoredTextualAbbreviation()
    {
        return toString(true, false);
    }

    @Override
    public String getStoredDisplayAbbreviation()
    {
        return toString(true, false);
    }

    @Override
    public String getStoredName()
    {
        return toString(true, false);
    }

    @Override
    public SIUnit setSiPrefix(final SIPrefix siPrefix)
    {
        return this;
    }

    @Override
    public SIUnit setSiPrefix(final String prefix)
    {
        return this;
    }

    @Override
    public SIUnit setSiPrefixKilo(final String prefix)
    {
        return this;
    }

    @Override
    public SIUnit setSiPrefixPer(final String prefix)
    {
        return this;
    }

    @Override
    public SIPrefix getSiPrefix()
    {
        return null;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.dimensions);
        return result;
    }

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SIUnit other = (SIUnit) obj;
        if (!Arrays.equals(this.dimensions, other.dimensions))
            return false;
        return true;
    }

}
