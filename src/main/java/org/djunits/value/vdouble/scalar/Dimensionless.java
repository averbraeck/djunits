package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;

/**
 * Easy access methods for the Dimensionless DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Dimensionless extends AbstractDoubleScalarRel<DimensionlessUnit, Dimensionless>
        implements DimensionlessFunctions<DimensionlessUnit, Dimensionless>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Dimensionless ZERO = new Dimensionless(0.0, DimensionlessUnit.SI);

    /** Constant with value one. */
    public static final Dimensionless ONE = new Dimensionless(1.0, DimensionlessUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Dimensionless NaN = new Dimensionless(Double.NaN, DimensionlessUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Dimensionless POSITIVE_INFINITY = new Dimensionless(Double.POSITIVE_INFINITY, DimensionlessUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Dimensionless NEGATIVE_INFINITY = new Dimensionless(Double.NEGATIVE_INFINITY, DimensionlessUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Dimensionless POS_MAXVALUE = new Dimensionless(Double.MAX_VALUE, DimensionlessUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Dimensionless NEG_MAXVALUE = new Dimensionless(-Double.MAX_VALUE, DimensionlessUnit.SI);

    /**
     * Construct Dimensionless scalar.
     * @param value double; the double value
     * @param unit DimensionlessUnit; unit for the double value
     */
    public Dimensionless(final double value, final DimensionlessUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Dimensionless scalar.
     * @param value Dimensionless; Scalar from which to construct this instance
     */
    public Dimensionless(final Dimensionless value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless instantiateRel(final double value, final DimensionlessUnit unit)
    {
        return new Dimensionless(value, unit);
    }

    /**
     * Construct Dimensionless scalar.
     * @param value double; the double value in SI units
     * @return Dimensionless; the new scalar with the SI value
     */
    public static final Dimensionless instantiateSI(final double value)
    {
        return new Dimensionless(value, DimensionlessUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Dimensionless; the low value
     * @param one Dimensionless; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Dimensionless; a Scalar at the ratio between
     */
    public static Dimensionless interpolate(final Dimensionless zero, final Dimensionless one, final double ratio)
    {
        return new Dimensionless(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Dimensionless; the first scalar
     * @param r2 Dimensionless; the second scalar
     * @return Dimensionless; the maximum value of two relative scalars
     */
    public static Dimensionless max(final Dimensionless r1, final Dimensionless r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Dimensionless; the first scalar
     * @param r2 Dimensionless; the second scalar
     * @param rn Dimensionless...; the other scalars
     * @return Dimensionless; the maximum value of more than two relative scalars
     */
    public static Dimensionless max(final Dimensionless r1, final Dimensionless r2, final Dimensionless... rn)
    {
        Dimensionless maxr = r1.gt(r2) ? r1 : r2;
        for (Dimensionless r : rn)
        {
            if (r.gt(maxr))
            {
                maxr = r;
            }
        }
        return maxr;
    }

    /**
     * Return the minimum value of two relative scalars.
     * @param r1 Dimensionless; the first scalar
     * @param r2 Dimensionless; the second scalar
     * @return Dimensionless; the minimum value of two relative scalars
     */
    public static Dimensionless min(final Dimensionless r1, final Dimensionless r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Dimensionless; the first scalar
     * @param r2 Dimensionless; the second scalar
     * @param rn Dimensionless...; the other scalars
     * @return Dimensionless; the minimum value of more than two relative scalars
     */
    public static Dimensionless min(final Dimensionless r1, final Dimensionless r2, final Dimensionless... rn)
    {
        Dimensionless minr = r1.lt(r2) ? r1 : r2;
        for (Dimensionless r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Dimensionless representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a Dimensionless
     * @return Dimensionless; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Dimensionless valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Dimensionless: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Dimensionless: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DimensionlessUnit unit = DimensionlessUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Dimensionless(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Dimensionless from " + text);
    }

    /**
     * Returns a Dimensionless based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Dimensionless; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Dimensionless of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Dimensionless: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Dimensionless: empty unitString");
        DimensionlessUnit unit = DimensionlessUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Dimensionless(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Dimensionless with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        return toString();
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless acos()
    {
        return instantiateRel(Math.acos(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless asin()
    {
        return instantiateRel(Math.asin(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless atan()
    {
        return instantiateRel(Math.atan(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless cbrt()
    {
        return instantiateRel(Math.cbrt(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless cos()
    {
        return instantiateRel(Math.cos(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless cosh()
    {
        return instantiateRel(Math.cosh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless exp()
    {
        return instantiateRel(Math.exp(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless expm1()
    {
        return instantiateRel(Math.expm1(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless log()
    {
        return instantiateRel(Math.log(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless log10()
    {
        return instantiateRel(Math.log10(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless log1p()
    {
        return instantiateRel(Math.log1p(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless pow(final double x)
    {
        return instantiateRel(Math.pow(getInUnit(), x), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless signum()
    {
        return instantiateRel(Math.signum(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless sin()
    {
        return instantiateRel(Math.sin(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless sinh()
    {
        return instantiateRel(Math.sinh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless sqrt()
    {
        return instantiateRel(Math.sqrt(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless tan()
    {
        return instantiateRel(Math.tan(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless tanh()
    {
        return instantiateRel(Math.tanh(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final Dimensionless inv()
    {
        return instantiateRel(1.0 / getInUnit(), getDisplayUnit());
    }

    /**
     * Calculate the division of Dimensionless and Dimensionless, which results in a Dimensionless scalar.
     * @param v Dimensionless; scalar
     * @return Dimensionless; scalar as a division of Dimensionless and Dimensionless
     */
    public final Dimensionless divide(final Dimensionless v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and AbsorbedDose, which results in a AbsorbedDose scalar.
     * @param v Dimensionless; scalar
     * @return AbsorbedDose; scalar as a multiplication of Dimensionless and AbsorbedDose
     */
    public final AbsorbedDose times(final AbsorbedDose v)
    {
        return new AbsorbedDose(this.si * v.si, AbsorbedDoseUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Acceleration, which results in a Acceleration scalar.
     * @param v Dimensionless; scalar
     * @return Acceleration; scalar as a multiplication of Dimensionless and Acceleration
     */
    public final Acceleration times(final Acceleration v)
    {
        return new Acceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and AmountOfSubstance, which results in a AmountOfSubstance scalar.
     * @param v Dimensionless; scalar
     * @return AmountOfSubstance; scalar as a multiplication of Dimensionless and AmountOfSubstance
     */
    public final AmountOfSubstance times(final AmountOfSubstance v)
    {
        return new AmountOfSubstance(this.si * v.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Angle, which results in a Angle scalar.
     * @param v Dimensionless; scalar
     * @return Angle; scalar as a multiplication of Dimensionless and Angle
     */
    public final Angle times(final Angle v)
    {
        return new Angle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and SolidAngle, which results in a SolidAngle scalar.
     * @param v Dimensionless; scalar
     * @return SolidAngle; scalar as a multiplication of Dimensionless and SolidAngle
     */
    public final SolidAngle times(final SolidAngle v)
    {
        return new SolidAngle(this.si * v.si, SolidAngleUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Area, which results in a Area scalar.
     * @param v Dimensionless; scalar
     * @return Area; scalar as a multiplication of Dimensionless and Area
     */
    public final Area times(final Area v)
    {
        return new Area(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and CatalyticActivity, which results in a CatalyticActivity scalar.
     * @param v Dimensionless; scalar
     * @return CatalyticActivity; scalar as a multiplication of Dimensionless and CatalyticActivity
     */
    public final CatalyticActivity times(final CatalyticActivity v)
    {
        return new CatalyticActivity(this.si * v.si, CatalyticActivityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Density, which results in a Density scalar.
     * @param v Dimensionless; scalar
     * @return Density; scalar as a multiplication of Dimensionless and Density
     */
    public final Density times(final Density v)
    {
        return new Density(this.si * v.si, DensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Dimensionless, which results in a Dimensionless scalar.
     * @param v Dimensionless; scalar
     * @return Dimensionless; scalar as a multiplication of Dimensionless and Dimensionless
     */
    public final Dimensionless times(final Dimensionless v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalCapacitance, which results in a ElectricalCapacitance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalCapacitance; scalar as a multiplication of Dimensionless and ElectricalCapacitance
     */
    public final ElectricalCapacitance times(final ElectricalCapacitance v)
    {
        return new ElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalCharge, which results in a ElectricalCharge scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalCharge; scalar as a multiplication of Dimensionless and ElectricalCharge
     */
    public final ElectricalCharge times(final ElectricalCharge v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalConductance, which results in a ElectricalConductance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalConductance; scalar as a multiplication of Dimensionless and ElectricalConductance
     */
    public final ElectricalConductance times(final ElectricalConductance v)
    {
        return new ElectricalConductance(this.si * v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalCurrent, which results in a ElectricalCurrent scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalCurrent; scalar as a multiplication of Dimensionless and ElectricalCurrent
     */
    public final ElectricalCurrent times(final ElectricalCurrent v)
    {
        return new ElectricalCurrent(this.si * v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalInductance, which results in a ElectricalInductance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalInductance; scalar as a multiplication of Dimensionless and ElectricalInductance
     */
    public final ElectricalInductance times(final ElectricalInductance v)
    {
        return new ElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalPotential, which results in a ElectricalPotential scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalPotential; scalar as a multiplication of Dimensionless and ElectricalPotential
     */
    public final ElectricalPotential times(final ElectricalPotential v)
    {
        return new ElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and ElectricalResistance, which results in a ElectricalResistance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalResistance; scalar as a multiplication of Dimensionless and ElectricalResistance
     */
    public final ElectricalResistance times(final ElectricalResistance v)
    {
        return new ElectricalResistance(this.si * v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Energy, which results in a Energy scalar.
     * @param v Dimensionless; scalar
     * @return Energy; scalar as a multiplication of Dimensionless and Energy
     */
    public final Energy times(final Energy v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and EquivalentDose, which results in a EquivalentDose scalar.
     * @param v Dimensionless; scalar
     * @return EquivalentDose; scalar as a multiplication of Dimensionless and EquivalentDose
     */
    public final EquivalentDose times(final EquivalentDose v)
    {
        return new EquivalentDose(this.si * v.si, EquivalentDoseUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and FlowMass, which results in a FlowMass scalar.
     * @param v Dimensionless; scalar
     * @return FlowMass; scalar as a multiplication of Dimensionless and FlowMass
     */
    public final FlowMass times(final FlowMass v)
    {
        return new FlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and FlowVolume, which results in a FlowVolume scalar.
     * @param v Dimensionless; scalar
     * @return FlowVolume; scalar as a multiplication of Dimensionless and FlowVolume
     */
    public final FlowVolume times(final FlowVolume v)
    {
        return new FlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Force, which results in a Force scalar.
     * @param v Dimensionless; scalar
     * @return Force; scalar as a multiplication of Dimensionless and Force
     */
    public final Force times(final Force v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Frequency, which results in a Frequency scalar.
     * @param v Dimensionless; scalar
     * @return Frequency; scalar as a multiplication of Dimensionless and Frequency
     */
    public final Frequency times(final Frequency v)
    {
        return new Frequency(this.si * v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Illuminance, which results in a Illuminance scalar.
     * @param v Dimensionless; scalar
     * @return Illuminance; scalar as a multiplication of Dimensionless and Illuminance
     */
    public final Illuminance times(final Illuminance v)
    {
        return new Illuminance(this.si * v.si, IlluminanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Length, which results in a Length scalar.
     * @param v Dimensionless; scalar
     * @return Length; scalar as a multiplication of Dimensionless and Length
     */
    public final Length times(final Length v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and LinearDensity, which results in a LinearDensity scalar.
     * @param v Dimensionless; scalar
     * @return LinearDensity; scalar as a multiplication of Dimensionless and LinearDensity
     */
    public final LinearDensity times(final LinearDensity v)
    {
        return new LinearDensity(this.si * v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and LuminousFlux, which results in a LuminousFlux scalar.
     * @param v Dimensionless; scalar
     * @return LuminousFlux; scalar as a multiplication of Dimensionless and LuminousFlux
     */
    public final LuminousFlux times(final LuminousFlux v)
    {
        return new LuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and LuminousIntensity, which results in a LuminousIntensity scalar.
     * @param v Dimensionless; scalar
     * @return LuminousIntensity; scalar as a multiplication of Dimensionless and LuminousIntensity
     */
    public final LuminousIntensity times(final LuminousIntensity v)
    {
        return new LuminousIntensity(this.si * v.si, LuminousIntensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and MagneticFluxDensity, which results in a MagneticFluxDensity scalar.
     * @param v Dimensionless; scalar
     * @return MagneticFluxDensity; scalar as a multiplication of Dimensionless and MagneticFluxDensity
     */
    public final MagneticFluxDensity times(final MagneticFluxDensity v)
    {
        return new MagneticFluxDensity(this.si * v.si, MagneticFluxDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and MagneticFlux, which results in a MagneticFlux scalar.
     * @param v Dimensionless; scalar
     * @return MagneticFlux; scalar as a multiplication of Dimensionless and MagneticFlux
     */
    public final MagneticFlux times(final MagneticFlux v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Mass, which results in a Mass scalar.
     * @param v Dimensionless; scalar
     * @return Mass; scalar as a multiplication of Dimensionless and Mass
     */
    public final Mass times(final Mass v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Power, which results in a Power scalar.
     * @param v Dimensionless; scalar
     * @return Power; scalar as a multiplication of Dimensionless and Power
     */
    public final Power times(final Power v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Pressure, which results in a Pressure scalar.
     * @param v Dimensionless; scalar
     * @return Pressure; scalar as a multiplication of Dimensionless and Pressure
     */
    public final Pressure times(final Pressure v)
    {
        return new Pressure(this.si * v.si, PressureUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and RadioActivity, which results in a RadioActivity scalar.
     * @param v Dimensionless; scalar
     * @return RadioActivity; scalar as a multiplication of Dimensionless and RadioActivity
     */
    public final RadioActivity times(final RadioActivity v)
    {
        return new RadioActivity(this.si * v.si, RadioActivityUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Speed, which results in a Speed scalar.
     * @param v Dimensionless; scalar
     * @return Speed; scalar as a multiplication of Dimensionless and Speed
     */
    public final Speed times(final Speed v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Temperature, which results in a Temperature scalar.
     * @param v Dimensionless; scalar
     * @return Temperature; scalar as a multiplication of Dimensionless and Temperature
     */
    public final Temperature times(final Temperature v)
    {
        return new Temperature(this.si * v.si, TemperatureUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Duration, which results in a Duration scalar.
     * @param v Dimensionless; scalar
     * @return Duration; scalar as a multiplication of Dimensionless and Duration
     */
    public final Duration times(final Duration v)
    {
        return new Duration(this.si * v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Torque, which results in a Torque scalar.
     * @param v Dimensionless; scalar
     * @return Torque; scalar as a multiplication of Dimensionless and Torque
     */
    public final Torque times(final Torque v)
    {
        return new Torque(this.si * v.si, TorqueUnit.SI);
    }

    /**
     * Calculate the multiplication of Dimensionless and Volume, which results in a Volume scalar.
     * @param v Dimensionless; scalar
     * @return Volume; scalar as a multiplication of Dimensionless and Volume
     */
    public final Volume times(final Volume v)
    {
        return new Volume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and Length, which results in a LinearDensity scalar.
     * @param v Dimensionless; scalar
     * @return LinearDensity; scalar as a division of Dimensionless and Length
     */
    public final LinearDensity divide(final Length v)
    {
        return new LinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and LinearDensity, which results in a Length scalar.
     * @param v Dimensionless; scalar
     * @return Length; scalar as a division of Dimensionless and LinearDensity
     */
    public final Length divide(final LinearDensity v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and Duration, which results in a Frequency scalar.
     * @param v Dimensionless; scalar
     * @return Frequency; scalar as a division of Dimensionless and Duration
     */
    public final Frequency divide(final Duration v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and Frequency, which results in a Duration scalar.
     * @param v Dimensionless; scalar
     * @return Duration; scalar as a division of Dimensionless and Frequency
     */
    public final Duration divide(final Frequency v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and ElectricalConductance, which results in a ElectricalResistance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalResistance; scalar as a division of Dimensionless and ElectricalConductance
     */
    public final ElectricalResistance divide(final ElectricalConductance v)
    {
        return new ElectricalResistance(this.si / v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the division of Dimensionless and ElectricalResistance, which results in a ElectricalConductance scalar.
     * @param v Dimensionless; scalar
     * @return ElectricalConductance; scalar as a division of Dimensionless and ElectricalResistance
     */
    public final ElectricalConductance divide(final ElectricalResistance v)
    {
        return new ElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public Dimensionless reciprocal()
    {
        return Dimensionless.instantiateSI(1.0 / this.si);
    }

}
