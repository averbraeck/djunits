# Vector with Absolute Quantities

## Introduction

Vectors and matrices for absolute quantities are one-dimensional and two-dimensional mathematical data containers for `AbsQuantity` values, where each instance of an `AbsVector` or `AbsMatrix` contains values of one specific absolute quantity. `AbsVector` and `AbsMatrix` have a single `displayUnit` for the entire vector or matrix, and a single `Reference` to store the reference point for all quantities in the vector or matrix. The `AbsVector` or `AbsMatrix` classes store the vector information in a relative `Vector` or `Matrix` of the same size as the `AbsVector` or `AbsMatrix`, and add a `Reference`.

To define absolute vectors, both the absolute quantity and the corresponding relative quantity need to be specified as generics. Whereas a relative `Vector2.Col` can be specified as `Vector2.Col<Angle>`, its absolute equivalent to store a `Direction` is specified as `AbsVector2.Col<Direction, Angle>`. 

Larger absolute vectors are implemented in four different ways: Sparse or Dense data storage, combined with Double or Float precision, which gives four combinations. Sparse storage should be used for vectors that contain many zero values. Dense data storage would, in that case, store all the zeros, whereas in a sparse storage only the numbers unequal to zero are stored, together with an index. As the index adds some overhead, sparse storage only makes sense when the number of zeros is over 50% of the number of entries. 


## Absolute vector types

Absolute vectors can be defined as row vectors or as column vectors. `Row` and `Col` are defined as inner classes of the corresponding absolute vectors. For each relative vector size, an absolute equivalent exists that starts with `Abs`.

The generic type of `AbsVector` of any size is the `AbsVectorN` class. This vector can use sparse or dense storage, and be populated with single-precision `float` values or double precision `double` values. For efficiency reasons, since the data storage for `AbsVectorN` carries quite some overhead, separate classes are defined for `AbsVector1` (no distinction between row and column version), and for `AbsVector2` and `AbsVector3`, all with a `Row` and `Col` extension. 


## Absolute vector operations

An `AbsVector` does **not** implement the `Hadamard` interface for entry-by-entry operations. Operations like scaling, multiplication, and inversion do not make sense for the absolute quantities in the `AbsVector`.

If an `AbsVectorN` is internally of a size congruent with a specific vector type, e.g. `AbsVector2.Row` or `AbsVector3.Col`, it can be obtained as such using methods such as `asAbsVector2Row()` or `asAbsVector3Col()`. Many such methods exist to carry out a transformation between vectors and matrices of various sizes. These methods will check the consistency of the vector size with the desired vector type at runtime. All absolute vectors, irrespective of their size, can be transformed to an `AbsQuantityTable` using the `asAbsQuantityTable()` method, and to an `AbsMatrixNxM` with the `asAbsMatrixNxM()` method.

The `AbsVector` class implements the `transpose()` operation, which transforms an absolute row vector into a column vector and vice versa. The resulting vector will have the same outer class type as the original; the `transpose()` method on an `AbsVector2.Row` will result in a `AbsVector2.Col`. 

The generic methods of an `AbsVector` are:

- `int rows()` returns the number of rows of the vector.
- `int cols()` returns the number of columns of the vector.
- `int size()` returns the size of the vector; the number of rows for a column vector, or the number of columns for a row vector.
- `boolean isColumnVector()` returns whether this vector is a column vector.
- `boolean isRowVector()` returns whether this vector is a row vector. Note that a `AbsVector1` is both a column vector and and row vector.
- `Iterator<A> iterator()` returns an `AbsQuantity` iterator over the entries of the vector.
- `getDisplayUnit()` returns the display unit of the entire `AbsVector`.
- `setDisplayUnit(unit)` sets a new display unit for the entire `AbsVector` based on a strongly typed `unit`.
- `setDisplayUnit(string)` sets a new display unit for the entire `AbsVector` based on a `String` representation of the unit.
- `getReference()` returns the reference point for all absolute quantities in the `AbsVector`.
- `boolean isRelative()` returns whether the underlying `AbsQuantity` is relative or not. Note that `AbsVector` only stores absolute quantities.
- `boolean isAbsolute()` returns whether the underlying `AbsQuantity` is absolute or not. Note that `AbsVector` only stores absolute quantities.
- `transpose()` returns a new `Vector` where the rows and columns are swapped.

