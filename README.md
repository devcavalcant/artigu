<h2 align="center">
<img src="./assets/Banner.svg" />

Artigu

</h2>

- [English](README.md)
- [Portuguese](PORTUGUESE.md)

### About

Artigu is a system for writing articles or reading various articles published by other users in a clean and minimalist way.

This project is part of a test to condense our knowledge of JAVA and SPRING BOOT, where we are trying to understand the basic security of an API, as well as looking for the best solutions among the various possible ways.
### Specifications

#### Versions

- Java: 17
- Spring Boot: 3.2.2
- Project: Maven
- Packaging: Jar

#### Dependencies

- Spring Web
- Spring Data JPA
- Lombok
- Postgresql Driver
- Spring Security

### Installation

1. Clone this repository:

```bash
git clone https://github.com/devcavalcant/artigu
```
2. Install all dependencies with ``maven``

PostgreSQL is required to run this project. Particularly, this project uses docker to instantiate a postgres image.
If using a different method, please modify the ``application.properties`` file.

#### Installing Postgresql in Docker:

With Docker installed, ``execute``:
```bash
docker pull postgres
```

Finally, run postgres:

```bash
docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=docker postgres
```

### User Stories

- As a Writer, I want to register a article in my articles list, so that I can access its data in the future  
- As a Writer, I want to update the data of my articles
- As a Writer, I want to delete a article from my articles list 
- As a Writer, I want to export articles in json format, so that I can save my work for future problems
- As a Reader, I want to access all articles







