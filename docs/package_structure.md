DJUNITS is divided into a number of packages:

* **org.djunits** easy methods for pre-condition testing. Copied from the [DJUTILS](https://djutils.org/manual/djutils-project/exceptions) project.

* **org.djunits.locale** for localization of the units. Currently, localization files are provided for English and Dutch. The localization files can be found in src/main/resources. If you like to write a localization for your own language, please do so (but contact us first so we can prevent two persons doing the same task). NOTE: Localization does not work as intended.
     
* **org.djunits.unit** where the unit classes are stored. All units are based on _one_ base unit (usually named `BASE` or `SI`). A builder is available for construction of the base unit of each unit. From this base any number of derived units can be created. E.g., each additional `SpeedUnit` is created providing a factor that scales it to the standard, SI-based speed unit `SpeedUnit.SI` using the deriveLinear method. The `deriveSI` method in `Unit` generates units with the standard SI prefixes (milli, micro, ..., kilo, Mega, ...). The deriveLinearOffset method is used to create absolute units that have different zero points (mainly used for absolute temperatures).
     
* **org.djunits.unit.quantity** A (physical) quantity is a property of a material or system that can be quantified by measurement. A physical quantity can be expressed as the combination of a value (magnitude) and a unit. For example, the physical quantity energy can be quantified as x joule where x is the value and joule is the unit. The `BASE` or `SI` value in each unit class is a `Quantity`.

* **org.djunits.unit.scale** defines the scales that can be used for a unit. Scales perform conversions of externally used values expressed in various units to and from the internally used si values. Examples are the `LinearScale` that is used in most units, the `OffsetLinearScale` that is used in, e.g., Temperature, the GradeScale that is used in percentage angles, and the `LogarithmicScale` that can be used in, e.g., Decibels. The `Scale` implements two important methods: a method to convert to the standard unit of that type (e.g. from degree Fahrenheit to Kelvin) and a method to convert from the standard unit of that type to the given type (e.g. from Kelvin to degree Fahrenheit).

* **org.djunits.si** handles SI prefixes (milli, micro, ...,  kilo, Mega, ...) and manipulation of SI coefficients to derive the resulting unit when quantities of various types are multiplied or divided.

* **org.djunits.unit.util** Exception classes and an interface (UNITS.java) for easy use of hundreds of units (at the expense of polluting the namespace of each class that implements this interface).

* **org.djunits.unitsystem** defines the different unit systems such as SI (meter, kilogram, second, mol, Ampere, candela, and Kelvin), SI Derived (e.g., degree Celcius, Joule, Ohm or kWh), SI Accepted (e.g, minute, hour or kilometer), and Imperial (e.g., degree Fahrenheit, mile, or pound). Additionally, some less used unit systems are defined such as AU (Atomic Units), MTS (meter-tonne-second), and CGS (centimeter-gram-second).
     
* **org.djunits.value** provides the base interfaces for the value implementations. Example interfaces are `Relative`, `Absolute`, `Value` and `IndexedValue` that each are used to ensure implementations of methods in the scalar, vector, and matrix subpackages. This package also contains a number of abstract classes that implement those elements of scalar, vector and matrix classes that are common to many or all.

* **org.djunits.value.base** provides the interfaces for `Scalar`, `Vector` and `Matrix` implementations.

* **org.djunits.value.formatter** provides classes that can help in formatting unitized values. This includes the `EngineeringFormatter` that renders floating point values in a prescribed number of characters (minimum 10), transiting to mantissa + exponent notation when normal notation would cause loss of precision. The exponent in mantissa + exponent notation is always a multiple of 3 to ease interpretation using SI prefixes (nano, micro, Mega, etc.).

* **org.djunits.value.function** provides two interfaces. One for functions that return a (dimensionless) double, e.g. trig functions, sqrt. The other for functions that return a value of the same type as the input, e.g., abs, ceil, floor, neg, rint)

* **org.djunits.value.storage** provides the `DenseData` and `SparseData` interfaces, the `StorageType` enum and the `AbstractStorage` class that implements what all vector and matrix data stores have in common.

* **org.djunits.value.util** provides functions for normalizing angles, scanning numbers and expressing values into some unit.

* **org.djunits.value.vdouble.function** interfaces for functions that yield a double value which can be applied (using parallel processing) to vectors and matrices. 

* **org.djunits.value.vdouble.matrix** contains the classes for matrices of double precision values in all supported units. Provides two types of classes for double precision implementations. The first one is the generic `DoubleMatrix` that can be instantiated with any unit in absolute or relative form through generalization. The second type has one specific class for each of the predefined units.

* **org.djunits.value.vdouble.matrix.base** contains the classes for matrices of double precision values in all supported units.

* **org.djunits.value.vdouble.matrix.data** common code for storing the SI values for double precision matrices.

* **org.djunits.value.vdouble.scalar** contains the classes for double precision scalar values in all supported units. It provides specific classes for the standard provided units such as `Area`, `Temperature`, `Duration`, and `Length` and a generic `SIScalar` class that can be instantiated with any unit. Here are seven ways to create a `Length` of 100 km (l1 .. l7 all get set to the same value and the equals method would confirm that the objects are equal):

