# Matrix with Absolute Quantities

## Introduction

Vectors and matrices for absolute quantities are one-dimensional and two-dimensional mathematical data containers for `AbsQuantity` values, where each instance of an `AbsVector` or `AbsMatrix` contains values of one specific absolute quantity. `AbsVector` and `AbsMatrix` have a single `displayUnit` for the entire vector or matrix, and a single `Reference` to store the reference point for all quantities in the vector or matrix. The `AbsVector` or `AbsMatrix` classes store the vector information in a relative `Vector` or `Matrix` of the same size as the `AbsVector` or `AbsMatrix`, and add a `Reference`.

To define absolute matrices, both the absolute quantity and the corresponding relative quantity need to be specified as generics. Whereas a relative `Matrix2x2` can be specified as `Matrix2x2<Angle>`, its absolute equivalent to store a `Direction` is specified as `AbsMatrix2x2<Direction, Angle>`. 

Larger absolute matrices are implemented in four different ways: Sparse or Dense data storage, combined with Double or Float precision, which gives four combinations. Sparse storage should be used for matrices that contain many zero values. Dense data storage would, in that case, store all the zeros, whereas in a sparse storage only the numbers unequal to zero are stored, together with an index. As the index adds some overhead, sparse storage only makes sense when the number of zeros is over 50% of the number of entries. 


## Absolute matrix types

The abstract class `AbsMatrix` extends the abstract class `AbsVectorMatrix`, which contains numerous methods for operations that are common to absolute matrices, vectors and tables. Square absolute matrices as defined in the `AbsSquareMatrix` abstract class contain additional methods that only make sense for square matrices, such as symmetry tests and retrieving a diagonal vector. 

The generic type of `AbsMatrix` of any size is the `AbsMatrixNxM`. This matrix can use sparse or dense storage, and be populated with single-precision `float` values or double precision `double` values. 

The generic type of `AbsSquareMatrix` of any size is `AbsMatrixNxN`. This matrix can also use sparse or dense storage, and store `float` values or `double` values. For efficiency reasons, since the `AbsMatrixNxN` carries some overhead for the flexible data storage, separate classes are defined for `AbsMatrix1x1`, `AbsMatrix2x2`, and `AbsMatrix3x3`. Data inside these matrices is stored in a dense `double[]` array that uses row-major indexing.


## Matrix operations

The `AbsMatrix` classes **do not implement** the `Hadamard` interface for entry-by-entry operations, since absolute quantities are neither scalable, nor additive.

If an `AbsMatrixNxM` is internally of a size congruent with a specific matrix or vector type, e.g. `AbsVector2.Row` or `AbsMatrix3x3`, it can be obtained as such using methods such as `asAbsVector2Row()` or `asAbsMatrix3x3()`. The same holds for `AbsMatrixNxN` that can be transformed to a strongly typed `AbsMatrix1x1`, `AbsMatrix2x2`, or `AbsMatrix3x3` (or `AbsMatrixNxM`). Many such methods exist to carry out a transformation between vectors and matrices of various sizes. These methods will check the consistency of the matrix size with the desired matrix size at runtime. All absolute matrices, irrespective of their size, can be transformed to an `AbsQuantityTable` using the `asAbsQuantityTable()` method.

The `transpose()` method returns the transposed matrix, where rows and columns have been swapped. A transposed matrix has the same `displayUnit` as the original matrix.

The generic methods of an `AbsMatrix` are:

- `int rows()` returns the number of rows of the matrix.
- `int cols()` returns the number of columns of the matrix.
- `getDisplayUnit()` returns the display unit of the entire `AbsMatrix`.
- `setDisplayUnit(unit)` sets a new display unit for the entire `AbsMatrix` based on a strongly typed `unit`.
- `setDisplayUnit(string)` sets a new display unit for the entire `AbsMatrix` based on a `String` representation of the unit.
- `boolean isRelative()` returns whether the underlying `AbsQuantity` is relative or not. Note that `AbsMatrix` only stores absolute quantities.
- `boolean isAbsolute()` returns whether the underlying `AbsQuantity` is absolute or not. Note that `AbsMatrix` only stores absolute quantities.
- `transpose()` returns a new `AbsMatrix` where the rows and columns are swapped.

