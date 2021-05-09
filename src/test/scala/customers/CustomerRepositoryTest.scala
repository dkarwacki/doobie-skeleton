package customers

import cats.effect.IO
import doobie.Transactor
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import utils.{DatabaseTest, Generators}

class CustomerRepositoryTest
    extends AnyWordSpec
    with Matchers
    with DatabaseTest {

  "A CustomerRepository" can {
    "create customer which" should {
      "return created customer" in new CustomerFixture(transactor) {
        //when
        val created = repository.create(customer).unsafeRunSync()

        //then
        created.id.isEmpty shouldEqual false
        created.firstName shouldEqual customer.firstName
        created.lastName shouldEqual customer.lastName
        created.created shouldEqual customer.created
        created.modified shouldEqual customer.modified
      }
    }

    "find customer by id which" should {
      "return found customer" in new CustomerFixture(transactor) {
        //given
        val created = repository.create(customer).unsafeRunSync()

        //when
        val result = repository.findById(created.id.get).unsafeRunSync()

        //then
        result.isDefined shouldEqual true
        result.get.firstName shouldEqual customer.firstName
        result.get.lastName shouldEqual customer.lastName
        result.get.created.roundedToSeconds() shouldEqual customer.created
          .roundedToSeconds()
      }

      "return none if customer cannot be found" in new CustomerFixture(
        transactor
      ) {
        //when
        val result = repository.findById(CustomerId(0)).unsafeRunSync()

        //then
        result.isDefined shouldEqual false
      }
    }
  }
}

class CustomerFixture(transactor: Transactor[IO]) extends Generators {
  val customer = customerGen.one()

  val repository = new CustomerRepositoryImpl(transactor)
}
