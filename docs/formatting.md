# Formatting

## Introduction

All quantities, vectors, matrices and quantity tables, both relative and absolute, can be formatted in a flexible way. All quantity-related types have a `format()` method that takes a `Format` object as a parameter. The `Format` object specifies the format that needs to be applied to the quantity-related type. Specific formatters exist for quantities, vectors, matrices and quantity tables. The format has default values that are called with the method `defaults()`. Changes are applied to the default settings to specify the format. As an example, let's take a length in miles, and format it in meters as a floating point variable with a thousands separator:

```java
Length l = Length.of(12.43, "mi");
Length l = Length.of(12.43, "mi");
String s = l.format(QuantityFormat.defaults().setDisplayUnit("m")
    .setGroupingSeparator(true));
System.out.println(s);
```

This will print:

```
20,004.14592 m
```

All quantity-related types have four methods:

- `toString()` returns a String with a representation of the quantity-related object using the default formatting rules. 
- `format()` returns a String with a representation of the quantity-related object using the default formatting rules.
- `format(Unit unit)` returns a String, with the quantity-related object expressed in the given unit, using the default formatting rules.
- `format(Format format)` returns a String representation of the quantity-related object, using the provided formatting rules.

All `Format` subclasses implement methods for formatting the **number**, the **unit**, the **locale**, and the **reference** in case the object is absolute. The formatting rules for these four categories will be discussed below.


## Number formatting settings

The following settings for formatting the number part (entries in a vector, table or matrix, or the number of a quantity) can be used:

- `setVariableLength()` formats the number without any restrictions. It behaves like the `Double.toString()` method and it prints all decimals, including, e.g., .99999999997 or .0000000001 when a calculation did not result in a 'clean' fraction. When the number of digits before the decimal point becomes larger than 10, or the number of leading zeros in a fraction of a number less than one becomes more than six, scientific notation is used. This setting ignores the `setWidth()` and `setDecimals()` settings. 
- `setFixedFloat()` formats the number using a fixed number of positions and a fixed number of decimals. This is an ideal format for matrices and column vectors, since the numbers (and decimal points) will be aligned. The width can be set with the the `setWidth()` method, and the number of decimals with the `setDecimals()`. The grouping separator can be turned on or off with `setGroupingSeparator(boolean)`. Of course, numbers have to be in the same order of magnitude to make this format viable. Technically, if `W` is the width and `D` is the number of decimals, the Java format used is `%D.Wf` without a grouping separator, and `%,D.Wf` with the grouping separator.
- `setScientific()` formats the number using scientific formatting in the form of `x.yyyyE+zz` or `x.yyyyE-zz`. This is an ideal format for numbers with varying magnitudes. For matrices and column vectors, the numbers (and decimal points) will be aligned. The number of decimals can be set with the `setDecimals()`. The total width is set with `setWidth()`, which can lead to extra spacing on the left. There is a choice for an `E` or `e` symbol using the method `setUpperE(boolean)`. Technically, if `W` is the width and `D` is the number of decimals, the Java format used is `%D.WE` with `upperE` set to true, and `%D.We` with `upperE` set to false`.
- `setEngineering()` formats the number using engineering formatting in the form of `x.yyyyE+zz` or `x.yyyyE-zz`, where the value of `zz` is always a multiple of three. You can therefore easily interpret the number in 'micro', 'milli', 'kilo', 'mega' terms. This is an ideal format for numbers with varying magnitudes that have to be interpreted using SI-prefixes. For matrices and column vectors, the numbers (and decimal points) will be aligned. The number of decimals can be set with the `setDecimals()`. The total width is set with `setWidth()`, which can lead to extra spacing on the left. There is a choice for an `E` or `e` symbol using the method `setUpperE(boolean)`. Technically, if `W` is the width and `D` is the number of decimals, the Java format used is `%D.WE` with `upperE` set to true, and `%D.We` with `upperE` set to false`.
- `setFixedWithSciFallback()` formats the number using `setFixedFloat()` if it fits the set width and will use `setScientific()` when the resulting string does not fit the set width. This will enable aligned columns for vectors or matrices, but uses precision when space allows. Widths and decimals that can store all numbers are w.d = 9.1, 10.2, 11.3 etc. For width 9 and 1 decimal, the largest number in terms of size is `-1.2E+300`.
- `setFixedWithEngFallback()` formats the number using `setFixedFloat()` if it fits the set width and will use `setEngineering()` when the resulting string does not fit the set width. This will enable aligned columns for vectors or matrices, but uses precision when space allows. Widths and decimals that can store all numbers are w.d = 9.1, 10.2, 11.3 etc. For width 9 and 1 decimal, the largest number in terms of size is `-1.2E+300`.
- `setFormatString(String)` allows for a user-defined format string for formatting numbers. It will ignore any settings of `setWidth()`, `setDecimals()`, `setGroupingSeparator()`, or `setUpperE()`. Suppose you want a left-aligned 12-wide, 4 decimal format that falls back to scientific notation according to the Java format rules, you would specify: `setFormatString("%-12.4G")`. 

