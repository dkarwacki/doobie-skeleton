lazy val catsVersion = "2.3.1"
lazy val doobieVersion = "0.10.0"
lazy val scalaTestVersion = "3.2.2"
lazy val scalaCheckVersion = "1.14.1"
lazy val mockitoVersion = "3.2.2.0"
lazy val newTypeVersion = "0.4.4"
lazy val refinedTypeVersion = "0.9.24"
lazy val pureConfigVersion = "0.15.0"
lazy val flywayVersion = "7.2.0"

lazy val catsDependencies = Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsVersion,
  "org.typelevel" %% "cats-effect-laws" % catsVersion % "test"
)

lazy val doobieDependencies = Seq(
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-hikari" % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion % Test
)

lazy val scalaTestDependencies = Seq(
  "org.scalactic" %% "scalactic" % scalaTestVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)

lazy val scalaCheckDependencies = Seq(
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test"
)

lazy val mockitoDependencies = Seq(
  "org.scalatestplus" %% "mockito-3-4" % mockitoVersion % "test"
)

lazy val loggerDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

lazy val newTypeDependencies = Seq(
  "io.estatico" %% "newtype" % newTypeVersion
)

lazy val refinedTypeDependencies = Seq(
  "eu.timepit" %% "refined" % refinedTypeVersion,
  "eu.timepit" %% "refined-cats" % refinedTypeVersion,
  "eu.timepit" %% "refined-pureconfig" % refinedTypeVersion,
  "eu.timepit" %% "refined-scalacheck" % refinedTypeVersion
)

lazy val pureConfigDependencies = Seq(
  "com.github.pureconfig" %% "pureconfig" % pureConfigVersion
)

lazy val flywayDependencies = Seq(
  "org.flywaydb" % "flyway-core" % flywayVersion
)

lazy val root = (project in file("."))
  .settings(
    name := "doobie-skeleton",
    organization := "com.dkarwacki",
    version := "0.1",
    scalaVersion := "2.13.5",
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies ++=
      catsDependencies
        ++ doobieDependencies
        ++ loggerDependencies
        ++ scalaTestDependencies
        ++ scalaCheckDependencies
        ++ mockitoDependencies
        ++ newTypeDependencies
        ++ refinedTypeDependencies
        ++ pureConfigDependencies
        ++ flywayDependencies
  )
