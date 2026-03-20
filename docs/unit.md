# Unit

## Introduction

DJUNITS defines a sizable number of instantiated units for each quantity. For each quantity, the most common SI, SI-derived, SI-accepted and imperial units are included. A few MTS and CGS units are included as well. 

Units are typically defined as an inner class of the corresponding Quantity. Quantity types and Unit types have a 1:1 relationship. This means that additional unit _instances_ can be defined, but it is impossible to add a new Unit _type_ to an existing quantity. The 1:1 relationship also shows in the definition of the generics of a `UnitInterface` and `Quantity`:

```java
public interface UnitInterface<U extends UnitInterface<U, Q>, Q extends Quantity<Q, U>>
public abstract class Quantity<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
```

This means that the unit type is aware of the quantity type it belongs to, and the quantity type is aware of the unit type it should use. When a quantity and unit are defined, the generics define the relationship, and the user is not concerned with generics anymore:

```java
public class Power extends Quantity<Power, Power.Unit>
public static class Unit extends AbstractUnit<Power.Unit, Power>
```

In this case the `Power.Unit` class is defined as an inner class within the `Power` class.


## Naming conventions

The String representation of units follows a strict naming convention:

- Abbreviations are defined using the common usage of upper case and lower case; `K` for kelvin, `C` for coulomb, `Pa` for Pascal, and `Sv` for Sievert.
- Upper and lower case are important. There is a `Length.Unit` `nm` (nanometer), and `NM` (Nautical Mile).
- SI-prefixes (from quecto to Quetta) are used for most of the SI-derived units, so there is a `hm`, `μK`, `ns`, `TW`, and `GeV`. 
- When the abbreviation is built from SI-units, the order is: rad, sr, kg, m, s, A, K, mol, cd; so `Power` is expressed in `kgm2/s3`.
- When there are SI-units with powers, no additional signs such as `^` for the subscripts are used; `Speed` is expressed in `m/s2`.
- When there are SI-units with positive and negative powers, a slash is used for division; `ElectricPotential` is expressed in `kgm2/s3A`.
- Maximally one slash is allowed in the string representation, where all powers after the slash are negated.
- Periods in the unit abbreviations are used where confusion is possible (but may also be used where confusion is not possible). Example: for `Power` the `sn.m/s` (sthene-meter per second) has a period to help realize it is not a 'second-something'. Similarly, the foot-pound force per hour is expressed as `ft.lbf/h` to help readability.
- If there are multiple definitions for the same unit, a source is appended without a space and between brackets. British Thermal Unit is an example; there is an ISO definition and an International Table definition. The units become `BTU(ISO)` and `BTU(IT)`. Similarly for calories `cal(IT)` and metric horsepower `hp(M)`. 

The static definition of variable names for the units in the unit class also follows a strict naming convention:

- The String representation is used as the starting point. All capitals and smaller case letters stay intact.
- The &mu; sign for micro is replaced by `mu`. Microkelvin has `μK` as String representation, and is defined in Java as `muK`.
- The `.` `/`, `(`, `)`, and all other non-alphabetic and non-number symbols are replaced by an underscore: `km/h` for speed becomes `km_h` as the static constant name. The `Power` unit foot-pound force per hour (`ft.lbf/h`) is defined in Java as `ft_lbf_h`.
- An underscore at the end of the variable is removed. The British Thermal Unit `BTU(ISO)` and `BTU(IT)` become `BTU_ISO` and `BTU_IT` as Java names; metric horsepower becomes `hp_M`. 
- If there are two consecutive underscores, one is removed. 
- Greek letters are written with their name. The &Omega; for ohm becomes `ohm`. Micro-ohm &mu;&Omega; becomes `muohm` for the variable name.
- The degree symbol is transformed to `deg` in the temperature unit definition. &deg;C becomes `degC` in Java.
- The angstrom (&Aring;) is denoted with an `A` in Java. 

Note that the static names for the units do **not** follow Java conventions.


## Adding a unit instance to an existing unit

DJUNITS already defines quite a number of units that are ready to use, but this set can never be complete. Therefore, additional units can easily be instantiated and added for user code.

Suppose, that a user would like to add the [Furlong](https://en.wikipedia.org/wiki/Furlong) (an old imperial length unit that is one eighth of a mile, or 660 feet), the [Fortnight](https://en.wikipedia.org/wiki/Fortnight) (a duration unit of 14 days), and a speed unit that indicates speed in Furlongs per Fortnight. Suppose that the user wants to make these three units available as public static constants in a utility class. The code to do so looks as follows:

```java
public static final Length.Unit fr = 
    Length.Unit.ft.deriveUnit("fr", "Furlong", 660.0, UnitSystem.OTHER);
public static final Duration.Unit fn = 
    Duration.Unit.day.deriveUnit("fn", "Fortnight", 14.0, UnitSystem.OTHER);
public static final Speed.Unit fr_fn = 
    Speed.Unit.SI.deriveUnit("fr/fn", "Furlongs per Fortnight",
    fr.getScale().toBaseValue(1.0) / fn.getScale().toBaseValue(1.0), UnitSystem.OTHER);
```

The first two definitions use the `deriveUnit` method that defines a unit in terms of an already existing unit using a linear factor. It is no problem that the Furlong is defined with respect to a foot (instead of the SI unit for length; the meter). The (combined) factor to the SI unit will be calculated once (at the time of construction of the `fr` unit) by the `deriveUnit` method onto the `ft` unit. The `fr_fn` speed unit is also constructed using the `deriveUnit` method of `Speed.Unit.SI`. In this case the factor is computed from the scales of the just created length and duration units. Again, the factor to map Furlongs per Fortnight to and from the SI unit meters per second, will be automatically calculated once. These units can now be used in any piece of code, e.g.:

```java
Length oneThousandFurlong = new Length(1000.0, fr);
System.out.println(oneThousandFurlong);
Duration twoFortNight = new Duration(2.0, fn);
System.out.println(twoFortNight);
Speed speed = oneThousandFurlong.divide(twoFortNight);
System.out.println(speed + "(should be around 0.083152 m/s)");
System.out.println(speed.toString(fr_fn));

```

The program will print the following output:

```
1000.00000 fr
2.00000000 fn
0.08315476 m/s(should be around 0.083152 m/s)
500.000000 fr/fn
```


## Defining a completely new Unit

Of course it is also possible to define a completely new unit type from scratch. Since a unit can only be defined in conjunction with a quantity, defining a new unit will be discussed in the [Define quantity section](define_quantity).

