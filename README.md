# blog-backend

## Travis CI Address
https://travis-ci.com/pplmo/blog-backend

## generate image and push it to docker hub 'purplemystic/blog'
```shell script
./gradlew jib
```

## create postgres and blog containers
```shell script
# *************** Solution 1 ***************
# 1. create postgresql 
# 5432 is for postgresql itself
# assign port 8080 for blog's netty service
docker run -d --name pg_db \
    -p 5432:5432 \
    -p 8888:8080 \
    -e 'POSTGRES_USER=blog' \
    -e 'POSTGRES_PASSWORD=blog' \
    -e 'POSTGRES_DB=blog' \
    postgres

# 2. create database
psql blog blog < blog.sql

# 3. create blog
# use 8080 from container db
docker run --rm --name blog --network container:pg_db purplemystic/blog

# *************** Solution 2 ***************
# This solution is not ready: blog cannot access db:5432
docker network create my_br
docker run -d --name pg_db \
    --network my_br \
    -p 5432:5432 \
    -e 'POSTGRES_USER=blog' \
    -e 'POSTGRES_PASSWORD=blog' \
    -e 'POSTGRES_DB=blog' \
    postgres

psql blog blog < blog.sql

docker run --rm --name blog --network my_br -p 8080:8080 purplemystic/blog
```

## backup and restore database
```text
pg_dump -U <user_name> <db_name> > blog.sql
e.g. 
pg_dump -U blog blog > blog.sql
docker container exec pg_db pg_dump -U blog blog > blog.sql

psql -U <user_name> <db_name> < blog.sql
e.g. 
psql blog blog < blog.sql
docker container exec pg_db psql blog blog < blog.sql
```
