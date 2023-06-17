package org.djunits.value.vdouble.vector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.util.UnitException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * DoubleVectorInstantiateTest.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleVectorInstantiateTest
{
    /**
     * Test the instantiation of dense and sparse vector types with dense data (no zeros).
     */
    @Test
    public void testInstantiateDenseData()
    {
        LengthVector lvdkm10 = new LengthVector(DOUBLEVECTOR.denseArray(100), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(100, lvdkm10.size());
        assertEquals(100, lvdkm10.cardinality());
        assertEquals(50 * 101 * 1000.0, lvdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lvdkm10.getDisplayUnit());

        LengthVector lvskm10 = new LengthVector(DOUBLEVECTOR.denseArray(100), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(100, lvskm10.size());
        assertEquals(100, lvskm10.cardinality());
        assertEquals(50 * 101 * 1000.0, lvskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lvskm10.getDisplayUnit());

        assertEquals(lvdkm10, lvdkm10.toSparse().toDense());
        assertEquals(lvskm10, lvskm10.toDense().toSparse());
        assertEquals(lvdkm10, lvskm10.toDense());
        assertEquals(lvskm10, lvdkm10.toSparse());
        assertEquals(lvdkm10, lvdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lvskm10, lvskm10.toDense()); // dense and sparse are the same if content is the same
        assertEquals(lvdkm10, lvdkm10.toDense());
        assertEquals(lvskm10, lvskm10.toSparse());
        assertTrue(lvdkm10.isDense());
        assertTrue(lvskm10.isSparse());

        LengthVector lvdsi10 = new LengthVector(DOUBLEVECTOR.denseArray(100), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(100, lvdsi10.size());
        assertEquals(100, lvdsi10.cardinality());
        assertEquals(50 * 1.01, lvdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lvdsi10.getDisplayUnit());

        LengthVector lvssi10 = new LengthVector(DOUBLEVECTOR.denseArray(100), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(100, lvssi10.size());
        assertEquals(100, lvssi10.cardinality());
        assertEquals(50 * 1.01, lvssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lvssi10.getDisplayUnit());

        LengthVector lvdsc10 =
                new LengthVector(DOUBLEVECTOR.denseScalarArray(100, Length.class), LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(100, lvdsc10.size());
        assertEquals(100, lvdsc10.cardinality());
        assertEquals(50 * 101, lvdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lvdsc10.getDisplayUnit());

        LengthVector lvssc10 =
                new LengthVector(DOUBLEVECTOR.denseScalarArray(100, Length.class), LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(100, lvssc10.size());
        assertEquals(100, lvssc10.cardinality());
        assertEquals(50 * 101, lvssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lvssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse vector types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatSparseData()
    {
        LengthVector lvdkm10 = new LengthVector(DOUBLEVECTOR.sparseArray(100), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(100, lvdkm10.size());
        assertEquals(10, lvdkm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lvdkm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lvdkm10.getDisplayUnit());

        LengthVector lvskm10 = new LengthVector(DOUBLEVECTOR.sparseArray(100), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(100, lvskm10.size());
        assertEquals(10, lvskm10.cardinality());
        assertEquals(5 * 11 * 1000.0, lvskm10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lvskm10.getDisplayUnit());

        assertEquals(lvdkm10, lvdkm10.toSparse().toDense());
        assertEquals(lvskm10, lvskm10.toDense().toSparse());
        assertEquals(lvdkm10, lvskm10.toDense());
        assertEquals(lvskm10, lvdkm10.toSparse());
        assertEquals(lvdkm10, lvdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lvskm10, lvskm10.toDense()); // dense and sparse are the same if content is the same

        LengthVector lvdsi10 = new LengthVector(DOUBLEVECTOR.sparseArray(100), LengthUnit.CENTIMETER, StorageType.DENSE);
        assertEquals(100, lvdsi10.size());
        assertEquals(10, lvdsi10.cardinality());
        assertEquals(5 * 11 * 0.01, lvdsi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lvdsi10.getDisplayUnit());

        LengthVector lvssi10 = new LengthVector(DOUBLEVECTOR.sparseArray(100), LengthUnit.CENTIMETER, StorageType.SPARSE);
        assertEquals(100, lvssi10.size());
        assertEquals(10, lvssi10.cardinality());
        assertEquals(5 * 11 * 0.01, lvssi10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lvssi10.getDisplayUnit());

        LengthVector lvdsc10 =
                new LengthVector(DOUBLEVECTOR.sparseScalarArray(100, Length.class), LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(100, lvdsc10.size());
        assertEquals(10, lvdsc10.cardinality());
        assertEquals(5 * 11, lvdsc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lvdsc10.getDisplayUnit());

        LengthVector lvssc10 =
                new LengthVector(DOUBLEVECTOR.sparseScalarArray(100, Length.class), LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(100, lvssc10.size());
        assertEquals(10, lvssc10.cardinality());
        assertEquals(5 * 11, lvssc10.zSum().getSI(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lvssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse vector types with dense data (no zeros).
     */
    @Test
    public void testInstantiateDenseDataWithClass()
    {
        AreaVector lvdkm10 = new AreaVector(DOUBLEVECTOR.denseArray(100), AreaUnit.SQUARE_KILOMETER, StorageType.DENSE);
        assertEquals(100, lvdkm10.size());
        assertEquals(100, lvdkm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lvdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lvdkm10.getDisplayUnit());

        AreaVector lvskm10 = new AreaVector(DOUBLEVECTOR.denseArray(100), AreaUnit.SQUARE_KILOMETER, StorageType.SPARSE);
        assertEquals(100, lvskm10.size());
        assertEquals(100, lvskm10.cardinality());
        assertEquals(50 * 101 * 1.0E6, lvskm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lvskm10.getDisplayUnit());

        assertEquals(lvdkm10, lvdkm10.toSparse().toDense());
        assertEquals(lvskm10, lvskm10.toDense().toSparse());
        assertEquals(lvdkm10, lvskm10.toDense());
        assertEquals(lvskm10, lvdkm10.toSparse());
        assertEquals(lvdkm10, lvdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lvskm10, lvskm10.toDense()); // dense and sparse are the same if content is the same

        AreaVector lvdsi10 = new AreaVector(DOUBLEVECTOR.denseArray(100), AreaUnit.SQUARE_CENTIMETER, StorageType.DENSE);
        assertEquals(100, lvdsi10.size());
        assertEquals(100, lvdsi10.cardinality());
        assertEquals(50 * 101 * 0.01 * 0.01, lvdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lvdsi10.getDisplayUnit());

        AreaVector lvssi10 = new AreaVector(DOUBLEVECTOR.denseArray(100), AreaUnit.SQUARE_CENTIMETER, StorageType.SPARSE);
        assertEquals(100, lvssi10.size());
        assertEquals(100, lvssi10.cardinality());
        assertEquals(50 * 101 * 0.01 * 0.01, lvssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lvssi10.getDisplayUnit());

        AreaVector lvdsc10 =
                new AreaVector(DOUBLEVECTOR.denseScalarArray(100, Area.class), AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE);
        assertEquals(100, lvdsc10.size());
        assertEquals(100, lvdsc10.cardinality());
        assertEquals(50 * 101, lvdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lvdsc10.getDisplayUnit());

        AreaVector lvssc10 =
                new AreaVector(DOUBLEVECTOR.denseScalarArray(100, Area.class), AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE);
        assertEquals(100, lvssc10.size());
        assertEquals(100, lvssc10.cardinality());
        assertEquals(50 * 101, lvssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lvssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse vector types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatSparseDataWithClass()
    {
        AreaVector lvdkm10 = new AreaVector(DOUBLEVECTOR.sparseArray(100), AreaUnit.SQUARE_KILOMETER, StorageType.DENSE);
        assertEquals(100, lvdkm10.size());
        assertEquals(10, lvdkm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lvdkm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lvdkm10.getDisplayUnit());

        AreaVector lvskm10 = new AreaVector(DOUBLEVECTOR.sparseArray(100), AreaUnit.SQUARE_KILOMETER, StorageType.SPARSE);
        assertEquals(100, lvskm10.size());
        assertEquals(10, lvskm10.cardinality());
        assertEquals(5 * 11 * 1.0E6, lvskm10.zSum().getSI(), 0.1);
        assertEquals(AreaUnit.SQUARE_KILOMETER, lvskm10.getDisplayUnit());

        assertEquals(lvdkm10, lvdkm10.toSparse().toDense());
        assertEquals(lvskm10, lvskm10.toDense().toSparse());
        assertEquals(lvdkm10, lvskm10.toDense());
        assertEquals(lvskm10, lvdkm10.toSparse());
        assertEquals(lvdkm10, lvdkm10.toSparse()); // dense and sparse are the same if content is the same
        assertEquals(lvskm10, lvskm10.toDense()); // dense and sparse are the same if content is the same

        AreaVector lvdsi10 = new AreaVector(DOUBLEVECTOR.sparseArray(100), AreaUnit.SQUARE_CENTIMETER, StorageType.DENSE);
        assertEquals(100, lvdsi10.size());
        assertEquals(10, lvdsi10.cardinality());
        assertEquals(5 * 11 * 0.01 * 0.01, lvdsi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lvdsi10.getDisplayUnit());

        AreaVector lvssi10 = new AreaVector(DOUBLEVECTOR.sparseArray(100), AreaUnit.SQUARE_CENTIMETER, StorageType.SPARSE);
        assertEquals(100, lvssi10.size());
        assertEquals(10, lvssi10.cardinality());
        assertEquals(5 * 11 * 0.01 * 0.01, lvssi10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_CENTIMETER, lvssi10.getDisplayUnit());

        AreaVector lvdsc10 =
                new AreaVector(DOUBLEVECTOR.sparseScalarArray(100, Area.class), AreaUnit.SQUARE_HECTOMETER, StorageType.DENSE);
        assertEquals(100, lvdsc10.size());
        assertEquals(10, lvdsc10.cardinality());
        assertEquals(5 * 11, lvdsc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lvdsc10.getDisplayUnit());

        AreaVector lvssc10 =
                new AreaVector(DOUBLEVECTOR.sparseScalarArray(100, Area.class), AreaUnit.SQUARE_HECTOMETER, StorageType.SPARSE);
        assertEquals(100, lvssc10.size());
        assertEquals(10, lvssc10.cardinality());
        assertEquals(5 * 11, lvssc10.zSum().getSI(), 0.001);
        assertEquals(AreaUnit.SQUARE_HECTOMETER, lvssc10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse vector types with dense data (no zeros).
     * @throws UnitException on error
     */
    @Test
    public void testInstantiateSIUnit() throws UnitException
    {
        SIVector si10dd = new SIVector(DOUBLEVECTOR.denseArray(100), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(100, si10dd.size());
        assertEquals(100, si10dd.cardinality());
        assertEquals(50 * 101, si10dd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));
        assertEquals("getScalarClass returns SIScalar", SIScalar.class, si10dd.getScalarClass());

        SIVector si10ds = new SIVector(DOUBLEVECTOR.denseArray(100), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(100, si10ds.size());
        assertEquals(100, si10ds.cardinality());
        assertEquals(50 * 101, si10ds.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIVector si10sd = new SIVector(DOUBLEVECTOR.sparseArray(100), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(100, si10sd.size());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getSI(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIVector si10ss = new SIVector(DOUBLEVECTOR.sparseArray(100), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(100, si10ss.size());
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
    }

    // =============================================== EDGE CASE MATRICES ===================================================

    /**
     * Test the instantiation of dense and sparse vector types with one vector or one column, and errors with null pointers.
     */
    @Test
    public void testInstantiateEdgeCases()
    {
        // DENSE DATA

        SpeedVector vect1dd = new SpeedVector(DOUBLEVECTOR.denseArray(1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, vect1dd.size());
        assertEquals(1, vect1dd.cardinality());
        assertEquals(1, vect1dd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, vect1dd.getDisplayUnit());

        SpeedVector vect1ds = new SpeedVector(DOUBLEVECTOR.denseArray(1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, vect1ds.size());
        assertEquals(1, vect1ds.cardinality());
        assertEquals(1, vect1ds.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, vect1ds.getDisplayUnit());

        // SPARSE DATA

        SpeedVector vect1sd = new SpeedVector(DOUBLEVECTOR.sparseArray(1), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, vect1sd.size());
        assertEquals(1, vect1sd.cardinality());
        assertEquals(1, vect1sd.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, vect1sd.getDisplayUnit());

        SpeedVector vect1ss = new SpeedVector(DOUBLEVECTOR.sparseArray(1), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, vect1ss.size());
        assertEquals(1, vect1ss.cardinality());
        assertEquals(1, vect1ss.zSum().getSI(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, vect1ss.getDisplayUnit());

        // NULL

        double[] d1 = new double[1];

        Try.testFail(() -> new SpeedVector((double[]) null, SpeedUnit.METER_PER_SECOND, StorageType.DENSE),
                "constructing vector with null input should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SpeedVector(d1, null, StorageType.DENSE),
                "constructing vector with null unit should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SpeedVector(d1, SpeedUnit.METER_PER_SECOND, null),
                "constructing vector with null storage type should have thrown an exception", NullPointerException.class);
    }

    /**
     * Test vector construction and operations with zero length.
     */
    @Test
    public void testInstantiateZero()
    {
        double[] doubleArray = new double[] {};
        Speed[] speedArray = new Speed[] {};
        List<Double> doubleList = new ArrayList<>();
        List<Speed> speedList = new ArrayList<>();
        SortedMap<Integer, Double> doubleMap = new TreeMap<>();
        SortedMap<Integer, Speed> speedMap = new TreeMap<>();
        SortedMap<Integer, SpeedVector> svMap = new TreeMap<>();
        svMap.put(0,
                new SpeedVector(DoubleVectorData.instantiate(doubleArray, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(1,
                new SpeedVector(DoubleVectorData.instantiate(doubleArray, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(2, new SpeedVector(DoubleVectorData.instantiate(speedArray, StorageType.DENSE), SpeedUnit.KM_PER_HOUR));
        svMap.put(3, new SpeedVector(DoubleVectorData.instantiate(speedArray, StorageType.SPARSE), SpeedUnit.KM_PER_HOUR));
        svMap.put(4,
                new SpeedVector(DoubleVectorData.instantiate(doubleList, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(5,
                new SpeedVector(DoubleVectorData.instantiate(doubleList, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(6,
                new SpeedVector(DoubleVectorData.instantiate(doubleMap, 0, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(7,
                new SpeedVector(
                        DoubleVectorData.instantiate(doubleMap, 0, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        svMap.put(8, new SpeedVector(DoubleVectorData.instantiate(speedList, IdentityScale.SCALE, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        svMap.put(9, new SpeedVector(DoubleVectorData.instantiate(speedList, IdentityScale.SCALE, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        svMap.put(10, new SpeedVector(DoubleVectorData.instantiate(speedMap, 0, IdentityScale.SCALE, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        svMap.put(11, new SpeedVector(DoubleVectorData.instantiate(speedMap, 0, IdentityScale.SCALE, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));
        svMap.put(12, new SpeedVector(DoubleVectorData.instantiate(speedList, IdentityScale.SCALE, StorageType.DENSE),
                SpeedUnit.KM_PER_HOUR));
        svMap.put(13, new SpeedVector(DoubleVectorData.instantiate(speedList, IdentityScale.SCALE, StorageType.SPARSE),
                SpeedUnit.KM_PER_HOUR));

        for (Map.Entry<Integer, SpeedVector> entry : svMap.entrySet())
        {
            int key = entry.getKey();
            SpeedVector sv = entry.getValue();
            assertEquals(0, sv.size());
            assertEquals(0, sv.cardinality());
            assertEquals(0, sv.getScalars().length);
            assertEquals(SpeedUnit.KM_PER_HOUR, sv.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, sv.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            assertFalse(sv.isMutable());
            SpeedVector svm = sv.mutable();
            assertTrue(svm.isMutable());
            assertEquals(SpeedUnit.KM_PER_HOUR, svm.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, svm.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            SpeedVector svr = svm.abs();
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svm = svm.ceil();
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.decrementBy(Speed.ONE);
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.incrementBy(Speed.ONE);
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.plus(sv);
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            assertEquals("DENSE/SPARSE: key = " + key, svr.getStorageType(),
                    key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE);
            svr = sv.toDense();
            assertEquals(StorageType.DENSE, svr.getStorageType());
            svr = sv.toSparse();
            assertEquals(StorageType.SPARSE, svr.getStorageType());
            assertArrayEquals(new double[] {}, sv.getValuesSI(), 0.0001);
            assertArrayEquals(new double[] {}, sv.getValuesInUnit(), 0.0001);
            assertArrayEquals(new double[] {}, sv.getValuesInUnit(SpeedUnit.KNOT), 0.0001);
            svr = sv.times(2.0);
            assertEquals(0, svr.size());
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
        }
    }
}
