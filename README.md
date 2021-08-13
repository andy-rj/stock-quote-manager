# Read Me First

To run in docker container, open terminal in the root directory and run 

	docker-compose build

then run 

	docker-compose up

to stop services:

	docker-compose stop
	
all services should start :D

to build the project run 

./mvnw package

ps: to run mvnw package with tests, the mysql container must be running
(run "docker-compose start mysql" before docker-compose build)
