package customers

import cats.effect.IO
import cats.implicits._
import doobie._
import doobie.implicits._

trait CustomerRepository {
  def create(customer: Customer): IO[Customer]
  def findById(id: CustomerId): IO[Option[Customer]]
}

class CustomerRepositoryImpl(transactor: Transactor[IO])
    extends CustomerRepository {
  override def create(customer: Customer): IO[Customer] = {
    CustomerQueries
      .createQuery(customer)
      .withUniqueGeneratedKeys[CustomerId]("id")
      .transact(transactor)
      .map(id => customer.copy(id = id.some))
  }

  override def findById(id: CustomerId): IO[Option[Customer]] = {
    CustomerQueries
      .findByIdQuery(id)
      .option
      .transact(transactor)
  }
}
