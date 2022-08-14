# Delft Java UNIT System

DJUNITS is a set of Java classes that make life easy for scientific software writers by catching many common errors at compile time 
and some others at runtime.

* DJUNITS performs automatic conversions between most commonly used units of the same type. E.g., conversion of distances from Miles 
  to kilometers.
* DJUNITS stores all values internally in the basic SI unit for that quantiry. The value can be converted to any (user-selectable) 
  suitable unit for display.
* DJUNITS distinguishes Absolute and Relative values to catch common errors at compile time,
* DJUNITS ensures that quantities expressed in different (but compatible) units are correctly added together. E.g. a distance in 
  Miles is correctly added to a distance in kilometers.
* DJUNITS knows or computes the SI type of the result when a value in one unit is multiplied, or divided by another value 
  (that may have another unit),
* DJUNITS handles Scalars, Vectors and Matrices.
* DJUNITS stores almost everything in immutable objects. Vectors and Matrices also come in a Mutable variant where the stored values 
  can be modified one by one or all at once.
* DJUNITS stores values as Float or Double values.


## Origin

DJUNITS was developed at the Delft University of Technology as part of the Open Traffic Simulator project (started in 2014).

In August 2015 it became obvious that the units and values classes developed for the Open Traffic Simulator were sufficiently 
mature to be used in other projects.

The main authors/contributors of the DJUNITS project are Alexander Verbraeck and Peter Knoppers.

## Absolute and Relative values

Values in DJUNITS are either Absolute or Relative.

An Absolute value is a value measured from a standard reference. For geographical directions North and East should be Absolute 
values. Adding two absolute values together makes no sense. Subtracting one absolute value from another does make sense (and results 
in a Relative value). Subtracting East from North should result in an angle of ±90° or ±π/2 (depending on the unit used to express 
the result). An absolute unit needs reference to be useful. Values subtracted from each other need to know their reference to be 
able to carry out the subtraction.

A Relative value expresses the difference between two (Absolute or Relative) values. The angle in the example above is a Relative 
value. Relative values can be added together and subtracted from each other (resulting in Relative values). Adding a Relative 
value to an Absolute value results in an Absolute value. Subtracting a Relative value from an Absolute value also results in 
an Absolute value.

In the geographical example, directions are Absolute and angles are Relative. Similarly, when applied to lengths, positions are 
Absolute and distances are Relative.

Generally, if adding a value to itself makes no sense, the value is Absolute; otherwise it is Relative.

| Operation   | Operands              | Result      |
| ----------- | --------------------- | ----------- |
| + (plus)    | Absolute + Absolute   | Not allowed |
| + (plus)    | Absolute + Relative   | Absolute    |
| + (plus)    | Relative + Absolute   | Absolute    | 
| + (plus)    | Relative + Relative   | Relative    |
| - (minus)   | Absolute - Absolute   | Relative (corresponding quantity) |
| - (minus)   | Absolute - Relative   | Absolute    |
| - (minus)   | Relative - Absolute   | Not allowed | 
| - (minus)   | Relative - Relative   | Relative    |
| * (times)   | Absolute * Absolute   | Not allowed |
| * (times)   | Absolute * Relative   | Not allowed |
| * (times)   | Relative * Absolute   | Not allowed |
| * (times)   | Relative * Relative   | Relative (different quantity) |
| / (divide)  | Absolute / Absolute   | Not allowed |
| / (divide)  | Absolute / Relative   | Not allowed |
| / (divide)  | Relative / Absolute   | Not allowed |
| / (divide)  | Relative / Relative   | Relative (different quantity) |

Attempts to perform operations that are marked not allowed are caught at compile time.

All quantities make sense as Relative values. The four quantities that also make sense as Absolute values are listed in the 
table below.


