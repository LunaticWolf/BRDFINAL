package com.nu.nsbt.brd1.customer.validation;

import com.nu.nsbt.brd1.database.*;
import com.nu.nsbt.brd1.customer.customerdata.*;
import com.nu.nsbt.brd1.customer.entrycontroller.Input;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeSet;
import java.io.FileWriter;
import java.io.IOException;



public class Rejection 
{
     	//Validate Object ==> validate calls Valid Methods
	    ValidationI vm = new ValidationMethods();

	
	    //Checking For Validity At Record Level
		public void recordLevel(String server, String str, Customer customer,FileWriter bw) throws IOException 
		{

		Dao dao = new InsertIntoDb();
		TreeSet<String> set = dao.fetchCustomerCode();

		//Validated Boolean values
		boolean code = vm.validCustomerCode(customer, set);
		set.add(customer.getCustomerCode());

		boolean name = vm.validCustomerName(customer);

		boolean pinCode = vm.validPinCode(customer);

		boolean record = vm.validRecordStatus(customer);

		boolean flag = vm.validFlag(customer);

		boolean email = vm.validEmail(customer);

		//Connection to Database ==> Conn
		Connection connection = dao.connection();
		
		
		//Validity Check
		if (code && name && record && pinCode && flag && email) 
		{
			
			//inputdb
			int rowsAffected = dao.inputbd(server, customer, connection);

			if (rowsAffected > 0)
				System.out.println("More than One Record Afftected \nSuccessfully executed..");

			else
				System.out.println("Rejection\nAt Record Level\nValidity Check:Some error ocuured");
				System.err.println("ERROR");

			try 
			{
				System.out.println("\nCondition Checked Status: Commit");
				connection.commit();
				connection.close();
				
			} 
			catch (SQLException e) 
			{
				System.out.println("\nSQL Exception: Condition Status Check Failed");
				e.printStackTrace();
			}
			catch(Exception e)
			{
				System.out.println("\nIo exception: Condition Status Check Failed");
				e.printStackTrace();
			}
			
		} 
		else 
		{
			System.out.println("\n Rejection:\nAt Record Level: Invalid Records ..");

			try
			{
				String newLine = System.getProperty("line.separator");
				
				bw.append(newLine+customer.getCustomerCode()+"~"+customer.getCustomerName()+"~"+customer.getCustomerAddress1()+"~"
				+customer.getCustomerAddress2()+"~"+customer.getCustomerPinCode()+"~"+customer.getEmailAddress()+"~"
				+customer.getContactNumber()+"~"+customer.getPrimaryConatctPerson()+"~"+customer.getRecordStatus()
				+"~"+customer.getActiveInactiveFlag()+"~"+customer.getCreatedBy()+"~"+customer.getCreateDate()
				+"~"+customer.getAuthorizedBy()+"~"+customer.getAuthorizedDate()+"~"+customer.getModifiedBy()
				+"~"+customer.getModifiedDate()+newLine);
//				System.err.println("Already exist!! ");
				
				
				System.out.println("\nData Error ");
				if (code == false) 
				{
				//System.out.println("not working here !");
					bw.append("Error in code"+newLine);
					bw.append("Error in Customer Code"+newLine);
					bw.flush();
				} 
				else if (name == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in Name"+newLine);
					bw.flush();
				} 
				else if (pinCode == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in Pincode"+newLine);
					bw.flush();
				} 
				else if (record == false) 
				{
					bw.append("Error in Code"+newLine);	
					bw.append("Error in Record"+newLine);
					bw.flush();
				} 
				else if (flag == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in Flag Value"+newLine);
					bw.flush();
				} else if (email == false) {
					bw.append("Error in code"+newLine);
					bw.append("Error in Email Format"+newLine);
					bw.flush();
				}
			} 
			catch (IOException e)
			{
				System.out.println("\nError Occcured While Writing Records.");
				e.printStackTrace();
			}

		}
	}

	public void fileLevel(String server, String str, Customer customer,
			FileWriter bw)
	{
		Dao dao = new InsertIntoDb();
		TreeSet<String> set = dao.fetchCustomerCode();

		int fetchcount = 0;

		boolean code = vm.validCustomerCode(customer, set);
		set.add(customer.getCustomerCode());

		boolean name = vm.validCustomerName(customer);

		boolean pinCode = vm.validPinCode(customer);

		boolean record = vm.validRecordStatus(customer);

		boolean flag = vm.validFlag(customer);

		boolean email = vm.validEmail(customer);

		Connection conn = dao.connection();

		if (code && name && record && pinCode && flag && email) 
		{
			
			
			//inputbd
			int rowsAffected = dao.inputbd(server, customer, conn);
			fetchcount+=1;

			if (rowsAffected > 0)
				System.out.println("Done!!");
			else
				System.out.println("Some Error Occured");
		}
		else 
		{

		System.out.println("At File Level Rejection:\nError Occured..");
			try 
			{
				String newLine = System.getProperty("line.separator");
				bw.append(newLine+customer.getCustomerCode()+"~"+customer.getCustomerName()+"~"+customer.getCustomerAddress1()+"~"
				+customer.getCustomerAddress2()+"~"+customer.getCustomerPinCode()+"~"+customer.getEmailAddress()+"~"
				+customer.getContactNumber()+"~"+customer.getPrimaryConatctPerson()+"~"+customer.getRecordStatus()
				+"~"+customer.getActiveInactiveFlag()+"~"+customer.getCreatedBy()+"~"+customer.getCreateDate()
				+"~"+customer.getAuthorizedBy()+"~"+customer.getAuthorizedDate()+"~"+customer.getModifiedBy()
				+"~"+customer.getModifiedDate()+newLine);
				
				if (code == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in Customer code"+newLine);
					bw.flush();
				}
				else if (name == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Code Error"+newLine);
					bw.flush();
				} 
				else if (pinCode == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in pincode"+newLine);
					bw.flush();
				} 
				else if (record == false) 
				{
					bw.append("Error in Code"+newLine);	
					bw.append("Error in rRecord"+newLine);
					bw.flush();
				} 
				else if (flag == false) 
				{
					bw.append("Error in Code"+newLine);
					bw.append("Error in Flag Value"+newLine);
					bw.flush();
				}
				else if (email == false) 
				{
					bw.append("Error in code"+newLine);
					bw.append("Error in Email Format"+newLine);
					bw.flush();
				}
				conn.rollback();

			}
			catch (Exception e) 
			{
				System.out.println("\nAt File Level Rejection:Some Error Occured..");
				e.printStackTrace();
			}
			
			if (Input.recordsProcessed == fetchcount) 
			{
				try 
				{
					
					//Commit Sucessfully done
					System.out.print("\nAt File Level Rejection:\nRecord Successfully Stored");
					conn.commit();
				} 
				catch (SQLException e) 
				{
					System.out.println("\nAt File Level Rejection:\n Some Error occurred..");
					e.printStackTrace();
				}
				
				/*finally
				{
				connection.close();
				}*/

			}

		}
	}
}
