package innerjoin;

import exercise.table.model.Table;

public interface Join {

	Table join(Table leftTable, Table rightTable, String leftColumn, String rightColumn);
}
