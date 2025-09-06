package org.djunits.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatDimensionlessVector, a vector of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatDimensionlessVector extends FloatVectorRel<DimensionlessUnit, FloatDimensionless, FloatDimensionlessVector>
        implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionlessVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct a FloatDimensionlessVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatDimensionlessVector(final FloatVectorData data, final DimensionlessUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatDimensionlessVector from a float[] object. The Float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final float[] data, final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatDimensionlessVector from a float[] object. The Float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatDimensionlessVector(final float[] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDimensionlessVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final float[] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a FloatDimensionlessVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatDimensionlessVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatDimensionless[] */

    /**
     * Construct a FloatDimensionlessVector from an array of FloatDimensionless objects. The FloatDimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final FloatDimensionless[] data, final DimensionlessUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatDimensionlessVector from an array of FloatDimensionless objects. The FloatDimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatDimensionlessVector(final FloatDimensionless[] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDimensionlessVector from an array of FloatDimensionless objects. The FloatDimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final FloatDimensionless[] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a FloatDimensionlessVector from an array of FloatDimensionless objects. The FloatDimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatDimensionlessVector(final FloatDimensionless[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Dimensionless> */

    /**
     * Construct a FloatDimensionlessVector from a list of Number objects or a list of FloatDimensionless objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatDimensionless objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatDimensionless objects, each FloatDimensionless has its own unit, and the displayUnit is just used for printing. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Dimensionless&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final List<? extends Number> data, final DimensionlessUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatDimensionless
                        ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatDimensionlessVector from a list of Number objects or a list of FloatDimensionless objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatDimensionless objects. In case the list contains Number objects, the displayUnit indicates the unit in which the
     * values in the list are expressed, as well as the unit in which they will be printed. In case the list contains
     * FloatDimensionless objects, each FloatDimensionless has its own unit, and the displayUnit is just used for printing. The
     * values but will always be internally stored as SI values or base values, and expressed using the display unit or base
     * unit when printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Dimensionless&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatDimensionlessVector(final List<? extends Number> data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatDimensionlessVector from a list of Number objects or a list of FloatDimensionless objects. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatDimensionless objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Dimensionless&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a FloatDimensionlessVector from a list of Number objects or a list of FloatDimensionless objects. When data
     * contains numbers such as Float, assume that they are expressed using SI units. When the data consists of
     * FloatDimensionless objects, they each have their own unit, but will be printed using SI units or base units. The values
     * but will always be internally stored as SI values or base values, and expressed using the display unit or base unit when
     * printing. Assume the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Dimensionless&gt;
     */
    public FloatDimensionlessVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatDimensionless> */

    /**
     * Construct a FloatDimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatDimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatDimensionless
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains FloatDimensionless objects, each
     * FloatDimensionless has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final Map<Integer, ? extends Number> data, final int size,
            final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatDimensionless
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatDimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatDimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit
     * has a different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatDimensionless
     * objects. In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are
     * expressed, as well as the unit in which they will be printed. In case the map contains FloatDimensionless objects, each
     * FloatDimensionless has its own unit, and the displayUnit is just used for printing. The values but will always be
     * internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatDimensionlessVector(final Map<Integer, ? extends Number> data, final int size,
            final DimensionlessUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatDimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatDimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatDimensionless objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatDimensionlessVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a FloatDimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index
     * values to of FloatDimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter
     * indicates the size of the vector, since the largest index does not have to be part of the map. When data contains numbers
     * such as Float, assume that they are expressed using SI units. When the data consists of FloatDimensionless objects, they
     * each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is SPARSE since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatDimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatDimensionlessVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatDimensionless> getScalarClass()
    {
        return FloatDimensionless.class;
    }

    @Override
    public FloatDimensionlessVector instantiateVector(final FloatVectorData fvd, final DimensionlessUnit displayUnit)
    {
        return new FloatDimensionlessVector(fvd, displayUnit);
    }

    @Override
    public FloatDimensionless instantiateScalarSI(final float valueSI, final DimensionlessUnit displayUnit)
    {
        FloatDimensionless result = FloatDimensionless.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public final FloatDimensionlessVector acos()
    {
        assign(FloatMathFunctions.ACOS);
        return this;
    }

    @Override
    public final FloatDimensionlessVector asin()
    {
        assign(FloatMathFunctions.ASIN);
        return this;
    }

    @Override
    public final FloatDimensionlessVector atan()
    {
        assign(FloatMathFunctions.ATAN);
        return this;
    }

    @Override
    public final FloatDimensionlessVector cbrt()
    {
        assign(FloatMathFunctions.CBRT);
        return this;
    }

    @Override
    public final FloatDimensionlessVector cos()
    {
        assign(FloatMathFunctions.COS);
        return this;
    }

    @Override
    public final FloatDimensionlessVector cosh()
    {
        assign(FloatMathFunctions.COSH);
        return this;
    }

    @Override
    public final FloatDimensionlessVector exp()
    {
        assign(FloatMathFunctions.EXP);
        return this;
    }

    @Override
    public final FloatDimensionlessVector expm1()
    {
        assign(FloatMathFunctions.EXPM1);
        return this;
    }

    @Override
    public final FloatDimensionlessVector log()
    {
        assign(FloatMathFunctions.LOG);
        return this;
    }

    @Override
    public final FloatDimensionlessVector log10()
    {
        assign(FloatMathFunctions.LOG10);
        return this;
    }

    @Override
    public final FloatDimensionlessVector log1p()
    {
        assign(FloatMathFunctions.LOG1P);
        return this;
    }

    @Override
    public final FloatDimensionlessVector pow(final double x)
    {
        assign(FloatMathFunctions.POW((float) x));
        return this;
    }

    @Override
    public final FloatDimensionlessVector signum()
    {
        assign(FloatMathFunctions.SIGNUM);
        return this;
    }

    @Override
    public final FloatDimensionlessVector sin()
    {
        assign(FloatMathFunctions.SIN);
        return this;
    }

    @Override
    public final FloatDimensionlessVector sinh()
    {
        assign(FloatMathFunctions.SINH);
        return this;
    }

    @Override
    public final FloatDimensionlessVector sqrt()
    {
        assign(FloatMathFunctions.SQRT);
        return this;
    }

    @Override
    public final FloatDimensionlessVector tan()
    {
        assign(FloatMathFunctions.TAN);
        return this;
    }

    @Override
    public final FloatDimensionlessVector tanh()
    {
        assign(FloatMathFunctions.TANH);
        return this;
    }

    @Override
    public final FloatDimensionlessVector inv()
    {
        assign(FloatMathFunctions.INV);
        return this;
    }

}
