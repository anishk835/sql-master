package exercise.table.sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exercise.model.SQLTable;

public class SortTableImpl implements SortTable {

	@Override
	public SQLTable sortTableOn(SQLTable table, String column, Order order) {
		SQLTable result = new SQLTable();
		int index = getColumnIndex(table.getColumns(), column);
		result.setColumns(table.getColumns());
		Comparator<List<String>> comparator = getComparator(index, order);
		List<List<String>> data = table.getData().stream().sorted(comparator).collect(Collectors.toList());
		result.setData(data);
		result.setTableNames(table.getTableNames());
		return result;
	}

	private int getColumnIndex(List<String> columns, String column) {
		return IntStream.range(0, columns.size()).filter(i -> columns.get(i).equals(column)).findFirst().getAsInt();
	}

	private Comparator<List<String>> getComparator(int index, Order order) {
		Comparator<List<String>> comparator;
		if (order.equals(Order.ASC)) {
			comparator = Comparator.comparing((List<String> e) -> e.get(index));
		} else {
			comparator = Comparator.comparing((List<String> e) -> e.get(index)).reversed();
		}
		return comparator;
	}

}
