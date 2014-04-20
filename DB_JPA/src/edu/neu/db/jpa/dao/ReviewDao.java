package edu.neu.db.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.db.jpa.movies.Movie;
import edu.neu.db.jpa.movies.Review;
import edu.neu.db.jpa.movies.User;

public class ReviewDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_JPA");
	EntityManager em = null;
	
	public void createReview(int userId, int movieId, Review review){
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
	
	public Review getReview(int reviewId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review review = em.find(Review.class, reviewId);
		
		em.getTransaction().commit();
		em.close();
		return review;
	}
	
	public void updateReview(int reviewId, Review newReview){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review review = em.find(Review.class, reviewId);
		review.setComment(newReview.getComment());
		review.setCommentMovie(newReview.getCommentMovie());
		review.setCommentUser(newReview.getCommentUser());
		review.setDate(newReview.getDate());
		em.merge(review);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteReview(int reviewId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review review = em.find(Review.class, reviewId);
		User user = new User();
		user = review.getCommentUser();
		user.getReviews().remove(review);
		em.persist(user);
		
		Movie movie = new Movie();
		movie = review.getCommentMovie();
		movie.getReviews().remove(review);
		em.persist(movie);
		
		em.remove(review);	
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args){
	
		//Test CreateReview Method		
		/*
	 
		ReviewDao reviewDao = new ReviewDao();
		Review review = new Review();
		review.setComment("asdf");
		review.setId(1);
		
		reviewDao.createReview(1, 1, review);*/
		
		//Test DeleteReview Method
		/*
		ReviewDao reviewDao = new ReviewDao();
		reviewDao.deleteReview(2);
		*/
		
	}
}
