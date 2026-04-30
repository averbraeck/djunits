package org.djunits.unit.si;

/**
 * PrefixType describes the type of prefix, e.g., KILO, or PER_KILO.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public enum PrefixType
{
    /** Unit. */
    UNIT,
    
    /** Per unit. */
    PER_UNIT,
    
    /** Kilo. */
    KILO,
    
    /** Per kilo. */
    PER_KILO,
}
