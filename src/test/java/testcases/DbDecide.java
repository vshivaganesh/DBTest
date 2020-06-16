package testcases;

import java.sql.SQLException;

public class DbDecide {

	public static void dbSelect(String sourceDb, String targetDb, String url, String user, String pwd)
			throws ClassNotFoundException, SQLException {
		if (sourceDb == "Oracle" || targetDb == "Oracle") {
			DbManager.setOracleDbConnection(sourceDb, targetDb);
		}
		if (sourceDb == "MySql" || targetDb == "MySql") {
			DbManager.setMysqlDbConnection(sourceDb, targetDb);
		}
		if (sourceDb == "Snowflake" || targetDb == "Snowflake") {
			DbManager.setSnowflakeDbConnection(sourceDb, targetDb, url, user, pwd);
		}
	}

}