Helper methods for the formatting are:

- `setWidth(width)` sets the total fixed width of the output. If the output does not fit the width, it can be overruled. The default value is `10`.
- `setDecimals(decimals)` sets the exact number of decimals for the output. The default value is `3`.
- `setGroupingSeparator(boolean)` sets the grouping separator (e.g., thousands separator) on or off. The default value is `false`.
- `setUpperE(boolean)` sets the exponent symbol to `E` if `true` and to `e` if `false`. 

The usage of these settings by the different formats is as follows:

| Format               | Width   | Decimals | Grouping       | UpperE       |
| -------------------- | ------- | -------- | -------------- | ------------ |
| FixedFloat           | Used    | Used     | Used           | Ignored      |
| Scientific           | Used    | Used     | Ignored        | Used         |
| Engineering          | Used    | Used     | Ignored        | Used         |
| FixedWithSciFallback | Used    | Used     | Used for fixed | Used for Sci |
| FixedWithEngFallback | Used    | Used     | Used for fixed | Used for Eng |
| FormatString         | Ignored | Ignored  | Ignored        | Ignored      |


## Unit formatting settings

The following settings for formatting the unit can be used:

- `setUnitPrefix(String)` defines the string that will be used just before the unit. For a quantity, it will be the string between the number and the unit. For a matrix it will be the srtring between the end of the matrix and the unit. The default value is `" "`, a single space.
- `setUnitPostfix(String)` defines the string that will be used just after the unit. An example of using this setting is when the unit has to be placed between brackets. In that case, `setUnitPrefix(" (").setUnitPostfix(")"` can be used. The default value is an empty string.
- `setDisplayUnit(Unit)` will try to format the number(s) in line with the given unit, and attach this unit to the formatted output. Note that this influences the values of the numbers as well. The display unit of the object that is being formatted remains untouched. In case the given unit is not applicable for the object being formatted, its original display unit will be used. No exception is thrown in this case. 
- `setDisplayUnit(String)` will try to parse the string into a unit, format the number(s) in line with that unit, and attach this unit to the formatted output. Note that this influences the values of the numbers as well. The display unit of the object that is being formatted remains untouched. In case the given string can not be correctly parsed, or the unit is not applicable for the object being formatted, its original display unit will be used. No exception is thrown in this case. 
- `setTextual()` uses the textual representation as defined for the unit, so `deg` for an angle in degrees. The default is the display representation.
- `setDisplay()` uses the display representation as defined for the unit, so &deg; for an angle in degrees. This is the default setting.
- `setTextual(boolean)` switches to textual or display representation, depending on the value of the boolean argument. 
- `setSiUnits()` switches the unit representation to its SI representation, so for energy, kgm<sup>2</sup>/s<sup>2</sup> instead of J. Note that the number(s) will be shown in SI units rather than in their regular display unit, so this setting influences the number display as well. The default is that the use of SI units is false. When the SI units setting is used, several other settings apply, which will be explained below.


## SI unit formatting settings

When `setSiUnits()` is applied, the number(s) will be shown relative to the SI unit, and the unit will be shown consisting of integer powers of `rad`, `sr`, `kg`, `m`, `s`, `A`, `K`, `mol`, and `cd` in the numerator and denominator. For energy, for instance, kg.m^2/s^2 can be obtained as the output string for the unit. The following formatting options are available for formatting an SI string. Note that these settings can be changed as well when SI formatting is off, and the settings will have no effect in that case.

- `setDivider(boolean)` uses a divider for the negative powers when `true` and negative powers when `false`. Energy will use `kgm2/s2` when the divider is set to `true`, and `kgm2s-2` when divider is set to false. The default value is `true` for the divider.`
- `setPowerPrefix(String)` and `setPowerPostfix(String)` provide a prefix and postfix string for all powers. As an example, suppose we want to precede powers with a caret, the we use `setPowerPrefix("^")`. The energy unit will be formatted as `kgm^2/s^2` after applying this setting. Another example is HTML formatting of the powers: `setPowerPrefix("<sup>").setPowerPostfix("</sup>")`. The energy unit will be formatted as `kgm<sup>2</sup>/s<sup>2</sup>` after applying this setting, and be displayed as <code>kgm<sup>2</sup>/s<sup>2</sup></code>. The default values for the prefix and postfix are an empty string. 
- `setDotSeparator(String)` sets a separator that will used between different SI units. Suppose that the dot separator is set to a center dot: `setDotSeparator("\u22C5")`, then the energy unit will be formated as <code>kg&sdot;m/s2</code>. The default value for the dot separator is the empty string. 

The settings can be combined to format quantities, e.g., as a HTML string:

```java
var q = Energy.of(1.23, "kJ");
var s = q.format(QuantityFormat.defaults().setSiUnits().setDotSeparator("&sdot;")
    .setPowerPrefix("<sup>").setPowerPostfix("</sup>").setDivider(false));
