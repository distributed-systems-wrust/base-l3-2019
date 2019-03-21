package pl.edu.wroc.pwr.ds

import scala.util.Failure
import scala.util.Success
import akka.actor.ActorSystem
import akka.grpc.GrpcClientSettings
import akka.stream.ActorMaterializer
import org.apache.log4j.Logger

object GreeterClient {
  val log = Logger.getLogger(this.getClass)
  def main(args: Array[String]): Unit = {
    implicit val sys = ActorSystem("HelloWorldClient")
    implicit val mat = ActorMaterializer()
    implicit val ec = sys.dispatcher

    val client = GreeterServiceClient(GrpcClientSettings.fromConfig(GreeterService.name))

    def singleRequestReply(name: String): Unit = {
      log.info(s"Performing request: $name")
      val reply = client.sayHello(HelloRequest(name))
      reply.onComplete {
        case Success(msg) =>
          log.info(msg)
        case Failure(e) =>
          log.error("Error", e)
      }
    }

    singleRequestReply("Bob")

  }
}
