package org.djunits.value.vfloat.matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.AreaMatrix;
import org.djunits.value.vdouble.matrix.Determinant;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatArea;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.vector.FLOATVECTOR;
import org.djunits.value.vfloat.vector.FloatAreaVector;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * ...
 */
public class FloatMatrixMethodTest
{

    /**
     * Test the standard methods of all matrix classes.
     * @throws UnitException on error
     * @throws ValueRuntimeException on error
     */
    @Test
    public void testMatrixMethods() throws ValueRuntimeException, UnitException
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(10, 20);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(10, 20);
        float[][] reverseSparseTestData = new float[sparseTestData.length][];
        // sparseTestData and reverseSparseTestData should not "run out of values" at the same index
        for (int index = 0; index < sparseTestData.length; index++)
        {
            reverseSparseTestData[reverseSparseTestData.length - 1 - index] = sparseTestData[index];
        }
        // Ensure that there are > 50% positions where both have a non-zero value
        for (int row = 1; row < 8; row++)
        {
            for (int col = 2; col < 18; col++)
            {
                sparseTestData[row][col] = 10000.456f + row + 100 * col;
                reverseSparseTestData[row][col] = 20000.567f + row + 100 * col;
            }
        }

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaMatrix am =
                        FloatMatrix.instantiate(FloatMatrixData.instantiate(testData, au.getScale(), storageType), au);

                // INDEX CHECKING
                for (int row : new int[] {-1, 0, denseTestData.length - 1, denseTestData.length})
                {
                    for (int col : new int[] {-1, 0, denseTestData[0].length - 1, denseTestData[0].length})
                    {
                        if (row < 0 || col < 0 || row >= denseTestData.length || col >= denseTestData[0].length)
                        {
                            try
                            {
                                am.get(row, col);
                                fail("bad row or bad column value should have thrown a ValueRuntimeException");
                            }
                            catch (ValueRuntimeException vre)
                            {
                                // Ignore expected exception
                            }
                        }
                        else
                        {
                            am.get(row, col);
                        }
                    }
                    if (row < 0 || row >= denseTestData.length)
                    {
                        try
                        {
                            am.getRow(row);
                            fail("getRow with bad row value should have thrown a ValueRuntimeException");
                        }
                        catch (ValueRuntimeException vre)
                        {
                            // Ignore expected exception
                        }
                    }
                }
                for (int col : new int[] {-1, 0, denseTestData[0].length - 1, denseTestData[0].length})
                {
                    if (col < 0 || col >= denseTestData[0].length)
                    {
                        try
                        {
                            am.getColumn(col);
                            fail("getColumn with bad column value should have thrown a ValueRuntimeException");
                        }
                        catch (ValueRuntimeException vre)
                        {
                            // Ignore expected exception
                        }
                    }
                    else
                    {
                        am.getColumn(col);
                    }
                }

                // SPARSE AND DENSE
                assertEquals(am, am.toSparse());
                assertEquals(am, am.toDense());
                assertEquals(am, am.toSparse().toDense());
                assertEquals(am, am.toDense().toSparse());
                assertEquals(am.hashCode(), am.toSparse().hashCode());
                assertEquals(am.hashCode(), am.toDense().hashCode());
                assertTrue(am.toDense().isDense());
                assertFalse(am.toDense().isSparse());
                assertTrue(am.toSparse().isSparse());
                assertFalse(am.toSparse().isDense());

