package testcases;

import java.sql.SQLException;

public class TestDbCon {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		DbManager.setOracleDbConnection();
		DbManager.setMysqlDbConnection();
		new DCToolFrame();

	}

}
