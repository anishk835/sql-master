package exercise.table.merge;

import exercise.model.SQLTable;

public interface MergeTables {

	SQLTable mergeTable(SQLTable...tables);
}
