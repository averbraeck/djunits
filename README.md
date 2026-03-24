# djunits version 6
## Delft Java UNIT System

DJUNITS is a set of Java classes that make life easier for scientific software writers, modelers, and anyone who uses quantities 
in their code, by catching many common errors with the use of quantities and units at compile time and some others at runtime.

* DJUNITS performs automatic conversions between most commonly used units of the same quantity. E.g., conversion of distances from miles 
  to kilometers.
* DJUNITS stores all values internally in the basic SI unit for that quantity. The value can be converted to any (user-selectable) 
  suitable unit for display.
* DJUNITS distinguishes absolute and relative values to catch common errors at compile time.
* DJUNITS ensures that values of the same quantity expressed in different units are correctly added together. E.g. a distance in 
  miles is correctly added to a distance in kilometers.
* DJUNITS knows or computes the SI type of the result when a value in one unit is multiplied, or divided by another value 
  (that may have another unit).
* DJUNITS handles Scalars, Vectors and Matrices, as well as quantity tables.
* DJUNITS stores everything in immutable objects, except for the display unit that can be changed. 
* DJUNITS stores the data for vectors and matrices as float or double values, and using dense or sparse storage.


## Origin

DJUNITS was developed at Delft University of Technology as part of the Open Traffic Simulator project (started in 2014).

In August 2015 it became obvious that the units and values classes developed for the Open Traffic Simulator were sufficiently 
mature to be used in other projects. In 2026, a complete re-implementation of djunits took place, culminating in djunits 6.

The main authors/contributors of the DJUNITS project are Alexander Verbraeck, Peter Knoppers and Wouter Schakel.


## Absolute and Relative values

Values in DJUNITS are either absolute or relative.

An absolute value is a value measured from a standard reference. Examples are time with a reference point 1-1-1970 (UNIX epoch) or 
a reference point 1-1-0000 (Gregorian calendar time). As an other example, geographical directions, a direction can use North and East 
as reference points. Adding two absolute values together makes no sense. Subtracting one absolute value from another does make sense 
(and results in a relative value). Subtracting East from North should result in an angle of ±90° or ±π/2 rad (depending on the unit used 
to express the result). An absolute quantity always needs a reference to be useful. Values subtracted from each other need to know 
their reference to be able to carry out the subtraction. Therefore, a reference is explicitly stored with an absolute quantity.

A relative value expresses the difference between two (absolute or relative) values. The angle in the example above is a relative 
value. Relative values can be added together and subtracted from each other (resulting in relative values). Adding a relative 
value to an absolute value results in an absolute value. Subtracting a relative value from an absolute value also results in 
an absolute value.

In the geographical example, directions are absolute and angles are relative. Similarly, when applied to lengths, positions are 
absolute and distances are relative.

Generally, if adding a value to itself makes no sense, the value is absolute; otherwise it is relative.

| Operation 	| Operands              | Result      |
| ----------- | --------------------- | ----------- |
| + (plus) 	  | Absolute + Absolute 	| Not allowed |
| + (plus) 	  | Absolute + Relative 	| Absolute    |
| + (plus)    | Relative + Absolute	  | Absolute 	  | 
| + (plus)    | Relative + Relative	  | Relative    |
| - (minus)	  | Absolute - Absolute 	| Relative    |
| - (minus)	  | Absolute - Relative 	| Absolute    |
| - (minus)   | Relative - Absolute	  | Not allowed | 
| - (minus)   | Relative - Relative	  | Relative    |
| * (times) 	| Absolute * Absolute	  | Not allowed |
| * (times) 	| Absolute * Relative	  | Not allowed |
| * (times) 	| Relative * Absolute	  | Not allowed |
| * (times) 	| Relative * Relative	  | Relative (see Note below) |
| / (divide) 	| Absolute / Absolute	  | Not allowed |
| / (divide) 	| Absolute / Relative	  | Not allowed |
| / (divide) 	| Relative / Absolute	  | Not allowed |
| / (divide) 	| Relative / Relative	  | Relative (see Note below) |

> **Note** that when multiplying two relative quantities, the resulting quantity is of a different type: the multiplication of two `Length` quantities results in an `Area` quantity. The same holds for division: dividing a `Length` quantity by a `Duration` quantity results in a `Speed` quantity.

Attempts to perform operations that are marked not allowed are caught at compile time.

All quantities make sense as relative values. The four quantities that also make sense as absolute values are listed in the 
table below.


