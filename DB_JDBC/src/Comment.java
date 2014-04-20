
import java.util.Date;


public class Comment {
	private String Id;
	private String username;
	private String movieId;
	private String comment;
	private Date date;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Comment(String id, String username, String movieId, String comment,
			Date date) {
		super();
		Id = id;
		this.username = username;
		this.movieId = movieId;
		this.comment = comment;
		this.date = date;
	}
}