                // EQUALS
                assertEquals(am, am);
                assertNotEquals(am, new Object());
                assertNotEquals(am, null);
                assertNotEquals(am, FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(testData, LengthUnit.METER.getScale(), storageType), LengthUnit.METER));
                assertNotEquals(am, am.divide(2.0f));

                // MUTABLE
                assertFalse(am.isMutable());
                FloatAreaMatrix ammut = am.mutable();
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                FloatAreaMatrix ammut2 = ammut.multiplyBy(1.0);
                assertEquals(am, ammut2);
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                assertTrue(ammut2.isMutable());
                ammut2 = ammut2.mutable().divideBy(2.0);
                assertEquals(am, ammut);
                assertNotEquals(am, ammut2);
                FloatAreaMatrix ammut3 = ammut2.mutable().divideBy(0.0);
                for (int row = 0; row < ammut3.rows(); row++)
                {
                    for (int col = 0; col < ammut3.cols(); col++)
                    {
                        if (ammut2.getSI(row, col) == 0)
                        {
                            assertTrue("Value should be NaN", Float.isNaN(ammut3.getSI(row, col)));

                        }
                        else
                        {
                            assertTrue("Value should be Infinite", Float.isInfinite(ammut3.getSI(row, col)));
                        }
                    }
                }

                // ZSUM and CARDINALITY
                FloatArea zSum = am.zSum();
                float sum = 0;
                int card = 0;
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        sum += testData[row][col];
                        card += testData[row][col] == 0.0f ? 0 : 1;
                    }
                }
                assertEquals("zSum", sum, zSum.getInUnit(), 5f);
                assertEquals("cardinality", card, am.cardinality());
                FloatAreaMatrix ammutZero = ammut.multiplyBy(0.0f);
                assertEquals("cardinality should be 0", 0, ammutZero.cardinality());
                assertEquals("zSum should be 0", 0.0, ammutZero.zSum().getSI(), 0);

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                FloatAreaMatrix amold = am.clone();
                FloatArea fa = FloatArea.of(10.0f, "m^2");
                FloatAreaMatrix aminc = am.mutable().incrementBy(fa).immutable();
                FloatAreaMatrix amdec = am.mutable().decrementBy(fa).immutable();
                FloatAreaMatrix amid = aminc.mutable().decrementBy(fa);
                assertEquals("immutable matrix should not change when converted to mutable", am, amold);
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals("increment and decrement with scalar should result in same matrix", am.getSI(row, col),
                                amid.getSI(row, col), 5f);
                        assertEquals("m + s = (m+s)", au.getScale().toStandardUnit(testData[row][col]) + 10.0,
                                aminc.getSI(row, col), 6f);
                        assertEquals("m - s = (m-s)", au.getScale().toStandardUnit(testData[row][col]) - 10.0,
                                amdec.getSI(row, col), 6f);
                    }
                }

                // MULTIPLYBY() and DIVIDEBY(), TIMES(), DIVIDE()
                FloatAreaMatrix amt5 = am.mutable().multiplyBy(5.0f).immutable();
                FloatAreaMatrix amd5 = am.mutable().divideBy(5.0f).immutable();
                FloatAreaMatrix amtd = amt5.mutable().divideBy(5.0f);
                FloatAreaMatrix amtimD = am.times(5.0d);
                FloatAreaMatrix amtimF = am.times(5.0f);
                FloatAreaMatrix amdivD = am.divide(5.0d);
                FloatAreaMatrix amdivF = am.divide(5.0f);
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals("times followed by divide with constant should result in same matrix", am.getSI(row, col),
                                amtd.getSI(row, col), 0.5);
                        assertEquals("m * 5.0 = (m*5.0)", au.getScale().toStandardUnit(testData[row][col]) * 5.0f,
                                amt5.getSI(row, col), 50f);
                        assertEquals("m / 5.0 = (m/5.0)", au.getScale().toStandardUnit(testData[row][col]) / 5.0f,
                                amd5.getSI(row, col), 2f);
                        assertEquals("amtimD", amt5.getSI(row, col), amtimD.getSI(row, col), 0.1f);
                        assertEquals("amtimF", amt5.getSI(row, col), amtimF.getSI(row, col), 0.1f);
                        assertEquals("amdivD", amd5.getSI(row, col), amdivD.getSI(row, col), 0.01f);
                        assertEquals("amdivD", amd5.getSI(row, col), amdivF.getSI(row, col), 0.01f);
                    }
                }

                // GET(), GETINUNIT()
                assertEquals("get()", new FloatArea(testData[2][2], au), am.get(2, 2));
                assertEquals("getSI()", au.getScale().toStandardUnit(testData[2][2]), am.getSI(2, 2), 1f);
                assertEquals("getInUnit()", testData[2][2], am.getInUnit(2, 2), 0.1);
                assertEquals("getInUnit(unit)",
                        AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2][2])),
                        am.getInUnit(2, 2, AreaUnit.SQUARE_YARD), 10f);

                // SET(), SETINUNIT()
                FloatArea fasqft = new FloatArea(10.5f, AreaUnit.SQUARE_FOOT);
                FloatAreaMatrix famChange = am.clone().mutable();
                famChange.set(2, 2, fasqft);
                assertEquals("set()", fasqft.si, famChange.get(2, 2).si, 0.1f);
                famChange = am.clone().mutable();
                famChange.setSI(2, 2, 123.4f);
                assertEquals("setSI()", 123.4f, famChange.get(2, 2).si, 0.1f);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.2f);
                assertEquals("setInUnit()", 1.2f, famChange.getInUnit(2, 2), 0.1f);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.5f, AreaUnit.HECTARE);
                assertEquals("setInUnit(unit)", 15000.0f, famChange.get(2, 2).si, 1.0f);

                // GETROW(), GETCOLUMN(), GETDIAGONAL
                float[][] squareData = storageType.equals(StorageType.DENSE) ? FLOATMATRIX.denseRectArrays(12, 12)
                        : FLOATMATRIX.sparseRectArrays(12, 12);
                FloatAreaMatrix amSquare =
                        FloatMatrix.instantiate(FloatMatrixData.instantiate(squareData, au.getScale(), storageType), au);
                float[] row2si = am.getRowSI(2);
                float[] col2si = am.getColumnSI(2);
                float[] diagsi = amSquare.getDiagonalSI();
                FloatAreaVector row2v = am.getRow(2);
                FloatAreaVector col2v = am.getColumn(2);
                FloatAreaVector diagv = amSquare.getDiagonal();
                FloatArea[] row2scalar = am.getRowScalars(2);
                FloatArea[] col2scalar = am.getColumnScalars(2);
                FloatArea[] diagscalar = amSquare.getDiagonalScalars();
                for (int col = 0; col < testData[0].length; col++)
                {
                    assertEquals("row2si", au.getScale().toStandardUnit(testData[2][col]), row2si[col], 10f);
                    assertEquals("row2v", au.getScale().toStandardUnit(testData[2][col]), row2v.getSI(col), 10f);
                    assertEquals("row2scalar", au.getScale().toStandardUnit(testData[2][col]), row2scalar[col].si, 10f);
                }
                for (int row = 0; row < testData.length; row++)
                {
                    assertEquals("col2si", au.getScale().toStandardUnit(testData[row][2]), col2si[row], 10f);
                    assertEquals("col2v", au.getScale().toStandardUnit(testData[row][2]), col2v.getSI(row), 10f);
                    assertEquals("col2scalar", au.getScale().toStandardUnit(testData[row][2]), col2scalar[row].si, 10f);
                }
                for (int diag = 0; diag < amSquare.rows(); diag++)
                {
                    assertEquals("diag2si", au.getScale().toStandardUnit(squareData[diag][diag]), diagsi[diag], 0.1f);
                    assertEquals("diag2v", au.getScale().toStandardUnit(squareData[diag][diag]), diagv.getSI(diag), 0.1f);
                    assertEquals("diag2scalar", au.getScale().toStandardUnit(squareData[diag][diag]), diagscalar[diag].si,
                            0.1f);
                }

                // GETVALUES(), GETSCALARS()
                float[][] valsi = am.getValuesSI();
                float[][] valunit = am.getValuesInUnit();
                float[][] valsqft = am.getValuesInUnit(AreaUnit.SQUARE_YARD);
                FloatArea[][] valscalars = am.getScalars();
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals("getValuesSI()", au.getScale().toStandardUnit(testData[row][col]), valsi[row][col], 5f);
                        assertEquals("getValuesInUnit()", testData[row][col], valunit[row][col], 0.1);
                        assertEquals("getValuesInUnit(unit)", AreaUnit.SQUARE_YARD.getScale()
                                .fromStandardUnit(au.getScale().toStandardUnit(testData[row][col])), valsqft[row][col], 10f);
                        assertEquals("getValuesInUnit(unit)", au.getScale().toStandardUnit(testData[row][col]),
                                valscalars[row][col].si, 5f);
                    }
                }

                // ASSIGN FUNCTION ABS, CEIL, FLOOR, NEG, RINT
                FloatAreaMatrix amdiv2 = am.divide(2.0f);
                assertEquals(am.getStorageType(), amdiv2.getStorageType());
                assertEquals(am.getDisplayUnit(), amdiv2.getDisplayUnit());
                FloatAreaMatrix amAbs = amdiv2.mutable().abs().immutable();
                assertEquals(am.getStorageType(), amAbs.getStorageType());
                assertEquals(am.getDisplayUnit(), amAbs.getDisplayUnit());
                FloatAreaMatrix amCeil = amdiv2.mutable().ceil().immutable();
                assertEquals(am.getStorageType(), amCeil.getStorageType());
                assertEquals(am.getDisplayUnit(), amCeil.getDisplayUnit());
                FloatAreaMatrix amFloor = amdiv2.mutable().floor().immutable();
                assertEquals(am.getStorageType(), amFloor.getStorageType());
                assertEquals(am.getDisplayUnit(), amFloor.getDisplayUnit());
                FloatAreaMatrix amNeg = amdiv2.mutable().neg().immutable();
                assertEquals(am.getStorageType(), amNeg.getStorageType());
                assertEquals(am.getDisplayUnit(), amNeg.getDisplayUnit());
                FloatAreaMatrix amRint = amdiv2.mutable().rint().immutable();
                assertEquals(am.getStorageType(), amRint.getStorageType());
                assertEquals(am.getDisplayUnit(), amRint.getDisplayUnit());
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        // TODO: Should be rounded IN THE UNIT rather than BY SI VALUES
                        assertEquals("div2", au.getScale().toStandardUnit(testData[row][col]) / 2.0f, amdiv2.getSI(row, col),
                                5f);
                        assertEquals("abs", (float) Math.abs(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amAbs.getSI(row, col), 0.1f);
                        assertEquals("ceil", (float) Math.ceil(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amCeil.getSI(row, col), 5f);
                        assertEquals("floor", (float) Math.floor(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amFloor.getSI(row, col), 5f);
                        assertEquals("neg", -au.getScale().toStandardUnit(testData[row][col]) / 2.0f, amNeg.getSI(row, col),
                                5f);
                        assertEquals("rint", (float) Math.rint(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amRint.getSI(row, col), 5f);
                    }
                }

                float[][] testData4x4 = new float[][] {{2, 3, 5, 7}, {11, 13, 17, 19}, {23, 29, 31, 37}, {41, 43, 47, 49}};
                FloatAreaMatrix am4x4 =
                        FloatMatrix.instantiate(FloatMatrixData.instantiate(testData4x4, au.getScale(), storageType), au);
                float det = am4x4.determinantSI();
                float detCalc = Determinant.det(am4x4.getValuesSI());
                float err = Math.max(det, detCalc) / 1000.0f;
                assertEquals("Determinant of square matrix with unit " + au.getDefaultTextualAbbreviation() + ", storage = "
                        + storageType + " = " + det + " but should have been " + detCalc, detCalc, det, err);
                Try.testFail(() -> am.determinantSI(), "Determinant of non-square matrix should have thrown exception");

                // TEST METHODS THAT INVOLVE TWO MATRIX INSTANCES

                for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    float[][] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
                    {

                        // PLUS and INCREMENTBY(MATRIX)
                        FloatAreaMatrix am2 = FloatMatrix
                                .instantiate(FloatMatrixData.instantiate(testData2, au2.getScale(), storageType2), au2);
                        FloatAreaMatrix amSum1 = am.plus(am2);
                        FloatAreaMatrix amSum2 = am2.plus(am);
                        FloatAreaMatrix amSum3 = am.mutable().incrementBy(am2).immutable();
                        FloatAreaMatrix amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals("a+b == b+a", amSum1, amSum2);
                        assertEquals("a+b == b+a", amSum1, amSum3);
                        assertEquals("a+b == b+a", amSum1, amSum4);
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amSum1.getSI(row, col))
                                        ? Math.abs(amSum1.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals("value in matrix matches",
                                        au.getScale().toStandardUnit(testData[row][col])
                                                + au2.getScale().toStandardUnit(testData2[row][col]),
                                        amSum1.getSI(row, col), tolerance);
                            }
                        }

                        // MINUS and DECREMENTBY(MATRIX)
                        FloatAreaMatrix amDiff1 = am.minus(am2);
                        FloatAreaMatrix amDiff2 = am2.minus(am).mutable().neg();
                        FloatAreaMatrix amDiff3 = am.mutable().decrementBy(am2).immutable();
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff2);
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff3);
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amDiff1.getSI(row, col))
                                        ? Math.abs(amDiff1.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals("value in matrix matches",
                                        au.getScale().toStandardUnit(testData[row][col])
                                                - au2.getScale().toStandardUnit(testData2[row][col]),
                                        amDiff1.getSI(row, col), tolerance);
                            }
                        }

                        // TIMES(MATRIX) and DIVIDE(MATRIX)
                        FloatSIMatrix amTim = am.times(am2);
                        FloatSIMatrix amDiv = am.divide(am2);
                        assertEquals("unit of m2 * m2 should be m4", "m4",
                                amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        assertEquals("unit of m2 / m2 should be 1", "1",
                                amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amTim.getSI(row, col))
                                        ? Math.abs(amTim.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals("value in m2 * m2 matches",
                                        au.getScale().toStandardUnit(testData[row][col])
                                                * au2.getScale().toStandardUnit(testData2[row][col]),
                                        amTim.getSI(row, col), tolerance);
                                tolerance = Float.isFinite(amTim.getSI(row, col)) ? Math.abs(amDiv.getSI(row, col) / 10000.0f)
                                        : 0.1f;
                                assertEquals("value in m2 / m2 matches (could be NaN)",
                                        au.getScale().toStandardUnit(testData[row][col])
                                                / au2.getScale().toStandardUnit(testData2[row][col]),
                                        amDiv.getSI(row, col), tolerance);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Test if mutable methods give an error in case the matrix is immutable.
     */
    @Test
    public void testImmutableMatrix()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(5, 10);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaMatrix am =
                        FloatMatrix.instantiate(FloatMatrixData.instantiate(testData, au.getScale(), storageType), au);
                am = am.immutable();
                final FloatAreaMatrix amPtr = am;
                FloatArea fa = FloatArea.of(10.0f, "m^2");
                Try.testFail(() -> amPtr.assign(FloatMathFunctions.ABS), "ImmutableMatrix.assign(...) should throw error");
                Try.testFail(() -> amPtr.decrementBy(fa), "ImmutableMatrix.decrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.decrementBy(amPtr), "ImmutableMatrix.decrementBy(matrix) should throw error");
                Try.testFail(() -> amPtr.incrementBy(fa), "ImmutableMatrix.incrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.incrementBy(amPtr), "ImmutableMatrix.incrementBy(matrix) should throw error");
                Try.testFail(() -> amPtr.divideBy(2.0f), "ImmutableMatrix.divideBy(factor) should throw error");
                Try.testFail(() -> amPtr.multiplyBy(2.0f), "ImmutableMatrix.multiplyBy(factor) should throw error");
                Try.testFail(() -> amPtr.set(1, 1, fa), "ImmutableMatrix.set() should throw error");
                Try.testFail(() -> amPtr.setSI(1, 1, 20.1f), "ImmutableMatrix.setSI() should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 1, 15.2f), "ImmutableMatrix.setInUnit(f) should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 1, 15.2f, AreaUnit.ARE),
                        "ImmutableMatrix.setInUnit(f, u) should throw error");
                Try.testFail(() -> amPtr.abs(), "ImmutableMatrix.abs() should throw error");
                Try.testFail(() -> amPtr.ceil(), "ImmutableMatrix.ceil() should throw error");
                Try.testFail(() -> amPtr.floor(), "ImmutableMatrix.floor() should throw error");
                Try.testFail(() -> amPtr.neg(), "ImmutableMatrix.neg() should throw error");
                Try.testFail(() -> amPtr.rint(), "ImmutableMatrix.rint() should throw error");
            }
        }
    }

    /**
     * Test toString() methods. TODO: expand?
     */
    @Test
    public void testMatrixToString()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(5, 10);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaMatrix am =
                        FloatMatrix.instantiate(FloatMatrixData.instantiate(testData, au.getScale(), storageType), au);
                String s1 = am.toString(); // non-verbose with unit
                assertTrue(s1.contains(au.getDefaultTextualAbbreviation()));
                String s2 = am.toString(AreaUnit.SQUARE_INCH); // non-verbose with unit
                assertTrue(s2.contains(AreaUnit.SQUARE_INCH.getDefaultTextualAbbreviation()));
                String s3 = am.toString(AreaUnit.SQUARE_INCH, true, true); // verbose with unit
                assertTrue(s3.contains(AreaUnit.SQUARE_INCH.getDefaultTextualAbbreviation()));
                if (storageType.equals(StorageType.DENSE))
                {
                    assertTrue(s3.contains("Dense"));
                    assertFalse(s3.contains("Sparse"));
                }
                else
                {
                    assertFalse(s3.contains("Dense"));
                    assertTrue(s3.contains("Sparse"));
                }
                assertTrue(s3.contains("Rel"));
                assertFalse(s3.contains("Abs"));
                assertTrue(s3.contains("Immutable"));
                assertFalse(s3.contains("Mutable"));
                FloatAreaMatrix ammut = am.mutable();
                String smut = ammut.toString(AreaUnit.SQUARE_INCH, true, true); // verbose with unit
                assertFalse(smut.contains("Immutable"));
                assertTrue(smut.contains("Mutable"));
                String sNotVerbose = ammut.toString(false, false);
                assertFalse(sNotVerbose.contains("Rel"));
                assertFalse(sNotVerbose.contains("Abs"));
                assertFalse(sNotVerbose.contains("Immutable"));
                assertFalse(sNotVerbose.contains("Mutable"));
                assertFalse(sNotVerbose.contains(au.getDefaultTextualAbbreviation()));
            }
        }
        FloatTimeMatrix tm = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        FloatLengthMatrix lm = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, LengthUnit.SI.getScale(), StorageType.DENSE), LengthUnit.SI);
        String sl = lm.toString(LengthUnit.SI, true, true); // verbose with unit
        assertTrue(sl.contains("Rel"));
        assertFalse(sl.contains("Abs"));
    }

    /**
     * Test the extra methods that Absolute and Relative with Absolute matrices implement.
     */
    @Test
    public void testSpecialMatrixMethodsRelWithAbs()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10);
        FloatTimeMatrix tm = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationMatrix dm = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);
        assertTrue(tm.isAbsolute());
        assertFalse(dm.isAbsolute());
        assertFalse(tm.isRelative());
        assertTrue(dm.isRelative());

        FloatTimeMatrix absPlusRel = tm.plus(dm);
        FloatTimeMatrix absMinusRel = tm.minus(dm);
        float[][] halfDenseData = FLOATMATRIX.denseRectArrays(5, 10);
        for (int row = 0; row < halfDenseData.length; row++)
        {
            for (int col = 0; col < halfDenseData[row].length; col++)
            {
                halfDenseData[row][col] *= 0.5;
            }
        }
        FloatTimeMatrix halfTimeMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(halfDenseData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationMatrix absMinusAbs = tm.minus(halfTimeMatrix);
        FloatTimeMatrix absDecByRelS = tm.mutable().decrementBy(FloatDuration.of(1.0f, "min"));
        FloatTimeMatrix absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0f));
        FloatTimeMatrix relPlusAbs = dm.plus(tm);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals("absPlusRel", 61.0 * denseTestData[row][col], absPlusRel.getSI(row, col), 0.01);
                assertEquals("absMinusRel", -59.0 * denseTestData[row][col], absMinusRel.getSI(row, col), 0.01);
                assertEquals("absMinusAbs", denseTestData[row][col] / 2.0, absMinusAbs.getSI(row, col), 0.01);
                assertEquals("absDecByRelS", denseTestData[row][col] - 60.0, absDecByRelS.getSI(row, col), 0.01);
                assertEquals("absDecByRelM", -29.0 * denseTestData[row][col], absDecByRelM.getSI(row, col), 0.01);
                assertEquals("relPlusAbs", 61.0 * denseTestData[row][col], relPlusAbs.getSI(row, col), 0.01);
            }
        }
        for (int dRows : new int[] {-1, 0, 1})
        {
            for (int dCols : new int[] {-1, 0, 1})
            {
                if (dRows == 0 && dCols == 0)
                {
                    continue;
                }
                float[][] other = FLOATMATRIX.denseRectArrays(denseTestData.length + dRows, denseTestData[0].length + dCols);
                FloatTimeMatrix wrongTimeMatrix = FloatMatrix.instantiate(
                        FloatMatrixData.instantiate(other, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
                try
                {
                    tm.mutable().minus(wrongTimeMatrix);
                    fail("Mismatching size should have thrown a ValueRuntimeException");
                }
                catch (ValueRuntimeException vre)
                {
                    // Ignore expected exception
                }
            }
        }
        assertTrue("toString returns something informative",
                FloatMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                        .startsWith("FloatMatrixData"));
    }

    /**
     * Execute a mats++-like memory test on a sparse matrix. See
     * <a href="http://www.eng.auburn.edu/~agrawvd/COURSE/E7250_05/REPORTS_TERM/Raghuraman_Mem.doc">Memory Test</a>.
     */
    @Test
    public void memoryTest()
    {
        AreaMatrix am = DoubleMatrix.instantiate(new double[5][10], AreaUnit.SI, StorageType.SPARSE);
        am = am.mutable();
        double nonZeroValue = 123.456;
        // Initially the array is filled with zero values; we can skip the initialization phase
        for (int compoundIndex = 0; compoundIndex < am.cols() * am.rows(); compoundIndex++)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals("initial value is 0", 0d, am.getSI(row, col), 0.0001);
            am.setSI(row, col, nonZeroValue);
            assertEquals("current value is nonZero", nonZeroValue, am.getSI(row, col), 0.0001);
        }
        for (int compoundIndex = am.cols() * am.rows(); --compoundIndex >= 0;)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals("current value is nonZero", nonZeroValue, am.getSI(row, col), 0.0001);
            am.setSI(row, col, 0d);
            assertEquals("final value is 0", 0d, am.getSI(row, col), 0.0001);
        }
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(10, 20);
        FloatTimeMatrix timeMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationMatrix durationMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);

        float[] halfDenseData = FLOATVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        FloatTimeMatrix relPlusAbsTime = durationMatrix.plus(timeMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals("relPlusAbsTime", 61.0 * denseTestData[row][col], relPlusAbsTime.getSI(row, col), 0.01);
            }
        }
        FloatTime floatTime = durationMatrix.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals("Unit of instantiateScalarAbsSI matches", TimeUnit.EPOCH_DAY, floatTime.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, floatTime.si, 0.1);

        FloatAngleMatrix angleMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, AngleUnit.DEGREE.getScale(), StorageType.DENSE), AngleUnit.DEGREE);
        FloatDirectionMatrix directionMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, DirectionUnit.EAST_DEGREE.getScale(), StorageType.DENSE),
                DirectionUnit.EAST_DEGREE);

        FloatDirectionMatrix relPlusAbsDirection = angleMatrix.plus(directionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals("relPlusAbsTime", 2.0 / 180 * Math.PI * denseTestData[row][col],
                        relPlusAbsDirection.getSI(row, col), 0.01);
            }
        }
        FloatDirection floatDirection = angleMatrix.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals("Unit of instantiateScalarAbsSI matches", DirectionUnit.NORTH_RADIAN, floatDirection.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, floatDirection.si, 0.1);

        FloatTemperatureMatrix temperatureMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT.getScale(), StorageType.DENSE),
                TemperatureUnit.DEGREE_FAHRENHEIT);
        FloatAbsoluteTemperatureMatrix absoluteTemperatureMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, AbsoluteTemperatureUnit.KELVIN.getScale(), StorageType.DENSE),
                AbsoluteTemperatureUnit.KELVIN);

        FloatAbsoluteTemperatureMatrix relPlusAbsTemperature = temperatureMatrix.plus(absoluteTemperatureMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals("relPlusAbsTime", (1.0 + 5.0 / 9.0) * denseTestData[row][col],
                        relPlusAbsTemperature.getSI(row, col), 0.01);
            }
        }
        FloatAbsoluteTemperature floatAbsoluteTemperature =
                temperatureMatrix.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals("Unit of instantiateScalarAbsSI matches", AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT,
                floatAbsoluteTemperature.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, floatAbsoluteTemperature.si, 0.1);

        FloatLengthMatrix lengthMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, LengthUnit.MILE.getScale(), StorageType.DENSE), LengthUnit.MILE);
        FloatPositionMatrix positionMatrix = FloatMatrix.instantiate(
                FloatMatrixData.instantiate(denseTestData, PositionUnit.KILOMETER.getScale(), StorageType.DENSE),
                PositionUnit.KILOMETER);

        FloatPositionMatrix relPlusAbsPosition = lengthMatrix.plus(positionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals("relPlusAbsTime", 2609.344 * denseTestData[row][col], relPlusAbsPosition.getSI(row, col), 0.1);
            }
        }
        FloatPosition floatPosition = lengthMatrix.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals("Unit of instantiateScalarAbsSI matches", PositionUnit.ANGSTROM, floatPosition.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, floatPosition.si, 0.1);
    }

    /**
     * Test the <code>as</code> method of the FloatSIMatrix class.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <U> the unit type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U>> void testAsUnit() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnitException
    {
        float[][] testValues = FLOATMATRIX.denseRectArrays(10, 20);
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, storageType})
                    {
                        SIUnit siUnit = SIUnit.of(unit.getQuantity().getSiDimensions());
                        FloatSIMatrix matrix = FloatSIMatrix.instantiate(testValues, siUnit, storageType2);
                        Method asMethod = FloatSIMatrix.class.getDeclaredMethod("as", Unit.class);
                        FloatMatrixRel<U, ?, ?, ?> asMatrix =
                                (FloatMatrixRel<U, ?, ?, ?>) asMethod.invoke(matrix, siUnit);
                        assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrix.getDisplayUnit());
                        siUnit = SIUnit.of(AbsoluteTemperatureUnit.KELVIN.getQuantity().getSiDimensions());
                        for (int row = 0; row < testValues.length; row++)
                        {
                            for (int col = 0; col < testValues[0].length; col++)
                            {
                                assertEquals("Values should match", testValues[row][col], matrix.getInUnit(row, col), 0.001);
                            }
                        }
                        try
                        {
                            asMethod.invoke(matrix, siUnit);
                            fail("as method should not be able to cast to unrelated (absoluteTemperature) unit");
                        }
                        catch (InvocationTargetException ite)
                        {
                            Throwable cause = ite.getCause();
                            assertEquals("cause is UnitRuntimeException", UnitRuntimeException.class, cause.getClass());
                            // Otherwise ignore expected exception
                        }
                    }
                }
            }
        }
    }

    /**
     * Test the equals method.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals()
    {
        float[][] testData = FLOATMATRIX.denseRectArrays(12, 34);
        testData[2][2] = 0;
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatMatrixData fmd = FloatMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue("Float matrix is equal to itself", fmd.equals(fmd));
            assertFalse("Float matrix is not equal to null", fmd.equals(null));
            assertFalse("Float matrix data is not equal to some string", fmd.equals("some string"));
            assertTrue("Float matrix is equal to sparse version of itself", fmd.equals(fmd.toSparse()));
            assertTrue("Float matrix is equal to dense version of itself", fmd.equals(fmd.toDense()));
            for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                FloatMatrixData dvd2 = FloatMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(
                        "Float matrix data is equal to other double vector containing same values regardless of storage type",
                        fmd, dvd2);
                float[][] testData2 = FLOATMATRIX.denseRectArrays(12, 33);
                testData2[2][2] = 0;
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Float matrix data is not equal to other double vector containing same values except last one",
                        fmd.equals(dvd2));
                testData2 = FLOATMATRIX.denseRectArrays(13, 34);
                testData2[2][2] = 0;
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Float matrix data is not equal to other double vector containing same values except last one",
                        fmd.equals(dvd2));
                testData2 = FLOATMATRIX.denseRectArrays(12, 34);
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Float matrix data is not equal to other double vector containing same values except for one zero",
                        fmd.equals(dvd2));
            }
        }
    }

    /**
     * Test the sparse value class.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void sparseValueTest()
    {
        try
        {
            new FloatSparseValue(-1, 0, 123.456f);
            fail("Negative row should have caused a ValueRuntimeException");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        try
        {
            new FloatSparseValue(0, -1, 123.456f);
            fail("Negative column should have caused a ValueRuntimeException");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        FloatLength length = FloatLength.valueOf("123 km");
        FloatSparseValue dsv = new FloatSparseValue(2, 3, length);
        assertEquals("row matches", 2, dsv.getRow());
        assertEquals("column matches", 3, dsv.getColumn());
        assertEquals("value matches", 123000, dsv.getValueSI(), 0.1);
        dsv = new FloatSparseValue(2, 3, 123.000f, LengthUnit.KILOMETER);
        assertEquals("row matches", 2, dsv.getRow());
        assertEquals("column matches", 3, dsv.getColumn());
        assertEquals("value matches", 123000, dsv.getValueSI(), 0.1);
    }

}
