package designPatterns.facade;

import java.sql.Connection;

public class FacadePatternTest {

	/**
	 * Facade pattern hides the complexities of the system and provides an interface to the client using which the client can access the system.
	 * @param args
	 */
	public static void main(String[] args) {
		String tableName="Employee";
		
		//generating MySql HTML report and Oracle PDF report without using Facade
		Connection con = MySqlHelper.getMySqlDBConnection();
		MySqlHelper mySqlHelper = new MySqlHelper();
		mySqlHelper.generateMySqlHTMLReport(tableName, con);
		
		Connection con1 = OracleHelper.getOracleDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOraclePDFReport(tableName, con1);
		
		//generating MySql HTML report and Oracle PDF report using Facade
		FacadeHelper.generateReport(FacadeHelper.DBTypes.MYSQL, FacadeHelper.ReportTypes.HTML, tableName);
		FacadeHelper.generateReport(FacadeHelper.DBTypes.ORACLE, FacadeHelper.ReportTypes.PDF, tableName);
	}

}