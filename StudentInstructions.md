# Student Instructions

You are provided with an interface `IListOfString` and need to complete and
test the concrete classes `EmptyListOfString` and `NonEmptyListOfString`.

Specifically, you need to implement and test the methods:

* `int size()`
* `String concat()`
  The methods are described in the javadoc.

## Tests

You are encouraged to practice test-driven development (TDD) and write
your tests before implementing the methods under test. To earn credit,
your tests must:

* be in `ILOSTest.java`
* have the `@Test` annotation
* have the `public` visibility modifier
* start with the name of the method under test (such
  as `sizeWorksForLength0`)
  Use the existing tests as a model.

You should modify and submit these files:

* `ILOSTest.java`
* `EmptyLOS.java`
* `NonEmptyLOS.java`

All of your tests must be annotated with `@Test` and have names that start
with the name of the method being tested, with the exact same capitalization.**
Use the provided test as a model for names.

## Grading

Grading will be based on:

* checkstyle with configuration
  file [config/checkstyle-rules.xml](config/checkstyle-rules.xml) [10 points]
* PMD
  with [java/best_practices.xml](https://docs.pmd-code.org/latest/pmd_rules_java_bestpractices.html) [10 points]
* Unit testing
    * Your implementation passes your tests [20 points]
    * Our hidden correct implementation passes your tests [15 points]
    * Our hidden buggy implementations fail your tests [15 points]
    * Your implementation passes our hidden tests [20 points]
* Code coverage [10 points]
    * `EmptyLOS` [2 points]
    * `NonEmptyLOS` [8 points]

Your code coverage score will be directly proportional to your line coverage.
For example, if you achieve 85% line coverage for both classes, you will earn
8.5 out of 10 points.
