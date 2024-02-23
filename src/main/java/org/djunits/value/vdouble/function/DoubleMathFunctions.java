package org.djunits.value.vdouble.function;

/**
 * DoubleMathFunctions provides a static implementation of Math functions.
 * <p>
 * This file was generated by the djunits value classes generator, 26 jun, 2015
 * <p>
 * Copyright (c) 2015-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public interface DoubleMathFunctions
{
    /**
     * Function that returns <code>Math.abs(a)</code>.
     */
    DoubleFunction ABS = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.abs(a);
        }
    };

    /**
     * Function that returns <code>Math.acos(a)</code>.
     */
    DoubleFunction ACOS = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.acos(a);
        }
    };

    /**
     * Function that returns <code>Math.asin(a)</code>.
     */
    DoubleFunction ASIN = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.asin(a);
        }
    };

    /**
     * Function that returns <code>Math.atan(a)</code>.
     */
    DoubleFunction ATAN = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.atan(a);
        }
    };

    /**
     * Function that returns <code>Math.cbrt(a)</code>.
     */
    DoubleFunction CBRT = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.cbrt(a);
        }
    };

    /**
     * Function that returns <code>Math.ceil(a)</code>.
     */
    DoubleFunction CEIL = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.ceil(a);
        }
    };

    /**
     * Function that returns <code>Math.cos(a)</code>.
     */
    DoubleFunction COS = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.cos(a);
        }
    };

    /**
     * Function that returns <code>Math.cosh(a)</code>.
     */
    DoubleFunction COSH = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.cosh(a);
        }
    };

    /**
     * Function that returns <code>Math.exp(a)</code>.
     */
    DoubleFunction EXP = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.exp(a);
        }
    };

    /**
     * Function that returns <code>Math.expm1(a)</code>.
     */
    DoubleFunction EXPM1 = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.expm1(a);
        }
    };

    /**
     * Function that returns <code>Math.floor(a)</code>.
     */
    DoubleFunction FLOOR = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.floor(a);
        }
    };

    /**
     * Function that returns <code>Math.log(a)</code>.
     */
    DoubleFunction LOG = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.log(a);
        }
    };

    /**
     * Function that returns <code>Math.log10(a)</code>.
     */
    DoubleFunction LOG10 = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.log10(a);
        }
    };

    /**
     * Function that returns <code>Math.log1p(a)</code>.
     */
    DoubleFunction LOG1P = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.log1p(a);
        }
    };

    /**
     * Function that returns <code>-a</code>.
     */
    DoubleFunction NEG = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return 0d == a ? 0d : -a;
        }
    };

    /**
     * Function that returns <code>Math.pow(a, b)</code>.
     * @param b double; power parameter
     * @return power function
     */
    @SuppressWarnings("checkstyle:methodname")
    static DoubleFunction POW(final double b)
    {
        return new DoubleFunction()
        {
            @Override
            public double apply(final double a)
            {
                return Math.pow(a, b);
            }
        };
    }

    /**
     * Function that returns <code>Math.rint(a)</code>.
     */
    DoubleFunction RINT = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.rint(a);
        }
    };

    /**
     * Function that returns <code>Math.signum(a)</code>.
     */
    DoubleFunction SIGNUM = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.signum(a);
        }
    };

    /**
     * Function that returns <code>Math.sin(a)</code>.
     */
    DoubleFunction SIN = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.sin(a);
        }
    };

    /**
     * Function that returns <code>Math.sinh(a)</code>.
     */
    DoubleFunction SINH = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.sinh(a);
        }
    };

    /**
     * Function that returns <code>Math.sqrt(a)</code>.
     */
    DoubleFunction SQRT = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.sqrt(a);
        }
    };

    /**
     * Function that returns <code>Math.tan(a)</code>.
     */
    DoubleFunction TAN = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.tan(a);
        }
    };

    /**
     * Function that returns <code>Math.tanh(a)</code>.
     */
    DoubleFunction TANH = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return Math.tanh(a);
        }
    };

    /**
     * Function that returns <code>1/a</code>.
     */
    DoubleFunction INV = new DoubleFunction()
    {
        @Override
        public double apply(final double a)
        {
            return 1 / a;
        }
    };

    /**
     * Function that returns <code>a + b</code>.
     * @param b double; increment
     * @return power function
     */
    @SuppressWarnings("checkstyle:methodname")
    static DoubleFunction INC(final double b)
    {
        return new DoubleFunction()
        {
            @Override
            public double apply(final double a)
            {
                return a + b;
            }
        };
    }

    /**
     * Function that returns <code>a - b</code>.
     * @param b double; decrement
     * @return power function
     */
    @SuppressWarnings("checkstyle:methodname")
    static DoubleFunction DEC(final double b)
    {
        return new DoubleFunction()
        {
            @Override
            public double apply(final double a)
            {
                return a - b;
            }
        };
    }

    /**
     * Function that returns <code>a * b</code>.
     * @param b double; multiplier
     * @return power function
     */
    @SuppressWarnings("checkstyle:methodname")
    static DoubleFunction MULT(final double b)
    {
        return new DoubleFunction()
        {
            @Override
            public double apply(final double a)
            {
                return a * b;
            }
        };
    }

    /**
     * Function that returns <code>a / b</code>.
     * @param b double; divisor
     * @return power function
     */
    @SuppressWarnings("checkstyle:methodname")
    static DoubleFunction DIV(final double b)
    {
        return new DoubleFunction()
        {
            @Override
            public double apply(final double a)
            {
                return a / b;
            }
        };
    }
}
