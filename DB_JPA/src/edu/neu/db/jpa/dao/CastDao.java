package edu.neu.db.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.db.jpa.movies.Actor;
import edu.neu.db.jpa.movies.Cast;
import edu.neu.db.jpa.movies.Movie;

public class CastDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_JPA");
	EntityManager em = null;
	
	public void createCast(int actorId, int movieId, Cast cast){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(cast);
		
		Actor actor = em.find(Actor.class, actorId);
		Movie movie = em.find(Movie.class, movieId);
		
		cast.setActorInMovie(actor);
		cast.setMovieActedIn(movie);
		em.merge(cast);
		
		actor.getCasts().add(cast);
		movie.getCasts().add(cast);
		em.merge(movie);
		em.merge(actor);
		
		em.getTransaction().commit();
		em.close();
 	}
	
	public Cast getCast(int castId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Cast cast = em.find(Cast.class, castId);
		
		em.getTransaction().commit();
		em.close();
		return cast;
	}
	
	public List<Cast> getCastsForMovie(int movieId){
		List<Cast> casts = new ArrayList<Cast>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, movieId);
		casts = movie.getCasts();
		
		em.getTransaction().commit();
		em.close();
		return casts;
	}
	
	public void changeCharacterForCast(int castId, String newCharacter){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Cast cast = em.find(Cast.class, castId);
		cast.setCharacter(newCharacter);
		em.merge(cast);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args){
		//Test CreateCast Method
		/*
		CastDao castDao = new CastDao();
		Cast cast = new Cast();
		cast.setId(1);
		cast.setCharacter("hahaha");
		
		castDao.createCast(1, 1, cast);
		*/
	}
}
