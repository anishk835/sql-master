package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import exercise.csvreader.CsvReader;
import exercise.csvreader.CsvReaderImpl;
import exercise.io.FileScanner;
import exercise.io.FileScannerImpl;
import exercise.model.Table;
import exercise.table.TableList;
import exercise.table.TableListImpl;
import exercise.table.sort.SortTable;
import exercise.table.sort.SortTableImpl;

public class Main {

	private static final String SRC_DIR = "/Users/anishkumar/Downloads/coding-challenge-sql-master/src/main/resources";
	private static final String USER = "users.csv";
	private static final String PURCHASES = "purchases.csv";

	public static void main(String[] args) {
		// get list of CSV files.
		FileScanner fileScanner = new FileScannerImpl();
		List<String> files = fileScanner.files(SRC_DIR);

		CsvReader csvReader = new CsvReaderImpl();
		String[] table = { USER, PURCHASES };
		TableList tables = new TableListImpl(csvReader);
		List<Table> csvToTables = tables.csvToTables(files, table.length);

		System.out.println(csvToTables);
		Table finalTable = mergeColumns(csvToTables.get(1), csvToTables.get(0));

		// sort on purchase ID
		SortTable sortTable = new SortTableImpl();
		Table sortedTableById = sortTable.sortTable(finalTable, 0);
		List<List<String>> sortOnPurchaseId = finalTable.getData().stream()
				.sorted(Comparator.comparing((List<String> e) -> e.get(0)).reversed()).collect(Collectors.toList());

		System.out.println(sortedTableById);
		System.out.println("\n");
		System.out.println(sortOnPurchaseId);
		System.out.println("\n");
		System.out.println(finalTable.getData());
	}

	private static Table mergeColumns(Table user, Table purchase) {
		// final table with inner join
		Table finalTable = new Table();
		List<String> finalCol = new ArrayList<>();
		List<List<String>> finalData = new ArrayList<>();
		finalTable.setColumns(finalCol);
		finalTable.setData(finalData);

		// final result merge two tables
		List<String> userCol = user.getColumns();
		List<String> purchaseCol = purchase.getColumns();

		finalCol.addAll(purchaseCol.subList(0, userCol.size() - 1));
		finalCol.addAll(userCol.subList(1, userCol.size()));

		List<List<String>> userData = user.getData();
		List<List<String>> purchasedata = purchase.getData();
		for (List<String> pdata : purchasedata) {

			List<String> dataSet = new ArrayList<>();
			int lastIndex = pdata.size() - 1;
			List<String> subList = pdata.subList(0, lastIndex);
			dataSet.addAll(subList);
			String userId = pdata.get(lastIndex);

			for (List<String> uData : userData) {
				if (uData.get(0).equals(userId)) {
					dataSet.addAll(uData.subList(1, uData.size()));
				}
			}
			finalData.add(dataSet);
		}
		return finalTable;
	}
}
