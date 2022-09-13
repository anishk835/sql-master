package exercise.table;

import java.util.List;

import exercise.model.SQLTable;

public interface TableList {

	List<SQLTable> csvToTables(List<String> files);
}
