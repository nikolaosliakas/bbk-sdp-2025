# JAVA Generics

Release in Java 5.0

Generics allow for types validation at compile time for not primitive checks.

## pre-release context
When retrieving element from Collection, you had to cast it
Unsafe, as this would not be caught at compile time, but at runtime.
Devs would use Object wrapper classes and explicitly need to cast the wrapper when creating
objects of that class.

## Generics
Generics are parameterized types. 
These are replaced with concrete types at compile time

Generics must be reference types not primitive types

Subtypes (like sub type of Collections means it is _bounded type_)

### Benefits

Stronger type checks at compile time
Eliminates unnecessary casting

Example:
```java
// prior to Java 5.0
public interface Comparable {
    public int compareTo(Object o);
}

//Using generic types
public interface Comparable<T>{
    public int compareTo(T o);
}
```
Same interface can be used for an Array of any type!



