# SIQuantity and SIUnit

## SIQuantity

The `SIQuantity` is a generic quantity that uses a configurable unit called `SIUnit`. The `SIUnit` defines a combination of the 8 used SI dimensions with any positive or negative power. Suppose that a program uses molar entropy, which has a unit of kg&middot;m<sup>2</sup>&middot;s<sup>-2</sup>&middot;K<sup>-1</sup>&middot;mol<sup>-1</sup>. We can define a whole new quantity for molar entropy, but we can also use it in any calculation as:

```java
var entropyWater = new SIQuantity(70.0, SIUnit.of("kg.m2.s-2.K-1.mol-1"));
System.out.println(entropyWater);
```

This would print:

```
70.0000000 kgm2/s2Kmol
```

We could also have specified it as: `new SIQuantity(70.0, SIUnit.of("kgm2/s2Kmol"));`. The `SIUnit.of()` method takes any integer power of `rad`, `sr`, `kg`, `m`, `s`, `A`, `K`, `mol`, and `cd` in the numerator and denominator. In this case, the SI unit is equivalent to a J/K&middot;mol. Suppose we multiply our molar entropy with 1 K and 1 mol, we should get an Energy of 70 J:

```java
Energy energy = entropyWater.multiply(TemperatureDifference.of(1.0, "K"))
    .multiply(AmountOfSubstance.of(1.0, "mol")).as(Energy.Unit.J);
System.out.println(energy);
```

This prints:

```
70.0000000 J
```

Note that this can only be done when the SI units of 'energy' (kgm2/s2) match the SI units of the results of our calculation (also kgm2/s2). When not, e.g. using `entropyWater.as(Energy.Unit.J)`, results in:

```
Exception in thread "main" java.lang.IllegalArgumentException: 
org.djunits.quantity.def.Quantity.as (804): Quantity.as(J) called, 
but units do not match: kgm2/s2Kmol <> kgm2/s2
```

As shown, the `SIQuantity` combined with the `SIUnit` as its unit can store any combination of positive or negative powers of SI base units (plus rad and sr). In DJUNITS, any multiplication or division of scalar, vector or matrix quantities that has not been predefined to result in a 'known' quantity type is expresses as an `SIQuantity`. When we multiply a `Duration` with a `Duration`, we get a quantity expressed in square seconds. DJUNITS does not have a ready type for that, so it expresses it as an `SIQuantity` with an `SIUnit` of `s2`. If later, we divide a `Length` by this quantity, we can cast the result to a known `Acceleration` quantity with `.as(Acceleration.Unit.km_h)` to express the result as an acceleration in km/h. 


## SIUnit

Internally, the `SIUnit` stores the unit in an `int[9]`, which means that large exponents can be processed. This can be important in matrix calculations, where the exponents can (temporarily) become very high. Parsing order of the textual strings is very important: otherwise the `s` in `sr` can be mistaken for a `second`, and the `m` in `mol` can be mistaken for a `meter`. 

SIUnits can be instantiated using the dimensions in the following order:

1. **angle** dimension of the angle (rad)
2. **solidAngle** dimension of the solidAngle (sr)
3. **mass** dimension of the mass (kg)
4. **length** dimension of the length (m)
5. **time** dimension of the time (s)
6. **current** dimension of the current (A)
7. **temperature** dimension of the temperature (K)
8. **amountOfSubstance** dimension of the amount of substance (mol)
9. **luminousIntensity** dimension of the luminous intensity (cd)

Alternatively, an `int[9]` can be given to the constructor, or the `of(String)` method can be used. So, an acceleration in m/s2 could be defined as:

```java
SIUnit acc1 = new SIUnit(new int[] {0, 0, 0, 1, -2, 0, 0, 0, 0});
SIUnit acc2 = new SIUnit(0, 0, 0, 1, -2, 0, 0, 0, 0);
SIUnit acc3 = SIUnit.of("m/s2");
```

All three methods are equivalent and lead to the same unit. 

