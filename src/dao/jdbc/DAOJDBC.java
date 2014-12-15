package dao.jdbc;

import java.sql.*;

public class DAOJDBC {
	
	final private static String DRIVER="com.mysql.jdbc.Driver";
	final private static String URL="jdbc:mysql://webpanel.telecomnancy.univ-lorraine.fr/alternc-sql/codingweek_prj11";
	final private static String USER="codingweek_prj11";
	final private static String MDP="i1FpwTi0a";
	
	public Connection getConnection() {
			Connection connection = null;
			
			try{
				Class.forName(DRIVER);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			
			try{
				connection = DriverManager.getConnection(URL,USER,MDP);	
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return connection;
	}
}
