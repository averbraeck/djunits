# Maven use

Maven is one of the easiest ways to include DJUNITS in a Java project. The Maven files for DJUNITS reside at Maven Central. When a POM-file is created for the project, the following snippet needs to be included to include DJUNITS:

```xml
<dependencies>
  <dependency>
    <groupId>org.djunits</groupId>
    <artifactId>djunits</artifactId>
    <version>5.0.0</version>
  </dependency>
  ... other dependencies of your project go here ...
</dependencies>
```

Of course, the version number (5.0.0 in the above example) needs to be replaced with the version that one wants to include in the project.

Older versions of DJUNITS libraries are stored on a server at TU Delft at [https://djunits.org/maven](https://djunits.org/maven). If an older version is needed, a `<repositories>` tag can be added to the pom-file of the Maven project. For version 5.0.0 and higher, a `<repositories>` tag is not needed.


### Dependencies

DJUNITS is directly dependent on the following package that has no further dependencies:

* **jakarta.annotation-api** for manipulating annotations using java versions later than Java-8

If the DJUNITS library is used as a part of a Maven project, all dependencies will be automatically resolved, and the programmer / user does not have to worry about finding the libraries.


### Git Location (version 5 and higher) and SVN location (until version 4)

Source code from version 5 and up can be found at [https://github.com/averbraeck/djunits.git](https://github.com/averbraeck/djunits.git).

Source code from version 1 to version 4 can be checked out from [https://svn.tbm.tudelft.nl/DJUNITS/](https://svn.tbm.tudelft.nl/DJUNITS/). The trunk with the current project (might be unstable) is at [https://svn.tbm.tudelft.nl/DJUNITS/trunk](https://svn.tbm.tudelft.nl/DJUNITS/trunk). Releases can be found at [https://svn.tbm.tudelft.nl/DJUNITS/release](https://svn.tbm.tudelft.nl/DJUNITS/release).
