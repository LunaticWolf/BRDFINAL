package validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.TreeSet;

//import connection.ConnectionI;
//import connection.OracleConnection;
import daoOperation.InsertDao;
import daoOperation.InsertToDb;
import entityPojo_customer.Customer;
import main_classes.Input;

public class Rejection {

	ValidationI vm = new ValidateMethods();


	
	public void recordLevel(String server, String str, Customer customer,BufferedWriter bw) throws IOException {
		
		InsertDao dao = new InsertToDb();
	 TreeSet<String> set=dao.fetch_customer_code(server);
	 
		boolean code = vm.validCustomerCode(customer, set);
		set.add(customer.getCustomer_code());

		boolean name = vm.validCustomerName(customer);

		boolean pinCode = vm.validPinCode(customer);

		boolean record = vm.validRecordStatus(customer);

		boolean flag = vm.validFlag(customer);

		boolean email = vm.validEmail(customer);

		Connection conn = dao.connection(server);
		if (code && name && record && pinCode && flag && email) {
			int rowsAffected = dao.inputbd(server, customer, conn);

			if (rowsAffected > 0)
				System.out.println("done");

			else
				System.out.println("Some error ocuured");

			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		else {
			
			try {
				System.out.println("nhi aa rhe ");
				if(code==false)
				{
				bw.append(str);
				bw.newLine();
				bw.append("Error in code");
				bw.newLine();
				bw.flush();
				}
				else if(name==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in name");
					bw.newLine();
					bw.flush();
				}
				else if(pinCode==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in pincode");
					bw.newLine();
					bw.flush();
				}
				else if(record==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in record");
					bw.newLine();
					bw.flush();
				}
				else if(flag==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in flag value");
					bw.newLine();
					bw.flush();
				}
				else if(email==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in email format");
					bw.newLine();
					bw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void fileLevel(String server, String str, Customer customer,BufferedWriter bw) {
		InsertDao dao = new InsertToDb();
		TreeSet<String> set=dao.fetch_customer_code(server);
		
		int c=0;
		
		boolean code = vm.validCustomerCode(customer, set);
		set.add(customer.getCustomer_code());
		
		boolean name = vm.validCustomerName(customer);

		boolean pinCode = vm.validPinCode(customer);

		boolean record = vm.validRecordStatus(customer);

		boolean flag = vm.validFlag(customer);

		boolean email = vm.validEmail(customer);

		Connection conn = dao.connection(server);

		if (code && name && record && pinCode && flag && email) {
			int rowsAffected = dao.inputbd(server, customer, conn);
			c=c+1;
			

			if (rowsAffected > 0)
				System.out.println("done");
			else
				System.out.println("Some error ocuured");
		} 
		else {
			try {
				if(code==false)
				{
				bw.append(str);
				bw.newLine();
				bw.append("Error in code");
				bw.newLine();
				bw.flush();
				}
				else if(name==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in name");
					bw.newLine();
					bw.flush();
				}
				else if(pinCode==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in pincode");
					bw.newLine();
					bw.flush();
				}
				else if(record==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in record");
					bw.newLine();
					bw.flush();
				}
				else if(flag==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in flag value");
					bw.newLine();
					bw.flush();
				}
				else if(email==false)
				{
					bw.append(str);
					bw.newLine();
					bw.append("Error in email format");
					bw.newLine();
					bw.flush();
				}
				conn.rollback();
				

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			if(Input.noOfrows==c)
			{
				try {
					conn.commit();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}
}
