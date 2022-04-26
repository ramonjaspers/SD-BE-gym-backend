# SD-BE-gym-backend
### some requests
GET http://localhost:8080/activities/

POST http://localhost:8080/activities/

GET http://localhost:8080/activities/{{activityName}}

PUT http://localhost:8080/activities/{{activityName}}

DELETE http://localhost:8080/activities/{{activityName}}

POST http://localhost:8080/authenticate/

GET http://localhost:8080/employees/

GET http://localhost:8080/employees/{{username}}

PUT http://localhost:8080/employees/{{username}}

POST http://localhost:8080/employees/{{username}}

DELETE http://localhost:8080/employees/{{username}}

PATCH http://localhost:8080/employees/{{username}}/salary

GET http://localhost:8080/exerciseMuscles/

POST http://localhost:8080/exerciseMuscles/

GET http://localhost:8080/exerciseMuscles/activity/{{activityName}}

DELETE http://localhost:8080/exerciseMuscles/activity/{{activityName}}

GET http://localhost:8080/exerciseMuscles/{{id}}

PATCH http://localhost:8080/exerciseMuscles/{{id}}

DELETE http://localhost:8080/exerciseMuscles/{{id}}

GET http://localhost:8080/facilities/

POST http://localhost:8080/facilities/

GET http://localhost:8080/facilities/membership/{{membership}}

GET http://localhost:8080/facilities/{{id}}

PUT http://localhost:8080/facilities/{{id}}

DELETE http://localhost:8080/facilities/{{id}}

GET http://localhost:8080/memberships/

POST http://localhost:8080/memberships/

GET http://localhost:8080/memberships/{{name}}

PUT http://localhost:8080/memberships/{{name}}

DELETE http://localhost:8080/memberships/{{name}}

GET http://localhost:8080/persons/

POST http://localhost:8080/persons/

GET http://localhost:8080/persons/{{username}}

PUT http://localhost:8080/persons/{{username}}

DELETE http://localhost:8080/persons/{{username}}

GET http://localhost:8080/persons/{{username}}/authorities

POST http://localhost:8080/persons/{{username}}/authorities

DELETE http://localhost:8080/persons/{{username}}/authorities/{{authority}}

GET http://localhost:8080/persons/{{username}}/image

POST http://localhost:8080/persons/{{username}}/image

DELETE http://localhost:8080/persons/{{username}}/image

PATCH http://localhost:8080/persons/{{username}}/password

GET http://localhost:8080/subscriptions/

GET http://localhost:8080/subscriptions/{{username}}

PUT http://localhost:8080/subscriptions/{{username}}

POST http://localhost:8080/subscriptions/{{username}}

DELETE http://localhost:8080/subscriptions/{{username}}


