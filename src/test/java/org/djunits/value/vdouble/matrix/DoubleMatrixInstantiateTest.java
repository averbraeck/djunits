package org.djunits.value.vdouble.matrix;

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
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixInterface;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRelWithAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.djunits.value.vdouble.vector.SIVector;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRelWithAbs;
import org.djunits.value.vdouble.vector.base.DoubleVectorInterface;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.junit.Test;

/**
 * DoubleMatrixInstantiateTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleMatrixInstantiateTest
{
    /**
     * Test the constructors of all matrix classes.
     * @param <U> the unit type
     */
    @Test
    public <U extends Unit<U>> void instatiateAllMatrixTypes()
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
            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                double[][] testValues = DOUBLEMATRIX.denseRectArrays(5, 10);
                DoubleMatrixData dmd = DoubleMatrixData.instantiate(testValues, standardUnit.getScale(), storageType);
                DoubleMatrixInterface<U, ?, ?, ?> doubleMatrix =
                        DoubleMatrix.instantiate(testValues, standardUnit, storageType);
                Class<?> scalarClass = doubleMatrix.getScalarClass();
                assertEquals("scalar class should have the right name", scalarName, scalarClass.getSimpleName());
                Class<?> vectorClass = doubleMatrix.getVectorClass();
                assertEquals("vector class should have the right name", scalarName + "Vector", vectorClass.getSimpleName());
                DoubleMatrixInterface<U, ?, ?, ?> doubleMatrix2 = doubleMatrix.instantiateMatrix(dmd, standardUnit);
                assertEquals("matrix constructed from DoubleMatrixData should be equal to matrix constructed from double[][]",
                        doubleMatrix, doubleMatrix2);
                DoubleVectorData dvd =
                        DoubleVectorData.instantiate(doubleMatrix.getRowSI(0), standardUnit.getScale(), storageType);
                DoubleVectorInterface<U, ?, ?> doubleVector = doubleMatrix.instantiateVector(dvd, standardUnit);
                assertArrayEquals("Double vector contains values from row 0", new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        doubleVector.getValuesSI(), 0.001);
                DoubleScalarInterface<U, ?> doubleScalar = doubleMatrix.instantiateScalarSI(1.234, standardUnit);
                assertEquals("Constructed scalar has correct value", 1.234, doubleScalar.getSI(), 0.001);
                assertEquals("Constructed scalar has correct unit", standardUnit, doubleScalar.getDisplayUnit());
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
            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                double[][] testValues = DOUBLEMATRIX.denseRectArrays(5, 10);
                DoubleMatrixData dmd = DoubleMatrixData.instantiate(testValues, relStandardUnit.getScale(), storageType);
                AbstractDoubleMatrixRelWithAbs<AU, ?, ?, ?, RU, ?, ?, ?> relDoubleMatrix = (AbstractDoubleMatrixRelWithAbs<AU,
                        ?, ?, ?, RU, ?, ?, ?>) DoubleMatrix.instantiate(testValues, relStandardUnit, storageType);
                AbstractDoubleMatrixAbs<AU, ?, ?, ?, RU, ?, ?, ?> absDoubleMatrix = (AbstractDoubleMatrixAbs<AU, ?, ?, ?, RU, ?,
                        ?, ?>) DoubleMatrix.instantiate(testValues, absStandardUnit, storageType);

                DoubleMatrixInterface<AU, ?, ?, ?> absDoubleMatrix2 =
                        relDoubleMatrix.instantiateMatrixAbs(dmd, absStandardUnit);
                assertEquals("matrix constructed from DoubleMatrixData should be equal to matrix constructed from double[][]",
                        absDoubleMatrix, absDoubleMatrix2);
                DoubleVectorData dvd =
                        DoubleVectorData.instantiate(relDoubleMatrix.getRowSI(0), relStandardUnit.getScale(), storageType);
                AbstractDoubleVectorAbs<AU, ?, ?, RU, ?, ?> absDoubleVector =
                        relDoubleMatrix.instantiateVectorAbs(dvd, absStandardUnit);
                assertArrayEquals("Double vector contains values from row 0", new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        absDoubleVector.getValuesSI(), 0.001);
                AbstractDoubleScalarAbs<AU, ?, RU, ?> absDoubleScalar =
                        relDoubleMatrix.instantiateScalarAbsSI(1.234, absStandardUnit);
                assertEquals("Constructed scalar has correct value", 1.234, absDoubleScalar.si, 0.001);
                assertEquals("Constructed scalar has correct unit", absStandardUnit, absDoubleScalar.getDisplayUnit());

                DoubleMatrixInterface<RU, ?, ?, ?> relDoubleMatrix2 =
                        absDoubleMatrix.instantiateMatrixRel(dmd, relStandardUnit);
                assertEquals("matrix constructed from DoubleMatrixData should be equal to matrix constructed from double[][]",
                        relDoubleMatrix, relDoubleMatrix2);
                dvd = DoubleVectorData.instantiate(absDoubleMatrix.getRowSI(0), absStandardUnit.getScale(), storageType);
                AbstractDoubleVectorRelWithAbs<AU, ?, ?, RU, ?, ?> relDoubleVector =
                        absDoubleMatrix.instantiateVectorRel(dvd, relStandardUnit);
                assertArrayEquals("Double vector contains values from row 0", new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        relDoubleVector.getValuesSI(), 0.001);
                AbstractDoubleScalarRelWithAbs<AU, ?, RU, ?> relDoubleScalar =
                        absDoubleMatrix.instantiateScalarRelSI(1.234, relStandardUnit);
                assertEquals("Constructed scalar has correct value", 1.234, relDoubleScalar.si, 0.001);
                assertEquals("Constructed scalar has correct unit", relStandardUnit, relDoubleScalar.getDisplayUnit());

            }
        }
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateSquareDenseData()
    {
        LengthMatrix lmdkm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(100, lmdkm10.cardinality());
        assertEquals(50 * 101 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
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

        LengthMatrix lmdsi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(100, lmdsi10.cardinality());
        assertEquals(50 * 101, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(100, lmssi10.cardinality());
        assertEquals(50 * 101, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(100, lmdsc10.cardinality());
        assertEquals(50 * 101, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Length.class),
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
        LengthMatrix lmdkm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
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
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        LengthMatrix lmdsi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(10, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(10, 10, Length.class), 10, 10,
                LengthUnit.NANOMETER, StorageType.DENSE);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(10, 10, Length.class), 10, 10,
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
        AreaMatrix lmdkm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(100, lmdkm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        AreaMatrix lmskm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(100, lmskm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lmskm10.zSum().getSI(), 0.1);
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

        AreaMatrix lmdsi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(100, lmdsi10.cardinality());
        assertEquals(50 * 101, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        AreaMatrix lmssi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(100, lmssi10.cardinality());
        assertEquals(50 * 101, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        AreaMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(100, lmdsc10.cardinality());
        assertEquals(50 * 101, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        AreaMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, AreaMatrix.class);
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
        AreaMatrix lmdkm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        AreaMatrix lmskm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, AreaMatrix.class);
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
        assertEquals(lmdkm10.hashCode(), lmdkm10.toDense().hashCode());
        assertEquals(lmskm10.hashCode(), lmskm10.toSparse().hashCode());

        AreaMatrix lmdsi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        AreaMatrix lmssi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(10, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        AreaMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        AreaMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, AreaMatrix.class);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());

        AreaMatrix lmdtu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(10, 10, Area.class), 10, 10, AreaUnit.ACRE,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmdtu10.getDisplayUnit());

        AreaMatrix lmstu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(10, 10, Area.class), 10, 10, AreaUnit.ACRE,
                StorageType.SPARSE, AreaMatrix.class);
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
        SIMatrix si10dd = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(100, si10dd.cardinality());
        assertEquals(50 * 101, si10dd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));
        assertEquals("getScalarClass returns SIScalar", SIScalar.class, si10dd.getScalarClass());
        assertEquals("getVectorClass returns SIVector", SIVector.class, si10dd.getVectorClass());

        SIMatrix si10ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(10, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(100, si10ds.cardinality());
        assertEquals(50 * 101, si10ds.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
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
        assertEquals(si10dd, si10dd.toSparse());
        assertEquals(si10ds, si10ds.toDense());

        assertEquals(si10sd, si10ss.toDense());
        assertEquals(si10ss, si10sd.toSparse());
        assertEquals(si10sd, si10sd.toDense());
        assertEquals(si10ss, si10ss.toSparse());
        assertEquals(si10sd, si10sd.toSparse().toDense());
        assertEquals(si10ss, si10ss.toDense().toSparse());
        assertEquals(si10sd, si10sd.toSparse());
        assertEquals(si10ss, si10ss.toDense());

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
        LengthMatrix lmdkm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(200, lmdkm10.cardinality());
        assertEquals(100 * 201 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
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

        LengthMatrix lmdsi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(200, lmdsi10.cardinality());
        assertEquals(100 * 201, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(200, lmssi10.cardinality());
        assertEquals(100 * 201, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(200, lmdsc10.cardinality());
        assertEquals(100 * 201, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Length.class),
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
        LengthMatrix lmdkm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), LengthUnit.KILOMETER, StorageType.SPARSE);
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

        LengthMatrix lmdsi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 =
                DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(20, 10), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Length.class),
                LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(20, 10, Length.class), 20, 10,
                LengthUnit.NANOMETER, StorageType.DENSE);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(20, 10, Length.class), 20, 10,
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
        AreaMatrix lmdkm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(200, lmdkm10.cardinality());
        assertEquals(100 * 201 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        AreaMatrix lmskm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(200, lmskm10.cardinality());
        assertEquals(100 * 201 * 1.0E6, lmskm10.zSum().getSI(), 0.1);
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

        AreaMatrix lmdsi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(200, lmdsi10.cardinality());
        assertEquals(100 * 201, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        AreaMatrix lmssi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.denseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(200, lmssi10.cardinality());
        assertEquals(100 * 201, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        AreaMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(200, lmdsc10.cardinality());
        assertEquals(100 * 201, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        AreaMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, AreaMatrix.class);
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
        AreaMatrix lmdkm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lmdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lmdkm10.getDisplayUnit());

        AreaMatrix lmskm10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_KILOMETER,
                StorageType.SPARSE, AreaMatrix.class);
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

        AreaMatrix lmdsi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(5 * 11, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmdsi10.getDisplayUnit());

        AreaMatrix lmssi10 = DoubleMatrix.instantiateSI(DOUBLEMATRIX.sparseRectArrays(20, 10), AreaUnit.SQUARE_CENTIMETER,
                StorageType.SPARSE, AreaMatrix.class);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(10, lmssi10.cardinality());
        assertEquals(5 * 11, lmssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lmssi10.getDisplayUnit());

        AreaMatrix lmdsc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(5 * 11, lmdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmdsc10.getDisplayUnit());

        AreaMatrix lmssc10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Area.class),
                AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE, AreaMatrix.class);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(10, lmssc10.cardinality());
        assertEquals(5 * 11, lmssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lmssc10.getDisplayUnit());

        AreaMatrix lmdtu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(20, 10, Area.class), 20, 10, AreaUnit.ACRE,
                StorageType.DENSE, AreaMatrix.class);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(5 * 11, lmdtu10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.ACRE, lmdtu10.getDisplayUnit());

        AreaMatrix lmstu10 = DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectTuples(20, 10, Area.class), 20, 10, AreaUnit.ACRE,
                StorageType.SPARSE, AreaMatrix.class);
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
        SIMatrix si10dd = DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(200, si10dd.cardinality());
        assertEquals(100 * 201, si10dd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(20, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(200, si10ds.cardinality());
        assertEquals(100 * 201, si10ds.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(20, 10), SIUnit.of("m2/s3"), StorageType.SPARSE);
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
        assertEquals(si10dd, si10dd.toSparse());
        assertEquals(si10ds, si10ds.toDense());

        assertEquals(si10sd, si10ss.toDense());
        assertEquals(si10ss, si10sd.toSparse());
        assertEquals(si10sd, si10sd.toDense());
        assertEquals(si10ss, si10ss.toSparse());
        assertEquals(si10sd, si10sd.toSparse().toDense());
        assertEquals(si10ss, si10ss.toDense().toSparse());
        assertEquals(si10sd, si10sd.toSparse());
        assertEquals(si10ss, si10ss.toDense());

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
        assertEquals(si10sd.hashCode(), si10sd.toSparse().toDense().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toDense().toSparse().hashCode());
        assertEquals(si10sd.hashCode(), si10sd.toSparse().hashCode());
        assertEquals(si10ss.hashCode(), si10ss.toDense().hashCode());
    }

    // =============================================== EDGE CASE MATRICES ===================================================

    /**
     * Test the instantiation of dense and sparse matrix types with one row or one column, and errors with null pointers.
     */
    @Test
    @SuppressWarnings({"checkstyle:localvariablename", "checkstyle:methodlength"})
    public void testInstantiateEdgeCases()
    {
        // DENSE DATA

        SpeedMatrix row1dd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1dd.rows());
        assertEquals(10, row1dd.cols());
        assertEquals(10, row1dd.cardinality());
        assertEquals(5 * 11, row1dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1dd.getDisplayUnit());

        SpeedMatrix col1dd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1dd.rows());
        assertEquals(1, col1dd.cols());
        assertEquals(10, col1dd.cardinality());
        assertEquals(5 * 11, col1dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1dd.getDisplayUnit());

        SpeedMatrix row1ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ds.rows());
        assertEquals(10, row1ds.cols());
        assertEquals(10, row1ds.cardinality());
        assertEquals(5 * 11, row1ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ds.getDisplayUnit());

        SpeedMatrix col1ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(10, col1ds.rows());
        assertEquals(1, col1ds.cols());
        assertEquals(10, col1ds.cardinality());
        assertEquals(5 * 11, col1ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1ds.getDisplayUnit());

        // SPARSE DATA

        SpeedMatrix row1sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1sd.rows());
        assertEquals(10, row1sd.cols());
        assertEquals(1, row1sd.cardinality());
        assertEquals(1, row1sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1sd.getDisplayUnit());

        SpeedMatrix col1sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1sd.rows());
        assertEquals(1, col1sd.cols());
        assertEquals(1, col1sd.cardinality());
        assertEquals(1, col1sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1sd.getDisplayUnit());

        SpeedMatrix row1ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 10), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ss.rows());
        assertEquals(10, row1ss.cols());
        assertEquals(1, row1ss.cardinality());
        assertEquals(1, row1ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ss.getDisplayUnit());

        SpeedMatrix col1ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(10, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
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

        // 1 x 1 DENSE DATA

        SpeedMatrix row11dd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11dd.rows());
        assertEquals(1, row11dd.cols());
        assertEquals(1, row11dd.cardinality());
        assertEquals(1, row11dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11dd.getDisplayUnit());

        SpeedMatrix col11dd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11dd.rows());
        assertEquals(1, col11dd.cols());
        assertEquals(1, col11dd.cardinality());
        assertEquals(1, col11dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11dd.getDisplayUnit());

        SpeedMatrix row11ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ds.rows());
        assertEquals(1, row11ds.cols());
        assertEquals(1, row11ds.cardinality());
        assertEquals(1, row11ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ds.getDisplayUnit());

        SpeedMatrix col11ds =
                DoubleMatrix.instantiate(DOUBLEMATRIX.denseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ds.rows());
        assertEquals(1, col11ds.cols());
        assertEquals(1, col11ds.cardinality());
        assertEquals(1, col11ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ds.getDisplayUnit());

        // 1 x 1 SPARSE DATA

        SpeedMatrix row11sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11sd.rows());
        assertEquals(1, row11sd.cols());
        assertEquals(1, row11sd.cardinality());
        assertEquals(1, row11sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11sd.getDisplayUnit());

        SpeedMatrix col11sd =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11sd.rows());
        assertEquals(1, col11sd.cols());
        assertEquals(1, col11sd.cardinality());
        assertEquals(1, col11sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11sd.getDisplayUnit());

        SpeedMatrix row11ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ss.rows());
        assertEquals(1, row11ss.cols());
        assertEquals(1, row11ss.cardinality());
        assertEquals(1, row11ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ss.getDisplayUnit());

        SpeedMatrix col11ss =
                DoubleMatrix.instantiate(DOUBLEMATRIX.sparseRectArrays(1, 1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ss.rows());
        assertEquals(1, col11ss.cols());
        assertEquals(1, col11ss.cardinality());
        assertEquals(1, col11ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ss.getDisplayUnit());

        // NULL

        double[][] d1_1 = new double[1][];
        d1_1[0] = new double[1];

        new Try()
        {
            @Override
            public void execute()
            {
                DoubleMatrix.instantiate((double[][]) null, SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
            }
        }.test("constructing matrix with null input should have thrown an exception", NullPointerException.class);

        new Try()
        {
            @Override
            public void execute()
            {
                DoubleMatrix.instantiate(d1_1, null, StorageType.DENSE);
            }
        }.test("constructing matrix with null unit should have thrown an exception", NullPointerException.class);

        new Try()
        {
            @Override
            public void execute()
            {
                DoubleMatrix.instantiate(d1_1, SpeedUnit.METER_PER_SECOND, null);
            }
        }.test("constructing matrix with null storage type should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test matrix construction and operations with zero length.
     */
    @Test
    public void testInstantiateZero()
    {
        double[][] doubleMatrix00 = new double[0][0];
        double[][] doubleMatrix10 = new double[1][];
        doubleMatrix10[0] = new double[] {};
        Speed[][] speedMatrix00 = new Speed[0][0];
        Speed[][] speedMatrix10 = new Speed[1][];
        speedMatrix10[0] = new Speed[] {};
        Collection<DoubleSparseValue<SpeedUnit, Speed>> speedList = new ArrayList<>();
        SortedMap<Integer, SpeedMatrix> smMap = new TreeMap<>();
        smMap.put(0,
                DoubleMatrix.instantiate(
                        DoubleMatrixData.instantiate(doubleMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(1,
                DoubleMatrix.instantiate(
                        DoubleMatrixData.instantiate(doubleMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(2, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedMatrix00, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(3, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedMatrix00, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(4, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedList, 0, 0, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(5, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedList, 0, 0, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(6,
                DoubleMatrix.instantiate(
                        DoubleMatrixData.instantiate(doubleMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(7,
                DoubleMatrix.instantiate(
                        DoubleMatrixData.instantiate(doubleMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(8, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedMatrix10, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        smMap.put(9, DoubleMatrix.instantiate(DoubleMatrixData.instantiate(speedMatrix10, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));

        for (Map.Entry<Integer, SpeedMatrix> entry : smMap.entrySet())
        {
            int key = entry.getKey();
            SpeedMatrix sv = entry.getValue();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, 0, sv.cardinality());
            assertEquals("key=" + key, 0, sv.getScalars().length);
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, sv.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, sv.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            assertFalse("key=" + key, sv.isMutable());
            SpeedMatrix svm = sv.mutable();
            assertTrue("key=" + key, svm.isMutable());
            assertEquals(SpeedUnit.KM_PER_HOUR, svm.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, svm.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            SpeedMatrix svr = svm.abs();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svm = svm.ceil();
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.decrementBy(Speed.ONE);
            assertEquals("key=" + key, 0, sv.rows());
            assertEquals("key=" + key, 0, sv.cols());
            assertEquals("key=" + key, SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.incrementBy(Speed.ONE);
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