System.out.println(s);
```

This will output: `1230 kg&sdot;m<sup>2</sup>&sdot;s<sup>-2</sup>`, which will be rendered in a browser as: <code>1230 kg&sdot;m<sup>2</sup>&sdot;s<sup>-2</sup></code>.


## Locale formatting

The locale for formatting a quantity-related object can be changed, without changing the locale for the entire program and without explicitly setting the locale in the code:

- `setLocale(Locale)` changes the locale for this format string only.

The following example shows the effect of using a locale setting:

```java
Locale.setDefault(Locale.GERMANY);
Length l = Length.of(12.43, "mi");
String sDE = l.format(QuantityFormat.defaults().setDisplayUnit("m").setGroupingSeparator(true));
System.out.println(sDE);
String sUS = l.format(QuantityFormat.defaults().setLocale(Locale.US)
    .setDisplayUnit("m").setGroupingSeparator(true));
System.out.println(sUS);
Locale.setDefault(Locale.US);
```

which outputs:

```
20.004,14592 m
20,004.14592 m
```


## Reference formatting (absolute)

For quantities, vectors, matrices and tables that use an absolute quantity, the reference point that is used can be formatted. Note that including the reference point is by default set to false. The following methods can be used for formatting the reference:

- `setPrintReference()` turns reference printing on.
- `setNoReference()` turns reference printing off. This is the default setting.
- `setPrintReference(boolean)` turns reference printing on or off, depending on the value of the boolean.
- `setReferencePrefix(String)` sets the prefix for the reference to the provided string. By default, the reference prefix is set to `" ("`.
- `setReferencePostfix(String)` sets the postfix for the reference to the provided string. By default, the reference postfix is set to `")"`.

As an example, suppose we want to show that the absolute temperatures are relative to 0 &deg;C, and not to 0 kelvin, by using a string `(relative to 0 &deg;C)` instead of the normal reference string, which will be ` (CELSIUS)` when turned on:

```java
Temperature t = new Temperature(0.0, Temperature.Unit.degF);
System.out.println(t.relativeTo(Temperature.Reference.CELSIUS).format(
    QuantityFormat.defaults().setDisplayUnit("K").setPrintReference()
        .setReferencePrefix(" (relative to 0 ")));
```

outputs:

```
-17.77777777777777 K (relative to 0 CELSIUS)
```

## Quantity formatting

Quantity formatting is done using the `QuantityFormat` class. Since `QuantityFormat` extends `Format`, all above settings for formatting the number, unit, locale, and absolute references can be used as well. 

The `QuantityFormat` class has one additional setting, which is the formatting using SI prefixes. Using SI prefixes means that 1200 J will be displayed as 1.2 kJ, and 1.34&sdot;10<sup>-6</sup> m will be displayed as 1.34 \micro;m. 

- `setScaleSiPrefixes()` turns on the scaling of SI prefixes. By default, any 10th power between -30 and +32 (inclusive) will be translated to the nearest SI unit. So, 1200 m will be turned into 1.2 km, and 1.45E-9 s will be transformed into 1.45 ns. 
- `setScaleSiPrefixes(minPrefixPower, maxPrefixPower)` turns on the scaling of SI prefixes if the 10th power is between `minPrefixPower` and `maxPrefixPower`, inclusive. This can be used to prevent transformations that are not often used. For length, for instance, units above the km are not often used -- we typically do not use Mm, Gm, etc. But &micro;m, nm, pm, are often used. By calling `setScaleSiPrefixes(-30, 3)`, the intended prefixes are used. 1,000,000 m will remain in meters in this case. 

> **Note** that the unit will have to be translated into the SI unit to make this work. In other words, an energy in `meV` will not be automatically translated into `J` an SI prefix. In a future version of djunits, this might be done automatically.:

```java
Energy energy = new Energy(13.34, "GeV");
System.out.println(energy.as(Energy.Unit.J).format(QuantityFormat.defaults()
    .setScaleSiPrefixes()));
```

prints:

```
2.1373036297559995 nJ
```

> **Note** that the `setScaleSiPrefixes()` also works for the `kg`, which already starts with a 10<sup>3</sup> power as the default unit. The scaling in `setScaleSiPrefixes(minPrefixPower, maxPrefixPower)` is treated relative to the `g`, so if you want to print `kg`, but no `Mg`, and you do not want to go below the pg, use `setScaleSiPrefixes(-12, 3)`. The scaling also works for 'per' units, such as 'per mol', 'per kg', etc.


## Vector formatting


## Matrix fomatting


## QuantityTable formatting


## Changing default values

