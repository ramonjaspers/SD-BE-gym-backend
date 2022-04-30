# SD-BE-gym-backend
---
## Activities
GET http://localhost:8080/activities/

GET http://localhost:8080/activities/{{activityName}}

POST http://localhost:8080/activities/

DELETE http://localhost:8080/activities/{{activityName}}

## Authentication
POST http://localhost:8080/authenticate/


## Employees
GET http://localhost:8080/employees/

GET http://localhost:8080/employees/{{username}}

PUT http://localhost:8080/employees/{{username}}

POST http://localhost:8080/employees/{{username}}

PATCH http://localhost:8080/employees/{{username}}/salary

DELETE http://localhost:8080/employees/{{username}}


## Exercise muscles
GET http://localhost:8080/exerciseMuscles/

GET http://localhost:8080/exerciseMuscles/activity/{{activityName}}

GET http://localhost:8080/exerciseMuscles/{{id}}

POST http://localhost:8080/exerciseMuscles/

PATCH http://localhost:8080/exerciseMuscles/{{id}}

DELETE http://localhost:8080/exerciseMuscles/activity/{{activityName}}

DELETE http://localhost:8080/exerciseMuscles/{{id}}


## Facilities
GET http://localhost:8080/facilities/

GET http://localhost:8080/facilities/membership/{{membership}}

GET http://localhost:8080/facilities/{{id}}

PUT http://localhost:8080/facilities/{{id}}

POST http://localhost:8080/facilities/

DELETE http://localhost:8080/facilities/{{id}}


## Memberships
GET http://localhost:8080/memberships/

POST http://localhost:8080/memberships/

GET http://localhost:8080/memberships/{{name}}

PUT http://localhost:8080/memberships/{{name}}

DELETE http://localhost:8080/memberships/{{name}}


## Persons
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


## Subscriptions
GET http://localhost:8080/subscriptions/

GET http://localhost:8080/subscriptions/{{username}}

PUT http://localhost:8080/subscriptions/{{username}}

POST http://localhost:8080/subscriptions/{{username}}

DELETE http://localhost:8080/subscriptions/{{username}}


## Workouts
POST http://localhost:8080/workouts/person/{{username}}

POST http://localhost:8080/workouts/{{id}}/activity/{{activity}}

PATCH http://localhost:8080/workouts/{{id}}/newName/{{newName}}

GET http://localhost:8080/workouts/

GET http://localhost:8080/workouts/person/{{username}}

GET http://localhost:8080/workouts/{{id}}

DELETE http://localhost:8080/workouts/{{id}}

DELETE http://localhost:8080/workouts/person/{{username}}

DELETE http://localhost:8080/workouts/{{id}}/activity/{{activity}}
