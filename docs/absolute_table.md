# Quantity Table with Absolute Quantities

## Introduction

ABsolute quantity tables are 2-dimensional tables with data of absolute quantities of the same type. They can be regarded as generic data containers, suitable for data interfaces or method input types. In a sense, it acts like an `AbsMatrixNxM` without the ability to carry out matrix and vector calculations, and without the Hadamard (entry-by-entry) operations.

The `AbsQuantityTable` supports dense storage in a `double[]` or `float[]` array, or sparse storage, where values are stored with an integer-based row-column index and a `double` or `float` value. Since the sparse storage involves quite some overhead, tables need to have a significant percentage of 0-values (40-50% or more) for using sparse storage to make sense. 


## Quantity table operations

The generic methods of a `AbsQuantityTable` are:

- `int rows()` returns the number of rows of the quantity table.
- `int cols()` returns the number of columns of the quantity table.
- `getDisplayUnit()` returns the display unit of the entire `AbsQuantityTable`.
- `setDisplayUnit(unit)` sets a new display unit for the entire `AbsQuantityTable` based on a strongly typed `unit`.
- `setDisplayUnit(string)` sets a new display unit for the entire `AbsQuantityTable` based on a `String` representation of the unit.
- `getReference()` returns the reference point for all absolute quantities in the `AbsQuantityTable`.
- `boolean isRelative()` returns whether the underlying `AbsQuantity` is relative or not. Note that `AbsQuantityTable` only stores absolute quantities.
- `boolean isAbsolute()` returns whether the underlying `AbsQuantity` is absolute or not. Note that `AbsQuantityTable` only stores absolute quantities.
- `transpose()` returns a new `AbsQuantityTable` where the rows and columns are swapped.

Addition and subtraction of absolute vectors and matrices follow the rules for the [absolute quantity](../absolute_quantity):

- `absQuantityTable.add(quantity)` returns a new `absQuantityTable` where all entries of of `absQuantityTable` have been increased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absQuantityTable`.
- `absQuantityTable1.add(relQuantityTable2)` returns a new `absQuantityTable` where all entries of `relQuantityTable2` have been added to the corresponding entries of `absQuantityTable1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absQuantityTable1`. The number of rows and columns of `absQuantityTable1` and `relQuantityTable2` have to be equal, of course.
- `absQuantityTable.subtract(quantity)` returns a new `absQuantityTable` where all entries of of `absQuantityTable` have been decreased by the (relative) quantity. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absQuantityTable`.
- `absQuantityTable1.subtract(relQuantityTable2)` returns a new `absQuantityTable` where all entries of `relQuantityTable2` have been subtracted from the corresponding entries of `absQuantityTable1`. The `displayUnit` and `reference` of the resulting absolute vector are taken from `absQuantityTable1`. The number of rows and columns of `absQuantityTable1` and `relQuantityTable2` have to be equal, of course.
- `absQuantityTable.subtract(absQuantity)` returns a new **relative** `QuantityTable` where all entries of of `absQuantityTable` have been decreased by the provided absolute quantity. The `displayUnit` of the resulting relative vector is taken from `absQuantityTable`.
- `absQuantityTable1.subtract(absQuantityTable2)` returns a new **relative** `QuantityTable` where all entries of `absQuantityTable2` have been subtracted from the corresponding entries of `absQuantityTable1`. The `displayUnit` of the resulting absolute vector is taken from `absQuantityTable1`. The number of rows and columns of `absQuantityTable1` and `absQuantityTable2` have to be equal, of course.


## Obtaining values of quantity table entries

Several methods exist to get access to the entries of a `AbsQuantityTable`. When single entries, rows or columns are retrieved, two versions of the methods exist: a version where the row and column number are 0-based, and a version where the row and column number are 1-based. The 1-based methods have a name that starts with `m` for `matrix`, since the entry numbering of a matrix start with m<sub>11</sub>, and not with m<sub>00</sub>. So, there is an `si(row, col)` method where `row` ranges from `0` to `table.rows()-1` and `col` ranges from `0` to `table.cols()-1`, and an `msi(mRow, mCol)` method where `mRow` ranges from `1` up to and including `table.rows()` and `mCol` ranges from `1` up to and including `table.cols`.

Quantity-based value methods return a value `A` that is consistent with the absolute quantity stored in the `AbsQuantityTable`. Suppose `aqt` is a `AbsQuantityTable<Time, Duration>`. The result of the operation `aqt.get(1,3)` will then be a strongly typed `Time` quantity. The letter `A` in the methods below indicates that strongly typed absolute quantity such as `Time`. The letter `Q` indicates the relative quantity belonging to the absolute quantity, such as `Duration` as the relative quantity for `Time`.

If the underlying relative quantity table is needed, where all values are of type `Q` and relative to the reference point of the absolute quantity table, the method `getRelativeVecMat()` can be used. As an example, for an `AbsQuantityTable<Time, Duration>`, the `getRelativeVecMat()` method returns a `QuantityTable<Duration>` with the same dimensions, SI-content, and display unit.


