package bootcamp.cst235.crudlab;

import bootcamp.cst235.crudlab.controller.RoyContact;

/**
 * Starts the RoyContact program
 * @author Roy Chancellor
 *
 */
public class ContactApplication {
	public static void main(String[] args) {
		//Make a new RoyContact object that receives a new ContactDataSource with
		//SQL verbose set to false. The ContactDataSource object will set up
		//a database connection to the MySQL business database
		RoyContact rc = new RoyContact();
		rc.controller();
	}

}
