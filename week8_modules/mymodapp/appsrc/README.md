# Week8 notes

From the cmd line

1. Compile the 'required package code'

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules/mymodapp git:[main]
javac -d appmodules/appfuncs appsrc/appfuncs/appfuncs/simplefuncs/SimpleMathFuncs.java

javac -d [location] [sourcefile to compile]
```
2. Then compile the `module-info.java` for the `appfuncs` module.

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules/mymodapp git:[main]
javac -d appmodules/appfuncs appsrc/appfuncs/module-info.java

javac -d [location] [sourcefile to compile]
```
This steps puts the __module-info.class__ file into the __appmodules/appfuncs__ directory.

## Better option

Just compile a `module-info.java` file and its source file in one line

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules/mymodapp git:[main]
javac -d appmodules/appfuncs appsrc/appfuncs/appfuncs/simplefuncs/SimpleMathFuncs.java appsrc/appfuncs/module-info.java

javac -d [location] [sourcefile to compile] [sourcefile to compile]
```

Then for the module
```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules/mymodapp git:[main]
 :: --module-path [name of compiled module] -d [sourcefile to compile] [sourcefile to compile for module-info.java]
javac --module-path appmodules -d appmodules/appstart appsrc/appstart/module-info.java appsrc/appstart/appstart/mymodappdemo/MyModAppDemo.java
```
Notes `--module-path` option this is the module path. This is where the compiler will look for user-defined modules.
The flag will tell the compiler to look for what this file `requires` 

## Running the application

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules/mymodapp git:[main]
java --module-path appmodules -m appstart/appstart.mymodappdemo.MyModAppDemo
        2 is a factor of 10
        Smallest factor common to both 35 and 105 is 5
        Largesst factor common to both 35 and 105 is 7
```
## `Requires` and `Exports`
Specified within each `module-info.java`.
`requires [moduleName]` Ability to specify dependence
`exports [moduleName]` Ability to satisfy dependence

## JLINK
Working with the exploded directory and creating runtime images.
From the Complete Reference:
"the option `--launcher` tells __jlink__ to create a command that starts the application. It specifies the name of the application
and the path to the main class. In this case, the main class is __MyModAppDemo__. The `--module-path` option specifies the apth to the re
required modules. Ths first is the path to the platform modules; the second is the path to the application modules...The 
`--add-modules` option specifies the module or modules to add. Notice `appstart` is specified. This is because jlink
automatically resolves all dependencies and includes all required modules. Finally `--output` specifies the output directory."
```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules git:[main]
jlink 
      --launcher MyModApp=appstart/appstart.mymodappdemo.MyModAppDemo 
      --module-path "%JAVA_HOME%"/jmods:mymodapp/appmodules 
      --add-modules appstart 
      --output mylinkedmodapp
```
After this is run `mylinkedmodapp` will be created that creates the runtime image. In `mylinkedmodapp/bin` you find the
MyModAppDemo main class.

## JAR
`J`ava `AR`chive - file format used for app deployment.
There are modular JAR files.

You must create a directory under `~/mymodapp` called `applib`
The following must be executed above: `mymodapp`

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules git:[main]
jar --create 
    --file=mymodapp/applib/appfuncs.jar 
    -C mymodapp/appmodules/appfuncs .
```

```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules git:[main]
jar --create --file=mymodapp/applib/appstart.jar 
    --main-class=appstart.mymodappdemo.MyModAppDemo 
    -C mymodapp/appmodules/appstart .
```
- `--create` - new JAR file.
- `--file` - specifies name
  - use `-C` to give the file names
  - `--main-class=` is where the 'main' class of the application is.

__Modular JAR__
This links the JAR together
```shell
jlink 
--launcher MyModApp=appstart 
--module-path="%JAVA_HOME%"/jmods:mymodapp/applib 
--add-modules appstart 
--output mylinkedmodapp

```

Running the linked app:
```shell
~/IdeaProjects/bbk-sdp-2025/week8_modules git:[main]
java -p mymodapp/applib -m appstart
  2 is a factor of 10
  Smallest factor common to both 35 and 105 is 5
  Largesst factor common to both 35 and 105 is 7
```
