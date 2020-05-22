package testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbManager {

	private static Connection oraclecon = null;
	private static Connection mysqlcon = null;
	private static Connection snowflakecon = null;
	private static Connection sourcecon = null;
	private static Connection targetcon = null;

	public static void setOracleDbConnection(String sourceDb, String targetDb)
			throws SQLException, ClassNotFoundException {

		try {

			Class.forName(TestConfig.oracleDriver);
			oraclecon = DriverManager.getConnection(TestConfig.oracleUrl, TestConfig.oracleUsername,
					TestConfig.oraclePassword);
			if (!oraclecon.isClosed()) {
				System.out.println("Successfully connected to oracle server");
				if (sourceDb == "Oracle")
					sourcecon = oraclecon;
				if (targetDb == "Oracle")
					targetcon = oraclecon;
			}

		}

		catch (Exception e) {

			System.err.println("Can't connect to oracle server");
		}

	}

	public static void setMysqlDbConnection(String sourceDb, String targetDb)
			throws SQLException, ClassNotFoundException {

		try {

			Class.forName(TestConfig.mysqlDriver);
			mysqlcon = DriverManager.getConnection(TestConfig.mysqlUrl, TestConfig.mysqlUsername,
					TestConfig.mysqlPassword);
			if (!mysqlcon.isClosed()) {
				System.out.println("Successfully connected to mysql server");
				if (sourceDb == "MySql")
					sourcecon = mysqlcon;
				if (targetDb == "MySql")
					targetcon = mysqlcon;
			}

		}

		catch (Exception e) {

			System.err.println("Can't connect to mysql server");
		}

	}

	public static void setSnowflakeDbConnection(String sourceDb, String targetDb)
			throws SQLException, ClassNotFoundException {

		try {

			Class.forName(TestConfig.snowflakeDriver);
			snowflakecon = DriverManager.getConnection(TestConfig.snowflakeUrl, TestConfig.snowflakeUsername,
					TestConfig.snowflakePassword);
			if (!snowflakecon.isClosed()) {
				System.out.println("Successfully connected to snowflake server");
				if (sourceDb == "Snowflake")
					sourcecon = snowflakecon;
				if (targetDb == "Snowflake")
					targetcon = snowflakecon;

			}

		}

		catch (Exception e) {

			System.err.println("Can't connect to snowflake server");
		}

	}

	public static String dataCompare(String qry1, String qry2) throws SQLException, ClassNotFoundException {

		String return_stmt;
		try {

			Statement st1 = sourcecon.createStatement();
			ResultSet rs1 = st1.executeQuery(qry1);
			List<String> values1 = new ArrayList<String>();
			Statement st2 = targetcon.createStatement();
			ResultSet rs2 = st2.executeQuery(qry2);

			System.out.println("Source table column count : " + rs1.getMetaData().getColumnCount());
			System.out.println("Target table column count : " + rs2.getMetaData().getColumnCount());

			if (rs1.getMetaData().getColumnCount() != rs2.getMetaData().getColumnCount()) {

				System.out.println("Number of columns mismatch");
				return "Number of columns mismatch";

			}

			List<String> values2 = new ArrayList<String>();
			String qry1_record = null;
			String qry2_record = null;
			while (rs1.next()) {
				qry1_record = rs1.getString(1);
				for (int i = 1; i < rs1.getMetaData().getColumnCount(); i++) {
					qry1_record = qry1_record + " | " + rs1.getString(i + 1);
				}

				values1.add(qry1_record);

			}
			while (rs2.next()) {
				qry2_record = rs2.getString(1);
				for (int i = 1; i < rs2.getMetaData().getColumnCount(); i++) {
					qry2_record = qry2_record + " | " + rs2.getString(i + 1);
				}

				values2.add(qry2_record);

			}

			Iterator<String> itr1 = values1.iterator();
			Iterator<String> itr2 = values2.iterator();

			int i = 0, count = 0;
			String val1;
			String val2;
			while (itr1.hasNext()) {
				i++;
				val1 = itr1.next();
				val2 = itr2.next();
				if (!val1.contentEquals(val2)) {
					System.out.println("Mismatch occured in record : " + i);
					System.out.println("Source : " + val1 + "     \n" + "Target : " + val2);
					count++;
				}

			}
			if (count == 0) {
				System.out.println("Source and Target tables data matches");
				return_stmt = "Source and Target tables data matches";
			} else {
				System.out.println("\nTotal mismatched records : " + count);
				return_stmt = "Total mismatched records : " + count;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return return_stmt;
	}
}
