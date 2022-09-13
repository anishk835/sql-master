package exercise.table;

import java.util.ArrayList;
import java.util.List;

import exercise.csvreader.CsvReader;
import exercise.model.Table;

public class TableListImpl implements TableList {

	private CsvReader csvReader;

	public TableListImpl(CsvReader csvReader) {
		this.csvReader = csvReader;
	}

	@Override
	public List<Table> csvToTables(List<String> files, int totalTable) {
		List<Table> result = new ArrayList<>(totalTable);
		for (String path : files) {
			Table table = new Table();
			List<List<String>> csvData = csvReader.readCsvFile(path);
			table.setColumns(csvData.get(0));
			table.setData(csvData.subList(1, csvData.size()));
			result.add(table);
		}
		return result;

	}

}