Addition and subtraction of absolute vectors and matrices follow the rules for the [absolute quantity](../absolute_quantity):

- `absVector.add(quantity)` returns a new `AbsVector` where all entries of of `absVector` have been increased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absVector`.
- `absVector1.add(relVector2)` returns a new `AbsVector` where all entries of `relVector2` have been added to the corresponding entries of `absVector1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absVector1`. The number of rows and columns of `absVector1` and `relVector2` have to be equal, of course.
- `absVector.subtract(quantity)` returns a new `AbsVector` where all entries of of `absVector` have been decreased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absVector`.
- `absVector1.subtract(relVector2)` returns a new `AbsVector` where all entries of `relVector2` have been subtracted from the corresponding entries of `absVector1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absVector1`. The number of rows and columns of `absVector1` and `relVector2` have to be equal, of course.
- `absVector.subtract(absQuantity)` returns a new **relative** `Vector` where all entries of of `absVector` have been decreased by the provided absolute quantity. The `displayUnit` of the resulting relative vector is taken from `absVector`.
- `absVector1.subtract(absVector2)` returns a new **relative** `Vector` where all entries of `absVector2` have been subtracted from the corresponding entries of `absVector1`. The `displayUnit` of the resulting absolute vector is taken from `absVector1`. The number of rows and columns of `absVector1` and `absVector2` have to be equal, of course.

It is possible to transform any `AbsVector` into an `AbsVectorN`, an `AbsMatrixNxM` or `AbsQuantityTable`. If the dimensions match, an `AbsQuantityTable`, `AbsMatrixNxM`, `AbsMatrixNxN`, or `AbsVectorN` can be transformed into an `AbsVector1`, `AbsVector2.Col`, `AbsVector2.Row`, `AbsVector3.Col`, or `AbsVector3.Row`. The methods are named, e.g., `asAbsMatrixNxM()`, `asAbsVector2Col()`, etc.


## Obtaining values of vector entries

Several methods exist to get access to the entries of an `AbsVector`. When single entries are retrieved, two versions of the methods exist: a version where the index is 0-based, and a version where the index is 1-based. The 1-based methods have a name that starts with `m` for `matrix`, since the entries of a vector start with v<sub>1</sub> and not v<sub>0</sub> and the entries of a matrix start with m<sub>11</sub>, and not with m<sub>00</sub>. So, there is an `si(index)` method where `index` ranges from `0` to `vector.size()-1`, and an `msi(mIndex)` method where `mIndex` ranges from `1` up to and including `vector.size()`. 

Quantity-based methods return a value `A` that is consistent with the absolute quantity stored in the `Vector`. Suppose `v` is an `AbsoluteVector3.Row<Direction, Angle>`. The result of the operation `v.mget(1)` will then be a strongly typed `Direction` (absolute) quantity. The letter `A` in the methods below indicates that strongly typed absolute quantity such as `Direction`.

An `AbsVector` contains the following methods to obtain its values:

