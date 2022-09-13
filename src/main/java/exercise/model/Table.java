package exercise.model;

import java.util.List;
import java.util.Objects;

public class Table {

	private List<String> columns;
	private List<List<String>> data;

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

	@Override
	public int hashCode() {
		return Objects.hash(columns, data);
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
		return Objects.equals(columns, other.columns) && Objects.equals(data, other.data);
	}

	@Override
	public String toString() {
		return "Table [columns=" + columns + ", data=" + data + "]";
	}

}
