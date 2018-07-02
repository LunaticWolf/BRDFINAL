package daoOperation;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.util.HashSet;
import java.util.TreeSet;

import entityPojo_customer.Customer;

public interface InsertDao {
	public void conditionCheck(String server, String str, String rejection, Customer customer,BufferedWriter bw);

	public int inputbd(String server, Customer customer, Connection conn);

	public Connection connection(String server);
	public TreeSet<String> fetch_customer_code(String server);
}
