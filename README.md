# Java backend

## Description

This is a project created for Internet Services Architecture and you can find instructions for everything in the [instructions](instructions/) folder.  
The project is written purely in Java along with Spring Boot framework. It follows Model-View-Controller (MVC) design pattern and is split into multiple microservices.  
The aim of the project is to provide REST API access to 2 types of entities stored in H2 ORM database: Department and Employee.

## Installation and running

Follow these steps to get the project running:

1. Clone the the repository by writing in the CLI: `git clone https://github.com/polemaster/isa.git`.
2. Open these 3 folders with your IDE (e.g. Intellij IDEA) that are inside the _isa_ folder which was just created: _DepartmentService_, _EmployeeService_ and _Gateway_
3. (Optional) In your IDE, select Java 17 as your Java version
4. Run the main file using IDE for each opened project (microservice) - file containing main() function. For example for _DepartmentService_ it is _DepartmentServiceApplication.java_
5. Server should be running.

## Usage

Now you can check if everything is running by sending REST requests to _localhost:8080_ via Postman, browser or some other app. You can find all available http requests in requests.http files in each project (however, it's recommended to use the one from Gateway)
