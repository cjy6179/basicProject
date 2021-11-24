package common.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseConnetion;
import logger.FirstLogger;

/**
 * Servlet implementation class SelectUserInfoServlet
 */
@WebServlet("/SelectUserInfoServlet")
public class SelectUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	FirstLogger logger = FirstLogger.getLogger();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DatabaseConnetion database = new DatabaseConnetion();
		Connection conn = database.getDBConnenction();
		
		String sql = "SELECT @rownum:=@rownum+1, id, name, age, gender FROM T_USER_MST b, (SELECT @ROWNUM:=0) R ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		List<UserInfoVO> userInfoList = new ArrayList<UserInfoVO>();
				
				
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ){
				UserInfoVO userInfoVo = new UserInfoVO();
				
				userInfoVo.setIndex(rs.getInt(1));
				userInfoVo.setId(rs.getString(2));
				userInfoVo.setName(rs.getString(3));
				userInfoVo.setAge(rs.getString(4));
				userInfoVo.setGender(rs.getString(5));
				
				userInfoList.add(userInfoVo);
				count++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(e.getMessage());
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
		
		String dipatcherSource = "/jsp/user/userInfoList.jsp";    
		request.setAttribute("userInfoList", userInfoList);
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
