package org.djunits.value.vfloat.vector.base;

import org.djunits.unit.Unit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Vector;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Interface for the FloatVector classes, specifically defining the methods that deal with float values.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the generic scalar type belonging to U
 * @param <V> the generic vector type
 */
public interface FloatVectorInterface<U extends Unit<U>, S extends FloatScalarInterface<U, S>,
        V extends FloatVectorInterface<U, S, V>> extends Vector<U, S, V>
{
    /**
     * Retrieve the value stored at a specified position in the standard SI unit.
     * @param index int; index of the value to retrieve
     * @return float; value at position index in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    float getSI(int index) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified position in the original unit.
     * @param index int; index of the value to retrieve
     * @return float; value at position index in the original unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    float getInUnit(int index) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified position converted into a specified unit.
     * @param index int; index of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return float; value at position index converted into the specified unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    float getInUnit(int index, U targetUnit) throws ValueRuntimeException;

    /**
     * Create a float[] array filled with the values in the standard SI unit.
     * @return float[]; array of values in the standard SI unit
     */
    float[] getValuesSI();

    /**
     * Create a float[] array filled with the values in the original unit.
     * @return float[]; the values in the original unit
     */
    float[] getValuesInUnit();

    /**
     * Create a float[] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return float[]; the values converted into the specified unit
     */
    float[] getValuesInUnit(U targetUnit);

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueSI float; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setSI(int index, float valueSI) throws ValueRuntimeException;

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int index, float valueInUnit) throws ValueRuntimeException;

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param index int; the index of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int index, float valueInUnit, U valueUnit) throws ValueRuntimeException;

    /**
     * Set the scalar value at the specified position.
     * @param index int; the index of the value to set
     * @param value S; the value to set
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void set(int index, S value) throws ValueRuntimeException;

    /**
     * Execute a function on a cell by cell basis. Note: May be expensive when used on sparse data.
     * @param floatFunction FloatFunction; the function to apply
     * @return V; this updated vector
     */
    V assign(FloatFunction floatFunction);

    /**
     * Instantiate a new vector of the class of this vector. This can be used instead of the FloatVector.instiantiate() methods
     * in case another vector of this class is known. The method is faster than FloatVector.instantiate, and it will also work
     * if the vector is user-defined.
     * @param fvd FloatVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    V instantiateVector(FloatVectorData fvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this vector. This can be used instead of the FloatScalar.instiantiate() methods
     * in case a vector of this class is known. The method is faster than FloatScalar.instantiate, and it will also work if the
     * vector and/or scalar are user-defined.
     * @param valueSI float; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the vector type
     */
    S instantiateScalarSI(float valueSI, U displayUnit);

}
