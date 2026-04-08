# Vector of quantities

## Introduction

Vectors and Matrices are one-dimensional and two-dimensional mathematical data containers for `Quantity` values, where each instance of a `Vector` or `Matrix` contains values of one specific quantity. A `Vector` or `Matrix` has a `displayUnit` for the entire vector or matrix. Internally, vectors and matrices store all values in their SI or BASE unit, just like the `Quantity`. 

Larger Vectors and Matrices are implemented in four different ways: Sparse or Dense data storage, combined with Double or Float precision, which gives four combinations. Sparse storage should be used for vectors or matrices that contain many zero values. Dense data storage would, in that case, store all the zeros, whereas in a sparse storage only the numbers unequal to zero are stored, together with an index. As the index adds some overhead, sparse storage only makes sense when the number of zeros is over 50% of the number of entries. 


## Vector types

Vectors can be defined as row vectors or as column vectors. The difference is especially important for vector-vector multiplications and matrix-vector multiplications. `Row` and `Col` are defined as inner classes of the corresponding vectors. Several implementations of vectors exist, which are shown in the UML class diagram below:

![](images/vector.png)

The generic type of `Vector` of any size is the `VectorN` class. This vector can use sparse or dense storage, and be populated with single-precision `float` values or double precision `double` values. For efficiency reasons, since the `VectorN` carries some overhead for the flexible data storage, separate classes are defined for `Vector1` (no distinction between row and column version), and for `Vector2` and `Vector3`, both with a `Row` and `Col` extension. 


## Vector operations

A `Vector` implements the `Hadamard` interface for entry-by-entry operations. These include:

- `invertEntries()`: Invert the vector on an entry-by-entry basis (1/value), where the unit will also be inverted. The inversion of a `Duration` vector will result in a vector of the same type (row/column) and size, with a unit of `1/s`, corresponding to a `Frequency`. 
- `multiplyEntries(Vector other)`: Multiply the entries of this vector on an entry-by-entry basis with those of another vector of the same type and size (but generally containing values of another quantity).
- `divideEntries(Vector other)`: Divide the entries of this vector on an entry-by-entry basis by those of another vector of the same type and size (but generally containing values of another quantity).
- `multiplyEntries(Quantity<?, ?> quantity)`: Multiply the entries of this vector on an entry-by-entry basis with the provided quantity.
- `divideEntries(Quantity<?, ?> quantity)`: Divide the entries of this vector on an entry-by-entry basis by the provided quantity.

All Hadamard operations result in a new instance of the `Vector` with a new unit, but of the same type (`Vector2.Col`, `Vector3.Row`, `VectorN.Col`, etc.) and with the same size.

The result of a Hadamard operation on, e.g. a `VectorN.Row<Speed>` will typically be a `VectorN.Row<SIQuantity>` since the inverse operation, multiplication or division will result in a `Vector` with a unit that is unknown beforehand and cannot be determined by the compiler. In the above example of `invertEntries` for a `Duration` vector, the resulting vector can be transformed into a proper `VectorN.Row<Frequency>` vector using the `as(Frequency.Unit.Hz)` method. 

If a `VectorN` is internally of a size congruent with a specific vector type, e.g. `Vector2.Row` or `Vector3.Col`, it can be obtained as such using methods such as `asVector2Row()` or `asVector3Col()`. Many such methods exist to carry out a transformation between vectors and matrices of various sizes. These methods will check the consistency of the vector size with the desired vector type at runtime. All vectors, irrespective of their size, can be transformed to a `QuantityTable` using the `asQuantityTable()` method, and to a `MatrixNxM` with the `asMatrixNxM()` method.

If vector-vector multiplication results in a special matrix type, for example multiplying a `Vector3.Col` by a `Vector3.Row` resulting in a 3x3 matrix, the resulting `MatrixNxM` from the calculation can be obtained as a `Matrix3x3` using the method `asMatrix3x3()`. This allows it, for example, to be added to another `Matrix3x3`. 

The `Vector` class implements the `transpose()` operation, which transforms a row vector into a column vector and vice versa. The resulting vector will have the same outer class type as the original; the `transpose()` method on a `Vector2.Row` will result in a `Vector2.Col`. 

Furthermore, a vector is `Additive`, which means that vectors of the same type, size, and quantity can be added to and subtracted from each other. It is also possible to `add` or `subtract` a fixed `Quantity` of the correct type to/from the vector. Vectors also implement the `Scalable` interface, which exposes the `scaleBy(double factor)` and `divideBy(double factor)` methods. Since vectors are immutable, all these operations result in a new instance of a vector.

