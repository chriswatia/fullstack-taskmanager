# Project and Task Management Application
This is a simple web-based application for managing projects and tasks. It allows users to create projects, add tasks to projects, view tasks, and update their status. The backend is built with Spring Boot, while the frontend is created using React.

## Features
*   Create Projects: Users can create new projects with a name and description.
*   Create Tasks: Tasks can be created for a specific project, with a title, description, status, and due date.
*   View Tasks: View the list of tasks associated with each project.
*   Manage Task Status: Tasks can have different statuses (TO_DO, IN_PROGRESS, DONE).

## Technologies Used
### Frontend:
*   React
*   Bootstrap (for styling)
*   Axios (for HTTP requests)

### Backend:
*   Spring Boot (REST API)
*   H2 (Database)

## Installation
### Frontend
Clone this repository
```bash
git clone https://github.com/chriswatia/fullstack-taskmanager.git
``` 

`cd frontend`
#### Install dependencies:
```bash
npm install
``` 

##### Run the development server:

```bash
npm start
```
The frontend will be available at http://localhost:3000.

### Backend
`cd taskmanager`
Install dependencies:
```bash
mvn clean install
```

Run the Spring Boot application:
```bash
mvn spring-boot:run
```
The backend will be available at http://localhost:8080/api/v1


## Endpoints
### Projects
*   GET /api/v1/projects: Get a list of all projects.
*   POST /api/v1/projects: Create a new project.
*   GET /api/v1/projects/{id}: Get a specific project by ID.

Tasks
*   GET /api/v1/tasks/projects/{projectId}: Get tasks for a specific project.
*   POST /api/v1/tasks/projects/{projectId}: Create a new task for a project.
*   PUT /api/v1/tasks/{id}: Update an existing task.
*   DELETE /api/v1/tasks/{id}: Delete a task.