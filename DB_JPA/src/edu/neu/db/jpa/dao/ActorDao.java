package edu.neu.db.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.db.jpa.movies.Actor;
import edu.neu.db.jpa.movies.Cast;
import edu.neu.db.jpa.movies.Movie;

public class ActorDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_JPA");
	EntityManager em = null;
	
	public void createActor(Actor newActor){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(newActor);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> getAllActors(){
		List<Actor> actors = new ArrayList<Actor>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select actor from Actor actor");
		actors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return actors;
	}
	
	public List<Cast> getCastForActor(int actorId){
		List<Cast> casts = new ArrayList<Cast>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, actorId);
		casts = actor.getCasts();
		
		em.getTransaction().commit();
		em.close();
		return casts;
	}
	
	public List<Movie> getMoviesForActor(int actorId){
		List<Movie> movies = new ArrayList<Movie>();
		List<Cast> casts = new ArrayList<Cast>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, actorId);
		casts = actor.getCasts();
		for(Cast cast: casts){
			Movie movie = new Movie();
			movie = cast.getMovieActedIn();
			movies.add(movie);
		}
		
		em.getTransaction().commit();
		em.close();
		return movies;
	}
	
	public static void main(String[] args){
		
		//Test CreateActor Method		
		/*
		ActorDao actorDao = new ActorDao();
		Actor actor = new Actor(4, "asd", "asdf", null, null);
		actorDao.createActor(actor);
		*/
		
		//Test ListActors Method
		/*
		ActorDao actorDao = new ActorDao();
		List<Actor> actors = new ArrayList<Actor>();
		actors = actorDao.getAllActors();
		
		for(Actor actor : actors){
			System.out.println(actor);
		}
		*/
	}
	
}
