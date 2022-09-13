package exercise.csvreader;

import java.util.List;

public interface CsvReader {

	List<List<String>> readCsvFile(String path);
}
