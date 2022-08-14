package org.djunits.demo.examples;

import java.util.Locale;

import org.djunits.locale.DefaultLocale;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.util.UNITS;
import org.djunits.value.vdouble.scalar.Duration;

/**
 * This Java code demonstrates conversions between related unit using DJUNITS.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @version $Revision: 672 $, $LastChangedDate: 2019-10-18 14:32:01 +0200 (Fri, 18 Oct 2019) $, by $Author: averbraeck $,
 *          initial version 3 sep. 2015 <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class LocaleDemo implements UNITS
{
    /** */
    private LocaleDemo()
    {
        // utility constructor.
    }

    /**
     * Create some scalar values to demonstrate conversion from and to related units.
     * @param args String[]; the command line arguments; not used
     */
    public static void main(final String[] args)
    {
        DefaultLocale.setLocale(Locale.forLanguageTag("NL"));
        Duration hour = new Duration(3.0, DurationUnit.HOUR);
        System.out.println(hour.toTextualString());
        System.out.println(hour);
        DefaultLocale.setLocale(Locale.US);
        System.out.println(hour.toTextualString());
        System.out.println(hour);
    }
}
