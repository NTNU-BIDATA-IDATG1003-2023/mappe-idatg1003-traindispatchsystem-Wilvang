[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/sT7H9ZJB)
# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = "Johan Fredrik Wilvang"  
STUDENT ID = "107003"

## Project description

This project is a simplified system for dispatching train departures, using a texted based user interface (TUI). The system makes it easier to control the flow of traffic at a train station. The operator at the station are able to easily add new train departures and change the information associated with a train departure. The system handles the fact that all train departures have unique train numbers and will automatically remove train departures upon departure. Tere is also a built-in search functionality to make it easier to navigate to the correct train departure. In order to get an overview of the traffic flow at the station, a train information table and train history has been created. 

## Project structure

The project is structured with Apache Maven. The unit tests are located in the test folder in a corresponding package as the unit classes. The source code is pacaged in the main folder. There are 3 pagaes in the main folder. The edu.ntnu.stud.transport package contains the unit classes _TrainDeparture_ and _TrainStation_. The edu.ntnu.stud.ui package holds all the classes responseble for the user interface. The _Menu_ class is the only class that directly comunicates with the main class. The package edu.ntnu.stud.util contains the class _InputValidator_ and the enum _ErrorResponse_ which are responseble for error handling and gracefull termination.

## Link to repository

[My GitHub Repository](https://github.com/Wilvang?tab=repositories)

## How to run the project

To run the project, you have to open the project in your IDE and execute the main class. The main class is _TrainDispatchApp.java_ wich conatins the main method witch runs the application. The application is a TUI and the only way to interact with it is trought text input. The output to be expected asre messages and menus based on the text input provided. If the text input is not in the correct forat, the application will output an error and promt to try again.

## How to run the tests

The unit tests are handled by Apache Maven. The JUnit5 dependencies are located in the pom.xml file. If you are using Intelij, you can use the Maven plugin. Select the test phase in the Build Lifecycle to run the unit tests. Otherwise, the tests can be run using the following command in the terminal;
```console
mvn test
```

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
