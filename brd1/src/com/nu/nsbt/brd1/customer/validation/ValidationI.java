package com.nu.nsbt.brd1.customer.validation;

import com.nu.nsbt.brd1.customer.customerdata.Customer;
import java.util.TreeSet;


public interface ValidationI 
{
	public boolean validCustomerCode(Customer customer, TreeSet<String> set);

	public boolean validCustomerName(Customer customer);

	public boolean validPinCode(Customer customer);

	public boolean validEmail(Customer customer);

	public boolean validRecordStatus(Customer customer);

	public boolean validFlag(Customer customer);

}