| Quantity    | Absolute interpretation | Absolute class<br/>and Unit | Relative interpretation | Relative class<br/> and Unit |
| ----------- | ----------------------- | ----------------------------| ----------------------- | ---------------------------- |
| Length      | Position                | Position<br/>PositionUnit   | Distance                | Length<br/>LengthUnit        |
| Angle       | Direction or Slope      | Direction<br/>DirectionUnit | Angle (direction or slope difference) | Angle<br/>AngleUnit |
| Temperature | Temperature             | AbsoluteTemperature<br/>AbsoluteTemperatureUnit | Temperature difference | Temperature<br/>TemperatureUnit |
| Time        | Time (instant)          | Time<br/>TimeUnit           | Duration                | Duration<br/>DurationUnit    |

The use of Absolute in relation to Temperature here may be confusing. In the table above, an absolute temperature is not 
necessarily expressed in Kelvin. E.g. the melting temperature of water at normal atmospheric pressure is an Absolute value 
(it does not make sense to add this temperature to itself). In DJUNITS this value would internally be stored as 273.15 K, but 
on display it may be converted (back) to Celsius and displayed as 0 °C. A temperature difference of 5 K (Kelvin) is a Relative, 
even though Kelvin is often called absolute temperature.

Dimensionless is a special relative unit in DJUNITS that has a unit of 1.


## Units

DJUNITS has a large number of pre-defined units. Internally, all values are stored in SI-units or an equivalent standard unit. For scalar values, the field is called si and can be retrieved as it is a public (immutable) field. Alternatively, the getSI() method can be used. The internal storage in SI units allows addition and subtraction of values that have been initialized using different units. Formatting and expressing the unit can be done using any defined unit. The code below illustrates some of the features.

```java
Speed speed1 = new Speed(30, SpeedUnit.MILE_PER_HOUR);
System.out.println("speed1:     " + speed1);
Speed speed2 = new Speed(10, SpeedUnit.METER_PER_SECOND);
System.out.println("speed2:     " + speed2);
Speed diff = speed1.minus(speed2);
// Default display unit will be SI unit for speed:
System.out.println("difference: " + diff); 
// Change default display unit; internal SI value is unaltered:
diff.setDisplayUnit(SpeedUnit.MILE_PER_HOUR); 
System.out.println("difference: " + diff);
// Works, but not mistake-safe:
System.out.println("difference: " + diff.getInUnit(SpeedUnit.KNOT) + " kt"); 
// Safer:
System.out.println("difference: " + diff.toString(SpeedUnit.KNOT)); 
// Programmer must be really sure that SI-unit is m/s:
System.out.println("difference: " + diff.si + " m/s (si)"); 
// Same as previous:
System.out.println("difference: " + diff.getSI() + " m/s (si)"); 
// Safer:
System.out.println("difference: " + diff.toString(SpeedUnit.SI) + " (si)"); 
System.out.println("difference: " + diff.toString(SpeedUnit.KM_PER_HOUR));
```

This would create the following output:

```
speed1:     30.0000000 mi/h
speed2:     10.0000000 m/s
difference: 3.41120000 m/s
difference: 7.63063708 mi/h
difference: 6.630842332613389 kt
difference: 6.63084233 kt
difference: 3.411199999999999 m/s (si)
difference: 3.411199999999999 m/s (si)
difference: 3.41120000 m/s (si)
difference: 12.2803200 km/h
```

When a class implements the interface UNITS (org.djunits.unit.UNITS), all defined units are available without the prefix XxxUnit. So, 
in that case a Length can be defined as new Length(12.0, METER) instead of Length(12.0, LengthUnit.METER).


## Multiplication and Division

Multiplying or dividing physical quantities produces a result in a different physical unit. There is no general way (we could think of) 
where the Java compiler can check the type of the result in the general case. Therefore DJUNITS has an extensive list of built-in 
multiplication and division operations with known result type. For instance

```java
Speed speed = new Speed(50, SpeedUnit.KM_PER_HOUR);
Duration duration = new Duration(0.5, DurationUnit.HOUR);
Length distance = speed.multiplyBy(duration);
Acceleration acc0 = speed.divideBy(duration);
Area area = distance.multiplyBy(distance);
Volume vol = area.multiplyBy(distance);
```

DJUNITS knows that the result of multiplication of a speed and a time is a distance. The value of distance is 2500 m.

