CREATE TABLE customer
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    created    TIMESTAMP NOT NULL,
    modified   TIMESTAMP NOT NULL,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL
);

