package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.table.model.Table;

class OrderByDescTest {

	private Table table;

	@BeforeEach
	void setUp() {
		table = new Table.Builder()
				.withColumnNames(Arrays.asList("id", "name", "age"))
				.addRow(List.of(
						Map.of("id", "1", "name", "Alice", "age", "25"),
						Map.of("id", "2", "name", "Bob", "age", "30"),
						Map.of("id", "3", "name", "Charlie", "age", "20"),
						Map.of("id", "4", "name", "David", "age", "35")))
				.build();
	}

	@Test
	void testOrderByDescAge() {
		Table sortedTable = table.orderByDesc("age");
		List<Map<String, String>> rows = sortedTable.getData();

		assertEquals(4, rows.size());
		assertEquals("David", rows.get(0).get("name"));
		assertEquals("35", rows.get(0).get("age"));
		assertEquals("Bob", rows.get(1).get("name"));
		assertEquals("30", rows.get(1).get("age"));
		assertEquals("Alice", rows.get(2).get("name"));
		assertEquals("25", rows.get(2).get("age"));
		assertEquals("Charlie", rows.get(3).get("name"));
		assertEquals("20", rows.get(3).get("age"));
	}

	@Test
	void testOrderByDescName() {
		Table sortedTable = table.orderByDesc("name");
		List<Map<String, String>> rows = sortedTable.getData();

		assertEquals(4, rows.size());
		assertEquals("David", rows.get(0).get("name"));
		assertEquals("Charlie", rows.get(1).get("name"));
		assertEquals("Bob", rows.get(2).get("name"));
		assertEquals("Alice", rows.get(3).get("name"));
	}

	@Test
	void testOrderByDescId() {
		Table sortedTable = table.orderByDesc("id");
		List<Map<String, String>> rows = sortedTable.getData();

		assertEquals(4, rows.size());
		assertEquals("4", rows.get(0).get("id"));
		assertEquals("3", rows.get(1).get("id"));
		assertEquals("2", rows.get(2).get("id"));
		assertEquals("1", rows.get(3).get("id"));
	}
}
