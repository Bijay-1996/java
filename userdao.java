package empadd.net.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import empapp.net.user.*;

public class userdao {
	private String jdbcURL = "jdbc:sqlserver://HPSQL_785/database=dbjava;Integrity security=true";
    private String jdbcusername = "BIJAY";
    private String jdbcPassword = "";

    private static final String INSERT_userS_SQL = "INSERT INTO employee" + "  (fname, lname, salary, dept, position, emailid, num, pic ) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_user_BY_ID = "select id,fname,lname,salary,dept,position,emailid,num,pic from employee where id =?";
    private static final String SELECT_ALL_userS = "select * from employee";
    private static final String DELETE_userS_SQL = "delete from employee where id = ?;";
    private static final String UPDATE_userS_SQL = "update employee set fname = ?,lname= ?, salary =?, dept =?, position =?, emailid =?, num =?, pic =? where id = ?;";

    public userdao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcusername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertuser(user user) throws SQLException {
        System.out.println(INSERT_userS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); java.sql.PreparedStatement preparedStatement = connection.prepareStatement(INSERT_userS_SQL)) {
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getSalary());
            preparedStatement.setString(4, user.getDepartment());
            preparedStatement.setString(5, user.getPosition());
            preparedStatement.setString(6, user.getEmial());
            preparedStatement.setString(7, user.getPnum());
            preparedStatement.setImage(8, user.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public user selectuser(int id) {
        user user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(SELECT_user_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String salary = rs.getString("salary");
                String department = rs.getString("dept");
                String position = rs.getString("position");
                String email = rs.getString("email");
                String num = rs.getString("num");
                byte[] pic = rs.getBytes("pic");
                user = new user(id, fname, lname, salary, dept, position, emailid, num, pic);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List < user > selectAllusers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < user > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_userS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String salary = rs.getString("salary");
                String department = rs.getString("dept");
                String position = rs.getString("position");
                String email = rs.getString("email");
                String num = rs.getString("num");
                byte[] pic = rs.getBytes("pic");
                users.add(new user(id, fname, lname, salary, dept, position, emailid, num, pic));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteuser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_userS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateuser(user user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_userS_SQL);) {
        	statement.setString(1, user.getFname());
        	statement.setString(2, user.getLname());
            statement.setString(3, user.getSalary());
            statement.setString(4, user.getDepartment());
            statement.setString(5, user.getPosition());
            statement.setString(6, user.getEmial());
            statement.setString(7, user.getPnum());
            statement.setImage(8, user.getImage());
            statement.setInt(9, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
