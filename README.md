# Public Transport Company Management System

## Project Overview

This project involves the development of a management system for a public transport company. The system is designed to handle ticketing, subscription management, fleet operations, and route tracking. It aims to ensure efficient operations and provide essential tools for both administrators and regular users.

## Key Features

### Ticketing and Subscription Management
- **Ticket Issuance:** Tickets can be issued through automatic distributors and authorized resellers.
- **Subscription Options:** Weekly and monthly subscriptions are available for users with an annual renewable card.
- **Unique Identification:** Every ticket and subscription is assigned a unique code.
- **Usage Tracking:** Tracks the number of tickets and subscriptions issued within a given period, categorized by issuance point.
- **Validation:** Allows rapid validation of subscriptions using the user’s card number.

### Fleet Management
- **Vehicle Types:** The fleet includes trams and buses, each with specific capacity.
- **Service Status:** Tracks whether vehicles are in service or under maintenance.
- **Usage Monitoring:** Maintains records of service and maintenance periods.
- **Ticket Validation:** Invalidates tickets upon validation and tracks the number of validated tickets per vehicle and overall within a specific timeframe.

### Route Management
- **Route Assignment:** Vehicles are assigned to routes defined by departure zones, endpoints, and estimated travel times.
- **Performance Tracking:** Records the number of trips completed and actual travel times.
- **Average Time Calculation:** Administrators can calculate the average actual travel time for specific routes.

## Implementation Details

- **Programming Language:** Java
- **Persistence:** JPA (Java Persistence API) with PostgreSQL for database support.
- **Testing:** A Scanner-based simulation through `main()` to validate the functionality from both user and administrator perspectives.

This system integrates essential functionalities for managing a public transport company effectively, ensuring a seamless experience for users and operational efficiency for administrators.

