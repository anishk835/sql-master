package exercise.model;

import java.util.List;

public class SQLTable {

	private List<String> columns;
	private List<List<String>> data;
	private String tableNames;

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
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
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((tableNames == null) ? 0 : tableNames.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SQLTable other = (SQLTable) obj;
		if (columns == null) {
			if (other.columns != null)
				return false;
		} else if (!columns.equals(other.columns))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
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
		return "Table [columns=" + columns + ", data=" + data + ", tableNames=" + tableNames + "]";
	}

}
