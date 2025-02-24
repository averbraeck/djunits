package org.djunits.value.vdouble.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.util.UnitException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.SIVector;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * DoubleMatrixInstantiateTest.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleMatrixInstantiateTest
{

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateSquareDenseData()
    {
        LengthMatrix lmdkm10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmdkm10.get(0, 0));
        assertEquals(100, lmdkm10.cardinality());
        assertEquals(1000 * 100 * 101 / 2, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(100 * 101 / 2, lmdkm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmskm10.get(0, 0));
        assertEquals(100, lmskm10.cardinality());
        assertEquals(1000 * 100 * 101 / 2, lmskm10.zSum().getSI(), 0.001);
        assertEquals(100 * 101 / 2, lmskm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.zSum().getDisplayUnit());
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

        LengthMatrix lmdsi10 = new LengthMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), StorageType.DENSE);
        lmdsi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmdsi10.get(0, 0));
        assertEquals(100, lmdsi10.cardinality());
        assertEquals(100 * 101 / 2, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(100 * 100 * 101 / 2, lmdsi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 = new LengthMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), StorageType.SPARSE);
        lmssi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmssi10.get(0, 0));
        assertEquals(100, lmssi10.cardinality());
        assertEquals(100 * 101 / 2, lmssi10.zSum().getSI(), 0.001);
        assertEquals(100 * 100 * 101 / 2, lmssi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmdsc10.get(0, 0));
        assertEquals(100, lmdsc10.cardinality());
        assertEquals(100 * 101 / 2, lmdsc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectScalarArrays(10, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmssc10.get(0, 0));
        assertEquals(100, lmssc10.cardinality());
        assertEquals(100 * 101 / 2, lmssc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 = new LengthMatrix(DOUBLEMATRIX.denseRectTuples(10, 10, Length.class, LengthUnit.NANOMETER, false),
                LengthUnit.NANOMETER, 10, 10, StorageType.DENSE);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmdtu10.get(0, 0));
        assertEquals(new Length(2, LengthUnit.NANOMETER), lmdtu10.get(0, 1));
        assertEquals(new Length(3, LengthUnit.NANOMETER), lmdtu10.get(0, 2));
        assertEquals(100, lmdtu10.cardinality());
        assertEquals(100 * 101 / 2, lmdtu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 = new LengthMatrix(DOUBLEMATRIX.denseRectTuples(10, 10, Length.class, LengthUnit.NANOMETER, false),
                LengthUnit.NANOMETER, 10, 10, StorageType.SPARSE);
        assertEquals(10, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmstu10.get(0, 0));
        assertEquals(new Length(2, LengthUnit.NANOMETER), lmstu10.get(0, 1));
        assertEquals(new Length(3, LengthUnit.NANOMETER), lmstu10.get(0, 2));
        assertEquals(100, lmstu10.cardinality());
        assertEquals(100 * 101 / 2, lmstu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatSquareSparseData()
    {
        LengthMatrix lmdkm10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(10, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmdkm10.get(0, 0));
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(1000 * 10 * 11 / 2, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(10 * 11 / 2, lmdkm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(10, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmskm10.get(0, 0));
        assertEquals(10, lmskm10.cardinality());
        assertEquals(1000 * 10 * 11 / 2, lmskm10.zSum().getSI(), 0.001);
        assertEquals(10 * 11 / 2, lmskm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.zSum().getDisplayUnit());
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

        LengthMatrix lmdsi10 = new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), StorageType.DENSE);
        lmdsi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(10, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmdsi10.get(0, 0));
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(10 * 11 / 2, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(100 * 10 * 11 / 2, lmdsi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 = new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), StorageType.SPARSE);
        lmssi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(10, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmssi10.get(0, 0));
        assertEquals(10, lmssi10.cardinality());
        assertEquals(10 * 11 / 2, lmssi10.zSum().getSI(), 0.001);
        assertEquals(100 * 10 * 11 / 2, lmssi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(10, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmdsc10.get(0, 0));
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(10 * 11 / 2, lmdsc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectScalarArrays(10, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(10, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmssc10.get(0, 0));
        assertEquals(10, lmssc10.cardinality());
        assertEquals(10 * 11 / 2, lmssc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectTuples(10, 10, Length.class, LengthUnit.NANOMETER, false),
                        LengthUnit.NANOMETER, 10, 10, StorageType.DENSE);
        assertEquals(10, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmdtu10.get(0, 0));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmdtu10.get(0, 1));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmdtu10.get(1, 0));
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(10 * 11 / 2, lmdtu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectTuples(10, 10, Length.class, LengthUnit.NANOMETER, false),
                        LengthUnit.NANOMETER, 10, 10, StorageType.SPARSE);
        assertEquals(10, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmstu10.get(0, 0));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmstu10.get(0, 1));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmstu10.get(1, 0));
        assertEquals(10, lmstu10.cardinality());
        assertEquals(10 * 11 / 2, lmstu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     * @throws UnitException on error
     */
    @Test
    public void testInstantiateSquareSIUnit() throws UnitException
    {
        SIMatrix si10dd = new SIMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(100, si10dd.cardinality());
        assertEquals(100 * 101 / 2, si10dd.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));
        assertEquals(SIScalar.class, si10dd.getScalarClass(), "getScalarClass returns SIScalar");
        assertEquals(SIVector.class, si10dd.getVectorClass(), "getVectorClass returns SIVector");

        SIMatrix si10ds = new SIMatrix(DOUBLEMATRIX.denseRectArrays(10, 10, false), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(10, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(100, si10ds.cardinality());
        assertEquals(100 * 101 / 2, si10ds.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10sd = new SIMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(10, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10ss = new SIMatrix(DOUBLEMATRIX.sparseRectArrays(10, 10, false), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(10, si10ss.rows());
        assertEquals(10, si10ss.cols());
        assertEquals(10, si10ss.cardinality());
        assertEquals(5 * 11, si10ss.zSum().getInUnit(), 0.001);
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

    /* =========================================== RECTANGULAR MATRICES =============================================== */

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     */
    @Test
    public void testInstantiateRectDenseData()
    {
        LengthMatrix lmdkm10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmdkm10.get(0, 0));
        assertEquals(200, lmdkm10.cardinality());
        assertEquals(1000 * 200 * 201 / 2, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(200 * 201 / 2, lmdkm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmskm10.get(0, 0));
        assertEquals(200, lmskm10.cardinality());
        assertEquals(1000 * 200 * 201 / 2, lmskm10.zSum().getSI(), 0.001);
        assertEquals(200 * 201 / 2, lmskm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.zSum().getDisplayUnit());
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

        LengthMatrix lmdsi10 = new LengthMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), StorageType.DENSE);
        lmdsi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmdsi10.get(0, 0));
        assertEquals(200, lmdsi10.cardinality());
        assertEquals(200 * 201 / 2, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(100 * 200 * 201 / 2, lmdsi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 = new LengthMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), StorageType.SPARSE);
        lmssi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmssi10.get(0, 0));
        assertEquals(200, lmssi10.cardinality());
        assertEquals(200 * 201 / 2, lmssi10.zSum().getSI(), 0.001);
        assertEquals(100 * 200 * 201 / 2, lmssi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmdsc10.get(0, 0));
        assertEquals(200, lmdsc10.cardinality());
        assertEquals(200 * 201 / 2, lmdsc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 =
                new LengthMatrix(DOUBLEMATRIX.denseRectScalarArrays(20, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmssc10.get(0, 0));
        assertEquals(200, lmssc10.cardinality());
        assertEquals(200 * 201 / 2, lmssc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 = new LengthMatrix(DOUBLEMATRIX.denseRectTuples(20, 10, Length.class, LengthUnit.NANOMETER, false),
                LengthUnit.NANOMETER, 20, 10, StorageType.DENSE);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmdtu10.get(0, 0));
        assertEquals(new Length(2, LengthUnit.NANOMETER), lmdtu10.get(0, 1));
        assertEquals(new Length(3, LengthUnit.NANOMETER), lmdtu10.get(0, 2));
        assertEquals(200, lmdtu10.cardinality());
        assertEquals(200 * 201 / 2, lmdtu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 = new LengthMatrix(DOUBLEMATRIX.denseRectTuples(20, 10, Length.class, LengthUnit.NANOMETER, false),
                LengthUnit.NANOMETER, 20, 10, StorageType.SPARSE);
        assertEquals(20, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmstu10.get(0, 0));
        assertEquals(new Length(2, LengthUnit.NANOMETER), lmstu10.get(0, 1));
        assertEquals(new Length(3, LengthUnit.NANOMETER), lmstu10.get(0, 2));
        assertEquals(200, lmstu10.cardinality());
        assertEquals(200 * 201 / 2, lmstu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with sparse data (90% zeros).
     */
    @Test
    public void testInstantiatRectSparseData()
    {
        LengthMatrix lmdkm10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), LengthUnit.KILOMETER, StorageType.DENSE);
        assertEquals(20, lmdkm10.rows());
        assertEquals(10, lmdkm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmdkm10.get(0, 0));
        assertEquals(10, lmdkm10.cardinality());
        assertEquals(1000 * 10 * 11 / 2, lmdkm10.zSum().getSI(), 0.001);
        assertEquals(10 * 11 / 2, lmdkm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmdkm10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.KILOMETER, lmdkm10.getDisplayUnit());

        LengthMatrix lmskm10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), LengthUnit.KILOMETER, StorageType.SPARSE);
        assertEquals(20, lmskm10.rows());
        assertEquals(10, lmskm10.cols());
        assertEquals(new Length(1, LengthUnit.KILOMETER), lmskm10.get(0, 0));
        assertEquals(10, lmskm10.cardinality());
        assertEquals(1000 * 10 * 11 / 2, lmskm10.zSum().getSI(), 0.001);
        assertEquals(10 * 11 / 2, lmskm10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.KILOMETER, lmskm10.zSum().getDisplayUnit());
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

        LengthMatrix lmdsi10 = new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), StorageType.DENSE);
        lmdsi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(20, lmdsi10.rows());
        assertEquals(10, lmdsi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmdsi10.get(0, 0));
        assertEquals(10, lmdsi10.cardinality());
        assertEquals(10 * 11 / 2, lmdsi10.zSum().getSI(), 0.001);
        assertEquals(100 * 10 * 11 / 2, lmdsi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmdsi10.getDisplayUnit());

        LengthMatrix lmssi10 = new LengthMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), StorageType.SPARSE);
        lmssi10.setDisplayUnit(LengthUnit.CENTIMETER);
        assertEquals(20, lmssi10.rows());
        assertEquals(10, lmssi10.cols());
        assertEquals(new Length(100, LengthUnit.CENTIMETER), lmssi10.get(0, 0));
        assertEquals(10, lmssi10.cardinality());
        assertEquals(10 * 11 / 2, lmssi10.zSum().getSI(), 0.001);
        assertEquals(100 * 10 * 11 / 2, lmssi10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.CENTIMETER, lmssi10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.CENTIMETER, lmssi10.getDisplayUnit());

        LengthMatrix lmdsc10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.DENSE);
        assertEquals(20, lmdsc10.rows());
        assertEquals(10, lmdsc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmdsc10.get(0, 0));
        assertEquals(10, lmdsc10.cardinality());
        assertEquals(10 * 11 / 2, lmdsc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmdsc10.getDisplayUnit());

        LengthMatrix lmssc10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectScalarArrays(20, 10, Length.class, LengthUnit.HECTOMETER, false),
                        LengthUnit.HECTOMETER, StorageType.SPARSE);
        assertEquals(20, lmssc10.rows());
        assertEquals(10, lmssc10.cols());
        assertEquals(new Length(1, LengthUnit.HECTOMETER), lmssc10.get(0, 0));
        assertEquals(10, lmssc10.cardinality());
        assertEquals(10 * 11 / 2, lmssc10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.HECTOMETER, lmssc10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.HECTOMETER, lmssc10.getDisplayUnit());

        LengthMatrix lmdtu10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectTuples(20, 10, Length.class, LengthUnit.NANOMETER, false),
                        LengthUnit.NANOMETER, 20, 10, StorageType.DENSE);
        assertEquals(20, lmdtu10.rows());
        assertEquals(10, lmdtu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmdtu10.get(0, 0));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmdtu10.get(0, 1));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmdtu10.get(1, 0));
        assertEquals(10, lmdtu10.cardinality());
        assertEquals(10 * 11 / 2, lmdtu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmdtu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmdtu10.getDisplayUnit());

        LengthMatrix lmstu10 =
                new LengthMatrix(DOUBLEMATRIX.sparseRectTuples(20, 10, Length.class, LengthUnit.NANOMETER, false),
                        LengthUnit.NANOMETER, 20, 10, StorageType.SPARSE);
        assertEquals(20, lmstu10.rows());
        assertEquals(10, lmstu10.cols());
        assertEquals(new Length(1, LengthUnit.NANOMETER), lmstu10.get(0, 0));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmstu10.get(0, 1));
        assertEquals(new Length(0, LengthUnit.NANOMETER), lmstu10.get(1, 0));
        assertEquals(10, lmstu10.cardinality());
        assertEquals(10 * 11 / 2, lmstu10.zSum().getInUnit(), 0.001);
        assertEquals(LengthUnit.NANOMETER, lmstu10.zSum().getDisplayUnit());
        assertEquals(LengthUnit.NANOMETER, lmstu10.getDisplayUnit());
    }

    /**
     * Test the instantiation of dense and sparse matrix types with dense data (no zeros).
     * @throws UnitException on error
     */
    @Test
    public void testInstantiateRectSIUnit() throws UnitException
    {
        SIMatrix si10dd = new SIMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10dd.rows());
        assertEquals(10, si10dd.cols());
        assertEquals(200, si10dd.cardinality());
        assertEquals(200 * 201 / 2, si10dd.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10dd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));
        assertEquals(SIScalar.class, si10dd.getScalarClass(), "getScalarClass returns SIScalar");
        assertEquals(SIVector.class, si10dd.getVectorClass(), "getVectorClass returns SIVector");

        SIMatrix si10ds = new SIMatrix(DOUBLEMATRIX.denseRectArrays(20, 10, false), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(20, si10ds.rows());
        assertEquals(10, si10ds.cols());
        assertEquals(200, si10ds.cardinality());
        assertEquals(200 * 201 / 2, si10ds.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10ds.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10sd = new SIMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), SIUnit.of("m2/s3"), StorageType.DENSE);
        assertEquals(20, si10sd.rows());
        assertEquals(10, si10sd.cols());
        assertEquals(10, si10sd.cardinality());
        assertEquals(5 * 11, si10sd.zSum().getInUnit(), 0.001);
        assertEquals("m2/s3", si10sd.getDisplayUnit().getQuantity().getSiDimensions().toString(true, false, false));

        SIMatrix si10ss = new SIMatrix(DOUBLEMATRIX.sparseRectArrays(20, 10, false), SIUnit.of("m2/s3"), StorageType.SPARSE);
        assertEquals(20, si10ss.rows());
        assertEquals(10, si10ss.cols());
        assertEquals(10, si10ss.cardinality());
        assertEquals(5 * 11, si10ss.zSum().getInUnit(), 0.001);
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

    /* =========================================== EDGE CASE MATRICES ================================================= */

    /**
     * Test the instantiation of dense and sparse matrix types with one row or one column, and errors with null pointers.
     */
    @Test
    @SuppressWarnings({"checkstyle:localvariablename", "checkstyle:methodlength"})
    public void testInstantiateEdgeCases()
    {
        // DENSE DATA

        SpeedMatrix row1dd =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 10, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1dd.rows());
        assertEquals(10, row1dd.cols());
        assertEquals(10, row1dd.cardinality());
        assertEquals(5 * 11, row1dd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1dd.getDisplayUnit());

        SpeedMatrix col1dd =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(10, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1dd.rows());
        assertEquals(1, col1dd.cols());
        assertEquals(10, col1dd.cardinality());
        assertEquals(5 * 11, col1dd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1dd.getDisplayUnit());

        SpeedMatrix row1ds =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 10, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ds.rows());
        assertEquals(10, row1ds.cols());
        assertEquals(10, row1ds.cardinality());
        assertEquals(5 * 11, row1ds.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ds.getDisplayUnit());

        SpeedMatrix col1ds =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(10, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(10, col1ds.rows());
        assertEquals(1, col1ds.cols());
        assertEquals(10, col1ds.cardinality());
        assertEquals(5 * 11, col1ds.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1ds.getDisplayUnit());

        // SPARSE DATA

        SpeedMatrix row1sd =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 10, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row1sd.rows());
        assertEquals(10, row1sd.cols());
        assertEquals(1, row1sd.cardinality());
        assertEquals(1, row1sd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1sd.getDisplayUnit());

        SpeedMatrix col1sd =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(10, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(10, col1sd.rows());
        assertEquals(1, col1sd.cols());
        assertEquals(1, col1sd.cardinality());
        assertEquals(1, col1sd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col1sd.getDisplayUnit());

        SpeedMatrix row1ss =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 10, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row1ss.rows());
        assertEquals(10, row1ss.cols());
        assertEquals(1, row1ss.cardinality());
        assertEquals(1, row1ss.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row1ss.getDisplayUnit());

        SpeedMatrix col1ss =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(10, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(10, col1ss.rows());
        assertEquals(1, col1ss.cols());
        assertEquals(1, col1ss.cardinality());
        assertEquals(1, col1ss.zSum().getInUnit(), 0.001);
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
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11dd.rows());
        assertEquals(1, row11dd.cols());
        assertEquals(1, row11dd.cardinality());
        assertEquals(1, row11dd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11dd.getDisplayUnit());

        SpeedMatrix col11dd =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11dd.rows());
        assertEquals(1, col11dd.cols());
        assertEquals(1, col11dd.cardinality());
        assertEquals(1, col11dd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11dd.getDisplayUnit());

        SpeedMatrix row11ds =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ds.rows());
        assertEquals(1, row11ds.cols());
        assertEquals(1, row11ds.cardinality());
        assertEquals(1, row11ds.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ds.getDisplayUnit());

        SpeedMatrix col11ds =
                new SpeedMatrix(DOUBLEMATRIX.denseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ds.rows());
        assertEquals(1, col11ds.cols());
        assertEquals(1, col11ds.cardinality());
        assertEquals(1, col11ds.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ds.getDisplayUnit());

        // 1 x 1 SPARSE DATA

        SpeedMatrix row11sd =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, row11sd.rows());
        assertEquals(1, row11sd.cols());
        assertEquals(1, row11sd.cardinality());
        assertEquals(1, row11sd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11sd.getDisplayUnit());

        SpeedMatrix col11sd =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.DENSE);
        assertEquals(1, col11sd.rows());
        assertEquals(1, col11sd.cols());
        assertEquals(1, col11sd.cardinality());
        assertEquals(1, col11sd.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11sd.getDisplayUnit());

        SpeedMatrix row11ss =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, row11ss.rows());
        assertEquals(1, row11ss.cols());
        assertEquals(1, row11ss.cardinality());
        assertEquals(1, row11ss.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, row11ss.getDisplayUnit());

        SpeedMatrix col11ss =
                new SpeedMatrix(DOUBLEMATRIX.sparseRectArrays(1, 1, false), SpeedUnit.METER_PER_SECOND, StorageType.SPARSE);
        assertEquals(1, col11ss.rows());
        assertEquals(1, col11ss.cols());
        assertEquals(1, col11ss.cardinality());
        assertEquals(1, col11ss.zSum().getInUnit(), 0.001);
        assertEquals(SpeedUnit.METER_PER_SECOND, col11ss.getDisplayUnit());

        // NULL

        double[][] d1_1 = new double[1][];
        d1_1[0] = new double[1];

        Try.testFail(() -> new SpeedMatrix((double[][]) null, SpeedUnit.METER_PER_SECOND, StorageType.DENSE),
                "constructing matrix with null input should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SpeedMatrix(d1_1, null, StorageType.DENSE),
                "constructing matrix with null unit should have thrown an exception", NullPointerException.class);
        Try.testFail(() -> new SpeedMatrix(d1_1, SpeedUnit.METER_PER_SECOND, null),
                "constructing matrix with null storage type should have thrown an exception", NullPointerException.class);
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
                new SpeedMatrix(
                        DoubleMatrixData.instantiate(doubleMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(1,
                new SpeedMatrix(
                        DoubleMatrixData.instantiate(doubleMatrix00, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(2, new SpeedMatrix(DoubleMatrixData.instantiate(speedMatrix00, StorageType.DENSE), SpeedUnit.KM_PER_HOUR));
        smMap.put(3, new SpeedMatrix(DoubleMatrixData.instantiate(speedMatrix00, StorageType.SPARSE), SpeedUnit.KM_PER_HOUR));
        smMap.put(4, new SpeedMatrix(DoubleMatrixData.instantiate(speedList, 0, 0, StorageType.DENSE), SpeedUnit.KM_PER_HOUR));
        smMap.put(5, new SpeedMatrix(DoubleMatrixData.instantiate(speedList, 0, 0, StorageType.SPARSE), SpeedUnit.KM_PER_HOUR));
        smMap.put(6,
                new SpeedMatrix(
                        DoubleMatrixData.instantiate(doubleMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.DENSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(7,
                new SpeedMatrix(
                        DoubleMatrixData.instantiate(doubleMatrix10, SpeedUnit.KM_PER_HOUR.getScale(), StorageType.SPARSE),
                        SpeedUnit.KM_PER_HOUR));
        smMap.put(8, new SpeedMatrix(DoubleMatrixData.instantiate(speedMatrix10, StorageType.DENSE), SpeedUnit.KM_PER_HOUR));
        smMap.put(9, new SpeedMatrix(DoubleMatrixData.instantiate(speedMatrix10, StorageType.SPARSE), SpeedUnit.KM_PER_HOUR));

        for (Map.Entry<Integer, SpeedMatrix> entry : smMap.entrySet())
        {
            int key = entry.getKey();
            SpeedMatrix sv = entry.getValue();
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(0, sv.cardinality(), "key=" + key);
            assertEquals(0, sv.getScalars().length, "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, sv.getDisplayUnit(), "key=" + key);
            assertEquals(sv.getStorageType(), key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE,
                    "DENSE/SPARSE: key = " + key);
            assertFalse(sv.isMutable(), "key=" + key);
            SpeedMatrix svm = sv.mutable();
            assertTrue(svm.isMutable(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svm.getDisplayUnit());
            assertEquals(svm.getStorageType(), key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE,
                    "DENSE/SPARSE: key = " + key);
            SpeedMatrix svr = svm.abs();
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svm = svm.ceil();
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit(), "key=" + key);
            svr = svm.decrementBy(Speed.ONE);
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit(), "key=" + key);
            svr = svm.incrementBy(Speed.ONE);
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit());
            svr = svm.plus(sv);
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit(), "key=" + key);
            assertEquals(svr.getStorageType(), key % 2 == 0 ? StorageType.DENSE : StorageType.SPARSE,
                    "DENSE/SPARSE: key = " + key);
            svr = sv.toDense();
            assertEquals(StorageType.DENSE, svr.getStorageType(), "key=" + key);
            svr = sv.toSparse();
            assertEquals(StorageType.SPARSE, svr.getStorageType(), "key=" + key);
            assertEquals(0, sv.getValuesSI().length, "key=" + key);
            assertEquals(0, sv.getValuesInUnit().length, "key=" + key);
            assertEquals(0, sv.getValuesInUnit(SpeedUnit.KNOT).length, "key=" + key);
            svr = sv.times(2.0);
            assertEquals(0, sv.rows(), "key=" + key);
            assertEquals(0, sv.cols(), "key=" + key);
            assertEquals(SpeedUnit.KM_PER_HOUR, svr.getDisplayUnit(), "key=" + key);
        }
    }

}
