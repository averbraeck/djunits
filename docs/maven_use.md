# Maven use

Maven is one of the easiest ways to include DJUNITS in a Java project. The Maven files for DJUNITS reside at [https://djunits.org/maven](https://djunits.org/maven). When a POM-file is created for the project, the following snippet needs to be included to include DJUNITS:

```xml
<dependencies>
  <dependency>
    <groupId>org.djunits</groupId>
    <artifactId>djunits</artifactId>
    <version>5.00.00</version>
  </dependency>
  ... other dependencies of your project go here ...
</dependencies>
```

Of course, the version number (5.00.00 in the above example) needs to be replaced with the version that one wants to include in the project.

Currently, the DJUNITS files are stored on a server at TU Delft, and are not yet made available on Maven Central. Therefore, the repository location has to be specified separately in the Maven POM-file:

```xml
<repositories>
  <repository>
    <name>djunits Public Repository</name>
    <id>djunits</id>
    <url>https://djunits.org/maven</url>
  </repository>
  ... other repositories used by your project go here ...
</repositories>
```

### Dependencies

DJUNITS is directly dependent on the following package that has no further dependencies:

* **jakarta.annotation-api** for manipulating annotations using java versions later than Java-8

If the DJUNITS library is used as a part of a Maven project, all dependencies will be automatically resolved, and the programmer / user does not have to worry about finding the libraries.


### Git Location (version 5 and higher) and SVN location (until version 4)

Source code from version 5 and up can be ound at [https://github.com/averbraeck/djunits.git](https://github.com/averbraeck/djunits.git).

Source code from version 1 to version 4 can be checked out from [https://svn.tbm.tudelft.nl/DJUNITS/](https://svn.tbm.tudelft.nl/DJUNITS/). The trunk with the current project (might be unstable) is at [https://svn.tbm.tudelft.nl/DJUNITS/trunk](https://svn.tbm.tudelft.nl/DJUNITS/trunk). Releases can be found at [https://svn.tbm.tudelft.nl/DJUNITS/release](https://svn.tbm.tudelft.nl/DJUNITS/release).
