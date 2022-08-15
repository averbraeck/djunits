# Code Generation

Many class files in the DJUNITS project are highly similar. Where possible those similarities have been abstracted away into a super class. Regrettably, this is not possible for constructors and static methods. In a very early version of DJUNITS, almost all files (even the unit tests) were written by a code generator. In later versions we took a step back from this extreme approach.

Most files in the unit, scalar, vector and matrix packages are written by our code generator. Most files in other packages are "hand-written". All generated files contain a `@Generated` line, just before the start of the `class` definition. Such files should not be edited manually. Instead the corresponding stanza file should be edited and the depending files re-generated.

The code generator source code can be found in [https://svn.tbm.tudelft.nl/DJUNITS/trunk/djunits-generator/](https://svn.tbm.tudelft.nl/DJUNITS/trunk/djunits-generator/). The stanzas for the files are stored in the resources tree of this project. Within the stanza files, words starting and ending on a percent sign, e.g., `%TypeAbs%`, are replaced by the actual absolute type name. A string like `%FORMULAS%` is replaced by an entire file (applying simular substitutions inside it).

* The file FORMULAS.txt contains the common multiplications and divisions of quantities and the unit of the result.
* The file REPLACE.txt contains some fixes that have to be applied to the Time and FloatTime classes due to insufficient precision of the float data type to represent times in seconds since the year 0.
* The file TYPES_ABS_REL.txt contains the names used to generate all absolute quantities and units (with the names of the corresponding relative quantities and units).
* The file TYPES_REL.txt contains the names of all relative units (excluding those that have a corresponding absolute unit).

The code generator consists of a couple of programs that write output files in a directory `generated-code` under the directory of the `djunits-generator` project.

The following programs need to be run:

* GenerateDJUNIT; this generates all scalar, vector and matrix classes for float and double precision. The generated code appears in the generated-code directory under the djunits-generator project. The double tree and the float tree need to be copied into the org.djunits.value packages overwriting existing files.
* GenerateStaticUNITS; this reads the DJUNITS source files (which should be present in the work space) and then writes to the console. Copy all console output and paste it in org.djunits.unit.util.UNITS.java between the @formatter:off and @formatter:on comments.
* GenerateUSLocale; this reads the DJUNITS source files (which should be present in the work space) and then writes to the console. Copy all console output and paste it in the file src/main/resources/localeunit.properties. Any changes in localeunit.properties should be reflected by similar changes in all other languages.
* GenerateXSD: this writes file resources/djunits.xsd under the generated-code directory of the djunits-generator project. This is schema file for the ots-xsd project (part of OpenTrafficSim). This file should replace part of the file ots-types.xsd. This can be useful for other projects that need to parse values in XML files.
* CliUnitConverters. this writes to the console. This output should be copied and inserted in CliUnitConverters.java in the djutils-ext project starting at the comment "Register all DJUNITS converters" and ending just before the closing brace of that class file. If new units were added to DJUNITS, an organize imports operation may be needed on that class file.
* GenerateCliConvertersTest: this writes to the console. When new units have been added, this generator needs to be augmented with tests for those new units. This output should be copied and inserted in CLIUnitConverters in two parts.

Some fixing up is required on the generated code:

* The generated code lacks has some javadoc comments for parameters in a format that is not quite conforming to our style. This is fixed by running the `ParamComments` program. This program will patch ALL java files in all djunits project trees.
* Then, the generated class files must be copied//moved into the appropriate source code tree.
* The generated code lacks the required `import` lines. This is semi-manually fixed by running the `Organize Imports` operation of Eclipse. Select all packages, then use the keyboard short cut ctrl-shift-O, or right-click; source; organize imports.
* The code generator creates output with lines that are often longer than the limit we impose by CheckStyle rules. To fix this, the code formatter of Eclipse must be run on the result. Select all packages, then right-click; source; format.
