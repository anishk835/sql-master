package exercise.table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import exercise.csvreader.CsvReader;
import exercise.model.SQLTable;

public class TableListImpl implements TableList {

	private static final String REGEX = ".";
	private CsvReader csvReader;

	public TableListImpl(CsvReader csvReader) {
		this.csvReader = csvReader;
	}

	@Override
	public List<SQLTable> csvToTables(List<String> files) {
		List<SQLTable> result = new ArrayList<>(files.size());
		for (String path : files) {
			SQLTable table = new SQLTable();
			table.setTableNames(getTableName(path));
			List<List<String>> csvData = csvReader.readCsvFile(path);
			table.setColumns(csvData.get(0));
			table.setData(csvData.subList(1, csvData.size()));
			result.add(table);
		}
		return result;

	}

	private String getTableName(String path) {
		final String[] dir = path.split(File.separator);
		String tableName = dir[dir.length - 1];
		return tableName.concat(REGEX) != null ? tableName.substring(0, tableName.lastIndexOf('.')).toUpperCase()
				: tableName.toUpperCase();
	}

}
