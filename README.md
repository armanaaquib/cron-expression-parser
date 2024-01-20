# Cron Expression Parser

Cron expression parser is a command line application which parses a cron string and expands each field to show the times at which it will run.

For example, the following input argument:

```console
*/15 0 1,15 * 1-5 /usr/bin/find
```

will yield the following output:
```console
minute       0 15 30 45
hour         0
day of month 1 15
month        1 2 3 4 5 6 7 8 9 10 11 12
day of week  1 2 3 4 5
command      /usr/bin/find
```

## Requirements

The project requires [JDK 17](https://adoptium.net/en-GB/) or higher.

The project makes use of Gradle and uses the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), which means you don't need Gradle installed.


## Running application

`./gradlew run --args "<cron-string>"`
```console
./gradlew run --args "*/15 0 1,15 * 1-5 /usr/bin/find"
```

## Running tests

```console
./gradlew clean test
```
