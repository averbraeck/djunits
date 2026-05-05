package org.djunits.formatter;

import org.djunits.formatter.FormatContext.FloatFormatMode;

/**
 * VectorFormat stores the settings that influence both the value part and the unit part of an output string when formatting a
 * row or column vector. The inner subclasses {@link VectorFormat.Col} and {@link VectorFormat.Row} take care of the formatting
 * of column vectors and row vectors specifically.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <F> the vector format type, Row or Col
 */
public abstract class VectorFormat<F extends VectorFormat<F>> extends Format<F, VectorFormatContext>
{
    /**
     * Construct a VectorFormat object with a given context. Note that the context can be an existing context that is being
     * modified or a default context.
     * @param ctx the vector format context to use
     */
    protected VectorFormat(final VectorFormatContext ctx)
    {
        super(ctx);
    }

    /**
     * Set the start symbol to use for a vector, e.g., "[".
     * @param startSymbol new startSymbol for a vector
     * @return VectorFormat object for fluent design
     */
    public F setStartSymbol(final String startSymbol)
    {
        this.ctx.startSymbol = startSymbol;
        return self();
    }

    /**
     * Set the end symbol to use for a vector, e.g., "]".
     * @param endSymbol new endSymbol for a vector
     * @return VectorFormat object for fluent design
     */
    public F setEndSymbol(final String endSymbol)
    {
        this.ctx.endSymbol = endSymbol;
        return self();
    }

    /**
     * Set the separator symbol to use for a vector, e.g., ", ".
     * @param separatorSymbol new separatorSymbol for a vector
     * @return VectorFormat object for fluent design
     */
    public F setSeparatorSymbol(final String separatorSymbol)
    {
        this.ctx.separatorSymbol = separatorSymbol;
        return self();
    }

    /**
     * Set the vector prefix, e.g., "Col".
     * @param vectorPrefix new vector prefix
     * @return VectorFormat object for fluent design
     */
    public F setVectorPrefix(final String vectorPrefix)
    {
        this.ctx.vectorPrefix = vectorPrefix;
        return self();
    }

    /**
     * VectorFormat.Col stores the settings that influence both the value part and the unit part of an output string when
     * formatting a column vector.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Col extends VectorFormat<Col>
    {
        /** The defaults for formatting a column vector (which can be changed). */
        @SuppressWarnings("checkstyle:staticvariablename")
        private static VectorFormatContext DEFAULT = makeDefault();

        /**
         * Make column default.
         * @return a new default for the Column VectorFormatContext
         */
        private static VectorFormatContext makeDefault()
        {
            var vfc = new VectorFormatContext();
            vfc.startSymbol = "[\n";
            vfc.endSymbol = "]";
            vfc.separatorSymbol = "\n";
            vfc.vectorPrefix = "";
            vfc.formatMode = FloatFormatMode.FIXED_WITH_SCI_FALLBACK;
            return vfc;
        }

        /**
         * Construct a VectorFormat object with a given context. Note that the context can be an existing context that is being
         * modified or a default context.
         * @param ctx the vector format context to use
         */
        protected Col(final VectorFormatContext ctx)
        {
            super(ctx);
        }

        /**
         * Return an instance of VectorFormat.Col for row vectors, initialized with the default values.
         * @return an instance of VectorFormat.Col for row vectors, initialized with the default values
         */
        public static VectorFormat.Col defaults()
        {
            return new VectorFormat.Col(DEFAULT.clone());
        }

        /**
         * Return an instance of VectorFormat.Col with the DEFAULT_ROW values, which can be changed for all subsequent calls.
         * @return an instance of VectorFormat.Col with the DEFAULT_ROW values
         */
        public static VectorFormat.Col changeDefaults()
        {
            return new VectorFormat.Col(DEFAULT);
        }

        /**
         * Reset the default values of VectorFormat.Col for row vectors to their original values.
         */
        public static void resetDefaults()
        {
            DEFAULT = makeDefault();
        }
    }

    /**
     * VectorFormat.Row stores the settings that influence both the value part and the unit part of an output string when
     * formatting a row vector.
     * <p>
     * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Row extends VectorFormat<Row>
    {
        /** The defaults for formatting a row vector (which can be changed). */
        @SuppressWarnings("checkstyle:staticvariablename")
        private static VectorFormatContext DEFAULT = makeDefault();

        /**
         * Make row default.
         * @return a new default for the Row VectorFormatContext
         */
        static VectorFormatContext makeDefault()
        {
            var vfc = new VectorFormatContext();
            vfc.formatMode = FloatFormatMode.VARIABLE_LENGTH;
            vfc.startSymbol = "[";
            vfc.endSymbol = "]";
            vfc.separatorSymbol = ", ";
            vfc.vectorPrefix = "";
            return vfc;
        }

        /**
         * Construct a VectorFormat object with a given context. Note that the context can be an existing context that is being
         * modified or a default context.
         * @param ctx the vector format context to use
         */
        protected Row(final VectorFormatContext ctx)
        {
            super(ctx);
        }

        /**
         * Return an instance of VectorFormat.Row for row vectors, initialized with the default values.
         * @return an instance of VectorFormat.Row for row vectors, initialized with the default values
         */
        public static VectorFormat.Row defaults()
        {
            return new VectorFormat.Row(DEFAULT.clone());
        }

        /**
         * Return an instance of VectorFormat.Row with the DEFAULT_ROW values, which can be changed for all subsequent calls.
         * @return an instance of VectorFormat.Row with the DEFAULT_ROW values
         */
        public static VectorFormat.Row changeDefaults()
        {
            return new VectorFormat.Row(DEFAULT);
        }

        /**
         * Reset the default values of VectorFormat.Row for row vectors to their original values.
         */
        public static void resetDefaults()
        {
            DEFAULT = makeDefault();
        }
    }

}
