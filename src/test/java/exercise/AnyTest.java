package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.csvreader.CsvReaderImpl;
import exercise.io.FileScanner;
import exercise.io.FileScannerImpl;
import exercise.model.JoinTable;
import exercise.model.SQLTable;
import exercise.table.TableList;
import exercise.table.TableListImpl;
import exercise.table.merge.MergeTwoTableImpl;
import exercise.table.sort.Order;
import exercise.table.sort.SortTable;
import exercise.table.sort.SortTableImpl;

class AnyTest {

	private static final String COLUMN = "USER_ID";
	private static SQLTable expectedPurchaseOnUserTableResult = new SQLTable();
	private JoinTable[] joinTables = null;
	private List<SQLTable> csvToTables;

	@BeforeEach
	void init() {
		// // @formatter:off
		List<List<String>> purchasesUsersData = List.of(
				List.of("9", "chair-1", "1", "andre", "andre@bar.de"), 
				List.of("7", "table-1", "4", "lydia", "lydia@bar.de"),
				List.of("6", "table-2", "4", "lydia", "lydia@bar.de"), 
				List.of("5", "guitar-2", "3", "swen", "swen@foo.de"),
				List.of("4", "guitar-1", "2", "manuel", "manuel@foo.de"), 
				List.of("3", "car-3", "1", "andre", "andre@bar.de"),
				List.of("2", "car-2", "1", "andre", "andre@bar.de"), 
				List.of("1", "car-1", "1", "andre", "andre@bar.de"));
		// @formatter:on
		expectedPurchaseOnUserTableResult.setColumns(List.of("AD_ID", "TITLE", "USER_ID", "NAME", "EMAIL"));
		expectedPurchaseOnUserTableResult.setData(purchasesUsersData);
		expectedPurchaseOnUserTableResult.setTableNames("PURCHASES_USERS");

		Path path = Paths.get("src", "main", "resources");
		FileScanner fileScanner = new FileScannerImpl();
		List<String> files = fileScanner.files(path.toAbsolutePath().toString());
		TableList tables = new TableListImpl(new CsvReaderImpl());
		csvToTables = tables.csvToTables(files);
	}

	@Test
	void testPurchaseOnUserTableResult() {
		// Create join on first purchase table on user table
		joinTables = new JoinTable[] { new JoinTable().setTables(csvToTables.get(0)).onJoinColumnName(COLUMN),
				new JoinTable().setTables(csvToTables.get(1)).onJoinColumnName(COLUMN) };
		SortTable sortTable = new SortTableImpl();
		SQLTable actualResult = Table.tableBuilder(joinTables, new MergeTwoTableImpl()).innerJoinTwoTable()
				.sortTable("AD_ID", Order.DESC, sortTable).getFinalTable();
		assertEquals(expectedPurchaseOnUserTableResult.getTableNames(), actualResult.getTableNames());
		assertEquals(expectedPurchaseOnUserTableResult.getColumns(), actualResult.getColumns());
		assertEquals(expectedPurchaseOnUserTableResult.getData(), actualResult.getData());
	}

}
