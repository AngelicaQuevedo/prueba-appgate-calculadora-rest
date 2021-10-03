# prueba-appgate-calculadora-rest

rest calculator, appgate test

# general architecture diagram

![alt text](https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest/blob/develop/src/main/resources/Diagrama%20Arquitectura.png?raw=true)

# CI/CD strategy diagram 

![alt text](https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest/blob/develop/src/main/resources/CI-CD.png?raw=true)




# Used Core Libraries
* spring-boot
* spring-data-mongodb
* redis
* JWT

# operation summary

1. Authentication endpoint, generates a token based on the username and password sent in the request body, in the following format:


 * http://localhost:8080/authenticate

{
"username": "lian",
"password":"dummy"
}

It is mandatory to send the token in each request as a header (token of type Bearer) and the api version:
Headers: 

X-API-VERSION : 1

Authorization:
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaWFuIiwiZXhwIjoxNjMyMjAzMjUxLCJpYXQiOjE2MzIyMDIwNTF9.MzePPQV7Lj2Xx6GTMaeM0yyZEeM7CmJnh0IQpfiooGgetjayCrdERa74GBEVWJfBEhelFirdye84_9xiPdwNew

###### The users to generate the token are : 
* angelica
* lian
* maria
* pricila
* nita
* gohan
* roshi 

password : dummy

![alt text](https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest/blob/develop/src/main/resources/auth.png?raw=true)

2. Adding operator endpoint
"operator" indicates an integer to be added to the list. "previousResult" indicates if you want to add the value of the last result obtained, for any user the result of the last operation is saved whatever the operation is and is overwritten if you operate again. !whenever true is sent, the last result is added to the list!:
* http://localhost:8080/rest-calculator/add-operators/{operator}/{previousResult}
* http://localhost:8080/rest-calculator/add-operators/3/true

![alt text](https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest/blob/develop/src/main/resources/addingoperator.png?raw=true)

3. Execute Operation endpoint
Here the operation sent as request param is executed with the list of elements previously added. The whole list of integers that were held for the user is deleted, leaving only the result of the last operation generated and stored in mongo,  
* endpoint http://localhost:8080/rest-calculator/execute-operation/sum

	###### Available operations: 
	
	* sum,
	* subtraction,
	* multiplication,
	* division,
	* potentiation

![alt text](https://github.com/AngelicaQuevedo/prueba-appgate-calculadora-rest/blob/develop/src/main/resources/operation.png?raw=true)

# Improvements to be implemented

* Declare variables with more appropriate names. 

* Generate an atomic code, and separate some functionalities that can be reusable and avoid code repetition.

* For the result of the operations an integer is returned, so the power operation in its maximum value returns the maximum possible value for an integer value.

* We are trying to apply a domain-based architecture but some of the objects are included in wrong domains.

* It is necessary to take into account the principle of dependency inversion especially to respect the domain-based architecture, additionally in many classes it depends on concretions.

* Only a factory pattern is applied for the creation of the operations, it is possible to use a strategy pattern to minimize and make more efficient the logic inside the computation controller.

* The generation of unit tests either with mock MVC, mockito and junit. 


# How to upload the project 

I had a last minute problem with the communication with redis in the compose, I could not finish this part.

1. mvn clean install , on the main path of the project
2. sudo docker run -p 6379:6379 redis
3. sudo docker run -p 27017:27017 mongo
4. Execute the following command in the root path java -jar target/calculadora-rest-0.0.1-SNAPSHOT.jar





