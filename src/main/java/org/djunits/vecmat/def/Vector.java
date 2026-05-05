package org.djunits.vecmat.def;

import java.util.Iterator;

import org.djunits.formatter.VectorFormat;
import org.djunits.formatter.VectorFormatter;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.value.Value;
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

    /* *********************************************************************************/
    /* ************************** STRING AND FORMATTING METHODS ************************/
    /* *********************************************************************************/

    /**
     * Formatting methods for column vector.
     * @param <V> the vector type
     * @param <Q> the quantity type
     */
    public interface Col<V extends Value<V, Q>, Q extends Quantity<Q>> extends Value<V, Q>
    {
        /**
         * Concise description of this vector.
         * @return a String with the vector, with the unit attached.
         */
        @Override
        default String format()
        {
            return format(VectorFormat.Col.defaults());
        }

        /**
         * String representation of this vector after applying the format.
         * @param format the format to apply for the vector
         * @return a String representation of this vector, formatted according to the given format
         */
        default String format(final VectorFormat<?> format)
        {
            return VectorFormatter.format((Vector<?, ?, ?, ?, ?>) this, format);
        }

        /**
         * String representation of this vector, expressed in the specified unit.
         * @param targetUnit the unit into which the values of the vector are converted for display
         * @return printable string with the vector's values expressed in the specified unit
         */
        @Override
        default String format(final Unit<?, Q> targetUnit)
        {
            return format(VectorFormat.Col.defaults().setDisplayUnit(targetUnit));
        }
    }

    /**
     * Formatting methods for row vector.
     * @param <V> the vector type
     * @param <Q> the quantity type
     */
    public interface Row<V extends Value<V, Q>, Q extends Quantity<Q>> extends Value<V, Q>
    {
        /**
         * Concise description of this vector.
         * @return a String with the vector, with the unit attached.
         */
        @Override
        default String format()
        {
            return format(VectorFormat.Row.defaults());
        }

        /**
         * String representation of this vector after applying the format.
         * @param format the format to apply for the vector
         * @return a String representation of this vector, formatted according to the given format
         */
        default String format(final VectorFormat<?> format)
        {
            return VectorFormatter.format((Vector<?, ?, ?, ?, ?>) this, format);
        }

        /**
         * String representation of this vector, expressed in the specified unit.
         * @param targetUnit the unit into which the values of the vector are converted for display
         * @return printable string with the vector's values expressed in the specified unit
         */
        @Override
        default String format(final Unit<?, Q> targetUnit)
        {
            return format(VectorFormat.Row.defaults().setDisplayUnit(targetUnit));
        }
    }

}
