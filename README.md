[![Build Status](https://travis-ci.org/mercanil/Packer.svg?branch=master)](https://travis-ci.org/mercanil/Packer) [![Coverage Status](https://coveralls.io/repos/github/mercanil/Packer/badge.svg?branch=master)](https://coveralls.io/github/mercanil/Packer?branch=master)

## Objective
The main objective of this project; build a complete application determine which items to put into the package

### Tech
Stumpy developed with Java 8, a number of technologies and open source projects:

* [JDK8]
* [Maven]
* [JUnit]
* [Lombok]
* [SLF4J]
* [Assertj]



### Algorithm
Dynamic programming solution for knapsack problem.
You can get information about algorithm [here]

### Design Patterns
* Strategy design pattern implemented for selecting input and output algorithm at runtime.


### Installation
Packer requires [JDK8]. Please check your JAVA_HOME for success of build.
```sh
$ echo ${JAVA_HOME}
```

This is a maven project. maven install command.
```sh
$ cd ${project.basedir}
$ mvn clean install
```

#### Javadoc

After building the project. You can check out the javadoc of project html file at this path.
```sh
$ cd ${project.basedir}
$ mvn javadoc:javadoc
```
You can find javadoc on  ${project.basedir}/target/apidocs/index.html



[JDK8]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
[JUnit]: <https://junit.org/>
[Swagger]: <https://swagger.io/>
[Maven]: <https://maven.apache.org/>
[Lombok]: <https://projectlombok.org/>
[Assertj]: <https://joel-costigliola.github.io/assertj/>
[SLF4J]: <https://www.slf4j.org/>
[here]: <https://en.wikipedia.org/wiki/Knapsack_problem#0/1_knapsack_problem/>