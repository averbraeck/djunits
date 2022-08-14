package org.djunits.value.vdouble.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.Try;
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
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.DOUBLEMATRIX;
import org.djunits.value.vdouble.matrix.SIMatrix;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.junit.Test;

/**
 * Test the incrementBy, etc. methods.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AreaUnit au : new AreaUnit[] { AreaUnit.SQUARE_METER, AreaUnit.ACRE })
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am =
                        DoubleVector.instantiate(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);

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
                assertNotEquals(am, DoubleVector.instantiate(
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
                        assertTrue("Value should be NaN", Double.isNaN(ammut3.getSI(index)));

                    }
                    else
                    {
                        assertTrue("Value should be Infinite", Double.isInfinite(ammut3.getSI(index)));
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
                assertEquals("zSum", sum, zSum.getInUnit(), 0.1);
                assertEquals("cardinality", card, am.cardinality());

                // INCREMENTBY(SCALAR) and DECREMENTBY(SCALAR)
                AreaVector amold = am.clone();
                Area fa = Area.of(10.0d, "m^2");
                AreaVector aminc = am.mutable().incrementBy(fa).immutable();
                AreaVector amdec = am.mutable().decrementBy(fa).immutable();
                AreaVector amid = aminc.mutable().decrementBy(fa);
                assertEquals("immutable vector should not change when converted to mutable", am, amold);
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals("increment and decrement with scalar should result in same vector", am.getSI(index),
                            amid.getSI(index), 0.1);
                    assertEquals("m + s = (m+s)", au.getScale().toStandardUnit(testData[index]) + 10.0, aminc.getSI(index),
                            0.1);
                    assertEquals("m - s = (m-s)", au.getScale().toStandardUnit(testData[index]) - 10.0, amdec.getSI(index),
                            0.1);
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
                    assertEquals("times followed by divide with constant should result in same vector", am.getSI(index),
                            amtd.getSI(index), 0.1);
                    assertEquals("m * 5.0 = (m*5.0)", au.getScale().toStandardUnit(testData[index]) * 5.0d, amt5.getSI(index),
                            0.1);
                    assertEquals("m / 5.0 = (m/5.0)", au.getScale().toStandardUnit(testData[index]) / 5.0d, amd5.getSI(index),
                            0.1);
                    assertEquals("amtimD", amt5.getSI(index), amtimD.getSI(index), 0.1d);
                    assertEquals("amtimF", amt5.getSI(index), amtimF.getSI(index), 0.1d);
                    assertEquals("amdivD", amd5.getSI(index), amdivD.getSI(index), 0.01d);
                    assertEquals("amdivD", amd5.getSI(index), amdivF.getSI(index), 0.01d);
                }

                // GET(), GETINUNIT()
                assertEquals("get()", new Area(testData[2], au), am.get(2));
                assertEquals("getSI()", au.getScale().toStandardUnit(testData[2]), am.getSI(2), 0.1);
                assertEquals("getInUnit()", testData[2], am.getInUnit(2), 0.1);
                assertEquals("getInUnit(unit)",
                        AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[2])),
                        am.getInUnit(2, AreaUnit.SQUARE_YARD), 0.1);

                // SET(), SETINUNIT()
                Area fasqft = new Area(10.5d, AreaUnit.SQUARE_FOOT);
                AreaVector famChange = am.clone().mutable();
                famChange.set(2, fasqft);
                assertEquals("set()", fasqft.si, famChange.get(2).si, 0.1d);
                famChange = am.clone().mutable();
                famChange.setSI(2, 123.4d);
                assertEquals("setSI()", 123.4d, famChange.get(2).si, 0.1d);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.2d);
                assertEquals("setInUnit()", 1.2d, famChange.getInUnit(2), 0.1d);
                famChange = am.clone().mutable();
                famChange.setInUnit(2, 1.5d, AreaUnit.HECTARE);
                assertEquals("setInUnit(unit)", 15000.0d, famChange.get(2).si, 1.0d);

                // GETVALUES(), GETSCALARS()
                double[] valsi = am.getValuesSI();
                double[] valunit = am.getValuesInUnit();
                double[] valsqft = am.getValuesInUnit(AreaUnit.SQUARE_YARD);
                Area[] valscalars = am.getScalars();
                for (int index = 0; index < testData.length; index++)
                {
                    assertEquals("getValuesSI()", au.getScale().toStandardUnit(testData[index]), valsi[index], 0.1);
                    assertEquals("getValuesInUnit()", testData[index], valunit[index], 0.1);
                    assertEquals("getValuesInUnit(unit)",
                            AreaUnit.SQUARE_YARD.getScale().fromStandardUnit(au.getScale().toStandardUnit(testData[index])),
                            valsqft[index], 0.1);
                    assertEquals("getValuesInUnit(unit)", au.getScale().toStandardUnit(testData[index]), valscalars[index].si,
                            0.1);
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
                    assertEquals("div2", au.getScale().toStandardUnit(testData[index]) / 2.0d, amdiv2.getSI(index), 0.1d);
                    assertEquals("abs", Math.abs(au.getScale().toStandardUnit(testData[index]) / 2.0d), amAbs.getSI(index),
                            0.1d);
                    assertEquals("ceil", Math.ceil(au.getScale().toStandardUnit(testData[index]) / 2.0d), amCeil.getSI(index),
                            0.1d);
                    assertEquals("floor", Math.floor(au.getScale().toStandardUnit(testData[index]) / 2.0d),
                            amFloor.getSI(index), 0.1d);
                    assertEquals("neg", -au.getScale().toStandardUnit(testData[index]) / 2.0d, amNeg.getSI(index), 0.1d);
                    assertEquals("rint", Math.rint(au.getScale().toStandardUnit(testData[index]) / 2.0d), amRint.getSI(index),
                            0.1d);
                }

                // TEST METHODS THAT INVOLVE TWO VECTOR INSTANCES

                for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
                {
                    double[] testData2 = storageType2.equals(StorageType.DENSE) ? denseTestData : reverseSparseTestData;
                    for (AreaUnit au2 : new AreaUnit[] { AreaUnit.SQUARE_METER, AreaUnit.ACRE })
                    {
                        // PLUS and INCREMENTBY(VECTOR)
                        AreaVector am2 = DoubleVector
                                .instantiate(DoubleVectorData.instantiate(testData2, au2.getScale(), storageType2), au2);
                        AreaVector amSum1 = am.plus(am2);
                        AreaVector amSum2 = am2.plus(am);
                        AreaVector amSum3 = am.mutable().incrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        AreaVector amSum4 = am2.mutable().incrementBy(am).immutable();
                        assertEquals("a+b == b+a", amSum1, amSum2);
                        assertEquals("a+b == b+a", amSum1, amSum3);
                        assertEquals("a+c == c+a", amSum1, amSum4);
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amSum1.getSI(index)) ? Math.abs(amSum1.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals("value in vector matches", au.getScale().toStandardUnit(testData[index])
                                    + au2.getScale().toStandardUnit(testData2[index]), amSum1.getSI(index), tolerance);
                        }

                        // MINUS and DECREMENTBY(VECTOR)
                        AreaVector amDiff1 = am.minus(am2);
                        AreaVector amDiff2 = am2.minus(am).mutable().neg();
                        AreaVector amDiff3 = am.mutable().decrementBy(am2).immutable();
                        // different order of running out of nonzero values
                        AreaVector amDiff4 = am2.mutable().decrementBy(am).neg().immutable();
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff2);
                        assertEquals("a-b == -(b-a)", amDiff1, amDiff3);
                        assertEquals("a-c == -(c-a)", amDiff1, amDiff4);
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amDiff1.getSI(index)) ? Math.abs(amDiff1.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals("value in vector matches", au.getScale().toStandardUnit(testData[index])
                                    - au2.getScale().toStandardUnit(testData2[index]), amDiff1.getSI(index), tolerance);
                        }

                        // TIMES(VECTOR) and DIVIDE(VECTOR)
                        SIVector amTim = am.times(am2);
                        SIVector amDiv = am.divide(am2);
                        assertEquals("unit of m2 * m2 should be m4", "m4",
                                amTim.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        assertEquals("unit of m2 / m2 should be 1", "1",
                                amDiv.getDisplayUnit().getQuantity().getSiDimensions().toString(false, false, false));
                        for (int index = 0; index < testData.length; index++)
                        {
                            double tolerance =
                                    Double.isFinite(amTim.getSI(index)) ? Math.abs(amTim.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals("value in m2 * m2 matches", au.getScale().toStandardUnit(testData[index])
                                    * au2.getScale().toStandardUnit(testData2[index]), amTim.getSI(index), tolerance);
                            tolerance = Double.isFinite(amTim.getSI(index)) ? Math.abs(amDiv.getSI(index) / 10000.0d) : 0.1d;
                            assertEquals("value in m2 / m2 matches (could be NaN)",
                                    au.getScale().toStandardUnit(testData[index])
                                            / au2.getScale().toStandardUnit(testData2[index]),
                                    amDiv.getSI(index), tolerance);
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

        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AreaUnit au : new AreaUnit[] { AreaUnit.SQUARE_METER, AreaUnit.ACRE })
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am =
                        DoubleVector.instantiate(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);
                am = am.immutable();
                final AreaVector amPtr = am;
                Area fa = Area.of(10.0d, "m^2");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.assign(DoubleMathFunctions.ABS);
                    }
                }.test("ImmutableVector.assign(...) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.decrementBy(fa);
                    }
                }.test("ImmutableVector.decrementBy(scalar) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.decrementBy(amPtr);
                    }
                }.test("ImmutableVector.decrementBy(vector) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.incrementBy(fa);
                    }
                }.test("ImmutableVector.incrementBy(scalar) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.incrementBy(amPtr);
                    }
                }.test("ImmutableVector.incrementBy(vector) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.divideBy(2.0d);
                    }
                }.test("ImmutableVector.divideBy(factor) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.multiplyBy(2.0d);
                    }
                }.test("ImmutableVector.multiplyBy(factor) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.set(1, fa);
                    }
                }.test("ImmutableVector.set() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.setSI(1, 20.1d);
                    }
                }.test("ImmutableVector.setSI() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.setInUnit(1, 15.2d);
                    }
                }.test("ImmutableVector.setInUnit(f) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.setInUnit(1, 15.2d, AreaUnit.ARE);
                    }
                }.test("ImmutableVector.setInUnit(f, u) should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.abs();
                    }
                }.test("ImmutableVector.abs() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.ceil();
                    }
                }.test("ImmutableVector.ceil() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.floor();
                    }
                }.test("ImmutableVector.floor() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.neg();
                    }
                }.test("ImmutableVector.neg() should throw error");
                new Try()
                {
                    @Override
                    public void execute()
                    {
                        amPtr.rint();
                    }
                }.test("ImmutableVector.rint() should throw error");
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

        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (AreaUnit au : new AreaUnit[] { AreaUnit.SQUARE_METER, AreaUnit.ACRE })
            {
                double[] testData = storageType.equals(StorageType.DENSE) ? denseTestData : sparseTestData;
                AreaVector am =
                        DoubleVector.instantiate(DoubleVectorData.instantiate(testData, au.getScale(), storageType), au);
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
        TimeVector tm = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        String st = tm.toString(TimeUnit.DEFAULT, true, true); // verbose with unit
        assertFalse(st.contains("Rel"));
        assertTrue(st.contains("Abs"));
        LengthVector lm = DoubleVector.instantiate(
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
        TimeVector tm = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector dm = DoubleVector.instantiate(
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
        TimeVector halfTimeVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(halfDenseData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector absMinusAbs = tm.minus(halfTimeVector);
        TimeVector absDecByRelS = tm.mutable().decrementBy(Duration.of(1.0d, "min"));
        TimeVector absDecByRelM = tm.mutable().decrementBy(dm.divide(2.0d));
        TimeVector relPlusAbs = dm.plus(tm);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("absPlusRel", 61.0 * denseTestData[index], absPlusRel.getSI(index), 0.01);
            assertEquals("absMinusRel", -59.0 * denseTestData[index], absMinusRel.getSI(index), 0.01);
            assertEquals("absMinusAbs", denseTestData[index] / 2.0, absMinusAbs.getSI(index), 0.01);
            assertEquals("absDecByRelS", denseTestData[index] - 60.0, absDecByRelS.getSI(index), 0.01);
            assertEquals("absDecByRelM", -29.0 * denseTestData[index], absDecByRelM.getSI(index), 0.01);
            assertEquals("relPlusAbs", 61.0 * denseTestData[index], relPlusAbs.getSI(index), 0.01);
        }
        for (int dLength : new int[] { -1, 1 })
        {
            double[] other = DOUBLEVECTOR.denseArray(denseTestData.length + dLength);
            TimeVector wrongTimeVector = DoubleVector.instantiate(
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
        assertTrue("toString returns something informative",
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE).toString()
                        .startsWith("DoubleVectorData"));
    }

    /**
     * Test the instantiateAbs method and instantiateScalarAbsSI method.
     */
    @Test
    public void testInstantiateAbs()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(105);
        TimeVector timeVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, TimeUnit.DEFAULT.getScale(), StorageType.DENSE), TimeUnit.DEFAULT);
        DurationVector durationVector = DoubleVector.instantiate(
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
            assertEquals("relPlusAbsTime", 61.0 * denseTestData[index], relPlusAbsTime.getSI(index), 0.01);
        }
        Time time = durationVector.instantiateScalarAbsSI(123.456f, TimeUnit.EPOCH_DAY);
        assertEquals("Unit of instantiateScalarAbsSI matches", TimeUnit.EPOCH_DAY, time.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, time.si, 0.1);

        AngleVector angleVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, AngleUnit.DEGREE.getScale(), StorageType.DENSE), AngleUnit.DEGREE);
        DirectionVector directionVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, DirectionUnit.EAST_DEGREE.getScale(), StorageType.DENSE),
                DirectionUnit.EAST_DEGREE);

        DirectionVector relPlusAbsDirection = angleVector.plus(directionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsDirection", 2.0 / 180 * Math.PI * denseTestData[index], relPlusAbsDirection.getSI(index),
                    0.01);
        }
        Direction direction = angleVector.instantiateScalarAbsSI(123.456f, DirectionUnit.NORTH_RADIAN);
        assertEquals("Unit of instantiateScalarAbsSI matches", DirectionUnit.NORTH_RADIAN, direction.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, direction.si, 0.1);

        TemperatureVector temperatureVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, TemperatureUnit.DEGREE_FAHRENHEIT.getScale(), StorageType.DENSE),
                TemperatureUnit.DEGREE_FAHRENHEIT);
        AbsoluteTemperatureVector absoluteTemperatureVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, AbsoluteTemperatureUnit.KELVIN.getScale(), StorageType.DENSE),
                AbsoluteTemperatureUnit.KELVIN);

        AbsoluteTemperatureVector relPlusAbsTemperature = temperatureVector.plus(absoluteTemperatureVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsTemperature", (1.0 + 5.0 / 9.0) * denseTestData[index], relPlusAbsTemperature.getSI(index),
                    0.01);
        }
        AbsoluteTemperature absoluteTemperature =
                temperatureVector.instantiateScalarAbsSI(123.456f, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals("Unit of instantiateScalarAbsSI matches", AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT,
                absoluteTemperature.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, absoluteTemperature.si, 0.1);

        LengthVector lengthVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, LengthUnit.MILE.getScale(), StorageType.DENSE), LengthUnit.MILE);
        PositionVector positionVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, PositionUnit.KILOMETER.getScale(), StorageType.DENSE),
                PositionUnit.KILOMETER);

        PositionVector relPlusAbsPosition = lengthVector.plus(positionVector);
        for (int index = 0; index < denseTestData.length; index++)
        {
            assertEquals("relPlusAbsPosition", 2609.344 * denseTestData[index], relPlusAbsPosition.getSI(index), 1);
        }
        Position position = lengthVector.instantiateScalarAbsSI(123.456f, PositionUnit.ANGSTROM);
        assertEquals("Unit of instantiateScalarAbsSI matches", PositionUnit.ANGSTROM, position.getDisplayUnit());
        assertEquals("Value of instantiateScalarAbsSI matches", 123.456f, position.si, 0.1);
    }

    /**
     * Test the <code>as</code> method of the SIVector class.
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
        double[][] testValues = DOUBLEMATRIX.denseRectArrays(10, 20);
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            for (String type : CLASSNAMES.REL_LIST)
            {
                Class.forName("org.djunits.unit." + type + "Unit");
                Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
                for (U unit : quantity.getUnitsById().values())
                {
                    for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, storageType })
                    {
                        SIUnit siUnit = SIUnit.of(unit.getQuantity().getSiDimensions());
                        SIMatrix matrix = SIMatrix.instantiate(testValues, siUnit, storageType2);
                        Method asMethod = SIMatrix.class.getDeclaredMethod("as", Unit.class);
                        AbstractDoubleMatrixRel<U, ?, ?, ?> asMatrix =
                                (AbstractDoubleMatrixRel<U, ?, ?, ?>) asMethod.invoke(matrix, siUnit);
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
        double[] testData = DOUBLEVECTOR.denseArray(123);
        testData[2] = 0;
        for (StorageType storageType : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
        {
            DoubleVectorData dvd = DoubleVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType);
            assertTrue("Double vector data is equal to itself", dvd.equals(dvd));
            assertFalse("Double vector data is not equal to null", dvd.equals(null));
            assertFalse("Double vector data is not equal to some string", dvd.equals("some string"));
            assertTrue("Double vector is equal to sparse version of itself", dvd.equals(dvd.toSparse()));
            assertTrue("Double vector is equal to dense version of itself", dvd.equals(dvd.toDense()));
            for (StorageType storageType2 : new StorageType[] { StorageType.DENSE, StorageType.SPARSE })
            {
                DoubleVectorData dvd2 = DoubleVectorData.instantiate(testData, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertEquals(
                        "Double vector data is equal to other double vector containing same values regardless of storage type",
                        dvd, dvd2);
                double[] testData2 = DOUBLEVECTOR.denseArray(122);
                testData2[2] = 0;
                dvd2 = DoubleVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Double vector data is not equal to other double vector containing same values except last one",
                        dvd.equals(dvd2));
                testData2 = DOUBLEVECTOR.denseArray(123);
                dvd2 = DoubleVectorData.instantiate(testData2, TemperatureUnit.KELVIN.getScale(), storageType2);
                assertFalse("Double vector data is not equal to other double vector containing same values except for one zero",
                        dvd.equals(dvd2));
            }
        }
    }

}
