# IO module
Its a project to test and try different ways to manipulate or interact with the file system using java API.

# References
- [Java API docs](https://download.java.net/java/early_access/valhalla/docs/api/)

# Dependencies
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [custom build tool](https://github.com/AlfonsoG-dev/javaBuild)
- [custom file system operations](https://github.com/AlfonsoG-dev/filesManager)

# Usage

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

# Disclaimer
- This project is for educational purposes.
- Security issues aren't taken into account.
