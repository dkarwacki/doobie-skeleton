package customers

import java.time.LocalDateTime

case class Customer(
    firstName: String,
    lastName: String,
    created: LocalDateTime,
    modified: Option[LocalDateTime] = None,
    id: Option[CustomerId] = None
)
