
import java.util.Date;


public class Actor {
	private String id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Actor(){}
	
	public Actor(String id,String firstName,String lastName,Date dateOfBirth){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.dateOfBirth=dateOfBirth;
	}
	
}
