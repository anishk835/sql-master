package exercise.table.merge;

import exercise.model.JoinTable;
import exercise.model.SQLTable;

public interface MergeTables {

	SQLTable merge(JoinTable[] joinTable, String tableName);
}
