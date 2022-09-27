package exercise;

import exercise.model.JoinTable;
import exercise.model.SQLTable;
import exercise.table.merge.MergeTables;
import exercise.table.sort.Order;
import exercise.table.sort.SortTable;

public class Table {

	private MergeTables mergeTables;
	private JoinTable[] joinTables;
	private String mergedTableName;
	private SQLTable table;

	public Table(JoinTable[] tables, MergeTables mergeTables) {
		this.joinTables = tables;
		this.mergeTables = mergeTables;
	}

	public Table setMergedTableName(String tableName) {
		this.mergedTableName = tableName;
		return this;
	}

	public SQLTable getFinalTable() {
		return this.table;
	}

	public Table innerJoinTwoTable() {
		this.table = mergeTables.merge(joinTables, mergedTableName);
		return this;
	}

	public Table sortTable(String column, Order order, SortTable sortTable) {
		this.table = sortTable.sortTableOn(table, column, order);
		return this;
	}

	public static Table tableBuilder(JoinTable[] tables, MergeTables mergeTables) {
		return new Table(tables, mergeTables);
	}
}
