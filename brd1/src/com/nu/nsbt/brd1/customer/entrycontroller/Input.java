package com.nu.nsbt.brd1.customer.entrycontroller;
import com.nu.nsbt.brd1.database.*;
import com.nu.nsbt.brd1.customer.customerdata.Customer;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;



public class Input 
{
	public static int recordsProcessed=0;
	
	static Customer customer = new Customer();
	static BufferedReader bufferedreader;
	

	public static void main(String[] args) {
		
		int count = 1;
		String str;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Initiating the Process..\n");
		System.out.println("Enter Database Server you wish to use -\n 1. Oracle\n + 2. MySql\n + 3. Sqlserver\n");
		String server = scanner.next();
		System.out.println("\nEnter the File with Complete file location.");
		String location = scanner.next();
		System.out.println("\nEnter the File name.");
		String fileName = scanner.next();
		System.out.println("\nEnter the File Extention");
		String fileExtention = scanner.next();

		
		while (!(fileExtention.equals("txt"))) 
		{
			System.out.println("\nEnter a Valid File Extention.");
			fileExtention = scanner.next();
		}

		
		System.out.println("\n\nEnter Type of Rejection level you wish.\nPress 'F' for File Level Rejection.\nPress 'R' for Record Level Rejection.");
		String rejection = scanner.next();

		try 
		{
			FileReader filereader = new FileReader(location + ":/" + fileName + "."+ fileExtention);
			 bufferedreader = new BufferedReader(filereader);
			 
			 //BufferedWriter bw = null;
			FileWriter bw = new FileWriter("C:\\Users\\nsbt.trainee\\Desktop\\ErrorFile.txt", true);
			//BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			
			//bw = new BufferedWriter(fw);
			
			
			System.out.println("Records under process");	
			Dao dao = new InsertIntoDb();

			//Line-by-Line Breaking the record file into strings
			while ((str = bufferedreader.readLine()) != null) {
				String st[] = new String[20];
				recordsProcessed+=1;
				st = str.split("~", -1);
				
				
				
				try
				{
					customer.setCustomerId(count);
					count = count + 1;
					customer.setCustomerCode(st[0]);
					customer.setCustomerName(st[1]);
					customer.setCustomerAddress1(st[2]);
					customer.setCustomerAddress2(st[3]);
					customer.setCustomerPinCode(Integer.parseInt(st[4]));
					customer.setEmailAddress(st[5]);
					customer.setContactNumber(st[6]);
					customer.setPrimaryConatctPerson(st[7]);
					customer.setRecordStatus(st[8]);
					customer.setActiveInactiveFlag(st[9]);
					customer.setCreateDate(st[10]);
					customer.setCreatedBy(st[11]);
					customer.setModifiedDate(st[12]);
					customer.setModifiedBy(st[13]);
					customer.setAuthorizedDate(st[14]);
					customer.setAuthorizedBy(st[15]);
				}

				catch (ArrayIndexOutOfBoundsException e)
				{
					System.out.println("\n\n\nError..!!\n Could Not Read Data off the file");
					e.printStackTrace();
					System.exit(0);
				}

				finally 
				{
					System.out.println("\n Database:\n");
					dao.conditionCheck(server, str, rejection, customer,bw);
				}

				bufferedreader.close();
			}

		} 
		
		catch (Exception e) 
		{
			System.err.println("\nFile not found");
			e.printStackTrace();
			System.exit(0);
			
		}

		scanner.close();
	}
}