Addition and subtraction of absolute vectors and matrices follow the rules for the [absolute quantity](../absolute_quantity):

- `absMatrix.add(quantity)` returns a new `AbsMatrix` where all entries of of `absMatrix` have been increased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absMatrix`.
- `absMatrix1.add(relMatrix2)` returns a new `AbsMatrix` where all entries of `relMatrix2` have been added to the corresponding entries of `absMatrix1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absMatrix1`. The number of rows and columns of `absMatrix1` and `relMatrix2` have to be equal, of course.
- `absMatrix.subtract(quantity)` returns a new `AbsMatrix` where all entries of of `absMatrix` have been decreased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absMatrix`.
- `absMatrix1.subtract(relMatrix2)` returns a new `AbsMatrix` where all entries of `relMatrix2` have been subtracted from the corresponding entries of `absMatrix1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absMatrix1`. The number of rows and columns of `absMatrix1` and `relMatrix2` have to be equal, of course.
- `absMatrix.subtract(absQuantity)` returns a new **relative** `Matrix` where all entries of of `absMatrix` have been decreased by the provided absolute quantity. The `displayUnit` of the resulting relative vector is taken from `absMatrix`.
- `absMatrix1.subtract(absMatrix2)` returns a new **relative** `Matrix` where all entries of `absMatrix2` have been subtracted from the corresponding entries of `absMatrix1`. The `displayUnit` of the resulting absolute vector is taken from `absMatrix1`. The number of rows and columns of `absMatrix1` and `absMatrix2` have to be equal, of course.

It is possible to transform any `AbsMatrix` into an `AbsMatrixNxM` or `AbsQuantityTable`. If the dimensions match, an `AbsQuantityTable`, `AbsMatrixNxM`, `AbsMatrixNxN`, or `AbsVectorN` can be transformed into an `AbsVector1`, `AbsVector2.Col`, `AbsVector2.Row`, `AbsVector3.Col`, or `AbsVector3.Row`. The methods are named, e.g., `asAbsMatrixNxM()`, `asAbsVector2Col()`, etc.

Many of the matrix operations are delegated to the mathematics utility classes `ArrayMath` and `MatrixMath`, which can be found in the `org.djunits.util` package.


## Obtaining values of matrix entries

Several methods exist to get access to the entries of an `AbsMatrix`. When single entries, rows or columns are retrieved, two versions of the methods exist: a version where the row and column number are 0-based, and a version where the row and column number are 1-based. The 1-based methods have a name that starts with `m` for `matrix`, since the entry numbering of a matrix start with m<sub>11</sub>, and not with m<sub>00</sub>. So, there is an `si(row, col)` method where `row` ranges from `0` to `matrix.rows()-1` and `col` ranges from `0` to `matrix.cols()-1`, and an `msi(mRow, mCol)` method where `mRow` ranges from `1` up to and including `matrix.rows()` and `mCol` ranges from `1` up to and including `matrix.cols`.

Quantity-based value methods return a value `A` that is consistent with the absolute quantity stored in the `AbsMatrix`. Suppose `mx` is an `AbsMatrix3x3<Direction, Angle>`. The result of the operation `mx.mget(1,3)` will then be a strongly typed `Direction` quantity. The letter `A` in the methods below indicates that strongly typed quantity such as `Direction`, and the letter `Q` is the generic indicator for the corresponding relative quantity type such as `Angle`.

A `AbsMatrix` contains the following methods to obtain its values:

### SI-based value methods

- `double[][] getSiGrid()` returns a 2-dimensional `double[][]` array with the SI-values of the entries in the matrix, relative to the reference point. 
- `double[] getSiArray()` returns the values of the matrix in SI-units as a row-major `double[]` array with the same length as the matrix. This means that for an n x m matrix (n rows, m columns), the data is stored as [a<sub>11</sub>, a<sub>12</sub>, ..., a<sub>1m</sub>, a<sub>21</sub>, a<sub>22</sub>, ..., a<sub>2m</sub>, ..., a<sub>n1</sub>, a<sub>n2</sub>, ..., a<sub>nm</sub>]. The SI-values are relative to the reference point of the absolute matrix.
- `double si(int row, int col)` returns the SI-value of the entry at the 0-based row and column. The SI-value is relative to the reference point of the absolute matrix.
- `double msi(int mRow, int mCol)` returns the SI-value of the entry at the 1-based row indicated by `mRow` and 1-based column indicated by `mCol`. The SI-value is relative to the reference point of the absolute matrix.


