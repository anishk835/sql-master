package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import exercise.csvreader.CsvReader;
import exercise.csvreader.CsvReaderImpl;
import exercise.table.model.Table;

class CsvReaderTest {

	private CsvReader csvReader = new CsvReaderImpl();

	@Test
	void testReadUsersFile() {
		String path = "src/main/resources/users.csv";
		Table table = csvReader.readCsvFile(path);

		assertNotNull(table);
		assertEquals(3, table.getColumnNames().size());
		assertEquals("USER_ID", table.getColumnNames().get(0));
		assertEquals("NAME", table.getColumnNames().get(1));
		assertEquals("EMAIL", table.getColumnNames().get(2));
		assertEquals(5, table.getData().size()); // 5 users in the file
	}

	@Test
	void testReadPurchasesFile() {
		String path = "src/main/resources/purchases.csv";
		Table table = csvReader.readCsvFile(path);

		assertNotNull(table);
		assertEquals(3, table.getColumnNames().size());
		assertEquals("AD_ID", table.getColumnNames().get(0));
		assertEquals("TITLE", table.getColumnNames().get(1));
		assertEquals("USER_ID", table.getColumnNames().get(2));
		assertEquals(8, table.getData().size()); // 8 purchases in the file
	}
}