- `getRelativeVecMat()` returns the 'embedded' relative vector or matrix, whose values are relative to the reference point of the `AbsVector`. The size and type of the returned vector are congruent with the type of the `AbsVector`.
- `double[] getSiArray()` returns a safe copy of the values of the vector in SI-units as a `double[]` array with the same length as the vector. All returned SI-values are relative to the reference point.
- `A[] getScalarArray()` returns a 1-dimensional strongly typed quantity array that represents the vector. The quantities in the array will all have the same `displayUnit` and `Reference` as the original `AbsVector`.
- `double si(int index)` returns the SI-value of the entry at the 0-based `index`. The returned SI-value is relative to the reference point.
- `double msi(int mIndex)` returns the SI-value of the entry at the 1-based `mIndex`. The returned SI-value is relative to the reference point.
- `A get(int index)` returns the absolute quantity representation of the entry at the 0-based `index`. The returned `AbsQuantity` will have the same `displayUnit` and `Reference` as the original `AbsVector`.
- `A mget(int mIndex)` returns the absolute quantity representation of the entry at the 1-based `mIndex`. The returned `AbsQuantity` will have the same `displayUnit` and `Reference` as the original `AbsVector`.


## Mathematical operations

An `AbsVector` implements several mathematical operations. The most important ones are:

- `A mean()` returns the mean quantity value of the entries of the `AbsVector` as a strongly typed `AbsQuantity`.
- `A min()` returns the minimum quantity value of the entries of the `AbsVector` as a strongly typed `AbsQuantity`.
- `A max()` returns the maximum quantity value of the entries of the `AbsVector` as a strongly typed `AbsQuantity`.
- `A median()` returns the median quantity value of the entries of the `AbsVector` as a strongly typed `AbsQuantity`. The median value is the value  of the middle entry when all entries have been sorted on their SI-values, relative to the reference point. When the size of the vector is even, the average of the two values that together make up the middle is returned. 


## Vector definition and storage

### Creating a `AbsVector1`

For a `AbsVector1`, there is no distinction between a row and column vector. Several methods exist to instantiate a `AbsVector1`:

- `new AbsVector1<A, Q>(double xSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector1` based on an SI-value for the quantity, with a displayUnit and a reference point.
- `AbsVector1.of(double xInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector1` based on a value expressed in the given unit, e.g., `60.0, Speed.Unit.km_h`, and a reference point.
- `AbsVector1.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector1` based on an array of length 1 with values expressed in the given unit, and a reference point.
- `AbsVector1.ofSi(double xSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector1` based on an SI-value for a quantity with a displayUnit and a reference point.
- `AbsVector1.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector1` based on an array of length 1 with SI-values for a quantity with a displayUnit and a reference point.
- `AbsVector1.of(Q x, Reference reference)` <br>
  creates a `AbsVector1` based on a provided quantity and a reference point.
- `AbsVector1.of(Q[] data, Reference reference)` <br>
  creates a `AbsVector1` based on an array of length 1 containing a provided quantity and a reference point.
- `AbsVector1.of(A absX)` <br>
  creates a `AbsVector1` based on the provided absolute quantity.
- `AbsVector1.of(A[] absData)` <br>
  creates a `AbsVector1` based on an array of length 1 containing an absolute quantity.


### Creating a `AbsVector2`

For a `AbsVector2`, a row vector `AbsVector2.Row` and a column vector `AbsVector2.Col` exist. Several methods exist to instantiate a `AbsVector2`. Below, the instantiation methods are given for `AbsVector2.Col`. The instantiation methods for a `AbsVector2.Row` are analogous.

