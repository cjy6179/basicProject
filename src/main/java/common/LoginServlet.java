package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.vo.test.PersonVO;
import database.DatabaseConnetion;
import logger.FirstLogger;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FirstLogger logger = FirstLogger.getLogger();

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("LoginServlet 왜 안돼냐");
//		response.getWriter().append("Served 아아아: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		DatabaseConnetion dbInfo = new DatabaseConnetion();
		
		Connection conn = dbInfo.getDBConnenction(request.getServletContext());
		
		String sql = "SELECT id, name, age, gender FROM T_USER_MST WHERE id = ? AND password =?"; 
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String state="S";
		PersonVO person = new PersonVO();;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				person.setId(rs.getString(1));
				person.setName(rs.getString(2));
				person.setAge(rs.getString(3));
				person.setGender(rs.getString(4));
				
				count++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(e.getMessage());
			state="error";
		} finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }if( rs != null && !rs.isClosed()){
                    rs.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }

		String dipatcherSource="jsp/main.jsp";
		
		if(count <= 0) {
			state="F";
			dipatcherSource="index.jsp";
		}
		
		request.setAttribute("person", person);
		request.setAttribute("state", state);
		RequestDispatcher view = request.getRequestDispatcher(dipatcherSource);
	
		view.forward(request, response);
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
