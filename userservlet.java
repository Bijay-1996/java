package empadd.net.userservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import empapp.net.user.*;
import empadd.net.userdao.*;


@WebServlet("/")
public class userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userdao userdao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
        userdao = new userdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        doGet(request, response);
    	    }

    	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        String action = request.getServletPath();

    	        try {
    	            switch (action) {
    	                case "/new":
    	                    showNewForm(request, response);
    	                    break;
    	                case "/insert":
    	                    insertUser(request, response);
    	                    break;
    	                case "/delete":
    	                    deleteUser(request, response);
    	                    break;
    	                case "/edit":
    	                    showEditForm(request, response);
    	                    break;
    	                case "/update":
    	                    updateUser(request, response);
    	                    break;
    	                default:
    	                    listUser(request, response);
    	                    break;
    	            }
    	        } catch (SQLException ex) {
    	            throw new ServletException(ex);
    	        }
    	    }

    	    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < User > listUser = userDAO.selectAllUsers();
    	        request.setAttribute("listUser", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("emp-list.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("emp_form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        User existingUser = userdao.selectUser(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("emp_form.jsp");
    	        request.setAttribute("user", existingUser);
    	        dispatcher.forward(request, response);

    	    }

    	    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String fname = request.getParameter("fname");
    	        String lname = request.getParameter("lname");
    	        String salary = request.getParameter("salary");
    	        String dept = request.getParameter("dept");
    	        String position = request.getParameter("position");
    	        String emailid = request.getParameter("emailid");
    	        String num = request.getParameter("num");
    	        byte[] pic = request.byte("pic");
    	        user newUser = new user(fname, lname, salary, dept, position, emailid, num, pic);
    	        userdao.insertUser(newUser);
    	        response.sendRedirect("list");
    	    }

    	    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        String fname = request.getParameter("fname");
    	        String lname = request.getParameter("lname");
    	        String salary = request.getParameter("salary");
    	        String dept = request.getParameter("dept");
    	        String position = request.getParameter("position");
    	        String emailid = request.getParameter("emailid");
    	        String num = request.getParameter("num");
    	        String pic = request.getParameter("pic");
    	        

    	        user book = new user(id, fname, lname, salary, dept, position, emailid, num, pic);
    	        userdao.updateUser(book);
    	        response.sendRedirect("list");
    	    }

    	    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        userDAO.deleteUser(id);
    	        response.sendRedirect("list");

    	    }

}
