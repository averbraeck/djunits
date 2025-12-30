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
     * @param <U> the unit type
     */
    public static class Vector2D<U extends UnitInterface<U, ?>> implements Value<U>
    {
        /** */
        private double x;

        /** */
        private double y;

        /** */
        private UnitInterface<U, ?> unit;

        /**
         * @param x x
         * @param y y
         * @param unit unit
         */
        public Vector2D(final double x, final double y, final U unit)
        {
            this.x = x;
            this.y = y;
            this.unit = unit;
        }

        /**
         * @param x x
         * @param y y
         * @param unit unit
         * @return vec
         * @param <U> the unit type
         */
        public static <U extends UnitInterface<U, ?>> Vector2D<U> of(final double x, final double y, final U unit)
        {
            return new Vector2D<U>(x, y, unit);
        }

        /**
         * @param <Q> the quantity type
         * @return X value
         */
        @SuppressWarnings("unchecked")
        public <Q extends Quantity<Q, ? extends U>> Q getX()
        {
            return (Q) this.unit.ofSi(this.x);
        }

        /**
         * @param <Q> the quantity type
         * @return Y value
         */
        @SuppressWarnings("unchecked")
        public <Q extends Quantity<Q, ? extends U>> Q getY()
        {
            return (Q) this.unit.ofSi(this.y);
        }

        /**
         * Add with other vec.
         * @param other vec
         * @return sum vec
         */
        @SuppressWarnings("unchecked")
        public Vector2D<U> plus(final Vector2D<U> other)
        {
            return new Vector2D<U>(this.x + other.x, this.y + other.y, (U) this.unit);
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public U getDisplayUnit()
        {
            return (U) this.unit;
        }

        @Override
        public void setDisplayUnit(final U newUnit)
        {
            this.unit = newUnit;
        }

        @Override
        public boolean isRelative()
        {
            // XXX
            return getX().isRelative();
        }

        @Override
        public String toString(final U displayUnit)
        {
            return toString();
        }

        @Override
        public String toString()
        {
            return "[" + this.x + ", " + this.y + "] " + this.unit.toString();
        }
    }

    /**
     * @param <C> the collection type
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public abstract static class QuantityCollection<C extends QuantityCollection<C, Q, U>, Q extends Quantity<Q, U>,
            U extends UnitInterface<U, Q>> implements Value<U>
    {
        /** */
        private static final long serialVersionUID = 600L;

    }

    // wrapper class?

    /**
     * @param <Q> the quantity (without its unit)
     */
    public static class QuantityList<Q extends Quantity<Q, ?>>
    {
        /** */
        private UnitInterface<?, Q> displayUnit;

        /** */
        private QuantityData data;

        /**
         * @param data the data
         * @param displayUnit the display unit
         */
        public QuantityList(final QuantityData data, final AbstractUnit<?, Q> displayUnit)
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
        var lengthList = new QuantityList<Length>(data, Length.Unit.MILLIMETER);

        var mass1List = new QuantityList2(Mass.class, data, "kg");
        var mass2List = new QuantityList2(Mass.class, data, Mass.Unit.KILOGRAM);

        var v2 = new Vector2D<Length.Unit>(10, 20, Length.Unit.METER);
        var v3 = Vector2D.of(10, 20, Length.Unit.FOOT);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v2.plus(v3));
        var v4 = Vector2D.of(10, 20, Mass.Unit.GRAM);
        // does not compile: System.out.println(v2.plus(v4));
    }
}
