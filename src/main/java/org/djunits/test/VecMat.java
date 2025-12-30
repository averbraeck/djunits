package org.djunits.test;

import java.lang.reflect.Method;

import org.djunits.quantity.Length;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Units;
import org.djunits.value.Value;

/**
 * VecMat.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VecMat
{
    /**
     * @param <V> the vector type
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public abstract static class Vector2D<V extends Vector2D<V, Q, U>, Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
            implements Value<V, U>
    {
        /** */
        private static final long serialVersionUID = 500L;

        @Override
        public U getDisplayUnit()
        {
            return null;
        }

        @Override
        public V setDisplayUnit(final U newUnit)
        {
            return null;
        }

        @Override
        public boolean isRelative()
        {
            return false;
        }

        @Override
        public String toString(final U displayUnit)
        {
            return null;
        }

        @Override
        public String toString(final boolean verbose, final boolean withUnit)
        {
            return null;
        }

        @Override
        public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
        {
            return null;
        }

    }

    /** */
    public static class Length2D extends Vector2D<Length2D, Length, Length.Unit>
    {
        /** */
        private static final long serialVersionUID = 500L;

    }

    // Too many generics...

    /**
     * @param <C> the collection type
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public abstract static class QuantityCollection<C extends QuantityCollection<C, Q, U>, Q extends Quantity<Q, U>,
            U extends UnitInterface<U, Q>> implements Value<C, U>
    {
        /** */
        private static final long serialVersionUID = 500L;

    }

    // wrapper class?

    /**
     * @param <Q> the quantity (without its unit)
     */
    @SuppressWarnings("rawtypes")
    public static class QuantityList<Q extends Quantity>
    {
        /** */
        private UnitInterface<?, ?> displayUnit;

        /** */
        private QuantityData data;

        /**
         * @param data the data
         * @param displayUnit the display unit
         */
        public QuantityList(final QuantityData data, final AbstractUnit<?, ?> displayUnit)
        {
            this.displayUnit = displayUnit;
        }
    }

    /** */
    public interface QuantityData
    {

    }

    /** */
    public static class DoubleData implements QuantityData
    {
        /** */
        private final double[] data;

        /**
         * @param data the double[] data
         */
        public DoubleData(final double[] data)
        {
            this.data = data;
        }

        /**
         * @return data
         */
        public double[] getData()
        {
            return this.data;
        }
    }

    /** */
    public static class QuantityList2
    {
        /** */
        private UnitInterface<?, ?> displayUnit;

        /** */
        private QuantityData data;

        /** */
        private Class<? extends Quantity<?, ?>> qClass;

        /**
         * @param qClass the quantity class
         * @param data the data
         * @param displayUnit the display unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityList2(final Class<Q> qClass,
                final QuantityData data, final UnitInterface<U, Q> displayUnit)
        {
            this.qClass = qClass;
            this.data = data;
            this.displayUnit = displayUnit;
        }

        /**
         * @param qClass the quantity class
         * @param data the data
         * @param abbreviation the display unit abbreviation
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityList2(final Class<Q> qClass,
                final QuantityData data, final String abbreviation)
        {
            this.qClass = qClass;
            this.data = data;
            Class<U> unitClass = null;
            try
            {
                Method m = qClass.getMethod("ofSi", double.class);
                m.setAccessible(true);
                unitClass = (Class<U>) m.invoke(null, 1.0).getClass();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            this.displayUnit = Units.resolve(unitClass, abbreviation);
        }
    }

    /**
     * @param <Q> the quantity type
     */
    public interface Unit<Q extends Quantity<Q, ?>>
    {

    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        var data = new DoubleData(new double[] {1, 2, 3});
        var lengthList = new QuantityList<Length>(data, Mass.Unit.KILOGRAM);

        var mass1List = new QuantityList2(Mass.class, data, "kg");
        var mass2List = new QuantityList2(Mass.class, data, Mass.Unit.KILOGRAM);
    }
}