| Quantity    | Absolute interpretation | Absolute class<br/>and Unit | Relative interpretation | Relative class<br/> and Unit |
| ----------- | ----------------------- | ----------------------------| ----------------------- | ---------------------------- |
| Length      | Position                | Position<br/>Length.Unit   | Distance                | Length<br/>Length.Unit        |
| Angle       | Direction or Slope      | Direction<br/>Angle.Unit | Angle (direction or slope difference) | Angle<br/>Angle.Unit |
| Temperature | Temperature             | Temperature<br/>Temperature.Unit | Temperature difference | TemperatureDifference<br/>Temperature.Unit |
| Time        | Time (instant)          | Time<br/>Duration.Unit           | Duration                | Duration<br/>Duration.Unit    |

`Dimensionless` is a special relative unit in DJUNITS that has a `Unitless` unit.


## Units

DJUNITS has a large number of pre-defined units. Internally, all values are stored in SI-units or an equivalent standard unit. For scalar values, the field can be retrieved with the `si()` method. The internal storage in SI units allows addition and subtraction of values that have been initialized using different units. Formatting and expressing the unit can be done using any defined unit. The code below illustrates some of the features.

```java
Speed speed1 = new Speed(30, Speed.Unit.mi_h);
System.out.println("speed1:     " + speed1);
Speed speed2 = new Speed(10, Speed.Unit.m_s);
System.out.println("speed2:     " + speed2);
Speed diff = speed1.subtract(speed2);

// Default display unit will be SI unit for speed:
System.out.println("difference: " + diff);

// Change default display unit; internal SI value is unaltered:
diff.setDisplayUnit(Speed.Unit.mi_s);
System.out.println("difference: " + diff);

// Works, but error-prone and not localizable:
System.out.println("error-prone " + diff.getInUnit(Speed.Unit.kt) + " kt");

// Safer, the unit is provided by the system and localizable:
System.out.println("difference: " + diff.toString(Speed.Unit.kt));
System.out.println("difference: " + diff.toString(Speed.Unit.km_h));
```

This would create the following output:

```
speed1:     30.0000000 mi/h
speed2:     10.0000000 m/s
difference: 7.63063708 mi/h
difference: 0.00211962 mi/s
error-prone 6.630842332613389 kt
difference: 6.63084233 kt
difference: 12.2803200 km/h
```

It is possible to use units without the Quantity.Unit. The `of()` methods are helper methods to easily use a unit, e.g.,
`Length.of(12.0, "m")` instead of `new Length(12.0, Length.Unit.m)`.


## Multiplication and Division

Multiplying or dividing physical quantities produces a result in a different physical unit. There is no generic way for the Java 
compiler to check the type of the result. Therefore, DJUNITS has an extensive list of built-in multiplication 
and division operations with known result type. For instance:

```java
Speed speed = new Speed(50, Speed.Unit.km_h);
Duration duration = new Duration(0.5, Duration.Unit.h);
Length distance = speed.multiply(duration);
Acceleration acc = speed.divide(duration);
Area area = distance.multiply(distance);
Volume vol = area.multiply(distance);
```

DJUNITS knows that the result of multiplication of a speed and a time is a distance. The value of the distance in the above case is 2500 m.

There is never a need for multiplication or division with an absolute operand. It just does not make sense to multiply 
23 September 2025, 3 PM (an absolute Time) by 2.


## Catch mistakes at compile time where possible

DJUNITS is designed to protect the programmer from easily made mistakes:

```java
Speed speed = new Speed(12, Speed.Unit.km_h);
Length length = new Length(4, Length.Unit.mi);

// Good:
Duration howLongOK = length.divide(speed); 

// Does not compile; result would be a frequency:
Duration howLongWrong = speed.divide(length); 

// Does not compile; subtracting a length from a speed makes no sense:
Speed other = speed.subtract(length); 

// Throws a UnitRuntimeException:
Acceleration acceleration = speed.multiply(speed).as(Acceleration.Unit.m_s2); 

// OK:
Energy kineticEnergy = speed.multiply(speed).multiply(new Mass(3, Mass.Unit.kg)
    .scaleBy(0.5)).as(Energy.Unit.J);
```

The mistakes on the lines with comments starting with `Does not compile` will be caught at compile time. In a development environment that continuously checks for coding errors (like Eclipse) such mistakes will be flagged by the Java editor.

The before-last line multiplies a speed by another speed. The result of this operation is not something that DJUNITS supports directly. Such scalars can be cast to something DJUNITS does know of with an `as(TargetUnit)` method. Whether that cast is permitted can only be checked at runtime and this example would fail with:

