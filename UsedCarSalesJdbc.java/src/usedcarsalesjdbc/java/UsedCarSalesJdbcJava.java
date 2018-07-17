
package usedcarsalesjdbc.java;

/**
 *
 * @author Tiffany Holden
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Scanner;

public class UsedCarSalesJdbcJava {

	private static OracleDataSource ods = null;
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static String query;
        private static Boolean executeAgain;
	private static Scanner scannerIn = new Scanner(System.in);

   public static void main(String[] args) throws SQLException {

       dbConnect();
       dealershipTable();
       carsTable();
       customersTable();
       salespersonsTable();
       salesTable();
       
       System.out.println("Perform Action on Table");
       do{
       System.out.println("Would you like to Insert(1), Update(2), Delete(3) or Retrieve(4)"
               + "\n\t Please enter 1-4: ");
       int selection = scannerIn.nextInt();
       scannerIn.nextLine();
	   switch (selection) {
		case 1: insertRecord();
                    break;
                case 2: updateRecord();
                    break;
                case 3: deleteRecord();
                    break;
                case 4: selectRecord();
                    break;
           }
          System.out.print("Perform another action? Y OR N: ");
          String input = scannerIn.next();
           if (input.equalsIgnoreCase("Y")){
               executeAgain = true;
           }
           else {
               executeAgain = false;
           }  
        System.out.println();
   }
        while (executeAgain);
   }
   
       
	
   public static void dbConnect() {

	try{
	ods = new OracleDataSource();
	String url = "jdbc:oracle:thin:@//localhost:1521/oracle";
	ods.setURL(url);
	ods.setUser("HR");
	ods.setPassword("hr");
	conn = ods.getConnection();
	conn.setAutoCommit(false);
	boolean connected = conn.isValid(10);

	if(connected)
	   System.out.println("Successfully Connected.");
	else
	   System.out.println("Failed.  Not Connected.");

	}

	catch (SQLException e) {
	   System.out.println(e);
	}
   }

   public static void dealershipTable() throws SQLException {

	try{
	query="CREATE TABLE Dealership" +
		"(locationID VARCHAR2(8)," +
		"dealershipName VARCHAR2(20),"+
		"streetAddress VARCHAR2(30),"+
		"city VARCHAR2(20),"+
		"state VARCHAR2(2),"+
		"CONSTRAINT locationID PRIMARY KEY(locationID))";

	stmt = conn.createStatement();
	stmt.executeUpdate(query); 
	}

	catch (SQLException e) {
	   System.out.println(e);
	}
	finally {
	   if(stmt != null)
		System.out.println("Dealership Table Created.");
	   stmt.close();
	}
   }

   public static void carsTable() throws SQLException {

	try{
	query="CREATE TABLE Cars" +
		"(VIN VARCHAR2(16)," +
		"Make VARCHAR2(20) NOT NULL,"+
		"Model VARCHAR2(20) NOT NULL,"+
		"Color VARCHAR2(15),"+
		"Price NUMBER(8,2)NOT NULL,"+
		"Mileage NUMBER(8,2),"+
		"locationID VARCHAR2(8),"+
		"CONSTRAINT VIN PRIMARY KEY(VIN),"+
		"CONSTRAINT carlocId_fk FOREIGN KEY(locationID) REFERENCES Dealership(locationID),"+
		"CONSTRAINT check_price CHECK(Price > 0))";

	stmt = conn.createStatement();
	stmt.executeUpdate(query); 
	}
        
	finally  {
	   if(stmt != null)
		System.out.println("Cars Table Created.");
	   stmt.close();
	}
   }

   public static void customersTable() throws SQLException {

	try{
	query="CREATE TABLE Customers" +
		"(customerID VARCHAR2(10)," +
		"firstName VARCHAR2(20) NOT NULL,"+
		"lastName VARCHAR2(20) NOT NULL,"+
		"email VARCHAR2(30),"+
		"phone VARCHAR2(12),"+
		"CONSTRAINT customerID PRIMARY KEY(customerID))";

	stmt = conn.createStatement();
	stmt.executeUpdate(query); 
	}
        
	finally {
	   if(stmt != null)
		System.out.println("Customers Table Created.");
	   stmt.close();
	}
   }

   public static void salespersonsTable() throws SQLException {

	try{
	query="CREATE TABLE Salespersons" +
		"(salespersonID VARCHAR2(10)," +
		"firstName VARCHAR2(20) NOT NULL,"+
		"lastName VARCHAR2(20) NOT NULL,"+
		"email VARCHAR2(30),"+
		"phone VARCHAR2(12),"+
		"CONSTRAINT salespersonID PRIMARY KEY(salespersonID))";

	stmt = conn.createStatement();
	stmt.executeUpdate(query); 
	}

	finally {
	   if(stmt != null)
		System.out.println("Salesperson Table Created.");
	   stmt.close();
	}
   }

   public static void salesTable() throws SQLException {

	try{
	query="CREATE TABLE Sales" +
		"(saleID VARCHAR2(10)," +
		"VIN VARCHAR2(16) NOT NULL,"+
		"customerID VARCHAR2(10) NOT NULL,"+
		"salespersonID VARCHAR2(10) NOT NULL,"+
		"CONSTRAINT saleID PRIMARY KEY(saleID),"+
		"CONSTRAINT customerID_fk FOREIGN KEY(customerID) REFERENCES Customers(customerID),"+
		"CONSTRAINT salespersonID_fk FOREIGN KEY(salespersonID) REFERENCES Salespersons(salespersonID),"+
		"CONSTRAINT VIN_fk FOREIGN KEY(VIN) REFERENCES Cars(VIN))";

	stmt = conn.createStatement();
	stmt.executeUpdate(query); 
	}

	finally {
	   if(stmt != null)
		System.out.println("Sales Table Created.");
	   stmt.close();
	}
   }

   public static void insertRecord() throws SQLException {
	try{
	   System.out.print("Select Table to Insert Record - " +
				"\n\t Dealership = 1" +
				"\n\t Cars = 2" +
				"\n\t Customers = 3" +
				"\n\t Salespersons = 4"+
				"\n\t Sales = 5" +
				"\n\t Enter 1-5: ");
	   System.out.println();
	   int selection = scannerIn.nextInt();
           scannerIn.nextLine();
	   switch (selection) {
		case 1: query = "INSERT INTO Dealership(locationID,dealershipName,streetAddress, city,state) values (?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			System.out.println("Inserting Record into Dealership Table");
			System.out.print("locationID = ");
			String locationInput = scannerIn.nextLine();
			pstmt.setString(1,locationInput);
			System.out.print("\n dealershipName = ");
			String dealershipNameInput = scannerIn.nextLine();
			pstmt.setString(2,dealershipNameInput);
			System.out.print("\n streetAddress = ");
			String addressInput = scannerIn.nextLine();
			pstmt.setString(3,addressInput);
			System.out.print("\n city = ");
			String cityInput = scannerIn.nextLine();
			pstmt.setString(4,cityInput);
			System.out.print("\n state = ");
			String stateInput = scannerIn.nextLine();
			pstmt.setString(5,stateInput);
			pstmt.execute();
			break;

		case 2: query = "INSERT INTO Cars(VIN,make,model,color,price,mileage,locationID) values (?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			System.out.println("Inserting Record into Cars Table");
			System.out.print("VIN = ");
			String VINInput = scannerIn.nextLine();
			pstmt.setString(1,VINInput);
			System.out.print("\n make = ");
			String makeInput = scannerIn.nextLine();
			pstmt.setString(2,makeInput);
			System.out.print("\n model = ");
			String modelInput = scannerIn.nextLine();
			pstmt.setString(3,modelInput);
			System.out.print("\n color = ");
			String colorInput = scannerIn.nextLine();
			pstmt.setString(4,colorInput);
			System.out.print("\n price = ");
			String priceInput = scannerIn.nextLine();
			pstmt.setString(5,priceInput);
			System.out.print("\n mileage = ");
			String mileageInput = scannerIn.nextLine();
			pstmt.setString(6,mileageInput);
                        System.out.print("locationID = ");
			String locationID =  scannerIn.nextLine();
			pstmt.setString(7,locationID);
			pstmt.execute();
			break;
	   
		case 3: query = "INSERT INTO Customers(customerID,firstName,lastName,email,phone) values (?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			System.out.println("Inserting Record into Customers Table");
			System.out.print("customerID = ");
			String customerIDInput = scannerIn.nextLine();
			pstmt.setString(1,customerIDInput);
			System.out.print("\n firstName = ");
			String cfnInput = scannerIn.nextLine();
			pstmt.setString(2,cfnInput);
			System.out.print("\n lastName = ");
			String clnInput = scannerIn.nextLine();
			pstmt.setString(3,clnInput);
			System.out.print("\n email = ");
			String custEmailInput = scannerIn.nextLine();
			pstmt.setString(4,custEmailInput);
			System.out.print("\n phone = ");
			String custPhoneInput = scannerIn.nextLine();
			pstmt.setString(5,custPhoneInput);
			pstmt.execute();
			break;

		case 4: query = "INSERT INTO Salespersons(salespersonID,firstName,lastName,email,phone) values (?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			System.out.println("Inserting Record into Salespersons Table");
			System.out.print("salespersonID = ");
			String salespersonIDInput = scannerIn.nextLine();
			pstmt.setString(1,salespersonIDInput);
			System.out.print("\n firstName = ");
			String sfnInput = scannerIn.nextLine();
			pstmt.setString(2,sfnInput);
			System.out.print("\n lastName = ");
			String slnInput = scannerIn.nextLine();
			pstmt.setString(3,slnInput);
			System.out.print("\n email = ");
			String spEmailInput = scannerIn.nextLine();
			pstmt.setString(4,spEmailInput);
			System.out.print("\n phone = ");
			String spPhoneInput = scannerIn.nextLine();
			pstmt.setString(5,spPhoneInput);
			pstmt.execute();
			break;

		case 5: query = "INSERT INTO Sales(saleID,VIN,customerID,salespersonID) values (?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			System.out.println("Inserting Record into Sales Table");
			System.out.print("saleID = ");
			String saleIDInput = scannerIn.nextLine();
			pstmt.setString(1,saleIDInput);
			System.out.print("\n VIN = ");
			String vinInput = scannerIn.nextLine();
			pstmt.setString(2,vinInput);
			System.out.print("\n customerID = ");
			String cidInput = scannerIn.nextLine();
			pstmt.setString(3,cidInput);
			System.out.print("\n salespersonID = ");
			String spidInput = scannerIn.nextLine();
			pstmt.setString(4,spidInput);
			pstmt.execute();
			break;
	   }
	}
	
	finally {
	   if(pstmt != null)
		System.out.println("Record Inserted Successfully.");
	   pstmt.close();
	}
   }

public static void updateRecord() throws SQLException {

   try {
	System.out.println("Enter table to update: ");
	String table = scannerIn.nextLine();
	do {
	System.out.println("Update Value For: (Enter Column Name to be Updated) ");
	String updateColumn = scannerIn.nextLine();
	System.out.println("Where : (Enter Column Name to Reference) ");
	String refColumn = scannerIn.nextLine();
	query = "UPDATE " + table + " SET " + updateColumn + " = ? WHERE " + refColumn + " = ?";
	pstmt = conn.prepareStatement(query);
	System.out.println("Enter New value for " + updateColumn + " :");
	String newValue = scannerIn.nextLine();
	pstmt.setString(1, newValue);
	System.out.println("Enter Reference value for " + refColumn + " :");
	String refValue = scannerIn.nextLine();
	pstmt.setString(2, refValue);
	pstmt.executeUpdate();
        System.out.print("Update another record? Y OR N: ");
        String input = scannerIn.next();
           if (input.equalsIgnoreCase("Y")){
               executeAgain = true;
           }
           else {
               executeAgain = false;
           }  
        System.out.println();
   }
        while (executeAgain);
   }
   finally {
	   if(pstmt != null)
		System.out.println("Record(s) Updated Successfully.");
	   pstmt.close();
	}
}
public static void deleteRecord() throws SQLException {

   try {
	System.out.println("Enter table holding record to delete: ");
	String table = scannerIn.nextLine();
	do {
	System.out.println("Delete Record Where : (Enter Column Name to Reference) ");
	String refColumn = scannerIn.nextLine();
	query = "DELETE " + table + " WHERE " + refColumn + " = ?";
	pstmt = conn.prepareStatement(query);
	System.out.println("Enter Reference value for " + refColumn + " :");
	String refValue = scannerIn.nextLine();
	pstmt.setString(1, refValue);
	pstmt.executeUpdate();
        System.out.print("Delete another record? Y OR N: ");
        String input = scannerIn.next();
           if (input.equalsIgnoreCase("Y")){
               executeAgain = true;
           }
           else {
               executeAgain = false;
           }  
        System.out.println();
   }
        while (executeAgain);
   }
   finally {
	   if(pstmt != null)
		System.out.println("Record(s) Deleted Successfully.");
	   pstmt.close();
	}
}

public static void selectRecord() throws SQLException {
    
    try {
        System.out.println("Enter table to retrieve data from: ");
        String table = scannerIn.nextLine();
        System.out.println("Retrieve: (Enter column to retrieve) ");
        String retrieve1 = scannerIn.nextLine();
        System.out.print("Add another column to retrieve? Y OR N ");
        String input = scannerIn.nextLine();
           if (input.equalsIgnoreCase("Y")){ 
               System.out.println("Retrieve: (Enter column to retrieve) ");
               String retrieve2 = scannerIn.nextLine();
               System.out.println("Select Record Where : (Enter Column Name to Reference) ");
               String refColumn = scannerIn.nextLine();
               query = "SELECT " + retrieve1 + ", " + retrieve2 + " FROM "
                       + table + " WHERE " + refColumn + " = ?";
               pstmt = conn.prepareStatement(query);
               System.out.println("Enter Reference value for " + refColumn + " :");
               String refValue = scannerIn.nextLine();
               pstmt.setString(1,refValue);
               ResultSet rs = pstmt.executeQuery();
               while(rs.next()){
                   String dataRetrieve1 = rs.getString(retrieve1);
                   String dataRetrieve2 = rs.getString(retrieve2);
                   System.out.println(retrieve1 + " " + dataRetrieve1);
                   System.out.println(retrieve2 + " " + dataRetrieve2);
               }
           }
           else {
               System.out.println("Select Record Where : (Enter Column Name to Reference) ");
               String refColumn = scannerIn.nextLine();
               query = "SELECT " + retrieve1 + " FROM "
                       + table + "WHERE" + refColumn + " = ?";
               pstmt = conn.prepareStatement(query);
               System.out.println("Enter Reference value for " + refColumn + " :");
               String refValue = scannerIn.nextLine();
               pstmt.setString(1,refValue);
               ResultSet rs = pstmt.executeQuery(query);
               while(rs.next()){
                   String dataRetrieve1 = rs.getString(retrieve1);
                   System.out.println(dataRetrieve1);
           }
        }
    }
    finally {
	   if(pstmt != null);
	   pstmt.close();
	}
}
}

   
