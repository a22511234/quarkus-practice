# Learning Java with Quarkus and JPAStreamer


This repository is used for learning Java development using Quarkus and JPAStreamer.

## Prerequisites
- Java 17 or later
- Docker
- [Sakila database](https://dev.mysql.com/doc/sakila/en/) running in a Docker container.

## Database
The application makes use of the MySQL Sakila sample database. To download and run it as a Docker container on port 3306.

```
docker run -d --publish 3306:3306 --name mysqld restsql/mysql-sakila
```
> If you are running on e.g. an M1 Mac, you need to append the flag --platform linux/amd64 after docker run.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```