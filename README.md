# Coding Challenge - Bike Inventory

Hello and welcome to this coding challenge. In this challenge, you are tasked to build a small micro-service to handle the inventory of a bike repair shop.

## Task
You will be building a micro-service using Spring Boot and Java to handle the inventory of a bike repair shop. 

This service should expose the following REST endpoints:
- POST /bike/new
	- adds a new bike
	- returns the id of this new bike
- GET /bike?id={id}
	- returns the bike with this id 
- GET /bike/all
	- returns a list of all bikes
- DELETE /bike?id={id}
	- deletes the bike with this id from the inventory

A bike should have the following fields:
- Id
- Brand 
- Color
- Size
- Number of gears


Please use the already existing project structure. 

Write clean code.

Test your code with unit tests.

## Additional Notes

This micro-service is just responsible for handling the bikes that are currently in the bike repair shop. Don't add other functionality like billing systems, customer handling, repair tasks or similar. A frontend is also not required.

Please use Spring Boot and Java to solve this challenge.

Feel free to approach us anytime if anything is unclear.

### Useful information:

Spring Boot: https://spring.io/projects/spring-boot

REST Services with Spring: https://spring.io/guides/tutorials/rest/

Lombok: https://projectlombok.org/


