package exercise;

import exercise.csvreader.CsvReader;
import exercise.csvreader.CsvReaderImpl;
import exercise.table.model.Table;
import innerjoin.InnerJoin;

import java.util.List;
import java.util.Map;

/**
 * Demonstration of SQL-like operations implementation
 * Shows CSV reading, ORDER BY DESC, and INNER JOIN functionality
 */
public class SqlOperationsDemo {

	public static void main(String[] args) {
		CsvReader csvReader = new CsvReaderImpl();

		System.out.println("=== SQL Operations Demo ===\n");

		// 1. Read CSV files
		System.out.println("1. Reading CSV files...");
		Table users = csvReader.readCsvFile("src/main/resources/users.csv");
		Table purchases = csvReader.readCsvFile("src/main/resources/purchases.csv");

		System.out.println("Users table:");
		printTable(users);

		System.out.println("\nPurchases table:");
		printTable(purchases);

		// 2. ORDER BY DESC
		System.out.println("\n2. ORDER BY DESC on users by USER_ID:");
		Table sortedUsers = users.orderByDesc("USER_ID");
		printTable(sortedUsers);

		System.out.println("\nORDER BY DESC on purchases by AD_ID:");
		Table sortedPurchases = purchases.orderByDesc("AD_ID");
		printTable(sortedPurchases);

		// 3. INNER JOIN
		System.out.println("\n3. INNER JOIN purchases and users on USER_ID:");
		InnerJoin innerJoin = new InnerJoin();
		Table joinedTable = innerJoin.join(purchases, users, "USER_ID", "USER_ID");
		printTable(joinedTable);

		System.out.println("\n=== Demo Complete ===");
	}

	private static void printTable(Table table) {
		List<String> columns = table.getColumnNames();
		List<Map<String, String>> rows = table.getData();

		// Print header
		System.out.println(String.join(" | ", columns));
		System.out.println("-".repeat(columns.size() * 15));

		// Print rows
		for (Map<String, String> row : rows) {
			StringBuilder line = new StringBuilder();
			for (int i = 0; i < columns.size(); i++) {
				if (i > 0) line.append(" | ");
				line.append(row.get(columns.get(i)));
			}
			System.out.println(line);
		}
		System.out.println("Total rows: " + rows.size());
	}
}
