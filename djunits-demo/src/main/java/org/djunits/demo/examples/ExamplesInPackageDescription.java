package org.djunits.demo.examples;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.util.UnitException;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.SIScalar;
import org.djunits.value.vdouble.vector.AbsoluteTemperatureVector;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.vector.FloatAreaVector;
import org.djunits.value.vfloat.vector.FloatSIVector;
import org.djunits.value.vfloat.vector.base.FloatVector;

/**
 * Test code for package list page.
 */
public final class ExamplesInPackageDescription
{
    /** Do not instantiate. */
    private ExamplesInPackageDescription()
    {
        // Do not instantiate.
    }

    /**
     * Demo code for the package list page.
     * @param args String[]; not used
     * @throws UnitException on error creating unit
     * @throws ValueRuntimeException when instantiate of a Value fails
     */
    public static void main(final String[] args) throws ValueRuntimeException, UnitException
    {
        Length l1 = new Length(100.0, LengthUnit.KILOMETER);
        System.out.println(l1);
        Length l2 = Length.valueOf("100.0 km");
        System.out.println(l2);
        Length l3 = Length.of(100.0, "km");
        System.out.println(l3);
        Length l4 = SIScalar.of(100000.0, "m").asLength(LengthUnit.KILOMETER);
        System.out.println(l4);
        Length l5 = SIScalar.valueOf("100000.0 m").asLength(LengthUnit.KILOMETER);
        System.out.println(l5);
        Length l6 = SIScalar.valueOf("100000.0 m").asLength();
        l6.setDisplayUnit(LengthUnit.KILOMETER);
        Length l7 = SIScalar.valueOf("100000.0 m").as(LengthUnit.KILOMETER);
        System.out.println(l7);
        System.out.println(l1.equals(l2));
        System.out.println(l1.equals(l3));
        System.out.println(l1.equals(l4));
        System.out.println(l1.equals(l5));
        System.out.println(l1.equals(l6));
        System.out.println(l1.equals(l7));

        System.out.println();

        FloatLength fl1 = new FloatLength(100.0f, LengthUnit.KILOMETER);
        System.out.println(fl1);
        FloatLength fl2 = FloatLength.valueOf("100.0 km");
        System.out.println(fl2);
        FloatLength fl3 = FloatLength.of(100.0f, "km");
        System.out.println(fl3);
        FloatLength fl4 = FloatSIScalar.of(100000.0f, "m").asLength(LengthUnit.KILOMETER);
        System.out.println(fl4);
        FloatLength fl5 = FloatSIScalar.valueOf("100000.0 m").asLength(LengthUnit.KILOMETER);
        System.out.println(fl5);
        FloatLength fl6 = FloatSIScalar.valueOf("100000.0 m").asLength();
        fl6.setDisplayUnit(LengthUnit.KILOMETER);
        FloatLength fl7 = FloatSIScalar.valueOf("100000.0 m").as(LengthUnit.KILOMETER);
        System.out.println(fl7);
        System.out.println(fl6);
        System.out.println(l6);
        System.out.println(fl1.equals(fl2));
        System.out.println(fl1.equals(fl3));
        System.out.println(fl1.equals(fl4));
        System.out.println(fl1.equals(fl5));
        System.out.println(fl1.equals(fl6));
        System.out.println(fl1.equals(fl7));

        AbsoluteTemperatureVector tv1 = DoubleVector.instantiate(new double[] {273.15, 290.4, 280.5, 279.1},
                AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
        System.out.println(tv1);
        // AbsoluteTemperatureVector tv2 = SIVector.instantiate(new double[] { 273.15, 290.4, 280.5, 279.1 },
        // SIUnit.of("K"), StorageType.DENSE).asAbsoluteTemperature());
        // System.out.println(tv2);

        System.out.println();
        float[] values = new float[] {10.0f, 0.0f, 10.0f, 0.0f, 0.0f, 20.0f, 0.0f};
        FloatAreaVector fv = FloatVector.instantiate(values, AreaUnit.SQUARE_KILOMETER, StorageType.SPARSE);
        FloatAreaVector fv2 = fv.mutable().neg();
        System.out.println(fv);
        System.out.println(fv2);
        FloatSIVector.instantiate(values, null, StorageType.SPARSE);
    }

}
