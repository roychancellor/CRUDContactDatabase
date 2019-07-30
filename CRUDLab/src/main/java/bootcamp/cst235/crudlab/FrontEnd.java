package bootcamp.cst235.crudlab;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class that contains front end methods for Contacts CRUD
 * @author Roy Chancellor
 */
public class FrontEnd {
	/**
	 * Class data
	 */
	private ContactDataSource contacts;
	private static Scanner sc = new Scanner(System.in);
	public static final int MENU_EXIT = 0;
	private static final int MAIN_HEADER_LENGTH = 35;
	
	/**
	 * Constructor
	 * @param contacts the list of contacts so the front end an show them
	 */
	public FrontEnd(ContactDataSource contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * shows the menu to choose which database connection to make
	 */
	public static void showConnectionMenu() {
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("WHICH DATABASE CONNECTION?");
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("1. Connect to Localhost:3306");
		System.out.println("2. Connect to AWS");
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("0. Exit Without Connecting");
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("Enter an option:");
	}
	
	/**
	 * shows the main CRUD menu to the user
	 */
	public void showMainMenu() {
		System.out.println();
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("ID\tNAME\t\tPHONE");
		printHeader("-", MAIN_HEADER_LENGTH);
		
		//Check that contacts exist
		if(contacts != null && contacts.getContacts().size() > 0) {
			printContactList();
		}
		else {
			System.out.println("NO CONTACTS TO PRINT");
		}
		
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("1. Add New Contact");
		System.out.println("2. Update Existing Contact");
		System.out.println("3. Delete Existing Contact");
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("0. Exit Application");
		printHeader("-", MAIN_HEADER_LENGTH);
		System.out.println("Enter an option:");
	}
	
	/**
	 * iterates through a list of Contact objects and prints the toString of each item
	 */
	private void printContactList() {
		for(int i = 0; i < contacts.getContacts().size(); i++) {
			System.out.println(contacts.getContacts().get(i).toString());
		}
	}

	/**
	 * Method that prompts the user to create a new contact
	 * @param action requires the use of Constants.CREATE or an error message will appear
	 */
	public void promptForContactInfo(int action) {
		if(action == Constants.CREATE) {
			System.out.println("\nCreating new contact\nEnter contact name and phone number.");
		}
		else {
			showErrorMessage("ERROR: Unknown request");
		}
	}

	/**
	 * Overloaded method that prompts the user to update an existing contact
	 * @param action requires Constant.UPDATE
	 * @param nameToUpdate the name of the contact to update
	 */
	public void promptForContactInfo(int action, String nameToUpdate) {
		if(action == Constants.UPDATE) {
			System.out.println("\nEnter new name and phone number for " + nameToUpdate + ":");
		}
	}
	
	/**
	 * Gets the contact's fullName and phoneNumber as Strings from the user
	 * @return the user's entry
	 */
	public String getContactName() {
		System.out.println("\n\tContact name (first last):");
		return sc.nextLine();
	}
	
	/**
	 * Gets the contact's phone as a String from the user
	 * @return the user's entry
	 */
	public String getContactPhone() {
		System.out.println("\n\tContact phone (xxx-xxx-xxxx):");
		return sc.nextLine();
	}
	
	/**
	 * gets the name of the contact to modify based on the contact's id number which the user enters
	 * @param action requires Constants.UPDATE or Constants.DELETE or will return null
	 * @return the name of the contact to modify
	 */
	public String getNameToModify(int action) {
		if(action == Constants.UPDATE) {
			System.out.print("\nUpdating existing contact\n");
		}
		else if(action == Constants.DELETE) {
			System.out.print("\nDeleting contact\n");
		}

		//Get the contact ID from the user and convert to the contact name
		//THIS IS BECAUSE THE SQL INTERFACE METHODS USE fullName AS THE WHERE CLAUSE
		System.out.println("\nSelect contact ID:");	
		int contactIdToUpdate = getIntFromUser(1, contacts.getContacts().size(),
			"Oops, must enter an integer between 1 and " + contacts.getContacts().size());
		String nameToUpdate = contacts.idToName(contactIdToUpdate);
		if(nameToUpdate != null) {
			return nameToUpdate;
		}
		else {
			return null;
		}
	}
	
	/**
	 * helper method that gets an integer between minValue and maxValue from the user
	 * If the user enters anything other than an integer, catches the exception
	 * and prints the error message received from the method call
	 * @param minValue the minimum value of the menu
	 * @param maxValue the maximum value of the menu
	 * @param errorMessage the error message to display for an invalid entry
	 * @return the integer the user entered
	 */
	public static int getIntFromUser(int minValue, int maxValue, String errorMessage) {
		int selection = 0;
		boolean invalidSelection;
		
		//Loop until the user enters an integer between the given limits
		do {
			invalidSelection = false;
			try {
				selection = sc.nextInt();
				if(selection < minValue || selection > maxValue) {
					showErrorMessage(errorMessage);
					invalidSelection = true;
				}
			}
			catch(InputMismatchException e) {
				showErrorMessage(errorMessage);
				invalidSelection = true;
				sc.nextLine();
			}
		} while(invalidSelection);

		//scan the next line to clear out the newline character before returning
		sc.nextLine();
		
		return selection;
	}
	
	/**
	 * shows the cash error message when user enters the wrong type of cash
	 * @param message the error message to display
	 */
	public static void showErrorMessage(String message) {
		System.out.println("\n" + message);		
	}

	/**
	 * utility method that prints headString numTimes
	 * @param headString
	 * @param numTimes
	 */
	private static void printHeader(String headString, int numTimes) {
		for(int i = 0; i < numTimes; i++)
			System.out.print(headString);
		System.out.println();
	}	
}
