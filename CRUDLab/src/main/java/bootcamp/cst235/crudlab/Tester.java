package bootcamp.cst235.crudlab;

import java.util.List;

/**
 * Test driver for the contact database application
 * @author roy
 *
 */
public class Tester {

	public static void main(String[] args) {
		//Create a ContactDataSource object which opens a connection to the database
		ContactDataSource source = new ContactDataSource(false);

		//Create a database to hold customer contacts
		System.out.println("\n**** CREATING DATABASE AND CONTACTS TABLE ****");
		source.createDatabaseAndTable();
		
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
		
		//Update contacts in the contacts table
		System.out.println("\n**** UPDATING CONTACTS ****");
		source.updateContact("Damien Miller", "Yogi Berra", "999-999-9999");
		source.updateContact("Mark Grace", "Steve Garvey", "111-111-1111");
		source.updateContact("Craig Counsell", "Ketel Marte", "222-222-2222");
		
		//Delete contacts from the contacts table
		System.out.println("\n**** DELETING CONTACTS ****");
		source.deleteContact("Curt Schilling");
		source.deleteContact("Curt Schilling");  //should give zero records
		
		//Retrieve and print all contacts from the DB
		System.out.println("\n**** RETRIEVING ALL CONTACTS ****");
		List<Contact> contactList = source.makeContactListFromDatabase();
		
		//Iterate through the list and print each contact
		if(contactList != null && contactList.size() > 0) {
			System.out.println("\nID\tNAME\t\tPHONE");
			System.out.println("----------------------------------------");
			for(Contact c : contactList) {
				System.out.println(c.toString());
			}
		}
		else {
			System.out.println("\n*** NO RECORDS TO PRINT");
		}

		//Close the database
		source.close();
	}

}