```
IllegalArgumentException: org.djunits.quantity.def.Quantity.as (804): 
    Quantity.as(m/s2) called, but units do not match: m2/s2 <> m/s2
```

A correct example where DJUNITS does not know the unit of the result (thus requiring an `as(TargetUnit)` cast) is:

```java
Energy kineticEnergy = speed.multiply(speed).multiply(new Mass(3, Mass.Unit.kg)
    .scaleBy(0.5)).as(Energy.Unit.J);
System.out.println(kineticEnergy);
```

This would print:

```
16.6666667 J
```


## Scalars, Vectors and Matrices

Simple values are referred to as quantities or scalars. DJUNITS also handles groups of values (these must all be of the same unit) such as vectors or 
matrices. Efficient classes have been created for the 'small' vectors and matrices of size 1 to 3. The following vector and matrix classes exist:

* `Vector1` for a vector with just one entry
* `Vector2.Row` and `Vector2.Col` for a row and column vector of size 2
* `Vector3.Row` and `Vector3.Col` for a row and column vector of size 3
* `VectorN.Row` and `VectorN.Col` for a row and column vector of any size
* `Matrix1x1` for a square matrix of size 1 x 1
* `Matrix2x2` for a square matrix of size 2 x 2
* `Matrix3x3` for a square matrix of size 3 x 3
* `MatrixNxN` for a square matrix of any size
* `MatrixNxM` for a non-square or square matrix of any size

The larger vectors and matrices (`VectorN`, `MatrixNxN`, `MatrixNxM`) come in four varieties:

* Dense, Double
* Dense, Float
* Sparse, Double
* Sparse, Float

Dense vectors and matrices use arrays to store the values. Sparse vectors and matrices use an indexed structure to store only the 
non-zero values. Numeric 0.0 values are not stored explicitly in Sparse vectors and matrices. Very large vectors and matrices with 
lots of 0.0 values are more efficiently stored using Sparse data storage.

The Java double precision floating point value takes 8 bytes of memory, the float value takes 4 bytes. Both are available in DJUNITS
for storage in `VectorN`, `MatrixNxN`, `MatrixNxM`. 

Creating vectors and matrices is straightforward:

```java
System.out.println("\n\nMatrices");
var mat = Matrix2x2.of(new double[][] {{1.0, 2.0}, {5.0, 4.0}}, Duration.Unit.s);
System.out.println("matrix:\n" + mat);
System.out.println("\nmatrix + matrix:\n" + mat.add(mat));
System.out.println("\nmatrix + 1 day:\n" + mat.add(Duration.of(1.0, "day")));
System.out.println("\ndeterminant: " + mat.determinant());
```

This prints:

```
Matrices
matrix:
[1.00000000, 2.00000000
 5.00000000, 4.00000000] s

matrix + matrix:
[2.00000000, 4.00000000
 10.0000000, 8.00000000] s

matrix + 1 day:
[86401.0000, 86402.0000
 86405.0000, 86404.0000] s

determinant: -6.0000000 s2
```


## Vector and Matrix calculations

All standard vector and matrix operations are available, such as row and column extraction, calculation of determinant, inverse, and adjugate, transposing of vectors and matrices, matrix-matrix multiplication, matrix-vector multiplication, vector-vector multiplication, matrix-quantity multiplication, and vector-quantity multiplication. Hadamard (entry-by-entry) operations on the entries of a vector or matrix are also supported. In all cases, units of the resulting vector or matrix are calculated. This means that if we multiply a `Length` matrix with a `Length` matrix, we get a resulting matrix of quantity `Area` with an `Area.Unit` as its unit.

The following example first shows a Hadamard operation, followed by an algebraic matrix multiplication:

```java
VectorN.Col<Length> lv1 = VectorN.Col.of(
    new double[] {10, 20.0, 60, 120.0, 400.0}, Length.Unit.km);
Duration duration = Duration.of(2.0, "h");
VectorN.Col<Speed> sv1 = 
    lv1.divideEntries(duration).as(Speed.Unit.km_h);
System.out.println("Length: " + lv1);
System.out.println("Speed : " + sv1);

MatrixNxM<Length> lm4x2 = MatrixNxM.of(
    new double[][] {{1, 2, 3, 4}, {5, 6, 7, 8}}, Length.Unit.m);
MatrixNxM<Length> lm2x4 = MatrixNxM.of(
    new double[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}}, Length.Unit.m);

var mult44 = lm4x2.multiply(lm2x4).as(Area.Unit.m2);
System.out.println("\nMatrix1:\n" + lm4x2);
System.out.println("Matrix2:\n" + lm2x4);
System.out.println("Multiplication:\n" + mult44);

Matrix2x2<Area> mult22 = lm4x2.multiply(lm2x4).asMatrix2x2().as(Area.Unit.a);
System.out.println("\nMatrix1:\n" + lm2x4);
System.out.println("Matrix2:\n" + lm4x2);
System.out.println("Multiplication:\n" + mult22);
```

