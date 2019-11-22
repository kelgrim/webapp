# webapp
This is a project which I use for learning to create a Rest Service with Spring boot and should probably not be used for anything other than learning purposes. 
There's not really any practical uses for it, though it could be used as a test object for learning how to test rest api's. 

Currently the project contains the following:
- Simple Rest service which can be used to retrieve user and message information from an embedded H2 database
- Unit tests to test the different components of the application using Junit and Mockito
- Cucumber tests to do an integration test on the API using Rest Assured. 

Documentation will follow, for now the below information should get most people started. .
The service can be run as a standalone jar. 
Below are some endpoints to get started with. 
http://localhost:8080/api/v1/user to get a list of users
http://localhost:8080/api/v1/1 to get the details for user with id 1
http://localhost:8080/api/v1/user/inbox to get all messages
http://localhost:8080/api/v1/message/1 to get details for message 1
Operations for GET/POST/PUT/DELETE are supported. 

Any feedback is much appreciated.
