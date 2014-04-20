import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CastManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	String createCastSql = "INSERT INTO CAST (ID,ACTORID,MOVIEID,CHARACTERNAME) VALUES(?,?,?,?);";
	String readAllCastsSql = "SELECT * FROM CAST;";
	String readAllCastsForActorSql = "SELECT * FROM CAST WHERE ACTORID=?;";
	String readAllCastsForMovieSql = "SELECT * FROM CAST WHERE MOVIEID=?;";
	String readCastForIdSql = "SELECT * FROM CAST WHERE ID=?;";
	String updateCastSql = "UPDATE CAST SET ACTORID=?,MOVIEID=?,CHARACTERNAME=? WHERE ID=?;";
	String deleteCastSql = "DELETE FROM CAST WHERE ID=?;";
	
	public void createCast(Cast newCast){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCastSql);
			statement.setString(1, newCast.getId());
			statement.setString(2, newCast.getActorId());
			statement.setString(3, newCast.getMovieId());
			statement.setString(4, newCast.getCharacterName());
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
	
	public List<Cast> readAllCasts(){
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsSql);
			results = statement.executeQuery();
			while(results.next()){
				Cast cast = new Cast(results.getString("id"), results.getString("actroId"), results.getString("movieId"), results.getString("characterName"));
				casts.add(cast);
			}
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
		return casts;
	}
	
	public List<Cast> readAllCastsForActor(){
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForActorSql);
			results = statement.executeQuery();
			while(results.next()){
				Cast cast = new Cast(results.getString("id"), results.getString("actroId"), results.getString("movieId"), results.getString("characterName"));
				casts.add(cast);
			}
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
		return casts;
	}
	
	public List<Cast> readAllCastsForMovie(){
		List<Cast> casts = new ArrayList<Cast>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForMovieSql);
			results = statement.executeQuery();
			while(results.next()){
				Cast cast = new Cast(results.getString("id"), results.getString("actroId"), results.getString("movieId"), results.getString("characterName"));
				casts.add(cast);
			}
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
		return casts;
	}
	
	public Cast readCastForId(){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCastForIdSql);
			results = statement.executeQuery();
			if(results.next()){
				Cast cast = new Cast(results.getString("id"), results.getString("actroId"), results.getString("movieId"), results.getString("characterName"));
				return cast;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		return null;
	}
	
	public void updateCast(String castId, Cast newCast){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCastSql);
			statement.setString(1, newCast.getActorId());
			statement.setString(2, newCast.getMovieId());
			statement.setString(3, newCast.getCharacterName());
			statement.setString(4, newCast.getId());
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
	
	public void deleteCast(String castId){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCastSql);
			statement.setString(1, castId);
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
	
	DataSource ds;
	public CastManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/moives");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
