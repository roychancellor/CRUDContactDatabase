package bootcamp.cst235.crudlab;

public class Constants {
	/**
	 * String constants used throughout the application for SQL commands
	 */
	public static final String DB_URL = "jdbc:mysql://localhost:3306";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "root";
	public static final String DB_NAME = "business";
	public static final String CONTACT_TABLE = "contacts";
	public static final String CONTACT_ID = "id";
	public static final String CONTACT_ID_TYPE = "int PRIMARY KEY AUTO_INCREMENT";
	public static final String CONTACT_NAME = "fullName";
	public static final String CONTACT_PHONE = "phoneNumber";
	public static final String CONTACT_NAME_TYPE = "VARCHAR(200)";
	public static final String CONTACT_PHONE_TYPE = "VARCHAR(12)";
	
	//SQL command to ADD a contact to the table
	public static final String ADD_CONTACT = "INSERT INTO "
		+ DB_NAME + "." + CONTACT_TABLE
		+ "(" + CONTACT_NAME + "," + CONTACT_PHONE + ")"
		+ " VALUES(?,?)";
	
	//SQL command to UPDATE a contact
	public static final String UPDATE_CONTACT = "UPDATE " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "SET " + CONTACT_NAME + "=?," + CONTACT_PHONE + "=? "
		+ "WHERE " + CONTACT_NAME + "=?";
	
	//SQL command to DELETE a contact
	public static final String DELETE_CONTACT = "DELETE FROM " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "WHERE " + CONTACT_NAME + "=?";
	
	//SQL command to SELECT all contacts and ORDER BY fullName
	public static final String GET_CONTACTS = "SELECT * FROM " + DB_NAME + "." + CONTACT_TABLE + " "
			+ "ORDER BY " + CONTACT_NAME;
}
