package bootcamp.cst235.crudlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contains CRUD functions for a VERY basic contact database
 * Implements the DataSource interface
 * @author roy
 *
 */
public class ContactDataSource implements DataSource {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement sql;
	private List<Contact> contacts;
	private boolean verboseSQL;
	
	//Constructor
	/**
	 * Constructor for class: Opens a MySQL database and creates a Statement object
	 * @param verboseSQL when true will print all SQL commands to the console
	 */
	public ContactDataSource(boolean verboseSQL) {
		//Open connection to MySQL DBMS
		try {
			System.out.print("Connecting to " + Constants.DB_URL);
			this.conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER_NAME, Constants.PASSWORD);
			System.out.println("...SUCCESS!");
			
			//Create a Statement object
			try {
				System.out.print("\nCreating a Statement object for the Connection");
				this.stmt = conn.createStatement();
				System.out.println("...SUCCESS!");
			}
			catch(SQLException e) {
				printMethod(new Throwable().getStackTrace()[0].getMethodName());
				e.printStackTrace();
			}
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		this.contacts = new ArrayList<Contact>();
		this.verboseSQL = verboseSQL;
	}
	
	//Getters and Setters
	/**
	 * @return the connection
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @return the stmt (a Statement object)
	 */
	public Statement getStmt() {
		return stmt;
	}

	/**
	 * @return the rs (a ResultSet object)
	 */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * @return the sql (a PreparedStatement)
	 */
	public PreparedStatement getSql() {
		return sql;
	}

	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @return the verboseSQL setting
	 */
	public boolean isVerboseSQL() {
		return verboseSQL;
	}

	/**
	 * @param verboseSQL the verboseSQL to set
	 */
	public void setVerboseSQL(boolean verboseSQL) {
		this.verboseSQL = verboseSQL;
	}

