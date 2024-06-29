package exercise.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exercise.table.sort.DescendingSort;
import exercise.table.sort.Sort;

public class Table {

	private List<String> columns;
	private List<Map<String, String>> rows;
	private String tableNames;

	public Table() {
		this.columns = new ArrayList<>();
		this.rows = new ArrayList<>();
	}

	private Table(Builder builder) {
		this.columns = builder.columnNames;
		this.rows = builder.rows;
	}

	public List<String> getColumnNames() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<Map<String, String>> getData() {
		return rows;
	}

	public void setData(List<Map<String, String>> data) {
		this.rows = data;
	}

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columns == null) ? 0 : columns.hashCode());
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		result = prime * result + ((tableNames == null) ? 0 : tableNames.hashCode());
		return result;
	}

	public Table orderByDesc(String columnName) {
        Sort sortingStrategy = new DescendingSort();
        return sortingStrategy.sort(this, columnName);
    }
	
	public static class Builder {
		private List<String> columnNames = new ArrayList<>();
		private List<Map<String, String>> rows = new ArrayList<>();

		public Builder withColumnNames(List<String> columnNames) {
			this.columnNames = columnNames;
			return this;
		}

		public Builder addRow(List<Map<String, String>> row) {
			this.rows.addAll(row);
			return this;
		}

		public Table build() {
			return new Table(this);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (columns == null) {
			if (other.columns != null)
				return false;
		} else if (!columns.equals(other.columns))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		if (tableNames == null) {
			if (other.tableNames != null)
				return false;
		} else if (!tableNames.equals(other.tableNames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Table [columns=" + columns + ", data=" + rows + ", tableNames=" + tableNames + "]";
	}

}
