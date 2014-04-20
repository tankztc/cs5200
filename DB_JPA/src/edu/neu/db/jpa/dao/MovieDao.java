package edu.neu.db.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.db.jpa.movies.Cast;
import edu.neu.db.jpa.movies.Movie;
import edu.neu.db.jpa.movies.Review;

public class MovieDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_JPA");
	EntityManager em = null;
	
	@SuppressWarnings("unchecked")
	public List<Movie> listAllMovies(){
		List<Movie> movies = new ArrayList<Movie>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select movie from Movie movie");
		movies = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return movies;
	}
	
	public List<Review> getReviewsForMovie(int movieId){
		List<Review> reviews = new ArrayList<Review>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, movieId);
		reviews = movie.getReviews();
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	public List<Cast> getCastForMovie(int movieId){
		List<Cast> casts = new ArrayList<Cast>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, movieId);
		casts = movie.getCasts();
		
		em.getTransaction().commit();
		em.close();
		return casts;
	}
	
}
