package exercise.table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import exercise.csvreader.CsvReader;
import exercise.table.model.Table;

public class TableListImpl implements TableList {

	private static final String REGEX = ".";
	private CsvReader csvReader;

	public TableListImpl(CsvReader csvReader) {
		this.csvReader = csvReader;
	}

	@Override
	public List<Table> csvToTables(List<String> files) {
		List<Table> result = new ArrayList<>(files.size());
		for (String path : files) {
			Table table = new Table();
			table.setTableNames(getTableName(path));
			result.add(csvReader.readCsvFile(path));
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
