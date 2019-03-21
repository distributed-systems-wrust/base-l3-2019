package pl.edu.wroc.pwr.ds

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import akka.actor.ActorSystem
import akka.http.scaladsl.{Http, HttpConnectionContext}
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.UseHttp2.Always
import akka.stream.ActorMaterializer
import akka.stream.Materializer
import com.typesafe.config.ConfigFactory
import org.apache.log4j.Logger

object GreeterServer {
  val log = Logger.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    log.info("Application start")
    val conf = ConfigFactory.defaultApplication()
    val system: ActorSystem = ActorSystem("HelloWorld", conf)
    new GreeterServer(system).run()
  }
}

class GreeterServer(system: ActorSystem) {
  val log = Logger.getLogger(this.getClass)

  def run(): Future[Http.ServerBinding] = {
    implicit val sys = system
    implicit val mat: Materializer = ActorMaterializer()
    implicit val ec: ExecutionContext = sys.dispatcher

    val service: HttpRequest => Future[HttpResponse] =
      GreeterServiceHandler(GreeterServiceImpl)

    val bound = Http().bindAndHandleAsync(
      service,
      interface = "127.0.0.1",
      port = 8080,
      connectionContext = HttpConnectionContext(http2 = Always)
    )

    bound.foreach { binding =>
      log.info(s"gRPC server bound to: ${binding.localAddress}")
    }

    bound
  }
}
