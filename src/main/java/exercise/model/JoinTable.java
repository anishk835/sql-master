package exercise.model;

public class JoinTable {

	private SQLTable tables;
	private String joinColumnName;

	public SQLTable getTables() {
		return tables;
	}

	public JoinTable setTables(SQLTable tables) {
		this.tables = tables;
		return this;
	}

	public String getOnJoinColumnName() {
		return joinColumnName;
	}

	public JoinTable onJoinColumnName(String column) {
		this.joinColumnName = column;
		return this;
	}

}
