# Personal Finance Dashboard

This is a JavaFX desktop application that helps users visualize and manage their personal finances. It includes dashboards showing spending distribution, expense evolution over time, and comparisons between income and expenses.

## ✨ Features

- **Dashboard view** with:
  - Pie chart for spending categories
  - Line chart showing evolution of spending over time
  - Bar chart comparing income vs expenses per period
- **ComboBox** to filter data by time period
- Data persisted using **SQLite**
- MVC architecture with DAO pattern

## 🛠 Technologies Used

- Java 11+
- JavaFX
- SQLite (JDBC)
- FXML (JavaFX UI layout)

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 11 or newer
- JavaFX SDK installed (JavaFX is not bundled in Java 11+)
- Maven or an IDE like IntelliJ IDEA or Eclipse
- SQLite (the database is auto-created if it doesn't exist)

### ⚙️ Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/personal-finance-dashboard.git
cd personal-finance-dashboard
```

2. **Import into your IDE**

   - Open as a Maven project
   - Add the JavaFX SDK to your project
   - Set VM options to:
     ```
     --module-path /path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml
     ```

3. **Run the application**

   - From your IDE: run `Main.java`
   - Or from terminal using Maven:

```bash
mvn clean javafx:run
```

---

## 📁 Project Structure

```
src/
├── com.example.demo
│   ├── Main.java
│   ├── controller/
│   │   └── FinanceController.java
│   ├── db/
│   │   ├── Database.java
│   │   ├── ExpenseDAO.java
│   │   └── IncomeDAO.java
│   ├── model/
│   │   ├── Expense.java
│   │   └── Income.java
│   └── resources/
│       └── dashboard.fxml
```

---

## 🗓 Database

- Database file: `database.db` (created automatically)
- Tables:
  - `expense` — stores spending categories and values
  - `income` — stores multiple income types per period

No manual setup required. Tables are created automatically if they don't exist.

---

## 💡 Notes

- You can extend the app with features like user accounts, data import/export, monthly summaries, and more.
- Add CRUD forms to insert new income and expense entries via UI.
- Animate or style the charts with JavaFX CSS.

---

## 🧑‍💻 Author

Built with ❤️ by MaelFT

