package org.djunits.demo.examples;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Speed;

/** Example for adding a unit. */
public final class AddingAUnitExample
{
    /** */
    private AddingAUnitExample()
    {
        // utility constructor.
    }

    /** a new LengthUnit. */
    public static final LengthUnit FURLONG = LengthUnit.FOOT.deriveLinear(660.0, "fr", "Furlong");

    /** a new DurationUnit. */
    public static final DurationUnit FORTNIGHT = DurationUnit.DAY.deriveLinear(14.0, "fn", "Fortnight");

    /** a new SpeedUnit based on the new LengthUnit and the new DurationUnit. */
    public static final SpeedUnit FURLONGS_PER_FORTNIGHT =
            SpeedUnit.SI.deriveLinear(FURLONG.getScale().toStandardUnit(1.0) / FORTNIGHT.getScale().toStandardUnit(1.0),
                    "fr/fn", "Furlongs per Fortnight");

    /**
     * @param args String[]; can be blank
     */
    public static void main(final String[] args)
    {
        Length oneThousandFurlong = new Length(1000.0, FURLONG);
        System.out.println(oneThousandFurlong);
        Duration twoFortNight = new Duration(2.0, FORTNIGHT);
        System.out.println(twoFortNight);
        Speed speed = oneThousandFurlong.divide(twoFortNight);
        System.out.println(speed); // Uses SpeedUnit.SI; m/s
        System.out.println(speed.toString(FURLONGS_PER_FORTNIGHT));
    }

}
