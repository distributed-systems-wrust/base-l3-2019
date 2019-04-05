# 2019 - Task List 3 


 - [Akka gRPC docs](https://developer.lightbend.com/docs/akka-grpc/current/overview.html#akka-grpc)
 - [protobufs](https://developers.google.com/protocol-buffers/)

```bash
./sbt assembly
java -jar server/target/scala-2.12/server-assembly-0.1-SNAPSHOT.jar
java -jar client/target/scala-2.12/client-assembly-0.1-SNAPSHOT.jar
```


1. Protobuf - protobufs definitions
2. Client - gRPC client using protobuf definitions
3. Server - gRPC server using protobufs definitions


Tasks:
1. Read about akka gRPC
2. Read about protobufs
3. Look at sample code
4. Add new method definition to protobuf definition
     - define new input object
     - define new output object
     - define new method
5. Implement method in the server
6. Add new method call in the client
