package org.djunits.quantity.def;

import org.djutils.base.Identifiable;

/**
 * Reference contains information about the reference point or origin / zero point of an absolute quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public interface Reference extends Identifiable
{
    /** 
     * Return a name or explanation for the reference. 
     * @return a name or explanation for the reference
     */
    String getName();
    
}
