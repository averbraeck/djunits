# Defining a new Quantity type

Scalar quantity types are the foundation of DJUNITS. A quantity wraps a value (double precision floating point) and a corresponding unit. DJUNTIS contains class files for many different quantity types, but users can create not-builtin quantity types using the `SIQuantity` class. Assume we have defined a JerkUnit with m/s<sup>3</sup> as the SI unit (see preceding page). We can then use it in a `Double` or `Float` `Scalar`, `Vector`, or `Matrix`:

```java
SIScalar jerkScalar = new SIScalar(2.0, SIUnit.of("m/s3"));
SIVector jerkVector = new SIVector(new DoubleVectorDataDense(new double[] {1.0, 2.0, 3.0}), SIUnit.of("ms-3"));
SIScalar jerk2 = jerkVector.get(1);
```

But it would be much nicer if we can define a `Scalar` called `Jerk` and a `Vector` called `JerkVector` that would allow us to code this as follows:

```java
Jerk jerk1 = new Jerk(1.2, JerkUnit.SI);
Jerk jerk2 = jerk1.times(2.0);
Jerk jerk3 = new Jerk(4.0, JerkUnit.IN_PER_S3);
double[] sv = new double[] {1, 2, 3, 4, 5}; 
JerkVector jerkVector = 
        new JerkVector(sv, JerkUnit.SI, StorageType.DENSE);
```

To create a jerk scalar that can be constructed from a value in, e.g. ft/s<sup>3</sup>, a JerkScalar class needs to be written.

## Building a new Quantity class


The next paragraphs show how to build a new Relative Scalar type. Building a new Scalar type that has an absolute and a relative subtype is unlikely to ever be required; we believe there are not other units that can be absolute, par the four that are already implemented in DJUNITS.

### Extending a Relative ScalarType from the Abstract Scalar Template

Several Abstract classes are available that simplify creating new `Scalar`, `Vector`, and `Matrix` classes. For the `Scalar` class, these are `DoubleScalarAbs` and `DoubleScalarRel`. The `DoubleScalarRel` class takes two generic arguments: the unit, and the name of the class itself. The 2nd parameter might seem strange, as the definition looks to be self-referential. The way it is used is that in the methods of the Abstract class, the generics argument is needed to specify the return type and argument type for various methods that are implemented in the abstract super class. So the first line of the new Jerk scalar class is:

```java
public class Jerk extends Quantity<Jerk, JerkUnit>
{
    public Jerk(final double value, final JerkUnit unit)
    {
        super(value, unit);
    }

    @Override
    public Jerk instantiate(final double siValue)
    {
        return new Jerk(siValue, JerkUnit.SI);
    }
}
```

Each scalar class file in DJUNITS should have a constructor. The default one takes a double argument and a display unit. One method that needs to be implemented is the `instantiate` method, which is internally used in, for instance, the `add()` method to create a new instance of `Jerk` after the addition.

A method that *can* be implemented is the `ofSi()` static method as a quick generator with the default unit.

```java
public static Jerk ofSi(final double valueSi)
{
    return new Jerk(valueSi, JerkUnit.SI);
}
```

With this minimum amount of code (two constructors, and the instantiateRel method), the Jerk scalar is ready for use in code like:

```java
Jerk jerk1 = new Jerk(1.2, JerkUnit.SI);
Jerk jerk2 = jerk1.scaleBy(2.0);
Jerk jerk3 = new Jerk(4.0, JerkUnit.in_s3);
Jerk jerk4 = Jerk.ofSi(5.0);
```

## Building the new Unit for the Quantity