	//Class CRUD methods
	/**
	 * Closes a JDBC database connection
	 */
	public void close() {
		try {
			System.out.print("\nClosing connection to " + Constants.DB_URL);
			this.conn.close();
			System.out.println("...SUCCESS!");
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
	}

	/**
	 * Creates a people database and a contacts table with id, name, phone
	 * @return true if the try block succeeds, false if not
	 */
	public boolean createDatabaseAndTable() {
		boolean success = false;
		try {
			//CREATE the business DATABASE (if it already exists, DROP it first)
			System.out.print("Creating database: " + Constants.DB_NAME);
			
			String sql = "DROP DATABASE IF EXISTS " + Constants.DB_NAME;
			success = stmt.execute(sql);
			sql = "CREATE DATABASE " + Constants.DB_NAME;
			
			if(verboseSQL) System.out.println("\nIssuing SQL: " + sql);
			success = stmt.execute(sql);
			System.out.println("...Success");
			
			//CREATE the contacts TABLE
			System.out.print("Creating table: " + Constants.CONTACT_TABLE);
			sql = "CREATE TABLE " + Constants.DB_NAME + "." + Constants.CONTACT_TABLE
				+ "(" + Constants.CONTACT_ID + " " + Constants.CONTACT_ID_TYPE + ","
				+ Constants.CONTACT_NAME + " " + Constants.CONTACT_NAME_TYPE + ","
				+ Constants.CONTACT_PHONE + " " + Constants.CONTACT_PHONE_TYPE + ")";
			if(verboseSQL) System.out.println("\nIssuing SQL: " + sql);
			success = stmt.execute(sql);
			System.out.println("...Success");
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}

		return success;
	}

	/**
	 * Adds a contact to the contacts table
	 * @param contactName the contact name being added
	 * @param contactPhone the contact phone being added
	 * @return true if the try block succeeds, false if not
	 */
	public boolean addContact(String contactName, String contactPhone) {
		try {
			System.out.print("Adding contact " + contactName + ", " + contactPhone);
			
			//Prepare the SQL statement
			sql = conn.prepareStatement(Constants.ADD_CONTACT);
			if(verboseSQL) printSQL();

			//Populate statement parameters
			sql.setString(1, contactName);
			sql.setString(2, contactPhone);
			
			//Execute SQL statement
			int numRec = sql.executeUpdate();
			
			System.out.println("...Success, " + numRec + " record(s) inserted.");
			
			return true;
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Updates an existing contact in the database
	 * @param oldName the name being updated
	 * @param contactName the new contact name
	 * @param contactPhone the new contact phone
	 * @return true if the try block succeeds, false if not
	 */
	public boolean updateContact(String oldName, String contactName, String contactPhone) {
		try {
			System.out.print("Updating contact [" + oldName + "] to " + contactName + ", " + contactPhone);
			
			//Prepare the SQL statement
			sql = conn.prepareStatement(Constants.UPDATE_CONTACT_BY_NAME);
			if(verboseSQL) printSQL();
			
			//Populate statement parameters
			sql.setString(1, contactName);
			sql.setString(2, contactPhone);
			sql.setString(3, oldName);
			
			//Execute SQL statement
			int numRec = sql.executeUpdate();
			
			System.out.println("...Success, " + numRec + " record(s) updated.");
			
			return true;
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Deletes a contact from the database
	 * @param contactName the name of the contact being deleted
	 * @return true if the try block succeeds, false if not
	 */
	public boolean deleteContact(String contactName) {
		try {
			System.out.print("Deleting contact " + contactName);
			
			//Prepare the SQL statement
			sql = conn.prepareStatement(Constants.DELETE_CONTACT_BY_NAME);
			if(verboseSQL) printSQL();
			
			//Populate statement parameters
			sql.setString(1, contactName);
			
			//Execute SQL statement
			int numRec = sql.executeUpdate();
			
			//Print the result
			if(numRec > 0) {
				System.out.println("...Success, " + numRec + " record(s) deleted.");				
			}
			else {
				System.out.println("...Record did not exist, nothing deleted.");
			}
			
			return true;
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Makes a List of contacts from the contacts in the database
	 * @return the list of contacts
	 */
	public List<Contact> makeContactListFromDatabase() {
		try {
			//Prepare the SQL statement
			String sql = Constants.GET_CONTACTS;
			if(verboseSQL) System.out.println("Executing SQL: " + sql);
			
			//Execute SQL statement and get a result set
			this.rs = stmt.executeQuery(sql);
			
			//Clear the contact list so only the most up-to-date list exists
			this.contacts.clear();
			
			//Process the result set
			while(this.rs.next()) {
				Contact c = new Contact();
				//Read the fields in the current record and store in Contact object
				c.setId(rs.getInt(Constants.CONTACT_ID));
				c.setFullName(rs.getString(Constants.CONTACT_NAME));
				c.setPhoneNumber(rs.getString(Constants.CONTACT_PHONE));
				
				//Add the contact to the list of contacts
				this.contacts.add(c);
			}
			
			return this.contacts;
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Makes a List of contacts from the contacts in the database
	 * @return the list of contacts
	 */
	public String idToName(int id) {
		try {
			//Prepare the SQL statement
			String sql = Constants.ID_TO_NAME + Integer.toString(id);
			if(verboseSQL) System.out.println("Executing SQL: " + sql);
			
			//Execute SQL statement and get a result set
			this.rs = stmt.executeQuery(sql);
			
			//Process the result set
			if(this.rs.next()) {
				return rs.getString(Constants.CONTACT_NAME);
			}
		}
		catch(SQLException e) {
			printMethod(new Throwable().getStackTrace()[0].getMethodName());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Prints a class name
	 * @param methodName the name of the class to print
	 */
	private void printMethod(String methodName) {
		System.out.println("\n***** Error in " + methodName + "\n");
	}
	
	/**
	 * prints the SQL statement to the console
	 */
	private void printSQL() {
		System.out.println("\nExecuting SQL: " + sql.toString());
	}
}
