import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "ddd-sample"
  val appVersion      = "1.0-SNAPSHOT"

  override def settings = super.settings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings

  aggregate in Test := true

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  ).aggregate(
    application,
    domain,
    infrastructure
  ).dependsOn(
    application,
    domain,
    infrastructure
  )

  lazy val application = play.Project(
    name = "application",
    path = file("modules/application"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  ).dependsOn(
    domain,
    infrastructure
  )

  lazy val domain = Project(
    id = "domain",
    base = file("modules/domain"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  ).dependsOn(
    infrastructure
  )

  lazy val infrastructure = Project(
    id = "infrastructure",
    base = file("modules/infrastructure"),
    settings = Project.defaultSettings ++ com.typesafe.sbtidea.SbtIdeaPlugin.ideaSettings
  )
}
