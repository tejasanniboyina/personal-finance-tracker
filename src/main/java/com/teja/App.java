package com.teja;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class App {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ExpenseManager manager = new ExpenseManager();

        while (true) {
            System.out.println("Please select an option.");
            System.out.println("1.Add Expense");
            System.out.println("2.View Expenses");
            System.out.println("3.Delete Expense");
            System.out.println("4.Total Expenses");
            System.out.println("5.Search Category");
            System.out.println("6.Exit");

            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    manager.addExpense(enterExpenses());
                    break;
                case 2:
                    manager.viewExpense();
                    break;
                case 3:
                    System.out.print("Enter the ID of Expense to be Deleted: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (manager.deleteExpense(id)) {
                        System.out.println("Expense with id:" + id + " has been deleted successfully.");
                    } else {
                        System.out.println("Expense with ID: " + id + " is not found.");
                    }
                    System.out.println("The remaining Expenses are : ");
                    manager.viewExpense();
                    break;
                case 4:
                    double total = manager.calculateTotalExpenses();
                    System.out.println("The Total Expenses are: " + total);
                    break;
                case 5:
                    System.out.println("Enter Category:");
                    scanner.nextLine();
                    String category = scanner.nextLine();
                    List<Expense> result = manager.searchByCategory(category);
                    if (result.isEmpty()) {
                        System.out.println("No Matches Found...");

                    } else {
                        for (Expense expense : result) {
                            System.out.println(expense);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Thanks for using Personal Finance Tracker!!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }


    }


    //for case 1:
    static Expense enterExpenses() {
        System.out.print("Enter ID:   ");
        int id = scanner.nextInt();

        System.out.print("Enter Amount:   ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Category:   ");
        String category = scanner.nextLine();

        System.out.print("Enter Description:  ");
        String description = scanner.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD):  ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput);

        Expense expense = new Expense(id, amount, category, description, date);

        return expense;
    }
}
