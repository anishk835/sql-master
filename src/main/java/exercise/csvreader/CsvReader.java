package exercise.csvreader;

import exercise.table.model.Table;

public interface CsvReader {

	Table readCsvFile(String path);
}
