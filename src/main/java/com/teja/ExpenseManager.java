package com.teja;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expensesList = new ArrayList<>();

    void addExpense(Expense expense) {
        expensesList.add(expense);

    }

    void viewExpense() {
        if (expensesList.isEmpty()) {
            System.out.println("No Expenses Found....");
        } else {
            for (Expense expense : expensesList) {
                System.out.println(expense);
            }
        }

    }

    boolean deleteExpense(int id) {

        for (int i = 0; i < expensesList.size(); i++) {
            if (expensesList.get(i).getId() == id) {
                expensesList.remove(i);
                return true;
            }
        }
        return false;
    }

    double calculateTotalExpenses() {

        double total = 0;
        for (Expense expense1 : expensesList) {
            total += expense1.getAmount();
        }
        return total;
    }

    List<Expense> searchByCategory(String category) {
        List<Expense> matchingExpenses = new ArrayList<>();

        for (Expense expense : expensesList) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                matchingExpenses.add(expense);
            }
        }
        return matchingExpenses;
    }
}
