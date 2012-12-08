package com.github.xuwei_k.sbt_plugin_ranking

import com.github.xuwei_k.ghscala.GhScala

object Main extends App{

  val repo = """https:\/\/github.com\/([a-zA-Z0-9_\-]+)\/([a-zA-Z0-9_\-]+)""".r
  val url = "http://www.scala-sbt.org/release/docs/Community/Community-Plugins.html"
  val html = io.Source.fromURL(url).mkString
  val repoList = repo.findAllIn(html).map{case repo(user,name) => user -> name }.toList.distinct
  val info = repoList.flatMap{case (u,r) =>
    try{
//      Thread.sleep(1000)
      Option(GhScala.repo(u,r))
    }catch{
      case e =>
        println(u,r)
        e.printStackTrace
      None
    }
  }
  info.sortBy(_.watchers).reverse.foreach{r => println(r.html_url + " " + r.watchers)}

}
