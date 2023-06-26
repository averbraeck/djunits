package org.djunits.value.vdouble.matrix;

import java.util.Arrays;

/**
 * Compute the determinant of a matrix.
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class Determinant
{
    /** */
    private Determinant()
    {
        // Utility class
    }

    /**
     * Method that calculates determinant of given matrix. From:
     * https://gist.githubusercontent.com/Cellane/398372/raw/23a3e321daa52d4c6b68795aae093bf773ce2940/MatrixOperations.java
     * @param matrix double[][]; matrix of which we need to know the determinant
     * @return double; the determinant of given matrix
     */
    public static double det(final double[][] matrix)
    {
        double[][] temporary;
        double result = 0;
        if (matrix.length == 1)
        {
            result = matrix[0][0];
            return (result);
        }
        if (matrix.length == 2)
        {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (result);
        }
        for (int i = 0; i < matrix[0].length; i++)
        {
            temporary = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++)
            {
                for (int k = 0; k < matrix[0].length; k++)
                {
                    if (k < i)
                    {
                        temporary[j - 1][k] = matrix[j][k];
                    }
                    else if (k > i)
                    {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, i) * det(temporary);
        }
        return (result);
    }

    /**
     * Method that calculates determinant of given matrix. From:
     * https://gist.githubusercontent.com/Cellane/398372/raw/23a3e321daa52d4c6b68795aae093bf773ce2940/MatrixOperations.java
     * @param matrix float[][]; matrix of which we need to know the determinant
     * @return float; the determinant of given matrix
     */
    public static float det(final float[][] matrix)
    {
        float[][] temporary;
        float result = 0;
        if (matrix.length == 1)
        {
            result = matrix[0][0];
            return (result);
        }
        if (matrix.length == 2)
        {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (result);
        }
        for (int i = 0; i < matrix[0].length; i++)
        {
            temporary = new float[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++)
            {
                for (int k = 0; k < matrix[0].length; k++)
                {
                    if (k < i)
                    {
                        temporary[j - 1][k] = matrix[j][k];
                    }
                    else if (k > i)
                    {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, i) * det(temporary);
        }
        return (result);
    }

    /**
     * Program entry point.
     * @param args String[]; the command line arguments (not used)
     */
    public static void main(final String[] args)
    {
        double[][] testData1 = new double[][] {{2.0}};
        double[][] testData2 = new double[][] {{2, 3}, {5, 7}};
        double[][] testData3 = new double[][] {{2, 3, 5}, {7, 11, 13}, {17, 19, 23}};
        double[][] testData4 = new double[][] {{2, 3, 5, 7}, {11, 13, 17, 19}, {23, 29, 31, 37}, {41, 43, 47, 49}};
        System.out.println(Arrays.deepToString(testData1));
        System.out.println("det=" + det(testData1));
        System.out.println(Arrays.deepToString(testData2));
        System.out.println("det=" + det(testData2));
        System.out.println(Arrays.deepToString(testData3));
        System.out.println("det=" + det(testData3));
        System.out.println(Arrays.deepToString(testData4));
        System.out.println("det=" + det(testData4));
    }

}