Although we're not entirely sure, we believe that there is never a need for multiplication or division with an Absolute operand. 
It just does not make sense to multiply 23 September 2015, 3 PM (an absolute Time) by 2...


## Catch mistakes at compile time where possible

DJUNITS is designed to protect the programmer from easily made mistakes:

```java
Speed speed = new Speed(12, SpeedUnit.KM_PER_HOUR);
Length length = new Length(4, LengthUnit.MILE);

// Good:
Duration howLongOK = length.divide(speed); 

// Does not compile; result would be a frequency:
Duration howLongWrong = speed.divide(length); 

// Does not compile; subtracting a length from a speed make no sense:
Speed other = speed.minus(length); 

// Throws a UnitRuntimeException:
Acceleration acceleration = speed.times(speed).asAcceleration(); 

// OK:
Energy kineticEnergy = speed.times(speed).times(new Mass(3, MassUnit.KILOGRAM)
    .times(0.5)).asEnergy(); // OK
```

The mistakes on the lines with comments starting with Does not compile will be caught at compile time. In a development environment that continously checks for coding errors (like eclipse) such mistakes will be marked in by the java editor.

The before-last line multiplies a speed by another speed. The result of this operation is not something that DJUNITS supports directly. Such scalars can be cast to something DJUNITS does know of with an asXxx method. Whether that cast is permitted can only be checked at runtime and this example would fail with a UnitRuntimeException: cannot cast 11.1111111 m2/s2 to Acceleration.

A correct example where DJUNITS does not know the unit of the result (thus requiring an asXXX() cast) is:

```java
Energy kineticEnergy = speed.times(speed).times(new Mass(3, MassUnit.KILOGRAM)
    .times(0.5)).asEnergy(); // OK
System.out.println(kineticEnergy);
```

This would print:

```
16.6666667 J
```


## Scalars, Vectors and Matrices

Simple values are referred to as scalars. DJUNITS also handles groups of values (these must all be of the same unit) as vectors or 
matrices. Vectors and matrices come in four varieties:

* Dense, Immutable
* Dense, Mutable
* Sparse, Immutable
* Sparse, Mutable

Dense vectors and matrices use arrays to store the values. Sparse vectors and matrices use an indexed structure to store only the 
non-zero values. Numeric 0.0 values are not stored explicitly in Sparse vectors and matrices. Very large vectors and matrices with 
lots of 0.0 values are more efficiently stored in Sparse organization.

Immutable vectors and matrices do not provide methods to change any of their values. Mutable vectors and matrices have methods to 
update their values. Changing values from or to zero in mutable sparse vectors or matrices is a slow operation.


## Doubles and Floats

The Java double precision floating point value takes 8 bytes of memory, the float value takes 4 bytes. Both are available in DJUNITS. 
The typed double values use no special prefix indicating their precision. So the scalar type that stores a speed as a double is named 
Speed. Similarly, SpeedVector and SpeedMatrix store their values in double arrays. DJUNITS types that use Floats to store their (si) 
values have class names prefixed with Float. The float speed scalar class is therefore FloatSpeed, and the equivalent vector and 
matrix classes are FloatSpeedVector and FloatSpeedMatrix.


## Extensions

Several extensions are under consideration:

* Typed vectors and matrices, so a LengthMatrix can be multiplied with the inverse of a DurationMatrix (units in 1/s) to give a 
  SpeedMatrix. This can be cell-cell multiplication (n x m matrix 'times' an n x m matrix yielding an n x m matrix) or real matrix 
  multiplication (n x m matrix times an m x p matrix yielding an n x p matrix).
* Adding complex scalars, vectors and matrices. With the code generator, it should be quite easy to ready DJUNITS for complex typed 
  scalars, vectors, and matrices.
* Adding BigDecimal scalars, vectors and matrices. With the code generator, it should be quite easy to ready DJUNITS for BigDecimal 
  typed scalars, vectors, and matrices.


## Documentations and test reports

DJUNITS documentation and test reports for the current version can be found at https://djunits.org/docs/current and the API can be 
found at https://djunits.org/docs/current/apidocs/index.html.
