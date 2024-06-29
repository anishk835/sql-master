package exercise.table;

import java.util.List;

import exercise.table.model.Table;

public interface TableList {

	List<Table> csvToTables(List<String> files);
}
