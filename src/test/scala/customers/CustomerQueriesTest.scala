package customers

import doobie.scalatest.IOChecker
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import utils.{DatabaseTest, Generators}

class CustomerQueriesTest
    extends AnyWordSpec
    with Matchers
    with IOChecker
    with DatabaseTest
    with Generators {

  "Customer queries" should {
    "create query" in {
      check(CustomerQueries.createQuery(customerGen.one()))
    }

    "find by id query" in {
      check(CustomerQueries.findByIdQuery(customerIdGen.one()))
    }
  }
}
