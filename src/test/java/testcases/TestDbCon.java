package testcases;

import java.sql.SQLException;

public class TestDbCon {

	public static void main(String[] args) throws SQLException {
		
		DbManager.setOracleDbConnection();
		DbManager.getOracleQuery("select * from employees_sample1 order by emp_no","select * from employees_sample order by emp_no");

	}

}
