package cn.mldn.house.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

public class DataSource {
	private static final DataSource SINGLEINSTANCE =  new DataSource();
	private javax.sql.DataSource dataSource;
	private Connection connection;
	
	private DataSource(){
		if (dataSource == null) {
			try {
				Context context = new InitialContext();
				Context ct = (Context) context.lookup("java:comp/env");
				dataSource = (javax.sql.DataSource) ct.lookup("jdbc/mldn");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static DataSource getDataSource() {
		return SINGLEINSTANCE;
	}
	public Connection getConnection(){
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void close(){
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
