package org.djunits.value.vdouble.vector;

import java.util.List;
import java.util.Map;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double DimensionlessVector, a vector of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T11:42:31.564730700Z")
public class DimensionlessVector extends DoubleVectorRel<DimensionlessUnit, Dimensionless, DimensionlessVector>
        implements DoubleMathFunctions, DimensionlessFunctions<DimensionlessUnit, DimensionlessVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an DimensionlessVector from an internal data object.
     * @param data the internal data object for the vector
     * @param displayUnit the display unit of the vector data
     */
    public DimensionlessVector(final DoubleVectorData data, final DimensionlessUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[] */

    /**
     * Construct an DimensionlessVector from a double[] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the vector, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final double[] data, final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct an DimensionlessVector from a double[] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public DimensionlessVector(final double[] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an DimensionlessVector from a double[] object with SI-unit values.
     * @param data the data for the vector, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final double[] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct an DimensionlessVector from a double[] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array.
     * @param data the data for the vector, in SI units
     */
    public DimensionlessVector(final double[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Dimensionless[] */

    /**
     * Construct an DimensionlessVector from an array of Dimensionless objects. The Dimensionless values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final Dimensionless[] data, final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(DoubleVectorData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct an DimensionlessVector from an array of Dimensionless objects. The Dimensionless values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     * @param displayUnit the display unit of the values when printing
     */
    public DimensionlessVector(final Dimensionless[] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an DimensionlessVector from an array of Dimensionless objects. The Dimensionless values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array.
     * @param data the data for the vector
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final Dimensionless[] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct an DimensionlessVector from an array of Dimensionless objects. The Dimensionless values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array.
     * @param data the data for the vector
     */
    public DimensionlessVector(final Dimensionless[] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH List<Double> or List<Dimensionless> */

    /**
     * Construct an DimensionlessVector from a list of Number objects or a list of Dimensionless objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * Dimensionless objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains Dimensionless
     * objects, each Dimensionless has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Dimensionless&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final List<? extends Number> data, final DimensionlessUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(new double[] {}, IdentityScale.SCALE, storageType)
                : data.get(0) instanceof Dimensionless ? DoubleVectorData.instantiate(data, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an DimensionlessVector from a list of Number objects or a list of Dimensionless objects. Note that the
     * displayUnit has a different meaning depending on whether the list contains Number objects (e.g., Double objects) or
     * Dimensionless objects. In case the list contains Number objects, the displayUnit indicates the unit in which the values
     * in the list are expressed, as well as the unit in which they will be printed. In case the list contains Dimensionless
     * objects, each Dimensionless has its own unit, and the displayUnit is just used for printing. The values but will always
     * be internally stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume
     * the storage type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Dimensionless&gt;
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public DimensionlessVector(final List<? extends Number> data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct an DimensionlessVector from a list of Number objects or a list of Dimensionless objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of Dimensionless objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Dimensionless&gt;
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final List<? extends Number> data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct an DimensionlessVector from a list of Number objects or a list of Dimensionless objects. When data contains
     * numbers such as Double, assume that they are expressed using SI units. When the data consists of Dimensionless objects,
     * they each have their own unit, but will be printed using SI units or base units. The values but will always be internally
     * stored as SI values or base values, and expressed using the display unit or base unit when printing. Assume the storage
     * type is DENSE since we offer the data as a List.
     * @param data the data for the vector as a List&lt;Double&gt; or List&lt;Dimensionless&gt;
     */
    public DimensionlessVector(final List<? extends Number> data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Map<Integer, Double> or Map<Integer, Dimensionless> */

    /**
     * Construct an DimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Dimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or Dimensionless objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains Dimensionless objects, each Dimensionless has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Dimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final Map<Integer, ? extends Number> data, final int size, final DimensionlessUnit displayUnit,
            final StorageType storageType)
    {
        this(data.size() == 0 ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                : data.values().iterator().next() instanceof Dimensionless
                        ? DoubleVectorData.instantiate(data, size, IdentityScale.SCALE, storageType)
                        : DoubleVectorData.instantiate(data, size, displayUnit.getScale(), storageType),
                displayUnit);
    }

    /**
     * Construct an DimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Dimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. Note that the displayUnit has a
     * different meaning depending on whether the map contains Number objects (e.g., Double objects) or Dimensionless objects.
     * In case the map contains Number objects, the displayUnit indicates the unit in which the values in the map are expressed,
     * as well as the unit in which they will be printed. In case the map contains Dimensionless objects, each Dimensionless has
     * its own unit, and the displayUnit is just used for printing. The values but will always be internally stored as SI values
     * or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE since
     * we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Dimensionless&gt;
     * @param size the size off the vector, i.e., the highest index
     * @param displayUnit the display unit of the vector data, and the unit of the data points when the data is expressed as
     *            List&lt;Double&gt; or List&lt;Number&gt; in general
     */
    public DimensionlessVector(final Map<Integer, ? extends Number> data, final int size, final DimensionlessUnit displayUnit)
    {
        this(data, size, displayUnit, StorageType.SPARSE);
    }

    /**
     * Construct an DimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Dimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Dimensionless objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Dimensionless&gt;;
     * @param size the size off the vector, i.e., the highest index
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Vector
     */
    public DimensionlessVector(final Map<Integer, ? extends Number> data, final int size, final StorageType storageType)
    {
        this(data, size, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct an DimensionlessVector from a (sparse) map of index values to Number objects or a (sparse) map of index values
     * to of Dimensionless objects. Using index values is particularly useful for sparse vectors. The size parameter indicates
     * the size of the vector, since the largest index does not have to be part of the map. When data contains numbers such as
     * Double, assume that they are expressed using SI units. When the data consists of Dimensionless objects, they each have
     * their own unit, but will be printed using SI units or base units. The values but will always be internally stored as SI
     * values or base values, and expressed using the display unit or base unit when printing. Assume the storage type is SPARSE
     * since we offer the data as a Map.
     * @param data the data for the vector as a Map&lt;Integer, Double&gt; or Map&lt;Integer, Dimensionless&gt;;
     * @param size the size off the vector, i.e., the highest index
     */
    public DimensionlessVector(final Map<Integer, ? extends Number> data, final int size)
    {
        this(data, size, StorageType.SPARSE);
    }

    /* ****************************** Other methods ****************************** */

    @Override
    public Class<Dimensionless> getScalarClass()
    {
        return Dimensionless.class;
    }

    @Override
    public DimensionlessVector instantiateVector(final DoubleVectorData dvd, final DimensionlessUnit displayUnit)
    {
        return new DimensionlessVector(dvd, displayUnit);
    }

    @Override
    public Dimensionless instantiateScalarSI(final double valueSI, final DimensionlessUnit displayUnit)
    {
        Dimensionless result = Dimensionless.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public final DimensionlessVector acos()
    {
        assign(DoubleMathFunctions.ACOS);
        return this;
    }

    @Override
    public final DimensionlessVector asin()
    {
        assign(DoubleMathFunctions.ASIN);
        return this;
    }

    @Override
    public final DimensionlessVector atan()
    {
        assign(DoubleMathFunctions.ATAN);
        return this;
    }

    @Override
    public final DimensionlessVector cbrt()
    {
        assign(DoubleMathFunctions.CBRT);
        return this;
    }

    @Override
    public final DimensionlessVector cos()
    {
        assign(DoubleMathFunctions.COS);
        return this;
    }

    @Override
    public final DimensionlessVector cosh()
    {
        assign(DoubleMathFunctions.COSH);
        return this;
    }

    @Override
    public final DimensionlessVector exp()
    {
        assign(DoubleMathFunctions.EXP);
        return this;
    }

    @Override
    public final DimensionlessVector expm1()
    {
        assign(DoubleMathFunctions.EXPM1);
        return this;
    }

    @Override
    public final DimensionlessVector log()
    {
        assign(DoubleMathFunctions.LOG);
        return this;
    }

    @Override
    public final DimensionlessVector log10()
    {
        assign(DoubleMathFunctions.LOG10);
        return this;
    }

    @Override
    public final DimensionlessVector log1p()
    {
        assign(DoubleMathFunctions.LOG1P);
        return this;
    }

    @Override
    public final DimensionlessVector pow(final double x)
    {
        assign(DoubleMathFunctions.POW((float) x));
        return this;
    }

    @Override
    public final DimensionlessVector signum()
    {
        assign(DoubleMathFunctions.SIGNUM);
        return this;
    }

    @Override
    public final DimensionlessVector sin()
    {
        assign(DoubleMathFunctions.SIN);
        return this;
    }

    @Override
    public final DimensionlessVector sinh()
    {
        assign(DoubleMathFunctions.SINH);
        return this;
    }

    @Override
    public final DimensionlessVector sqrt()
    {
        assign(DoubleMathFunctions.SQRT);
        return this;
    }

    @Override
    public final DimensionlessVector tan()
    {
        assign(DoubleMathFunctions.TAN);
        return this;
    }

    @Override
    public final DimensionlessVector tanh()
    {
        assign(DoubleMathFunctions.TANH);
        return this;
    }

    @Override
    public final DimensionlessVector inv()
    {
        assign(DoubleMathFunctions.INV);
        return this;
    }

}
