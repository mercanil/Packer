[![Build Status](https://travis-ci.org/mercanil/Packer.svg?branch=master)](https://travis-ci.org/mercanil/Packer) [![Coverage Status](https://coveralls.io/repos/github/mercanil/Packer/badge.svg?branch=master)](https://coveralls.io/github/mercanil/Packer?branch=master)

## Objective
You want to send your friend a package with different things.
Each thing you put inside the package has such parameters as index number, weight and cost. The package has a weight limit. Your goal is to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible.
You would prefer to send a package which weights less in case there is more than one package with the same price.


### Tech
Packer developed with Java 8, a number of technologies and open source projects:

* [JDK8]
* [Maven]
* [JUnit]
* [Lombok]
* [SLF4J]
* [Assertj]


### Algorithm
Dynamic programming solution for knapsack problem.You can get more information about algorithm [here]
The implementation of the algorithm can be found in the class PackageItemSelector.

### Design Patterns
* Strategy design pattern implemented for selecting input and output algorithm at runtime.


###  How to use (For Maven Projects)
Packer requires [JDK8]. Please check your JAVA_HOME for success of build.
```sh
$ echo ${JAVA_HOME}
```

This is a maven project. Install in your local maven repository:
```sh
$ cd ${project.basedir}
$ mvn clean install
```


Add these dependency to you pom.xml

```
  <dependency>
    <groupId>com.mobiquityinc</groupId>
    <artifactId>packer</artifactId>
    <version>1.0-SNAPSHOT</version>
  <dependency>
```

### Input File Format
    
$WeightLimit: (index,weight,cost) (index,weight,cost)

10: (1,1,€1) (2,2,€2) (3,30.91,€14) (4,16.13,€58)

#### Javadoc

After building the project. You can check out the javadoc of project html file at this path.
```sh
$ cd ${project.basedir}
$ mvn javadoc:javadoc
```
You can find javadoc on  ${project.basedir}/target/apidocs/index.html

#### Travis CI
Project is integrated with [Travis]. After each commit [Travis CI] runs maven tests and creates valid version of project.


### Coveralls   
To ensure code quality the project unit test results are sent to [Coveralls] after each build. Unit tests and coverage 
metrics are [available] in Coveralls.



[JDK8]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
[JUnit]: <https://junit.org/>
[Swagger]: <https://swagger.io/>
[Maven]: <https://maven.apache.org/>
[Lombok]: <https://projectlombok.org/>
[Assertj]: <https://joel-costigliola.github.io/assertj/>
[SLF4J]: <https://www.slf4j.org/>
[here]: <https://en.wikipedia.org/wiki/Knapsack_problem#0/1_knapsack_problem/>
[Travis]: <https://travis-ci.org>
[Travis CI]: <https://travis-ci.org/mercanil/Packer>
[Coveralls]: <https://coveralls.io/>
[available]: <https://coveralls.io/github/mercanil/Packer?branch=master/>
