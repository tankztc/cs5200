package edu.neu.db.jpa.movies;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	private Date date;
	
	private User commentUser;
	private Movie commentMovie;
	
	public User getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}
	public Movie getCommentMovie() {
		return commentMovie;
	}
	public void setCommentMovie(Movie commentMovie) {
		this.commentMovie = commentMovie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Review(int id, String comment, Date date, User commentUser,
			Movie commentMovie) {
		super();
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.commentUser = commentUser;
		this.commentMovie = commentMovie;
	}
	public Review() {
		super();
	}
	
	
}
