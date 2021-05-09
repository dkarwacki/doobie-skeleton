package utils.flyway

import cats.effect.{IO}
import com.typesafe.scalalogging.LazyLogging
import config.DatabaseConfiguration
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.Location

object FlywayMigrations extends LazyLogging {

  def migrate(config: DatabaseConfiguration): IO[Int] =
    IO {
      logger.info("Running migrations")
      val count = flyway(config).migrate().migrationsExecuted
      logger.info(s"Executed $count migrations")
      count
    }

  def clean(config: DatabaseConfiguration): IO[Int] =
    IO {
      logger.info("Cleaning migrations")
      val count = flyway(config).clean().schemasCleaned.size()
      logger.info(s"$count schemas cleaned")
      count
    }

  private def flyway(config: DatabaseConfiguration): Flyway = {
    Flyway.configure
      .dataSource(
        config.url,
        config.user,
        config.password
      )
      .group(true)
      .outOfOrder(false)
      .table(config.migrationsTable)
      .locations(
        config.migrationsLocations
          .map(new Location(_)): _*
      )
      .baselineOnMigrate(true)
      .load()
  }
}
