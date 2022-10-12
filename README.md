
# Spring Boot Student Management Project

A POC on Spring Boot, that performs CRUD Operations






## Installation

1. Maven
2. JDK
3. IDE such as Eclipse IDE, Spring Tool Suite, IntelliJ, Vstudio
4. MySql

    
## API Reference

#### Get all Students

```http
  GET /api/student/view
```

#### Get a particular student using student id

```http
  GET /api/student/view/{studentId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of student to fetch |

#### Add a new student

```http
  POST /api/student/create
```

#### Update information of existing Student using studentId

```http
  PUT /api/student/update/{studentId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of student to update |

#### Delete Student information using studentId

```http
  DELETE /api/student/delete/{studentId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of student to delete |






## Features

- CRUD Operations on Student Management
- Unit testing for all the CRUD operations has been done

