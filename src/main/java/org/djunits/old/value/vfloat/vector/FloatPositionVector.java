package org.djunits.old.value.vfloat.vector;

import java.util.List;
import java.util.Map;

import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.PositionUnit;
import org.djunits.old.unit.scale.IdentityScale;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.scalar.FloatLength;
import org.djunits.old.value.vfloat.scalar.FloatPosition;
import org.djunits.old.value.vfloat.vector.base.FloatVectorAbs;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Absolute FloatPosition Vector.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatPositionVector
        extends FloatVectorAbs<PositionUnit, FloatPosition, FloatPositionVector, LengthUnit, FloatLength, FloatLengthVector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a FloatPositionVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public FloatPositionVector(final FloatVectorData data, final PositionUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[] */

    /**
     * Construct a FloatPositionVector from a float[] object. The Float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final float[] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatPositionVector from a float[] object. The Float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatPositionVector(final float[] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPositionVector from a float[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final float[] data, final StorageType storageType)
    {
        this(data, PositionUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatPositionVector from a float[] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public FloatPositionVector(final float[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatPosition[] */

    /**
     * Construct a FloatPositionVector from an array of FloatPosition objects. The FloatPosition values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final FloatPosition[] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(FloatVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatPositionVector from an array of FloatPosition objects. The FloatPosition values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public FloatPositionVector(final FloatPosition[] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPositionVector from an array of FloatPosition objects. The FloatPosition values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final FloatPosition[] data, final StorageType storageType)
    {
        this(data, PositionUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatPositionVector from an array of FloatPosition objects. The FloatPosition values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public FloatPositionVector(final FloatPosition[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Float> or List<Position> */

    /**
     * Construct a FloatPositionVector from a list of Number objects or a list of FloatPosition objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatPosition objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains FloatPosition
     * objects, each FloatPosition has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Position&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final List<? extends Number> data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(new float[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof FloatPosition ? FloatVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatPositionVector from a list of Number objects or a list of FloatPosition objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Float objects) or
     * FloatPosition objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains FloatPosition
     * objects, each FloatPosition has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Position&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatPositionVector(final List<? extends Number> data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPositionVector from a list of Number objects or a list of FloatPosition objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatPosition objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Position&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, PositionUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatPositionVector from a list of Number objects or a list of FloatPosition objects. When data contains
     * numbers such as Float, assume that they are expressed using SI units. When the data consists of FloatPosition objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Float&gt; or List&lt;Position&gt;
     */
    public FloatPositionVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Float> or Map<Integer, FloatPosition> */

    /**
     * Construct a FloatPositionVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatPosition objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatPosition objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatPosition objects, each FloatPosition has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatPosition&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final Map<Integer, ? extends Number> data, final int size, final PositionUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof FloatPosition
                        ? FloatVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : FloatVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct a FloatPositionVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatPosition objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Float objects) or FloatPosition objects. In
     * case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed, as
     * well as the unit in which they will be printed. In case the map contains FloatPosition objects, each FloatPosition has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatPosition&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Float&gt; or List&lt;Number&gt; in general
     */
    public FloatPositionVector(final Map<Integer, ? extends Number> data, final int size, final PositionUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct a FloatPositionVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatPosition objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatPosition objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatPosition&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public FloatPositionVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, PositionUnit.DEFAULT, storageType);
    }

    /**
     * Construct a FloatPositionVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of FloatPosition objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Float, assume that they are expressed using SI units. When the data consists of FloatPosition objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Float&gt; or Map&lt;Integer, FloatPosition&gt;
     * @param size the size off the vector, i.e., the highest index
     */
    public FloatPositionVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<FloatPosition>

            getScalarClass()
    {
        return FloatPosition.class;
    }

    @Override
    public FloatPositionVector instantiateVector(final FloatVectorData fvd, final PositionUnit displayUnit)
    {
        return new FloatPositionVector(fvd, displayUnit);
    }

    @Override
    public FloatPosition instantiateScalarSI(final float valueSI, final PositionUnit displayUnit)
    {
        FloatPosition result = FloatPosition.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatLengthVector instantiateVectorRel(final FloatVectorData fvd, final LengthUnit displayUnit)
    {
        return new FloatLengthVector(fvd, displayUnit);
    }

    @Override
    public FloatLength instantiateScalarRelSI(final float valueSI, final LengthUnit displayUnit)
    {
        FloatLength result = FloatLength.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
