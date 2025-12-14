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
    private final double conversionFactorToBaseUnit;

    /**
     * Construct a Scale for linear transformations.
     * @param conversionFactorToBaseUnit the conversion factor by which this number has to be multiplied to convert it to the
     *            base (e.g., SI) unit.
     */
    public LinearScale(final double conversionFactorToBaseUnit)
    {
        Throw.when(conversionFactorToBaseUnit == 0.0, UnitRuntimeException.class,
                "conversion factor for linear scale cannnot be 0");
        this.conversionFactorToBaseUnit = conversionFactorToBaseUnit;
    }

    @Override
    public double toBaseValue(final double value)
    {
        return value * this.conversionFactorToBaseUnit;
    }

    @Override
    public double fromBaseValue(final double value)
    {
        return value / this.conversionFactorToBaseUnit;
    }

    /**
     * Retrieve the factor for conversion to the standard unit.
     * @return the factor for conversion to the standard unit
     */
    public final double getConversionFactorToBaseUnit()
    {
        return this.conversionFactorToBaseUnit;
    }

    @Override
    public boolean isBaseScale()
    {
        return this.conversionFactorToBaseUnit == 1.0;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.conversionFactorToBaseUnit);
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
        if (Double.doubleToLongBits(this.conversionFactorToBaseUnit) != Double
                .doubleToLongBits(other.conversionFactorToBaseUnit))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "LinearScale [conversionFactorToStandardUnit=" + this.conversionFactorToBaseUnit + "]";
    }

}
