package org.djunits.unit.scale;

import org.djunits.old.unit.util.UnitRuntimeException;
import org.djutils.exceptions.Throw;

/**
 * A Scale for linear transformations not involving a zero-offset, e.g. for Length, Time, Area. <br>
 * A linear scale is a scale that is linearly relates a unit to the underlying SI standard unit. E.g. Mile is linearly related
 * to meter (the SI unit for length) and the conversion is zero-based (0 miles equals 0 meter). Unlike temperature in degrees
 * Celsius which is <strong>not</strong> linearly related to the Kelvin (the SI unit for temperature) because the conversion is
 * not zero-based (0&deg;C is 273.15K).
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public class LinearScale implements Scale
{
    /** */
    private static final long serialVersionUID = 500L;

    /** multiply by this number to convert to the base (e.g., SI) unit. */
    private final double scaleFactorToBaseUnit;

    /**
     * Construct a Scale for linear transformations. To transform, e.g., a minute to a second, a scale factor of 60 is used.
     * @param scaleFactorToBaseUnit the conversion factor by which this number has to be multiplied to convert it to the base
     *            (e.g., SI) unit.
     */
    public LinearScale(final double scaleFactorToBaseUnit)
    {
        Throw.when(scaleFactorToBaseUnit == 0.0, UnitRuntimeException.class, "scale factor for linear scale cannnot be 0");
        this.scaleFactorToBaseUnit = scaleFactorToBaseUnit;
    }

    /**
     * Construct a Scale for linear transformations, with a numerator and denominator. To transform, e.g., m/s to km/h, a
     * numerator of 1000 (a km is 1000 m) and a denominator of 3600 (an hour is 3600 seconds) is used.
     * @param numerator the factor by which this number has to be multiplied to convert it to the base (e.g., SI) unit.
     * @param denominator the factor by which this number has to be divided to convert it to the base (e.g., SI) unit.
     */
    public LinearScale(final double numerator, final double denominator)
    {
        Throw.when(numerator == 0.0, UnitRuntimeException.class, "numerator in scale factor for linear scale cannnot be 0");
        Throw.when(denominator == 0.0, UnitRuntimeException.class, "denominator in scale factor for linear scale cannnot be 0");
        this.scaleFactorToBaseUnit = numerator / denominator;
    }

    @Override
    public double toBaseValue(final double value)
    {
        return value * this.scaleFactorToBaseUnit;
    }

    @Override
    public double fromBaseValue(final double value)
    {
        return value / this.scaleFactorToBaseUnit;
    }

    /**
     * Retrieve the factor for conversion to the standard unit.
     * @return the factor for conversion to the standard unit
     */
    public final double getScaleFactorToBaseUnit()
    {
        return this.scaleFactorToBaseUnit;
    }

    @Override
    public boolean isBaseScale()
    {
        return this.scaleFactorToBaseUnit == 1.0;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.scaleFactorToBaseUnit);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

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
        LinearScale other = (LinearScale) obj;
        if (Double.doubleToLongBits(this.scaleFactorToBaseUnit) != Double.doubleToLongBits(other.scaleFactorToBaseUnit))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "LinearScale [scaleFactorToBaseUnit=" + this.scaleFactorToBaseUnit + "]";
    }

}
