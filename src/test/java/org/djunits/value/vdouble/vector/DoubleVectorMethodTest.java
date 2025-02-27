package org.djunits.value.vdouble.vector;

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
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.djutils.exceptions.Try;
import org.junit.jupiter.api.Test;

/**
 * Test the incrementBy, etc. methods.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class DoubleVectorMethodTest
{

    /**
     * Test the standard methods of all vector classes.
     * @throws UnitException on error
     * @throws ValueRuntimeException on error
     */
    @Test
    public void testVectorMethods() throws ValueRuntimeException, UnitException
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        double[] sparseTestData = DOUBLEVECTOR.sparseArray(105);
        double[] reverseSparseTestData = new double[sparseTestData.length];
        // sparseTestData and reverseSparseTestData should not "run out of values" at the same index
        for (int index = 0; index < sparseTestData.length; index++)
        {
            reverseSparseTestData[reverseSparseTestData.length - 1 - index] = sparseTestData[index];
        }
        // Ensure that both have a value at some index (i.c. 10)
        sparseTestData[10] = 123.456;
        reverseSparseTestData[10] = sparseTestData[10];
        // Ensure that there are > 50% positions where both have a non-zero value
        for (int index = 20; index < 90; index++)
        {
            sparseTestData[index] = 10000.456 + index;
            reverseSparseTestData[index] = 20000.567 + index;
        }
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am = new AreaVector(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);

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
                assertNotEquals(am, new LengthVector(
                        DoubleVectorData.instantiate(testData, LengthUnit.METER.getScale(), storageType), LengthUnit.METER));
                assertNotEquals(am, am.divide(2.0d));

                // MUTABLE
                assertFalse(am.isMutable());
                AreaVector ammut = am.mutable();
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                AreaVector ammut2 = ammut.multiplyBy(1.0);
                assertEquals(am, ammut2);
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                assertTrue(ammut2.isMutable());
                ammut2 = ammut2.mutable().divideBy(2.0);
                assertEquals(am, ammut);
                assertNotEquals(am, ammut2);
                AreaVector ammut3 = ammut2.mutable().divideBy(0.0);
                for (int index = 0; index < ammut3.size(); index++)
                {
                    if (ammut2.getSI(index) == 0)
                    {
                        assertTrue(Double.isNaN(ammut3.getSI(index)), "Value should be NaN");

                    }
                    else
                    {
                        assertTrue(Double.isInfinite(ammut3.getSI(index)), "Value should be Infinite");
                    }
                }

                // ZSUM and CARDINALITY
                Area zSum = am.zSum();
                double sum = 0;
                int card = 0;
                for (int index = 0; index < testData.length; index++)
                {
                    sum += testData[index];
                    card += testData[index] == 0.0d ? 0 : 1;
                }
                assertEquals(sum, zSum.getInUnit(), 0.1, "zSum");
                assertEquals(card, am.cardinality(), "cardinality");

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                AreaVector amold = am.clone();
                Area fa = Area.of(10.0d, "m^2");
                AreaVector aminc = am.mutable().incrementBy(fa).immutable();
                AreaVector amdec = am.mutable().decrementBy(fa).immutable();
                AreaVector amid = aminc.mutable().decrementBy(fa);
                assertEquals(am, amold, "immutable vector should not change when converted to mutable");
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals(am.getSI(index), amid.getSI(index), 0.1,
                            "increment and decrement with scalar should result in same vector");
                    assertEquals(au.getScale().toStandardUnit(testData[index]) + 10.0, aminc.getSI(index), 0.1,
                            "m + s = (m+s)");
                    assertEquals(au.getScale().toStandardUnit(testData[index]) - 10.0, amdec.getSI(index), 0.1,
                            "m - s = (m-s)");
                }

                // MULTIPLYBY() and DIVIDEBY(), TIMES(), DIVIDE()
                AreaVector amt5 = am.mutable().multiplyBy(5.0d).immutable();
                AreaVector amd5 = am.mutable().divideBy(5.0d).immutable();
                AreaVector amtd = amt5.mutable().divideBy(5.0d);
                AreaVector amtimD = am.times(5.0d);
                AreaVector amtimF = am.times(5.0f);
                AreaVector amdivD = am.divide(5.0d);
                AreaVector amdivF = am.divide(5.0f);
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals(am.getSI(index), amtd.getSI(index), 0.1,
                            "times followed by divide with constant should result in same vector");
                    assertEquals(au.getScale().toStandardUnit(testData[index]) * 5.0d, amt5.getSI(index), 0.1,
                            "m * 5.0 = (m*5.0)");
                    assertEquals(au.getScale().toStandardUnit(testData[index]) / 5.0d, amd5.getSI(index), 0.1,
                            "m / 5.0 = (m/5.0)");
                    assertEquals(amt5.getSI(index), amtimD.getSI(index), 0.1d, "amtimD");
                    assertEquals(amt5.getSI(index), amtimF.getSI(index), 0.1d, "amtimF");
                    assertEquals(amd5.getSI(index), amdivD.getSI(index), 0.01d, "amdivD");
                    assertEquals(amd5.getSI(index), amdivF.getSI(index), 0.01d, "amdivD");
                }

                // GET(), GETINUNIT()
                assertEquals(new Area(testData[2], au), am.get(2), "get()");
                assertEquals(au.getScale().toStandardUnit(testData[2]), am.getSI(2), 0.1, "getSI()");
                assertEquals(testData[2], am.getInUnit(2), 0.1, "getInUnit()");
                assertEquals(AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2])),
                        am.getInUnit(2, AreaUnit.SQUARE_YARD), 0.1, "getInUnit(unit)");

                // SET(), SETINUNIT()
                Area fasqft = new Area(10.5d, AreaUnit.SQUARE_FOOT);
                AreaVector famChange = am.clone().mutable();
                famChange.set(2, fasqft);
                assertEquals(fasqft.si, famChange.get(2).si, 0.1d, "set()");
                famChange = am.clone().mutable();
                famChange.setSI(2, 123.4d);
                assertEquals(123.4d, famChange.get(2).si, 0.1d, "setSI()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.2d);
                assertEquals(1.2d, famChange.getInUnit(2), 0.1d, "setInUnit()");
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.5d, AreaUnit.HECTARE);
                assertEquals(15000.0d, famChange.get(2).si, 1.0d, "setInUnit(unit)");

                // GETVALUES(), GETSCALARS()
                double[] valsi = am.getValuesSI();
                double[] valunit = am.getValuesInUnit();
                double[] valsqft = am.getValuesInUnit(AreaUnit.SQUARE_YARD);
                Area[] valscalars = am.getScalars();
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals(au.getScale().toStandardUnit(testData[index]), valsi[index], 0.1, "getValuesSI()");
                    assertEquals(testData[index], valunit[index], 0.1, "getValuesInUnit()");
                    assertEquals(
                            AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[index])),
                            valsqft[index], 0.1, "getValuesInUnit(unit)");
                    assertEquals(au.getScale().toStandardUnit(testData[index]), valscalars[index].si, 0.1,
                            "getValuesInUnit(unit)");
                }

                // ASSIGN FUNCTION ABS, CEIL, FLOOR, NEG, RINT
                AreaVector amdiv2 = am.divide(2.0d);
                assertEquals(am.getStorageType(), amdiv2.getStorageType());
                assertEquals(am.getDisplayUnit(), amdiv2.getDisplayUnit());
                AreaVector amAbs = amdiv2.mutable().abs().immutable();
                assertEquals(am.getStorageType(), amAbs.getStorageType());
                assertEquals(am.getDisplayUnit(), amAbs.getDisplayUnit());
                AreaVector amCeil = amdiv2.mutable().ceil().immutable();
                assertEquals(am.getStorageType(), amCeil.getStorageType());
                assertEquals(am.getDisplayUnit(), amCeil.getDisplayUnit());
                AreaVector amFloor = amdiv2.mutable().floor().immutable();
                assertEquals(am.getStorageType(), amFloor.getStorageType());
                assertEquals(am.getDisplayUnit(), amFloor.getDisplayUnit());
                AreaVector amNeg = amdiv2.mutable().neg().immutable();
                assertEquals(am.getStorageType(), amNeg.getStorageType());
                assertEquals(am.getDisplayUnit(), amNeg.getDisplayUnit());
                AreaVector amRint = amdiv2.mutable().rint().immutable();
                assertEquals(am.getStorageType(), amRint.getStorageType());
                assertEquals(am.getDisplayUnit(), amRint.getDisplayUnit());
                for (int index = 0; index < testData.length; index++)
                {
                    // TODO: Should be rounded IN THE UNIT rather than BY SI VALUES
                    assertEquals(au.getScale().toStandardUnit(testData[index]) / 2.0d, amdiv2.getSI(index), 0.1d, "div2");
                    assertEquals(Math.abs(au.getScale().toStandardUnit(testData[index]) / 2.0d), amAbs.getSI(index), 0.1d,
                            "abs");
                    assertEquals(Math.ceil(au.getScale().toStandardUnit(testData[index]) / 2.0d), amCeil.getSI(index), 0.1d,
                            "ceil");
                    assertEquals(Math.floor(au.getScale().toStandardUnit(testData[index]) / 2.0d), amFloor.getSI(index), 0.1d,
                            "floor");
                    assertEquals(-au.getScale().toStandardUnit(testData[index]) / 2.0d, amNeg.getSI(index), 0.1d, "neg");
                    assertEquals(Math.rint(au.getScale().toStandardUnit(testData[index]) / 2.0d), amRint.getSI(index), 0.1d,
                            "rint");
                }

                // TEST METHODS THAT INVOLVE TWO VECTOR INSTANCES

                for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    double[] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
                    {
                        // PLUS and INCREMENTBY(VECTOR)
                        AreaVector am2 =
                                new AreaVector(DoubleVectorData.instantiate(testData2, au2.getScale(), storageType2), au2);
                        AreaVector amSum1 = am.plus(am2);
                        AreaVector amSum2 = am2.plus(am);
                        AreaVector amSum3 = am.mutable().incrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        AreaVector amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals(amSum1, amSum2, "a+b == b+a");
                        assertEquals(amSum1, amSum3, "a+b == b+a");
                        assertEquals(amSum1, amSum4, "a+c == c+a");
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amSum1.getSI(index)) ? Math.abs(amSum1.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals(
                                    au.getScale().toStandardUnit(testData[index])
                                            + au2.getScale().toStandardUnit(testData2[index]),
                                    amSum1.getSI(index), tolerance, "value in vector matches");
                        }

                        // MINUS and DECREMENTBY(VECTOR)
                        AreaVector amDiff1 = am.minus(am2);
                        AreaVector amDiff2 = am2.minus(am).mutable().neg();
                        AreaVector amDiff3 = am.mutable().decrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        AreaVector amDiff4 = am2.mutable().decrementBy(am).neg().immutable();
                        assertEquals(amDiff1, amDiff2, "a-b == -(b-a)");
                        assertEquals(amDiff1, amDiff3, "a-b == -(b-a)");
                        assertEquals(amDiff1, amDiff4, "a-c == -(c-a)");
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amDiff1.getSI(index)) ? Math.abs(amDiff1.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals(
                                    au.getScale().toStandardUnit(testData[index])
                                            - au2.getScale().toStandardUnit(testData2[index]),
                                    amDiff1.getSI(index), tolerance, "value in vector matches");
                        }

                        // TIMES(VECTOR) and DIVIDE(VECTOR)
                        SIVector amTim = am.times(am2);
                        SIVector amDiv = am.divide(am2);
                        assertEquals("m4", amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 * m2 should be m4");
                        assertEquals("", amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false),
                                "unit of m2 / m2 should be empty string");
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amTim.getSI(index)) ? Math.abs(amTim.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals(
                                    au.getScale().toStandardUnit(testData[index])
                                            * au2.getScale().toStandardUnit(testData2[index]),
                                    amTim.getSI(index), tolerance, "value in m2 * m2 matches");
                            tolerance = Double.isFinite(amDiv.getSI(index)) ? Math.abs(amDiv.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals(
                                    au.getScale().toStandardUnit(testData[index])
                                            / au2.getScale().toStandardUnit(testData2[index]),
                                    amDiv.getSI(index), tolerance, "value in m2 / m2 matches (could be NaN)");
                        }
                        // This does not compile: SIVector amTim2 = am.immutable().multiplyBy(am2).immutable();
                    }
                }
            }
        }
    }

    /**
     * Test if mutable methods give an error in case the vector is immutable.
     */
    @Test
    public void testImmutableVector()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        double[] sparseTestData = DOUBLEVECTOR.sparseArray(105);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am = new AreaVector(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);
                am = am.immutable();
                final AreaVector amPtr = am;
                Area fa = Area.of(10.0d, "m^2");
                Try.testFail(() -> amPtr.assign(DoubleMathFunctions.ABS), "ImmutableVector.assign(...) should throw error");
                Try.testFail(() -> amPtr.decrementBy(fa), "ImmutableVector.decrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.decrementBy(amPtr), "ImmutableVector.decrementBy(vector) should throw error");
                Try.testFail(() -> amPtr.incrementBy(fa), "ImmutableVector.incrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.incrementBy(amPtr), "ImmutableVector.incrementBy(vector) should throw error");
                Try.testFail(() -> amPtr.divideBy(2.0d), "ImmutableVector.divideBy(factor) should throw error");
                Try.testFail(() -> amPtr.multiplyBy(2.0d), "ImmutableVector.multiplyBy(factor) should throw error");
                Try.testFail(() -> amPtr.set(1, fa), "ImmutableVector.set() should throw error");
                Try.testFail(() -> amPtr.setSI(1, 20.1d), "ImmutableVector.setSI() should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 15.2d), "ImmutableVector.setInUnit(f) should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 15.2d, AreaUnit.ARE),
                        "ImmutableVector.setInUnit(f, u) should throw error");
                Try.testFail(() -> amPtr.abs(), "ImmutableVector.abs() should throw error");
                Try.testFail(() -> amPtr.ceil(), "ImmutableVector.ceil() should throw error");
                Try.testFail(() -> amPtr.floor(), "ImmutableVector.floor() should throw error");
                Try.testFail(() -> amPtr.neg(), "ImmutableVector.neg() should throw error");
                Try.testFail(() -> amPtr.rint(), "ImmutableVector.rint() should throw error");
            }
        }
    }

    /**
     * Test toString() methods. TODO: expand?
     */
    @Test
    public void testVectorToString()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        double[] sparseTestData = DOUBLEVECTOR.sparseArray(105);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am = new AreaVector(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);
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
                AreaVector ammut = am.mutable();
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
        TimeVector tm = new TimeVector(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        LengthVector lm = new LengthVector(
                DoubleVectorData.instantiate(denseTestData, LengthUnit.SI.getScale(), StorageType.DENSE), LengthUnit.SI);
        String sl = lm.toString(LengthUnit.SI, true, true); // verbose with unit
        assertTrue(sl.contains("Rel"));
        assertFalse(sl.contains("Abs"));
    }

    /**
     * Test the extra methods that Absolute and Relative with Absolute matrices implement.
     */
    @Test
    public void testSpecialVectorMethodsRelWithAbs()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        TimeVector tm = new TimeVector(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector dm = new DurationVector(
                DoubleVectorData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);
        assertTrue(tm.isAbsolute());
        assertFalse(dm.isAbsolute());
        assertFalse(tm.isRelative());
        assertTrue(dm.isRelative());

        TimeVector absPlusRel = tm.plus(dm);
        TimeVector absMinusRel = tm.minus(dm);
        double[] halfDenseData = DOUBLEVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        TimeVector halfTimeVector = new TimeVector(
                DoubleVectorData.instantiate(halfDenseData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector absMinusAbs = tm.minus(halfTimeVector);
        TimeVector absDecByRelS = tm.mutable().decrementBy(Duration.of(1.0d, "min"));
        TimeVector absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0d));
        TimeVector relPlusAbs = dm.plus(tm);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals(61.0 * denseTestData[index], absPlusRel.getSI(index), 0.01, "absPlusRel");
            assertEquals(-59.0 * denseTestData[index], absMinusRel.getSI(index), 0.01, "absMinusRel");
            assertEquals(denseTestData[index] / 2.0, absMinusAbs.getSI(index), 0.01, "absMinusAbs");
            assertEquals(denseTestData[index] - 60.0, absDecByRelS.getSI(index), 0.01, "absDecByRelS");
            assertEquals(-29.0 * denseTestData[index], absDecByRelM.getSI(index), 0.01, "absDecByRelM");
            assertEquals(61.0 * denseTestData[index], relPlusAbs.getSI(index), 0.01, "relPlusAbs");
        }
        for (int dLength : new int[] {-1, 1})
        {
            double[] other = DOUBLEVECTOR.denseArray(denseTestData.length + dLength);
            TimeVector wrongTimeVector = new TimeVector(
                    DoubleVectorData.instantiate(other, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
            try
            {
                tm.mutable().minus(wrongTimeVector);
                fail("Mismatching size should have thrown a ValueRuntimeException");
            }
            catch (ValueRuntimeException vre)
            {
                // Ignore expected exception
            }
        }
        assertTrue(DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                .startsWith("DoubleVectorData"), "toString returns something informative");
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        TimeVector timeVector = new TimeVector(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector durationVector = new DurationVector(
                DoubleVectorData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);

        double[] halfDenseData = DOUBLEVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        TimeVector relPlusAbsTime = durationVector.plus(timeVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals(61.0 * denseTestData[index], relPlusAbsTime.getSI(index), 0.01, "relPlusAbsTime");
        }
        Time time = durationVector.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals(TimeUnit.EPOCH_DAY, time.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, time.si, 0.1, "Value of instantiateScalarAbsSI matches");

        AngleVector angleVector = new AngleVector(
                DoubleVectorData.instantiate(denseTestData, AngleUnit.DEGREE.getScale(), StorageType.DENSE), AngleUnit.DEGREE);
        DirectionVector directionVector = new DirectionVector(
                DoubleVectorData.instantiate(denseTestData, DirectionUnit.EAST_DEGREE.getScale(), StorageType.DENSE),
                DirectionUnit.EAST_DEGREE);

        DirectionVector relPlusAbsDirection = angleVector.plus(directionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals(2.0 / 180 * Math.PI * denseTestData[index], relPlusAbsDirection.getSI(index), 0.01,
                    "relPlusAbsDirection");
        }
        Direction direction = angleVector.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals(DirectionUnit.NORTH_RADIAN, direction.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, direction.si, 0.1, "Value of instantiateScalarAbsSI matches");

        TemperatureVector temperatureVector = new TemperatureVector(
                DoubleVectorData.instantiate(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT.getScale(), StorageType.DENSE),
                TemperatureUnit.DEGREE_FAHRENHEIT);
        AbsoluteTemperatureVector absoluteTemperatureVector = new AbsoluteTemperatureVector(
                DoubleVectorData.instantiate(denseTestData, AbsoluteTemperatureUnit.KELVIN.getScale(), StorageType.DENSE),
                AbsoluteTemperatureUnit.KELVIN);

        AbsoluteTemperatureVector relPlusAbsTemperature = temperatureVector.plus(absoluteTemperatureVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals((1.0 + 5.0 / 9.0) * denseTestData[index], relPlusAbsTemperature.getSI(index), 0.01,
                    "relPlusAbsTemperature");
        }
        AbsoluteTemperature absoluteTemperature =
                temperatureVector.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT, absoluteTemperature.getDisplayUnit(),
                "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, absoluteTemperature.si, 0.1, "Value of instantiateScalarAbsSI matches");

        LengthVector lengthVector = new LengthVector(
                DoubleVectorData.instantiate(denseTestData, LengthUnit.MILE.getScale(), StorageType.DENSE), LengthUnit.MILE);
        PositionVector positionVector = new PositionVector(
                DoubleVectorData.instantiate(denseTestData, PositionUnit.KILOMETER.getScale(), StorageType.DENSE),
                PositionUnit.KILOMETER);

        PositionVector relPlusAbsPosition = lengthVector.plus(positionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals(2609.344 * denseTestData[index], relPlusAbsPosition.getSI(index), 1, "relPlusAbsPosition");
        }
        Position position = lengthVector.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals(PositionUnit.ANGSTROM, position.getDisplayUnit(), "Unit of instantiateScalarAbsSI matches");
        assertEquals(123.456f, position.si, 0.1, "Value of instantiateScalarAbsSI matches");
    }

    /**
     * Test the equals method.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals()
    {
        double[] testData = DOUBLEVECTOR.denseArray(123);
        testData[2] = 0;
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            DoubleVectorData dvd = DoubleVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue(dvd.equals(dvd), "Double vector data is equal to itself");
            assertFalse(dvd.equals(null), "Double vector data is not equal to null");
            assertFalse(dvd.equals("some string"), "Double vector data is not equal to some string");
            assertTrue(dvd.equals(dvd.toSparse()), "Double vector is equal to sparse version of itself");
            assertTrue(dvd.equals(dvd.toDense()), "Double vector is equal to dense version of itself");
            for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                DoubleVectorData dvd2 = DoubleVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(dvd, dvd2,
                        "Double vector data is equal to other double vector containing same values regardless of storage type");
                double[] testData2 = DOUBLEVECTOR.denseArray(122);
                testData2[2] = 0;
                dvd2 = DoubleVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(dvd.equals(dvd2),
                        "Double vector data is not equal to other double vector containing same values except last one");
                testData2 = DOUBLEVECTOR.denseArray(123);
                dvd2 = DoubleVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse(dvd.equals(dvd2),
                        "Double vector data is not equal to other double vector containing same values except for one zero");
            }
        }
    }

    /**
     * Test the plus and similar methods.
     */
    @Test
    public void operationTest()
    {
        double[] testValues = new double[] {0, 123.456d, 0, -273.15, -273.15, 0, -273.15, 234.567d, 0, 0};
        double[] testValues2 = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] {AbsoluteTemperatureUnit.KELVIN,
                AbsoluteTemperatureUnit.DEGREE_CELSIUS, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT})
        {
            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                AbsoluteTemperatureVector atv = new AbsoluteTemperatureVector(testValues, temperatureUnit, storageType);
                for (TemperatureUnit relativeTemperatureUnit : new TemperatureUnit[] {TemperatureUnit.KELVIN,
                        TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT})
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        TemperatureVector rtv = new TemperatureVector(testValues2, relativeTemperatureUnit, storageType2);
                        AbsoluteTemperatureVector sumtv = atv.plus(rtv);
                        compareSum(atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                sumtv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));
                        AbsoluteTemperatureVector difftv = atv.minus(rtv);
                        compareSum(rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                difftv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));

                        String s = atv.toString(temperatureUnit);
                        assertTrue(s.startsWith("["), "toString returns something sensible");
                        assertTrue(s.endsWith("] " + temperatureUnit.toString()), "toString returns something sensible");
                        // System.out.println(atv.toString(true, true));
                        s = atv.toString(true, true);
                        assertTrue(s.contains("Immutable"), "toString includes Immutable");
                        assertTrue(s.contains("Abs"), "toString includes Abs");
                        assertTrue(s.contains(atv.isDense() ? "Dense" : "Sparse"), "toString includes Dense or Sparse");
                        assertTrue(s.endsWith("] " + temperatureUnit.toString()), "toString returns something sensible");
                        s = atv.mutable().toString(true, true);
                        assertTrue(s.contains("Mutable"), "toString includes Mutable");

                        s = rtv.toString();
                        assertTrue(s.startsWith("["), "toString returns something sensible");
                        assertTrue(s.endsWith("] " + relativeTemperatureUnit.toString()),
                                "toString returns something sensible");
                        s = rtv.toString(true, true);
                        assertTrue(s.contains("Immutable"), "toString includes Immutable");
                        assertTrue(s.contains("Rel"), "toString includes Rel");
                        assertTrue(s.contains(rtv.isDense() ? "Dense" : "Sparse"), "toString includes Dense or Sparse");
                        assertTrue(s.endsWith("] " + relativeTemperatureUnit.toString()),
                                "toString returns something sensible");
                        s = rtv.mutable().toString(true, true);
                        assertTrue(s.contains("Mutable"), "toString includes Mutable");

                    }
                }
            }
        }
    }

    /**
     * Check that two arrays and a sum array match.
     * @param left the left array
     * @param right the right array
     * @param sum the sum array
     */
    public void compareSum(final double[] left, final double[] right, final double[] sum)
    {
        assertEquals(left.length, sum.length, "length of left must equal length of sum");
        assertEquals(right.length, sum.length, "length of right must equal length of sum");
        for (int i = 0; i < sum.length; i++)
        {
            assertEquals(left[i] + right[i], sum[i], 0.001, "left plus right is sum");
        }
    }

}
