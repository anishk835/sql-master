package innerjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exercise.table.model.Table;

public class InnerJoin implements Join {

	@Override
	public Table join(Table leftTable, Table rightTable, String leftColumn, String rightColumn) {
        Table joinedTable = new Table.Builder()
                .withColumnNames(new ArrayList<>(leftTable.getColumnNames()))
                .build();

        for (String columnName : rightTable.getColumnNames()) {
            if (!leftTable.getColumnNames().contains(columnName)) {
                joinedTable.getColumnNames().add(columnName);
            }
        }

        Map<String, List<Map<String, String>>> rightTableMap = new HashMap<>();
        for (Map<String, String> rightRow : rightTable.getData()) {
            String key = rightRow.get(rightColumn);
            rightTableMap.computeIfAbsent(key, k -> new ArrayList<>()).add(rightRow);
        }

        for (Map<String, String> leftRow : leftTable.getData()) {
            String key = leftRow.get(leftColumn);
            if (rightTableMap.containsKey(key)) {
                for (Map<String, String> rightRow : rightTableMap.get(key)) {
                    Map<String, String> joinedRow = new HashMap<>(leftRow);
                    for (String columnName : rightRow.keySet()) {
                        if (!joinedRow.containsKey(columnName)) {
                            joinedRow.put(columnName, rightRow.get(columnName));
                        }
                    }
                    joinedTable.getData().add(joinedRow);
                }
            }
        }

        return joinedTable;
    }

}
