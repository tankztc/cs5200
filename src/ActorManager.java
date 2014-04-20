
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ActorManager {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	String createActorSql = "INSERT INTO ACTOR (ID,FIRSTNAME,LASTNAME,DATEOFBIRTH) VALUES (?,?,?,?);";
	String readAllActorsSql = "SELECT * FROM ACTOR;";
	String readActorSql = "SELECT * FROM ACTOR WHERE ID=?;";
	String updateActorSql = "UPDATE ACTOR SET FIRSTNAME=?, LASTNAME=?, DATEOFBIRTH=? WHERE ID=?;";
	String deleteActorSql = "DELETE FROM ACTOR WHERE ID=?;";
	DataSource ds;
	
	public void createActor(Actor newActor){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createActorSql);
			statement.setString(1, newActor.getId());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, (Date)newActor.getDateOfBirth());
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
	
	public List<Actor> readAllActors(){
		List<Actor> actors = new ArrayList<Actor>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllActorsSql);
			results = statement.executeQuery();
			while(results.next()){
				Actor actor = new Actor(results.getString("id"),results.getString("firstName"),results.getString("lastName"),results.getDate("dateOfBirth"));
				actors.add(actor);
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
		return actors;
	}
	
	public Actor readActor(String id){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readActorSql);
			statement.setString(1, id);
			results = statement.executeQuery();
			if(results.next()){
				Actor actor = new Actor(results.getString("id"),results.getString("firstName"),results.getString("lastName"),results.getDate("dateOfBirth"));
				return actor;
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
		
	public void updateActor(String id,Actor actor){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateActorSql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, (Date)actor.getDateOfBirth());
			statement.setString(4, actor.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteActor(String id){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteActorSql);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public ActorManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
