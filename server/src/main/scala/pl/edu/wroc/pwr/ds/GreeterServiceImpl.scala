package pl.edu.wroc.pwr.ds

import scala.concurrent.Future
import akka.NotUsed
import akka.stream.Materializer
import akka.stream.scaladsl.BroadcastHub
import akka.stream.scaladsl.Keep
import akka.stream.scaladsl.MergeHub
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import org.apache.log4j.Logger

case object GreeterServiceImpl extends GreeterService {
  val log = Logger.getLogger(this.getClass)

  override def sayHello(request: HelloRequest): Future[HelloReply] = {
    log.info(s"sayHello method call with $request")
    Future.successful(HelloReply(s"Hello, ${request.name}"))
  }

}
