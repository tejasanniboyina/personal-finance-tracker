package com.teja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {


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

           int rowsAffected = statement.executeUpdate();
           if(rowsAffected > 0){
               System.out.println("Expense added successfully!");
           }
        } catch(SQLException e){
        e.printStackTrace();
        }

    }

    List<Expense> viewExpense() {
        List<Expense> expensesList = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
       try(
               Connection connection = DatabaseConnection.getConnection();
               PreparedStatement statement = connection.prepareStatement(sql);
               ){
           ResultSet resultSet = statement.executeQuery();
           while(resultSet.next()){
              Expense expense = new Expense(
                      resultSet.getDouble("amount"),
               resultSet.getString("category"),
               resultSet.getString("description"),
               resultSet.getDate("date").toLocalDate()
               );
               expensesList.add(expense);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return expensesList;

    }

    void deleteExpense(int id) {

         final String sql = "DELETE FROM expenses WHERE id = ?";

         try(
                 Connection connection =  DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ){

             statement.setInt(1,id);
             int rowsAffected = statement.executeUpdate();

             if(rowsAffected>0){
                 System.out.println("Expense Deleted Successfully!!");
             }else{
                 System.out.println("ID not found!!");
             }
         }catch(SQLException e){
             e.printStackTrace();

         }

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
