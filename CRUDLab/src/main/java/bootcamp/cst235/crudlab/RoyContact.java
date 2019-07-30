package bootcamp.cst235.crudlab;

/**
 * Controller for the Contacts CRUD application
 * @author Roy Chancellor
 */
public class RoyContact {
	//Create a ContactDataSource object which opens a connection to the database
	private ContactDataSource contactSource;
	private FrontEnd ui;
	
	public RoyContact() {
		this.contactSource = new ContactDataSource(false);
		this.ui = new FrontEnd(contactSource);
	}
	
	/**
	 * where the program begins
	 */
	public void controller() {
		boolean keepRunning = false;
		do {
			//Connect to a user-specified database
			keepRunning = contactSource.connectToDatabase();
			
			//If the database is connected, work with the contacts
			if(contactSource.isConnectedToDb()) {
				//Create a database to hold customer contacts
				//COMMENT OUT LATER FOR PERSISTENCE
				System.out.println("\n**** CREATING DATABASE AND CONTACTS TABLE ****");
				contactSource.createDatabaseAndTable();
				
				//Add contacts to the contacts table
				//COMMENT OUT LATER FOR PERSISTENCE
				makeContacts(contactSource);
				
				//*** Begin the program
				boolean isRunning = true;
				do {
					//Make a list of contacts from the contact database
					contactSource.makeContactListFromDatabase();
		
					//Show the main menu
					ui.showMainMenu();
					
					//Get and process the user's option
					final int NUM_OPTIONS = 3;
					int userOption = FrontEnd.getIntFromUser(FrontEnd.MENU_EXIT, NUM_OPTIONS,
						"Oops, enter a value between " + FrontEnd.MENU_EXIT + " and " + NUM_OPTIONS);
					switch(userOption) {
						case 0:
							isRunning = doExit();
							break;
						case 1:
							doCreateContact();
							break;
						case 2:
							doModifyContact(Constants.UPDATE);
							break;
						case 3:
							doModifyContact(Constants.DELETE);
							break;
						default:
							System.out.println("\nInvalid option...should never get here!");
							break;
					}
				} while(isRunning);
			}
		} while(keepRunning);
	}
	
	/**
	 * Method to CREATE a contact in the database
	 */
	private void doCreateContact() {
		ui.promptForContactInfo(Constants.CREATE);
		contactSource.addContact(ui.getContactName(), ui.getContactPhone());
	}
	
	/**
	 * Method to UPDATE or DELETE an existing contact from the database
	 * @param action requires Constants.UPDATE or Constants.DELETE or 
	 */
	private void doModifyContact(int action) {
		//Get the contact name to modify
		String nameToUpdate = ui.getNameToModify(action);

		//Process the transaction
		if(nameToUpdate != null) {
			if(action == Constants.UPDATE) {
				ui.promptForContactInfo(Constants.UPDATE, nameToUpdate);
				contactSource.updateContact(nameToUpdate, ui.getContactName(), ui.getContactPhone());
			}
			else if(action == Constants.DELETE) {
				contactSource.deleteContact(nameToUpdate);
			}
			else {
				//Should NEVER get here because calling method should send correct constant
				FrontEnd.showErrorMessage("INTERNAL ERROR:");
				new Exception().printStackTrace();
			}
		}
		else {
			System.out.println("SQL Error: " + nameToUpdate + " not found...this is unexpected!");
			new Exception().printStackTrace();
		}
	}

	/**
	 * Method to exit the application, making sure to close the database connection
	 * @return false
	 */
	private boolean doExit() {
		System.out.println("\n*** Exiting...");

		//Close the database
		contactSource.close();

		return false;
	}
	
	/**
	 * Makes the initial contact list
	 * @param source the database source object
	 */
	private void makeContacts(ContactDataSource source) {
		//Add contacts to the contacts table
		System.out.println("\n**** ADDING CONTACTS ****");
		source.addContact("Tony Womack", "123-456-7890");
		source.addContact("Craig Counsell", "234-567-8901");
		source.addContact("Luis Gonzales", "345-678-9012");
		source.addContact("Matt Williams", "456-789-0123");
		source.addContact("Steve Finley", "567-890-1234");
		source.addContact("Danny Bautista", "678-901-2345");
		source.addContact("Mark Grace", "789-012-3456");
		source.addContact("Damien Miller", "890-123-4567");
		source.addContact("Curt Schilling", "901-234-5678");
		source.addContact("Randy Johnson", "012-345-6789");
	}	
}
