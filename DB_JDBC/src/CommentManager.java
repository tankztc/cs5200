
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





public class CommentManager {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	String createCommentSql = "INSERT INTO COMMENT (ID,USERNAME,MOVIEID,COMMENT,DATE) VALUES(?,?,?,?,?);";
	String readAllCommentsSql = "SELECT * FROM COMMENT;";
	String readAllCommentsForUsernameSql = "SELECT * FROM COMMENT WHERE USERNAME=?;";
	String readAllCommentsForMovieSql = "SELECT * FROM COMMENT WHERE MOVIEID=?;";
	String readCommentForIdSql = "SELECT * FROM COMMENT WHERE ID=?;"; 
	String updateCommentSql = "UPDATE COMMENT SET COMMENT=? WHERE ID=?;";
	String deleteCommentSql = "DELETE FROM COMMENT WHERE ID=?;";
	
	public void createComment(Comment newComment){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCommentSql);
			statement.setString(1, newComment.getId());
			statement.setString(2, newComment.getUsername());
			statement.setString(3, newComment.getMovieId());
			statement.setString(4, newComment.getComment());
			statement.setDate(5, (Date)newComment.getDate());
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
	
	public List<Comment> readAllComments(){
		List<Comment> comments = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsSql);
			results = statement.executeQuery();
			while(results.next()){
				Comment comment = new Comment(results.getString("id"), results.getString("username"), results.getString("movieId"), results.getString("comment"), results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}
	
	public List<Comment> readAllCommentsForUsername(String username){
		List<Comment> comments = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsForUsernameSql);
			statement.setString(1, username);
			results = statement.executeQuery();
			while(results.next()){
				Comment comment = new Comment(results.getString("id"), results.getString("username"), results.getString("movieId"), results.getString("comment"), results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}
	
	public List<Comment> readAllCommentsForMovie(String movieId){
		List<Comment> comments = new ArrayList<Comment>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsForMovieSql);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			while(results.next()){
				Comment comment = new Comment(results.getString("id"), results.getString("username"), results.getString("movieId"), results.getString("comment"), results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}
	
	public Comment readCommentForId(String commentid){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCommentForIdSql);
			statement.setString(1, commentid);
			results = statement.executeQuery();
			if(results.next()){
				Comment comment = new Comment(results.getString("id"), results.getString("username"), results.getString("movieId"), results.getString("comment"), results.getDate("date"));
				return comment;
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
		return null;
	}
	
	public void updateComment(String commentId,String comment){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCommentSql);
			statement.setString(1, comment);
			statement.setString(2, commentId);
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
	
	public void deleteComment(String commentId){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCommentSql);
			statement.setString(1, commentId);
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
	public CommentManager(){
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/movies");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
