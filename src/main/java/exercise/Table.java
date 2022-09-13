package exercise;

import java.util.List;

import exercise.csvreader.CsvReaderImpl;
import exercise.io.FileScanner;
import exercise.io.FileScannerImpl;
import exercise.model.SQLTable;
import exercise.table.TableList;
import exercise.table.TableListImpl;
import exercise.table.merge.MergeTables;
import exercise.table.sort.Order;
import exercise.table.sort.SortTable;
import exercise.table.sort.SortTableImpl;

public class Table {

	private TableList tables;
	private FileScanner fileScanner;
	private String dir;
	private SortTable sortTable;
	private MergeTables mergeTables;

	public Table(String dir, MergeTables mergeTables, Order order) {
		this.dir = dir;
		this.fileScanner = new FileScannerImpl();
		if (null != order) {
			this.sortTable = new SortTableImpl(order);
		}
		this.mergeTables = mergeTables;
		tables = new TableListImpl(new CsvReaderImpl());
	}

	public SQLTable innerJoin() {
		List<String> files = fileScanner.files(dir);
		List<SQLTable> csvToTables = tables.csvToTables(files);
		SQLTable mergeTable = mergeTables.mergeTable(csvToTables.get(1), csvToTables.get(0));
		return sortTable != null ? sortTable.sortTable(mergeTable, 0) : mergeTable;
	}

	public static Table tableBuilder(String dir, MergeTables mergeTables, Order order) {
		return new Table(dir, mergeTables, order);
	}
}
