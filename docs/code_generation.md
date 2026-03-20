# Code Generation

The code generator helps to generate code or files that can be used within DJUNITS or by other software libraries. One example is the `generateXSD` program that creates an XSD to enable parsing of all defined unit names for all quantities within DJUNITS. 

The programs write their output files into a directory `generated-code` under the main directory of the `djunits-generator` project.

The following programs can be run:

* `GenerateXsd`: this writes file resources/djunits.xsd under the generated-code directory of the djunits-generator project. This is a schema file for the `ots-xsd` project (part of OpenTrafficSim). This file should replace part of the file `ots-types.xsd`. This can be useful for other projects that need to parse values in XML files.
* `GenerateCliConverters`. This program writes to the console. This output should be copied and inserted in `CliUnitConverters.java` file in the `djutils-ext` project starting at the comment "Register all DJUNITS converters" and ending just before the closing brace of that class file. If new units were added to DJUNITS, an 'organize imports' operation may be needed on that class file.
* `GenerateCliConvertersTest`: this program writes to the console. When new units have been added, this generator needs to be augmented with tests for those new units. This output should be copied and inserted in `TestCliUnitConverters` in two parts.

