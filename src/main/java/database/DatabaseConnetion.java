package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.jdbc.BaseJdbcLogger;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import common.user.UserInfoVO;
import config.CustomServletContextListener;
import config.LoadProperties;
import logger.FirstLogger;

public class DatabaseConnetion {
	
	final static Properties properties = LoadProperties.getProperties();
	static SqlSessionFactory sqlSessionFactory;
	FirstLogger logger = FirstLogger.getLogger();
	
	public Connection getDBConnenction() {
		return dbConnenction();
	}

	public SqlSessionFactory getMybitisDBConnenction() {
		return mybitisBbConnenction();
	}
	
	private Connection dbConnenction() {
		
		String JDBC_DRIVER = properties.getProperty("driver");
		String DB_URL = properties.getProperty("url");
		String USERNAME = properties.getProperty("username");
		String PASSWORD = properties.getProperty("password");
		
		// MySql에 사용하는여러 객체를 만들어줍니다. 
		Connection conn = null; 

		logger.log("ggg");
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
			logger.log(e.getMessage());
			System.out.println("Class Not Found Exection"); 
			e.printStackTrace(); 
		} catch (SQLException e) { 
			System.out.println("SQL Exception : " + e.getMessage()); e.printStackTrace(); 
		}
		
		return conn;
	}
	
	private SqlSessionFactory mybitisBbConnenction() {
		
		String resource = "/basicProject/mapper/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		return sqlSessionFactory;
	}
	
}
