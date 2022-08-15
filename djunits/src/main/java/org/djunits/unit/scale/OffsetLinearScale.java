package org.djunits.unit.scale;

/**
 * Scale with factor and zero point offset.
 * <p>
 * A Scale for linear transformations with an offset that has to be applied first when converting to the standard (SI) unit,
 * before the scaling takes place, e.g. for Temperature. As an example, transform from Degrees Fahrenheit to Kelvin (SI). The
 * conversion is K = (F + 459.67) × 5⁄9, and F = K × 9⁄5 − 459.67.
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
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class OffsetLinearScale extends LinearScale
{
    /** */
    private static final long serialVersionUID = 20151011L;

    /** The offset that has to be taken into account for conversions, multiplied by the conversionFactorToStandardUnit. */
    private final double offsetToStandardUnit;

    /**
     * Construct a Scale for linear transformations with an offset, e.g. for Temperature.
     * @param conversionFactorToStandardUnit double; the conversion factor by which this number has to be multiplied to convert
     *            it to the standard (e.g., SI) unit.
     * @param offsetToStandardUnit the offset that has to be taken into account for conversions; when converting to a standard
     *            unit, the offset is applied first.
     */
    public OffsetLinearScale(final double conversionFactorToStandardUnit, final double offsetToStandardUnit)
    {
        super(conversionFactorToStandardUnit);
        this.offsetToStandardUnit = offsetToStandardUnit;
    }

    /** {@inheritDoc} */
    @Override
    public final double toStandardUnit(final double value)
    {
        return (value + this.offsetToStandardUnit) * getConversionFactorToStandardUnit();
    }

    /** {@inheritDoc} */
    @Override
    public final double fromStandardUnit(final double value)
    {
        return value / getConversionFactorToStandardUnit() - this.offsetToStandardUnit;
    }

    /**
     * Retrieve the offset from the standard unit.
     * @return double; the offset from the standard unit
     */
    public final double getOffsetToStandardUnit()
    {
        return this.offsetToStandardUnit;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBaseSIScale()
    {
        return super.isBaseSIScale() && this.offsetToStandardUnit == 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(this.offsetToStandardUnit);
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        OffsetLinearScale other = (OffsetLinearScale) obj;
        if (Double.doubleToLongBits(this.offsetToStandardUnit) != Double.doubleToLongBits(other.offsetToStandardUnit))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "OffsetLinearScale [offsetToStandardUnit=" + this.offsetToStandardUnit + ", conversionFactorToStandardUnit="
                + this.getConversionFactorToStandardUnit() + "]";
    }

}
