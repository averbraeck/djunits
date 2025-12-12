package org.djunits.value.vdouble.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.djunits.quantity.Quantities;
import org.djunits.quantity.Quantity;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UNITS;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.CLASSNAMES;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djunits.value.vdouble.vector.base.DoubleVectorRel;
import org.junit.jupiter.api.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class DoubleSIMatrixTest
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
     * @throws InstantiationException on error
     * @param <U> the unit type
     * @param <S> the scalar type
     * @param <V> the vector type
     * @param <M> the matrix type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U>, S extends DoubleScalarRel<U, S>, V extends DoubleVectorRel<U, S, V>,
            M extends DoubleMatrixRel<U, S, V, M>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        double[][] denseTestData = DOUBLEMATRIX.denseRectArrays(5, 10, false);
        DimensionlessMatrix dimlessMatrix = new DimensionlessMatrix(denseTestData, DimensionlessUnit.SI, StorageType.DENSE);
        dimlessMatrix = dimlessMatrix.mutable().divide(dimlessMatrix).asDimensionless(); // unit matrix
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (U unit : quantity.getUnitsById().values())
            {
                Constructor<DoubleMatrix<U, S, V, M>> constructorDUS = (Constructor<DoubleMatrix<U, S, V, M>>) CLASSNAMES
                        .doubleMatrixClass(type).getConstructor(double[][].class, unit.getClass(), StorageType.class);
                DoubleMatrixRel<U, S, V, M> matrix =
                        (DoubleMatrixRel<U, S, V, M>) constructorDUS.newInstance(denseTestData, unit, StorageType.DENSE);
                SIMatrix mult = matrix.times(dimlessMatrix);
                Method asMethod = SIMatrix.class.getDeclaredMethod("as" + type);
                DoubleMatrixRel<U, S, V, M> asMatrix = (DoubleMatrixRel<U, S, V, M>) asMethod.invoke(mult);
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrix.getDisplayUnit());
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(matrix.getSI(row, col), asMatrix.getSI(row, col), matrix.getSI(row, col) / 1000.0,
                                type + ", unit: " + unit.getDefaultTextualAbbreviation());
                    }
                }

                Method asMethodDisplayUnit = SIMatrix.class.getDeclaredMethod("as" + type, unit.getClass());
                DoubleMatrixRel<U, S, V, M> asMatrixDisplayUnit =
                        (DoubleMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrixDisplayUnit.getDisplayUnit());

                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(matrix.getSI(row, col), asMatrixDisplayUnit.getSI(row, col),
                                matrix.getSI(row, col) / 1000.0, type + ", unit: " + unit.getDefaultTextualAbbreviation());
                    }
                }

                // test exception for wrong 'as'
                SIMatrix cd4sr2 = new SIMatrix(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    DoubleMatrixRel<U, S, V, M> asMatrixDim = (DoubleMatrixRel<U, S, V, M>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    DoubleMatrixRel<U, S, V, M> asMatrixDim =
                            (DoubleMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(cd4sr2, matrix.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
                SIMatrix sim =
                        SIMatrix.of(denseTestData, quantity.getSiDimensions().toString(true, true, true), StorageType.DENSE);
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(denseTestData[row][col], sim.getInUnit(row, col), 0.001,
                                type + ", unit: " + unit.getDefaultTextualAbbreviation());
                    }
                }
            }
        }
    }

}
