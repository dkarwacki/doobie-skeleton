package utils

import cats.effect.{Blocker, ContextShift, IO}
import config.Configuration
import doobie.{ExecutionContexts, Transactor}
import org.scalatest.{BeforeAndAfterAll, Suite}
import pureconfig._
import pureconfig.generic.auto._
import utils.flyway.FlywayMigrations

import scala.concurrent.ExecutionContext

trait DatabaseTest extends BeforeAndAfterAll { self: Suite =>
  implicit val contextShift: ContextShift[IO] =
    IO.contextShift(ExecutionContext.global)

  val config: Configuration = ConfigSource.default.loadOrThrow[Configuration]
  val transactor: Transactor[IO] = {
    Transactor.fromDriverManager[IO](
      config.db.driver,
      config.db.url,
      config.db.user,
      config.db.password,
      Blocker.liftExecutionContext(ExecutionContexts.synchronous)
    )
  }

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    FlywayMigrations.migrate(config.db).void.unsafeRunSync()
  }

  override protected def afterAll(): Unit = {
    super.afterAll()
    FlywayMigrations.clean(config.db).void.unsafeRunSync()
  }
}
