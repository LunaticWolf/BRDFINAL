package com.nu.nsbt.brd1.customer.validation;

import java.util.TreeSet;
import java.util.regex.Pattern;

//import Main.Customer;
import com.nu.nsbt.brd1.customer.customerdata.Customer;



public  class ValidationMethods implements ValidationI 
{
	//Validation: Valid Unique Customer Code
	public boolean validCustomerCode(Customer customer, TreeSet<String> set) 
	{
		String val = customer.getCustomerCode();
		if(val.equals("") ||  val.length() > 10 || set.contains(val)) 
		{
			return false;
		}

		return true;
	}

	//Validation: Customer Name
	public boolean validCustomerName(Customer customer)
	{
		String name = customer.getCustomerName();
		if(name.length() < 31)
		{
			return name.matches("[a-zA-Z0-9]+");
		}
		return false;

	}

	
	
	//Vaild Test: Pincode Length<6
	public boolean validPinCode(Customer customer) 
	{
		int length = String.valueOf(customer.getCustomerPinCode()).length();
		if (length == 6)
			return true;
		else
			return false;
	}

	
	
	//Email Validity
	public boolean validEmail(Customer customer)
	{

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"+ "A-Z]{2,7}$";		
		if(customer.getEmailAddress().matches(emailRegex))
		{
		return true;	
		}
		return false;
	}

	
	//RecordStatus Validity
	public boolean validRecordStatus(Customer customer) 
	{
		String val = customer.getRecordStatus();
		if("NMDAR".contains(val.toUpperCase()))
		{
			return true;
		}
          return false;
	}

	
	//Flag Validity
	public boolean validFlag(Customer customer)
	{
		String val = customer.getActiveInactiveFlag();
		if("AI".contains(val.toUpperCase()))
		{
			return true;
		}
		return false;
	}

}

