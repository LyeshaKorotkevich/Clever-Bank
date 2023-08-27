FROM postgres:15-bullseye

LABEL author="Korotkevich Aliaksey"
LABEL description="Postgres Image for Clever-Bank"
LABEL version="1.0"

COPY ./infrastructure/db/*.sql /docker-entrypoint-initdb.d/