package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletContext;

import config.CustomServletContextListener;
import config.LoadProperties;

public class DatabaseConnetion {
	
	final static Properties p1 = LoadProperties.getProperties();
	
	public Connection getDBConnenction(ServletContext servletContext) {
		return dbConnenction(servletContext);
	}
	
	private Connection dbConnenction(ServletContext servletContext) {
		
		String JDBC_DRIVER = p1.getProperty("driver");
		String DB_URL = p1.getProperty("url");
		String USERNAME = p1.getProperty("username");
		String PASSWORD = p1.getProperty("password");
		
		// MySql에 사용하는여러 객체를 만들어줍니다. 
		Connection conn = null; 

		System.out.print("User Table 접속 : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
		//Class 클래스의 forName()함수를 이용해서 해당 클래스를 메모리로 로드 하는 것입니다. 
			//URL, ID, password를 입력하여 데이터베이스에 접속합니다. 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			// 접속결과를 출력합니다. 
			if (conn != null){
				System.out.println("성공");
			} else{
				System.out.println("실패");
			} 
		} catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection"); 
			e.printStackTrace(); 
		} catch (SQLException e) { 
			System.out.println("SQL Exception : " + e.getMessage()); e.printStackTrace(); 
		}
		
		return conn;
	}
	
	
}
