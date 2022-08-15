package org.djunits.unit.si;

import java.io.Serializable;

import org.djunits.Throw;

/**
 * SIPrefix contains information about one prefix, such as M for mega with the value 1.0E6.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class SIPrefix implements Serializable
{
    /** */
    private static final long serialVersionUID = 20190821L;

    /** The default textual prefix abbreviation, such as "M" for mega and "da" for deca. */
    private final String defaultTextualPrefix;

    /** The default display prefix abbreviation, such as "\u03BC" for micro. */
    private final String defaultDisplayPrefix;

    /** The prefix name such as "mega" or "deca". */
    private final String prefixName;

    /** The factor that the SI prefix represents, such as 1.0E6 for mega. */
    private final double factor;

    /**
     * Construct an SI prefix.
     * @param defaultTextualPrefix String; the prefix abbreviation, duch as "M" for mega and "da" for deca
     * @param prefixName String; the prefix name such as "mega" or "deca"
     * @param factor double; the factor that the SI prefix represents, such as 1.0E6 for mega
     * @param defaultDisplayPrefix the display prefix; "\u03BC" for micro
     */
    public SIPrefix(final String defaultTextualPrefix, final String prefixName, final double factor,
            final String defaultDisplayPrefix)
    {
        Throw.whenNull(defaultTextualPrefix, "SIPrefix.defaultTextualPrefix cannot be null");
        Throw.whenNull(prefixName, "SIPrefix.prefixName cannot be null");
        Throw.whenNull(defaultDisplayPrefix, "SIPrefix.defaultDisplayPrefix cannot be null");
        Throw.when(factor == 0, SIRuntimeException.class, "SIPrefix.factor cannot be 0");
        this.defaultTextualPrefix = defaultTextualPrefix;
        this.prefixName = prefixName;
        this.factor = factor;
        this.defaultDisplayPrefix = defaultDisplayPrefix;
    }

    /**
     * Construct an SI prefix with the defaultDisplayPrefix equal to the defaultTextualPrefix.
     * @param defaultTextualPrefix String; the prefix abbreviation, duch as "M" for mega and "da" for deca
     * @param prefixName String; the prefix name such as "mega" or "deca"
     * @param factor double; the factor that the SI prefix represents, such as 1.0E6 for mega
     */
    public SIPrefix(final String defaultTextualPrefix, final String prefixName, final double factor)
    {
        this(defaultTextualPrefix, prefixName, factor, defaultTextualPrefix);
    }

    /**
     * Retrieve the default textual prefix.
     * @return String; the default textual prefix
     */
    public String getDefaultTextualPrefix()
    {
        return this.defaultTextualPrefix;
    }

    /**
     * Retrieve the prefix name.
     * @return String; the prefix name
     */
    public String getPrefixName()
    {
        return this.prefixName;
    }

    /**
     * Retrieve the factor.
     * @return double; the factor
     */
    public double getFactor()
    {
        return this.factor;
    }

    /**
     * Retrieve the default display prefix.
     * @return String; the default display prefix
     */
    public String getDefaultDisplayPrefix()
    {
        return this.defaultDisplayPrefix;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SIPrefix [defaultTextualPrefix=" + this.defaultTextualPrefix + ", defaultDisplayPrefix="
                + this.defaultDisplayPrefix + ", prefixName=" + this.prefixName + ", factor=" + this.factor + "]";
    }

}
