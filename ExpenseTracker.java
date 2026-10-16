import java.util.Scanner;

public class ExpenseTracker {

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

System.out.println("=== Expense Tracker ===");

System.out.print("Enter your monthly budget (R): ");
double budget = scanner.nextDouble();

System.out.print("Enter expense 1 (R): ");
double expense1 = scanner.nextDouble();

System.out.print("Enter expense 2 (R): ");
double expense2 = scanner.nextDouble();

System.out.print("Enter expense 3 (R): ");
double expense3 = scanner.nextDouble();

double totalExpenses = expense1 + expense2 + expense3;
double remaining = budget - totalExpenses;

System.out.println("\n=== Monthly Summary ===");
System.out.println("Budget: R" + budget);
System.out.println("Total expenses: R" + totalExpenses);
System.out.println("Money remaining: R" + remaining);

scanner.close();
}
}
