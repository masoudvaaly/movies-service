## Features

Fetch movies details for further use from 3rd party APIs

Authentication handling

Add User preferences

<br/>

## Purpose

The movie service suppose to fetch movies details, 
gather user favorite filters and find matched movies
and notify users the movie showtime on cinemas

<br/>

## Installation
to run this service first you should run a postgres database
```bash
docker run --name moviesdb-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=moviesdb \
  -d postgres
```
<br/>