package exercise.table.sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.SQLTable;

public class SortTableImpl implements SortTable {

	private Order order;

	public SortTableImpl(Order order) {
		this.order = order;
	}

	@Override
	public SQLTable sortTable(SQLTable table, int index) {
		SQLTable result = new SQLTable();
		result.setColumns(table.getColumns());
		Comparator<List<String>> comparator = getComparator(index);
		List<List<String>> data = table.getData().stream().sorted(comparator).collect(Collectors.toList());
		result.setData(data);
		result.setTableNames(table.getTableNames());
		return result;
	}

	private Comparator<List<String>> getComparator(int index) {
		Comparator<List<String>> comparator;
		if (order.equals(Order.ASC)) {
			comparator = Comparator.comparing((List<String> e) -> e.get(index));
		} else {
			comparator = Comparator.comparing((List<String> e) -> e.get(index)).reversed();
		}
		return comparator;
	}

}
