package org.djunits.unit.si;

import java.io.Serializable;
import java.util.Arrays;

import org.djunits.Throw;
import org.djunits.unit.util.UnitException;

/**
 * SIDimensions stores the dimensionality of a unit using the SI standards. Angle (rad) and solid angle (sr) have been added to
 * be able to specify often used units regarding rotation.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class SIDimensions implements Serializable
{
    /** */
    private static final long serialVersionUID = 20190818L;

    /** The (currently) 9 dimensions we take into account: rad, sr, kg, m, s, A, K, mol, cd. */
    public static final int NUMBER_DIMENSIONS = 9;

    /** The default denominator which consists of all "1"s. */
    private static final byte[] UNIT_DENOMINATOR = new byte[] {1, 1, 1, 1, 1, 1, 1, 1, 1};

    /** The abbreviations of the SI units we use in SIDimensions. */
    private static final String[] SI_ABBREVIATIONS = new String[] {"rad", "sr", "kg", "m", "s", "A", "K", "mol", "cd"};

    /** For parsing, the mol has to be parsed before the m, otherwise the "m" from "mol" is eaten; same for "s" and "sr". */
    private static final int[] PARSE_ORDER = new int[] {0, 1, 2, 7, 3, 4, 5, 6, 8};

    /**
     * The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle (sr), 2: mass (kg), 3: length
     * (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance (mol), 8: luminous intensity (cd). As an
     * example, speed is indicated as length = 1; time = -1.
     */
    private final byte[] dimensions;

    /** In case the dimensions are fractional, the denominator will contain values different from 1. */
    private final byte[] denominator;

    /** Stores whether the dimensions are fractional or not. */
    private final boolean fractional;

    /**
     * Create an immutable SIDimensions instance based on a safe copy of a given dimensions specification. As an example, speed
     * is indicated as length = 1; time = -1 with the other dimensions equal to zero.
     * @param dimensions byte[]; The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle
     *            (sr), 2: mass (kg), 3: length (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance
     *            (mol), 8: luminous intensity (cd).
     */
    public SIDimensions(final byte[] dimensions)
    {
        Throw.whenNull(dimensions, "dimensions cannot be null");
        Throw.when(dimensions.length != NUMBER_DIMENSIONS, SIRuntimeException.class, "SIDimensions wrong dimensionality");
        this.dimensions = dimensions.clone(); // safe copy
        this.denominator = UNIT_DENOMINATOR;
        this.fractional = false;
    }

    /**
     * Create an immutable fractional SIDimensions instance based on a safe copy of a given specification, separated in a
     * numerator and a denominator.
     * @param numerator byte[]; The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle (sr),
     *            2: mass (kg), 3: length (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance (mol), 8:
     *            luminous intensity (cd).
     * @param denominator byte[]; The (currently) 9 dimensions of the SI unit we distinguish: 0: angle (rad), 1: solid angle
     *            (sr), 2: mass (kg), 3: length (m), 4: time (s), 5: current (A), 6: temperature (K), 7: amount of substance
     *            (mol), 8: luminous intensity (cd).
     */
    protected SIDimensions(final byte[] numerator, final byte[] denominator)
    {
        // TODO all operators on fractional dimensions
        Throw.whenNull(numerator, "numerator cannot be null");
        Throw.whenNull(denominator, "denominator cannot be null");
        Throw.when(numerator.length != NUMBER_DIMENSIONS, SIRuntimeException.class, "numerator has wrong dimensionality");
        Throw.when(denominator.length != NUMBER_DIMENSIONS, SIRuntimeException.class, "denominator has wrong dimensionality");
        this.dimensions = numerator.clone(); // safe copy
        this.denominator = denominator.clone(); // safe copy
        this.fractional = !Arrays.equals(denominator, UNIT_DENOMINATOR);
    }

    /**
     * Create an immutable SIDimensions instance based on a safe copy of a given dimensions specification.
     * @param angle int; dimension of the angle (rad)
     * @param solidAngle int; dimension of the solidAngle (sr)
     * @param mass int; dimension of the mass (kg)
     * @param length int; dimension of the length (m)
     * @param time int; dimension of the time (s)
     * @param current int; dimension of the current (A)
     * @param temperature int; dimension of the temperature (K)
     * @param amountOfSubstance int; dimension of the amount of substance (mol)
     * @param luminousIntensity int; dimension of the luminous intensity (cd)
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public SIDimensions(final int angle, final int solidAngle, final int mass, final int length, final int time,
            final int current, final int temperature, final int amountOfSubstance, final int luminousIntensity)
    {
        this.dimensions = new byte[NUMBER_DIMENSIONS];
        this.dimensions[0] = (byte) angle;
        this.dimensions[1] = (byte) solidAngle;
        this.dimensions[2] = (byte) mass;
        this.dimensions[3] = (byte) length;
        this.dimensions[4] = (byte) time;
        this.dimensions[5] = (byte) current;
        this.dimensions[6] = (byte) temperature;
        this.dimensions[7] = (byte) amountOfSubstance;
        this.dimensions[8] = (byte) luminousIntensity;
        this.denominator = UNIT_DENOMINATOR;
        this.fractional = false;
    }

    /**
     * Parse a string representing SI dimensions to an SIDimensions object. Example: SIDimensions.of("kgm/s2") and
     * SIDimensions.of("kgms-2") will both be translated to a dimensions object with vector {0,0,1,1,-2,0,0,0,0}. It is allowed
     * to use 0 or 1 for the dimensions. Having the same unit in the numerator and the denominator is not seen as a problem: the
     * values are subtracted from each other, so m/m will have a length dimensionality of 0. Dimensions between -9 and 9 are
     * allowed. Spaces, periods and ^ are taken out, but other characters are not allowed and will lead to a UnitException. The
     * order of allowed units is arbitrary, so "kg/ms2" is accepted as well as "kg/s^2.m".
     * @param siString String; the string to parse
     * @return SIDimension; the corresponding SI dimensions
     * @throws UnitException when the string could not be parsed into dimensions
     */
    public static SIDimensions of(final String siString) throws UnitException
    {
        Throw.whenNull(siString, "siString cannot be null");
        String dimString = siString.replaceAll("[ .^]", "");
        // TODO fractional: ^(-1/2)
        if (dimString.contains("/"))
        {
            String[] parts = dimString.split("\\/");
            if (parts.length != 2)
            {
                throw new UnitException("SI String " + dimString + " contains more than one division sign");
            }
            byte[] numerator = parse(parts[0]);
            byte[] denominator = parse(parts[1]);
            for (int i = 0; i < NUMBER_DIMENSIONS; i++)
            {
                numerator[i] -= denominator[i];
            }
            return new SIDimensions(numerator);
        }
        return new SIDimensions(parse(dimString));
    }

    /**
     * Translate a string representing SI dimensions to an SIDimensions object. Example: SIDimensions.of("kgm2") is translated
     * to a vector {0,0,1,2,0,0,0,0,0}. It is allowed to use 0 or 1 for the dimensions. Dimensions between -9 and 9 are allowed.
     * The parsing is quite lenient: periods and carets (^) are taken out, and the order can be arbitrary, so "kgms-2" is
     * accepted as well as "m.s^-2.kg"
     * @param siString String; concatenation of SI units with positive or negative dimensions. No divisions sign is allowed.
     * @return byte[]; a vector of length <code>NUMBER_DIMENSIONS</code> with the dimensions for the SI units
     * @throws UnitException when the String cannot be parsed, e.g. due to units not being recognized
     */
    private static byte[] parse(final String siString) throws UnitException
    {
        Throw.whenNull(siString, "siString cannot be null");
        byte[] result = new byte[NUMBER_DIMENSIONS];
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
                        throw new UnitException("SI string " + siString + " has a double entry for unit " + si);
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
                            throw new UnitException("SI string " + siString + " ends with a minus sign");
                        }
                        if (Character.isDigit(copy.charAt(1)))
                        {
                            result[i] = (byte) (-1 * (copy.charAt(1) - '0'));
                            copy = copy.substring(2);
                            break;
                        }
                        throw new UnitException(
                                "SI string " + siString + " has a minus sign for unit " + si + " but no dimension");
                    }
                    else if (Character.isDigit(copy.charAt(0)))
                    {
                        result[i] = (byte) (copy.charAt(0) - '0');
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
            throw new UnitException("Trailing information in SI string " + siString);
        }
        return result;
    }

    /**
     * Returns a safe copy of the SI abbreviations (a public static final String[] is mutable).
     * @return String[]; a safe copy of the SI abbreviations
     */
    public String[] siAbbreviations()
    {
        return SI_ABBREVIATIONS.clone();
    }

    /**
     * Add a set of SI dimensions to this SIDimensions. Note: as dimensions are considered to be immutable, a new dimension is
     * returned. The original dimension (<code>this</code>) remains unaltered.
     * @param other SIDimensions; the dimensions to add (usually as a result of multiplication of scalars)
     * @return SIDimensions; the new dimensions with the dimensions of this object plus the dimensions in the parameter
     */
    public SIDimensions plus(final SIDimensions other)
    {
        byte[] result = new byte[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = (byte) (this.dimensions[i] + other.dimensions[i]);
        }
        return new SIDimensions(result);
    }

    /**
     * Subtract a set of SI dimensions from this SIDimensions. Note: as dimensions are considered to be immutable, a new
     * dimension is returned. The original dimension (<code>this</code>) remains unaltered.
     * @param other SIDimensions; the dimensions to subtract (usually as a result of division of scalars)
     * @return SIDimensions; the new dimensions with the dimensions of this object minus the dimensions in the parameter
     */
    public SIDimensions minus(final SIDimensions other)
    {
        byte[] result = new byte[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = (byte) (this.dimensions[i] - other.dimensions[i]);
        }
        return new SIDimensions(result);
    }

    /**
     * Invert a set of SI dimensions; instead of m/s we get s/m. Note: as dimensions are considered to be immutable, a new
     * dimension is returned. The original dimension (<code>this</code>) remains unaltered.
     * @return SIDimensions; the new dimensions that are the inverse of the dimensions in this object
     */
    public SIDimensions invert()
    {
        byte[] result = new byte[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            result[i] = (byte) (-this.dimensions[i]);
        }
        return new SIDimensions(result);
    }

    /**
     * Add two SIDimensions and return the new SIDimensions. Usually, dimensions are added as a result of multiplication of
     * scalars.
     * @param dim1 SIDimensions; the first set of dimensions
     * @param dim2 SIDimensions; the second set of dimensions
     * @return the new dimensions with the sum of the dimensions in the parameters
     */
    public static SIDimensions add(final SIDimensions dim1, final SIDimensions dim2)
    {
        byte[] dim = new byte[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            dim[i] = (byte) (dim1.dimensions[i] + dim2.dimensions[i]);
        }
        return new SIDimensions(dim);
    }

    /**
     * Subtract an SIDimensions (dim2) from another SIDimensions (dim1) and return the new SIDimensions. Usually, dimensions are
     * added as a result of division of scalars.
     * @param dim1 SIDimensions; the first set of dimensions
     * @param dim2 SIDimensions; the second set of dimensions that will be subtracted from dim1
     * @return the new dimensions with the difference of the dimensions in the parameters
     */
    public static SIDimensions subtract(final SIDimensions dim1, final SIDimensions dim2)
    {
        byte[] dim = new byte[NUMBER_DIMENSIONS];
        for (int i = 0; i < NUMBER_DIMENSIONS; i++)
        {
            dim[i] = (byte) (dim1.dimensions[i] - dim2.dimensions[i]);
        }
        return new SIDimensions(dim);
    }

    /**
     * Indicate whether this SIDImensions contains one or more fractional dimensions.
     * @return boolean; whether this SIDImensions contains one or more fractional dimensions
     */
    public boolean isFractional()
    {
        return this.fractional;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.denominator);
        result = prime * result + Arrays.hashCode(this.dimensions);
        return result;
    }

    /** {@inheritDoc} */
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
        SIDimensions other = (SIDimensions) obj;
        if (!Arrays.equals(this.denominator, other.denominator))
            return false;
        if (!Arrays.equals(this.dimensions, other.dimensions))
            return false;
        return true;
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s^2" or "kg.m.s^-2" from this SIDimensions.
     * @param divided boolean; if true, return m/s2 for acceleration; if false return ms-2
     * @param separator String; add this string between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @param powerPrefix String; the prefix for the power, e.g., "^" or "<sup>"
     * @param powerPostfix String; the postfix for the power, e.g., "</sup>"
     * @return String; a formatted string for this SIDimensions
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
        return s.toString();
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s2" or "kg.m.s-2" from this SIDimensions.
     * @param divided boolean; if true, return m/s2 for acceleration; if false return ms-2
     * @param separator boolean; if true, add a period between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @return String; a formatted string describing this SIDimensions
     */
    public String toString(final boolean divided, final boolean separator)
    {
        return toString(divided, separator ? "." : "", "", "");
    }

    /**
     * Return a string such as "kgm/s2" or "kg.m/s^2" or "kg.m.s^-2" from this SIDimensions.
     * @param divided boolean; if true, return m/s2 for acceleration; if false return ms-2
     * @param separator boolean; if true, add a period between successive units, e.g. kg.m.s-2 instead of kgms-2
     * @param power boolean; if true, add a ^ sign before the power, e.g., "kg.m^2/s^3" instead of "kg.m2/s3"
     * @return String; a formatted string describing this SIDimensions
     */
    public String toString(final boolean divided, final boolean separator, final boolean power)
    {
        return toString(divided, separator ? "." : "", power ? "^" : "", "");
    }

    /**
     * Return a string such as "kgm/s<sup>2</sup>" or or "kg.m.s<sup>-2</sup>" from this SIDimensions.
     * @param divided boolean; if true, return "m/s<sup>2</sup>" for acceleration; if false return "ms<sup>-2</sup>"
     * @param separator boolean; if true, add a period between successive units, e.g. kg.m.s<sup>-2</sup>
     * @return String; a formatted string describing this SIDimensions
     */
    public String toHTMLString(final boolean divided, final boolean separator)
    {
        return toString(divided, separator ? "." : "", "<sup>", "</sup>");
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        if (this.fractional)
        {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < NUMBER_DIMENSIONS; i++)
            {
                if (i > 0)
                {
                    sb.append(", ");
                }
                if (this.denominator[i] != 1 && this.dimensions[i] != 0)
                {
                    sb.append(this.dimensions[i] + "/" + this.denominator[i]);
                }
                else
                {
                    sb.append(this.dimensions[i]);
                }
            }
            sb.append("]");
            return sb.toString();
        }
        else
        {
            return Arrays.toString(this.dimensions);
        }
    }

}
