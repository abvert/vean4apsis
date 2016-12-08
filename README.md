#### Framework choice  - Dropwizard
http://www.dropwizard.io

#### This project is based on the Dropwizard Getting Started exercise
http://www.dropwizard.io/0.9.2/docs/getting-started.html
##### Note! Deliberately kept the package structure, so one can compare with the Dropwizard original Getting Started project.

#### Finding solution for managing Concurrent Counters
Due to lack of time and experience in that area decided on learning by example:)
Thus googled and found following blog http://blog.takipi.com/java-8-longadders-the-fastest-way-to-add-numbers-concurrently/
 Downloaded provided code and run some tests. Author strongly promotes the LongAdder usage.
 Decided to investigate further via link http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/LongAdder.html
 which resulted in finding out about the ConcurrentHashMap and basically provided a complete solution.

#### To build via command line
mvn package

#### To run via command line
java -jar target/apsis-programming-test-vean-1.0-SNAPSHOT.jar server hello-apsis-config.yml

#### Testing increment a specified counter by 1
http://localhost:8080/hello-apsis?name=Anna
##### Note! omitting name will result in incrementation of a default counter.

#### Testing get a current value of a specified counter
http://localhost:8080/counter-info?name=Anna

#### Testing get a list of all counters and their current value
http://localhost:8080/counters-storage