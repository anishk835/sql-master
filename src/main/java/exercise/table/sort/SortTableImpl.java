package exercise.table.sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import exercise.model.Table;

public class SortTableImpl implements SortTable {

	@Override
	public Table sortTable(Table table, int index) {
		Table result = new Table();
		result.setColumns(table.getColumns());
		List<List<String>> data = table.getData().stream()
				.sorted(Comparator.comparing((List<String> e) -> e.get(index)).reversed()).collect(Collectors.toList());
		result.setData(data);
		return result;
	}

}
