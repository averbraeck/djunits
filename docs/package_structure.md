# Package Structure

DJUNITS is divided into a number of packages:

* **org.djunits.formatter** Formatting methods that can help in formatting quantities. This includes the `EngineeringFormatter` that renders floating point values in a prescribed number of characters (minimum 10), transiting to mantissa + exponent notation when normal notation would cause loss of precision. The exponent in mantissa + exponent notation is always a multiple of 3 to ease interpretation using SI prefixes (nano, micro, Mega, etc.).

* **org.djunits.quantity** A (physical) quantity is a property of a material or system that can be quantified by measurement. A physical quantity can be expressed as the combination of a value (magnitude) and a unit. For example, the physical quantity `Energy` can be quantified as `x joule` where x is the value and joule is the unit. Several dozen quanties have been predefined with their operations. Each quantity class has an inner class for the definition of the corresponding unit: the quantity class `Force` has a corresponding unit class: `Force.Unit`.

* **org.djunits.quantity.def** The abstract definitions of `Quantity` and `AbsoluteQuantity`. The class `AbstractReference` provides the reference point for an `AbsoluteQuantity`.
     
* **org.djunits.unit** Here, the unit definitions are stored. The most important classes and interfaces are `UnitInterface` to define a unit, and `AbstractUnit` as a generic superclass for all units. The `Unitless` unit for the `Dimensionless` quantity is also stored here.

* **org.djunits.unit.scale** defines the scales that can be used for a unit. Scales perform conversions of externally used values expressed in various units to and from the internally used si values. Examples are the `LinearScale` that is used in most units, and the `GradeScale` that is used in percentage angles. The `IdentityScale` is used for SI-units. The `Scale` implements two important methods: a method to convert values to the standard unit of that type (e.g. from degree Fahrenheit to Kelvin) and a method to convert values from the standard unit of that type to the given type (e.g. from Kelvin to degree Fahrenheit).

* **org.djunits.si** handles SI prefixes (milli, micro, ...,  kilo, Mega, ...) and manipulation of SI coefficients to derive the resulting unit when quantities of various types are multiplied or divided.

* **org.djunits.system** defines the different unit systems such as SI (meter, kilogram, second, mol, Ampere, candela, and Kelvin), SI Derived (e.g., degree Celcius, Joule, Ohm or kWh), SI Accepted (e.g, minute, hour or kilometer), and Imperial (e.g., degree Fahrenheit, mile, or pound). Additionally, some less used unit systems are defined such as AU (Atomic Units), MTS (meter-tonne-second), and CGS (centimeter-gram-second).

* **org.djunits.util** provides a number of classes to carry out calculations on arrays of values (`ArrayMath`), on matrices (`MatrixMath`), and a number of helper functions for, e.g., min, max, and sum of an unlimited number of values (`Math2`). A file with physical constants (`Constants`) is also found here.

* **org.djunits.value** provides the base interfaces for the value implementations. Example interfaces are `Value`, `Additive`, and `Scalable` that each are used to ensure implementations of methods in the quantity, vector, and matrix classes. 

* **org.djunits.vecmat** stores the classes for vectors, matrices and quantity tables.

* **org.djunits.vecmat.def** contains the interfaces and abstract class definitions for vectors, matrices and quantity tables. Specific abstract classes for square matrices and square dense matrices are available.

* **org.djunits.vecmat.operations** contains the operation interfaces for vectors, matrices and quantity tables. Examples are `Hadamard` for element-wise operations, `Normed` to calculate different vector norms, and `VectorTransposable` to transpose column vectors into row vectors and vice versa.

* **org.djunits.vecmat.storage** contains the interfaces and implementations of dense and sparse storage for larger vectors, matrices and quantity tables. It is possible to store data in double precision (`double`) or single precision (`float`).

* **org.djunits.vecmat.d1** contains the 1-dimensional vectors and matrices `Vector1` and `Matrix1x1`.

* **org.djunits.vecmat.d2** contains the 2-dimensional vectors and matrices `Vector2.Row`, `Vector2.Col` and the square matrix `Matrix2x2`.

* **org.djunits.vecmat.d3** contains the 3-dimensional vectors and matrices `Vector3.Row`, `Vector3.Col` and the square matrix `Matrix3x3`.

* **org.djunits.vecmat.dn** contains the n-dimensional vectors and matrices `VectorN.Row`, `VectorN.Col` and the square matrix `MatrixNxN`.

* **org.djunits.vecmat.dnxm** contains the (n x m)-dimensional matrices `MatrixNxM`.

* **org.djunits.vecmat.table** contains the (n x m)-dimensional quantity table `QuantityTable`.
