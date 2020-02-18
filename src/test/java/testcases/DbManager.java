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

	public static void setOracleDbConnection() {

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

//	public static List<String> getOracleQuery(String qry) throws SQLException{
//		
//		Statement st=con.createStatement();
//		ResultSet rs=st.executeQuery(qry);
//		List<String> values=new ArrayList<String>();
//		while(rs.next()) {
//			
//			values.add(rs.getString(1));
//			
//		}
//		return values;
//	}

	public static void getOracleQuery(String qry1, String qry2) throws SQLException {

		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(qry1);
		List<String> values1 = new ArrayList<String>();
		Statement st2 = con.createStatement();
		ResultSet rs2 = st2.executeQuery(qry2);
		List<String> values2 = new ArrayList<String>();
		while (rs1.next()) {

			values1.add(rs1.getString(1) + " | " + rs1.getString(2) + " | " + rs1.getString(3) + " | "
					+ rs1.getString(4) + " | " + rs1.getString(5));

		}
		while (rs2.next()) {

			values2.add(rs2.getString(1) + " | " + rs2.getString(2) + " | " + rs2.getString(3) + " | "
					+ rs2.getString(4) + " | " + rs2.getString(5));

		}

		Iterator<String> itr1 = values1.iterator();
		Iterator<String> itr2 = values2.iterator();

		int i = 0;
		String val1;
		String val2;
		while (itr1.hasNext()) {
			i++;
			val1 = itr1.next();
			val2 = itr2.next();
			if (!val1.contentEquals(val2)) {
				System.out.println("Mismatch occured in record : " + i);
				System.out.println("Source : " + val1 + "     " + "Target : " + val2);
			}

		}

	}
}
