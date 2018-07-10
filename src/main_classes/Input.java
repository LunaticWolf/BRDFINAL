package main_classes;
import java.util.Arrays;
import java.util.Scanner;
import daoOperation.InsertToDb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//import java.util.HashSet;

import daoOperation.InsertDao;
import daoOperation.InsertToDb;
import entityPojo_customer.Customer;
//import validation.Rejection;
//import validation.ValidateMethods;


public class Input 
{
	//static Customer customer = new Customer();
	public static int record = 0;

public static void main(String args[])
{
	Customer customer = new Customer();
	
		int count = 1;
		// int affectedRows=0;

		System.out.println("Initiating the Process..\n");
		Scanner scanner = new Scanner(System.in);
		
		String server = "Oracle";
		System.out.println("Enter the File with Complete file location.\n");
		String location = scanner.nextLine();
		System.out.println("Enter the File name.");
		String fileName = scanner.next();
		System.out.println("Enter the file Extention.");
		String fileExtention = scanner.next();
		
		
		while (!(fileExtention.equals("txt")))
		{
			System.out.println("Enter a Valid File Extention.");
			fileExtention = scanner.next();
		}

		System.out.println("Enter Type of Rejection level you wish.\nPress 'F' for File Level Rejection.\n Press 'R' for Record Level Rejection.");
		String rejection = scanner.next();

		String str;
		
		
		try 
		  {
			//Perform 'Write' operation on the file -> ErrorFile.txt
			/*System.out.println("Enter the Location and name of the file to store error logs from the test file.");
			String ErrorLocation = scanner.next();
			FileWriter filewriter = new FileWriter("ErrorLocation");*/
			FileWriter filewriter = new FileWriter("F:\\ErrorRecords.txt");
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			
			//FileReader fileReader = new FileReader(location);
			//FileReader fileReader = new FileReader(location + "\\" + fileName + "." + fileExtention);
			FileReader fileReader = new FileReader("C:\\Users\\Administrator\\Desktop\\Test Cases\\testCase1.txt");
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			//InsertDao dao = new InsertToDb();

			System.out.println("Records under process");

			//public static Customer customer = new Customer();
			
			InsertDao dao = new InsertToDb();
			
			//Line-by-Line Breaking the record file into strings
			
			while ((str = bufferedreader.readLine()) != null) {
				String valuesSeparated[] = new String[18];
				record = record + 1;
				valuesSeparated = str.split("~",-1);
				int valcount = 0;
				try 
				{
					//Customer ID Auto Increment with each record
					customer.setCustomer_id(count);
					count = count + 1;
					System.out.print("Records Processed = "+count);
					//Customer Data
					System.out.println("\n0");
					customer.setCustomer_code(valuesSeparated[0]);
					System.out.print("1");
					customer.setCustomer_name(valuesSeparated[1]);
					System.out.print(" 2");
					customer.setCustomer_address1(valuesSeparated[2]);
					System.out.print(" 3");
					customer.setCustomer_address2(valuesSeparated[3]);
					System.out.print(" 4");
					customer.setCustomer_pinCode(Integer.parseInt(valuesSeparated[4]));
					System.out.print(" 5");
					customer.setEmail_address(valuesSeparated[5]);
					System.out.print(" 6");
					customer.setContact_number(valuesSeparated[6]);
					System.out.print(" 7");
					customer.setPrimaryContactPerson(valuesSeparated[7]);
					System.out.print(" 8");
					customer.setRecord_status(valuesSeparated[8]);
					System.out.print(" 9");
					customer.setActive_inactiveFlag(valuesSeparated[9]);
					System.out.print(" 10");
					customer.setCreate_date(valuesSeparated[10]);
					System.out.print(" 11");
					customer.setCreated_by(valuesSeparated[11]);
					System.out.print(" 12");
					customer.setModified_date(valuesSeparated[12]);
					System.out.print(" 13");
					customer.setModified_by(valuesSeparated[13]);
					System.out.print(" 14");
					customer.setAuthorized_date(valuesSeparated[14]);
					System.out.print(" 15");
					customer.setAuthorized_by(valuesSeparated[15]);
				}

				catch (ArrayIndexOutOfBoundsException e) 
				{
					System.out.println("\n\n\n\nError..!!\n Could Not Read Data off the file");
					e.printStackTrace();
					System.exit(0);
				}

				finally 
				{
                   System.out.println("\n Database:\n");
                   
                   dao.conditionCheck(server, str, rejection, customer, bufferedwriter);
				}

			}
			bufferedreader.close();
		} catch (Exception e) 
		{
			System.out.println("File not found");
			System.exit(0);
		}

		scanner.close();
		
	}
	
}
