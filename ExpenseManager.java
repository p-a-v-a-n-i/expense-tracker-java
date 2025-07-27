import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses;
    private static final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(Expense e) {
        expenses.add(e);
        saveExpenses();
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense e : expenses) {
                System.out.println(e);
            }
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveExpenses();
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                pw.println(e.getDate() + ";" + e.getDescription() + ";" + e.getAmount());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    private void loadExpenses() {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(";");
                if (parts.length == 3) {
                    expenses.add(new Expense(parts[1], Double.parseDouble(parts[2]), parts[0]));
                }
            }
        } catch (FileNotFoundException e) {
            // File will be created on save
        }
    }
}
