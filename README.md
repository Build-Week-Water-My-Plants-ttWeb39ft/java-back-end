# Deployed API
https://water-my-plants-tt39.herokuapp.com/

# Java -> JS template

For an example of accessing this application with a JavaScript Front End see the repository (https://github.com/LambdaSchool/java-js-front-end.git)


## Basic Data Structure


## End-points:

---

### USERS

Users need to register and login to get access to their plant interface.

* /api/createnewuser (POST)

    an expample of the request body:
    ```
        {
        "username": "testuser",
        "password": "test123",
        "firstName": "test",
        "lastName": "user",
        "primaryemail": "test@test.com",
        "phone": "902-902902",
        }
    ```

    username, password, email are **required.**

    firstName, LastName, and phone are **optional.**

* /api/login (POST)

    an expample of the login body:
    ```
        {
        "username": "test",
        "password": "test123",
        }
    ```
    API returns a token as well as the user's id.

* /api/users (GET)

    Return a list of users.

    User needs to be an admin and logged in.
    
    
* /api/users/:id (GET)

    Get the user info with certain id.

    User needs to be logged in.
    
    **Able to access individual user's plant collection through this end-point**
    
    
 * /api/users/:id (PUT)

    update the user info with certain id.

    User needs to be logged in.
    

* /api/users/:id (DELETE)

    Delete the user with certain id.

    User needs to be an admin and logged in.
    
* /api/getuserinfo (GET)

    Gets record of current user that is logged in.
    

### Plants

All the plants end points require a token to get access.

* /api/plants (GET)

    Returns a list of all plants in database
    
    User needs to be an admin and logged in.
    
    
* api/plant/:id (GET / PUT / DELETE)

    get update or delete a task with task id.
    
    User needs to be logged in
    

* api/plants (POST)

    add a new plant

    an expample of the request:
    ```
        {
        "nickname": "test plant",
        "species": "Unknown",
        "frequency": "Biweekly",
        "days": "Monday, Wednesday, Friday",
        "image": "image url goes here",
        "datePlanted": "11/17/2020",
        "careinstructions": "Place in sunlight",
        "user": "3",
        }
    ```
    nickname, species, frequency, and user are **required**

    days, image, description, datePlanted, and careInstructions is **optional**
    
    User needs to be logged in, "user" will link plant to user via user's id.

