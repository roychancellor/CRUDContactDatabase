package bootcamp.cst235.crudlab;

/**
 * Starts the RoyContact program
 * @author Roy Chancellor
 *
 */
public class Main {
	public static void main(String[] args) {
		//Make a new RoyContact object that receives a new ContactDataSource with
		//SQL verbose set to false. The ContactDataSource object will set up
		//a database connection to the MySQL business database
		RoyContact rc = new RoyContact(new ContactDataSource(false));
		rc.controller();
	}

}
