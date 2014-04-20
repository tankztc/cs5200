package edu.neu.db.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.db.jpa.movies.Movie;
import edu.neu.db.jpa.movies.Review;
import edu.neu.db.jpa.movies.User;

public class UserDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_JPA");
	EntityManager em = null;
	
	public void createUser(User newUser){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(newUser);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Review> getReviewsForUser(int userId){
		List<Review> reviews = new ArrayList<Review>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		User user = em.find(User.class, userId);
		reviews = user.getReviews();
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	public void addReviewForMovie(int userId, int movieId, Review review){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(review);
		
		User user = em.find(User.class, userId);
		Movie movie = em.find(Movie.class, movieId);
		
		review.setCommentUser(user);
		review.setCommentMovie(movie);
		em.merge(review);
		
		user.getReviews().add(review);
		movie.getReviews().add(review);
		em.merge(movie);
		em.merge(user);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public static void main(String[] args){
		UserDao userDao = new UserDao();
		
//		User user = new User(2, "CCCCC" , "asdf", "adsf", "asdf", "adsf", null, null);
//		userDao.createUser(user);
		
		List<Review> reviews = new ArrayList<Review>();
		reviews = userDao.getReviewsForUser(1);
		
		for(Review review: reviews){
			System.out.println(review);
		}
		
	}
}
