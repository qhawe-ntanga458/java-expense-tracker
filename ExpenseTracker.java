import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExpenseTracker {

private static final Scanner scanner = new Scanner(System.in);
private static final NumberFormat CURRENCY =
NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-ZA"));

public static void main(String[] args) {
System.out.println("=== Monthly Expense Tracker ===");

BigDecimal budget = readMoney("Enter your monthly budget (R): ", false);

System.out.print("How many expenses do you want to record? ");
int numberOfExpenses = readExpenseCount();

List<String> descriptions = new ArrayList<>();
List<BigDecimal> amounts = new ArrayList<>();
BigDecimal totalExpenses = BigDecimal.ZERO;

for (int i = 0; i < numberOfExpenses; i++) {
System.out.println("\nExpense " + (i + 1));

System.out.print("Description: ");
String description = scanner.nextLine().trim();

while (description.isEmpty()) {
System.out.print("Description cannot be empty. Try again: ");
description = scanner.nextLine().trim();
}

BigDecimal amount = readMoney("Amount (R): ", true);

descriptions.add(description);
amounts.add(amount);
totalExpenses = totalExpenses.add(amount);
}

BigDecimal remaining = budget.subtract(totalExpenses);

System.out.println("\n=== Monthly Summary ===");
System.out.println("Monthly budget: " + formatMoney(budget));
System.out.println("\nRecorded expenses:");

for (int i = 0; i < descriptions.size(); i++) {
System.out.println(
(i + 1) + ". " + descriptions.get(i)
+ " - " + formatMoney(amounts.get(i))
);
}

System.out.println("\nTotal expenses: " + formatMoney(totalExpenses));
System.out.println("Money remaining: " + formatMoney(remaining));

if (remaining.signum() < 0) {
System.out.println("Warning: You have exceeded your budget by "
+ formatMoney(remaining.abs()));
} else if (remaining.signum() == 0) {
System.out.println("You have used your entire budget.");
} else {
System.out.println("You are within your budget.");
}

scanner.close();
}

private static BigDecimal readMoney(String prompt, boolean allowZero) {
while (true) {
System.out.print(prompt);
String input = scanner.nextLine().trim();

try {
BigDecimal amount = new BigDecimal(input);

if (amount.scale() > 2 && amount.stripTrailingZeros().scale() > 2) {
System.out.println("Please enter no more than two decimal places.");
continue;
}

if (amount.signum() < 0
|| (!allowZero && amount.signum() == 0)) {
System.out.println(allowZero
? "Amount cannot be negative. Try again."
: "Budget must be greater than zero. Try again.");
continue;
}

return amount;
} catch (NumberFormatException error) {
System.out.println("Invalid amount. Enter a number, e.g. 125.50.");
}
}
}

private static int readExpenseCount() {
while (true) {
String input = scanner.nextLine().trim();

try {
int count = Integer.parseInt(input);

if (count > 0) {
return count;
}

System.out.print("Enter at least one expense: ");
} catch (NumberFormatException error) {
System.out.print("Enter a valid whole number: ");
}
}
}

private static String formatMoney(BigDecimal amount) {
return "R" + amount.toPlainString();
}
}
