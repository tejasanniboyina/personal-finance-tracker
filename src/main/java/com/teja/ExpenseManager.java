package com.teja;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expensesList = new ArrayList<>();

    private int nextId  = 1;

    //ADD EXPENSE
    void addExpense(Expense expense) {

        String sql = "INSERT INTO expenses(amount,category,description,date) values(?,?,?,?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getCategory());
            statement.setString(3, expense.getDescription());
            statement.setDate(4, Date.valueOf(expense.getDate()));

            statement.executeUpdate();
        } catch(SQLException e){
        e.printStackTrace();
        }

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

    boolean updateExpense(Expense updatedExpense){
        for(Expense expense:expensesList){
            if(updatedExpense.getId()==expense.getId()){
                expense.setAmount(updatedExpense.getAmount());
                expense.setCategory(updatedExpense.getCategory());
                expense.setDescription(updatedExpense.getDescription());
                expense.setDate(updatedExpense.getDate());
                return true;
            }

        }
        return false;
    }
}
