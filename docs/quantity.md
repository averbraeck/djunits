# (Relative) Quantity

A (physical) quantity is a property of a material or system that can be quantified by measurement. A physical quantity can be expressed as the combination of a value (magnitude) and a unit. For example, the physical **quantity** energy can be quantified as `x joule` where `x` is the **value** and `joule` is the **unit**.<sup>[1]</sup>

Every quantity in DJUNITS needs a unit. One quantity can be expressed using multiple units. Typically the unit is defined as an inner class of the quantity class. As a standard the name `BASE`, or `SI` is used for the default unit and it should be public, static and final.

A `Quantity` is a `Number` and can therefore be used in any piece of code where a `Number` is expected. When using the methods from `Number`, such as `doubleValue()`, the value is always returned in SI or BASE units. 

![](images/quantity.png)

Note that next to the `Quantity`, which is relative, an `AbsoluteQuantity` exists. Absolute quantities are defined with respect to to a reference point, whereas relative quantities do not have a reference point. An `Angle` of 10 degrees is not fixed in space, whereas a `Direction` of 10 degrees relative to the 'NORTH' reference does represent a fixed direction in space. The same holds for `Temperature` versus `TemperatureDifference`, `Position` versus `Length`, and `Time` versus `Duration`. For more information about absolute quantities, see the [Absolute Quantity](absolute_quantity.md) page. 


## Constructing a quantity

The constructor of a quantity like a `Duration` is straightforward: it takes a value and a unit, a value and a `String` representing a unit, or another duration:

```java
Duration dur1 = new Duration(10.0, Duration.Unit.s);
Duration dur2 = new Duration(10.0, "min");
Duration dur3 = new Duration(dur2);
```

Several static `of()` and `valueOf()` methods are available as well:

```java
var dur4 = Duration.ofSi(10.0); // results in a duration of 10 seconds
var dur5 = Duration.valueOf("20 min"); // results in a duration of 20 minutes
var dur6 = Duration.of(24.0, "h"); // results in a duration of 24 hours
```


## Requesting information of the quantity

Quantities are strongly typed, and many operations on quantities are possible. A couple of examples are (where `Q` denotes the quantity such as `Area`):

- `Unit getDisplayUnit()` to ask the current unit in which the quantity is displayed.
- `Q setDisplayUnit(newUnit)` to give the unit a new display unit, without changing its value. Values are _always_ stored using the SI or BASE unit.
- `double si()` or `si` to give the value of the quantity expressed in SI or BASE units.
- `double getInUnit()` to get the value expressed in the given display unit.
- `double getInUnit(targetUnit)` to get the value expressed in the given target unit.
- `doubleValue()`, `floatValue()`, `intValue()`, etc. are provided since `Quantity` extends `Number`.
- logical operators such as `lt`, `le`, `eq`, `ne`, to check whether one quantity is, e.g., bigger or smaller than another one.
- logical operators such as `lt0`, `le0`, `eq0`, `ne0` to check whether one quantity is, e.g., bigger or smaller than zero.
- `String toString()` with several variants for formatting the unit, so it can be expressed, e.g., as `kgm/s2` or `kg.m.s-2` or as a HTML string `kg.m.s<sup>-2</sup>`.
- `String getName()` gives the (localized) name of the quantity.
- `SIUNit siUnit()` returns the SI-unit of the quantity, such as kg&middot;m/s<sup>2</sup>. 


## Instantiating quantities

