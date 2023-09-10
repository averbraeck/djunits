package org.djunits.value.vfloat.matrix;

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
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.junit.jupiter.api.Test;

/**
 * Test.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatSIMatrixTest
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
    public <U extends Unit<U>, S extends FloatScalarRel<U, S>, V extends FloatVectorRel<U, S, V>,
            M extends FloatMatrixRel<U, S, V, M>> void testAsAll()
                    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
                    InvocationTargetException, ClassNotFoundException, UnitException, InstantiationException
    {
        // load all classes
        assertEquals("m", UNITS.METER.getId());

        float[][] denseTestData = FLOATMATRIX.denseRectArrays(5, 10, false);
        FloatDimensionlessMatrix dimlessMatrix =
                new FloatDimensionlessMatrix(denseTestData, DimensionlessUnit.SI, StorageType.DENSE);
        dimlessMatrix = dimlessMatrix.mutable().divide(dimlessMatrix).asDimensionless(); // unit matrix
        for (String type : CLASSNAMES.REL_ALL_LIST)
        {
            Class.forName("org.djunits.unit." + type + "Unit");
            Quantity<U> quantity = (Quantity<U>) Quantities.INSTANCE.getQuantity(type + "Unit");
            for (U unit : quantity.getUnitsById().values())
            {
                Constructor<FloatMatrix<U, S, V, M>> constructorFUS = (Constructor<FloatMatrix<U, S, V, M>>) CLASSNAMES
                        .floatMatrixClass(type).getConstructor(float[][].class, unit.getClass(), StorageType.class);
                FloatMatrixRel<U, S, V, M> matrix =
                        (FloatMatrixRel<U, S, V, M>) constructorFUS.newInstance(denseTestData, unit, StorageType.DENSE);
                FloatSIMatrix mult = matrix.times(dimlessMatrix);
                Method asMethod = FloatSIMatrix.class.getDeclaredMethod("as" + type);
                FloatMatrixRel<U, S, V, M> asMatrix = (FloatMatrixRel<U, S, V, M>) asMethod.invoke(mult);
                assertEquals(matrix.getDisplayUnit().getStandardUnit(), asMatrix.getDisplayUnit());
                for (int row = 0; row < denseTestData.length; row++)
                {
                    for (int col = 0; col < denseTestData[0].length; col++)
                    {
                        assertEquals(matrix.getSI(row, col), asMatrix.getSI(row, col), matrix.getSI(row, col) / 1000.0,
                                type + ", unit: " + unit.getDefaultTextualAbbreviation());
                    }
                }

                Method asMethodDisplayUnit = FloatSIMatrix.class.getDeclaredMethod("as" + type, unit.getClass());
                FloatMatrixRel<U, S, V, M> asMatrixDisplayUnit =
                        (FloatMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(mult, unit.getStandardUnit());
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
                FloatSIMatrix cd4sr2 = new FloatSIMatrix(denseTestData, SIUnit.of("cd4/sr2"), StorageType.DENSE);
                try
                {
                    FloatMatrixRel<U, S, V, M> asMatrixDim = (FloatMatrixRel<U, S, V, M>) asMethod.invoke(cd4sr2);
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }

                try
                {
                    FloatMatrixRel<U, S, V, M> asMatrixDim =
                            (FloatMatrixRel<U, S, V, M>) asMethodDisplayUnit.invoke(cd4sr2, matrix.getDisplayUnit());
                    fail("should not be able to carry out 'as'" + type + " on cd4/sr2 SI unit -- resulted in " + asMatrixDim);
                }
                catch (InvocationTargetException | UnitRuntimeException e)
                {
                    // ok
                }
                FloatSIMatrix sim = FloatSIMatrix.of(denseTestData, quantity.getSiDimensions().toString(true, true, true),
                        StorageType.DENSE);
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