The generic methods of a `Vector` are:

- `int rows()` returns the number of rows of the vector.
- `int cols()` returns the number of columns of the vector.
- `int size()` returns the size of the vector; the number of rows for a column vector, or the number of columns for a row vector.
- `boolean isColumnVector()` returns whether this vector is a column vector.
- `boolean isRowVector()` returns whether this vector is a row vector. Note that a `Vector1` is both a column vector and and row vector.
- `Iterator<Q> iterator()` returns a `Quantity` iterator over the entries of the vector.
- `getDisplayUnit()` returns the display unit of the entire `Vector`.
- `setDisplayUnit(unit)` sets a new display unit for the entire `Vector` based on a strongly typed `unit`.
- `setDisplayUnit(string)` sets a new display unit for the entire `Vector` based on a `String` representation of the unit.
- `boolean isRelative()` returns whether the underlying `Quantity` is relative or not. Note that `Vector` only stores relative quantities.
- `boolean isAbsolute()` returns whether the underlying `Quantity` is absolute or not. Note that `Vector` only stores relative quantities.
- `transpose()` returns a new `Vector` where the rows and columns are swapped.
- `v1.add(v2)` returns a new `Vector` where all entries of `v2` have been added to the corresponding entries of `v1`. The `displayUnit` is taken from `v1`. The number of rows and columns of `v1` and `v2` have to be equal, of course.
- `v1.subtract(v2)` returns a new `Vector` where all entries of `v2` have been subtracted from the corresponding entries of `v1`. The `displayUnit` is taken from `v1`. The number of rows and columns of `v1` and `v2` have to be equal, of course.
- `v.scaleBy(double factor)` returns a new `Vector` where all entries of `v` have been scaled by `factor`. The `displayUnit` remains unchanged.
- `v.divideBy(double factor)` returns a new `Vector` where all entries of `v` have been scaled by `1.0/factor`. The `displayUnit` remains unchanged.


## Obtaining values of vector entries

Several methods exist to get access to the entries of a `Vector`. When single entries are retrieved, two versions of the methods exist: a version where the index is 0-based, and a version where the index is 1-based. The 1-based methods have a name that starts with `m` for `matrix`, since the entries of a vector start with v<sub>1</sub> and not v<sub>0</sub> and the entries of a matrix start with m<sub>11</sub>, and not with m<sub>00</sub>. So, there is an `si(index)` method where `index` ranges from `0` to `vector.size()-1`, and an `msi(mIndex)` method where `mIndex` ranges from `1` up to and including `vector.size()`. 

Quantity-based methods return a value `Q` that is consistent with the quantity stored in the `Vector`. Suppose `v` is a `Vector3.Row<Mass>`. The result of the operation `v.mget(1)` will then be a strongly typed `Mass` quantity. The letter `Q` in the methods below indicates that strongly typed quantity such as `Mass`.

A `Vector` contains the following methods to obtain its values:

- `double[] getSiArray()` returns a safe copy of the values of the vector in SI-units as a `double[]` array with the same length as the vector.
- `Q[] getScalarArray()` returns a 1-dimensional strongly typed quantity array that represents the vector. The quantities in the array will all have the same `displayUnit` as the original `Vector`.
- `double si(int index)` returns the SI-value of the entry at the 0-based `index`. 
- `double msi(int mIndex)` returns the SI-value of the entry at the 1-based `mIndex`. 
- `Q get(int index)` returns the quantity representation of the entry at the 0-based `index`. The returned `Quantity` will have the same `displayUnit` as the original `Vector`.
- `Q mget(int mIndex)` returns the quantity representation of the entry at the 1-based `mIndex`. The returned `Quantity` will have the same `displayUnit` as the original `Vector`.


## Mathematical operations

A `Vector` implements several mathematical operations. The most important ones are:

- `Q mean()` returns the mean quantity value of the entries of the `Vector` as a strongly typed `Quantity`.
- `Q min()` returns the minimum quantity value of the entries of the `Vector` as a strongly typed `Quantity`.
- `Q max()` returns the maximum quantity value of the entries of the `Vector` as a strongly typed `Quantity`.
- `Q median()` returns the median quantity value of the entries of the `Vector` as a strongly typed `Quantity`. The median value is the value  of the middle entry when all entries have been sorted on their SI-values. When the size of the vector is even, the average of the two values that together make up the middle is returned. 
- `Q sum()` returns the sum of the entries of the `Vector` as a strongly typed `Quantity`.
- `V negate()` returns a `Vector` of the same type and size where all entries $x_i$ have been set to $-x_i$. 
- `V abs()` returns a `Vector` of the same type and size where all entries $x_i$ have been set to $|x_i|$. 
- `Q normL1()` returns the L1-norm of the vector's entries, expressed as a quantity. The L1-norm is defined as $L1=|x_1|+|x_2|+...+ |x_n|$.
- `Q normL2()` returns the L2-norm of the vector's entries, expressed as a quantity. The L2-norm is defined as $L2=\sqrt(x_1^2+x_2^2+...+x_n^2)$.
- `Q normLp(int p)` returns the L<sub>p</sub>-norm of the vector's entries, expressed as a quantity. The L<sub>p</sub>-norm is defined as $L_p={(x_1^p+x_2^p+...+x_n^p)}^{(1/p)}$
- `Q normLinf()` returns the L<sub>&infin;</sub>-norm of this vector's entries, expressed as a quantity. The L<sub>&infin;</sub>-norm is defined as $L_{\infty}=max(|x_1|,|x_2|,...,|x_n|)$.
- `Q norm()` returns the default norm for the vector's entries. The default norm is defined as the L2-norm.
- `double nonZeroCount()` and `double nnz()` both return the number of non-zero entries in the vector.


## Vector definition and storage

### Creating a `Vector1`

For a `Vector1`, there is no distinction between a row and column vector. Several methods exist to instantiate a `Vector1`:

- `new Vector1<Q>(double xSi, Unit displayUnit)` creates a `Vector1` based on an SI-value for the quantity with a displayUnit.
- `Vector1.of(double xInUnit, Unit unit)` creates a `Vector1` based on a value expressed in the given unit, e.g., `60.0, Speed.Unit.km_h`.
- `Vector1.of(double[] dataInUnit, Unit unit)` creates a `Vector1` based on an array of length 1 with values expressed in the given unit.
- `Vector1.ofSi(double xSi, Unit displayUnit)` creates a `Vector1` based on an SI-value for a quantity with a displayUnit.
- `Vector1.ofSi(double[] dataSi, Unit displayUnit)` creates a `Vector1` based on an array of length 1 with SI-values for a quantity with a displayUnit.
- `Vector1.of(Q data)` creates a `Vector1` based on a provided quantity.
- `Vector1.of(Q[] data)` creates a `Vector1` based on an array of length 1 containing a provided quantity.


### Creating a `Vector2`

For a `Vector2`, a row vector `Vector2.Row` and a column vector `Vector2.Col` exist. Several methods exist to instantiate a `Vector2`. Below, the instantiation methods are given for `Vector2.Col`. The instantiation methods for a `Vector2.Row` are analogous.

- `new Vector2.Col<Q>(double xSi, double ySi, Unit displayUnit)` creates a `Vector2.Col` based on two SI-values for the quantities with a displayUnit.
- `Vector2.Col.of(double xInUnit, double yInUnit, Unit unit)` creates a `Vector2.Col` based on two values expressed in the given unit.
- `Vector2.Col.of(double[] dataInUnit, Unit unit)` creates a `Vector2.Col` based on an array of length 2 with values expressed in the given unit.
- `Vector2.Col.ofSi(double xSi, double ySi, Unit displayUnit)` creates a `Vector2.Col` based on two SI-values for the quantities with a displayUnit.
- `Vector2.Col.ofSi(double[] dataSi, Unit displayUnit)` creates a `Vector2.Col` based on an array of length 2 with SI-values for the quantities with a displayUnit.
- `Vector2.Col.of(Q x, Q y)` creates a `Vector2.Col` containing the two provided quantities.
- `Vector2.Col.of(Q[] data)` creates a `Vector2.Col` based on an array of length 2 containing the provided quantities.



## Example for Vector instantiation and usage

The example below shows the instantiation and usage of a column vector with 5 entries `VectorN.Col`:

```java
VectorN.Col<Length> lv1 = VectorN.Col.of(
    new double[] {10, 20.0, 60, 120.0, 400.0}, Length.Unit.km);
Duration duration = Duration.of(2.0, "h");
VectorN.Col<Speed> sv1 = 
    lv1.divideEntries(duration).as(Speed.Unit.km_h);
System.out.println("Length: " + lv1);
System.out.println("Speed : " + sv1);
```

Executing the code results in:

```
Length: Col[10.0, 20.0, 60.0, 120.0, 400.0] km
Speed : Col[5.0, 10.0, 30.0, 60.0, 200.0] km/h
```

The output shows that the vectors are column vectors, although they are printed row-wise. 