The last matrix is cast to a strongly typed `Matrix2x2<Area>`, where values are expressed in `are`. The code results in the following output:

```
Length: Col[10.0, 20.0, 60.0, 120.0, 400.0] km
Speed : Col[5.0, 10.0, 30.0, 60.0, 200.0] km/h

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


## QuantityTable

For those cases where a tabular storage of data is needed, but it is not necessary to carry out matrix or vector operations, the `QuantityTable` exists. It behaves like a `MatrixNxM` without the overhead of a matrix. Hadamard (entry-by-entry) operations are allowed on the `QuantityTable`. 


## Localization

DJUNITS has been designed with localization in mind. This means that quantities, units, vectors and matrices can print the unit information based on a resource bundle file for a localization. On the input side, quantities can also be created using localized string representations, as the example below shows:

```java
System.out.println("\nLOCALIZATION US");
Locale.setDefault(Locale.US);
var speed = Speed.of(50.0, "km/h");
System.out.println("Absorbed dose name: " + AbsorbedDose.ONE.getName());
System.out.println("50 km/h = " + speed);
System.out.println("Acceleration: " + Units.localizedQuantityName(Acceleration.Unit.class));

System.out.println("\nLOCALIZATION NL");
Locale.setDefault(Locale.forLanguageTag("nl"));
System.out.println("Absorbed dose name: " + AbsorbedDose.ONE.getName());
System.out.println("50 km/h = " + speed);
System.out.println("Acceleration: " + Units.localizedQuantityName(Acceleration.Unit.class));

var d3du = Duration.valueOf("3 dag");
d3du.setDisplayUnit("u");
System.out.println("3 dagen in uren: " + d3du);
```

This results in:

```
LOCALIZATION US
Absorbed dose name: Absorbed dose
50 km/h = 50.0000000 km/h
Acceleration: Acceleration

LOCALIZATION NL
Absorbed dose name: Geabsorbeerde straling
50 km/h = 50,0000000 km/u
Acceleration: Versnelling
3 dagen in uren: 72,0000000 u
```

The following localizations are currently bundled with DJUNITS: `en_US`, `de`, `en_GB`, `es`, `fr`, `it`, `ja`, `ko`, `nl`, `pt`, `zh_TW`, `zh`. 


## Difference with previous versions

DJUNITS version 6 is different from versions 1 to 5, and not upward compatible. Version 6 is a completely new implementation of the code with the following notable differences:

- The `Quantity` is now the central object from which all quantities inherit.
- The method `getSI()` and the field `si` for a `Quantity` have been replaced by the method `si()`.
- Unit classes are inner classes of the quantity, such as `Energy.Unit`.
- Units use 'human readable' definitions, such as `Volume.Unit.m3` and `Length.Unit.km`. 
- The `SIQuantity` and `SIUnit` classes have been implemented as a normal quantity.
- The `DimensionlessUnit` unit class has been renamed to `Unitless`.
- Localization is built-in as a design guideline and not as an afterthought. 
- Absolute quantities have been re-implemented using a `Reference` for the reference point. 
- Operation names are streamlined across quantities, vectors and matrices, e.g., `add`, `subtract`, `multiply`, `divide`.
- Vector and matrix classes use generics for definitions such as `Matrix3x3<Area>`, and only allow correct operations.
- Vector and matrix operations such as trace, multiplication, and inverse are now fully supported with consistent unit calculations.
- Hadamard operations have been added to vector and matrix calculations.
- The `QuantityTable` has been added for storage of tabular quantity data.

The manual of the latest **version 5** can be found at <a href="https://djunits.org/docs/5.4.2/manual/">https://djunits.org/docs/5.4.2/manual/</a>. 


## Documentations and test reports

DJUNITS documentation and test reports for the current version can be found at [https://djunits.org/docs/latest](https://djunits.org/docs/latest). The manual is at [https://djunits.readthedocs.io](https://djunits.readthedocs.io), or at [https://djunits.org/manual](https://djunits.org/manual) The API can be 
found at [https://djunits.org/docs/latest/apidocs/index.html](https://djunits.org/docs/latest/apidocs/index.html).
