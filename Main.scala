package community_build_ranking

import gigahorse._, support.okhttp.Gigahorse
import gigahorse.github.Github
import scala.concurrent._, duration._

object Main extends App {
  val repo = """https:\/\/github.com\/([a-zA-Z0-9_\-]+)\/([a-zA-Z0-9_\-]+)""".r

  // val url = "http://www.scala-sbt.org/release/docs/Community-Plugins.html"
  val url = "https://raw.githubusercontent.com/scala/community-builds/2.12.x/configs/project-refs.conf"

  val html = io.Source.fromURL(url).mkString

  val repoList = repo.findAllIn(html).map{case repo(user,name) => user -> name }.toList.distinct

  val client = Github.localConfigClient

  Gigahorse.withHttp { http =>
    val promises = repoList map { case (u,r) =>
      http.run(client(Github.repo(u, r)), Github.asRepo)
    }
    val info = for {
      p <- promises
    } yield Await.result(p, 1.minutes)
    info.sortBy(_.watchers_count).reverse foreach{ repo =>
      println("- " + repo.html_url.get + " " + repo.watchers_count.get)}
  }
}
