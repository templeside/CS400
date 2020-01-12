For this project, you will be implementing a small program to analyze the performance of your hash table
against Java's built-in TreeMap. TreeMap
(https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html) is a known and in-built data structure of
Java.
To analyze the performance, you need to write a small program that performs the same operations on
both your custom HashTable class and Java's TreeMap class. You will be using Java Flight recorder to
create a program profile, and Java Mission Control to analyze the profile information. Both are open
source and packaged with Oracle JVM’s (The lab machines have this installed).

The goal for your HashTable was to build a searchable data structure that achieves constant time O(1)
for lookup, insert, and delete operations with comparable performance to Java's built-in TreeMap type.
This assignment attempts to determine if your hash table achieved that goal.
Ensure that your hash table implementation works correctly prior to analyzing its performance against
Java's TreeMap class.
