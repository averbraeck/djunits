/**
 * Interfaces, abstract classes and utilities for FloatMatrix. Matrix values should be accessible by row and column. The way to
 * access values in a matrix in algebra is as follows:
 * 
 * <pre>
 * |<i> a<sub>11</sub> a<sub>12</sub> ... a<sub>1n</sub> </i>|
 * |<i> a<sub>21</sub> a<sub>22</sub> ... a<sub>2n</sub> </i>|
 * |<i> .<sub>..</sub> .<sub>..</sub> ... .<sub>..</sub> </i>|
 * |<i> a<sub>m1</sub> a<sub>m2</sub> ... a<sub>mn</sub> </i>|
 * </pre>
 * 
 * where m is the number of rows and n is the number of columns. <i>a<sub>ij</sub></i> is a value in the matrix where i ranges
 * from 1 to m, and j ranges from 1 to n. This is known as an m x n matrix, with (m, n) as its size. So when a float array f[][]
 * is passed to fill a matrix, the most logical is that the first index indicates the row, and the second index indicates the
 * column, so f[row][column]. In Java this means that the matrix needs to be initialized with <code><b>new</b> f[rows]</code>
 * and each row needs to be initialized with <code><b>new</b> f[r][columns]</code>.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
package org.djunits.value.vfloat.matrix.base;
