package config

case class DatabaseConfiguration(
    driver: String,
    url: String,
    user: String,
    password: String,
    migrationsTable: String,
    migrationsLocations: List[String]
)
