package org.djunits.vecmat.def;

import java.util.Iterator;

import org.djunits.formatter.FormatHint;
import org.djunits.formatter.Formatter;
import org.djunits.formatter.UnitHint;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.vecmat.operations.Normed;

/**
 * Vector contains the contract for Vector classes that contain relative quantity values.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <V> the vector type
 * @param <SI> the vector type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector type with generics &lt;?, ?&lt; for Hadamard operations
 * @param <VT> the type of the transposed version of the vector
 */
public abstract class Vector<Q extends Quantity<Q>, V extends Vector<Q, V, SI, H, VT>,
        SI extends Vector<SIQuantity, SI, ?, ?, ?>, H extends Vector<?, ?, ?, ?, ?>, VT extends Vector<Q, VT, ?, ?, V>>
        extends VectorMatrix<Q, V, SI, H, VT> implements Iterable<Q>, Normed<Q>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Vector with a unit, as an extension of Matrix.
     * @param displayUnit the display unit to use
     */
    public Vector(final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Retrieve the size of the vector.
     * @return the size of the vector
     */
    public abstract int size();

    /**
     * Return whether this vector is a column vector.
     * @return whether this vector is a column vector
     */
    public abstract boolean isColumnVector();

    /**
     * Return whether this vector is a row vector.
     * @return whether this vector is a row vector
     */
    public boolean isRowVector()
    {
        return !isColumnVector();
    }

    /**
     * Retrieve an si-value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public abstract double si(int index) throws IndexOutOfBoundsException;

    /**
     * Retrieve an si-value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public double msi(final int mIndex) throws IndexOutOfBoundsException
    {
        return si(mIndex - 1);
    }

    /**
     * Retrieve a value from the vector.
     * @param index the index (0-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public Q get(final int index) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(index)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Retrieve a value from the vector, based on a 1-valued index.
     * @param mIndex the index (1-based) to retrieve the value from
     * @return the value as a Scalar
     * @throws IndexOutOfBoundsException in case index is out of bounds
     */
    public Q mget(final int mIndex) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(mIndex - 1)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector as an array of scalars.
     * @return the vector as an array of scalars
     */
    public abstract Q[] getScalarArray();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return an iterator over the scalars in this vector in proper sequence
     */
    @Override
    public abstract Iterator<Q> iterator();

    /**********************************************************************************/
    /*************************** STRING AND FORMATTING METHODS ************************/
    /**********************************************************************************/

    /**
     * Concise description of this vector.
     * @return a String with the vector, with the unit attached.
     */
    @Override
    public String toString()
    {
        return toString(new FormatHint[] {});
    }

    /**
     * String representation of this vector after applying the format hints.
     * @param hints the format hints to apply for the vector
     * @return a String representation of this vector, formatted according to the format hints
     */
    public String toString(final FormatHint... hints)
    {
        return Formatter.formatVector(this, hints);
    }

    /**
     * String representation of this vector, expressed in the specified unit.
     * @param targetUnit the unit into which the values of the vector are converted for display
     * @return printable string with the vector's values expressed in the specified unit
     */
    @Override
    public String toString(final Unit<?, Q> targetUnit)
    {
        return toString(new UnitHint().setDisplayUnit(targetUnit));
    }

}