### Absolute quantity-based value methods

- `A[][] getScalarGrid()` returns a 2-dimensional strongly typed absolute quantity array that represents the matrix. The absolute quantities in the array will all have the same `displayUnit` and `Reference` as the original `AbsMatrix`.
- `A[] getScalarArray()` returns a 1-dimensional strongly typed row-major absolute quantity array that represents the matrix. The absolute quantities in the array will all have the same `displayUnit` and `Reference` as the original `AbsMatrix`.
- `A get(int row, int col)` returns the absolute quantity of the entry at the 0-based row and column. The returned `AbsQuantity` will have the same `displayUnit` and `Reference` as the original `AbsMatrix`.
- `A mget(int mRow, int mCol)` returns the absolute quantity of the entry at the 1-based row indicated by `mRow` and 1-based column indicated by `mCol`. The returned `AbsQuantity` will have the same `displayUnit` and `Reference` as the original `AbsMatrix`.


### Retrieving matrix rows

- `AbsVector getRowVector(int row)` retrieves the matrix row at the 0-based `row` as a row-vector. When the matrix is an `AbsMatrix3x3`, the vector returned is a `AbsVector3.Row` of the same `AbsQuantity`, and with the same `displayUnit` and `Reference`. 
- `AbsVector mgetRowVector(int mRow)` retrieves the matrix row at the 1-based `mRow` as a row-vector. When the matrix is an `AbsMatrixNxM`, the vector returned is a `AbsVectorN.Row` of the same `AbsQuantity`, and with the same `displayUnit` and `Reference`. 
- `A[] getRowScalars(int row)` retrieves the matrix row at the 0-based `row` as an array of absolute quantities. When the matrix is an `AbsMatrix2x2<Position, Length>`, the array returned is of type `Position[2]`, where the absolute quantities in the array have the same `displayUnit` and the same `Reference` as the original matrix. 
- `A[] mgetRowScalars(int mRow)` retrieves the matrix row at the 1-based `mRow` as an array of absolute quantities. When the matrix is an `AbsMatrixNxM<Temperature, TemperatureDifference>`, the array returned is of type `Temperature[matrix.cols()]`, where the quantities in the array have the same `displayUnit` and `Reference` as the original matrix. Note that the resulting `Temperature[]` array is 0-based.
- `double[] getRowSi(int row)` retrieves the SI-values of the 0-based `row` as a `double[]` array. When the matrix is an `AbsMatrix3x3`, the array returned is of type `double[3]`. The SI-values are relative to the reference point of the absolute matrix.
- `double[] mgetRowSi(int mRow)` retrieves the SI-values of the 1-based `mRow` as a `double[]` array. When the matrix is an `AbsMatrixNxM`, the array returned is of type `double[matrix.cols()]`. The SI-values are relative to the reference point of the absolute matrix. Note that the resulting `double[]` array is 0-based.


### Retrieving matrix columns

