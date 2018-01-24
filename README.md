# CodingExerciseByAdnan
Coding Exercise requested by Apex Systems

This is a Spring Boot application to make sure our main application is being accessed by humans and not a by any computers or bot.

Specifications
. Used Spring Boot
. used Gradle for build and dependency management
. Attempted at Test class using Spring Boot but was getting build errors in resolving org.hamcrest package, so I put that on the side for now.

Description:
When GET: http://localhost:8080/ request is made, this application generates a question with random operands challenging the user to submit the answer of the sum.  The maximum  operands in the sum can be 4, and minimum 2.

When POST: http://localhost:8080/ request is made, this application accepts the question, and the suggested answer by the user, and compares the results of summation from question with submitted answer, and responds whether the answer for right or wrong. 

Instructions to download deploy and tets
1. Clone this project in a local workspace
2. In the project directory, run  :
  gradle build

  (Ensure all dependencies are resolved and there are no build errors due to unresolved dependencies)
  
3. Now run the java class file Application.class file located in /CodingExcerciseByAdnan/src/main/java/com/coding/exercise/Application.
   This can be run either from the command line or from IDE.
   
4. Now use Postman or any REST API Test tool, and make following request:
    GET: http://localhost:8080/
    
    Expected Result:
    Something like this:
    Please sum the numbers 6,1,9,1

5. Now make another REST call to thi
    POST: http://localhost:8080/
Request body:
{
	"questionText" : "Please sum the numbers 6,1,9,1",
	"suggestedResult" : 17
}

Request Header:
Content-type: application/json

EXPECTED RESULT:
Either HttpStatus of 200 for HttpStatus.OK
or     HttpStatus of 400 for HttpStatus.BadRequest

