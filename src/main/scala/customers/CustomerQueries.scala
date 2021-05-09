package customers

import doobie.implicits._
import doobie.implicits.javatime._

object CustomerQueries {
  def createQuery(customer: Customer): doobie.Update0 =
    sql"""INSERT INTO customer(first_name, last_name, created, modified) VALUES (
         ${customer.firstName},
         ${customer.lastName},
         ${customer.created},
         now()
         )""".update

  def findByIdQuery(id: CustomerId): doobie.Query0[Customer] =
    sql"""SELECT first_name, last_name, created, modified, id FROM customer WHERE id = ${id}"""
      .query[Customer]
}