- `AbsVector getColumnVector(int col)` retrieves the matrix column at the 0-based `col` as a column-vector. When the matrix is an `AbsMatrix3x3`, the vector returned is a `AbsVector3.Col` of the same `AbsQuantity`, and with the same `displayUnit` and `Reference`. 
- `AbsVector mgetColumnVector(int mCol)` retrieves the matrix column at the 1-based `mCol` as a column-vector. When the matrix is an `AbsMatrixNxM`, the vector returned is a `AbsVectorN.Col` of the same `AbsQuantity`, and with the same `displayUnit` and `Reference`. 
- `A[] getColumnScalars(int col)` retrieves the matrix column at the 0-based `col` as an array of absolute quantities. When the matrix is an `AbsMatrix2x2<Position, Length>`, the array returned is of type `Position[2]`, where the quantities in the array have the same `displayUnit` and `Reference` as the original matrix. 
- `A[] mgetColumnScalars(int mCol)` retrieves the matrix column at the 1-based `mCol` as an array of absolute quantities. When the matrix is an `AbsMatrixNxM<Temperature, TemperatureDifference>`, the array returned is of type `Temperature[matrix.cols()]`, where the quantities in the array have the same `displayUnit` and `Reference` as the original matrix. Note that the resulting `Temperature[]` array is 0-based.
- `double[] getColumnSi(int col)` retrieves the SI-values of the 0-based `col` as a `double[]` array. When the matrix is an `AbsMatrix3x3`, the array returned is of type `double[3]`. The SI-values are relative to the reference point of the absolute matrix. 
- `double[] mgetColumnSi(int mCol)` retrieves the SI-values of the 1-based `mCol` as a `double[]` array. When the matrix is an `AbsMatrixNxM`, the array returned is of type `double[matrix.cols()]`. The SI-values are relative to the reference point of the absolute matrix. Note that the resulting `double[]` array is 0-based.


## Mathematical operations for all matrices

A `AbsMatrix` implements several mathematical operations. The most important ones are:

- `A mean()` returns the mean quantity value of the entries of the `AbsMatrix` as a strongly typed `AbsQuantity`.
- `A min()` returns the minimum quantity value of the entries of the `AbsMatrix` as a strongly typed `AbsQuantity`.
- `A max()` returns the maximum quantity value of the entries of the `AbsMatrix` as a strongly typed `AbsQuantity`.
- `A median()` returns the median quantity value of the entries of the `AbsMatrix` as a strongly typed `AbsQuantity`. The median value is the value  of the middle entry when all entries have been sorted on their SI-values. When the number of entries in the matrix is even, the average of the two values that together make up the middle is returned. 


## Extra operations for square matrices

Square matrices have a number of additional operations:

- `int order()` returns the number of rows or columns of the square matrix.
- `AbsVector getDiagonalVector()` returns the absolute quantities on the diagonal as an absolute column vector of the same quantity and size as the square matrix. The `displayUnit` and `reference` will be the same as that of the matrix.
- `A[] getDiagonalScalars()` returns the quantities on the diagonal as an array of quantities. When the matrix has order N, the array will have length N. The `displayUnit` and `reference` of the quantities will be the same as that of the matrix.
- `double[] getDiagonalSi()` returns the SI-values of the quantities on the diagonal as a `double[]` array. When the matrix has order N, the array will have length N. The SI values will be relative to the reference point of the matrix.
- `boolean isSymmetric()` returns whether the matrix is symmetric or not. A small tolerance of of 10<sup>-12</sup> times the largest absolute SI-quantity in the matrix is used to determine symmetry.
- `boolean isSymmetric(final Q tolerance)` returns whether the matrix is symmetric or not, using a provided tolerance.
- `boolean isSkewSymmetric()` returns whether the matrix is skew-symmetric or not. A small tolerance of of 10<sup>-12</sup> times the largest absolute SI-quantity in the matrix is used to determine skew-symmetry. Skew-symmetry means that $A^T=-A$, or $a_{ij}=-a_{ji}$ for all entries $a_{ij}$.
- `boolean isSkewSymmetric(final Q tolerance)` returns whether the matrix is skew-symmetric or not, using a provided tolerance.


## Vector definition and storage

### Creating an `AbsMatrix1x1`

Several methods exist to instantiate an `AbsMatrix1x1`:

