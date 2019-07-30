package bootcamp.cst235.crudlab;

import java.util.List;

/**
 * Interface of CRUD functions that must be implemented by the application
 * @author Roy Chancellor (suggested by instructor James Sparks)
 */
public interface DataSource {
	/**
	 * Closes a data connection
	 */
	void close();

	/**
	 * Deletes and recreates the data source
	 * @return true if successful, false if not
	 */	
	boolean createDatabaseAndTable();

	/**
	 * Adds a Contact
	 * @param contactName the name of the contact being added
	 * @param contactPhone the phone number of the contact being added
	 * @return true if successful, false if not
	 */
	boolean addContact(String contactName, String contactPhone);

	/**
	 * Updates a Contact
	 * @param oldName the name of the contact being updated
	 * @param contactName the name of the contact being updated
	 * @param contactPhone the phone number of the contact being updated
	 * @return true if successful, false if not
	 */
	boolean updateContact(String oldName, String contactName, String contactPhone);

	/**
	 * Deletes a Contact
	 * @param contactName the name of the contact being deleted
	 * @return true if successful, false if not
	 */
	boolean deleteContact(String contactName);

	/**
	 * Converts record fields into Contact fields and returns a list of Contact objects
	 * @return the list of contacts
	 */
	List<Contact> makeContactListFromDatabase();
}
