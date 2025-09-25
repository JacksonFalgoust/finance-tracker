## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## TO DO
1. Change to MySQL
2. Build frontend
3. Add different users
4. Swicth to flyway migration for premade categories

# Front end features
1. is recuring button when creating a transaction to switch between transaction entity and recurring transaction entity

# Tables to add
1. Budget - set spending limits (id, category_id, amount_limit, month)

2. Goal - set goals for future saving (id, goal_name, target_amount, current_amount, deadline)

3. Users - have multiple users (id, username, email, password)
    - use user_id foreign key


🔹 1. Features to Add

Budgets

Add a Budget entity (category + limit + period).

Track how much you’ve spent vs. the limit.

Recurring Transactions

Allow automatic monthly bills (rent, utilities, subscriptions).

Reports / Analytics

Endpoints for “total expenses by category”, “monthly income vs. expenses”, etc.

Use DTOs instead of exposing entities directly.

User Authentication

Add Spring Security with JWT (so multiple users can have their own accounts/categories).

Exporting Data

CSV or Excel download for transactions.

Search & Filters

REST endpoints with query params (e.g. GET /transactions?from=2025-01-01&to=2025-01-31&category=Groceries).

🔹 2. Technical Improvements

DTOs + Mappers

Don’t return entity objects directly from controllers.

Use DTOs to control JSON structure.

Validation

Add @Valid and @NotNull, @Positive annotations to your request DTOs.

Global Exception Handling

@ControllerAdvice + @ExceptionHandler to send clean error messages.

Logging

Use SLF4J with proper log levels instead of just System.out.println.

Tests

Unit tests for services.

Integration tests with MockMvc for controllers.

🔹 3. Deployment Readiness

Profiles

Use application-dev.properties (H2) and application-prod.properties (MySQL).

Docker (containerize app so any machine can run it without problems)

Create a docker-compose.yml with your Spring Boot app + MySQL container.

Database Migration

Add Flyway or Liquibase instead of relying on ddl-auto.

🔹 4. Optional Nice-to-Haves

Frontend

Hook it up to React, Angular, or Vue.

Or keep it simple with Thymeleaf templates.

Charts / Visualization

API endpoints that feed chart data (Pie chart for expenses by category, line chart for monthly cash flow).

Notifications

Email or push notification when you overspend a budget.

⚡ In short:
Right now you have a basic CRUD finance tracker.
Next steps are:

Add budgets, recurring transactions, and reports (feature depth).

Add validation, DTOs, exception handling, logging (code quality).

Add security, profiles, migrations, Docker (deployment readiness).