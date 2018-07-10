package com.nu.nsbt.brd1.serverconnection;

//package Connection;



public class ConnectionFactory
{
	public static ConnectionI factoryIntial(String name)
	{
		if(name.equalsIgnoreCase("ORACLE"))
		{
			return new OracleConnection();
		}
		else if(name.equalsIgnoreCase("MYSQL"))
		{
			return new MySqlConnection();
		}
		
		else 
		{
			System.err.println("Select a valid Server ");
			return  null;
	}
	}
}
