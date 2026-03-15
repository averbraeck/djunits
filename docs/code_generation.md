# Code Generation

The code generator consists of a couple of programs that write output files in a directory `generated-code` under the directory of the `djunits-generator` project.

The following programs can be run:

* GenerateXSD: this writes file resources/djunits.xsd under the generated-code directory of the djunits-generator project. This is schema file for the ots-xsd project (part of OpenTrafficSim). This file should replace part of the file ots-types.xsd. This can be useful for other projects that need to parse values in XML files.
* CliUnitConverters. this writes to the console. This output should be copied and inserted in CliUnitConverters.java in the djutils-ext project starting at the comment "Register all DJUNITS converters" and ending just before the closing brace of that class file. If new units were added to DJUNITS, an organize imports operation may be needed on that class file.
* GenerateCliConvertersTest: this writes to the console. When new units have been added, this generator needs to be augmented with tests for those new units. This output should be copied and inserted in CLIUnitConverters in two parts.

