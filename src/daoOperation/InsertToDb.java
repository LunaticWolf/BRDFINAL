package daoOperation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
//import java.util.TreeSet;
//java.io.*;
//java.sql.*;



//package import
import connection.ConnectionInterface;
import connection.OracleConnection;
import entityPojo_customer.Customer;
import validation.Rejection;


//Database Input
public  class InsertToDb implements InsertDao{
	
	//Initialization
	//Connection connection =   new OracleConnection();
	//Customer customer=null;
	Connection conn = null;
	ConnectionInterface c=null;
	PreparedStatement ps = null;
	public static HashSet<String> set= new HashSet<>();
	//int r = 0;
	//-------------------------------------------------------------------------------------------------------------
	//Interface Behaviour Override : 3
	public Connection connection(String server){
		c = new OracleConnection();
		
		conn = c.myConnection();
		try{
			//Connection To Database: 
		    System.out.println("Connection To Database : Sucessfully Connected ");
			conn.setAutoCommit(false);
			//Initial Commit : False
		}
		catch (SQLException e){
			System.out.println("Connection To Database: Unsucessful..");
			e.printStackTrace();
		}
		return conn;
	}
	//------------------------------------------------------------------------------------------------------------
	//Rejection Condition Check: 1
	@Override
	public void conditionCheck(String server, String str, String rejection,Customer customer,BufferedWriter bufferedwriter){
		System.out.println("\nRead Record: Successfully done..\nProceeding to Validation... ");
		//Rejection Check: At Record Level
		if (rejection.equalsIgnoreCase("r")){
			Rejection r = new Rejection();
			try{
				//Record level processing
				System.out.println("\nFile under Process - \nTesting the file at record level...\n Invalid Records will be logged under a file named ErrorFile.txt\n");	
				//System.out.println(server + str + bufferedwriter );
				r.recordLevel(server, str, customer,bufferedwriter);	
			  } 
			//Exception: At Record Level Rejection 
			catch (Exception e){
				System.out.println("File Process Error..!!\nCould not test the file under Record Level");
				e.printStackTrace();
				System.exit(0);
			}
		}

		// Rejection Check: At File Level
		else if(rejection.equalsIgnoreCase("f")){
			Rejection r = new Rejection();
			try{
				//File Level Processing
				System.out.println("File under Process - \nTesting the file at record level...\n Invalid Records will be logged under a file named ErrorFile.txt\n");
				r.fileLevel(server, str, customer ,bufferedwriter);
			}
			
			//Exception: At Record Level Rejection
			catch (Exception e){
				System.out.println("File Process Error..!!\nCould not test the file under File Rejection Level");
				e.printStackTrace();
				System.exit(0);
			}
		}
		else{
			System.out.println("Invalid Rejection Level Selection ..\n Please Retry..");
			System.exit(0);
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------
	// Data Fetch: Unique : 4
	public HashSet<String> fetch_customer_code(String server){

		try{
			//Initialization
			Connection conn=connection(server);
			Statement statement=null;
			ResultSet resultset=null;
			statement=conn.createStatement();
			
			//ResultSet<==Customer_Code
			resultset=statement.executeQuery("select cust_code from customer");
			
			
			while(resultset.next()){
				String code=resultset.getString(1);
				set.add(code);
			}
		}
		catch(Exception e){
			System.out.println("Error Occured..\n Could not execute result set to check Costomer Record Uniqueness");
		}
		return set;
	}
	//--------------------------------------------------------------------------------------------------------------
	
	//Inserting into DataBase.
	public int inputDB(String server, Customer customer, Connection conn){
		int r = 0;
		try{
             //ps<==Prepared Statement <== Executes Insert Query into database
			ps = conn.prepareStatement("insert into table67 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setInt(1, customer.getCustomer_id());
			ps.setString(2, customer.getCustomer_code());
			ps.setString(3, customer.getCustomer_name());
			ps.setString(4, customer.getCustomer_address1());
			ps.setString(5, customer.getCustomer_address2());
			ps.setInt(6, customer.getCustomer_pinCode());
			ps.setString(7, customer.getEmail_address());
			ps.setString(8, customer.getContact_number());
			ps.setString(9, customer.getPrimaryContactPerson());
			ps.setString(10, customer.getRecord_status());
			ps.setString(11, customer.getActive_inactiveFlag());
			ps.setString(12, customer.getCreate_date());
			ps.setString(13, customer.getCreated_by());
			ps.setString(14, customer.getModified_date());
			ps.setString(15, customer.getModified_by());
			ps.setString(16, customer.getAuthorized_date());
			ps.setString(17, customer.getAuthorized_by());

			r = ps.executeUpdate();
		} 
		catch (SQLException e){
			System.out.println("Error Occured..\n Couldn't Load Data into DataBase.\n");
		}
		return r;
	}
	//-----------------------------------------------------------------------------
	@Override
	public HashSet<String> fetch_customer_code() {
		// TODO Auto-generated method stub
		return InsertToDb.set;
	}
}
