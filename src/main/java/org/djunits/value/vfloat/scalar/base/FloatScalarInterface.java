package org.djunits.value.vfloat.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.base.Scalar;

/**
 * Float scalar functions.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit for which this is the interface
 * @param <S> the scalar type belonging to the unit
 */
public interface FloatScalarInterface<U extends Unit<U>, S extends FloatScalarInterface<U, S>> extends Scalar<U, S>
{
    /**
     * Retrieve the value in the underlying SI unit.
     * @return float
     */
    float getSI();

    /**
     * Retrieve the value in the original unit.
     * @return float
     */
    float getInUnit();

    /**
     * Retrieve the value converted into some specified unit.
     * @param targetUnit U; the unit to convert the value into
     * @return float
     */
    float getInUnit(U targetUnit);

    /**
     * Methods for Relative FloatScalar.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <U> the unit
     * @param <R> the relative scalar
     */
    interface Rel<U extends Unit<U>, R extends FloatScalarInterface.Rel<U, R>>
            extends FloatScalarInterface<U, R>, Scalar.Rel<U, R>
    {
        /**
         * Construct a new Relative Immutable FloatScalar of the right type. Each extending class must implement this method.
         * @param value float; the float value
         * @param unit U; the unit
         * @return R a new relative instance of the FloatScalar of the right type
         */
        R instantiateRel(float value, U unit);
    }

    /**
     * Additional methods for Relative Scalar that has a corresponding Absolute Scalar. An example is the relative scalar Length
     * that has a corresponding absolute scalar Position.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the absoluteunit
     * @param <A> the absolute scalar belonging to the relative scalar
     * @param <RU> the absolute unit belonging to the relative unit
     * @param <R> the relative scalar belonging to the absolute scalar
     */
    interface RelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>, A extends FloatScalarInterface.Abs<AU, A, RU, R>,
            RU extends Unit<RU>, R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>>
            extends FloatScalarInterface<RU, R>, Scalar.RelWithAbs<AU, A, RU, R>
    {
        /**
         * Construct a new Relative Immutable FloatScalar of the right type. Each extending class must implement this method.
         * @param value float; the float value
         * @param unit RU; the unit
         * @return R a new relative instance of the FloatScalar of the right type
         */
        R instantiateRel(float value, RU unit);

        /**
         * Construct a new Absolute Immutable FloatScalar of the right type. Each extending class must implement this method.
         * @param value float; the float value
         * @param unit AU; the absolute unit
         * @return A a new absolute instance of the FloatScalar of the right type
         */
        A instantiateAbs(float value, AU unit);
    }

    /**
     * Methods for Absolute Scalar.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the absoluteunit
     * @param <A> the absolute scalar belonging to the relative scalar
     * @param <RU> the absolute unit belonging to the relative unit
     * @param <R> the relative scalar belonging to the absolute scalar
     */
    interface Abs<AU extends AbsoluteLinearUnit<AU, RU>, A extends FloatScalarInterface.Abs<AU, A, RU, R>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>>
            extends FloatScalarInterface<AU, A>, Scalar.Abs<AU, A, RU, R>
    {
        /**
         * Construct a new Relative Immutable FloatScalar of the right type. Each extending class must implement this method.
         * @param value float; the float value
         * @param unit RU; the unit
         * @return R a new relative instance of the FloatScalar of the right type
         */
        R instantiateRel(float value, RU unit);

        /**
         * Construct a new Absolute Immutable FloatScalar of the right type. Each extending class must implement this method.
         * @param value float; the float value
         * @param unit AU; the absolute unit
         * @return A a new absolute instance of the FloatScalar of the right type
         */
        A instantiateAbs(float value, AU unit);
    }

}
