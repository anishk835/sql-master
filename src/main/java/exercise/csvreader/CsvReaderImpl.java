package exercise.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exercise.table.model.Table;

public class CsvReaderImpl implements CsvReader {

	private static final String COMMA_DELIMITER = ",";

	@Override
	public Table readCsvFile(String path) {
		final Table table = new Table();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			String[] columns = line.split(COMMA_DELIMITER);
			List<String[]> rows = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				rows.add(line.split(COMMA_DELIMITER));
			}
			List<Map<String, String>> data = rows.stream().map(
					r -> IntStream.range(0, r.length).boxed().collect(Collectors.toMap(i -> columns[i], i -> r[i])))
					.toList();
			table.setColumns(Arrays.asList(columns));
			table.setData(data);
			table.setTableNames("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return table;
	}

}
