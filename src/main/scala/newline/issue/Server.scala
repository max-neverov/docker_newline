package newline.issue

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Server {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("sample")
    // materialize stream blueprints as running streams
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route = path("foo") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, """{"foo":"bar"}"""))
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

    var i = 0

    while (StdIn.readLine() == null) {
      i += 1
      println(i)
    }

    bindingFuture.flatMap(_.unbind())
      .onComplete(_ => system.terminate)
  }

}
