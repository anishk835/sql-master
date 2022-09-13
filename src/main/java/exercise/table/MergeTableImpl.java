package exercise.table;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Table;

public class MergeTableImpl implements MergeTables {

	@Override
	public Table mergeTable(Table... tables) {
		Table finalTable = new Table();
		List<String> finalCol = new ArrayList<>();
		List<List<String>> finalData = new ArrayList<>();
		finalTable.setColumns(finalCol);
		finalTable.setData(finalData);
		mergeTwoTable(finalTable, tables);
		return finalTable;
	}

	private void mergeTwoTable(Table finalTable, Table[] tables) {
		List<String> userCol = tables[0].getColumns();
		List<String> purchaseCol = tables[0].getColumns();
		finalTable.getColumns().addAll(purchaseCol.subList(0, userCol.size() - 1));
		finalTable.getColumns().addAll(userCol.subList(1, userCol.size()));
		List<List<String>> userData = tables[0].getData();
		List<List<String>> purchasedata = tables[1].getData();
		for (List<String> pdata : purchasedata) {

			List<String> dataSet = new ArrayList<>();
			int lastIndex = pdata.size() - 1;
			List<String> subList = pdata.subList(0, lastIndex);
			dataSet.addAll(subList);
			String userId = pdata.get(lastIndex);

			for (List<String> uData : userData) {
				if (uData.get(0).equals(userId)) {
					dataSet.addAll(uData.subList(1, uData.size()));
				}
			}
			finalTable.getData().add(dataSet);
		}

	}

}
