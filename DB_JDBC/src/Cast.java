

public class Cast {
	private String Id;
	private String actorId;
	private String movieId;
	private String characterName;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Cast(String id, String actorId, String movieId, String characterName) {
		super();
		Id = id;
		this.actorId = actorId;
		this.movieId = movieId;
		this.characterName = characterName;
	}
	
	
	
}
