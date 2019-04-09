import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

  sealed trait AwsVersions {
    val namespace: String
    val version: String
  }

  object Aws1 extends AwsVersions {
    val namespace = "com.amazonaws"
    val version = "1.11.426"
  }
  object Aws2 extends AwsVersions {
    val namespace = "software.amazon.awssdk"
    val version = "2.4.11"
  }

  def aws(awsVersion: AwsVersions, service: String) = {
   val packageName = awsVersion match {
     case Aws1 => s"aws-java-sdk-$service"
     case Aws2 => service
   }

   awsVersion.namespace % packageName % awsVersion.version exclude("commons-logging", "commons-logging")
  }

  val monocleVersion = "1.5.0-cats"
  val monocle = Seq(
    "com.github.julien-truffaut"  %%  "monocle-core" % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-law" % monocleVersion % "test"
  )
  val catsCore = "org.typelevel" %% "cats-core" % "1.0.1"
  val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.14.0"
  val pegDown = "org.pegdown" % "pegdown" % "1.4.2"
  val discipline = "org.typelevel" %% "discipline" % "0.10.0"
}