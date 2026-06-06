package org.djunits.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SuppressFBWarnings allows to suppress FindBug warnings without any library imports. Source:
 * https://stackoverflow.com/a/44947252.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings
{
    /**
     * The set of FindBugs warnings that are to be suppressed in annotated element. The value can be a bug category, kind or
     * pattern.
     * @return the warnings that are to be suppressed.
     */
    String[] value() default {};

    /**
     * Optional documentation of the reason why the warning is suppressed.
     * @return the justification for suppression
     */
    String justification() default "";
}
