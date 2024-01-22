# Library Management System

This is a console-based Library Management System implemented in Java as a part of object-oriented programming (OOP) practice. The system facilitates the management of books within a library, covering various aspects from setup to user interaction.

## Table of Contents

- [Overview](#overview)
- [Practice Plan](#practice-plan)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Library Management System is a practice project focused on applying OOP principles to create a functional console application. It includes features such as book management, three-layer architecture, JSON data handling, and user authentication.

## Practice Plan

1. **Project Setup and Environment Configuration:**
   - Ensure safety practices and approval of practice topics.
   - Define development technologies and practice requirements.
   - Configure the development environment: git, scoop, OpenJDK, WSL, IntelliJ IDEA, and relevant plugins.
   - Analyze analogs and define business logic.
   - Task definition based on requirements and domain analysis.
   - Build a conceptual model and identify entities.

2. **Working with JSON Data and Persistence Layer:**
   - Explore the concept of persistence layer in OOP using flat files.
   - Implement serialization and deserialization of object collections.
   - Create the project and set up dependencies (libraries) using Ant.

3. **Three-Layer Architecture in OOP:**
   - Implement the Repository (Data Access Object) and Unit of Work architectural pattern.

4. **Business Logic Layer Coding:**
   - Implement CRUD operations, search, and filtering according to the domain.
   - Implement user authentication, authorization, and registration functions.
   - Validate input and output data.

5. **Presentation Layer Coding:**
   - Code the presentation layer, focusing on terminal interaction.
   - Build a visual user interface based on the terminal.

6. **Code Refactoring Stage and Manual Testing:**
   - Refactor code for improved readability and maintainability.
   - Perform manual testing of the project.

7. **Project Documentation:**
   - Document the project using JavaDoc and Markdown for GitHub.
   - Develop UML class diagrams.
   - Create the theoretical part of the report.

8. **Report Practical Section:**
   - Describe the information structure, functional capabilities, and project structure.
   - Describe manual testing, user interface with usage instructions.
   - Provide conclusions and a list of used sources.
   - Include conceptual model schema, class diagram, and code documentation.

9. **Error Correction in Report and Projects:**
   - Rectify errors in the report and project.

10. **Practice Defense:**
    - Defend the completed practice.

## Requirements

1. OOP principles: polymorphism, encapsulation, inheritance, composition, three-layer architecture.
2. Programming language: Java version 17+. Recommended IDEs: IntelliJ IDEA and NetBeans. Operating systems: Windows and Linux (via WSL).
3. Data storage in flat files. Supported formats: JSON, YAML, CSV, and binary.
4. Data operations: CRUD, search, filters. Validation of input and output data. Handling own and third-party exceptions.
5. User authentication, authorization, and registration functions. Email validation through sending emails.
6. Reporting based on the domain in office file formats.
7. Console application as the user interface.
8. Adherence to coding conventions and OOP principles.
9. Documentation and version control using JavaDoc and Git.

## Getting Started

To get started with the Library Management System, follow these steps:

1. Clone the repository: `git clone https://github.com/sangariusss/library-management-system.git`
2. Navigate to the project directory: `cd library-management-system`
3. Compile the project: `javac -d target/classes src/com/sangarius/oop/library/Main.java`

## Usage

After compiling the project, you can run the application:

```bash
java -classpath target/classes com.sangarius.oop.library.Main
```

Follow the on-screen instructions to interact with the Library Management System.

## Contributing

If you'd like to contribute to the development of the Library Management System, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix: `git checkout -b feature/new-feature`.
3. Make your changes and commit them: `git commit -m "Add new feature"`.
4. Push your changes to your fork: `git push origin feature/new-feature`.
5. Open a pull request.

## License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.
