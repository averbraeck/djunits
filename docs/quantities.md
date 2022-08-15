# Quantities

A (physical) quantity is a property of a material or system that can be quantified by measurement. A physical quantity can be expressed as the combination of a value (magnitude) and a unit. For example, the physical **quantity** energy can be quantified as _x_ joule where _x_ is the **value** and joule is the **unit**.<sup>1</sup>

Every unit in DJUNITS needs a quantity. One quantity can be referred to by multiple units. Typically the quantity is defined in the class of the unit. As a standard the name BASE, or SI is used for the quantity and it should be public, static and final.

```java
public static final Quantity<EnergyUnit> BASE = new Quantity<>("Energy", "kgm2/s2");
```

There are three constructors for a Quantity. All take a short descriptive identifier for the (physical) quantity as the first argument. The second argument identifies how base SI units must be combined to construct the unit of the new quantity. This is one of:

* `String siString`: A String expression that combines SI base units into the unit of the new quantity, e.g., `kgm2/s2`, or `kgm2s-2` (which are equivalent).
* `SIDimensions siDimensions`: An object that contains the coeffients for each of the SI base units; e.g., `new SIDimensions(0, 0, 1, 2, -2, 0, 0, 0, 0)` or `SIDimensions.of("kgm2/s2")`. The order of the parameters of that constructor with 7 parameters is: rad, sr, kg, m, s, A, K, mol, cd.
* `byte[] siSignature`: A byte array with the coefficients for each of the SI base units (same order as above); e.g., `new byte[] {0, 0, 1, 2, -2, 0, 0, 0, 0}`.

Each newly constructed `Quantity` is automatically registered in the `Quantities` class which has methods to find an existing `Quantity`.


## SIDimensions

DJUNITS treats radian (rad) and sterradian (sr) as first class base SI units in addition to the standard 7 (kg, m, s, A, K, mol, cd). The SIDimension class can parse, pretty-print, and manipulate the coefficients of any quantity. It is used to compute the coefficients of the unit of multiplication or division of scalars, vectors and matrices. If, for example, a Length is divided by a Duration, the coefficients of the Duration (0, 0, 0, 0, 0, 1, 0, 0, 0, 0) must be subtracted from those of the Length (0, 0, 0, 1, 0, 0, 0, 0, 0) , resulting in the coefficients for a Speed (0, 0, 0, 1, -1, 0, 0, 0, 0).

!!! note "Fractional Coefficients"
    SIDimensions is prepared for fractional coefficients, but this is not yet implemented.

<hr>
<sup>1</sup>. See [https://en.wikipedia.org/wiki/Physical_quantity](https://en.wikipedia.org/wiki/Physical_quantity)
