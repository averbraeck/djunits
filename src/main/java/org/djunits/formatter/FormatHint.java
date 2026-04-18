package org.djunits.formatter;

/**
 * FormatHint contains a number of settings to format a quantity, vector, matrix or quantity table. It is called a 'Hint'
 * because the required format cannot always be guaranteed; e.g., a maximum width for the output can be incompatible with other
 * formatting settings that cause a wide output. The FormatHints will be processed in a given order, to try to satisfy most.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public interface FormatHint
{
    /**
     * Apply a hint to the format context that stores the context settings.
     * @param ctx the settings repository
     */
    void applyTo(FormatContext ctx);
}