The constructor is the standard way to instantiate a quantity. The constructors are typically as follows, where `Q` denotes the quantity class`:

- `Q(double valueInUnit, Unit unit)` instantiates a new quantity with `valueInUnit` expressed in the given `unit`. Example: `new Area(10.0, Area.Unit.are)`.
- `Q(double valueInUnit, String unitString)` instantiates a new quantity with `valueInUnit`, where the unit string will be parsed into the unit, e.g., `new Area(80.0, "m2")`.
- `Q(Q value)` instantiates a new quantity by duplicating an existing quantity.
- `Q.ofSi(siVAlue)` instantiates a new quantity with the given SI or base value, and the default display unit. The display unit can be changed with the `setDisplayUnit(newUnit)` method.
- `Q.valueOf(String text)` parses the string with value and unit. The example is a quantity of the correct type, e.g. `Area.valueOf("10.0 are")`.
- `Q.of(double valueInUnit, String unitString)` instantiates a new quantity with `valueInUnit`, where the unit string will be parsed into the unit, e.g., `Area.of(80.0, "m2")`.

Two instance methods are also present, that are typically used internally to instantiate a new quantity from an existing one:

- `Area instantiateSi(siValue)` instantiates a new quantity with `si` as its SI or base value.
- `Area instantiate(valueInUnit, unit)` instantiates a new quantity with `valueInUnit` expressed in the given `unit`.


## Operations on the quantity

(Relative) quantities can always be added to and subtracted from other relative quantities of the same type, independent of their unit. This means that a `Length` quantity defined in miles can be added to a `Length` quantity defined in kilometers, but a `Length` quantity cannot be added to a `Speed` quantity. Relative quantities can also be scaled, negated, and the reciprocal of a quantity can be calculated. 

- `Q add(Q increment)` returns a new quantity where the SI value has been increased by the SI value from `increment`. The display unit of the resulting value is the same as the display unit of the quantity that called `add`.
- `Q subtract(Q decrement)` returns a quantity where the SI value from `decrement` is subtracted from the current quantity. The display unit of the resulting value is the same as the display unit of the quantity that called `subtract`.

Multiplication and division of quantities is also possible. A reciprocal function for the quantity (with a unit of one divided by the original unit) is provided as well. Some multiplication and division functions lead to a known quantity; `Length * Length -> Area`, `Length / Duration -> Speed`, `1 / Duration -> Frequency`. Others are not pre-programmed, and lead to an `SIQuantity` with an `SIUnit`. E.g., `1 / Area -> SIQuantity` with a unit `1/m2`.

- `SIQuantity multiply(Quantity quantity)` is the general multiplication of a quantity with another quantity.
- `SIQuantity divide(Quantity quantity)` is the general division of a quantity by another quantity.
- `SIQuantity product(Quantity quantity1, Quantity... quantities)` is a static method to multiply multiple quantitities with each other.
- `SIQuantity reciprocal()` returns a quantity with the reciprocal value (`1/si`) and the reciprocal unit.

Several functions can be applied to a quantity, yielding a new quantity with the same display unit as the caller.

- `Q abs()` returns a quantity with the absolute value of the SI value.
- `Q divideBy(double factor)` returns a new quantity with an SI-value of `si/factor`.
- `Q interpolate(Q zero, Q one, double ratio)` returns a value between quantity `zero` and quantity `one` where the SI value is `zero.si * (1.0 - ratio) + one * ratio`. So, when `ratio` is zero, the outcome will be the value of the `zero` quantity, and when `ratio` is one, the outcome will be the value of the `one` quantity.
- `Q max(Q q1, Q... quantities)` returns the maximum value (using the SI value) of the provided quantities.
- `Q mean(Q q1, Q... quantities)` returns the mean value (using the SI value) of the provided quantities.
- `Q min(Q q1, Q... quantities)` returns the minimum value (using the SI value) of the provided quantities.
- `Q negate()` returns a quantity with the negated value of the SI value (SI value multiplied by -1).
- `Q scaleBy(double factor)` scales the quantity by a dimensionless `factor`, based on the SI-value.
- `Q sum(Q q1, Q... quantities)` returns the summed value (using the SI value) of the provided quantities.


## Typical Quantity constants

The predefined quantities in the library have a standard set of constants for each quantity:

- `NaN` Quantity constant with SI value NaN.
- `NEG_MAXVALUE` Quantity constant with SI value -MAX_VALUE.
- `NEGATIVE_INFINITY` Quantity constant with SI value NEGATIVE_INFINITY.
- `ONE` Quantity constant with SI value 1.0.
- `POS_MAXVALUE` Quantity constant with SI value MAX_VALUE.
- `POSITIVE_INFINITY` Quantity constant with SI value POSITIVE_INFINITY.
- `ZERO` Quantity constant with SI value 0.0.

Especially the values `ZERO` and `ONE` are often used, e.g., `Speed.ZERO`, or `LinearObjectDensity.ONE`. 


## Formatting and printing quantities

The constructor is the standard way to instantiate a quantity. The constructors are typically as follows, where `Q` denotes the quantity class`:

- `String Quantity.format(double d)` formats a double value according to the current locale.
- `String Quantity.format(double d, String format)` formats a double value according to the current locale, and using the provided format string.
- `String toString()` returns the localized string representation of a quantity, using its current display unit. 
- `String toString(Unit displayUnit)` returns the localized string representation of a quantity, using the provided unit. 


## Examples of Quantity operations

Some examples:

```java
Duration dur = Duration.of(0.5, "s");
Frequency freq = dur.reciprocal(); // 2 Hz
var dur4 = dur.scaleBy(4.0); // 2 s
var dur5 = dur4.negate(); // -2 s
var dur6 = Duration.of(2.0, "min").add(dur.scaleBy(60.0)); // 2.5 min
```

Quantities can also be multiplied with and divided by quantities of other types. The correct SI-dimension for the resulting quantity is calculated, and for known multiplications and divisions, a quantity of the correct type is returned:

```java
Length length = Length.of(80.0, "km");
Duration hour = Duration.of(1.0, "h");
Speed speed = length.divide(hour);
```

The value of `speed` above will be 80 km/h. It will also be strongly typed as a `Speed` quantity. Because the calculation takes place in SI units, the resulting speed value will be expressed in the SI default unit for speed: m/s. If we want it expressed as km/h, we use:

```java
Speed speed = length.divide(hour).setDisplayUnit(Speed.Unit.km/h);
```

In case the multiplication or division results in a quantity that is not known, an `SIQuantity` with a unit `SIUnit` will be returned. See the following example:

```java
Duration s = Duration.of(20.0, "s");
var s2 = s.multiply(s);
```

Here, `s2` will have an internal si-value of `400`, and a unit of `s^2`. The type will be `SIQuantity`. Suppose, we divide a length by this variable to obtain an acceleration. In that case the compiler does not know that the result will be an `Acceleration` quantity. We can transform the result to an `Acceleration` with the `as` method. This method will give an error if the SI-unit is not of the correct type:

```java
var l = Length.of(10.0, "m");
var a1 = l.divide(s2); // SIQuantity
System.out.println("a1 = " + a1);
System.out.println("a1.class = " + a1.getClass().getSimpleName());
var a2 = a1.as(Acceleration.Unit.m_s2);
System.out.println("a2 = " + a2);
System.out.println("a2.class = " + a2.getClass().getSimpleName());
var a3 = s2.as(Acceleration.Unit.m_s2);
```

This results in:

```
a1 = 0.02500000 m/s2
a1.class = SIQuantity
a2 = 0.02500000 m/s2
a2.class = Acceleration
Exception in thread "main" java.lang.IllegalArgumentException: 
  Quantity.as (804): Quantity.as(m/s2) called, but units do not match: s2 <> m/s2
```


<hr>
<sup>[1]</sup>. See [https://en.wikipedia.org/wiki/Physical_quantity](https://en.wikipedia.org/wiki/Physical_quantity)
