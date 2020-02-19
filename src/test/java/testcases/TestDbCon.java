package testcases;

import java.sql.SQLException;
import java.util.Scanner;

public class TestDbCon {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter sql1:");
		String sql1=sc.nextLine();
		System.out.println("Please enter sql2:");
		String sql2=sc.nextLine();
		
		DbManager.setOracleDbConnection();
		DbManager.getOracleQuery(sql1,sql2);

		sc.close();
	}

}
