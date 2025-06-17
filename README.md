# IO module
A simple project to study and try different ways to manage files using the Java API.
- Also, i plan to integrate multithread operations.

## References
- [Java API docs](https://download.java.net/java/early_access/valhalla/docs/api/)

## Dependencies
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [custom build tool](https://github.com/AlfonsoG-dev/javaBuild)
- [custom file system operations](https://github.com/AlfonsoG-dev/filesManager)

---

## Installation

1. Clone the repository
```sh
git clone https://www.github.com/AlfonsoG-dev/IoModule
```
2. Build the project.
- You can either use the `.ps1` script
```sh
pwsh build.ps1
```
- Or you can use my [custom build tool](https://github.com/AlfonsoG-dev/javaBuild)
```sh
jb --build
```
> If you are using the `.jar` build.
```sh
java -jarJavaBuild.jar --build
```
3. Execute the program using `.jar` file
```sh
java -jar IoModule.jar
```

---

## Usage
There are CLI options to list files from the source of the `mysql JDBC` library.
1. List by using the `Runnable` implementation.
```sh
java -jar IoModule.jar r
```
2. List by using the `Callable` implementation.
```sh
java -jar IoModule.jar c
```
3. List by using the `Callable` in asynchronous manner
```sh
java -jar IoModule.jar ec
```

Lastly you can see the options by using:
```sh
java -jar IoModule.jar --h
```

---

# Disclaimer
- This project is for educational purposes.
- Security issues aren't taken into account.
