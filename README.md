# Bike inventory

Deployed [here](https://bikeinventory-intern.herokuapp.com/).

## How to host lokal (on Linux):

* Clone the git repo.
```shell
git clone https://github.com/iinsertNameHere/bike-inventory
```

* Go into the cloned repo.
```shell
cd /bike-inventory
```

* Start the app with maven.
```shell
./mvnw spring-boot:run
```

* Open http://localhost:9000 in your browser.

* You can finde the user Details in:
```
src/main/resources/application.yml
```
---
## DB Manager (on Linux):

#### 1:
Go into the cloned git repo.

#### 2:
```shell
heroku config:get DATABASE_URL | xargs pgweb --url
```
---
## Deploy on heroku (on Linux):

#### 1:
* install heroku cli
* run commands to login and create app and postgresql
```shell
heroku login
heroku create
heroku addons:create heroku-postgresql
```
* configure java 11 by placing the file `system.properties` with the following content
```
java.runtime.version=11 
```
