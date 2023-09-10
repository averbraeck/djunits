package org.djunits.value.vdouble.matrix;

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
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.AreaVector;
import org.djunits.value.vfloat.matrix.FloatAreaMatrix;
import org.djunits.value.vfloat.vector.FLOATVECTOR;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * ...
 */
public class DoubleMatrixMethodTest
{

    /**
     * Test the standard methods of all matrix classes.
     * @throws UnitException on error
     * @throws ValueRuntimeException on error
     */
    @Test
    @SuppressWarnings("checkstyle:methodlength")
    public void testMatrixMethods() throws ValueRuntimeException, UnitException
    {
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(10, 20, false);
        double[][] sparseTestData = DOUBLEMATRIX.sparseRectArrays(10, 20, false);
        double[][] reverseSparseTestData = new double[sparseTestData.length][];
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
                sparseTestData[row][col] = 10000.456 + row + 100 * col;
                reverseSparseTestData[row][col] = 20000.567 + row + 100 * col;
            }
        }

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaMatrix am = new AreaMatrix(testData, au, storageType);

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
                assertNotEquals(am, new LengthMatrix(testData, LengthUnit.METER, storageType));
                assertNotEquals(am, am.divide(2.0d));

                // MUTABLE
                assertFalse(am.isMutable());
                AreaMatrix ammut = am.mutable();
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                AreaMatrix ammut2 = ammut.multiplyBy(1.0);
                assertEquals(am, ammut2);
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                assertTrue(ammut2.isMutable());
                ammut2 = ammut2.mutable().divideBy(2.0);
                assertEquals(am, ammut);
                assertNotEquals(am, ammut2);
                AreaMatrix ammut3 = ammut2.mutable().divideBy(0.0);
                for (int row = 0; row < ammut3.rows(); row++)
                {
                    for (int col = 0; col < ammut3.cols(); col++)
                    {
                        if (ammut2.getSI(row, col) == 0)
                        {
                            assertTrue(Double.isNaN(ammut3.getSI(row, col)), "Value should be NaN");

                        }
                        else
                        {
                            assertTrue(Double.isInfinite(ammut3.getSI(row, col)), "Value should be Infinite");
                        }
                    }
                }

                // ZSUM and CARDINALITY
                Area zSum = am.zSum();
                double sum = 0;
                int card = 0;
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        sum += testData[row][col];
                        card += testData[row][col] == 0.0d ? 0 : 1;
                    }
                }
                assertEquals(sum, zSum.getInUnit(), 0.1, "zSum");
                assertEquals(card, am.cardinality(), "cardinality");
                AreaMatrix ammutZero = ammut.multiplyBy(0.0);
                assertEquals(0, ammutZero.cardinality(), "cardinality should be 0");
                assertEquals(0.0, ammutZero.zSum().getSI(), 0, "zSum should be 0");

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                AreaMatrix amold = am.clone();
                Area fa = Area.of(10.0d, "m^2");
                AreaMatrix aminc = am.mutable().incrementBy(fa).immutable();
                AreaMatrix amdec = am.mutable().decrementBy(fa).immutable();
                AreaMatrix amid = aminc.mutable().decrementBy(fa);
                assertEquals(am, amold, "immutable matrix should not change when converted to mutable");
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals(am.getSI(row, col), amid.getSI(row, col), 0.1,
                                "increment and decrement with scalar should result in same matrix");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) + 10.0, aminc.getSI(row, col), 0.1,
                                "m + s = (m+s)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) - 10.0, amdec.getSI(row, col), 0.1,
                                "m - s = (m-s)");
                    }
                }

                // MULTIPLYBY() and DIVIDEBY(), TIMES(), DIVIDE()
                AreaMatrix amt5 = am.mutable().multiplyBy(5.0d).immutable();
                AreaMatrix amd5 = am.mutable().divideBy(5.0d).immutable();
                AreaMatrix amtd = amt5.mutable().divideBy(5.0d);
                AreaMatrix amtimD = am.times(5.0d);
                AreaMatrix amtimF = am.times(5.0f);
                AreaMatrix amdivD = am.divide(5.0d);
                AreaMatrix amdivF = am.divide(5.0f);
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals(am.getSI(row, col), amtd.getSI(row, col), 0.1,
                                "times followed by divide with constant should result in same matrix");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) * 5.0d, amt5.getSI(row, col), 0.1,
                                "m * 5.0 = (m*5.0)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) / 5.0d, amd5.getSI(row, col), 0.1,
                                "m / 5.0 = (m/5.0)");
                        assertEquals(amt5.getSI(row, col), amtimD.getSI(row, col), 0.1d, "amtimD");
                        assertEquals(amt5.getSI(row, col), amtimF.getSI(row, col), 0.1d, "amtimF");
                        assertEquals(amd5.getSI(row, col), amdivD.getSI(row, col), 0.01d, "amdivD");
                        assertEquals(amd5.getSI(row, col), amdivF.getSI(row, col), 0.01d, "amdivD");
                    }
                }

                // GET(), GETINUNIT()
                assertEquals(new Area(testData[2][2], au), am.get(2, 2), "get()");
                assertEquals(au.getScale().toStandardUnit(testData[2][2]), am.getSI(2, 2), 0.1, "getSI()");
                assertEquals(testData[2][2], am.getInUnit(2, 2), 0.1, "getInUnit()");
                assertEquals(AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2][2])),
                        am.getInUnit(2, 2, AreaUnit.SQUARE_YARD), 0.1, "getInUnit(unit)");

                // SET(), SETINUNIT()
                Area fasqft = new Area(10.5d, AreaUnit.SQUARE_FOOT);
                AreaMatrix famChange = am.clone().mutable();
                famChange.set(2, 2, fasqft);
                assertEquals(fasqft.si, famChange.get(2, 2).si, 0.1d, "set()");
                famChange = am.clone().mutable();
                famChange.setSI(2, 2, 123.4d);
                assertEquals(123.4d, famChange.get(2, 2).si, 0.1d, "setSI()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.2d);
                assertEquals(1.2d, famChange.getInUnit(2, 2), 0.1d, "setInUnit()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 2, 1.5d, AreaUnit.HECTARE);
                assertEquals(15000.0d, famChange.get(2, 2).si, 1.0d, "setInUnit(unit)");

                // GETROW(), GETCOLUMN(), GETDIAGONAL
                double[][] squareData = storageType.equals(StorageType.DENSE) ? DOUBLEMATRIX.denseRectArrays(12, 12, false)
                        : DOUBLEMATRIX.sparseRectArrays(12, 12, false);
                AreaMatrix amSquare = new AreaMatrix(squareData, au, storageType);
                double[] row2si = am.getRowSI(2);
                double[] col2si = am.getColumnSI(2);
                double[] diagsi = amSquare.getDiagonalSI();
                AreaVector row2v = am.getRow(2);
                AreaVector col2v = am.getColumn(2);
                AreaVector diagv = amSquare.getDiagonal();
                Area[] row2scalar = am.getRowScalars(2);
                Area[] col2scalar = am.getColumnScalars(2);
                Area[] diagscalar = amSquare.getDiagonalScalars();
                for (int col = 0; col < testData[0].length; col++)
                {
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2si[col], 0.1d, "row2si");
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2v.getSI(col), 0.1d, "row2v");
                    assertEquals(au.getScale().toStandardUnit(testData[2][col]), row2scalar[col].si, 0.1d, "row2scalar");
                }
                for (int row = 0; row < testData.length; row++)
                {
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2si[row], 0.1d, "col2si");
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2v.getSI(row), 0.1d, "col2v");
                    assertEquals(au.getScale().toStandardUnit(testData[row][2]), col2scalar[row].si, 0.1d, "col2scalar");
                }
                for (int diag = 0; diag < amSquare.rows(); diag++)
                {
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagsi[diag], 0.1d, "diag2si");
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagv.getSI(diag), 0.1d, "diag2v");
                    assertEquals(au.getScale().toStandardUnit(squareData[diag][diag]), diagscalar[diag].si, 0.1d,
                            "diag2scalar");
                }

                // GETVALUES(), GETSCALARS()
                double[][] valsi = am.getValuesSI();
                double[][] valunit = am.getValuesInUnit();
                double[][] valsqft = am.getValuesInUnit(AreaUnit.SQUARE_YARD);
                Area[][] valscalars = am.getScalars();
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]), valsi[row][col], 0.1, "getValuesSI()");
                        assertEquals(testData[row][col], valunit[row][col], 0.1, "getValuesInUnit()");
                        assertEquals(
                                AreaUnit.SQUARE_YARD.getScale()
                                        .fromStandardUnit(au.getScale().toStandardUnit(testData[row][col])),
                                valsqft[row][col], 0.1, "getValuesInUnit(unit)");
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]), valscalars[row][col].si, 0.1,
                                "getValuesInUnit(unit)");
                    }
                }

                // ASSIGN FUNCTION ABS, CEIL, FLOOR, NEG, RINT
                AreaMatrix amdiv2 = am.divide(2.0d);
                assertEquals(am.getStorageType(), amdiv2.getStorageType());
                assertEquals(am.getDisplayUnit(), amdiv2.getDisplayUnit());
                AreaMatrix amAbs = amdiv2.mutable().abs().immutable();
                assertEquals(am.getStorageType(), amAbs.getStorageType());
                assertEquals(am.getDisplayUnit(), amAbs.getDisplayUnit());
                AreaMatrix amCeil = amdiv2.mutable().ceil().immutable();
                assertEquals(am.getStorageType(), amCeil.getStorageType());
                assertEquals(am.getDisplayUnit(), amCeil.getDisplayUnit());
                AreaMatrix amFloor = amdiv2.mutable().floor().immutable();
                assertEquals(am.getStorageType(), amFloor.getStorageType());
                assertEquals(am.getDisplayUnit(), amFloor.getDisplayUnit());
                AreaMatrix amNeg = amdiv2.mutable().neg().immutable();
                assertEquals(am.getStorageType(), amNeg.getStorageType());
                assertEquals(am.getDisplayUnit(), amNeg.getDisplayUnit());
                AreaMatrix amRint = amdiv2.mutable().rint().immutable();
                assertEquals(am.getStorageType(), amRint.getStorageType());
                assertEquals(am.getDisplayUnit(), amRint.getDisplayUnit());
                for (int row = 0; row < testData.length; row++)
                {
                    for (int col = 0; col < testData[0].length; col++)
                    {
                        // TODO: Should be rounded IN THE UNIT rather than BY SI VALUES
                        assertEquals(au.getScale().toStandardUnit(testData[row][col]) / 2.0d, amdiv2.getSI(row, col), 0.1d,
                                "div2");
                        assertEquals(Math.abs(au.getScale().toStandardUnit(testData[row][col]) / 2.0d), amAbs.getSI(row, col),
                                0.1d, "abs");
                        assertEquals(Math.ceil(au.getScale().toStandardUnit(testData[row][col]) / 2.0d), amCeil.getSI(row, col),
                                0.1d, "ceil");
                        assertEquals(Math.floor(au.getScale().toStandardUnit(testData[row][col]) / 2.0d),
                                amFloor.getSI(row, col), 0.1d, "floor");
                        assertEquals(-au.getScale().toStandardUnit(testData[row][col]) / 2.0d, amNeg.getSI(row, col), 0.1d,
                                "neg");
                        assertEquals(Math.rint(au.getScale().toStandardUnit(testData[row][col]) / 2.0d), amRint.getSI(row, col),
                                0.1d, "rint");
                    }
                }

                double[][] testData4x4 = new double[][] {{2, 3, 5, 7}, {11, 13, 17, 19}, {23, 29, 31, 37}, {41, 43, 47, 49}};
                AreaMatrix am4x4 = new AreaMatrix(testData4x4, au, storageType);
                double det = am4x4.determinantSI();
                double detCalc = Determinant.det(am4x4.getValuesSI());
                double err = Math.max(det, detCalc) / 10000.0;
                assertEquals(detCalc, det, err, "Determinant of square matrix with unit " + au.getDefaultTextualAbbreviation()
                        + ", storage = " + storageType + " = " + det + " but should have been " + detCalc);
                Try.testFail(() -> am.determinantSI(), "Determinant of non-square matrix should have thrown exception");

                // TEST METHODS THAT INVOLVE TWO MATRIX INSTANCES

                for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    double[][] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
                    {

                        // PLUS and INCREMENTBY(MATRIX)
                        AreaMatrix am2 = new AreaMatrix(testData2, au2, storageType2);
                        AreaMatrix amSum1 = am.plus(am2);
                        AreaMatrix amSum2 = am2.plus(am);
                        AreaMatrix amSum3 = am.mutable().incrementBy(am2).immutable();
                        AreaMatrix amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals(amSum1, amSum2, "a+b == b+a");
                        assertEquals(amSum1, amSum3, "a+b == b+a");
                        assertEquals(amSum1, amSum4, "a+b == b+a");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                double tolerance = Double.isFinite(amSum1.getSI(row, col))
                                        ? Math.abs(amSum1.getSI(row, col) / 10000.0d) : 0.1d;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                + au2.getScale().toStandardUnit(testData2[row][col]),
                                        amSum1.getSI(row, col), tolerance, "value in matrix matches");
                            }
                        }

                        // MINUS and DECREMENTBY(MATRIX)
                        AreaMatrix amDiff1 = am.minus(am2);
                        AreaMatrix amDiff2 = am2.minus(am).mutable().neg();
                        AreaMatrix amDiff3 = am.mutable().decrementBy(am2).immutable();
                        assertEquals(amDiff1, amDiff2, "a-b == -(b-a)");
                        assertEquals(amDiff1, amDiff3, "a-b == -(b-a)");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                double tolerance = Double.isFinite(amDiff1.getSI(row, col))
                                        ? Math.abs(amDiff1.getSI(row, col) / 10000.0d) : 0.1d;
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                - au2.getScale().toStandardUnit(testData2[row][col]),
                                        amDiff1.getSI(row, col), tolerance, "value in matrix matches");
                            }
                        }

                        // TIMES(MATRIX) and DIVIDE(MATRIX)
                        SIMatrix amTim = am.times(am2);
                        SIMatrix amDiv = am.divide(am2);
                        assertEquals("m4", amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 * m2 should be m4");
                        assertEquals("", amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 / m2 should be empty string");
                        for (int row = 0; row < testData.length; row++)
                        {
                            for (int col = 0; col < testData[0].length; col++)
                            {
                                double tolerance = Double.isFinite(amTim.getSI(row, col))
                                        ? Math.abs(amTim.getSI(row, col) / 10000.0d) : 0.1d;
                                if (Math.abs(au.getScale().toStandardUnit(testData[row][col])
                                        * au2.getScale().toStandardUnit(testData2[row][col])
                                        - amTim.getSI(row, col)) > tolerance)
                                {
                                    // system.out.println(
                                    // "mismatch at row " + row + ", col " + col + ", got " + amTim.getSI(row, col)
                                    // + ", expected " + au.getScale().toStandardUnit(testData[row][col])
                                    // * au2.getScale().toStandardUnit(testData2[row][col]));
                                    am.times(am2);
                                }
                                assertEquals(
                                        au.getScale().toStandardUnit(testData[row][col])
                                                * au2.getScale().toStandardUnit(testData2[row][col]),
                                        amTim.getSI(row, col), tolerance, "value in m2 * m2 matches");
                                tolerance = Double.isFinite(amDiv.getSI(row, col)) ? Math.abs(amDiv.getSI(row, col) / 10000.0d)
                                        : 0.1d;
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
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(5, 10, false);
        double[][] sparseTestData = DOUBLEMATRIX.sparseRectArrays(5, 10, false);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaMatrix am = new AreaMatrix(testData, au, storageType);
                am = am.immutable();
                final AreaMatrix amPtr = am;
                Area fa = Area.of(10.0d, "m^2");
                Try.testFail(() -> amPtr.assign(DoubleMathFunctions.ABS), "ImmutableMatrix.assign(...) should throw error");
                Try.testFail(() -> amPtr.decrementBy(fa), "ImmutableMatrix.decrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.decrementBy(amPtr), "ImmutableMatrix.decrementBy(matrix) should throw error");
                Try.testFail(() -> amPtr.incrementBy(fa), "ImmutableMatrix.incrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.incrementBy(amPtr), "ImmutableMatrix.incrementBy(matrix) should throw error");
                Try.testFail(() -> amPtr.divideBy(2.0d), "ImmutableMatrix.divideBy(factor) should throw error");
                Try.testFail(() -> amPtr.multiplyBy(2.0d), "ImmutableMatrix.multiplyBy(factor) should throw error");
                Try.testFail(() -> amPtr.set(1, 1, fa), "ImmutableMatrix.set() should throw error");
                Try.testFail(() -> amPtr.setSI(1, 1, 20.1d), "ImmutableMatrix.setSI() should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 1, 15.2d), "ImmutableMatrix.setInUnit(f) should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 1, 15.2d, AreaUnit.ARE),
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
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(5, 10, false);
        double[][] sparseTestData = DOUBLEMATRIX.sparseRectArrays(5, 10, false);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[][] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaMatrix am = new AreaMatrix(testData, au, storageType);
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
                AreaMatrix ammut = am.mutable();
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
        TimeMatrix tm = new TimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        LengthMatrix lm = new LengthMatrix(denseTestData, LengthUnit.SI, StorageType.DENSE);
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
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(5, 10, false);
        TimeMatrix tm = new TimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        DurationMatrix dm = new DurationMatrix(denseTestData, DurationUnit.MINUTE, StorageType.DENSE);
        assertTrue(tm.isAbsolute());
        assertFalse(dm.isAbsolute());
        assertFalse(tm.isRelative());
        assertTrue(dm.isRelative());

        TimeMatrix absPlusRel = tm.plus(dm);
        TimeMatrix absMinusRel = tm.minus(dm);
        double[][] halfDenseData = DOUBLEMATRIX.denseRectArrays(5, 10, false);
        for (int row = 0; row < halfDenseData.length; row++)
        {
            for (int col = 0; col < halfDenseData[row].length; col++)
            {
                halfDenseData[row][col] *= 0.5;
            }
        }
        TimeMatrix halfTimeMatrix = new TimeMatrix(halfDenseData, TimeUnit.DEFAULT, StorageType.DENSE);
        DurationMatrix absMinusAbs = tm.minus(halfTimeMatrix);
        TimeMatrix absDecByRelS = tm.mutable().decrementBy(Duration.of(1.0d, "min"));
        TimeMatrix absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0d));
        TimeMatrix relPlusAbs = dm.plus(tm);
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
                double[][] other =
                        DOUBLEMATRIX.denseRectArrays(denseTestData.length + dRows, denseTestData[0].length + dCols, false);
                TimeMatrix wrongTimeMatrix = new TimeMatrix(other, TimeUnit.DEFAULT, StorageType.DENSE);
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
        assertTrue(DoubleMatrixData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                .startsWith("DoubleMatrixData"), "toString returns something informative");
    }

    /**
     * Execute a mats++-like memory test on a sparse matrix. See
     * <a href="http://www.eng.auburn.edu/~agrawvd/COURSE/E7250_05/REPORTS_TERM/Raghuraman_Mem.doc">Memory Test</a>.
     */
    @Test
    public void memoryTest()
    {
        FloatAreaMatrix am = new FloatAreaMatrix(new float[5][10], AreaUnit.SI, StorageType.SPARSE);
        am = am.mutable();
        float nonZeroValue = 123.456f;
        // Initially the array is filled with zero values; we can skip the initialization phase
        for (int compoundIndex = 0; compoundIndex < am.cols() * am.rows(); compoundIndex++)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals(0f, am.getSI(row, col), 0.0001, "initial value is 0");
            am.setSI(row, col, nonZeroValue);
            assertEquals(nonZeroValue, am.getSI(row, col), 0.0001, "current value is nonZero");
        }
        for (int compoundIndex = am.cols() * am.rows(); --compoundIndex >= 0;)
        {
            // Let the row count fastest
            int row = compoundIndex % am.rows();
            int col = compoundIndex / am.rows();
            assertEquals(nonZeroValue, am.getSI(row, col), 0.0001, "current value is nonZero");
            am.setSI(row, col, 0f);
            assertEquals(0f, am.getSI(row, col), 0.0001, "final value is 0");
        }
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(10, 20, false);
        TimeMatrix timeMatrix = new TimeMatrix(denseTestData, TimeUnit.DEFAULT, StorageType.DENSE);
        DurationMatrix durationMatrix = new DurationMatrix(denseTestData, DurationUnit.MINUTE, StorageType.DENSE);

        float[] halfDenseData = FLOATVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        TimeMatrix relPlusAbsTime = durationMatrix.plus(timeMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(61.0 * denseTestData[row][col], relPlusAbsTime.getSI(row, col), 0.01, "relPlusAbsTime");
            }
        }
        Time time = durationMatrix.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals(TimeUnit.EPOCH_DAY, time.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, time.si, 0.1, "Value of instantiateScalarAbsSI matches");

        AngleMatrix angleMatrix = new AngleMatrix(denseTestData, AngleUnit.DEGREE, StorageType.DENSE);
        DirectionMatrix directionMatrix = new DirectionMatrix(denseTestData, DirectionUnit.EAST_DEGREE, StorageType.DENSE);

        DirectionMatrix relPlusAbsDirection = angleMatrix.plus(directionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(2.0 / 180 * Math.PI * denseTestData[row][col], relPlusAbsDirection.getSI(row, col), 0.01,
                        "relPlusAbsTime");
            }
        }
        Direction direction = angleMatrix.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals(DirectionUnit.NORTH_RADIAN, direction.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, direction.si, 0.1, "Value of instantiateScalarAbsSI matches");

        TemperatureMatrix temperatureMatrix =
                new TemperatureMatrix(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT, StorageType.DENSE);
        AbsoluteTemperatureMatrix absoluteTemperatureMatrix =
                new AbsoluteTemperatureMatrix(denseTestData, AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);

        AbsoluteTemperatureMatrix relPlusAbsTemperature = temperatureMatrix.plus(absoluteTemperatureMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals((1.0 + 5.0 / 9.0) * denseTestData[row][col], relPlusAbsTemperature.getSI(row, col), 0.01,
                        "relPlusAbsTime");
            }
        }
        AbsoluteTemperature absoluteTemperature =
                temperatureMatrix.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT, absoluteTemperature.getDisplayUnit(),
                "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, absoluteTemperature.si, 0.1, "Value of instantiateScalarAbsSI matches");

        LengthMatrix lengthMatrix = new LengthMatrix(denseTestData, LengthUnit.MILE, StorageType.DENSE);
        PositionMatrix positionMatrix = new PositionMatrix(denseTestData, PositionUnit.KILOMETER, StorageType.DENSE);

        PositionMatrix relPlusAbsPosition = lengthMatrix.plus(positionMatrix);
        for (int row = 0; row < denseTestData.length; row++)
        {
            for (int col = 0; col < denseTestData[0].length; col++)
            {
                assertEquals(2609.344 * denseTestData[row][col], relPlusAbsPosition.getSI(row, col), 0.1, "relPlusAbsTime");
            }
        }
        Position position = lengthMatrix.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals(PositionUnit.ANGSTROM, position.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, position.si, 0.01, "Value of instantiateScalarAbsSI matches");
    }

    /**
     * Test the equals method.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals()
    {
        double[][] testData = DOUBLEMATRIX.denseRectArrays(12, 34, false);
        testData[2][2] = 0;
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            DoubleMatrixData dmd = DoubleMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue(dmd.equals(dmd), "Double matrix is equal to itself");
            assertFalse(dmd.equals(null), "Double matrix is not equal to null");
            assertFalse(dmd.equals("some string"), "Double matrix data is not equal to some string");
            assertTrue(dmd.equals(dmd.toSparse()), "Double matrix is equal to sparse version of itself");
            assertTrue(dmd.equals(dmd.toDense()), "Double matrix is equal to dense version of itself");
            for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                DoubleMatrixData dvd2 = DoubleMatrixData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(dmd, dvd2,
                        "Double matrix data is equal to other double vector containing same values regardless of storage type");
                double[][] testData2 = DOUBLEMATRIX.denseRectArrays(12, 33, false);
                testData2[2][2] = 0;
                dvd2 = DoubleMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(dmd.equals(dvd2),
                        "Double matrix data is not equal to other double vector containing same values except last one");
                testData2 = DOUBLEMATRIX.denseRectArrays(13, 34, false);
                testData2[2][2] = 0;
                dvd2 = DoubleMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(dmd.equals(dvd2),
                        "Double matrix data is not equal to other double vector containing same values except last one");
                testData2 = DOUBLEMATRIX.denseRectArrays(12, 34, false);
                dvd2 = DoubleMatrixData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(dmd.equals(dvd2),
                        "Double matrix data is not equal to other double vector containing same values except for one zero");
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
            new DoubleSparseValue(-1, 0, 123.456);
            fail("Negative row should have caused a ValueRuntimeException");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        try
        {
            new DoubleSparseValue(0, -1, 123.456);
            fail("Negative column should have caused a ValueRuntimeException");
        }
        catch (ValueRuntimeException vre)
        {
            // Ignore expected exception
        }

        Length length = Length.valueOf("123 km");
        DoubleSparseValue dsv = new DoubleSparseValue(2, 3, length);
        assertEquals(2, dsv.getRow(), "row matches");
        assertEquals(3, dsv.getColumn(), "column matches");
        assertEquals(123000, dsv.getValueSI(), 0.1, "value matches");
        dsv = new DoubleSparseValue(2, 3, 123.000, LengthUnit.KILOMETER);
        assertEquals(2, dsv.getRow(), "row matches");
        assertEquals(3, dsv.getColumn(), "column matches");
        assertEquals(123000, dsv.getValueSI(), 0.1, "value matches");
    }

}
