# pet-tracking-application

## Overview

The pet-tracking-application is a Spring Boot application designed to provide endpoints for reading pet tracker data and query the saved information.


## Features

- **Save tracker data in DB :**

- **Get data:** 

- **Find all pets outside power saving mode:** 


## Getting Started

### Prerequisites

- Java 11 or later
- Maven
- docker (if used with postgres)

### Configuration

1. Clone the repository:

   ```bash
   git clone https://github.com/armanwj/pet-tracking-application.git
   ```


2. Build the project:

   ```bash
   mvn clean install
   ```

4. Start the application
5. for postgres cong Start local postgresql.
```
cd docker  
docker compose up -d  
```
6. Start the application with profile postgres:

   ```bash
   -Dspring.profiles.active=postgres
   ```


The API will be accessible at `http://localhost:8080`.
The pgadmin will be accesible at 'http://localhost:8888'


## API Endpoints

### Insert a new data

```http
POST /api/pets
```
```curl
curl --header "Content-Type: application/json" \
--request POST \
--data '{
"petType": "CAT",
"trackerType": "CAT_BIG",
"ownerId": 2,
"inZone": false,
"lostTracker": false
}' \
http://localhost:8080/api/pets
```

**Request:**
```json
{
    "petType": "CAT",
    "trackerType": "CAT_BIG",
    "ownerId": 1,
    "inZone": false,
    "lostTracker": false
}
```

### get all pets data

```http
GET /api/pets
```
```curl
curl http://localhost:8080/api/pets 
```


### get pets data by pet type

```http
GET /api/pets/pet-type/{petType}
```
```curl
curl http://localhost:8080/api/pets/pet-type/CAT
```
**Path Variable:**
```
petType = CAT or DOG
```


### get pets count which are outside power saving zone grouped by tracker type and pet type

```http
GET /api//pets/outside-saving-zone
```
```curl
curl http://localhost:8080/api/pets/outside-saving-zone 
```
