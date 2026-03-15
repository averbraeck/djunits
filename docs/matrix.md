# Matrix with quantities

Vectors and Matrices are implemented in four different ways: Sparse or Dense data storage, combined with Double or Float precision, which gives four combinations. Sparse storage should be used for vectors or matrices that contain many zero values. Dense data storage would, in that case, store all the zeroes, whereas in a sparse storage only the numbers unequal to zero are stored, together with an index. As the index adds some overhead, sparse storage makes only sense when the number of zeroes is over 50% of the number of cells. 


## Matrix types

Several implementations of matrices exist, which are shown in the UML class diagram below:

![](images/matrix.png)

As can be seen, the abstract class `Matrix` extends the abstract class `VectorMatrix`, which contains numerous methods for methods that are common to matrices, vectors and tables. Matrix multiplication is explicitly missing in the `VectorMatrix` class, since `QuantityTable` that extends `VectorMatrix` should not include matrix multiplication. The `Matrix` class adds matrix multiplication. Square matrices as defined in the `SquareMatrix` abstract class contain additional methods that only make sense for square matrices, such as `determinant()`, `trace()`, `inverse()`, and `adjugate()`. 

The generic type of `Matrix` of any size is the `MatrixNxM`. This matrix can use sparse or dense storage, and be populated with single-precision `float` values or double precision `double` values. 

The generic type of `SquareMatrix` of any size is `MatrixNxN`. This matrix can also use sparse or dense storage, and store `float` values or `double` values. For efficiency reasons, since the `MatrixNxN` carries quite some overhead for the flexible data storage, separate classes are defined for `Matrix1x1`, `Matrix2x2`, and `Matrix3x3`. Data inside these matrices is stored in a dense `double[]` array that uses row-major indexing.


## Matrix operations

A `Matrix` implements the `Hadamard` interface for element-wise operations. These include:

- `invertElements()`: Invert the matrix on an element-by-element basis (1/value), where the unit will also be inverted. The inversion of a `Duration` matrix will result in a matrix of the same type (1x1, 2x2, 3x3, NxN, NxM) and size (number of rows and columns), with a unit if `1/s`, corresponding to a `Frequency` matrix. 
- `multiplyElements(Matrix other)`: Multiply the elements of this matrix on an element-by-element basis with those of another matrix of the same type and size (but possibly representing another quantity).
- `divideElements(Matrix other)`: Divide the elements of this matrix on an element-by-element basis by those of another matrix of the same type and size (but possibly representing another quantity).
- `multiplyElements(Quantity<?, ?> quantity)`: Multiply the elements of this matrix on an element-by-element basis with the provided quantity.
- `divideElements(Quantity<?, ?> quantity)`: Divide the elements of this matrix on an element-by-element basis by those the provided quantity.

The result of a Hadamard operation on, e.g. a `MatrixNxM<Speed, Speed.Unit>` will typically be a `MatrixNxM<SIQuantity, SIUnit>` since the inverse operation, multiplication or division will result in a Matrix with a unit that is unknown on beforehand and cannot be determined by the compiler. In the above example of `invertElements` for a `Duration` matrix, the resulting matrix can be transformed into a proper `MatrixNxM<Frequency, Frequency.Unit>` matrix using the `as(Frequency.Unit.Hz)` method.

Furthermore, a matrix is `Additive`, which means that matrices of the same type, size, and quantity can be added to and subtracted from each other. Matrices also implement the `Scalable` interface, which exposes the `scaleBy(double factor)` and `divideBy(double factor)` methods.

Many of the matrix operations are delegated to the mathematics utility classes `ArrayMath` and `MatrixMath`, which can be found in the `org.djunits.util` package.


## Example matrix definition and usage

The example below shows the instantiation and usage of a `MatrixNxM`:

```java
MatrixNxM<Length, Length.Unit> lm4x2 = 
    MatrixNxM.of(new double[][] {{1, 2, 3, 4}, {5, 6, 7, 8}}, Length.Unit.m);
MatrixNxM<Length, Length.Unit> lm2x4 = 
    MatrixNxM.of(new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}}, Length.Unit.m);

var mult44 = lm4x2.multiply(lm2x4).as(Area.Unit.m2);
System.out.println("\nMatrix1:\n" + lm4x2);
System.out.println("Matrix2:\n" + lm2x4);
System.out.println("Multiplication:\n" + mult44);

Matrix2x2<Area, Area.Unit> mult22 = 
    lm4x2.multiply(lm2x4).asMatrix2x2().as(Area.Unit.a);
System.out.println("\nMatrix1:\n" + lm2x4);
System.out.println("Matrix2:\n" + lm4x2);
System.out.println("Multiplication:\n" + mult22);
```

The above code prints the following:

```
Matrix1:
[1.00000000, 2.00000000, 3.00000000, 4.00000000
 5.00000000, 6.00000000, 7.00000000, 8.00000000] m
Matrix2:
[1.00000000, 2.00000000
 3.00000000, 4.00000000
 5.00000000, 6.00000000
 7.00000000, 8.00000000] m
Multiplication:
[50.0000000, 60.0000000
 114.000000, 140.000000] m2

Matrix1:
[1.00000000, 2.00000000
 3.00000000, 4.00000000
 5.00000000, 6.00000000
 7.00000000, 8.00000000] m
Matrix2:
[1.00000000, 2.00000000, 3.00000000, 4.00000000
 5.00000000, 6.00000000, 7.00000000, 8.00000000] m
Multiplication:
[0.50000000, 0.60000000
 1.14000000, 1.40000000] a
```

As can be seen, the multiplication of a 2x4 `Length` with a 4x2 `Length` matrix results in a 2x2 `Area` matrix. The 2x2 matrix can be 'cast' to a true `Matrix2x2` class with more efficient storage and operations. When printing the content of a matrix, Area units such as are can be used.

Similarly, when multiplying in the opposite way, a 4x2 `Length` with a 2x4 `Length` matrix results in a 4x4 `Area` matrix. This matrix is of type `MatrixNxM` and could be case to a `MatrixNxN` with the `asMatrixNxN()` method, since it is a square matrix. This cast would open the matrix for operations such as inverse, trace and determinant, which are not defined for the `MatrixNxM`. 
