package org.djunits.unit.scale;

import org.djunits.Throw;
import org.djunits.unit.util.UnitRuntimeException;

/**
 * A Scale for linear transformations not involving a zero-offset, e.g. for Length, Time, Area. <br>
 * A linear scale is a scale that is linearly relates a unit to the underlying SI standard unit. E.g. Mile is linearly related
 * to meter (the SI unit for length) and the conversion is zero-based (0 miles equals 0 meter). Unlike temperature in degrees
 * Celsius which is <strong>not</strong> linearly related to the Kelvin (the SI unit for temperature) because the conversion is
 * not zero-based (0&deg;C is 273.15K).
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class LinearScale implements Scale
{
    /** */
    private static final long serialVersionUID = 20151011L;

    /** multiply by this number to convert to the standard (e.g., SI) unit. */
    private final double conversionFactorToStandardUnit;

    /**
     * Construct a Scale for linear transformations.
     * @param conversionFactorToStandardUnit double; the conversion factor by which this number has to be multiplied to convert
     *            it to the standard (e.g., SI) unit.
     */
    public LinearScale(final double conversionFactorToStandardUnit)
    {
        Throw.when(conversionFactorToStandardUnit == 0.0, UnitRuntimeException.class,
                "conversion factor for linear scale cannnot be 0");
        this.conversionFactorToStandardUnit = conversionFactorToStandardUnit;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("checkstyle:designforextension")
    @Override
    public double toStandardUnit(final double value)
    {
        return value * this.conversionFactorToStandardUnit;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("checkstyle:designforextension")
    @Override
    public double fromStandardUnit(final double value)
    {
        return value / this.conversionFactorToStandardUnit;
    }

    /**
     * Retrieve the factor for conversion to the standard unit.
     * @return double; the factor for conversion to the standard unit
     */
    public final double getConversionFactorToStandardUnit()
    {
        return this.conversionFactorToStandardUnit;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBaseSIScale()
    {
        return this.conversionFactorToStandardUnit == 1.0;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.conversionFactorToStandardUnit);
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
        LinearScale other = (LinearScale) obj;
        if (Double.doubleToLongBits(this.conversionFactorToStandardUnit) != Double
                .doubleToLongBits(other.conversionFactorToStandardUnit))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "LinearScale [conversionFactorToStandardUnit=" + this.conversionFactorToStandardUnit + "]";
    }

}
