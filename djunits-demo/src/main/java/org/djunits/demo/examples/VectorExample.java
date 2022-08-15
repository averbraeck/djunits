package org.djunits.demo.examples;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.scale.Scale;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.vector.LengthVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;

/** Demo for Vector. */
public final class VectorExample
{
    /** */
    private VectorExample()
    {
        // just a main method
    }

    /**
     * @param args String[]; not used
     */
    public static void main(final String[] args)
    {
        double[] data = new double[10];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = i * 0.8;
        }
        LengthVector lengthVector = DoubleVector.instantiate(data, LengthUnit.FOOT, StorageType.DENSE);
        lengthVector = lengthVector.mutable();
        System.out.println(lengthVector);
        lengthVector.rint();
        System.out.println(lengthVector);

        lengthVector = DoubleVector.instantiate(data, LengthUnit.FOOT, StorageType.DENSE);
        lengthVector = lengthVector.mutable();
        final Scale scale = lengthVector.getDisplayUnit().getScale();
        lengthVector.assign(new DoubleFunction()
        {
            @Override
            public double apply(final double value)
            {
                return scale.toStandardUnit(Math.rint(scale.fromStandardUnit(value)));
            }
        });
        System.out.println(lengthVector);
    }
}
