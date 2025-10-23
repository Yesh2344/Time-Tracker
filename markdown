# Time Tracker

A simple command-line application for tracking time spent on various tasks.

## Overview

This application allows users to create tasks, start and stop timers for those tasks, and view reports of the time spent on each task.  It's designed to be lightweight and easy to use for personal time management.

## Features

*   **Task Management:** Create, list, and delete tasks.
*   **Time Tracking:** Start and stop timers for specific tasks.  Timestamps are automatically recorded.
*   **Reporting:** Generate reports showing the total time spent on each task and a history of time entries.
*   **Data Persistence:** Time tracking data is saved to a file, so it persists between application runs.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 8 or higher.

### Installation

1.  Clone the repository:
    ```bash
    git clone [repository_url]  # Replace with your repository URL
    cd TimeTracker
    ```

2.  Compile the Java code:
    ```bash
    javac src/*.java
    ```

3. Create `data` folder in root

4.  Run the application:
    ```bash
    java src/Main
    ```

### Usage

The application is driven through command-line arguments. Here are the available commands:

*   **`create <taskName>`:** Creates a new task with the given name.
    *   Example: `java src/Main create "Writing Documentation"`
*   **`start <taskName>`:** Starts the timer for the specified task.  If the task doesn't exist, it will prompt to create it.
    *   Example: `java src/Main start "Writing Documentation"`
*   **`stop <taskName>`:** Stops the timer for the specified task.
    *   Example: `java src/Main stop "Writing Documentation"`
*   **`list`:** Lists all existing tasks.
    *   Example: `java src/Main list`
*   **`report <taskName>`:** Generates a report for the specified task, showing the total time spent.
    *   Example: `java src/Main report "Writing Documentation"`
*   **`allreport`:** Generates a report for all task in data file, showing the total time spent.
    *   Example: `java src/Main allreport`
*   **`delete <taskName>`:** Deletes a task and all its associated time entries.
    *   Example: `java src/Main delete "Writing Documentation"`
*   **`help`:** Displays a list of available commands and their descriptions.
    *   Example: `java src/Main help`

## Data Storage

The application stores time tracking data in a file named `time_entries.dat` within the `data` folder. This file stores task names and start/stop times.

## Error Handling

The application includes error handling to gracefully manage invalid commands, missing tasks, and other potential issues. Error messages are displayed to the user to guide them towards correct usage.

## Contributing

Contributions are welcome!  Please feel free to submit pull requests or report issues on the project's repository.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.

## Author

[Your Name/Organization]