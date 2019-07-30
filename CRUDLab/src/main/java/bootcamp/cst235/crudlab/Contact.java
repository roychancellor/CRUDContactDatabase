package bootcamp.cst235.crudlab;

/**
 * Class for a contact in a contacts database
 * @author Roy Chancellor
 */
public class Contact {
	/**
	 * Class data for a simple contact
	 */
	private int id;
	private String fullName;
	private String phoneNumber;
	
	//Getters and setters
	/**
	 * gets the id number of the contact
	 * @return id number
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the id number of the contact
	 * @param id the id number of the contact
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * gets the full name of the contact
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * sets the full name of the contact
	 * @param name the full name of the contact
	 */
	public void setFullName(String name) {
		this.fullName = name;
	}
	
	/**
	 * gets the phone number of the contact
	 * @return phone number of the contact
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * sets the phone number of the contact
	 * @param phoneNumber the phone number of the contact xxx-xxx-xxxx
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//Class methods
	@Override
	public String toString() {
		return Integer.toString(id) + "\t" + fullName + "\t" + phoneNumber;
	}
}
