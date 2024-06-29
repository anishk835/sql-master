package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.table.model.Table;
import innerjoin.InnerJoin;

class InnerJoinTest {

	private Table leftTable;
	private Table rightTable;
	private InnerJoin innerJoin;

	@BeforeEach
	void setUp() {
		leftTable = new Table.Builder().withColumnNames(Arrays.asList("user_id", "product"))
				.addRow(List.of(
						Map.of("user_id", "1", "product", "Laptop"),
						Map.of("user_id", "3", "product", "Tablet"), 
						Map.of("user_id", "2", "product", "Phone")))
				.build();

		rightTable = new Table.Builder().withColumnNames(Arrays.asList("id", "name"))
				.addRow(List.of(
						Map.of("id", "1", "name", "Alice"), 
						Map.of("id", "2", "name", "Bob")))
				.build();

		innerJoin = new InnerJoin();
	}
	
	@Test
    void testInnerJoin() {
        Table joinedTable = innerJoin.join(leftTable, rightTable, "user_id", "id");
        List<Map<String, String>> rows = joinedTable.getData();

        assertEquals(2, rows.size());
        assertEquals("Laptop", rows.get(0).get("product"));
        assertEquals("Alice", rows.get(0).get("name"));
        assertEquals("Phone", rows.get(1).get("product"));
        assertEquals("Bob", rows.get(1).get("name"));
    }

}