A `AbsQuantityTable` contains the following methods to obtain its values:

### SI-based value methods

- `double[][] getSiGrid()` returns a 2-dimensional `double[][]` array with the SI-values of the entries in the quantity table. The SI-values are relative to the reference point of the quantity table.
- `double[] getSiArray()` returns the values of the quantity table in SI-units as a row-major `double[]` array with the same length as the quantity table. This means that for a quantity table with n rows and m columns, the data is stored as [a<sub>11</sub>, a<sub>12</sub>, ..., a<sub>1m</sub>, a<sub>21</sub>, a<sub>22</sub>, ..., a<sub>2m</sub>, ..., a<sub>n1</sub>, a<sub>n2</sub>, ..., a<sub>nm</sub>]. The SI-values are relative to the reference point of the quantity table.
- `double si(int row, int col)` returns the SI-value of the entry at the 0-based row and column.  The SI-value is relative to the reference point of the quantity table.
- `double msi(int mRow, int mCol)` returns the SI-value of the entry at the 1-based row indicated by `mRow` and 1-based column indicated by `mCol`.  The SI-value is relative to the reference point of the quantity table.


### Quantity-based value methods

- `A[][] getScalarGrid()` returns a 2-dimensional strongly typed absolute quantity array that represents the quantity table. The absolute quantities in the array will all have the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `A[] getScalarArray()` returns a 1-dimensional strongly typed row-major absolute quantity array that represents the quantity table. The absolute quantities in the array will all have the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `A get(int row, int col)` returns the absolute quantity representation of the entry at the 0-based row and column. The returned `AbsQuantity` will have the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `A mget(int mRow, int mCol)` returns the absolute quantity representation of the entry at the 1-based row indicated by `mRow` and 1-based column indicated by `mCol`. The returned `AbsQuantity` will have the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `getRelativeVecMat()` returns the 'embedded' relative vector or matrix, whose values are relative to the reference point of the `AbsVector`. The size and type of the returned vector are congruent with the type of the `AbsVector`.


### Retrieving quantity table rows

- `AbsVectorN.Row getRowVector(int row)` retrieves the quantity table row at the 0-based `row` as a row-vector with the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `AbsVectorN.Row mgetRowVector(int mRow)` retrieves the quantity table row at the 1-based `mRow` as a row-vector with the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `A[] getRowScalars(int row)` retrieves the quantity table row at the 0-based `row` as an array of quantities, where the quantities in the array have the same `displayUnit` and reference point as the original quantity table. 
- `A[] mgetRowScalars(int mRow)` retrieves the quantity table row at the 1-based `mRow` as an array of quantities, where the quantities in the array have the same `displayUnit` and reference point as the original matrix. Note that the resulting `A[]` array is 0-based.
- `double[] getRowSi(int row)` retrieves the quantity table row at the 0-based `row` as a `double[]` array with SI-values. The values are relative to the reference point of the quantity table.
- `double[] mgetRowSi(int mRow)` retrieves the quantity table row at the 1-based `mRow` as a `double[]` array with SI-values. Note that the resulting `double[]` array is 0-based. The values are relative to the reference point of the quantity table.


### Retrieving quantity table columns

- `AbsVectorN.Col getColumnVector(int col)` retrieves the quantity table column at the 0-based `col` as a column-vector with the same `displayUnit` and reference point as the original `AbsQuantityTable`. 
- `AbsVectorN.Col mgetColumnVector(int mCol)` retrieves the quantity table column at the 1-based `mCol` as a column-vector with the same `displayUnit` and reference point as the original `AbsQuantityTable`.
- `A[] getColumnScalars(int col)` retrieves the quantity table column at the 0-based `col` as an array of quantities, where the quantities in the array have the same `displayUnit` and reference point as the original quantity table. 
- `A[] mgetColumnScalars(int mCol)` retrieves the quantity table column at the 1-based `mCol` as an array of quantities, where the quantities in the array have the same `displayUnit` and reference point as the original quantity table. Note that the resulting `A[]` array is 0-based.
- `double[] getColumnSi(int col)` retrieves the quantity table column at the 0-based `col` as a `double[]` array with SI-values. The values are relative to the reference point of the quantity table.
- `double[] mgetColumnSi(int mCol)` retrieves the quantity table column at the 1-based `mCol` as a `double[]` array with SI-values. Note that the resulting `double[]` array is 0-based. The values are relative to the reference point of the quantity table.


## Mathematical operations for `AbsQuantityTable`

A `AbsQuantityTable` implements several mathematical operations. The most important ones are:

- `A mean()` returns the mean quantity value of the entries of the `AbsQuantityTable` as a strongly typed `AbsQuantity`.
- `A min()` returns the minimum quantity value of the entries of the `AbsQuantityTable` as a strongly typed `AbsQuantity`.
- `A max()` returns the maximum quantity value of the entries of the `AbsQuantityTable` as a strongly typed `AbsQuantity`.
- `A median()` returns the median quantity value of the entries of the `AbsQuantityTable` as a strongly typed `AbsQuantity`. The median value is the value  of the middle entry when all entries have been sorted on their SI-values. When the number of entries in the quantity table is even, the average of the two values that together make up the middle is returned. 


