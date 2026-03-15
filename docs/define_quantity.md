# Defining a new Quantity type

Scalar quantity types are the foundation of DJUNITS. A quantity wraps a value (double precision floating point) and a corresponding unit. DJUNTIS contains class files for many different quantity types, but users can create not-builtin quantity types using the `SIQuantity` class. The instructions below show how to create a new quantity: the [Jerk](https://en.wikipedia.org/wiki/Jerk_(physics)). Jerk expresses change in acceleration per time unit. The Jerk has m/s<sup>3</sup> as the SI unit.

To create a Jerk quantity that can be constructed from a value in, e.g. ft/s<sup>3</sup>, a `Jerk` class and `Jerk.Unit` class need to be written.


## Building a new Quantity class

The next paragraphs show how to build a Quantity type called `Jerk`

The `Quantity` abstract class can easily be extended for creating new scalar quantity classes. The minimal that is needed os the following definition:

```java
public class Jerk extends Quantity<Jerk, Jerk.Unit>
{
    public Jerk(final double value, final Jerk.Unit unit)
    {
        super(value, unit);
    }

    @Override
    public Jerk instantiate(final double siValue)
    {
        return new Jerk(siValue, Jerk.Unit.SI);
    }
}
```

Each scalar class in DJUNITS should have a constructor. The default one takes a double argument and a display unit. One method that needs to be implemented is the `instantiate` method, which is internally used in, for instance, the `add()` method to create a new instance of `Jerk` after the addition. All standard methods for a Quantity are now available to the `Jerk`, such as addition and subtraction, multiplication and division, scaling and printing, as well as comparing to other `Jerk` instances.

A method that *can* be implemented is the `ofSi()` static method as a quick generator with the default unit.

```java
public static Jerk ofSi(final double valueSi)
{
    return new Jerk(valueSi, JerkUnit.SI);
}
```


## Building the new Unit for the Quantity

The next part that needs to be created is a class for the Jerk unit. It can be defined as a separate class (`JerkUnit`) or as a static inner class within the `Jerk` class (`Jerk.Unit`). There is no difference in usage except for the 'period' between `Jerk` and `Unit`. Because quantity and its unit are so tightly intertwined, DJUNITS typically defines the units inside the corresponding quantity class.

Every unit extends `AbstractUnit` (or at least implements `UnitInterface`) with the defined unit as its generic; this ensures that the generic unit class will do proper housekeeping, including for user-defined units. Many units have a natural zero value and linear scales to convert from and to various non-SI units. These units extend the `AbstractUnit` class that provides a number of constructors with an easy-to-use factor to create a linear scale with respect to the standard (SI) unit. The class definition of `Jerk.Unit`, the rate of change of acceleration (meter per second<sup>3</sup>) is therefore:

```java
public static class Unit extends AbstractUnit<Jerk.Unit, Jerk>
```

Usually, the unit(s) on which a unit is based are stored as part of the unit. In this case, a length unit and a duration unit. Furthermore, several standard units are defined, among which the SI constant, if possible:

```java
/** The SI unit for jerk is m/s^3. */
public static final Jerk.Unit SI = new Jerk.Unit("m/s3", 
    "meter per second cubed", IdentityScale.SCALE, UnitSystem.SI_DERIVED);

/** define some additional unit instances next to SI. */
public static final Jerk.Unit m_s3 = SI;
public static final Jerk.Unit cm_s3 = SI.deriveUnit("cm/s3", 
    "centimeter per second cubed", 0.01, UnitSystem.SI_DERIVED);
public static final Jerk.Unit mm_s3 = SI.deriveUnit("mm/s3", 
    "millimeter per second cubed", 0.001, UnitSystem.SI_DERIVED);
public static final Jerk.Unit ft_s3 = SI.deriveUnit("ft/s3", 
    "foot per second cubed", Length.Unit.CONST_FT, UnitSystem.IMPERIAL);
public static final Jerk.Unit in_s3 = SI.deriveUnit("in/s3", 
    "inch per second cubed", Length.Unit.CONST_IN, UnitSystem.IMPERIAL);
```

The naming for the definitions and the String representations follows the guidelines in the <a href="../unit">unit</a> section of this manual.
 
Usually, the static constructors derive the unit in a linear way from the SI unit using the `deriveUnit(...)` method. Some of the conversion factors are taken from constants in the `Length.Unit` class, such as the inch and foot. If we would change the `per second` to, e.g., `per minute`, we should not forget to raise the denominator scale factor to the power 3, `/ (60.0 * 60.0 * 60.0)`. 

`Jerk.Unit` needs two constructors, and several methods to operate:

```java
public Unit(final String textualAbbreviation, final String name, 
    final Scale scale, final UnitSystem unitSystem)
{
    super(textualAbbreviation, name, scale, unitSystem);
}

public Unit(final String textualAbbreviation, final String displayAbbreviation, 
    final String name, final Scale scale, final UnitSystem unitSystem)
{
    super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
}

@Override
public SIUnit siUnit()
{
    return SIUnit.of("m/s3");
}

@Override
public Jerk.Unit getBaseUnit()
{
    return SI;
}

@Override
public Jerk ofSi(final double si)
{
    return new Jerk(si, SI);
}

@Override
public Jerk.Unit deriveUnit(final String textualAbbreviation, 
    final String displayAbbreviation, final String name,
    final double scaleFactor, final UnitSystem unitSystem)
{
    return new Jerk.Unit(textualAbbreviation, displayAbbreviation, name, 
        new LinearScale(scaleFactor), unitSystem);
}
```

The difference between the two constructors is whether there is a separate textual abbreviation (e.g. using `mu` for &mu; in micro) from the display abbreviation. The `siUnit()` method indicates the unit using a combination of the base SI unit symbols: rad, sr, kg, m, s, A, K, mol, cd with their powers. In this case, the `SIUnit` is defined from the String `m/s3`. The `getBaseUnit()` method returns the unit belonging to the SI or BASE value. The `ofSi(si)` method allows a unit to instantiate its corresponding quantity. This is heavily used in underlying methods of the `Quantity` and `AbstractUnit`, as well as in vector and matrix operations. Finally, the `deriveUnit` method has to be defined to allow a linearly scaled unit to be defined in a easy way.


## Multiplication and division methods

Often, extra methods are implemented for common multiplications and divisions involving the just defined type and other types. E.g., when we multiply the `Jerk` by a `Duration`, we get an `Acceleration`. If we divide it by an `Acceleration`, we get a `Frequency` (m/s<sup>3</sup> / m/s<sup>2</sup> = 1/s). Such additional methods can be defined as follows:

```java
public Acceleration multiply(final Duration v)
{
    return Acceleration.ofSi(this.si() * v.si());
}

public Frequency divide(final Acceleration v)
{
    return Frequency.ofSi(this.si() / v.si());
}
```

Due to the fact that all values are internally stored in standard (if possible SI) units, no scale factors are needed. This reduces the chances for errors considerably and is the main reason for using the SI system in science and engineering as much as possible. With these additional methods defined, the Java compiler will know the result type of the common multiplications and divisions and catch programmer errors at compile time.


## Example usage of the new quantity

The quantity and unit that were just defined can be used in any code together with other quantities:

```java
Jerk jerk1 = new Jerk(1.2, Jerk.Unit.SI);
System.out.println("jerk1 = Jerk(1.2, Jerk.Unit.SI)    : " + jerk1);
Jerk jerk2 = jerk1.scaleBy(2.0);
System.out.println("jerk2 = jerk1.scaleBy(2.0)         : " + jerk2);
Jerk jerk3 = new Jerk(4.0, Jerk.Unit.in_s3);
System.out.println("jerk3 = Jerk(4.0, Jerk.Unit.in_s3  : " + jerk3);
System.out.println("jerk3 expressed in Jerk.Unit.SI    : " 
    + jerk3.toString(Jerk.Unit.SI));
System.out.println("jerk3 expressed in Jerk.Unit.ft_s3 : " 
    + jerk3.toString(Jerk.Unit.ft_s3));
```

This will print:

```
jerk1 = Jerk(1.2, Jerk.Unit.SI)    : 1.20000000 m/s3
jerk2 = jerk1.scaleBy(2.0)         : 2.40000000 m/s3
jerk3 = Jerk(4.0, Jerk.Unit.in_s3  : 4.00000000 in/s3
jerk3 expressed in Jerk.Unit.SI    : 0.10160000 m/s3
jerk3 expressed in Jerk.Unit.ft_s3 : 0.33333333 ft/s3
```

The usage of the newly defined quantity also includes vectors, matrices and quantity tables, without adding one extra line of code. The example below uses the `Jerk` in a 2x2 matrix, that is multiplied in an element-wise way by a `Duration` to get an `Acceleration` matrix if size 2x2:

```java
double[][] jmd = new double[][] {{1, 2}, {3, 4}};
Matrix2x2<Jerk, Jerk.Unit> jerkMatrix2 = Matrix2x2.of(jmd, Jerk.Unit.in_s3);
System.out.println("\nJerk matrix:\n" + jerkMatrix2);

Duration d = Duration.of(3.0, "s");
Matrix2x2<Acceleration, Acceleration.Unit> mAcc = 
    jerkMatrix2.multiplyElements(d).as(Acceleration.Unit.ft_s2);
System.out.println("Acceleration matrix:\n" + mAcc);
```

This will print:

```
Jerk matrix:
[1.00000000, 2.00000000
 3.00000000, 4.00000000] in/s3
Acceleration matrix:
[0.25000000, 0.50000000
 0.75000000, 1.00000000] ft/s2
```
