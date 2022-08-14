package org.djunits.value.vdouble.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRelWithAbs;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRelWithAbs;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;
import org.junit.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class DoubleSIVectorTest
{

    /**
     * Test all "asXX" methods.
     * @throws SecurityException on error
     * @throws NoSuchMethodException on error
     * @throws InvocationTargetException on error
     * @throws IllegalArgumentException on error
     * @throws IllegalAccessException on error
     * @throws ClassNotFoundException on error
     * @throws UnitException on error
     * @param <AU> the absolute unit type
     * @param <A> the absolute scalar type
     * @param <AV> the absolute vector type
     * @param <RU> the relative unit type
     * @param <R> the relative scalar type
     * @param <RV> the relative vector type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <AU extends AbsoluteLinearUnit<AU, RU>, A extends AbstractDoubleScalarAbs<AU, A, RU, R>,
            AV extends AbstractDoubleVectorAbs<AU, A, AV, RU, R, RV>, RU extends Unit<RU>,
            R extends AbstractDoubleScalarRelWithAbs<AU, A, RU, R>,
            RV extends AbstractDoubleVectorRelWithAbs<AU, A, AV, RU, R, RV>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[] denseTestData = DOUBLEVECTOR.denseArray(50);
        DimensionlessVector dimlessVector = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, DimensionlessUnit.SI.getScale(), StorageType.DENSE),
                DimensionlessUnit.SI);
        dimlessVector = dimlessVector.mutable().divide(dimlessVector).asDimensionless(); // unit vector
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<RU> quantity = (Quantity<RU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (RU unit : quantity.getUnitsById().values())
            {
                AbstractDoubleVectorRel<RU, R, RV> vector = (AbstractDoubleVectorRel<RU, R, RV>) DoubleVector
                        .instantiate(DoubleVectorData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                AbstractDoubleVectorRel<RU, R, RV> sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }

                SIVector mult = vector.times(dimlessVector);
                Method asMethod = SIVector.class.getDeclaredMethod("as" + type);
                AbstractDoubleVectorRel<RU, R, RV> asVector = (AbstractDoubleVectorRel<RU, R, RV>) asMethod.invoke(mult);
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVector.getDisplayUnit());
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVector.getSI(index), vector.getSI(index) / 1000.0);
                }

                Method asMethodDisplayUnit = SIVector.class.getDeclaredMethod("as" + type, unit.getClass());
                AbstractDoubleVectorRel<RU, R, RV> asVectorDisplayUnit =
                        (AbstractDoubleVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(vector.getDisplayUnit().getStandardUnit(), asVectorDisplayUnit.getDisplayUnit());

                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals(type + ", unit: " + unit.getDefaultTextualAbbreviation(), vector.getSI(index),
                            asVectorDisplayUnit.getSI(index), vector.getSI(index) / 1000.0);
                }

                // test exception for wrong 'as'
                SIVector cd4sr2 = SIVector.instantiate(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    AbstractDoubleVectorRel<RU, R, RV> asVectorDim =
                            (AbstractDoubleVectorRel<RU, R, RV>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    AbstractDoubleVectorRel<RU, R, RV> asVectorDim =
                            (AbstractDoubleVectorRel<RU, R, RV>) asMethodDisplayUnit.invoke(cd4sr2, vector.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asVectorDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
            }
        }
        for (String type : CLASSNAMES.ABS_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<AU> quantity = (Quantity<AU>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (AU unit : quantity.getUnitsById().values())
            {
                AV vector = DoubleVector
                        .instantiate(DoubleVectorData.instantiate(denseTestData, unit.getScale(), StorageType.DENSE), unit);
                AV sparseVector = vector.toSparse();
                for (int index = 0; index < denseTestData.length; index++)
                {
                    assertEquals("Value at index matches", vector.getSI(index), sparseVector.getSI(index), 0.0);
                }
                Class<A> scalarClass = vector.getScalarClass();
                assertTrue("Scalar class is correct kind of vdouble.scalar class",
                        scalarClass.getName().equals("org.djunits.value.vdouble.scalar." + type));
                double testValue = 123.45;
                A scalarAbs = vector.instantiateScalarSI(testValue, unit);
                assertEquals("Scalar has correct value", testValue, scalarAbs.getSI(), 0.001);
                assertEquals("Scalar ahs correct displayUnit", unit, scalarAbs.getDisplayUnit());
                Quantity<RU> relativeQuantity = (Quantity<RU>) Quantities.INSTANCE
                        .getQuantity(CLASSNAMES.REL_WITH_ABS_LIST.get(CLASSNAMES.ABS_LIST.indexOf(type)) + "Unit");
                for (RU relativeUnit : relativeQuantity.getUnitsById().values())
                {
                    R scalarRel = vector.instantiateScalarRelSI(testValue, relativeUnit);
                    assertEquals("display unit of scalarRel matches", relativeUnit, scalarRel.getDisplayUnit());
                    assertEquals("value of scalarRel matches", testValue, scalarRel.getSI(), 0.001);
                }
                // Indirectly test the instantiateVectorRel method (we don't have direct access to a DoubleVectorData object)
                // This was more difficult than it should be...
                RV relVector = vector.minus(vector);
                assertEquals(0.0, relVector.cardinality(), 0.0001);
            }
        }

        // just to see if Position and Length play nice for 'minus'
        PositionVector pv = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, IdentityScale.SCALE, StorageType.DENSE), PositionUnit.METER);
        LengthVector lv = DoubleVector.instantiate(
                DoubleVectorData.instantiate(denseTestData, IdentityScale.SCALE, StorageType.DENSE), LengthUnit.METER);
        PositionVector pdiff = pv.minus(lv);
        assertEquals(0.0, pdiff.cardinality(), 0.0001);
        LengthVector ldiff = pv.minus(pv);
        assertEquals(0.0, ldiff.cardinality(), 0.0001);
    }

    /**
     * Test the methods that are only implemented in DimensionLess vectors.
     */
    @Test
    public void testDimensionLess()
    {
        double[] denseTestData = DOUBLEVECTOR.denseArray(50);
        // put at least one negative value in the test data
        denseTestData[5] = -123d;
        // put a zero value in the test data
        denseTestData[10] = 0d;
        DimensionlessVector dlv =
                DoubleVector.instantiate(denseTestData, DimensionlessUnit.BASE.getStandardUnit(), StorageType.DENSE);
        verifyDimensionLessVector(denseTestData, new DoubleFunction()
        {
            @Override
            public double apply(final double value)
            {
                return value;
            }
        }, dlv);
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ABS, dlv.mutable().abs());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ACOS, dlv.mutable().acos());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ASIN, dlv.mutable().asin());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.ATAN, dlv.mutable().atan());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.CBRT, dlv.mutable().cbrt());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.CEIL, dlv.mutable().ceil());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.COS, dlv.mutable().cos());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.COSH, dlv.mutable().cosh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.EXP, dlv.mutable().exp());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.EXPM1, dlv.mutable().expm1());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.FLOOR, dlv.mutable().floor());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.INV, dlv.mutable().inv());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG, dlv.mutable().log());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG10, dlv.mutable().log10());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.LOG1P, dlv.mutable().log1p());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.NEG, dlv.mutable().neg());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.RINT, dlv.mutable().rint());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SIGNUM, dlv.mutable().signum());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SIN, dlv.mutable().sin());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SINH, dlv.mutable().sinh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.SQRT, dlv.mutable().sqrt());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.TAN, dlv.mutable().tan());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.TANH, dlv.mutable().tanh());
        verifyDimensionLessVector(denseTestData, DoubleMathFunctions.POW((float) Math.PI), dlv.mutable().pow(Math.PI));
    }

    /**
     * Verify the contents of a FloatDimensionlessVector.
     * @param reference double[]; the values on which the <code>operation</code> needs to be applied to get the values that must
     *            be verified
     * @param operation FloatFunction; the operation that converts the <code>reference</code> values to the values that must be
     *            verified
     * @param got FloatDimensionlessVector; the values that must be verified
     */
    public static void verifyDimensionLessVector(final double[] reference, final DoubleFunction operation,
            final DimensionlessVector got)
    {
        assertEquals("item count matches", reference.length, got.size());
        assertEquals("unit is DimensionLessUnit", DimensionlessUnit.BASE.getStandardUnit(),
                got.getDisplayUnit().getStandardUnit());
        for (int index = 0; index < reference.length; index++)
        {
            double expect = operation.apply(reference[index]);
            double tolerance = Math.abs(expect / 10000d);
            assertEquals("value must match", expect, got.getSI(index), tolerance);
        }
    }

}
