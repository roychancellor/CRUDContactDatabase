package bootcamp.cst235.crudlab;

/**
 * Class contains constants for use with SQL queries
 * @author Roy Chancellor
 */
public class Constants {
	/**
	 * String constants used throughout the application for SQL commands
	 */
	public static final String DB_URL = "jdbc:mysql://localhost:3306";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "root";  //I KNOW THIS IS NOT OK...FOR DEMO ONLY
	public static final String DB_NAME = "business";
	public static final String CONTACT_TABLE = "contacts";
	public static final String CONTACT_ID = "id";
	public static final String CONTACT_ID_TYPE = "int PRIMARY KEY AUTO_INCREMENT";
	public static final String CONTACT_NAME = "fullName";
	public static final String CONTACT_PHONE = "phoneNumber";
	public static final String CONTACT_NAME_TYPE = "VARCHAR(200)";
	public static final String CONTACT_PHONE_TYPE = "VARCHAR(12)";
	public static final int CREATE = 1;
	public static final int UPDATE = 0;
	public static final int DELETE = -1;
	
	//SQL command to ADD a contact to the table
	public static final String ADD_CONTACT = "INSERT INTO "
		+ DB_NAME + "." + CONTACT_TABLE
		+ "(" + CONTACT_NAME + "," + CONTACT_PHONE + ")"
		+ " VALUES(?,?)";
	
	//SQL command to UPDATE a contact by fullName
	public static final String UPDATE_CONTACT_BY_NAME = "UPDATE " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "SET " + CONTACT_NAME + "=?," + CONTACT_PHONE + "=? "
		+ "WHERE " + CONTACT_NAME + "=?";
	
	//SQL command to UPDATE a contact by id
	public static final String UPDATE_CONTACT_BY_ID = "UPDATE " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "SET " + CONTACT_NAME + "=?," + CONTACT_PHONE + "=? "
		+ "WHERE " + CONTACT_ID + "=?";
	
	//SQL command to DELETE a contact by fullName
	public static final String DELETE_CONTACT_BY_NAME = "DELETE FROM " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "WHERE " + CONTACT_NAME + "=?";
	
	//SQL command to DELETE a contact by id
	public static final String DELETE_CONTACT_BY_ID = "DELETE FROM " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "WHERE " + CONTACT_ID + "=?";
	
	//SQL command to SELECT all contacts and ORDER BY fullName
	public static final String GET_CONTACTS = "SELECT * FROM " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "ORDER BY " + CONTACT_ID;
	
	//SQL command to SELECT a fullName by id
	public static final String ID_TO_NAME = "SELECT " + CONTACT_NAME + " FROM " + DB_NAME + "." + CONTACT_TABLE + " "
		+ "WHERE " + CONTACT_ID + "=";
}
