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
1. Move everything to a service
2. Make controller
3. Test to make sure everything works
4. Change to MySQL
5. Make preset categories (e.i. other, food, entertainment etc.)
6. Build frontend
7. Add different users

# Tables to add

1. Accounts - tracker different accounts (id, name, type, amount)
    - Transactions include account_id to track what account was used

2. Categories - store categories for transactions in a table (id, name)
    - Reference category by category_id

3. Budget - set spending limits (id, category_id, amount_limit, month)

4. Goal - set goals for future saving (id, goal_name, target_amount, current_amount, deadline)

5. Recurring transactions - rent, subscritptions, etc. Can auto make transactions (id, category_id, amount, frequency, next_due_date)

6. Users - have multiple users (id, username, email, password)
    - use user_id foreign key