package com.nu.nsbt.brd1.database;
import com.nu.nsbt.brd1.customer.customerdata.*;
//package Insert;

//import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.io.FileWriter;
import java.sql.Connection;
//import java.util.HashSet;
import java.util.TreeSet;

//import Main.Customer;
//import com.nu.nsbt.brd1.customer.entrycontroller.*;

public interface Dao 
{
	//1.Conditional Check : For Rejection Entry..
	public void conditionCheck(String server, String str, String rejection, Customer customer,FileWriter bw );

	//2.Database Input Method
	public int inputbd(String server, Customer customer, Connection conn);

	//3.get Connection
	public Connection connection();
	
	//4.Array List fetch code for Customer Code
	public TreeSet<String> fetchCustomerCode();
}