- `new AbsMatrix1x1<Q>(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on an array of length 1 with SI-values for a quantity with a displayUnit  and a reference point.
- `AbsMatrix1x1.of(double xInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on a value expressed in the given unit, e.g., `60.0, Speed.Unit.km_h`,  and a reference point.
- `AbsMatrix1x1.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on an array of length 1 with values expressed in the given unit, and a reference point.
- `AbsMatrix1x1.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on a 1x1 grid (array of arrays) of values expressed in the given unit, and a reference point.
- `AbsMatrix1x1.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on an array of length 1 with SI-values for a quantity and a displayUnit, and a reference point.
- `AbsMatrix1x1.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on a 1x1 grid (array of arrays) of SI values and a displayUnit, and a reference point.
- `AbsMatrix1x1.of(Q[] data, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on an array of length 1 containing the quantity and a reference point. The display unit is taken from the quantity.
- `AbsMatrix1x1.of(Q[][] grid, Reference reference)` <br>
  creates an `AbsMatrix1x1` based on a 1x1 grid (array of arrays) of quantities and a reference point. The display unit is taken from the quantity.
- `AbsMatrix1x1.of(A xAbs)` <br>
  creates an `AbsMatrix1x1` based on an absolute quantity. The display unit and reference are taken from the quantity.
- `AbsMatrix1x1.of(A[] absData)` <br>
  creates an `AbsMatrix1x1` based on an array of length 1 containing the absolute quantity. The display unit and reference are taken from the absolute quantity.
- `AbsMatrix1x1.of(A[][] absGrid)` <br>
  creates an `AbsMatrix1x1` based on a 1x1 grid (array of arrays) of absolute quantities. The display unit and reference are taken from the absolute quantity.


### Creating an `AbsMatrix2x2`

Several methods exist to instantiate an `AbsMatrix2x2`. 

The **array**-based methods use a row-major array. This means that the data is presented "row-by-row", so, `{m11, m12, m21, m22}`. A `(r,c)` value is retrieved by `m[index]`, `index = r * cols() + c` where r, c are 0-based indices.

The **grid**-based methods count the rows in the 'outer' (first) array `[r][]`, and the columns in the 'inner' second array `[][c]`. A `(r,c)`value is retrieved by `m[r][c]`. 

- `new AbsMatrix2x2<Q>(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a row-major array of length 4 with SI-values for a quantity with a displayUnit and a reference.
- `AbsMatrix2x2.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a row-major array of length 4 with values expressed in the given unit, and a reference.
- `AbsMatrix2x2.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a 2x2 grid (array of arrays) of values expressed in the given unit, and a reference.
- `AbsMatrix2x2.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a row-major array of length 4 with SI-values for a quantity and a displayUnit and a reference.
- `AbsMatrix2x2.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a 2x2 grid (array of arrays) of SI values and a displayUnit and a reference.
- `AbsMatrix2x2.of(Q[] data, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a row-major array of length 4 containing the quantity and a reference. The display unit is taken from the quantity at position `[0]`.
- `AbsMatrix2x2.of(Q[][] grid, Reference reference)` <br>
  creates an `AbsMatrix2x2` based on a 2x2 grid (array of arrays) of quantities and a reference. The display unit is taken from the quantity at position `[0][0]`.
- `AbsMatrix2x2.of(A[] absData)` <br>
  creates an `AbsMatrix2x2` based on a row-major array of length 4 containing the absolute quantities. The display unit and reference are taken from the absolute quantity at position `[0]`. The reference points of all quantities should be the same.
- `AbsMatrix2x2.of(A[][] absGrid)` <br>
  creates an `AbsMatrix2x2` based on a 2x2 grid (array of arrays) of absolute quantities. The display unit and reference are taken from the absolute quantity at position `[0][0]`. The reference points of all quantities should be the same.


### Creating an `AbsMatrix3x3`

Several methods exist to instantiate an `AbsMatrix3x3`. 

The **array**-based methods use a row-major array. This means that the data is presented "row-by-row", so, `{m11, m12, m13, m21, m22, m23, m31, m32, m33}`. A `(r,c)` value is retrieved by `m[index]`, `index = r * cols() + c` where r, c are 0-based indices.

The **grid**-based methods count the rows in the 'outer' (first) array `[r][]`, and the columns in the 'inner' second array `[][c]`. A `(r,c)`value is retrieved by `m[r][c]`. 

- `new AbsMatrix3x3<Q>(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a row-major array of length 9 with SI-values for a quantity with a displayUnit and a reference.
- `AbsMatrix3x3.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a row-major array of length 9 with values expressed in the given unit, and a reference.
- `AbsMatrix3x3.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a 3x3 grid (array of arrays) of values expressed in the given unit, and a reference.
- `AbsMatrix3x3.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a row-major array of length 9 with SI-values for a quantity and a displayUnit and a reference.
- `AbsMatrix3x3.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a 3x3 grid (array of arrays) of SI values and a displayUnit and a reference.
- `AbsMatrix3x3.of(Q[] data, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a row-major array of length 9 containing the quantity and a reference. The display unit is taken from the quantity at position `[0]`.
- `AbsMatrix3x3.of(Q[][] grid, Reference reference)` <br>
  creates an `AbsMatrix3x3` based on a 3x3 grid (array of arrays) of quantities and a reference. The display unit is taken from the quantity at position `[0][0]`.
- `AbsMatrix3x3.of(A[] absData)` <br>
  creates an `AbsMatrix3x3` based on a row-major array of length 9 containing the absolute quantities. The display unit and reference are taken from the absolute quantity at position `[0]`. The reference points of all quantities should be the same.
- `AbsMatrix3x3.of(A[][] absGrid)` <br>
  creates an `AbsMatrix3x3` based on a 3x3 grid (array of arrays) of absolute quantities. The display unit and reference are taken from the absolute quantity at position `[0][0]`. The reference points of all quantities should be the same.


### Creating an `AbsMatrixNxN`

The `AbsMatrixNxN` class is used for storing **square** absolute matrices of any size (1x1 and up). Data can be stored as single-precision `float` variable, or as double-precision `double` values. Both dense storage (store every number) and sparse storage (only store non-zero values) are possible. 

Several methods exist to instantiate an `AbsMatrixNxN`.

The **DataGridSi**-based methods store the data in the `dataGridSi` object, which can be `DenseDoubleDataSi`, `SparseDoubleDataSi`, `DenseFloatDataSi`, or `SparseFloatDataSi`. These objects are instantiated through one of their `of()`, `ofSi()` or constructor methods. For many `of` and `ofSi` methods and the constructor, the number of rows and columns of the matrix need to be provided for the `DataGridSi` object to know the shape of the matrix. A `double[4]` array of SI values can represent a 2x2 matrix, but also a 4x1 or 1x4 matrix or vector. All three shapes can be stored in the `DataGridSi` object.

The **array**-based methods use a row-major array. This means that the data is presented "row-by-row", so, `{m11, m12, m13, m21, m22, m23, m31, m32, m33}` for a 3x3 matrix. A `(r,c)` value is retrieved by `m[index]`, `index = r * cols() + c` where r, c are 0-based indices. Since the construction methods know that a square matrix has to be constructed, they test whether the array length is a perfect square (e.g., 25) and construct the corresponding square matrix (e.g., 5x5) by taking the square root of the length for the number of rows and columns. 

The **grid**-based methods count the rows in the 'outer' (first) array `[r][]`, and the columns in the 'inner' second array `[][c]`. A `(r,c)`value is retrieved by `m[r][c]`. For a square NxN matrix, the number of rows and columns should be the same, and 'ragged' grids are not allowed and result in an `IllegalArgumentException`. 


- `new AbsMatrixNxN<Q>(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a `DataGridSi` storage object. More information can be found in the [storage](storage) section. 
- `AbsMatrixNxN.of(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a `DataGridSi` storage object. More information can be found in the [storage](storage) section. 
- `AbsMatrixNxN.of(double[] dataInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a row-major array with values expressed in the given unit. The number of elements in the array needs to be a perfect square.
- `AbsMatrixNxN.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a grid (array of arrays) with values expressed in the given unit. The number of rows and columns in the grid have to be the same, and the grid cannot be 'ragged'.
- `AbsMatrixNxN.ofSi(double[] dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a row-major array with SI-values for the quantities. The number of elements in the array needs to be a perfect square.
- `AbsMatrixNxN.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a grid (array of arrays) with with SI-values for the quantities. The number of rows and columns in the grid have to be the same, and the grid cannot be 'ragged'.
- `AbsMatrixNxN.of(Q[] data, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a row-major array with quantities. The number of elements in the array needs to be a perfect square.
- `AbsMatrixNxN.of(Q[][] grid, Reference reference)` <br>
  creates an `AbsMatrixNxN` based on a grid (array of arrays) with with quantities. The number of rows and columns in the grid have to be the same, and the grid cannot be 'ragged'.
- `AbsMatrixNxN.of(A[] absData)` <br>
  creates an `AbsMatrixNxN` based on a row-major array containing the absolute quantities. The number of elements in the array needs to be a perfect square. The display unit and reference are taken from the absolute quantity at position `[0]`. The reference points of all quantities should be the same.
- `AbsMatrixNxN.of(A[][] absGrid)` <br>
  creates an `AbsMatrixNxN` based on a square grid (array of arrays) of absolute quantities. The number of rows and columns in the grid have to be the same, and the grid cannot be 'ragged'. The display unit and reference are taken from the absolute quantity at position `[0][0]`. The reference points of all quantities should be the same.


### Creating an `AbsMatrixNxM`

The `AbsMatrixNxM` class is the most generic matrix class. It can be used for matrices of any size (1x1 and up). Data can be stored as single-precision `float` variable, or as double-precision `double` values. Both dense storage (store every number) and sparse storage (only store non-zero values) are possible. 

Several methods exist to instantiate an `AbsMatrixNxM`.

The **DataGridSi**-based methods store the data in the `dataGridSi` object, which can be `DenseDoubleDataSi`, `SparseDoubleDataSi`, `DenseFloatDataSi`, or `SparseFloatDataSi`. These objects are instantiated through one of their `of()`, `ofSi()` or constructor methods. For many `of` and `ofSi` methods and the constructor, the number of rows and columns of the matrix need to be provided for the `DataGridSi` object to know the shape of the matrix. A `double[6]` array of SI values can represent a 2x3 matrix, but also a 3x2 matrix or a 1x6 or 6x1 matrix or vector. All four shapes can be stored in the `DataGridSi` object by providing the number of rows and columns.

The **array**-based methods use a row-major array. This means that the data is presented "row-by-row", so, `{m11, m12, m13, m21, m22, m23}` for a 2x3 matrix. A `(r,c)` value is retrieved by `m[index]`, `index = r * cols() + c` where r, c are 0-based indices. 

The **grid**-based methods count the rows in the 'outer' (first) array `[r][]`, and the columns in the 'inner' second array `[][c]`. A `(r,c)`value is retrieved by `m[r][c]`. 'Ragged' grids are not allowed and result in an `IllegalArgumentException`. 


- `new MatrixNxM<Q>(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a `DataGridSi` storage object. More information can be found in the [storage](storage) section. 
- `AbsMatrixNxM.of(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a `DataGridSi` storage object. More information can be found in the [storage](storage) section. 
- `AbsMatrixNxM.of(double[] dataInUnit, int rows, int cols, Unit unit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a row-major array with values expressed in the given unit. The length of the array needs to be equal to `rows * cols`.
- `AbsMatrixNxM.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a grid (array of arrays) with values expressed in the given unit. The grid cannot be 'ragged'.
- `AbsMatrixNxM.ofSi(double[] dataSi, int rows, int cols, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a row-major array with SI-values for the quantities. The length of the array needs to be equal to `rows * cols`.
- `AbsMatrixNxM.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a grid (array of arrays) with with SI-values for the quantities. The grid cannot be 'ragged'.
- `AbsMatrixNxM.of(Q[] data, int rows, int cols, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a row-major array with quantities. The length of the array needs to be equal to `rows * cols`.
- `AbsMatrixNxM.of(Q[][] grid, Reference reference)` <br>
  creates an `AbsMatrixNxM` based on a grid (array of arrays) with with quantities. The grid cannot be 'ragged'.
- `AbsMatrixNxM.of(A[] absData, int rows, int cols)` <br>
  creates an `AbsMatrixNxM` based on a row-major array containing the absolute quantities. The length of the array needs to be equal to `rows * cols`. The display unit and reference are taken from the absolute quantity at position `[0]`. The reference points of all quantities should be the same.
- `AbsMatrixNxM.of(A[][] absGrid)` <br>
  creates an `AbsMatrixNxM` based on a grid (array of arrays) of absolute quantities. The grid cannot be 'ragged'. The display unit and reference are taken from the absolute quantity at position `[0][0]`. The reference points of all quantities should be the same.

 