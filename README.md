Immutable Collections are a port of Scala's immutable, persistent collection classes to pure Java.

Persistent in the context of functional data structures means the data structure preserves the previous version of itself when modified. 
This means any reference to a collection is effectively immutable. 
However, modifications can be made by returning a new version of the data structure, leaving the original structure unchanged.

### Why port?
Scala's collections can be directly used from Java, but the resulting code is far from idiomatic. Scala's standard library is also large and binary incompatible between versions.

Secondly, a pure Java implementation of functional persistent collections is usable only for Java.

### Features
- TreeMap
- HashMap
- DerivedKeyHashMap
- ArrayList
- LinkedList
- Vector
- HashSet
- Set
- Traversable
- Iterable
