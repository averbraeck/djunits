package org.djunits.vecmat.d1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.operations.VectorTransposable;
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
 * @param <U> the unit type
 */
public class Vector1<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends
        Vector<Q, U, Vector1<Q, U>, Vector1<SIQuantity, SIUnit>, Vector1<?, ?>> implements VectorTransposable<Vector1<Q, U>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /**
     * Create a new Vector1 with a unit.
     * @param xInUnit the x-value expressed in the given unit
     * @param displayUnit the display unit to use
     */
    public Vector1(final double xInUnit, final U displayUnit)
    {
        super(displayUnit);
        this.xSi = displayUnit.toBaseValue(xInUnit);
    }

    /**
     * Return a column or row vector with x and y values in SI or BASE units.
     * @param xSiNew the x value in SI or BASE units
     * @return a new column or row vector with adapted x and y values
     */
    public Vector1<Q, U> instantiateSi(final double xSiNew)
    {
        return new Vector1<Q, U>(xSiNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q, U> instantiateSi(final double[] siNew)
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
    public Vector1<Q, U> transpose()
    {
        return instantiateSi(this.xSi);
    }

    @Override
    public Vector1<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        Throw.when(siNew.length != 1, IllegalArgumentException.class, "Size of new data for Vector1 != 1, but %d",
                siNew.length);
        return new Vector1<SIQuantity, SIUnit>(siNew[0], siUnit);
    }

    @Override
    public double si(final int row, final int col) throws IndexOutOfBoundsException
    {
        Throw.when(row != 0 || col != 0, IllegalArgumentException.class, "si(r, c) can only request si(0, 0) for a Vector1");
        return this.xSi;
    }

    @Override
    public boolean isColumnVector()
    {
        return true;
    }

    @Override
    public Vector1<Q, U> getRowVector(final int row)
    {
        checkRow(row);
        return new Vector1<Q, U>(this.xSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q, U> mgetRowVector(final int mrow)
    {
        mcheckRow(mrow);
        return new Vector1<Q, U>(this.xSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q, U> getColumnVector(final int col)
    {
        checkCol(col);
        return new Vector1<Q, U>(this.xSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q, U> mgetColumnVector(final int mcol)
    {
        mcheckCol(mcol);
        return new Vector1<Q, U>(this.xSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public double[] getRowSi(final int row)
    {
        checkRow(row);
        return new double[] {this.xSi};
    }

    @Override
    public double[] getColumnSi(final int col)
    {
        checkCol(col);
        return new double[] {this.xSi};
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
        final U frozenDisplayUnit = getDisplayUnit(); // capture once
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

    /**
     * Return the contents of the vector as an array.
     * @return the contents of the vector as an array
     */
    @Override
    public double[] si()
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
    public Vector1<Q, U> scaleBy(final double factor)
    {
        return instantiateSi(this.xSi * factor);
    }

    @Override
    public Vector1<Q, U> add(final Vector1<Q, U> other)
    {
        return instantiateSi(this.xSi + other.xSi());
    }

    @Override
    public Vector1<Q, U> subtract(final Vector1<Q, U> other)
    {
        return instantiateSi(this.xSi - other.xSi());
    }

    @Override
    public Vector1<Q, U> negate()
    {
        return instantiateSi(-this.xSi);
    }

    @Override
    public Vector1<Q, U> abs()
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
    public Q mode()
    {
        return max();
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
    public Vector1<Q, U> add(final Q increment)
    {
        return instantiateSi(this.xSi + increment.si());
    }

    @Override
    public Vector1<Q, U> subtract(final Q decrement)
    {
        return instantiateSi(this.xSi - decrement.si());
    }

    @Override
    public boolean isRelative()
    {
        return x().isRelative();
    }

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
        Vector1<?, ?> other = (Vector1<?, ?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi);
    }

    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        s.append("[");
        s.append(withUnit.fromBaseValue(this.xSi));
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

}