- `new AbsVector2.Col<A, Q>(double xSi, double ySi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector2.Col` based on two SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector2.Col.of(double xInUnit, double yInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector2.Col` based on two values expressed in the given unit and a reference point.
- `AbsVector2.Col.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector2.Col` based on an array of length 2 with values expressed in the given unit and a reference point.
- `AbsVector2.Col.ofSi(double xSi, double ySi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector2.Col` based on two SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector2.Col.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector2.Col` based on an array of length 2 with SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector2.Col.of(Q x, Q y, Reference reference)` <br>
  creates a `AbsVector2.Col` containing the two provided quantities and a reference point.
- `AbsVector2.Col.of(Q[] data, Reference reference)` <br>
  creates a `AbsVector2.Col` based on an array of length 2 containing the provided quantities and a reference point.
- `AbsVector2.Col.of(A absX, A absY)` <br>
  creates a `AbsVector2.Col` based on the provided absolute quantities. The display unit is taken from the first quantity. The reference points of all quantities should be the same.
- `AbsVector2.Col.of(A[] absData)` <br>
  creates a `AbsVector2.Col` based on an array of length 2 containing absolute quantities. The display unit is taken from the first quantity. The reference points of all quantities should be the same.


### Creating a `AbsVector3`

For a `AbsVector3`, a row vector `AbsVector3.Row` and a column vector `AbsVector3.Col` exist. Several methods exist to instantiate a `AbsVector3`. Below, the instantiation methods are given for `AbsVector3.Col`. The instantiation methods for a `AbsVector3.Row` are analogous.

- `new AbsVector3.Col<A, Q>(double xSi, double ySi, double zSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector3.Col` based on three SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector3.Col.of(double xInUnit, double yInUnit, double zInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector3.Col` based on three values expressed in the given unit and a reference point.
- `AbsVector3.Col.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVector3.Col` based on an array of length 3 with values expressed in the given unit and a reference point.
- `AbsVector3.Col.ofSi(double xSi, double ySi, double zSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector3.Col` based on three SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector3.Col.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVector3.Col` based on an array of length 3 with SI-values for the quantities with a displayUnit and a reference point.
- `AbsVector3.Col.of(Q x, Q y, Q z, Reference reference)` <br>
  creates a `AbsVector3.Col` containing the three provided quantities and a reference point.
- `AbsVector3.Col.of(Q[] data, Reference reference)` <br>
  creates a `AbsVector3.Col` based on an array of length 3 containing the provided quantities and a reference point.
- `AbsVector3.Col.of(A absX, A absY, A absZ)` <br>
  creates a `AbsVector3.Col` based on the provided absolute quantities. The display unit is taken from the first quantity. The reference points of all quantities should be the same.
- `AbsVector3.Col.of(A[] absData)` <br>
  creates a `AbsVector3.Col` based on an array of length 3 containing absolute quantities. The display unit is taken from the first quantity. The reference points of all quantities should be the same.


### Creating a `AbsVectorN`

The `AbsVectorN` class is used for storing row and column vectors of any length. Data can be stored as single-precision `float` variable, or as double-preciding `double` values. Both dense (store every number) and sparse (only store non-zero values) is possible. For a `AbsVectorN`, a row vector subclass `AbsVectorN.Row` and a column vector subclass `AbsVectorN.Col` exist. Several methods exist to instantiate a `AbsVectorN`. Below, the instantiation methods are given for `AbsVectorN.Col`. The instantiation methods for a `AbsVectorN.Row` are analogous.

- `new AbsVectorN.Col<A, Q>(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on a `DataGridSi` storage object, a display unit and a reference point. More information can be found in the [storage](storage) section. 
- `AbsVectorN.Col.of(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on a `DataGridSi` storage object, a display unit and a reference point. More information can be found in the [storage](storage) section. 
- `AbsVectorN.Col.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on an array with values expressed in the given unit and a reference point. The vector will have the same number of elements as the array.
- `AbsVectorN.Col.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on an array with SI-values for the quantities, a display unit and a reference point. The vector will have the same number of elements as the array.
- `AbsVectorN.Col.of(Q[] data, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on an array with quantities and a reference point. The vector will have the same number of elements as the array.
- `AbsVectorN.Col.of(List<Q> data, Reference reference)` <br>
  creates a `AbsVectorN.Col` based on a list with quantities and a reference point. The vector will have the same number of elements as the list.
- `AbsVector3.Col.of(A[] absData)` <br>
  creates a `AbsVectorN.Col` based on an array containing absolute quantities. The display unit is taken from the first quantity. The reference points of all absolute quantities should be the same.
- `AbsVector3.Col.of(List<A> absData)` <br>
  creates a `AbsVectorN.Col` based on an array containing absolute quantities. The display unit is taken from the first quantity. The reference points of all absolute quantities should be the same.
