package org.djunits.value.vfloat.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatArea;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Try;
import org.junit.Test;

/**
 * Test the incrementBy, etc. methods.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatVectorMethodTest
{

    /**
     * Test the standard methods of all vector classes.
     * @throws UnitException on error
     * @throws ValueRuntimeException on error
     */
    @Test
    public void testVectorMethods() throws ValueRuntimeException, UnitException
    {
        float[] denseTestData = FLOATVECTOR.denseArray(105);
        float[] sparseTestData = FLOATVECTOR.sparseArray(105);
        float[] reverseSparseTestData = new float[sparseTestData.length];
        // sparseTestData and reverseSparseTestData should not "run out of values" at the same index
        for (int index = 0; index < sparseTestData.length; index++)
        {
            reverseSparseTestData[reverseSparseTestData.length - 1 - index] = sparseTestData[index];
        }
        // Ensure that both have a value at some index (i.c. 10)
        sparseTestData[10] = 123.456f;
        reverseSparseTestData[10] = sparseTestData[10];
        // Ensure that there are > 50% positions where both have a non-zero value
        for (int index = 20; index < 90; index++)
        {
            sparseTestData[index] = 10000.456f + index;
            reverseSparseTestData[index] = 20000.567f + index;
        }
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaVector am = new FloatAreaVector(FloatVectorData.instantiate(testData, au.getScale(), storageType), au);

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
                assertNotEquals(am, new FloatLengthVector(
                        FloatVectorData.instantiate(testData, LengthUnit.METER.getScale(), storageType), LengthUnit.METER));
                assertNotEquals(am, am.divide(2.0d));

                // MUTABLE
                assertFalse(am.isMutable());
                FloatAreaVector ammut = am.mutable();
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                FloatAreaVector ammut2 = ammut.multiplyBy(1.0);
                assertEquals(am, ammut2);
                assertTrue(ammut.isMutable());
                assertFalse(am.isMutable());
                assertTrue(ammut2.isMutable());
                ammut2 = ammut2.mutable().divideBy(2.0);
                assertEquals(am, ammut);
                assertNotEquals(am, ammut2);
                FloatAreaVector ammut3 = ammut2.mutable().divideBy(0.0);
                for (int index = 0; index < ammut3.size(); index++)
                {
                    if (ammut2.getSI(index) == 0)
                    {
                        assertTrue("Value should be NaN", Float.isNaN(ammut3.getSI(index)));

                    }
                    else
                    {
                        assertTrue("Value should be Infinite", Float.isInfinite(ammut3.getSI(index)));
                    }
                }

                // ZSUM and CARDINALITY
                FloatArea zSum = am.zSum();
                float sum = 0;
                int card = 0;
                for (int index = 0; index < testData.length; index++)
                {
                    sum += testData[index];
                    card += testData[index] == 0.0d ? 0 : 1;
                }
                assertEquals("zSum", sum, zSum.getInUnit(), 0.1);
                assertEquals("cardinality", card, am.cardinality());

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                FloatAreaVector amold = am.clone();
                FloatArea fa = FloatArea.of(10.0f, "m^2");
                FloatAreaVector aminc = am.mutable().incrementBy(fa).immutable();
                FloatAreaVector amdec = am.mutable().decrementBy(fa).immutable();
                FloatAreaVector amid = aminc.mutable().decrementBy(fa);
                assertEquals("immutable vector should not change when converted to mutable", am, amold);
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals("increment and decrement with scalar should result in same vector", am.getSI(index),
                            amid.getSI(index), 0.1E5);
                    assertEquals("m + s = (m+s)", au.getScale().toStandardUnit(testData[index]) + 10.0, aminc.getSI(index),
                            0.1E5);
                    assertEquals("m - s = (m-s)", au.getScale().toStandardUnit(testData[index]) - 10.0, amdec.getSI(index),
                            0.1E5);
                }

                // MULTIPLYBY() and DIVIDEBY(), TIMES(), DIVIDE()
                FloatAreaVector amt5 = am.mutable().multiplyBy(5.0d).immutable();
                FloatAreaVector amd5 = am.mutable().divideBy(5.0d).immutable();
                FloatAreaVector amtd = amt5.mutable().divideBy(5.0d);
                FloatAreaVector amtimD = am.times(5.0d);
                FloatAreaVector amtimF = am.times(5.0f);
                FloatAreaVector amdivD = am.divide(5.0d);
                FloatAreaVector amdivF = am.divide(5.0f);
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals("times followed by divide with constant should result in same vector", am.getSI(index),
                            amtd.getSI(index), 0.1);
                    assertEquals("m * 5.0 = (m*5.0)", au.getScale().toStandardUnit(testData[index]) * 5.0f, amt5.getSI(index),
                            0.1E4);
                    assertEquals("m / 5.0 = (m/5.0)", au.getScale().toStandardUnit(testData[index]) / 5.0f, amd5.getSI(index),
                            0.1E4);
                    assertEquals("amtimD", amt5.getSI(index), amtimD.getSI(index), 0.1f);
                    assertEquals("amtimF", amt5.getSI(index), amtimF.getSI(index), 0.1f);
                    assertEquals("amdivD", amd5.getSI(index), amdivD.getSI(index), 0.01f);
                    assertEquals("amdivD", amd5.getSI(index), amdivF.getSI(index), 0.01f);
                }

                // GET(), GETINUNIT()
                assertEquals("get()", new FloatArea(testData[2], au), am.get(2));
                assertEquals("getSI()", au.getScale().toStandardUnit(testData[2]), am.getSI(2), 0.1);
                assertEquals("getInUnit()", testData[2], am.getInUnit(2), 0.1);
                assertEquals("getInUnit(unit)",
                        AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2])),
                        am.getInUnit(2, AreaUnit.SQUARE_YARD), 0.1);

                // SET(), SETINUNIT()
                FloatArea fasqft = new FloatArea(10.5f, AreaUnit.SQUARE_FOOT);
                FloatAreaVector famChange = am.clone().mutable();
                famChange.set(2, fasqft);
                assertEquals("set()", fasqft.si, famChange.get(2).si, 0.1d);
                famChange = am.clone().mutable();
                famChange.setSI(2, 123.4f);
                assertEquals("setSI()", 123.4f, famChange.get(2).si, 0.1d);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.2f);
                assertEquals("setInUnit()", 1.2f, famChange.getInUnit(2), 0.1d);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.5f, AreaUnit.HECTARE);
                assertEquals("setInUnit(unit)", 15000.0d, famChange.get(2).si, 1.0d);

                // GETVALUES(), GETSCALARS()
                float[] valsi = am.getValuesSI();
                float[] valunit = am.getValuesInUnit();
                float[] valsqft = am.getValuesInUnit(AreaUnit.SQUARE_YARD);
                FloatArea[] valscalars = am.getScalars();
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals("getValuesSI()", au.getScale().toStandardUnit(testData[index]), valsi[index], 0.1E5);
                    assertEquals("getValuesInUnit()", testData[index], valunit[index], 0.1);
                    assertEquals("getValuesInUnit(unit)",
                            AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[index])),
                            valsqft[index], 0.1E5);
                    assertEquals("getValuesInUnit(unit)", au.getScale().toStandardUnit(testData[index]), valscalars[index].si,
                            0.1E5);
                }

                // ASSIGN FUNCTION ABS, CEIL, FLOOR, NEG, RINT
                FloatAreaVector amdiv2 = am.divide(2.0d);
                assertEquals(am.getStorageType(), amdiv2.getStorageType());
                assertEquals(am.getDisplayUnit(), amdiv2.getDisplayUnit());
                FloatAreaVector amAbs = amdiv2.mutable().abs().immutable();
                assertEquals(am.getStorageType(), amAbs.getStorageType());
                assertEquals(am.getDisplayUnit(), amAbs.getDisplayUnit());
                FloatAreaVector amCeil = amdiv2.mutable().ceil().immutable();
                assertEquals(am.getStorageType(), amCeil.getStorageType());
                assertEquals(am.getDisplayUnit(), amCeil.getDisplayUnit());
                FloatAreaVector amFloor = amdiv2.mutable().floor().immutable();
                assertEquals(am.getStorageType(), amFloor.getStorageType());
                assertEquals(am.getDisplayUnit(), amFloor.getDisplayUnit());
                FloatAreaVector amNeg = amdiv2.mutable().neg().immutable();
                assertEquals(am.getStorageType(), amNeg.getStorageType());
                assertEquals(am.getDisplayUnit(), amNeg.getDisplayUnit());
                FloatAreaVector amRint = amdiv2.mutable().rint().immutable();
                assertEquals(am.getStorageType(), amRint.getStorageType());
                assertEquals(am.getDisplayUnit(), amRint.getDisplayUnit());
                for (int index = 0; index < testData.length; index++)
                {
                    // TODO: Should be rounded IN THE UNIT rather than BY SI VALUES
                    assertEquals("div2", au.getScale().toStandardUnit(testData[index]) / 2.0d, amdiv2.getSI(index), 0.1E5);
                    assertEquals("abs", Math.abs(au.getScale().toStandardUnit(testData[index]) / 2.0d), amAbs.getSI(index),
                            0.1E5);
                    assertEquals("ceil", Math.ceil(au.getScale().toStandardUnit(testData[index]) / 2.0d), amCeil.getSI(index),
                            0.1E5);
                    assertEquals("floor", Math.floor(au.getScale().toStandardUnit(testData[index]) / 2.0d),
                            amFloor.getSI(index), 0.1E5);
                    assertEquals("neg", -au.getScale().toStandardUnit(testData[index]) / 2.0d, amNeg.getSI(index), 0.1E5);
                    assertEquals("rint", Math.rint(au.getScale().toStandardUnit(testData[index]) / 2.0d), amRint.getSI(index),
                            0.1E5);
                }

                // TEST METHODS THAT INVOLVE TWO VECTOR INSTANCES

                for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                {
                    float[] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
                    {
                        // PLUS and INCREMENTBY(VECTOR)
                        FloatAreaVector am2 =
                                new FloatAreaVector(FloatVectorData.instantiate(testData2, au2.getScale(), storageType2), au2);
                        FloatAreaVector amSum1 = am.plus(am2);
                        FloatAreaVector amSum2 = am2.plus(am);
                        FloatAreaVector amSum3 = am.mutable().incrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        FloatAreaVector amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals("a+b == b+a", amSum1, amSum2);
                        assertEquals("a+b == b+a", amSum1, amSum3);
                        assertEquals("a+c == c+a", amSum1, amSum4);
                        for (int index = 0; index < testData.length; index++)
                        {
                            float tolerance =
                                    Float.isFinite(amSum1.getSI(index)) ? Math.abs(amSum1.getSI(index) / 10000.0f) : 0.1f;
                            assertEquals("value in vector matches", au.getScale().toStandardUnit(testData[index])
                                    + au2.getScale().toStandardUnit(testData2[index]), amSum1.getSI(index), tolerance);
                        }

                        // MINUS and DECREMENTBY(VECTOR)
                        FloatAreaVector amDiff1 = am.minus(am2);
                        FloatAreaVector amDiff2 = am2.minus(am).mutable().neg();
                        FloatAreaVector amDiff3 = am.mutable().decrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        FloatAreaVector amDiff4 = am2.mutable().decrementBy(am).neg().immutable();
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff2);
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff3);
                        assertEquals("a-c == -(c-a)", amDiff1, amDiff4);
                        for (int index = 0; index < testData.length; index++)
                        {
                            float tolerance =
                                    Float.isFinite(amDiff1.getSI(index)) ? Math.abs(amDiff1.getSI(index) / 10000.0f) : 0.1f;
                            assertEquals("value in vector matches", au.getScale().toStandardUnit(testData[index])
                                    - au2.getScale().toStandardUnit(testData2[index]), amDiff1.getSI(index), tolerance);
                        }

                        // TIMES(VECTOR) and DIVIDE(VECTOR)
                        FloatSIVector amTim = am.times(am2);
                        FloatSIVector amDiv = am.divide(am2);
                        assertEquals("unit of m2 * m2 should be m4", "m4",
                                amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        assertEquals("unit of m2 / m2 should be 1", "1",
                                amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        for (int index = 0; index < testData.length; index++)
                        {
                            float tolerance =
                                    Float.isFinite(amTim.getSI(index)) ? Math.abs(amTim.getSI(index) / 10000.0f) : 0.1f;
                            assertEquals("value in m2 * m2 matches", au.getScale().toStandardUnit(testData[index])
                                    * au2.getScale().toStandardUnit(testData2[index]), amTim.getSI(index), tolerance);
                            tolerance = Float.isFinite(amTim.getSI(index)) ? Math.abs(amDiv.getSI(index) / 10000.0f) : 0.1f;
                            assertEquals("value in m2 / m2 matches (could be NaN)",
                                    au.getScale().toStandardUnit(testData[index])
                                            / au2.getScale().toStandardUnit(testData2[index]),
                                    amDiv.getSI(index), tolerance);
                        }
                        // This does not compile: FloatSIVector amTim2 = am.immutable().multiplyBy(am2).immutable();
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
        float[] denseTestData = FLOATVECTOR.denseArray(105);
        float[] sparseTestData = FLOATVECTOR.sparseArray(105);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaVector am = new FloatAreaVector(FloatVectorData.instantiate(testData, au.getScale(), storageType), au);
                am = am.immutable();
                final FloatAreaVector amPtr = am;
                FloatArea fa = FloatArea.of(10.0f, "m^2");
                Try.testFail(() -> amPtr.assign(FloatMathFunctions.ABS), "ImmutableVector.assign(...) should throw error");
                Try.testFail(() -> amPtr.decrementBy(fa), "ImmutableVector.decrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.decrementBy(amPtr), "ImmutableVector.decrementBy(vector) should throw error");
                Try.testFail(() -> amPtr.incrementBy(fa), "ImmutableVector.incrementBy(scalar) should throw error");
                Try.testFail(() -> amPtr.incrementBy(amPtr), "ImmutableVector.incrementBy(vector) should throw error");
                Try.testFail(() -> amPtr.divideBy(2.0d), "ImmutableVector.divideBy(factor) should throw error");
                Try.testFail(() -> amPtr.multiplyBy(2.0d), "ImmutableVector.multiplyBy(factor) should throw error");
                Try.testFail(() -> amPtr.set(1, fa), "ImmutableVector.set() should throw error");
                Try.testFail(() -> amPtr.setSI(1, 20.1f), "ImmutableVector.setSI() should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 15.2f), "ImmutableVector.setInUnit(f) should throw error");
                Try.testFail(() -> amPtr.setInUnit(1, 15.2f, AreaUnit.ARE),
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
        float[] denseTestData = FLOATVECTOR.denseArray(105);
        float[] sparseTestData = FLOATVECTOR.sparseArray(105);

        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            for (AreaUnit au : new AreaUnit[] {AreaUnit.SQUARE_METER, AreaUnit.ACRE})
            {
                float[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                FloatAreaVector am = new FloatAreaVector(FloatVectorData.instantiate(testData, au.getScale(), storageType), au);
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
                FloatAreaVector ammut = am.mutable();
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
        FloatTimeVector tm = new FloatTimeVector(
                FloatVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        FloatLengthVector lm = new FloatLengthVector(
                FloatVectorData.instantiate(denseTestData, LengthUnit.SI.getScale(), StorageType.DENSE), LengthUnit.SI);
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
        float[] denseTestData = FLOATVECTOR.denseArray(105);
        FloatTimeVector tm = new FloatTimeVector(
                FloatVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationVector dm = new FloatDurationVector(
                FloatVectorData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);
        assertTrue(tm.isAbsolute());
        assertFalse(dm.isAbsolute());
        assertFalse(tm.isRelative());
        assertTrue(dm.isRelative());

        FloatTimeVector absPlusRel = tm.plus(dm);
        FloatTimeVector absMinusRel = tm.minus(dm);
        float[] halfDenseData = FLOATVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        FloatTimeVector halfTimeVector = new FloatTimeVector(
                FloatVectorData.instantiate(halfDenseData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationVector absMinusAbs = tm.minus(halfTimeVector);
        FloatTimeVector absDecByRelS = tm.mutable().decrementBy(FloatDuration.of(1.0f, "min"));
        FloatTimeVector absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0f));
        FloatTimeVector relPlusAbs = dm.plus(tm);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("absPlusRel", 61.0 * denseTestData[index], absPlusRel.getSI(index), 0.01);
            assertEquals("absMinusRel", -59.0 * denseTestData[index], absMinusRel.getSI(index), 0.01);
            assertEquals("absMinusAbs", denseTestData[index] / 2.0, absMinusAbs.getSI(index), 0.01);
            assertEquals("absDecByRelS", denseTestData[index] - 60.0, absDecByRelS.getSI(index), 0.01);
            assertEquals("absDecByRelM", -29.0 * denseTestData[index], absDecByRelM.getSI(index), 0.01);
            assertEquals("relPlusAbs", 61.0 * denseTestData[index], relPlusAbs.getSI(index), 0.01);
        }
        for (int dLength : new int[] {-1, 1})
        {
            float[] other = FLOATVECTOR.denseArray(denseTestData.length + dLength);
            FloatTimeVector wrongTimeVector = new FloatTimeVector(
                    FloatVectorData.instantiate(other, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
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
        assertTrue("toString returns something informative",
                FloatVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                        .startsWith("FloatVectorData"));
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        float[] denseTestData = FLOATVECTOR.denseArray(105);
        FloatTimeVector timeVector = new FloatTimeVector(
                FloatVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        FloatDurationVector durationVector = new FloatDurationVector(
                FloatVectorData.instantiate(denseTestData, DurationUnit.MINUTE.getScale(), StorageType.DENSE),
                DurationUnit.SECOND);

        float[] halfDenseData = FLOATVECTOR.denseArray(105);
        for (int index = 0; index < halfDenseData.length; index++)
        {
            halfDenseData[index] *= 0.5;
        }
        FloatTimeVector relPlusAbsTime = durationVector.plus(timeVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsTime", 61.0 * denseTestData[index], relPlusAbsTime.getSI(index), 0.01);
        }
        FloatTime time = durationVector.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals("Unit of instantiateScalarAbsSI matches", TimeUnit.EPOCH_DAY, time.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, time.si, 0.1);

        FloatAngleVector angleVector = new FloatAngleVector(
                FloatVectorData.instantiate(denseTestData, AngleUnit.DEGREE.getScale(), StorageType.DENSE), AngleUnit.DEGREE);
        FloatDirectionVector directionVector = new FloatDirectionVector(
                FloatVectorData.instantiate(denseTestData, DirectionUnit.EAST_DEGREE.getScale(), StorageType.DENSE),
                DirectionUnit.EAST_DEGREE);

        FloatDirectionVector relPlusAbsDirection = angleVector.plus(directionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsDirection", 2.0 / 180 * Math.PI * denseTestData[index], relPlusAbsDirection.getSI(index),
                    0.01);
        }
        FloatDirection direction = angleVector.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals("Unit of instantiateScalarAbsSI matches", DirectionUnit.NORTH_RADIAN, direction.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, direction.si, 0.1);

        FloatTemperatureVector temperatureVector = new FloatTemperatureVector(
                FloatVectorData.instantiate(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT.getScale(), StorageType.DENSE),
                TemperatureUnit.DEGREE_FAHRENHEIT);
        FloatAbsoluteTemperatureVector absoluteTemperatureVector = new FloatAbsoluteTemperatureVector(
                FloatVectorData.instantiate(denseTestData, AbsoluteTemperatureUnit.KELVIN.getScale(), StorageType.DENSE),
                AbsoluteTemperatureUnit.KELVIN);

        FloatAbsoluteTemperatureVector relPlusAbsTemperature = temperatureVector.plus(absoluteTemperatureVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsTemperature", (1.0 + 5.0 / 9.0) * denseTestData[index], relPlusAbsTemperature.getSI(index),
                    0.01);
        }
        FloatAbsoluteTemperature absoluteTemperature =
                temperatureVector.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals("Unit of instantiateScalarAbsSI matches", AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT,
                absoluteTemperature.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, absoluteTemperature.si, 0.1);

        FloatLengthVector lengthVector = new FloatLengthVector(
                FloatVectorData.instantiate(denseTestData, LengthUnit.MILE.getScale(), StorageType.DENSE), LengthUnit.MILE);
        FloatPositionVector positionVector = new FloatPositionVector(
                FloatVectorData.instantiate(denseTestData, PositionUnit.KILOMETER.getScale(), StorageType.DENSE),
                PositionUnit.KILOMETER);

        FloatPositionVector relPlusAbsPosition = lengthVector.plus(positionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsPosition", 2609.344 * denseTestData[index], relPlusAbsPosition.getSI(index), 1);
        }
        FloatPosition position = lengthVector.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals("Unit of instantiateScalarAbsSI matches", PositionUnit.ANGSTROM, position.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, position.si, 0.1);
    }

    /**
     * Test the equals method.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals()
    {
        float[] testData = FLOATVECTOR.denseArray(123);
        testData[2] = 0;
        for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
        {
            FloatVectorData dvd = FloatVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue("Float vector data is equal to itself", dvd.equals(dvd));
            assertFalse("Float vector data is not equal to null", dvd.equals(null));
            assertFalse("Float vector data is not equal to some string", dvd.equals("some string"));
            assertTrue("Float vector is equal to sparse version of itself", dvd.equals(dvd.toSparse()));
            assertTrue("Float vector is equal to dense version of itself", dvd.equals(dvd.toDense()));
            for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                FloatVectorData dvd2 = FloatVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(
                        "Float vector data is equal to other float vector containing same values regardless of storage type",
                        dvd, dvd2);
                float[] testData2 = FLOATVECTOR.denseArray(122);
                testData2[2] = 0;
                dvd2 = FloatVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Float vector data is not equal to other float vector containing same values except last one",
                        dvd.equals(dvd2));
                testData2 = FLOATVECTOR.denseArray(123);
                dvd2 = FloatVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Float vector data is not equal to other float vector containing same values except for one zero",
                        dvd.equals(dvd2));
            }
        }
    }

    /**
     * Test the plus and similar methods.
     */
    @Test
    public void operationTest()
    {
        float[] testValues = new float[] {0, 123.456f, 0, -273.15f, -273.15f, 0, -273.15f, 234.567f, 0, 0};
        float[] testValues2 = new float[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (AbsoluteTemperatureUnit temperatureUnit : new AbsoluteTemperatureUnit[] {AbsoluteTemperatureUnit.KELVIN,
                AbsoluteTemperatureUnit.DEGREE_CELSIUS, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT})
        {
            for (StorageType storageType : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
            {
                FloatAbsoluteTemperatureVector atv =
                        new FloatAbsoluteTemperatureVector(testValues, temperatureUnit, storageType);
                for (TemperatureUnit relativeTemperatureUnit : new TemperatureUnit[] {TemperatureUnit.KELVIN,
                        TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT})
                {
                    for (StorageType storageType2 : new StorageType[] {StorageType.DENSE, StorageType.SPARSE})
                    {
                        FloatTemperatureVector rtv =
                                new FloatTemperatureVector(testValues2, relativeTemperatureUnit, storageType2);
                        FloatAbsoluteTemperatureVector sumtv = atv.plus(rtv);
                        compareSum(atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                sumtv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));
                        FloatAbsoluteTemperatureVector difftv = atv.minus(rtv);
                        compareSum(rtv.getValuesInUnit(TemperatureUnit.KELVIN),
                                difftv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN),
                                atv.getValuesInUnit(AbsoluteTemperatureUnit.KELVIN));

                        String s = atv.toString(temperatureUnit);
                        assertTrue("toString returns something sensible", s.startsWith("["));
                        assertTrue("toString returns something sensible", s.endsWith("] " + temperatureUnit.toString()));
                        // System.out.println(atv.toString(true, true));
                        s = atv.toString(true, true);
                        assertTrue("toString includes Immutable", s.contains("Immutable"));
                        assertTrue("toString includes Abs", s.contains("Abs"));
                        assertTrue("toString includes Dense or Sparse", s.contains(atv.isDense() ? "Dense" : "Sparse"));
                        assertTrue("toString returns something sensible", s.endsWith("] " + temperatureUnit.toString()));
                        s = atv.mutable().toString(true, true);
                        assertTrue("toString includes Mutable", s.contains("Mutable"));

                        s = rtv.toString();
                        assertTrue("toString returns something sensible", s.startsWith("["));
                        assertTrue("toString returns something sensible",
                                s.endsWith("] " + relativeTemperatureUnit.toString()));
                        s = rtv.toString(true, true);
                        assertTrue("toString includes Immutable", s.contains("Immutable"));
                        assertTrue("toString includes Rel", s.contains("Rel"));
                        assertTrue("toString includes Dense or Sparse", s.contains(rtv.isDense() ? "Dense" : "Sparse"));
                        assertTrue("toString returns something sensible",
                                s.endsWith("] " + relativeTemperatureUnit.toString()));
                        s = rtv.mutable().toString(true, true);
                        assertTrue("toString includes Mutable", s.contains("Mutable"));

                    }
                }
            }
        }
    }

    /**
     * Check that two arrays and a sum array match.
     * @param left float[]; the left array
     * @param right float[]; the right array
     * @param sum float[]; the sum array
     */
    public void compareSum(final float[] left, final float[] right, final float[] sum)
    {
        assertEquals("length of left must equal length of sum", left.length, sum.length);
        assertEquals("length of right must equal length of sum", right.length, sum.length);
        for (int i = 0; i < sum.length; i++)
        {
            assertEquals("left plus right is sum", left[i] + right[i], sum[i], 0.001);
        }
    }

}
