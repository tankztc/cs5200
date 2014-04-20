
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class UserManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	String createUserSql="INSERT INTO USER (USERNAME, PASSWORD, FIRSTNAME, LASTNAME,EMAIL,DATEOFBIRTH) VALUES (?,?,?,?,?,?);";
	String readAllUsersSql = "SELECT * FROM USER;";
	String readUserSql = "SELECT * FROM USER WHERE USERNAME=?";
	String updateUserSql = "UPDATE USER SET PASSWORD=?,FIRSTNAME=?,LASTNAME=?,EMAIL=?,DATEOFBIRTH=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?;";
	
	
	public void createUser(User newUser)
	{
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, (Date) newUser.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public List<User> readAllUsers(){
		List<User> users = new ArrayList<User>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
			while(results.next()){
				User user = new User(results.getString("username"),results.getString("password"),results.getString("firtName"),results.getString("lastName"),results.getString("email"),results.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return users;	
	}
	
	public User readUser(String username){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUserSql);
			statement.setString(1, username);
			results = statement.executeQuery();
			if(results.next()){
				User user = new User(results.getString("username"),results.getString("password"),results.getString("firtName"),results.getString("lastName"),results.getString("email"),results.getDate("dateOfBirth"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void updateUser(String username, User user){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setDate(5, (Date) user.getDateOfBirth());
			statement.setString(6, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(String username){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	DataSource ds;
	public UserManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
