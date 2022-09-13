package exercise.table;

import java.util.List;

import exercise.model.Table;

public interface TableList {

	List<Table> csvToTables(List<String> files, int totalTable);
}
