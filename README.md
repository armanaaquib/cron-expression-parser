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

### Help
| Field        | Required | Allowed values | Allowed special characters | Examples                                          |
| ------------ | -------- | -------------- | -------------------------- | ------------------------------------------------- |
| Minute       | Yes      | 0–59           | `*` `-` `,` `/`            | `*` `25` `2-5` `2,5,9-11` `*/15` `1-10/2` `10/15` |
| Hour         | Yes      | 0–23           | `*` `-` `,` `/`            | `*` `10` `2-5` `2,5,9-11` `*/5` `1-10/2` `10/5`   |
| Day of month | Yes      | 1–31           | `*` `-` `,` `/`            | `*` `10` `2-5` `2,5,9-11` `*/10` `1-10/2` `10/5`  |
| Month        | Yes      | 1–12           | `*` `-` `,` `/`            | `*` `10` `2-5` `2,5,9-11` `*/3` `1-10/2` `5/2`    |
| Day of week	 | Yes      | 0–6            | `*` `-` `,` `/`            | `*` `1` `2-5` `2,4-6` `*/2` `1-3/2` `2/2`         |
| Command      | Yes      |                |                            |                                                   |

## Developer Notes

The project requires [JDK 17](https://adoptium.net/en-GB/) or higher.

The project makes use of [Gradle Build Tool](https://docs.gradle.org/current/userguide/userguide.html) and uses the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), which means you don't need Gradle installed.

Recommend IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Running application

```
./gradlew run --args "<cron-string>"
```
Example:
```console
./gradlew run --args "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Running tests

```console
./gradlew clean test
```
