package org.djunits.unit.scale;

import java.util.Objects;

import org.djunits.old.unit.util.UnitRuntimeException;
import org.djutils.exceptions.Throw;

/**
 * Scale with factor and zero point offset. We did NOT make the OffsetLinearScale extend the LinearScale to avoid problems with
 * combining scales in the Unit's deriveUnit method.
 * <p>
 * A Scale for linear transformations with an offset that has to be applied first when converting to the base (SI) unit, before
 * the scaling takes place, e.g. for Temperature. As an example, transform from Degrees Fahrenheit to Kelvin (SI). The
 * scale is K = (F + 459.67) × 5⁄9, and F = K × 9⁄5 − 459.67.
 * </p>
 * <p>
 * When we have an original scale with offset o1 and scalefactor f1, the calculation to the base unit is
 * 
 * <pre>
 * valueSI = (value1 + o1) * f1
 * </pre>
 * <p>
 * So the offset is expressed in the "unit" of the value. As an example, when we transform degrees Fahrenheit to Kelvin, the
 * factor is 5/9, and the offset is 459.67 (degrees Fahrenheit of 0 degrees Fahrenheit expressed in Kelvin). The formula
 * becomes: K = (F + 459.67) * 5/9. So 0 F is 459.67 * 5/9 = 255.372 K. For Celcius to Kelvin, the scale factor is 1, and the
 * offset 273.15. From Fahrenheit to Celcius, the offset is -32, and the factor is 5/9.
 * </p>
 * <p>
 * When we apply a second offset transformation on a scale, e.g. from Fahrenheit to Celcius to Kelvin, this works as follows: If
 * we combine a second scale factor for a derived unit with offset o2 and scalefactor f2, we need to calculate the ultimate
 * scale to the base (si) unit. The factor then becomes:
 * 
 * <pre>
 * value1  = (value2 + o2) * f2
 * valueSI = (value1 + o1) * f1 = value2 * (f1 * f2) + (f1 * f2 * o2 + f1 * o1)
 * </pre>
 * <p>
 * as an example for F --2--&gt; C --1--&gt; K: o1 = 273.15, f1 = 1, o2 = -32, f2 = 5/9: <br>
 * 110 F = 110*5/9 -32*5/9 + 273.15 = 316.483 K.
 * </p>
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public class OffsetLinearScale implements Scale
{
    /** */
    private static final long serialVersionUID = 600L;

    /** multiply by this number to convert to the base (e.g., SI) unit. */
    private final double scaleFactorToBaseUnit;

    /** The offset that has to be taken into account for scales, multiplied by the scaleFactorToBaseUnit. */
    private final double offsetToBaseUnit;

    /**
     * Construct a Scale for linear transformations with an offset, e.g. for Temperature.
     * @param scaleFactorToBaseUnit the scale factor by which this number has to be multiplied to convert it to the base
     *            (e.g., SI) unit.
     * @param offsetToBaseUnit when converting to a base unit, the offset is applied first.
     */
    public OffsetLinearScale(final double scaleFactorToBaseUnit, final double offsetToBaseUnit)
    {
        Throw.when(scaleFactorToBaseUnit == 0.0, UnitRuntimeException.class, "scale factor for linear scale cannnot be 0");
        this.scaleFactorToBaseUnit = scaleFactorToBaseUnit;
        this.offsetToBaseUnit = offsetToBaseUnit;
    }

    @Override
    public final double toBaseValue(final double value)
    {
        return (value + this.offsetToBaseUnit) * this.scaleFactorToBaseUnit;
    }

    @Override
    public final double fromBaseValue(final double value)
    {
        return value / this.scaleFactorToBaseUnit - this.offsetToBaseUnit;
    }

    /**
     * Retrieve the offset from the base unit.
     * @return the offset from the base unit
     */
    public final double getOffsetToBaseUnit()
    {
        return this.offsetToBaseUnit;
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
        return this.scaleFactorToBaseUnit == 1.0 && this.offsetToBaseUnit == 0.0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.offsetToBaseUnit, this.scaleFactorToBaseUnit);
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
        OffsetLinearScale other = (OffsetLinearScale) obj;
        return Double.doubleToLongBits(this.offsetToBaseUnit) == Double.doubleToLongBits(other.offsetToBaseUnit)
                && Double.doubleToLongBits(this.scaleFactorToBaseUnit) == Double.doubleToLongBits(other.scaleFactorToBaseUnit);
    }

    @Override
    public String toString()
    {
        return "OffsetLinearScale [offsetToBaseUnit=" + this.offsetToBaseUnit + ", scaleFactorToBaseUnit="
                + this.scaleFactorToBaseUnit + "]";
    }

}
