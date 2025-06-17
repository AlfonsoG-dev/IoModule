$srcClases = ".\src\application\*.java src\application\*.java src\application\operations\*.java "
$libFiles = ""
$compile = "javac -Werror -Xlint:all -d .\bin\ $srcClases"
$createJar = "jar -cfm IoModule.jar Manifesto.txt -C .\bin\ ."
$javaCommand = "java -jar IoModule.jar"
$runCommand = "$compile" + " && " + "$createJar" + " && " +"$javaCommand"
Invoke-Expression $runCommand 
