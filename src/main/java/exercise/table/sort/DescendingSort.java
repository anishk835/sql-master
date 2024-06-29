package exercise.table.sort;

import java.util.List;
import java.util.Map;

import exercise.table.model.Table;

public class DescendingSort implements Sort {

	@Override
	public Table sort(Table table, String columnName) {
		List<Map<String, String>> sortedRows = table.getData().stream()
                .sorted((row1, row2) -> row2.get(columnName).compareTo(row1.get(columnName)))
                .toList();
		return new Table.Builder()
                .withColumnNames(table.getColumnNames())
                .addRow(sortedRows)
                .build();
	}

}
