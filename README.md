Java SHA Benchmarks
===================

JMH SHA benchmarks, mostly to show the presence and effectivity of [Intel SHA extension](https://en.wikipedia.org/wiki/Intel_SHA_extensions) [intrinsics](https://bugs.openjdk.java.net/browse/JDK-8150767).

Running
-------

```
mvn clean package
java -jar target/sha-benchmarks-0.1.0-SNAPSHOT.jar
```

Results
-------

Intel Core i7-7700T (Kaby Lake), no hardware support

single threaded

```
Benchmark                    (algorithm)   Mode  Cnt  Score   Error   Units
ShaBenchmarks.jdk                  SHA-1  thrpt   15  2.884 ± 0.032  ops/us
ShaBenchmarks.bouncy_caslte        SHA-1  thrpt   15  3.070 ± 0.043  ops/us
ShaBenchmarks.jdk                SHA-256  thrpt   15  3.273 ± 0.027  ops/us
ShaBenchmarks.bouncy_caslte      SHA-256  thrpt   15  2.174 ± 0.049  ops/us
```

8 threads

```
Benchmark                    (algorithm)   Mode  Cnt   Score   Error   Units
ShaBenchmarks.jdk                  SHA-1  thrpt   15  14.209 ± 0.334  ops/us
ShaBenchmarks.bouncy_caslte        SHA-1  thrpt   15  16.525 ± 0.326  ops/us
ShaBenchmarks.jdk                SHA-256  thrpt   15  12.515 ± 0.078  ops/us
ShaBenchmarks.bouncy_caslte      SHA-256  thrpt   15   9.883 ± 0.185  ops/us
```
