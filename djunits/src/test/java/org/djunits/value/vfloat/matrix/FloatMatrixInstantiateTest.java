package org.djunits.value.vfloat.matrix;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.Try;
import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixAbs;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRelWithAbs;
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.matrix.base.FloatMatrixInterface;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatArea;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;
import org.djunits.value.vfloat.vector.FloatSIVector;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.base.FloatVectorInterface;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.junit.Test;

/**
 * FloatMatrixInstantiateTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatMatrixInstantiateTest
{
    /**
     * Test the constructors of all matrix classes.
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    @Test
    public <U extends Unit<U>, S extends FloatScalarInterface<U, S>, V extends FloatVectorInterface<U, S, V>,
            M extends FloatMatrixInterface<U, S, V, M>> void instatiateAllMatrixTypes()
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (String scalarName : CLASSNAMES.ALL_LIST)
        {
            @SuppressWarnings("unchecked")
            Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(scalarName + "Unit");
            U standardUnit = quantity.getStandardUnit();
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                float[][] testValues = FLOATMATRIX.denseRectArrays(5, 10);
                FloatMatrixData dmd = FloatMatrixData.instantiate(testValues, standardUnit.getScale(), storageType);
                FloatMatrixInterface<U, S, V, M> floatMatrix = FloatMatrix.instantiate(testValues, standardUnit, storageType);
                Class<S> scalarClass = floatMatrix.getScalarClass();
                assertEquals("scalar class should have the right name", "Float" + scalarName, scalarClass.getSimpleName());
                Class<V> vectorClass = floatMatrix.getVectorClass();
                assertEquals("vector class should have the right name", "Float" + scalarName + "Vector",
                        vectorClass.getSimpleName());
                FloatMatrixInterface<U, S, V, M> floatMatrix2 = floatMatrix.instantiateMatrix(dmd, standardUnit);
                assertEquals("matrix constructed from FloatMatrixData should be equal to matrix constructed from float[][]",
                        floatMatrix, floatMatrix2);
                FloatVectorData dvd =
                        FloatVectorData.instantiate(floatMatrix.getRowSI(0), standardUnit.getScale(), storageType);
                FloatVectorInterface<U, S, V> floatVector = floatMatrix.instantiateVector(dvd, standardUnit);
                assertArrayEquals("Float vector contains values from row 0", new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                        floatVector.getValuesSI(), 0.001f);
                // TODO next cast should be unnecessary
                FloatScalarInterface<U, S> floatScalar = floatMatrix.instantiateScalarSI(1.234f, standardUnit);
                assertEquals("Constructed scalar has correct value", 1.234f, floatScalar.getSI(), 0.001);
                assertEquals("Constructed scalar has correct unit", standardUnit, floatScalar.getDisplayUnit());
            }
        }
    }

    /**
     * Test the extra methods that Absolute and Relative with Absolute matrices implement.
     * @param <AU> the absolute unit type
     * @param <RU> the relative unit type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>> void instantiateRelAbsMatrixTypes()
    {
        // Force loading of all classes
        LengthUnit length = UNITS.METER;
        if (length == null)
        {
            fail();
        }

        for (int classIndex = 0; classIndex < CLASSNAMES.REL_WITH_ABS_LIST.size(); classIndex++)
        {
            String relScalarName = CLASSNAMES.REL_WITH_ABS_LIST.get(classIndex);
            String absScalarName = CLASSNAMES.ABS_LIST.get(classIndex);
            Quantity<RU> relQuantity = (Quantity<RU>) Quantities.INSTANCE.getQuantity(relScalarName + "Unit");
            Quantity<AU> absQuantity = (Quantity<AU>) Quantities.INSTANCE.getQuantity(absScalarName + "Unit");
            RU relStandardUnit = relQuantity.getStandardUnit();
            AU absStandardUnit = absQuantity.getStandardUnit();
            for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                float[][] testValues = FLOATMATRIX.denseRectArrays(5, 10);
                FloatMatrixData dmd = FloatMatrixData.instantiate(testValues, relStandardUnit.getScale(), storageType);
                AbstractFloatMatrixRelWithAbs<AU, ?, ?, ?, RU, ?, ?, ?> relFloatMatrix = (AbstractFloatMatrixRelWithAbs<AU, ?,
                        ?, ?, RU, ?, ?, ?>) FloatMatrix.instantiate(testValues, relStandardUnit, storageType);
                AbstractFloatMatrixAbs<AU, ?, ?, ?, RU, ?, ?, ?> absFloatMatrix = (AbstractFloatMatrixAbs<AU, ?, ?, ?, RU, ?, ?,
                        ?>) FloatMatrix.instantiate(testValues, absStandardUnit, storageType);

                FloatMatrixInterface<AU, ?, ?, ?> absFloatMatrix2 = relFloatMatrix.instantiateMatrixAbs(dmd, absStandardUnit);
                assertEquals("matrix constructed from FloatMatrixData should be equal to matrix constructed from float[][]",
                        absFloatMatrix, absFloatMatrix2);
                FloatVectorData dvd =
                        FloatVectorData.instantiate(relFloatMatrix.getRowSI(0), relStandardUnit.getScale(), storageType);
                AbstractFloatVectorAbs<AU, ?, ?, RU, ?, ?> absFloatVector =
                        relFloatMatrix.instantiateVectorAbs(dvd, absStandardUnit);
                assertArrayEquals("Float vector contains values from row 0", new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                        absFloatVector.getValuesSI(), 0.001f);
                AbstractFloatScalarAbs<AU, ?, RU, ?> absFloatScalar =
                        relFloatMatrix.instantiateScalarAbsSI(1.234f, absStandardUnit);
                assertEquals("Constructed scalar has correct value", 1.234f, absFloatScalar.si, 0.001f);
                assertEquals("Constructed scalar has correct unit", absStandardUnit, absFloatScalar.getDisplayUnit());

                FloatMatrixInterface<RU, ?, ?, ?> relFloatMatrix2 = absFloatMatrix.instantiateMatrixRel(dmd, relStandardUnit);
                assertEquals("matrix constructed from FloatMatrixData should be equal to matrix constructed from float[][]",
                        relFloatMatrix, relFloatMatrix2);
                dvd = FloatVectorData.instantiate(absFloatMatrix.getRowSI(0), absStandardUnit.getScale(), storageType);
                AbstractFloatVectorRelWithAbs<AU, ?, ?, RU, ?, ?> relFloatVector =
                        absFloatMatrix.instantiateVectorRel(dvd, relStandardUnit);
                assertArrayEquals("Float vector contains values from row 0", new float[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                        relFloatVector.getValuesSI(), 0.001f);
                AbstractFloatScalarRelWithAbs<AU, ?, RU, ?> relFloatScalar =
                        absFloatMatrix.instantiateScalarRelSI(1.234f, relStandardUnit);
                assertEquals("Constructed scalar has correct value", 1.234f, relFloatScalar.si, 0.001f);
                assertEquals("Constructed scalar has correct unit", relStandardUnit, relFloatScalar.getDisplayUnit());

            }
        }
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateSquareDenseData()
    {
        FloatLengthMatrix lmdkm10 =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(100, lmdkm10.cardinality());
        assertEquals(50 * 101 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        FloatLengthMatrix lmskm10 =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(100, lmskm10.cardinality());
        assertEquals(50 * 101 * 1000.0, lmskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same
        assertEquals(lmdkm10, lmdkm10.toDense());
        assertEquals(lmskm10, lmskm10.toSparse());

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        assertTrue(lmdkm10.isDense());
        assertTrue(lmskm10.isSparse());

        FloatLengthMatrix lmdsi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(100, lmdsi10.cardinality());
        assertEquals(50 * 101, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        FloatLengthMatrix lmssi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(100, lmssi10.cardinality());
        assertEquals(50 * 101, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        FloatLengthMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(10, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(100, lmdsc10.cardinality());
        assertEquals(50 * 101, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        FloatLengthMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(10, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(100, lmssc10.cardinality());
        assertEquals(50 * 101, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatSquareSparseData()
    {
        FloatLengthMatrix lmdkm10 =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        FloatLengthMatrix lmskm10 =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(10, lmskm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());

        FloatLengthMatrix lmdsi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        FloatLengthMatrix lmssi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        FloatLengthMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(10, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        FloatLengthMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(10, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        FloatLengthMatrix lmdtu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(10, 10, FloatLength.class), 10, 10,
                LengthUnit.NANOMETER, StorageType.DENSE);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        FloatLengthMatrix lmstu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(10, 10, FloatLength.class), 10, 10,
                LengthUnit.NANOMETER, StorageType.SPARSE);
        assertEquals(10, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(10, lmstu10.cardinality());
        assertEquals(5 * 11, lmstu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateSquareDenseDataWithClass()
    {
        FloatAreaMatrix lmdkm10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(100, lmdkm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lmdkm10.zSum().getSI(), 1000);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        FloatAreaMatrix lmskm10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(100, lmskm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lmskm10.zSum().getSI(), 1000);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());

        FloatAreaMatrix lmdsi10 = FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(100, lmdsi10.cardinality());
        assertEquals(50 * 101, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        FloatAreaMatrix lmssi10 = FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(100, lmssi10.cardinality());
        assertEquals(50 * 101, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        FloatAreaMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(10, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(100, lmdsc10.cardinality());
        assertEquals(50 * 101, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        FloatAreaMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(10, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(100, lmssc10.cardinality());
        assertEquals(50 * 101, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatSquareSparseDataWithClass()
    {
        FloatAreaMatrix lmdkm10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        FloatAreaMatrix lmskm10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(10, lmskm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmskm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());

        FloatAreaMatrix lmdsi10 = FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        FloatAreaMatrix lmssi10 = FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        FloatAreaMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(10, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        FloatAreaMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(10, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());

        FloatAreaMatrix lmdtu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(10, 10, FloatArea.class), 10, 10,
                AreaUnit.ACRE, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmdtu10.getDisplayUnit());

        FloatAreaMatrix lmstu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(10, 10, FloatArea.class), 10, 10,
                AreaUnit.ACRE, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(10, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(10, lmstu10.cardinality());
        assertEquals(5 * 11, lmstu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     * @throws UnitException on error
     */
    @Test
    public void testInstantiateSquareSIUnit() throws UnitException
    {
        FloatSIMatrix si10dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(100, si10dd.cardinality());
        assertEquals(50 * 101, si10dd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        FloatSIMatrix si10ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(10, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(100, si10ds.cardinality());
        assertEquals(50 * 101, si10ds.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        FloatSIMatrix si10sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        FloatSIMatrix si10ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(10, si10ss.rows());
        assertEquals(10, si10ss.cols());
        assertEquals(10, si10ss.cardinality());
        assertEquals(5 * 11, si10ss.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ss.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        assertEquals(si10dd, si10ds.toDense());
        assertEquals(si10ds, si10dd.toSparse());
        assertEquals(si10dd, si10dd.toDense());
        assertEquals(si10ds, si10ds.toSparse());
        assertEquals(si10dd, si10dd.toSparse().toDense());
        assertEquals(si10ds, si10ds.toDense().toSparse());

        assertEquals(si10dd.hashCode(), si10ds.toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10dd.toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toSparse().toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toDense().toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toSparse().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toDense().hashCode());

        assertEquals(si10sd, si10ss.toDense());
        assertEquals(si10ss, si10sd.toSparse());
        assertEquals(si10sd, si10sd.toDense());
        assertEquals(si10ss, si10ss.toSparse());
        assertEquals(si10sd, si10sd.toSparse().toDense());
        assertEquals(si10ss, si10ss.toDense().toSparse());

        assertEquals(si10sd.hashCode(), si10ss.toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10sd.toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toSparse().toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toDense().toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toSparse().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toDense().hashCode());
    }

    // =============================================== RECTANGULAR MATRICES ===================================================

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateRectDenseData()
    {
        FloatLengthMatrix lmdkm10 =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(200, lmdkm10.cardinality());
        assertEquals(100 * 201 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        FloatLengthMatrix lmskm10 =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(200, lmskm10.cardinality());
        assertEquals(100 * 201 * 1000.0, lmskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same
        assertEquals(lmdkm10, lmdkm10.toDense());
        assertEquals(lmskm10, lmskm10.toSparse());

        assertTrue(lmdkm10.isDense());
        assertTrue(lmskm10.isSparse());

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        FloatLengthMatrix lmdsi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(200, lmdsi10.cardinality());
        assertEquals(100 * 201, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        FloatLengthMatrix lmssi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(200, lmssi10.cardinality());
        assertEquals(100 * 201, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        FloatLengthMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(20, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(200, lmdsc10.cardinality());
        assertEquals(100 * 201, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        FloatLengthMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(20, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(200, lmssc10.cardinality());
        assertEquals(100 * 201, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatRectSparseData()
    {
        FloatLengthMatrix lmdkm10 =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        FloatLengthMatrix lmskm10 =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(10, lmskm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        FloatLengthMatrix lmdsi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        FloatLengthMatrix lmssi10 =
                FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        FloatLengthMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(20, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        FloatLengthMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(20, 10, FloatLength.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        FloatLengthMatrix lmdtu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(20, 10, FloatLength.class), 20, 10,
                LengthUnit.NANOMETER, StorageType.DENSE);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        FloatLengthMatrix lmstu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(20, 10, FloatLength.class), 20, 10,
                LengthUnit.NANOMETER, StorageType.SPARSE);
        assertEquals(20, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(10, lmstu10.cardinality());
        assertEquals(5 * 11, lmstu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateRectDenseDataWithClass()
    {
        FloatAreaMatrix lmdkm10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(200, lmdkm10.cardinality());
        assertEquals(100 * 201 * 1.0E6, lmdkm10.zSum().getSI(), 1000);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        FloatAreaMatrix lmskm10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(200, lmskm10.cardinality());
        assertEquals(100 * 201 * 1.0E6, lmskm10.zSum().getSI(), 1000);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        FloatAreaMatrix lmdsi10 = FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(200, lmdsi10.cardinality());
        assertEquals(100 * 201, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        FloatAreaMatrix lmssi10 = FloatMatrix.instantiateSI(FLOATMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(200, lmssi10.cardinality());
        assertEquals(100 * 201, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        FloatAreaMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(20, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(200, lmdsc10.cardinality());
        assertEquals(100 * 201, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        FloatAreaMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.denseRectScalarArrays(20, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(200, lmssc10.cardinality());
        assertEquals(100 * 201, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatRectSparseDataWithClass()
    {
        FloatAreaMatrix lmdkm10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        FloatAreaMatrix lmskm10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(10, lmskm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmskm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmskm10.getDisplayUnit());

        assertEquals(lmdkm10, lmdkm10.toSparse().toDense());
        assertEquals(lmskm10, lmskm10.toDense().toSparse());
        assertEquals(lmdkm10, lmskm10.toDense());
        assertEquals(lmskm10, lmdkm10.toSparse());
        assertEquals(lmdkm10, lmdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lmskm10, lmskm10.toDense()); // dense and sparse are the same if content is the same

        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toSparse().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toDense().hashCode());
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        FloatAreaMatrix lmdsi10 = FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        FloatAreaMatrix lmssi10 = FloatMatrix.instantiateSI(FLOATMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        FloatAreaMatrix lmdsc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(20, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        FloatAreaMatrix lmssc10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectScalarArrays(20, 10, FloatArea.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());

        FloatAreaMatrix lmdtu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(20, 10, FloatArea.class), 20, 10,
                AreaUnit.ACRE, StorageType.DENSE, FloatAreaMatrix.class);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmdtu10.getDisplayUnit());

        FloatAreaMatrix lmstu10 = FloatMatrix.instantiate(FLOATMATRIX.sparseRectTuples(20, 10, FloatArea.class), 20, 10,
                AreaUnit.ACRE, StorageType.SPARSE, FloatAreaMatrix.class);
        assertEquals(20, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(10, lmstu10.cardinality());
        assertEquals(5 * 11, lmstu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmstu10.getDisplayUnit());

        assertNotEquals(lmdkm10, lmdsi10);
        assertNotEquals(lmdkm10, lmssi10);
        assertNotEquals(lmskm10, lmdsi10);
        assertNotEquals(lmskm10, lmssi10);
        assertNotEquals(lmdkm10, lmdtu10);
        assertNotEquals(lmdkm10, lmstu10);
        assertNotEquals(lmskm10, lmdtu10);
        assertNotEquals(lmskm10, lmstu10);

        assertNotEquals(lmdkm10.hashCode(), lmdsi10.hashCode());
        assertNotEquals(lmdkm10.hashCode(), lmssi10.hashCode());
        assertNotEquals(lmskm10.hashCode(), lmdsi10.hashCode());
        assertNotEquals(lmskm10.hashCode(), lmssi10.hashCode());
        assertNotEquals(lmdkm10.hashCode(), lmdtu10.hashCode());
        assertNotEquals(lmdkm10.hashCode(), lmstu10.hashCode());
        assertNotEquals(lmskm10.hashCode(), lmdtu10.hashCode());
        assertNotEquals(lmskm10.hashCode(), lmstu10.hashCode());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     * @throws UnitException on error
     */
    @Test
    public void testInstantiateRectSIUnit() throws UnitException
    {
        FloatSIMatrix si10dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(200, si10dd.cardinality());
        assertEquals(100 * 201, si10dd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));
        assertEquals("getScalarClass returns FloatSIScalar", FloatSIScalar.class, si10dd.getScalarClass());
        assertEquals("getVectorClass returns FloatSIVector", FloatSIVector.class, si10dd.getVectorClass());

        FloatSIMatrix si10ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(20, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(200, si10ds.cardinality());
        assertEquals(100 * 201, si10ds.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        FloatSIMatrix si10sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        FloatSIMatrix si10ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(20, si10ss.rows());
        assertEquals(10, si10ss.cols());
        assertEquals(10, si10ss.cardinality());
        assertEquals(5 * 11, si10ss.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ss.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        assertEquals(si10dd, si10ds.toDense());
        assertEquals(si10ds, si10dd.toSparse());
        assertEquals(si10dd, si10dd.toDense());
        assertEquals(si10ds, si10ds.toSparse());
        assertEquals(si10dd, si10dd.toSparse().toDense());
        assertEquals(si10ds, si10ds.toDense().toSparse());

        assertEquals(si10sd, si10ss.toDense());
        assertEquals(si10ss, si10sd.toSparse());
        assertEquals(si10sd, si10sd.toDense());
        assertEquals(si10ss, si10ss.toSparse());
        assertEquals(si10sd, si10sd.toSparse().toDense());
        assertEquals(si10ss, si10ss.toDense().toSparse());

        assertEquals(si10dd.hashCode(), si10ds.toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10dd.toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toSparse().toDense().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toDense().toSparse().hashCode());
        assertEquals(si10dd.hashCode(), si10dd.toSparse().hashCode());
        assertEquals(si10ds.hashCode(), si10ds.toDense().hashCode());

        assertEquals(si10sd.hashCode(), si10ss.toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10sd.toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toSparse().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toDense().hashCode());
    }

    // =============================================== EDGE CASE MATRICES ===================================================

    /**
     * Test the instantiation of dense and sparse matrix types with one row or one column, and errors with null pointers.
     */
    @Test
    @SuppressWarnings({ "checkstyle:methodlength", "checkstyle:localvariablename" })
    public void testInstantiateEdgeCases()
    {
        // DENSE DATA

        FloatSpeedMatrix row1dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1dd.rows());
        assertEquals(10, row1dd.cols());
        assertEquals(10, row1dd.cardinality());
        assertEquals(5 * 11, row1dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1dd.getDisplayUnit());

        FloatSpeedMatrix col1dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1dd.rows());
        assertEquals(1, col1dd.cols());
        assertEquals(10, col1dd.cardinality());
        assertEquals(5 * 11, col1dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1dd.getDisplayUnit());

        FloatSpeedMatrix row1ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ds.rows());
        assertEquals(10, row1ds.cols());
        assertEquals(10, row1ds.cardinality());
        assertEquals(5 * 11, row1ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ds.getDisplayUnit());

        FloatSpeedMatrix col1ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(10, col1ds.rows());
        assertEquals(1, col1ds.cols());
        assertEquals(10, col1ds.cardinality());
        assertEquals(5 * 11, col1ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1ds.getDisplayUnit());

        // SPARSE DATA

        FloatSpeedMatrix row1sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1sd.rows());
        assertEquals(10, row1sd.cols());
        assertEquals(1, row1sd.cardinality());
        assertEquals(1, row1sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1sd.getDisplayUnit());

        FloatSpeedMatrix col1sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1sd.rows());
        assertEquals(1, col1sd.cols());
        assertEquals(1, col1sd.cardinality());
        assertEquals(1, col1sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1sd.getDisplayUnit());

        FloatSpeedMatrix row1ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ss.rows());
        assertEquals(10, row1ss.cols());
        assertEquals(1, row1ss.cardinality());
        assertEquals(1, row1ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ss.getDisplayUnit());

        FloatSpeedMatrix col1ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(10, col1ss.rows());
        assertEquals(1, col1ss.cols());
        assertEquals(1, col1ss.cardinality());
        assertEquals(1, col1ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1ss.getDisplayUnit());

        // equals

        assertEquals(row1dd, row1dd);
        assertEquals(row1ss, row1ss);
        assertEquals(row1dd, row1ds);
        assertEquals(col1dd, col1ds);
        assertEquals(row1sd, row1ss);
        assertEquals(col1sd, col1ss);
        assertEquals(row1ds, row1dd);
        assertEquals(col1ds, col1dd);
        assertEquals(row1ss, row1sd);
        assertEquals(col1ss, col1sd);
        assertNotEquals(row1dd, col1dd);
        assertNotEquals(col1dd, row1dd);
        assertNotEquals(row1ss, col1ss);
        assertNotEquals(col1ss, row1ss);
        assertNotEquals(row1ds, col1sd);
        assertNotEquals(col1ds, row1sd);
        assertNotEquals(row1sd, col1ds);
        assertNotEquals(col1sd, row1ds);

        assertEquals(row1dd.hashCode(), row1dd.hashCode());
        assertEquals(row1ss.hashCode(), row1ss.hashCode());
        assertEquals(row1dd.hashCode(), row1ds.hashCode());
        assertEquals(col1dd.hashCode(), col1ds.hashCode());
        assertEquals(row1sd.hashCode(), row1ss.hashCode());
        assertEquals(col1sd.hashCode(), col1ss.hashCode());
        assertEquals(row1ds.hashCode(), row1dd.hashCode());
        assertEquals(col1ds.hashCode(), col1dd.hashCode());
        assertEquals(row1ss.hashCode(), row1sd.hashCode());
        assertEquals(col1ss.hashCode(), col1sd.hashCode());
        assertNotEquals(row1dd.hashCode(), col1dd.hashCode());
        assertNotEquals(col1dd.hashCode(), row1dd.hashCode());
        assertNotEquals(row1ss.hashCode(), col1ss.hashCode());
        assertNotEquals(col1ss.hashCode(), row1ss.hashCode());
        assertNotEquals(row1ds.hashCode(), col1sd.hashCode());
        assertNotEquals(col1ds.hashCode(), row1sd.hashCode());
        assertNotEquals(row1sd.hashCode(), col1ds.hashCode());
        assertNotEquals(col1sd.hashCode(), row1ds.hashCode());

        // 1 x 1 DENSE DATA

        FloatSpeedMatrix row11dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11dd.rows());
        assertEquals(1, row11dd.cols());
        assertEquals(1, row11dd.cardinality());
        assertEquals(1, row11dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11dd.getDisplayUnit());

        FloatSpeedMatrix col11dd =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11dd.rows());
        assertEquals(1, col11dd.cols());
        assertEquals(1, col11dd.cardinality());
        assertEquals(1, col11dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11dd.getDisplayUnit());

        FloatSpeedMatrix row11ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ds.rows());
        assertEquals(1, row11ds.cols());
        assertEquals(1, row11ds.cardinality());
        assertEquals(1, row11ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ds.getDisplayUnit());

        FloatSpeedMatrix col11ds =
                FloatMatrix.instantiate(FLOATMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ds.rows());
        assertEquals(1, col11ds.cols());
        assertEquals(1, col11ds.cardinality());
        assertEquals(1, col11ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ds.getDisplayUnit());

        // 1 x 1 SPARSE DATA

        FloatSpeedMatrix row11sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11sd.rows());
        assertEquals(1, row11sd.cols());
        assertEquals(1, row11sd.cardinality());
        assertEquals(1, row11sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11sd.getDisplayUnit());

        FloatSpeedMatrix col11sd =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11sd.rows());
        assertEquals(1, col11sd.cols());
        assertEquals(1, col11sd.cardinality());
        assertEquals(1, col11sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11sd.getDisplayUnit());

        FloatSpeedMatrix row11ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ss.rows());
        assertEquals(1, row11ss.cols());
        assertEquals(1, row11ss.cardinality());
        assertEquals(1, row11ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ss.getDisplayUnit());

        FloatSpeedMatrix col11ss =
                FloatMatrix.instantiate(FLOATMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ss.rows());
        assertEquals(1, col11ss.cols());
        assertEquals(1, col11ss.cardinality());
        assertEquals(1, col11ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ss.getDisplayUnit());

        // NULL

        float[][] d1_1 = new float[1][];
        d1_1[0] = new float[1];

        new Try()
        {
            @Override
            public void execute()
            {
                FloatMatrix.instantiate((float[][]) null, SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
            }
        }.test("constructing matrix with null input should have thrown an exception", NullPointerException.class);

        new Try()
        {
            @Override
            public void execute()
            {
                FloatMatrix.instantiate(d1_1, null, StorageType.DENSE);
            }
        }.test("constructing matrix with null unit should have thrown an exception", NullPointerException.class);

        new Try()
        {
            @Override
            public void execute()
            {
                FloatMatrix.instantiate(d1_1, SpeedUnit.METER_PER_SECOND, null);
            }
        }.test("constructing matrix with null storage type should have thrown an exception", NullPointerException.class);
    }
    
    /**
     * Test matrix construction and operations with zero length.
     */
    @Test
    public void testInstantiateZero()
    {
        float[][] floatMatrix00 = new float[0][0];
        float[][] floatMatrix10 = new float[1][];
        floatMatrix10[0] = new float[] {};
        FloatSpeed[][] speedMatrix00 = new FloatSpeed[0][0];
        FloatSpeed[][] speedMatrix10 = new FloatSpeed[1][];
        speedMatrix10[0] = new FloatSpeed[] {};
        Collection<FloatSparseValue<SpeedUnit, FloatSpeed>> speedList = new ArrayList<>();
        SortedMap<Integer, FloatSpeedMatrix> smMap = new TreeMap<>();
        smMap.put(0,
                FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(floatMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(1,
                FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(floatMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(2, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedMatrix00, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(3, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedMatrix00, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(4, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedList, 0, 0, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(5, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedList, 0, 0, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(6,
                FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(floatMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(7,
                FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(floatMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(8, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedMatrix10, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(9, FloatMatrix.instantiate(FloatMatrixData.instantiate(speedMatrix10, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));

        for (Map.Entry<Integer, FloatSpeedMatrix> entry : smMap.entrySet())
        {
            int key = entry.getKey();
            FloatSpeedMatrix sv = entry.getValue();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, 0, sv.cardinality());
            assertEquals("key=" + key, 0, sv.getScalars().length);
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, sv.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, sv.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            assertFalse("key=" + key, sv.isMutable());
            FloatSpeedMatrix svm = sv.mutable();
            assertTrue("key=" + key, svm.isMutable());
            assertEquals(SpeedUnit.KM_PER_HOUR, svm.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, svm.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            FloatSpeedMatrix svr = svm.abs();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svm = svm.ceil();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.decrementBy(FloatSpeed.ONE);
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.incrementBy(FloatSpeed.ONE);
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.plus(sv);
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, svr.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            svr = sv.toDense();
            assertEquals("key=" + key, StorageType.DENSE, svr.getStorageType());
            svr = sv.toSparse();
            assertEquals("key=" + key, StorageType.SPARSE, svr.getStorageType());
            assertEquals("key=" + key, 0, sv.getValuesSI().length);
            assertEquals("key=" + key, 0, sv.getValuesInUnit().length);
            assertEquals("key=" + key, 0, sv.getValuesInUnit(SpeedUnit.KNOT).length);
            svr = sv.times(2.0);
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
        }
    }

}
