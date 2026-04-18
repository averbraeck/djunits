package org.djunits.unit.si;

import org.djutils.exceptions.Throw;

/**
 * SIPrefix contains information about one prefix, such as M for mega with the value 1.0E6.
 * <p>
 * Copyright (c) 2019-2026 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * @author Alexander Verbraeck
 */
public class SIPrefix
{
    /** The default textual prefix abbreviation, such as "M" for mega and "da" for deca. */
    private final String defaultTextualPrefix;

    /** The default display prefix abbreviation, such as "\u03BC" for micro. */
    private final String defaultDisplayPrefix;

    /** The prefix name such as "mega" or "deca". */
    private final String prefixName;

    /** The factor that the SI prefix represents, such as 1.0E6 for mega. */
    private final double factor;
    
    /** The type of SI prefix. */
    private final PrefixType type;

    /**
     * Construct an SI prefix.
     * @param defaultTextualPrefix the prefix abbreviation, duch as "M" for mega and "da" for deca
     * @param prefixName the prefix name such as "mega" or "deca"
     * @param factor the factor that the SI prefix represents, such as 1.0E6 for mega
     * @param defaultDisplayPrefix "\u03BC" for micro
     * @param type the prefix type, e.g., KILO
     */
    public SIPrefix(final String defaultTextualPrefix, final String prefixName, final double factor,
            final String defaultDisplayPrefix, final PrefixType type)
    {
        Throw.whenNull(defaultTextualPrefix, "SIPrefix.defaultTextualPrefix cannot be null");
        Throw.whenNull(prefixName, "SIPrefix.prefixName cannot be null");
        Throw.whenNull(defaultDisplayPrefix, "SIPrefix.defaultDisplayPrefix cannot be null");
        Throw.whenNull(type, "SIPrefix.type cannot be null");
        Throw.when(factor == 0, IllegalArgumentException.class, "SIPrefix.factor cannot be 0");
        this.defaultTextualPrefix = defaultTextualPrefix;
        this.prefixName = prefixName;
        this.factor = factor;
        this.defaultDisplayPrefix = defaultDisplayPrefix;
        this.type = type;
    }

    /**
     * Construct an SI prefix with the defaultDisplayPrefix equal to the defaultTextualPrefix.
     * @param defaultTextualPrefix the prefix abbreviation, duch as "M" for mega and "da" for deca
     * @param prefixName the prefix name such as "mega" or "deca"
     * @param factor the factor that the SI prefix represents, such as 1.0E6 for mega
     * @param type the prefix type, e.g., KILO
     */
    public SIPrefix(final String defaultTextualPrefix, final String prefixName, final double factor, final PrefixType type)
    {
        this(defaultTextualPrefix, prefixName, factor, defaultTextualPrefix, type);
    }

    /**
     * Retrieve the default textual prefix.
     * @return the default textual prefix
     */
    public String getDefaultTextualPrefix()
    {
        return this.defaultTextualPrefix;
    }

    /**
     * Retrieve the prefix name.
     * @return the prefix name
     */
    public String getPrefixName()
    {
        return this.prefixName;
    }

    /**
     * Retrieve the factor.
     * @return the factor
     */
    public double getFactor()
    {
        return this.factor;
    }

    /**
     * Retrieve the default display prefix.
     * @return the default display prefix
     */
    public String getDefaultDisplayPrefix()
    {
        return this.defaultDisplayPrefix;
    }

    /**
     * Retrieve the type.
     * @return the type
     */
    public PrefixType getType()
    {
        return this.type;
    }

    @Override
    public String toString()
    {
        return "SIPrefix [defaultTextualPrefix=" + this.defaultTextualPrefix + ", defaultDisplayPrefix="
                + this.defaultDisplayPrefix + ", prefixName=" + this.prefixName + ", factor=" + this.factor + "]";
    }

}