## Transforming the `AbsQuantityTable`

`AbsQuantityTable` objects do not implement matrix operations such as determinant, matrix multiplication, etc. If an `AbsQuantityTable` at some point needs to be used for matrix operations, the `asVector` and `asMatrix` methods can transform the `AbsQuantityTable` into an `AbsMatrix` or column or row `AbsVector` of any of the types. For this, the `AbsQuantityTable` implements the `asAbsMatrix1x1()`, `asAbsMatrix2x2()`, `asAbsMatrix3x3()`, `asAbsMatrixNxN()`, `asAbsMatrixNxM()`, `asAbsVector1()`, `asAbsVector2Row()`, `asAbsVector2Col()`, `asAbsVector3Row()`, `asAbsVector3Col()`, `asAbsVectorNRow()`, and `asAbsVectorNCol()` methods. These methods will check the consistency of the quantity table size with the desired vector or matrix type at runtime.

Reversely, `AbsMatrix` or column or row `AbsVector` instances can all be turned _into_ a `AbsQuantityTable` with the `asAbsQuantityTable()` method. 


### Creating a `AbsQuantityTable`

The `AbsQuantityTable` class can be used to represent quantity tables of any size (1x1 and up). Data can be stored as single-precision `float` variable, or as double-precision `double` values. Both dense storage (store every number) and sparse storage (only store non-zero values) are possible. 

Several methods exist to instantiate a `AbsQuantityTable`.

The **DataGridSi**-based methods store the data in the `dataGridSi` object, which can be `DenseDoubleDataSi`, `SparseDoubleDataSi`, `DenseFloatDataSi`, or `SparseFloatDataSi`. These objects are instantiated through one of their `of()`, `ofSi()` or constructor methods. For some `of` and `ofSi` methods, the number of rows and columns of the quantity table need to be provided for the `DataGridSi` object to know the shape of the quantity table. A `double[6]` array of SI values can represent a 2x3 quantity table, but also a 3x2 quantity table or a 1x6 or 6x1 quantity table. All four shapes can be stored in the `DataGridSi` object by providing the number of rows and columns.

The **array**-based methods use a row-major array. This means that the data is presented "row-by-row", so, `{m11, m12, m13, m21, m22, m23}` for a 2x3 quantity table. A `(r,c)` value is retrieved by `m[index]`, `index = r * cols() + c` where r, c are 0-based indices. 

The **grid**-based methods count the rows in the 'outer' (first) array `[r][]`, and the columns in the 'inner' second array `[][c]`. A `(r,c)`value is retrieved by `m[r][c]`. 'Ragged' grids are not allowed and result in an `IllegalArgumentException`. 

- `new AbsQuantityTable<A, Q>(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a `DataGridSi` storage object, a display unit and a reference point. More information can be found in the [storage](storage) section. 
- `AbsQuantityTable.of(DataGridSi dataSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a `DataGridSi` storage object, a display unit and a reference point. More information can be found in the [storage](storage) section. 
- `AbsQuantityTable.of(double[] dataInUnit, int rows, int cols, Unit unit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a row-major array with values expressed in the given unit. The data is relative to the provided reference point. The length of the array needs to be equal to `rows * cols`.
- `AbsQuantityTable.of(double[][] gridInUnit, Unit unit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a grid (array of arrays) with values expressed in the given unit. The data is relative to the provided reference point. The grid cannot be 'ragged'.
- `AbsQuantityTable.ofSi(double[] dataSi, int rows, int cols, Unit displayUnit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a row-major array with SI-values for the quantities. The length of the array needs to be equal to `rows * cols`.
- `AbsQuantityTable.ofSi(double[][] gridSi, Unit displayUnit, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a grid (array of arrays) with with SI-values for the quantities, a display unit and a reference point. The grid cannot be 'ragged'.
- `AbsQuantityTable.of(Q[] data, int rows, int cols, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a row-major array with quantities, and a reference point for the values. The length of the array needs to be equal to `rows * cols`.
- `AbsQuantityTable.of(Q[][] grid, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a grid (array of arrays) with with quantities, and a reference point for the values. The grid cannot be 'ragged'.
- `AbsQuantityTable.of(A[] data, int rows, int cols)` <br>
  creates a `AbsQuantityTable` based on a row-major array with absolute quantities. The length of the array needs to be equal to `rows * cols`.
- `AbsQuantityTable.of(A[][] grid)` <br>
  creates a `AbsQuantityTable` based on a grid (array of arrays) with with absolute quantities. The grid cannot be 'ragged'.
- `AbsQuantityTable.of(QuantityTable<Q> relativeTable, Reference reference)` <br>
  creates a `AbsQuantityTable` based on a relative quantity table, and a reference point for the values.

