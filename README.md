
# **ChatApp README**

## **Overview**

ChatApp is an Android application built using Kotlin, Jetpack Compose, and MVVM architecture. It features a simple chat interface with two pre-defined users, message sectioning based on timestamps. This README outlines the implementation details, assumptions made, app limitations, and potential improvements.


## **Implementation Decisions**

**Technology Stack**
Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
UI Toolkit: Jetpack Compose
Data Storage: Room Database
Design Pattern: Repository Pattern
Compose Libraries Used: Coroutines, LiveData, ViewModel, Material3.

**UI Design**
Compose: Used Scaffold, AppBar, BottomBar, TextField etc...
LazyColumn: Used for efficiently displaying a list of messages.
Shadow Modifier: Applied to top and bottom bars to create a shadow effect.

**Message Sectioning**
Timestamp Condition: A timestamp is shown when there is no previous message or if the time gap between consecutive messages exceeds one hour.
Timestamp Format: {day} {timestamp} (e.g., "Thursday 11:59").

**Simulated Two-Way Messaging**
Users: Pre-populated with "Alice" and "Sarah".
Message Flow: Simulates responses from "Sarah" after "Alice" sends a message and vice versa.


## ***Assumptions***

**Static Users:**

The app is pre-populated with two static users ("Alice" and "Sarah").
No user authentication or dynamic user management is implemented.

**Message Storage:**

Messages are stored locally using Room Database.
No initial message history is provided, but sent messages are stored and simulated replies.

**Timestamp Sectioning:**

Timestamps are displayed if the gap between consecutive messages is more than one hour or if it’s the first message.
The format used is {day} {timestamp}.

**Error Handling:**

Basic error handling is implemented.
The app assumes the database operations are successful.

**UI Simplification:**

The UI is kept minimal with a focus on functionality.

**Network Operations:**

The app operates offline with no network interactions.
Only random reply real-time message syncing is implemented.


## ***App Limitations***

**Single Screen:**

Single screen without complex navigation graphs.

**Static User List:**

No dynamic user management or authentication.

**Limited Error Handling:**

Basic error handling without detailed error messages or retry logic.

**Simple UI Design:**

Minimal UI design without advanced features like complex animations or custom themes.

**No Network Integration:**

No remote data synchronization or real-time updates.

**Limited Testing:**

Lacks comprehensive unit tests, integration tests, and UI tests.


## ***Potential Improvements***

**Dynamic User Management:**

Introduce user authentication and dynamic user management, allowing users to log in, log out, and manage contacts.

**Improved Error Handling:**

Add robust error handling mechanisms, including user-friendly error messages, retry logic, and logging.

**Advanced UI/UX Features:**

Enhance the UI with custom themes, animations, and more advanced layouts. Add features like message reactions and typing indicators.

**Network Integration:**

Integrate network operations to sync messages with a backend server, enabling real-time messaging and data persistence across devices.

**Extended Testing:**

Cover unit tests, integration tests, and UI tests to ensure the app's reliability and maintainability.

**Performance Optimization:**

Optimize the app for performance, ensuring smooth scrolling, quick load times, and efficient memory usage.

**Notifications:**

Implement push notifications to alert user about new messages.