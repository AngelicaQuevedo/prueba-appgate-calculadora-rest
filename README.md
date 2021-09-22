# prueba-appgate-calculadora-rest

rest calculator, appgate test

# Used Core Libraries
* spring-boot
* spring-data-mongodb
* redis
* JWT

# operation summary

* http://localhost:8080/authenticate

{
"username": "lian",
"password":"dummy"
}

It is mandatory to send the token as a header token of type Bearer
and the api version
X-API-VERSION : 1

Authorization:
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaWFuIiwiZXhwIjoxNjMyMjAzMjUxLCJpYXQiOjE2MzIyMDIwNTF9.MzePPQV7Lj2Xx6GTMaeM0yyZEeM7CmJnh0IQpfiooGgetjayCrdERa74GBEVWJfBEhelFirdye84_9xiPdwNew

* http://localhost:8080/rest-calculator/execute-operation/sum


* http://localhost:8080/rest-calculator/add-operators/3/true

	available operations
	sum,
	subtraction,
	multiplication,
	division,
	potentiation



1. http://localhost:8080/rest-calculator/add-operators/{operator}/{previousResult}

"operator" indicates an integer to be added to the list.
"previousResult" indicates if you want to add the value of the last result obtained, for any user the result of the last operation is saved whatever the operation is and is overwritten if you operate again.

The value of the last operation will be added whenever "previousResult" is set to true, and the number sent as operator will be added, when it is set to false only the value sent as operator will be added. 


2. endpoint http://localhost:8080/rest-calculator/execute-operation/sum

endpoint to execute the operation sent, the whole list of integers that were held for the user is deleted, leaving only the result of the last operation generated and stored in mongo.

	available operations
	sum,
	subtraction,
	multiplication,
	division,
	potentiatio

# Improvements to be implemented

* Declare variables with more appropriate names. 

* Generate an atomic code, and separate some functionalities that can be reusable and avoid code repetition.

* For the result of the operations an integer is returned, so the power operation in its maximum value returns the maximum possible value for an integer value.

* We are trying to apply a domain-based architecture but some of the objects are included in wrong domains.

* It is necessary to take into account the principle of dependency inversion especially to respect the domain-based architecture, additionally in many classes it depends on concretions.

* Only a factory pattern is applied for the creation of the operations, it is possible to use a strategy pattern to minimize and make more efficient the logic inside the computation controller.

* The generation of unit tests either with mock MVC, mockito and junit. 

