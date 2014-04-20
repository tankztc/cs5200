package edu.neu.db.jpa.movies;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cast {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String characterName;
	private Date dateActedInMovie;
	
	private Movie movieActedIn;
	private Actor actorInMovie;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCharacter() {
		return characterName;
	}
	public void setCharacter(String character) {
		this.characterName = character;
	}
	public Date getDateActedInMovie() {
		return dateActedInMovie;
	}
	public void setDateActedInMovie(Date dateActedInMovie) {
		this.dateActedInMovie = dateActedInMovie;
	}
	public Movie getMovieActedIn() {
		return movieActedIn;
	}
	public void setMovieActedIn(Movie movieActedIn) {
		this.movieActedIn = movieActedIn;
	}
	public Actor getActorInMovie() {
		return actorInMovie;
	}
	public void setActorInMovie(Actor actorInMovie) {
		this.actorInMovie = actorInMovie;
	}
	
	public Cast() {
		super();
	}
	public Cast(int id, String character, Date dateActedInMovie,
			Movie movieActedIn, Actor actorInMovie) {
		super();
		this.id = id;
		this.characterName = character;
		this.dateActedInMovie = dateActedInMovie;
		this.movieActedIn = movieActedIn;
		this.actorInMovie = actorInMovie;
	}
	
	
}
