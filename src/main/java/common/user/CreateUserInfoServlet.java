package common.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import common.user.mybatis.UserMapper;
import database.DatabaseConnetion;

/**
 * Servlet implementation class CreateUserInfoServlet
 */
//@WebServlet("/CreateUserInfoServlet")
public class CreateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("param" + request.getParameter("id"));

		DatabaseConnetion dbInfo = new DatabaseConnetion();
		
		Connection conn = dbInfo.getDBConnenction();
		PreparedStatement pstmt = null;
		String state = "U";
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String pwd = request.getParameter("pwd");
				
		try {
			
			String sql =  "insert into T_USER_MST(id, name, age, gender, password) values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, pwd);
			
			int count = pstmt.executeUpdate();
			
			if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
                
                pstmt.clearParameters();
                
                sql =  "insert into T_PERSONAL_CODE"
                		+ "(id, code_name, code, upper_code, code_cn, use_at)"
                		+ "select ?, code_name, code, upper_code, code_cn, use_at"
                		+ "  from T_COMMON_CODE"
                		+ " where group_code in ('A', 'B', 'C')";
                
    			pstmt = conn.prepareStatement(sql);
                
    			pstmt.setString(1, id);
    			
    			count = pstmt.executeUpdate();
    			if( count == 0 ){
                    System.out.println("데이터 입력 실패");
                }
                else{
                	System.out.println("데이터 입력 성공22");
                }
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			state = "Error";
			e.printStackTrace();
			System.out.println("1");
		} finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            }
            catch( SQLException e){
            	System.out.println("2");
                e.printStackTrace();
                state = "Error";
            }
        }
		
		request.setAttribute("state", state);
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		
		RequestDispatcher view = request.getRequestDispatcher("/jsp/user/createUser.jsp");
		
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
