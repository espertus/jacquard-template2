# Jacquard Example 2

[Jacquard](https://github.com/espertus/jacquard) [[Javadoc](https://jacquard.ellenspertus.com)]
is a Java autograder library for Gradescope. This repository provides an example
of a Jacquard-based autograder that was used
for a homework assignment in a Java data structures course.
It demonstrates the use of:

* Configuring the autograder, including options for specifying test visibility
* Running Checkstyle and PMD on multiple targets
* Measuring code coverage
* Running unit tests on student code
* Cross-testing, i.e., running student tests against
    * student code
    * correct code
    * buggy code

## Prerequisites

The rest of this document assumes that you have read:

* [Jacquard Example 0](https://github.com/jacquard-autograder/jacquard-example0),
  a more detailed introduction to the basic features
* [The student instructions](StudentInstructions.md) for this assignment

[Jacquard Example 1](https://github.com/jacquard-autograder/jacquard-example1) is
_not_ a prerequisite.

## Video Explanation

1. [A look at `AutograderMain`](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=6b00de8f-4abe-49a6-a348-b12e0012f37b) (8:20), with chapters on:
   * Configuring the autograder (0:11)
   * Setting multiple targets (2:06)
   * Running Checkstyle on multiple targets (2:41)
   * Running PMD on multiple targets (4:16)
   * Measuring code coverage (5:14)
   * Testing student code with JUnit (6:30)
2. [Cross-testing](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=165ca9fa-98eb-4f0f-8841-b069013430c5) (3:15)
3. [Testing the autograder locally](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=fdc7e6cd-360a-4a17-ba02-b12e006326ed) (0:38)

## Execution

Unlike the previous examples, this autograder must be run from the command line.
It will not run within the IDE unless you remove 
or comment out the lines that use the `CrossTester`:
```
CrossTester crossTester = new CrossTester(
    student.ILOSTest.class,
    "student-tests.csv");
results.addAll(crossTester.run());
```
The command to run it on code in the `submission` directory is:
```
./test_autograder.sh
```

The command to run it on code in the `submissions/imperfect` directory (for example) is:
```
./test_autograder.sh imperfect
```


## Files

These directories and files have code specific to the assignment:

* `config/checkstyle-rules.xml` holds the checkstyle rules file
* `src/main/java/student` contains
    * `AutograderMain.java`, which has the `main` method that controls the autograder
    * `ILOS.java`, which provides a "list of string" interface
    * `EmptyLOS.java` and `NonEmptyLOS.java`, placeholders for the student
      implementations of the interface
    * `ILOSTest.java`, a placeholder for student tests
    * `HiddenILOSTest.java`, which contain hidden JUnit 5 tests of student code
* `src/main/java/buggy` contains a deliberately buggy implementation of the
  required code, on which student tests should report errors
* `src/main/java/correct` contains a correct implementation of the required
  code, on which student tests should not report errors
* `src/main/resources/student-tests.csv` contains a CSV file for [cross-testing](https://github.com/espertus/jacquard-examples/blob/main/README.md#what-is-cross-testing)
* `submission` holds a sample submission (required if you want to run
  `test_autograder.py` locally)
* `submissions` (which is not required) holds sample submissions to manually
  test the grader locally or on Gradescope
    * `perfect`, a subdirectory with a perfect submission
    * `imperfect`, a subdirectory with an imperfect submission
    * `starter`, a subdirectory with the starter code

Any of the above files could have different names or packages. The `submissions/` subdirectories
could also have different names.

### config.ini

The submission package and files are specified in the `[submission]` section of
[`config.ini`](config.ini) and should be edited if you change the package name or required
files. Currently, package names must have only a single part (e.g., "student",
not "edu.myschool.student").

```
[submission]
package = student
files = [EmptyLOS.java, NonEmptyLOS.java, ILOSTest.java]

[crosstests]
tests = [ILOSTest.java]
packages = [correct, buggy]
```

The `[crosstests]` sections indicates that the test `ILOSTest.java` in the
primary (`student`) package should also run on the instructor-provided
implementations in the `correct` and `buggy` packages. For more information, see
[What is cross-testing?](https://github.com/espertus/jacquard-examples/blob/main/README.md#what-is-cross-testing).

**The remainder of the Teacher Instructions are the same as
for [Jacquard Template 1](https://github.com/espertus/jacquard-template1).**

#### build.gradle

The main class of the autograder is specified in `build.gradle`:

```groovy
ext {
    javaMainClass = "student.AutograderMain"
}
```

You will need to change it if you use a different package/class name for
your main autograder class.

You are free to make other additions to `build.gradle`, such as adding
dependencies.

### Shell scripts

#### test_autograder.sh
This script, which requires Python 3, lets you test the autograder locally. If called without any
arguments, it will use the submission in the `submission/` directory.
```shell
./test_autograder.sh
```

If called with an argument, it will use the submission in that subdirectory,
prepending `submissions/` if necessary. For example, to test the autograder
against the files in `submissions/perfect`, you could execute either:
```shell
./test_autograder.sh submissions/perfect
./test_autograder.sh perfect
```

#### make_autograder.sh

This creates the zip file for you to upload to Gradescope.

### Uploading to Gradescope

#### Zip file

To create a zip file, run `./make_autograder.sh` from the command line.

To configure the autograder on Gradescope:

1. Click on "Configure Autograder" in the left sidebar.
2. Select "Zip file upload".
3. Click on "Replace Autograder (.zip)".
4. Select:
    * Base Image OS: Ubuntu
    * Base Image Version: 22.04
    * Base Image Variant: JDK 17
5. Click on "Update Autograder". (You may have to wait up to a minute for
   anything to happen. The button will go gray when the build begins.)
6. Wait for the "Built as of" time to be updated.

![screenshot showing Zip file upload of autograder.zip with Ubuntu 22.04 and
JDK 17 selected](../images/configure-autograder.png)

