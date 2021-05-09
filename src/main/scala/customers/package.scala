import cats.Eq
import cats.implicits._
import doobie.util.meta.Meta
import io.estatico.newtype.macros.newtype

package object customers {
  @newtype case class CustomerId(value: Long)

  object CustomerId {
    implicit val eq: Eq[CustomerId] = deriving
    implicit val meta: Meta[CustomerId] = deriving
  }

}
