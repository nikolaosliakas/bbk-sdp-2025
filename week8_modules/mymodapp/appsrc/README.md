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


