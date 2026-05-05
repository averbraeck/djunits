package org.djunits.vecmat.d1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.def.Vector;
import org.djutils.exceptions.Throw;

/**
 * Vector1 implements a vector with one real-valued entry. The vector is immutable, except for the display unit, which can be
 * changed. Some of the method that have been defined already for a generic vector have been re-implemented for efficiency.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class Vector1<Q extends Quantity<Q>> extends Vector<Q, Vector1<Q>, Vector1<SIQuantity>, Vector1<?>, Vector1<Q>>
        implements Vector.Row<Vector1<Q>, Q>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /**
     * Create a new Vector1 with a display unit.
     * @param xSi the x-value expressed in the SI unit for the quantity
     * @param displayUnit the display unit of the vector
     */
    public Vector1(final double xSi, final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
        this.xSi = xSi;
    }

    /**
     * Return a 1-vector with x value in SI or BASE units.
     * @param xSiNew the x value in SI or BASE units
     * @return a new column or row vector with adapted x and y values
     */
    public Vector1<Q> instantiateSi(final double xSiNew)
    {
        return new Vector1<Q>(xSiNew, getDisplayUnit());
    }

    @Override
    public Vector1<Q> instantiateSi(final double[] siNew)
    {
        Throw.when(siNew.length != 1, IllegalArgumentException.class, "Size of new data for Vector1 != 1, but %d",
                siNew.length);
        return instantiateSi(siNew[0]);
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public int rows()
    {
        return 1;
    }

    @Override
    public int cols()
    {
        return 1;
    }

    @Override
    public int nonZeroCount()
    {
        return this.xSi == 0.0 ? 0 : 1;
    }

    @Override
    public Vector1<Q> transpose()
    {
        return instantiateSi(this.xSi);
    }

    @Override
    public Vector1<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        Throw.when(siNew.length != 1, IllegalArgumentException.class, "Size of new data for Vector1 != 1, but %d",
                siNew.length);
        return new Vector1<SIQuantity>(siNew[0], siUnit);
    }

    @Override
    public boolean isColumnVector()
    {
        return true;
    }

    @Override
    public boolean isRowVector()
    {
        return true;
    }

    @Override
    public double si(final int index) throws IndexOutOfBoundsException
    {
        Throw.when(index != 0, IndexOutOfBoundsException.class, "Cannot retrieve Vector1[%d]", index);
        return this.xSi;
    }

    @Override
    public Iterator<Q> iterator()
    {
        final double[] si = new double[] {this.xSi};
        final Unit<?, Q> frozenDisplayUnit = getDisplayUnit(); // capture once
        return Arrays.stream(si).mapToObj(v -> frozenDisplayUnit.ofSi(v).setDisplayUnit(frozenDisplayUnit)).iterator();
    }

    @Override
    public Q[] getScalarArray()
    {
        final Q qx = x();
        final Class<?> qClass = qx.getClass();
        @SuppressWarnings("unchecked")
        final Q[] out = (Q[]) Array.newInstance(qClass, 1);
        out[0] = qx;
        return out;
    }

    /**
     * Return the x-value of the vector in SI or BASE units.
     * @return the x-value of the vector in SI or BASE units
     */
    public double xSi()
    {
        return this.xSi;
    }

    @Override
    public double[] unsafeSiArray()
    {
        return getSiArray();
    }

    @Override
    public double[] getSiArray()
    {
        return new double[] {this.xSi};
    }

    /**
     * Return the x-value of the vector as a quantity with the correct unit.
     * @return the x-value of the vector as a quantity with the correct unit
     */
    public Q x()
    {
        return getDisplayUnit().ofSi(this.xSi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> scaleBy(final double factor)
    {
        return instantiateSi(this.xSi * factor);
    }

    @Override
    public Vector1<Q> add(final Vector1<Q> other)
    {
        return instantiateSi(this.xSi + other.xSi());
    }

    @Override
    public Vector1<Q> subtract(final Vector1<Q> other)
    {
        return instantiateSi(this.xSi - other.xSi());
    }

    @Override
    public Vector1<Q> negate()
    {
        return instantiateSi(-this.xSi);
    }

    @Override
    public Vector1<Q> abs()
    {
        return instantiateSi(Math.abs(this.xSi));
    }

    @Override
    public Q normL1()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return x();
    }

    @Override
    public Q min()
    {
        return x();
    }

    @Override
    public Q max()
    {
        return x();
    }

    @Override
    public Q median()
    {
        return mean();
    }

    @Override
    public Q sum()
    {
        return x();
    }

    @Override
    public Vector1<Q> add(final Q increment)
    {
        return instantiateSi(this.xSi + increment.si());
    }

    @Override
    public Vector1<Q> subtract(final Q decrement)
    {
        return instantiateSi(this.xSi - decrement.si());
    }

    @Override
    public boolean isRelative()
    {
        return x().isRelative();
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a Vector1 without needing generics.
     * @param xInUnit the x-value expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @return a new Vector1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> of(final double xInUnit, final Unit<?, Q> unit)
    {
        return new Vector1<>(unit.toBaseValue(xInUnit), unit);
    }

    /**
     * Create a Vector1 without needing generics.
     * @param x the x-value expressed as a quantity
     * @return a new Vector1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> of(final Q x)
    {
        Throw.whenNull(x, "x");
        return new Vector1<>(x.si(), x.getDisplayUnit());
    }

    /**
     * Create a Vector1 without needing generics.
     * @param dataInUnit the x-value expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @return a new Vector1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(dataInUnit, "dataInUnit");
        Throw.when(dataInUnit.length != 1, IllegalArgumentException.class, "Length of dataInUnit != 1 but %d",
                dataInUnit.length);
        return new Vector1<>(unit.toBaseValue(dataInUnit[0]), unit);
    }

    /**
     * Create a Vector1 without needing generics.
     * @param xSi the x-value expressed in the SI unit
     * @param displayUnit the display unit to use
     * @return a new Vector1 with the display unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> ofSi(final double xSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        return new Vector1<>(xSi, displayUnit);
    }

    /**
     * Create a Vector1 without needing generics.
     * @param dataSi the x-value expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @return a new Vector1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(dataSi, "dataSi");
        Throw.when(dataSi.length != 1, IllegalArgumentException.class, "Length of dataSi != 1 but %d", dataSi.length);
        return new Vector1<>(dataSi[0], displayUnit);
    }

    /**
     * Create a Vector1 without needing generics.
     * @param data the x-value expressed as an array of quantities
     * @return a new Vector1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Vector1<Q> of(final Q[] data)
    {
        Throw.whenNull(data, "dataSi");
        Throw.when(data.length != 1, IllegalArgumentException.class, "Length of dataSi != 1 but %d", data.length);
        return new Vector1<>(data[0].si(), data[0].getDisplayUnit());
    }

    // ------------------------------------------ AS METHODS ------------------------------------------

    /**
     * Return the vector 'as' a vector with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the vector to
     * @return a quantity typed in the target vector class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> Vector1<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Vector1<TQ>(xSi(), targetUnit);
    }

    // ---------------------------------------- HASHCODE, EQUALS ----------------------------------------

    @Override
    public int hashCode()
    {
        return Objects.hash(this.xSi);
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
        Vector1<?> other = (Vector1<?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi);
    }

}
