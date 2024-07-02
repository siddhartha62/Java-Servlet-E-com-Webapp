package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.db.DatabaseController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DatabaseController dbController = new DatabaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printOut = response.getWriter();
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int loginResult = dbController.getUserLoginInfo(email,  password);
		
		if(loginResult == 1) {
			String userRole = dbController.getUserRole(email,  password);
			
			HttpSession userSession = request.getSession();
            userSession.setAttribute("email", email);
            userSession.setAttribute("role", userRole);
            userSession.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            Cookie roleCookie = new Cookie("role", userRole);
            roleCookie.setMaxAge(30 * 60); // 30 minutes
            response.addCookie(roleCookie);
            
            if("admin".equals(userRole)) {
            	response.sendRedirect(request.getContextPath() + "/pages/adminpanel.jsp");
            	
            }
            else if ("user".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/HomeproductServlet");
               
		}else {
			 request.setAttribute("errorMessage", "No Access");
             request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
				
	}else {
		request.setAttribute("errorMessage", "Invalid username or password");
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }
	}

}
