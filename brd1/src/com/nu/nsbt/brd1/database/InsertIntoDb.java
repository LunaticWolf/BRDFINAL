package com.nu.nsbt.brd1.database;


import com.nu.nsbt.brd1.customer.customerdata.*;
import com.nu.nsbt.brd1.customer.validation.*;
import com.nu.nsbt.brd1.serverconnection.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;



//Database Input
public class InsertIntoDb implements Dao 
{
	
	//Initialization
	Customer customer;
	Connection connection = null;
	ConnectionI conn = null;
	PreparedStatement preparedstatement = null;
	int rowsAffected = 0;

	
	
	//-------------------------------------------------------------------------------------------------------------

	
	//Interface Behaviour Override : 3
	public Connection connection() 
	{
		conn = new OracleConnection();
		connection = conn.myConnection();
		try 
		{
			//Connection To Database: 
		    System.out.println("Connection To Database : Sucessfully Connected ");
			connection.setAutoCommit(false);
			//Initial Commit : False
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection To Database: Unsucessful..");
			e.printStackTrace();
		}
		return connection;
	}
	
	
	//------------------------------------------------------------------------------------------------------------
	
	
	
	//Rejection Condition Check: 1
	public void conditionCheck(String server, String str, String rejection, Customer customer,FileWriter bw) 
	{
		System.out.println("\nRead Record: Successfully done..\nProceeding to Validation... ");
		//Rejection Check: At Record Level
		if (rejection.equalsIgnoreCase("r")) 
		{
			Rejection r = new Rejection();

			try 
			{
				//Record level processing
				System.out.println("\nFile under Process - \nTesting the file at record level...\n Invalid Records will be logged under a file named ErrorFile.txt\n");	
				r.recordLevel(server, str, customer,bw);
			} 


			catch (IOException e) 
			{
                //Exception: At Record Level Rejection
				System.out.println("File Process Error..!!\nCould not test the file under Record Level");			    
                e.printStackTrace();
		    	System.exit(0);
			}
		} 
		
		/*else 
		{
			//Rejection Check: At File Level
			Rejection r = new Rejection();
			//File Level Processing
			System.out.println("File under Process - \nTesting the file at record level...\n Invalid Records will be logged under a file named ErrorFile.txt\n");
			r.fileLevel(server, str, customer,bw);
		}*/



        //Rejection Check: At File Level
		else if(rejection.equalsIgnoreCase("f"))
		{

			Rejection r = new Rejection();
			try
			{
				//File Level Processing
				System.out.println("File under Process - \nTesting the file at record level...\n Invalid Records will be logged under a file named ErrorFile.txt\n");
				r.fileLevel(server, str, customer ,bw);
			}
			
			//Exception: At Record Level Rejection
			catch (Exception e)
			{
				System.out.println("File Process Error..!!\nCould not test the file under File Rejection Level");
				e.printStackTrace();
				System.exit(0);
			}
		}
		else
		{
			System.out.println("Invalid Rejection Level Selection ..\n Please Retry..");
			System.exit(0);
		}
	}
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	// Data Fetch: Unique : 4
	
	public TreeSet<String> fetchCustomerCode()
	{
		TreeSet<String> ts=new TreeSet<String>();
		
		try 
		{
			//Initialization
			Connection connection=connection();
			Statement statement=null;
			ResultSet resultset=null;
			statement=connection.createStatement();
			
			//ResultSet<==Customer_Code
			resultset=statement.executeQuery("select customer_code from TABLE67");
			
			while(resultset.next())
			{
				String code=resultset.getString(1);
				
				ts.add(code);
			}
		
	}
		catch(Exception e)
		{
			System.out.println("Error Occured..\n Could not execute result set to check Costomer Record Uniqueness");
			
		}
		return ts;
	}
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------	
    //Inserting into DataBase.

	public int inputbd(String server, Customer customer, Connection conn) {
		try 
		{
			//preparedstatement<==Prepared Statement <== Executes Insert Query into database
			preparedstatement = conn.prepareStatement("insert into TABLE67 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			preparedstatement.setInt(1, customer.getCustomerId());
			preparedstatement.setString(2, customer.getCustomerCode());
			preparedstatement.setString(3, customer.getCustomerName());
			preparedstatement.setString(4, customer.getCustomerAddress1());
			preparedstatement.setString(5, customer.getCustomerAddress2());
			preparedstatement.setInt(6, customer.getCustomerPinCode());
			preparedstatement.setString(7, customer.getEmailAddress());
			preparedstatement.setString(8, customer.getContactNumber());
			preparedstatement.setString(9, customer.getPrimaryConatctPerson());
			preparedstatement.setString(10, customer.getRecordStatus());
			preparedstatement.setString(11, customer.getActiveInactiveFlag());
			preparedstatement.setString(12, customer.getCreateDate());
			preparedstatement.setString(13, customer.getCreatedBy());
			preparedstatement.setString(14, customer.getModifiedDate());
			preparedstatement.setString(15, customer.getModifiedBy());
			preparedstatement.setString(16, customer.getAuthorizedDate());
			preparedstatement.setString(17, customer.getAuthorizedBy());

			rowsAffected = preparedstatement.executeUpdate();
			
		} 
		catch(SQLException e) 
		{
			System.err.println("\nDatabase ERROR OCCURED");
		}
		return rowsAffected;
	}

}
