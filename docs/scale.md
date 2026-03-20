# Scale

## Introduction

A `Scale` takes care of converting values as displayed for the user to and from the internal SI values. The method that converts a value in a given scale to the corresponding value in the base scale is called `toBaseValue(value)`. The method that converts a value expressed in the SI or base scale to the given scale is called `fromBaseValue(value)`. Both methods return a `double` representation of the value in the target scale.

* A `LinearScale` is used for most units. A linear scale is a scale that is both additive and multiplicative. I.e. `toBaseValue(a * b + c) == a * toBaseValue(b) + toBaseValue(c)`.
* The `IdentityScale` is a special case of a LinearScale where `toBaseValue(a) == a`.
* The `GradeScale` is used to express grades and angles in percentages.

The `Scale` interface prescribes that a scale must implement these three methods:

* `double toBaseValue(double value)` which takes a value in the user's unit and returns the corresponding base (usually SI) value.
* `double fromBaseValue(double value)` which takes a value in the base (usually SI) unit and returns the corresponding value in the user's unit.
* `boolean isBaseScale()` which should return true for scales that need no conversion to the base value, e.g., the `IdentityScale`.

Optional methods to implement are:

* `String toString()`
* `hashCode()`
* `equals(Object object)`


## LinearScale

The `LinearScale` is the most used one. It just contains a multiplication factor to transform a value to its corresponding value on the base or SI scale. So, a `Length.Unit` in miles is defined with a scale factor to the SI-unit of `Length`: meter. 

In the `Length.Unit` class, this is done as follows:

```java
public static final double CONST_FT = 0.3048;
public static final double CONST_MI = 5280.0 * CONST_FT;
public static final Length.Unit mi =
   new Length.Unit("mi", "mi", "mile", new LinearScale(CONST_MI), UnitSystem.IMPERIAL);
```

The above example shows that the mile unit is defined as being linearly dependent to the default (meter) unit with a factor of 5280 ft, and the foot is defined as 0.3048 m.

