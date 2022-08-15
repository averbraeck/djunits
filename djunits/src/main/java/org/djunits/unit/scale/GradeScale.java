package org.djunits.unit.scale;

/**
 * A Scale for transforming a slope as a grade, where 45 degrees is 1, and 90 degrees is infinite, to radians. The formula is:
 * angle = atan(grade). The factor that is given, is the factor by which the value is multiplied to get a grade. When a scale is
 * constructed for e.g. ratio as a percentage (100% instead of 1), the conversionFactor to apply is 0.01.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class GradeScale implements Scale
{
    /** */
    private static final long serialVersionUID = 20151011L;

    /** Multiply by this number to convert to the standard (e.g., SI) unit. */
    private final double conversionFactorToGrade;

    /**
     * Construct a Scale for transforming a slope as a grade, where 45 degrees is 1, and 90 degrees is infinite, to radians.
     * @param conversionFactorToGrade double; the conversion factor by which this number has to be multiplied to convert it to
     *            the standard (e.g., SI) unit.
     */
    public GradeScale(final double conversionFactorToGrade)
    {
        this.conversionFactorToGrade = conversionFactorToGrade;
    }

    /** {@inheritDoc} */
    @Override
    public final double toStandardUnit(final double value)
    {
        return Math.atan(value * this.conversionFactorToGrade);
    }

    /** {@inheritDoc} */
    @Override
    public final double fromStandardUnit(final double value)
    {
        return Math.tan(value) / this.conversionFactorToGrade;
    }

    /**
     * @return conversionFactorToGrade
     */
    public final double getConversionFactorToGrade()
    {
        return this.conversionFactorToGrade;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBaseSIScale()
    {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.conversionFactorToGrade);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GradeScale other = (GradeScale) obj;
        if (Double.doubleToLongBits(this.conversionFactorToGrade) != Double.doubleToLongBits(other.conversionFactorToGrade))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "GradeScale [conversionFactorToGrade=" + this.conversionFactorToGrade + "]";
    }

}
