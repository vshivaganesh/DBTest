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

	private static Connection con = null;

	public static void setOracleDbConnection() throws SQLException, ClassNotFoundException {

		try {

			Class.forName(TestConfig.oracleDriver);
			con = DriverManager.getConnection(TestConfig.oracleUrl, TestConfig.oracleUsername,
					TestConfig.oraclePassword);
			if (!con.isClosed()) {
				System.out.println("Successfully connected to oracle server");
			}

		}

		catch (Exception e) {

			System.err.println("Can't connect to oracle server");
		}

	}

	public static String getOracleQuery(String qry1, String qry2) throws SQLException, ClassNotFoundException {

		int qry1_number_of_columns = 1;
		int qry2_number_of_columns = 1;
		char indicator1 = ' ';
		char indicator2 = ' ';
		String return_stmt;
		try {
			for (int i = 0; i < qry1.indexOf("from"); i++) {
				if (qry1.charAt(i) == ',') {
					qry1_number_of_columns++;
				} else if (qry1.charAt(i) == '*') {
					indicator1 = '*';
				}

			}
			for (int i = 0; i < qry2.indexOf("from"); i++) {
				if (qry2.charAt(i) == ',') {
					qry2_number_of_columns++;
				} else if (qry2.charAt(i) == '*') {
					indicator2 = '*';
				}
			}
			if (qry1_number_of_columns != qry2_number_of_columns) {
				System.out.println("Number of columns mismatch");
				return "Number of columns mismatch";
			} else if (indicator1 == '*' || indicator2 == '*') {
				System.out.println("Please provide exact column names in queries");
				return "Please provide exact column names in queries";
			}

			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(qry1);
			List<String> values1 = new ArrayList<String>();
			Statement st2 = con.createStatement();
			ResultSet rs2 = st2.executeQuery(qry2);
			List<String> values2 = new ArrayList<String>();
			String qry1_record = null;
			String qry2_record = null;
			while (rs1.next()) {
				qry1_record = rs1.getString(1);
				for (int i = 1; i < qry1_number_of_columns; i++) {
					qry1_record = qry1_record + " | " + rs1.getString(i + 1);
				}

				values1.add(qry1_record);

			}
			while (rs2.next()) {

				qry2_record = rs2.getString(1);
				for (int i = 1; i < qry2_number_of_columns; i++) {
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