```java
Length l1 = new Length(100.0, LengthUnit.KILOMETER);
Length l2 = Length.valueOf("100.0 km");
Length l3 = Length.of(100.0, "km");
Length l4 = SIScalar.of(100000.0, "m").asLength(LengthUnit.KILOMETER);
Length l5 = SIScalar.valueOf("100000.0 m").asLength(LengthUnit.KILOMETER);
Length l6 = SIScalar.valueOf("100000.0 m").asLength();
l6.setDisplayUnit(LengthUnit.KILOMETER);
Length l7 = SIScalar.valueOf("100000.0 m").as(LengthUnit.KILOMETER);
```

Time/Duration, Position/Length, AbsoluteTemperature/Temperature, and Direction/Angle have both an absolute and a relative form. All other scalars are always relative. Each class has a multitude of mathematical operations that can be applied on the scalar, such as goniometric functions, logarithms, power functions, etc. Furthermore, many multiplications and divisions are implemented in a strongly typed way that is checked by the compiler. If an `ElectricalPotential` scalar (e.g. in Volts) is divided by an `ElectricalCurrent` scalar (e.g. in Amperes), the result can only be assigned to an `ElectricalResistance` unit (e.g., in Ohms). Please note that the scalars are stored as a public float value in a field named `si`, always expressed in the base unit (SI if possible). In addition, the unit in which the scalar was created is stored (not necessarily the SI unit). In total the scalar consists therefore of the standard fields of an Object plus 64 bits for the si double and 64 bits of the pointer to its unit.
     
* **org.djunits.value.vdouble.scalar.base** contains the classes for double precision scalars in all supported units. It also contains the `Constants` class with the values of common physical constants.

* **org.djunits.value.vdouble.vector** contains implementations of vectors of all standard types and the generic SIVector type.  An example is:

```java
AbsoluteTemperatureVector tv1 = DoubleVector.instantiate(
    new double[] { 273.15, 290.4, 280.5, 279.1 },
    AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
// NEEDS EXAMPLES OF ALTERNATIVE WAYS TO DO IT
```

Note that this vector is defined as an absolute vector (because the unit is an AbsoluteTemperatureUnit), independent of its storage implementation (dense).
     
* **org.djunits.value.vdouble.vector.base** contains the classes for vectors of double precision values in all supported units.

* **org.djunits.value.vdouble.vector.data** common code for storing the SI values for double precision vectors.

* **org.djunits.value.vfloat.function** interfaces for functions that yield a float value which can be applied (using parallel processing) to vectors and matrices. 

* **org.djunits.value.vfloat.matrix** contains Dense and Sparse implementations of matrices of float values. The way to use the matrices is very much like the examples above for double matrices.

* **org.djunits.value.vfloat.matrix.base** contains the classes for matrices of float values in all supported units.

* **org.djunits.value.vfloat.matrix.data** common code for storing the SI values for float matrices.

* **org.djunits.value.vfloat.scalar** provides two types of classes for float implementations. It provides specific classes for the standard provided units such as `Area`, `Temperature`, `Duration`, and `Length` and a generic `FloatSIScalar` class that can be instantiated with any unit. Here are seven ways to create a `FloatLength` of 100 km (fl1 .. fl7 all get set to the same value and the equals method would confirm that the objects are equal):

```java
FloatLength fl1 = new FloatLength(100.0f, LengthUnit.KILOMETER);
FloatLength fl2 = FloatLength.valueOf("100.0 km");
FloatLength fl3 = FloatLength.of(100.0f, "km");
FloatLength fl4 = FloatSIScalar.of(100000.0f, "m").asLength(LengthUnit.KILOMETER);
FloatLength fl5 = FloatSIScalar.valueOf("100000.0 m").asLength(LengthUnit.KILOMETER);
FloatLength fl6 = FloatSIScalar.valueOf("100000.0 m").asLength();
fl6.setDisplayUnit(LengthUnit.KILOMETER);
FloatLength fl7 = FloatSIScalar.valueOf("100000.0 m").as(LengthUnit.KILOMETER);
```

Time/Duration, Position/Length, AbsoluteTemperature/Temperature, and Direction/Angle have both an absolute and a relative form. All other scalars are always relative. Each class has a multitude of mathematical operations that can be applied on the scalar, such as goniometric functions, logarithms, power functions, etc. Furthermore, many multiplications and divisions are implemented in a strongly typed way that is checked by the compiler. If an `ElectricalPotential` scalar (e.g. in Volts) is divided by an `ElectricalCurrent` scalar (e.g. in Amperes), the result can only be assigned to an `ElectricalResistance` unit (e.g., in Ohms). Please note that the scalars are stored as a public float value in a field named `si`, always expressed in the base unit (SI if possible). In addition, the unit in which the scalar was created is stored (not necessarily the SI unit). In total the scalar consists therefore of the standard fields of an Object plus 32 bits for the si float and 64 bits of the pointer to its unit.
     
* **org.djunits.value.vdouble.scalar.base** contains the classes for double precision scalars in all supported units.

* **org.djunits.value.vfloat.vector** contains Dense and Sparse implementations of vectors. In addition, the vectors can be absolute or relative, depending on the unit that is used. For now, only the generic DoubleVector is supported, which can be instantiated with any unit. An example is:

```java
FloatAbsoluteTemperatureVector temperatureVector 
    = FloatVector.instantiate(new float[]{273.15, 290.4, 280.5, 279.1},
      AbsoluteTemperatureUnit.KELVIN, StorageType.DENSE);
```

Note that the vector is just defined as an absolute vector, independent of its (sparse) implementation.
     
* **org.djunits.value.vfloat.vector.base** contains the classes for vectors of float values in all supported units.

* **org.djunits.value.vfloat.vector.data** common code for storing the SI values for float vectors.

   