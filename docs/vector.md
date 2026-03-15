# Vector with quantities

## Introduction

Vectors and Matrices are implemented in four different ways: Sparse or Dense data storage, combined with Double or Float precision, which gives four combinations. Sparse storage should be used for vectors or matrices that contain many zero values. Dense data storage would, in that case, store all the zeroes, whereas in a sparse storage only the numbers unequal to zero are stored, together with an index. As the index adds some overhead, sparse storage makes only sense when the number of zeroes is over 50% of the number of cells. 


## Vector types

Vectors can be defined as row vectors or as column vectors. The difference is especially important for vector-vector multiplications and matrix-vector multiplications. `Row` and `Col` are defined as inner classes of the corresponding vectors. Several implementations of vectors exist, which are shown in the UML class diagram below:

![](images/vector.png)

As can be seen, the abstract class `Vector` extends the abstract class `Matrix`, where a row vector is a matrix with dimensions 1 x N, and a column vector is a matrix with dimensions N x 1. The fact that a vector is defined as a special type of matrix helps with matrix-vector multiplication. 

The generic type of `Vector` of any size is the `VectorN`. This vector can use sparse or dense storage, and be populated with single-precision `float` values or double precision `double` values. For efficiency reasons, since the `VectorN` carries quite some overhead for the flexible data storage, separate classes are defined for `Vector1` (no distinction between row and column version), `Vector2` and `Vector3`, both with a `Row` and `Col` extension. 


## Vector operations

A `Vector` implements the `Hadamard` interface for element-wise operations. These include:

- `invertElements()`: Invert the vector on an element-by-element basis (1/value), where the unit will also be inverted. The inversion of a `Duration` vector will result in a vector of the same type (row/column) and size, with a unit if `1/s`, corresponding to a `Frequency`. 
- `multiplyElements(Vector other)`: Multiply the elements of this vector on an element-by-element basis with those of another vector of the same type and size (but possibly representing another quantity).
- `divideElements(Vector other)`: Divide the elements of this vector on an element-by-element basis by those of another vector of the same type and size (but possibly representing another quantity).
- `multiplyElements(Quantity<?, ?> quantity)`: Multiply the elements of this vector on an element-by-element basis with the provided quantity.
- `divideElements(Quantity<?, ?> quantity)`: Divide the elements of this vector on an element-by-element basis by those the provided quantity.

The result of a Hadamard operation on, e.g. a `VectorN.Row<Speed, Speed.Unit>` will typically be a `VectorN.Row<SIQuantity, SIUnit>` since the inverse operation, multiplication or division will result in a Vector with a unit that is unknown on beforehand and cannot be determined by the compiler. In the above example of `invertElements` for a `Duration` vector, the resulting vector can be transformed into a proper `VectorN.Row<Frequency, Frequency.Unit>` vector using the `as(Frequency.Unit.Hz)` method.

Furthermore, a vector is `Additive`, which means that vectors of the same type, size, and quantity can be added to and subtracted from each other. Vectors also implement the `Scalable` interface, which exposes the `scaleBy(double factor)` and `divideBy(double factor)` methods.

