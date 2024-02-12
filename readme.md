# Description

This is a Cucumber BDD framework implemented in Java. It uses Selenium WebDriver for UI automation and the Rest Assured library for testing API scenarios.

## How to Run Tests

`CukesRunner.java` file is used to execute tests while developing locally. Pass the desired tag value in the `CukeRunner` file to target a specific test or a set of tests for execution.
Tests generate an HTML report at the end.

## How to Run Using Command Line

Tests are executed in the test lifecycle of Maven. Run tests with the `mvn test` command.

## How to Manage Browsers

Configuration properties file manages on what browser tests are executed. Acceptable values are `chrome`, `firefox`, `headless-chrome`.
While running tests as a part of CI, you can provide browser types as a run parameter: `mvn test -Dbrowser=chrome`. Parameters passed by the command line take priority over the properties file.

## Secret Management

Private keys and public keys required for the API scenarios must be passed as environment variables using keys `private_key` and `public_key`.
