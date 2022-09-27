package exercise.table.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import exercise.model.JoinTable;
import exercise.model.SQLTable;

public class MergeTwoTableImpl implements MergeTables {

	@Override
	public SQLTable merge(JoinTable[] joinTable, String tableName) {
		if (joinTable == null) {
			throw new RuntimeException("Array of tables to be joined is null");
		}
		if (joinTable.length < 2) {
			System.err.print("Single table can't be joined hence returning the same table");
			return joinTable[0].getTables();
		}
		SQLTable result = new SQLTable();
		List<SQLTable> tables = Stream.of(joinTable).map(t -> t.getTables()).collect(Collectors.toList());
		List<String> columns = Stream.of(joinTable).map(t -> t.getOnJoinColumnName()).collect(Collectors.toList());
		int[] index = getColumnIndexInTable(tables, columns);
		// prepare merge table column
		List<String> mergedColumn = createMergedColumns(tables, index);
		result.setColumns(mergedColumn); // set final columns in the result
		// merge data for each table
		List<List<String>> mergeTwoTableData = mergeTwoTableData(tables.get(0), tables.get(1), index[0], index[1]);
		result.setData(mergeTwoTableData);
		final String finalTableName = tableName != null ? tableName : setTableName(tables);
		result.setTableNames(finalTableName);
		return result;
	}

	private List<List<String>> mergeTwoTableData(SQLTable first, SQLTable second, int columnIndexFirst,
			int columnIndexSecond) {
		int skipCountFirstTable = first.getColumns().size();
		Map<String, Integer> map = new HashMap<>();
		List<List<String>> d = second.getData();
		for (int j = 0; j < d.size(); j++) {
			map.put(d.get(j).get(columnIndexSecond), j);
		}
		List<String> d1 = first.getData().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
		List<List<String>> data = new ArrayList<>();
		int startIndexFirstTable = 0;
		int endIndexFirstTable = skipCountFirstTable; // To skip the size equal to the number of columns
		while (endIndexFirstTable <= d1.size()) {
			List<String> temp = new ArrayList<>();
			int idx = map.getOrDefault(d1.get(columnIndexFirst), -1);
			if (idx != -1) {
				temp.addAll(d1.subList(startIndexFirstTable, endIndexFirstTable));
				List<String> list = second.getData().get(idx);
				temp.addAll(list.subList(0, columnIndexSecond));
				temp.addAll(list.subList(columnIndexSecond + 1, list.size()));
				data.add(temp);
			}
			startIndexFirstTable = endIndexFirstTable;
			endIndexFirstTable = endIndexFirstTable + skipCountFirstTable;
			columnIndexFirst = columnIndexFirst + skipCountFirstTable;
		}
		return data;
	}

	private int[] getColumnIndexInTable(List<SQLTable> tables, List<String> columns) {
		int idxCount = 0;
		int[] index = new int[tables.size()]; // store index of the matching column for each table
		for (int i = 0; i < tables.size(); i++) {
			List<String> cols = tables.get(i).getColumns();
			String curCol = columns.get(i);
			// TODO: handle not matching column
			index[idxCount++] = IntStream.range(0, cols.size()).filter(j -> cols.get(j).equals(curCol)).findFirst()
					.getAsInt();
		}
		return index;
	}

	private List<String> createMergedColumns(List<SQLTable> tables, int[] index) {
		List<String> mergedColumn = new ArrayList<>();
		List<String> col = tables.get(0).getColumns();
		int colIndex = index[0];
		mergedColumn.addAll(col.subList(0, colIndex));
		mergedColumn.addAll(col.subList(colIndex, col.size()));
		for (int i = 1; i < tables.size(); i++) {
			List<String> temp = tables.get(i).getColumns();
			colIndex = index[i];
			mergedColumn.addAll(temp.subList(0, colIndex));
			colIndex = colIndex + 1; // skip adding column again and it will be applicable on next table
			if (colIndex <= temp.size() - 1) {
				mergedColumn.addAll(temp.subList(colIndex, temp.size()));
			}
		}
		return mergedColumn;
	}

	private String setTableName(List<SQLTable> tables) {
		return tables.stream().map(t -> t.getTableNames().toUpperCase()).collect(Collectors.joining("_"));
	}

}
