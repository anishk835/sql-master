package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.model.SQLTable;
import exercise.table.merge.MergeTableImpl;
import exercise.table.sort.Order;

class AnyTest {
	private static final String SRC_DIR = "/Users/anishkumar/Downloads/coding-challenge-sql-master/src/main/resources";
	private static SQLTable expectedResult = new SQLTable();
	private final Table table = new Table(SRC_DIR, new MergeTableImpl(), Order.DESC);
	
	@BeforeEach
	void init() {
		List<List<String>> data = List.of(
				List.of("9", "chair-1", "andre", "andre@bar.de"), 
				List.of("7", "table-1", "lydia", "lydia@bar.de"),
				List.of("6", "table-2", "lydia", "lydia@bar.de"), 
				List.of("5", "guitar-2", "swen", "swen@foo.de"),
				List.of("4", "guitar-1", "manuel", "manuel@foo.de"), 
				List.of("3", "car-3", "andre", "andre@bar.de"),
				List.of("2", "car-2", "andre", "andre@bar.de"), 
				List.of("1", "car-1", "andre", "andre@bar.de"));
		expectedResult.setColumns(List.of("AD_ID", "TITLE", "NAME", "EMAIL"));
		expectedResult.setData(data);
		expectedResult.setTableNames("USERS_PURCHASES");
	}

	@Test
	void nameAsYouWant() {
		SQLTable actualResult = table.innerJoin();
		assertEquals(expectedResult.getTableNames(), actualResult.getTableNames());
		assertEquals(expectedResult.getColumns(), actualResult.getColumns());
		assertEquals(expectedResult.getData(), actualResult.getData());
	}

}
