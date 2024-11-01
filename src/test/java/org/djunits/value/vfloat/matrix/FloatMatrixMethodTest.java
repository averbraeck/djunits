package org.djunits.value.vfloat.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.util.UnitException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.AreaMatrix;
import org.djunits.value.vdouble.matrix.Determinant;
import org.djunits.value.vfloat.function.FloatMathFunctions;
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
import org.junit.jupiter.api.Test;

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
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(10, 20, false);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(10, 20, false);
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
                FloatAreaMatrix am = new FloatAreaMatrix(testData, au, storageType);

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
                                fail("bad row or bad column value should have thrown an IndexOutOfBoundsException");
                            }
                            catch (IndexOutOfBoundsException vre)
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
                            fail("getRow with bad row value should have thrown an IndexOutOfBoundsException");
                        }
                        catch (IndexOutOfBoundsException vre)
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
                            fail("getColumn with bad column value should have thrown an IndexOutOfBoundsException");
                        }
                        catch (IndexOutOfBoundsException vre)
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
                assertNotEquals(am, new FloatLengthMatrix(testData, LengthUnit.METER, storageType));
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
                            assertTrue(Float.isNaN(ammut3.getSI(row, col)), "Value should be NaN");

                        }
                        else
                        {
                            assertTrue(Float.isInfinite(ammut3.getSI(row, col)), "Value should be Infinite");
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
                assertEquals(sum, zSum.getInUnit(), 5f, "zSum");
                assertEquals(card, am.cardinality(), "cardinality");
                FloatAreaMatrix ammutZero = ammut.multiplyBy(0.0f);
                assertEquals(0, ammutZero.cardinality(), "cardinality should be 0");
                assertEquals(0.0, ammutZero.zSum().getSI(), 0, "zSum should be 0");

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                FloatAreaMatrix amold = am.clone();
                FloatArea fa = FloatArea.of(10.0f, "m^2");
                FloatAreaMatrix aminc = am.mutable().incrementBy(fa).immutable();
                FloatAreaMatrix amdec = am.mutable().decrementBy(fa).immutable();
                FloatAreaMatrix amid = aminc.mutable().decrementBy(fa);
                assertEquals(am, amold, "immutable matrix should not change when converted to mutable");
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals(am.getSI(row, col), amid.getSI(row, col), 5f,
                                "increment and decrement with scalar should result in same matrix");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) + 10.0, aminc.getSI(row, col), 6f,
                                "m + s = (m+s)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) - 10.0, amdec.getSI(row, col), 6f,
                                "m - s = (m-s)");
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
                        assertEquals(am.getSI(row, col), amtd.getSI(row, col), 0.5,
                                "times followed by divide with constant should result in same matrix");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) * 5.0f, amt5.getSI(row, col), 50f,
                                "m * 5.0 = (m*5.0)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) / 5.0f, amd5.getSI(row, col), 2f,
                                "m / 5.0 = (m/5.0)");
                        assertEquals(amt5.getSI(row, col), amtimD.getSI(row, col), 0.1f, "amtimD");
                        assertEquals(amt5.getSI(row, col), amtimF.getSI(row, col), 0.1f, "amtimF");
                        assertEquals(amd5.getSI(row, col), amdivD.getSI(row, col), 0.01f, "amdivD");
                        assertEquals(amd5.getSI(row, col), amdivF.getSI(row, col), 0.01f, "amdivD");
                    }
                }

                // GET(), GETINUNIT()
                assertEquals(new FloatArea(testData[2][2], au), am.get(2, 2), "get()");
                assertEquals(au.getScale().toStandardUnit(testData[2][2]), am.getSI(2, 2), 1f, "getSI()");
                assertEquals(testData[2][2], am.getInUnit(2, 2), 0.1, "getInUnit()");
                assertEquals(AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2][2])),
                        am.getInUnit(2, 2, AreaUnit.SQUARE_YARD), 10f, "getInUnit(unit)");

                // SET(), SETINUNIT()
                FloatArea fasqft = new FloatArea(10.5f, AreaUnit.SQUARE_FOOT);
                FloatAreaMatrix famChange = am.clone().mutable();
                famChange.set(2, 2, fasqft);
                assertEquals(fasqft.si, famChange.get(2, 2).si, 0.1f, "set()");
                famChange = am.clone().mutable();
                famChange.setSI(2, 2, 123.4f);
                assertEquals(123.4f, famChange.get(2, 2).si, 0.1f, "setSI()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.2f);
                assertEquals(1.2f, famChange.getInUnit(2, 2), 0.1f, "setInUnit()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.5f, AreaUnit.HECTARE);
                assertEquals(15000.0f, famChange.get(2, 2).si, 1.0f, "setInUnit(unit)");

                // GETROW(), GETCOLUMN(), GETDIAGONAL
                float[][] squareData = storageType.equals(StorageType.DENSE) ? FLOATMATRIX.denseRectArrays(12, 12, false)
                        : FLOATMATRIX.sparseRectArrays(12, 12, false);
                FloatAreaMatrix amSquare = new FloatAreaMatrix(squareData, au, storageType);
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
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2si[col], 10f, "row2si");
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2v.getSI(col), 10f, "row2v");
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2scalar[col].si, 10f, "row2scalar");
                }
                for (int row = 0; row < testData.length; row++)
                {
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2si[row], 10f, "col2si");
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2v.getSI(row), 10f, "col2v");
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2scalar[row].si, 10f, "col2scalar");
                }
                for (int diag = 0; diag < amSquare.rows(); diag++)
                {
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagsi[diag], 0.1f, "diag2si");
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagv.getSI(diag), 0.1f, "diag2v");
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagscalar[diag].si, 0.1f,
                            "diag2scalar");
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
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]), valsi[row][col], 5f, "getValuesSI()");
                        assertEquals(testData[row][col], valunit[row][col], 0.1, "getValuesInUnit()");
                        assertEquals(
                                AreaUnit.SQUARE_YARD.getScale()
                                        .fromStandardUnit(au.getScale().toStandardUnit(testData[row][col])),
                                valsqft[row][col], 10f, "getValuesInUnit(unit)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]), valscalars[row][col].si, 5f,
                                "getValuesInUnit(unit)");
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
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) / 2.0f, amdiv2.getSI(row, col), 5f,
                                "div2");
                        assertEquals((float) Math.abs(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amAbs.getSI(row, col), 0.1f, "abs");
                        assertEquals((float) Math.ceil(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amCeil.getSI(row, col), 5f, "ceil");
                        assertEquals((float) Math.floor(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amFloor.getSI(row, col), 5f, "floor");
                        assertEquals(-au.getScale().toStandardUnit(testData[row][col]) / 2.0f, amNeg.getSI(row, col), 5f,
                                "neg");
                        assertEquals((float) Math.rint(au.getScale().toStandardUnit(testData[row][col]) / 2.0f),
                                amRint.getSI(row, col), 5f, "rint");
                    }
                }

                float[][] testData4x4 = new float[][] {{2, 3, 5, 7}, {11, 13, 17, 19}, {23, 29, 31, 37}, {41, 43, 47, 49}};
                FloatAreaMatrix am4x4 = new FloatAreaMatrix(testData4x4, au, storageType);
                float det = am4x4.determinantSI();
                float detCalc = Determinant.det(am4x4.getValuesSI());
                float err = Math.max(det, detCalc) / 1000.0f;
                assertEquals(detCalc, det, err, "Determinant of square matrix with unit " + au.getDefaultTextualAbbreviation()
                        + ", storage = " + storageType + " = " + det + " but should have been " + detCalc);
                Try.testFail(() -> am.determinantSI(), "Determinant of non-square matrix should have thrown exception");

                // TEST METHODS THAT INVOLVE TWO MATRIX INSTANCES

                for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    float[][] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
                    {

                        // PLUS and INCREMENTBY(MATRIX)
                        FloatAreaMatrix am2 = new FloatAreaMatrix(testData2, au2, storageType2);
                        FloatAreaMatrix amSum1 = am.plus(am2);
                        FloatAreaMatrix amSum2 = am2.plus(am);
                        FloatAreaMatrix amSum3 = am.mutable().incrementBy(am2).immutable();
                        FloatAreaMatrix amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals(amSum1, amSum2, "a+b == b+a");
                        assertEquals(amSum1, amSum3, "a+b == b+a");
                        assertEquals(amSum1, amSum4, "a+b == b+a");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amSum1.getSI(row, col))
                                        ? Math.abs(amSum1.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                + au2.getScale().toStandardUnit(testData2[row][col]),
                                        amSum1.getSI(row, col), tolerance, "value in matrix matches");
                            }
                        }

                        // MINUS and DECREMENTBY(MATRIX)
                        FloatAreaMatrix amDiff1 = am.minus(am2);
                        FloatAreaMatrix amDiff2 = am2.minus(am).mutable().neg();
                        FloatAreaMatrix amDiff3 = am.mutable().decrementBy(am2).immutable();
                        assertEquals(amDiff1, amDiff2, "a-b == -(b-a)");
                        assertEquals(amDiff1, amDiff3, "a-b == -(b-a)");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amDiff1.getSI(row, col))
                                        ? Math.abs(amDiff1.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                - au2.getScale().toStandardUnit(testData2[row][col]),
                                        amDiff1.getSI(row, col), tolerance, "value in matrix matches");
                            }
                        }

                        // TIMES(MATRIX) and DIVIDE(MATRIX)
                        FloatSIMatrix amTim = am.times(am2);
                        FloatSIMatrix amDiv = am.divide(am2);
                        assertEquals("m4", amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 * m2 should be m4");
                        assertEquals("", amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 / m2 should be empty string");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                float tolerance = Float.isFinite(amTim.getSI(row, col))
                                        ? Math.abs(amTim.getSI(row, col) / 10000.0f) : 0.1f;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                * au2.getScale().toStandardUnit(testData2[row][col]),
                                        amTim.getSI(row, col), tolerance, "value in m2 * m2 matches");
                                tolerance = Float.isFinite(amDiv.getSI(row, col)) ? Math.abs(amDiv.getSI(row, col) / 10000.0f)
                                        : 0.1f;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                / au2.getScale().toStandardUnit(testData2[row][col]),
                                        amDiv.getSI(row, col), tolerance, "value in m2 / m2 matches (could be NaN)");
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
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10, false);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(5, 10, false);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaMatrix am = new FloatAreaMatrix(testData, au, storageType);
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
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10, false);
        float[][] sparseTestData = FLOATMATRIX.sparseRectArrays(5, 10, false);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaMatrix am = new FloatAreaMatrix(testData, au, storageType);
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
        FloatTimeMatrix tm = new FloatTimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        FloatLengthMatrix lm = new FloatLengthMatrix(denseTestData, LengthUnit.SI, StorageType.DENSE);
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
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10, false);
        FloatTimeMatrix tm = new FloatTimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        FloatDurationMatrix dm = new FloatDurationMatrix(denseTestData, DurationUnit.MINUTE, StorageType.DENSE);
        assertTrue(tm.isAbsolute());
        assertFalse(dm.isAbsolute());
        assertFalse(tm.isRelative());
        assertTrue(dm.isRelative());

        FloatTimeMatrix absPlusRel = tm.plus(dm);
        FloatTimeMatrix absMinusRel = tm.minus(dm);
        float[][] halfDenseData = FLOATMATRIX.denseRectArrays(5, 10, false);
        for (int row = 0; row < halfDenseData.length; row++)
        {
            for (int col = 0; col < halfDenseData[row].length; col++)
            {
                halfDenseData[row][col] *= 0.5;
            }
        }
        FloatTimeMatrix halfTimeMatrix = new FloatTimeMatrix(halfDenseData, TimeUnit.DEFAULT, StorageType.DENSE);
        FloatDurationMatrix absMinusAbs = tm.minus(halfTimeMatrix);
        FloatTimeMatrix absDecByRelS = tm.mutable().decrementBy(FloatDuration.of(1.0f, "min"));
        FloatTimeMatrix absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0f));
        FloatTimeMatrix relPlusAbs = dm.plus(tm);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(61.0 * denseTestData[row][col], absPlusRel.getSI(row, col), 0.01, "absPlusRel");
                assertEquals(-59.0 * denseTestData[row][col], absMinusRel.getSI(row, col), 0.01, "absMinusRel");
                assertEquals(denseTestData[row][col] / 2.0, absMinusAbs.getSI(row, col), 0.01, "absMinusAbs");
                assertEquals(denseTestData[row][col] - 60.0, absDecByRelS.getSI(row, col), 0.01, "absDecByRelS");
                assertEquals(-29.0 * denseTestData[row][col], absDecByRelM.getSI(row, col), 0.01, "absDecByRelM");
                assertEquals(61.0 * denseTestData[row][col], relPlusAbs.getSI(row, col), 0.01, "relPlusAbs");
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
                float[][] other =
                        FLOATMATRIX.denseRectArrays(denseTestData.length + dRows, denseTestData[0].length + dCols, false);
                FloatTimeMatrix wrongTimeMatrix = new FloatTimeMatrix(other, TimeUnit.DEFAULT, StorageType.DENSE);
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
        assertTrue(FloatMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                .startsWith("FloatMatrixData"), "toString returns something informative");
    }

    /**
     * Execute a mats++-like memory test on a sparse matrix. See
     * <a href="http://www.eng.auburn.edu/~agrawvd/COURSE/E7250_05/REPORTS_TERM/Raghuraman_Mem.doc">Memory Test</a>.
     */
    @Test
    public void memoryTest()
    {
        AreaMatrix am = new AreaMatrix(new double[5][10], AreaUnit.SI, StorageType.SPARSE);
        am = am.mutable();
        double nonZeroValue = 123.456;
        // Initially the array is filled with zero values; we can skip the initialization phase
        for (int compoundIndex = 0; compoundIndex < am.cols() * am.rows(); compoundIndex++)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals(0d, am.getSI(row, col), 0.0001, "initial value is 0");
            am.setSI(row, col, nonZeroValue);
            assertEquals(nonZeroValue, am.getSI(row, col), 0.0001, "current value is nonZero");
        }
        for (int compoundIndex = am.cols() * am.rows(); --compoundIndex >= 0;)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals(nonZeroValue, am.getSI(row, col), 0.0001, "current value is nonZero");
            am.setSI(row, col, 0d);
            assertEquals(0d, am.getSI(row, col), 0.0001, "final value is 0");
        }
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        float[][] denseTestData = FLOATMATRIX.denseRectArrays(10, 20, false);
        FloatTimeMatrix timeMatrix = new FloatTimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        FloatDurationMatrix durationMatrix = new FloatDurationMatrix(denseTestData, DurationUnit.MINUTE, StorageType.DENSE);

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
                assertEquals(61.0 * denseTestData[row][col], relPlusAbsTime.getSI(row, col), 0.01, "relPlusAbsTime");
            }
        }
        FloatTime floatTime = durationMatrix.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals(TimeUnit.EPOCH_DAY, floatTime.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, floatTime.si, 0.1, "Value of instantiateScalarAbsSI matches");

        FloatAngleMatrix angleMatrix = new FloatAngleMatrix(denseTestData, AngleUnit.DEGREE, StorageType.DENSE);
        FloatDirectionMatrix directionMatrix =
                new FloatDirectionMatrix(denseTestData, DirectionUnit.EAST_DEGREE, StorageType.DENSE);

        FloatDirectionMatrix relPlusAbsDirection = angleMatrix.plus(directionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(2.0 / 180 * Math.PI * denseTestData[row][col], relPlusAbsDirection.getSI(row, col), 0.01,
                        "relPlusAbsTime");
            }
        }
        FloatDirection floatDirection = angleMatrix.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals(DirectionUnit.NORTH_RADIAN, floatDirection.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, floatDirection.si, 0.1, "Value of instantiateScalarAbsSI matches");

        FloatTemperatureMatrix temperatureMatrix =
                new FloatTemperatureMatrix(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT, StorageType.DENSE);
        FloatAbsoluteTemperatureMatrix absoluteTemperatureMatrix =
                new FloatAbsoluteTemperatureMatrix(denseTestData, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);

        FloatAbsoluteTemperatureMatrix relPlusAbsTemperature = temperatureMatrix.plus(absoluteTemperatureMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals((1.0 + 5.0 / 9.0) * denseTestData[row][col], relPlusAbsTemperature.getSI(row, col), 0.01,
                        "relPlusAbsTime");
            }
        }
        FloatAbsoluteTemperature floatAbsoluteTemperature =
                temperatureMatrix.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT, floatAbsoluteTemperature.getDisplayUnit(),
                "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, floatAbsoluteTemperature.si, 0.1, "Value of instantiateScalarAbsSI matches");

        FloatLengthMatrix lengthMatrix = new FloatLengthMatrix(denseTestData, LengthUnit.MILE, StorageType.DENSE);
        FloatPositionMatrix positionMatrix = new FloatPositionMatrix(denseTestData, PositionUnit.KILOMETER, StorageType.DENSE);

        FloatPositionMatrix relPlusAbsPosition = lengthMatrix.plus(positionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(2609.344 * denseTestData[row][col], relPlusAbsPosition.getSI(row, col), 0.1, "relPlusAbsTime");
            }
        }
        FloatPosition floatPosition = lengthMatrix.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals(PositionUnit.ANGSTROM, floatPosition.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, floatPosition.si, 0.1, "Value of instantiateScalarAbsSI matches");
    }

    /**
     * Test the equals method.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals()
    {
        float[][] testData = FLOATMATRIX.denseRectArrays(12, 34, false);
        testData[2][2] = 0;
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatMatrixData fmd = FloatMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue(fmd.equals(fmd), "Float matrix is equal to itself");
            assertFalse(fmd.equals(null), "Float matrix is not equal to null");
            assertFalse(fmd.equals("some string"), "Float matrix data is not equal to some string");
            assertTrue(fmd.equals(fmd.toSparse()), "Float matrix is equal to sparse version of itself");
            assertTrue(fmd.equals(fmd.toDense()), "Float matrix is equal to dense version of itself");
            for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                FloatMatrixData dvd2 = FloatMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(fmd, dvd2,
                        "Float matrix data is equal to other double vector containing same values regardless of storage type");
                float[][] testData2 = FLOATMATRIX.denseRectArrays(12, 33, false);
                testData2[2][2] = 0;
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(fmd.equals(dvd2),
                        "Float matrix data is not equal to other double vector containing same values except last one");
                testData2 = FLOATMATRIX.denseRectArrays(13, 34, false);
                testData2[2][2] = 0;
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(fmd.equals(dvd2),
                        "Float matrix data is not equal to other double vector containing same values except last one");
                testData2 = FLOATMATRIX.denseRectArrays(12, 34, false);
                dvd2 = FloatMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(fmd.equals(dvd2),
                        "Float matrix data is not equal to other double vector containing same values except for one zero");
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
        assertEquals(2, dsv.getRow(), "row matches");
        assertEquals(3, dsv.getColumn(), "column matches");
        assertEquals(123000, dsv.getValueSI(), 0.1, "value matches");
        dsv = new FloatSparseValue(2, 3, 123.000f, LengthUnit.KILOMETER);
        assertEquals(2, dsv.getRow(), "row matches");
        assertEquals(3, dsv.getColumn(), "column matches");
        assertEquals(123000, dsv.getValueSI(), 0.1, "value matches");
    }

}
