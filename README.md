# CSE564 Fire Detection System
## 1. Description
This project is a simulation for a fire detection and 
evacuation system completed as a course requirement. The system
simulates fires in a building with a designed map. Upon 
detection of a fire, it calculates the safest paths for persons 
in the building, and guides them using alarms and LEDs. 

## 2. Installation and Execution
### 2.1 Command-line Execution
1. Download the JAR file for `com.fasterxml.jackson.core` [here](https://search.maven.org/artifact/com.fasterxml.jackson.core/jackson-databind/2.13.2.2/jar).
2. To compile the repository, run the following command:
```shell
javac -classpath PATH_TO_JAR src/Main.java
```
3. For execution:
```shell
java -classpath PATH_TO_JAR src.Main
```
### 2.2 IntelliJ IDEA
- Navigate to `[File | Project Structure]` > 
`[Project Settings | Libraries]`.
- Go to `+ | From Maven`.
- Enter `com.fasterxml.jackson.datatype:jackson-datatype-hibernate4:2.5.3` and press OK.

*If using Eclipse, check [here](https://wiki.eclipse.org/FAQ_How_do_I_add_an_extra_library_to_my_project%27s_classpath%3F).*