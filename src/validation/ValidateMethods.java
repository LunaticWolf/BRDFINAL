package validation;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import entityPojo_customer.Customer;

public class ValidateMethods implements ValidationI{

	//Validation: Valid Unique Customer Code
	public boolean validCustomerCode(Customer customer, HashSet<String> set){
		String val = customer.getCustomer_code();
		if(val.equals("") ||  val.length() > 10 || set.contains(val)) return false;
		return true;
	}
	//Validation: Customer Name
	@Override
	public boolean validCustomerName(Customer customer){
		String name = customer.getCustomer_name();
		if(name.length() < 31){
			return name.matches("[a-zA-Z0-9]+");
		}
		return false;
	}

	
	//Vaild Test: Pincode Length<6
	@Override
	public boolean validPinCode(Customer customer) 
	{
		int length = (int)(Math.log10(customer.getCustomer_pinCode()) + 1);
		if (length == 6)
			return true;
		return false;
	}

	//Email Validity
	@Override
	public boolean validEmail(Customer customer) 
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"+ "A-Z]{2,7}$";		
		return customer.getEmail_address().matches(emailRegex);
	}

	@Override
	public boolean validRecordStatus(Customer customer){
		String val = customer.getRecord_status();
		return "NMDAR".contains(val.toUpperCase());

	}

	@Override
	public boolean validFlag(Customer customer){
		String val = customer.getActive_inactiveFlag();
		return "ai".contains(val.toLowerCase());
		}
}
