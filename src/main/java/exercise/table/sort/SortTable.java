package exercise.table.sort;

import exercise.model.SQLTable;

public interface SortTable {

	SQLTable sortTableOn(SQLTable table, String column, Order order);

}
