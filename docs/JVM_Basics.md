**JVM Basics
Java Development Kit (JDK)**

The Java Development Kit (JDK) is a complete software package used to develop Java applications. 
It contains all the tools required to write, compile, debug, and run Java programs.

The JDK includes:

Java Compiler (javac)
Java Runtime Environment (JRE)
Development tools such as javadoc, jar, and jdb

In simple words, JDK is used by Java developers to create Java applications.

**Java Runtime Environment (JRE)**

The Java Runtime Environment (JRE) provides everything needed to run a Java application. 
It includes the Java Virtual Machine (JVM) and the required libraries.

The JRE does not contain development tools like the Java compiler (javac).

In simple words, JRE is used to run Java programs, but not to develop them.

**Java Virtual Machine (JVM)**

The Java Virtual Machine (JVM) is the component that executes Java bytecode. It converts the bytecode into machine code that the operating system understands.

The JVM is responsible for:
Loading Java classes
Executing Java programs
Managing memory
Performing Garbage Collection
Providing platform independence

Every operating system (Windows, macOS, Linux) has its own JVM implementation, allowing the same Java program to run on different platforms.

**Relationship Between JDK, JRE, and JVM**
                JDK
                +---------------------------+
                |   Development Tools       |
                |  (javac, jar, javadoc...) |
                |                           |
                |      +---------------+    |
                |      |      JRE      |    |
                |      |               |    |
                |      |   +-------+   |    |
                |      |   | JVM   |   |    |
                |      |   +-------+   |    |
                |      | Java Libraries|    |
                |      +---------------+    |
                +---------------------------+

**Bytecode :** 

When a Java source file (.java) is compiled using the Java compiler (javac), it is converted into a bytecode file (.class).

Bytecode is not specific to any operating system. Instead, it is a platform-independent intermediate code that can be executed by any JVM.

        Example:
            HelloWorld.java
               │
            javac
               │
            HelloWorld.class (Bytecode)
               │
            JVM
               │
            Machine Code
               │
            Program Output

**"Write Once, Run Anywhere"**

One of Java's biggest advantages is its platform independence. 
A Java program is compiled only once into bytecode. 
The same bytecode can be executed on any operating system that has a compatible JVM installed.

For example, a Java application compiled on Windows can run on macOS or Linux without changing the source code. 
This feature is known as **"Write Once, Run Anywhere (WORA)"** and makes Java a popular choice for cross-platform application development.
        
        WORA example: 
                       Java Source Code
                           │
                       Compile
                           │
                       Bytecode (.class)
                           │
                ┌──────────┼───────────┐
                ▼          ▼           ▼
                Windows JVM  Linux JVM   macOS JVM
                │          │           │
                ▼          ▼           ▼
                Runs Successfully on All Platforms