The instructions below show how to create a new unit: the [Jerk](https://en.wikipedia.org/wiki/Jerk_(physics)). Jerk expresses change in acceleration per time unit.

Every unit extends `Unit` with the defined unit as its generic; this ensures that the generic unit class will do proper housekeeping, including for user-defined units. Many units have a natural zero value and linear scales to convert from and to various non-SI units. These units extend the abstract `Unit` class that provides a number of constructors with an easy-to-use factor to create a linear scale with respect to the standard (SI) unit. Jerk should be a relative unit. The header of the user-defined unit for jerk, the rate of change of acceleration (meter per second<sup>3</sup>) is therefore:

```java
public class JerkUnit extends Unit<JerkUnit>
```

Usually, the unit(s) on which a unit is based are stored as part of the unit. In this case, a length unit and a duration unit. Furthermore, several standard units are defined, among which the SI constant, if possible:

```java
/** The base quantity, with "m/s3" as the SI signature. */
public static final Quantity<JerkUnit> BASE = new Quantity<>("Jerk", "m/s3");

/** The SI unit for jerk is m/s^3. */
public static final JerkUnit SI =
        new JerkUnit().build(new Unit.Builder<JerkUnit>()
                 .setQuantity(BASE)
                 .setId("m/s3")
                 .setName("meter per second cubed")
                 .setUnitSystem(UnitSystem.SI_DERIVED)
                 .setSiPrefixes(SIPrefixes.NONE, 1.0)
                 .setScale(IdentityScale.SCALE));

/** Define some additional units for jerk. */
public static final JerkUnit M_PER_S3 = SI;
public static final JerkUnit CM_PER_S3 = 
        SI.deriveLinear(factorLD("cm", "s"), "cm/s3", "centimeter per second cubed");
public static final JerkUnit MM_PER_S3 = 
        SI.deriveLinear(factorLD("mm", "s"), "mm/s3", "millimeter per second cubed");
public static final JerkUnit FT_PER_S3 = 
        SI.deriveLinear(factorLD("ft", "s"), "ft/s3", "foot per second cubed");
public static final JerkUnit IN_PER_S3 = 
        SI.deriveLinear(factorLD("in", "s"), "in/s3", "inch per second cubed");
```

Many of the static constructors of the standard units derive the unit in a linear way from the SI unit using the `deriveLinear(...)` method. The linear conversion factor that has to be used is calculated by the `private static double FactorLD(length, duration)` method that is shown below:

```java
private static double factorLD(final String length, final String duration)
{
    double l = LengthUnit.BASE.of(length).getScale().toStandardUnit(1.0);
    double d = DurationUnit.BASE.of(duration).getScale().toStandardUnit(1.0);
    return l / (d * d * d);
}
```

No constructors are needed, nor anything else to start using the JerkUnit defined above. The arguments of `factorLD` are Strings. The `LD` part of the name hints that the first argument should be a Length and the second a Duration. Accidentally swapping the arguments would be a mistake that cannot be caught at compile time, but would be caught at runtime when the class is initialized and the code `LengthUnit.BASE.of(length)` fails to find a length unit named `s`.

Although the JerkUnit can now be used, life for the programmer becomes much easier when classes for JerkScalar, JerkVector and JerkMatrix are created. This is explained in the next pages.


### Extra methods to implement

Often, extra methods are implemented for common multiplications and divisions involving the just defined type and other types. E.g., when we multiply the `Jerk` by a (Relative) `Duration`, we get an `Acceleration`. If we divide it by an `Acceleration`, we get a `Frequency` (m/s<sup>3</sup> / m/s<sup>2</sup> = 1/s). Such additional methods can be defined as follows:

```java
public final Acceleration multiplyBy(final Duration v)
{
    return Acceleration.ofSI(this.si * v.si);
}

public final Frequency divideBy(final Acceleration v)
{
    return Frequency.ofSI(this.si / v.si);
}
```

Due to the fact that all values are internally stored in standard (if possible SI) units, no scale factors are needed. This reduces the chances for errors considerably and is the main reason for using the SI system in science and engineering as much as possible. With these additional methods defined, the java compiler will know the result type of the common multiplications and divitions and catch programmer errors at compile time.

Another method that is often implemented is a static interpolate method, to interpolate between two `Jerk` scalars with a certain ratio (if the ratio is less than zero, or greater than one, this method performs linear extrapolation):

```java
public static Jerk interpolate(final Jerk zero, 
    final Jerk one, final double ratio)
{
    return new Jerk(zero.getInUnit() * (1 - ratio) 
            + one.getInUnit(zero.getUnit()) * ratio, zero.getUnit());
}
```

The above interpolate method ensures that the result uses the unit of the first argument. When the first argument has been defined in \[ft/s3\], the result will also use that unit (when printed). The unit for the result is obtained with the code zero.getUnit() and that unit gets recorded in the result. When the result is printed it will be expressed in \[ft/s3\]. When the result is used as the first argument of another call to the interpolate method it will propagate to the result of that call. Note that although the math is _not_ done in SI units, regardless of the particular Jerk units of the arguments, the internally stored value is always in the standard (SI) unit, i.c., \[m/s3\].


